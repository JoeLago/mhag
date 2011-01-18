
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

		gen.checkCharmLimit();
	        gen.createCharmLookupTable();

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

	public void checkCharmLimit()
	{
		//initialize
		skillClassMax = 0;
		skillPointMax = 0;
		numSlotMax = 0;

		for(int i = 0; i < 2; i++)
		{
			Arrays.fill(charmPointMax[i], 0);
			Arrays.fill(charmPoint2nd[i], 0);
			Arrays.fill(charmSlotMax[i], 0);
		}

		//check auto-guard
		for(int i = 0; i < Skill.skillIDTot; i++)
		{
			Skill skill = mhagData.getSkill(i);
			if(skill.getSkillName().equals("Auto-Guard"))
			{
				autoGuardID = i;
				autoGuardClass = skill.getSkillClass();
			}
		}

		for(int i = 0; i < Charm.charmIDTot; i++)
		{
			Charm charm = mhagData.getCharm(i);
			if(charm.getSkillClass()[0].equals(autoGuardClass))continue;
			int[] ind = charm.getCharmInd();
			int nSlot = charm.getNumSlot();
			int point = charm.getSkillPoint()[0];

			if(nSlot > charmSlotMax[ind[0]][ind[1]])
				charmSlotMax[ind[0]][ind[1]] = nSlot;

			if(point > charmPointMax[ind[0]][ind[1]])
				charmPointMax[ind[0]][ind[1]] = point;

			if(ind[1] > skillClassMax) skillClassMax = ind[1];
			if(nSlot > numSlotMax) numSlotMax = nSlot;
			if(point > skillPointMax) skillPointMax = point;
		}

		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(charmPointMax[i][j] == 0)
					limitedMax[i][j] = false;
				else
					limitedMax[i][j] = true;
			}
		}

		//check 2nd time, to find 2nd max
		for(int i = 0; i < Charm.charmIDTot; i++)
		{
			Charm charm = mhagData.getCharm(i);
			if(charm.getSkillClass()[0].equals(autoGuardClass))continue;
			int[] ind = charm.getCharmInd();
			int point = charm.getSkillPoint()[0];

			if(charm.getNumSlot() == charmSlotMax[ind[0]][ind[1]])
			{
				if(point == charmPointMax[ind[0]][ind[1]])
					limitedMax[ind[0]][ind[1]] = false;
				else if(point > charmPoint2nd[ind[0]][ind[1]])
					charmPoint2nd[ind[0]][ind[1]] = point;
			}
		}

		for(int i = 0; i < skillClassMax + 1; i++)
			System.out.printf("%3d: %3d %3d, %3d %3d\n",i,charmPointMax[0][i],
				charmSlotMax[0][i],charmPointMax[1][i],charmSlotMax[1][i]);
		for(int i = 0; i < skillClassMax + 1; i++)
			System.out.printf("%3d: %7s %3d, %7s %3d\n",i,limitedMax[0][i],charmPoint2nd[0][i],
				limitedMax[1][i],charmPoint2nd[1][i]);
		System.out.printf("%d, %d, %d\n",skillClassMax,skillPointMax, numSlotMax);

	}

	public void createCharmLookupTable()
	{
		// create table, initial it as null (-1)
		charmLookupTable = new int[skillClassMax + 1][numSlotMax + 1][skillPointMax + 1];
		for(int i = 0; i < skillClassMax + 1; i++)
			for(int j = 0; j < numSlotMax + 1; j++)
				Arrays.fill(charmLookupTable[i][j], -1);

		for(int i = 0; i < Charm.charmIDTot; i++)
		{
			Charm charm = mhagData.getCharm(i);
			if(charm.getSkillClass()[0].equals(autoGuardClass))continue;
			if((charm.getNumSkill() == 2) &&
				(charm.getSkillPoint()[1] != -10))continue;  // B-10; A-10 (maybe only in mh3)

			int skillClassInd = charm.getCharmInd()[1];
			int point = charm.getSkillPoint()[0];
			int nSlot = charm.getNumSlot();

			charmLookupTable[skillClassInd][nSlot][point] = i;
		}

		for(int i = 0; i < skillClassMax + 1; i++)
			for(int j = 0; j < numSlotMax + 1; j++)
			{
				System.out.printf("%3d, %3d: ", i,j);
				for(int k = 0; k < skillPointMax + 1; k++)
					System.out.printf("%3d ",charmLookupTable[i][j][k]);
				System.out.println();
			}

	}

	//get charmID from skill points
	public static int getCharmID(String skillClass, int skillPoint, int numSlot)
	{
		int id = -1;

		return id;
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

	//generator charm data
	private int[][] charmPointMax = new int[2][10];  //(lr/hr) skill limit
	private int[][] charmSlotMax = new int[2][10];   //(lr/hr) charm limit
	private int skillClassMax = 0; //max skill class number
	private int skillPointMax = 0; //max skill points
	private int numSlotMax = 0; //max number of slots
	private boolean[][] limitedMax = new boolean[2][10];
	// true: max point and max slot can't be obtained together
	private int[][] charmPoint2nd = new int[2][10]; //max possible point, if max slot

	private int[][][] charmLookupTable;
	// index: 1st: skillClassMax; 2nd: numSlotMax; 3rd: skillPointMax;

	//for auto-guard skill;
	private int autoGuardID = -1; 
	private	String autoGuardClass = "";

}
