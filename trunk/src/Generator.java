
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @program MHAG
 * @ Generator Class
 * @version 1.0
 * @author Tifa@mh3
 */
public class Generator {

	static void generator(Mhag aMhag, MhagData aMhagData) throws FileNotFoundException
	{
		Generator gen = new Generator();
		gen.mhag = aMhag;
		gen.mhagData= aMhagData;

		Set aSet = new Set();
		aSet.setSetFromFile(gen.mhag, gen.mhag.getFileIn());
		boolean pass = aSet.checkSet(gen.mhag, gen.mhagData);

		if(!pass)
		{
			MhagUtil.logLine(gen.mhag, "Incorrect set read from input file!");
			return;
		}

		gen.readGenInput(gen.mhag.getFileIn());

		gen.genMain(aSet);

		//gen.numerateTest();

	}

	public void genMain(Set aSet)
	{
		if(genMode == 0)
			setOptimizer(aSet, true);
		else if(genMode == 1)
			setOptimizer(aSet, false);
//		else
//			aSet = setSearch(aSet);

	}

	public void setOptimizer(Set aSet, boolean jewelOnly)
	{
		//aSet.save(mhag, mhagData, System.out);

		aSet.setRate(this);

		int[] gaps = aSet.checkGap(this);

		System.out.println(aSet.getRate());
		System.out.println(Arrays.toString(gaps));

		//aSet.calcSet(mhag, mhagData);
	}

	public void numerateTest()  // only for test
	{
		mhag.setLogOpt(2); //off log

		int[] dim = new int[5];
		int[] indices = new int[5];
		boolean[] inUse = new boolean[5];
		Set aSet = new Set();
		Arrays.fill(inUse,true);

		int num = 1;
		for (int i =0; i < 5; i++)
		{
			dim[i] = 10; //Armor.getArmorIDTot()[i];
			num *= dim[i];
		}

		int count = 0;
		for (int i = 0; i < num; i++)
		{
			indices = MhagUtil.getIndexArray(i, dim); //Armor.getArmorIDTot());
			aSet.init();
			aSet.setArmorID(indices);

			for (int j = 0; j < 5; j++)
				aSet.setInUse(j,inUse[j]);

			boolean pass = aSet.checkSet(mhag, mhagData);  //check set
			if(!pass)continue;
			aSet.calcSet(mhag, mhagData);   //calculate set

			count++;
		}

	}

	public void readGenInput(String fileIn) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "Reading Generator Options From Input File ...");

		String errorLine = "    Error in Input File, Please Check!";
		Scanner in = new Scanner(new File(fileIn));
		Arrays.fill(effects, -1);
		Arrays.fill(skills, -2);
		Arrays.fill(triggers, 0);
		int[] values = new int[10];

		while (in.hasNext())
		{
			String line = in.nextLine();
			if((line.startsWith("!")) ||
				(line.startsWith("#")))continue;
			// System.out.println(line);

			// process a line
			int splitPos = MhagUtil.extractWordPos(line, 0);
			if( splitPos == -1)  MhagUtil.logLine(mhag, errorLine);

			// first part: option word
			String opt = MhagUtil.extractWord(line, 0, splitPos);
			// second part: arguments
			String args = MhagUtil.extractWord(line, splitPos +1, -1);

			if(opt.equals("effects"))  // effect list
				numEffect= MhagUtil.extractInt(args, 10, values);
			else if(opt.equals("mode")) //generator mode
			{
	  			if(args.length() != 0)
					genMode = Integer.valueOf(args);
			}
		}

		for(int i =0; i < numEffect; i++)
		{
			effects[i] = values[i];
			skills[i] = mhagData.getEffect(effects[i]).getSkillID();
			triggers[i] = mhagData.getEffect(effects[i]).getEffectTrigger();
		}

		MhagUtil.logLine(mhag,String.format("Mode: %d",genMode));
		MhagUtil.logLine(mhag,String.format("# of Effects: %d",numEffect));
		MhagUtil.logLine(mhag,"Effects: "+Arrays.toString(effects));
		MhagUtil.logLine(mhag,"Skills: "+Arrays.toString(skills));
	}

	public Mhag getMhag() {return mhag;}
	public MhagData getMhagData() {return mhagData;}

	public int getNumEffect() {return numEffect;}

	public void setNumEffect(int num) {numEffect = num;}

	public int[] getEffects() {return effects;}
	public int getEffects(int ind) {return effects[ind];}

	public void setEffects(int[] effectID) {effects = effectID;}
	public void setEffects(int ind, int effectID) {effects[ind] = effectID;}

	public int[] getSkills() {return skills;}
	public int getSkills(int ind) {return skills[ind];}

	public void setSkills(int[] skillID) {skills = skillID;}
	public void setSkills(int ind, int skillID) {skills[ind] = skillID;}

	public int[] getTriggers() {return triggers;}
	public int getTriggers(int ind) {return triggers[ind];}

	public void setTriggers(int[] points) {triggers = points;}
	public void setTriggers(int ind, int point) {triggers[ind] = point;}

	private Mhag mhag;  //local mhag data
	private MhagData mhagData;  //local mhagData data

	private int genMode = 0; //mode 0: jewl only, 1: jewl+charm onlyh, 2. set search

	//generator data
	private int numEffect = 0;
	private int[] effects = new int[10]; //effect list
	private int[] skills = new int[10];  //skill list for corresponding effects
	private int[] triggers = new int[10];  //trigger points, for fast access
}
