package org.mhag.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

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

		/* to simplify the problem
		 * current version of Mhag doesn't support charm auto search
		gen.checkCharmLimit();
	        gen.createCharmLookupTable();
		 */

		gen.genMain(aSet);

		//gen.numerateTest();

	}

	public void genMain(Set aSet)
	{
		if(genMode == 0)  // jewel optimization
			setOptimizer(aSet);
		else if(genMode == 1)  // set search
			System.exit(0); //not ready yet, tifa
			//setOptimizer(aSet);
//		else
//			aSet = setSearch(aSet);

	}

	// Jewel Optimization
	public void setOptimizer(Set aSet)
	{
		//aSet.save(mhag, mhagData, System.out);

		aSet.setRate(this);

		checkGap(aSet);
		aSet.checkSlot(mhagData, slots);

		System.out.println(aSet.getRate());
		for(int i = 0; i < numGap; i++)
		{
			Skill skill = mhagData.getSkill(gapSkillID[i]);
			System.out.printf("%3d: %-10s %3d\n", i, skill.getSkillName(), gapPoint[i]);
		}
		System.out.println(Arrays.toString(slots));

		optJewel(aSet);

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

	// check gaps of skill points for the generator
	public void checkGap(Set set)
	{
		boolean[] skillMatched = new boolean[10];
		numGap = 0;
		Arrays.fill(gapPoint, 0);
		Arrays.fill(gapSkillID, 0);
		Arrays.fill(skillMatched, false);

		for(int i = 0; i < set.getNumSkill(); i++)
		{
			int id = set.getSkillID()[i];
			int skillInd = Set.matchID(skills,id);
			if(skillInd == -1)   //possible negative skills
			{
				if(mhagData.getSkill(id).getHasNegative()) // only nega skills
				{
					if(mhagData.getSkill(id).getBGSpec(set.getBlade())) //not include b/g specific nega skills
					{
						gapPoint[numGap] = -10 - set.getSkillPoint()[i]; //negative skill trigger always at -10
						gapSkillID[numGap] = id;
						numGap++;
					}
				}
				//minus number, meaning points left to reach negative effect
			}
			else  //matched query skills
			{
				gapPoint[numGap] = triggers[skillInd] - set.getSkillPoint()[i];
				gapSkillID[numGap] = id;
				numGap++;
				skillMatched[skillInd] = true;
			}

		}

		// other query skills
		for(int i = 0; i < skills.length; i++)
		{
			if(skills[i] < 0)continue; // not used
			if(skillMatched[i])continue;
			gapPoint[numGap] = triggers[i];
			gapSkillID[numGap] = skills[i];
			numGap++;
		}
	}

	// show available jewels based on skills
	public void showJewels()
	{
		boolean lowRank = true;
		for(int i = 0; i < Skill.skillIDTot; i++)
		{
			Skill skill = mhagData.getSkill(i);
			System.out.printf("%3d %-10s: ", i, skill.getSkillName());
			for(int j = 1; j < 4; j++)
			{
				if(skill.getJewelID(lowRank, j) < 0)continue;
				System.out.printf("(%d) %3d ",j,skill.getJewelSkillPoint(lowRank, j));
			}
			System.out.println();
		}
		lowRank = false;
		for(int i = 0; i < Skill.skillIDTot; i++)
		{
			Skill skill = mhagData.getSkill(i);
			System.out.printf("%3d %-10s: ", i, skill.getSkillName());
			for(int j = 1; j < 4; j++)
			{
				if(skill.getJewelID(lowRank, j) < 0)continue;
				System.out.printf("(%d) %3d ",j,skill.getJewelSkillPoint(lowRank, j));
			}
			System.out.println();
		}
	}

	// jewel Optimization
	public void optJewel(Set aSet)
	{
		//int nSlotMaxTheory = checkSlotMaxTheory();
		//int nSkillMax = checkSlotMinUsage(aSet, nSlotMaxTheory);
		//System.out.printf("max possible slots: %d\n", nSlotMaxTheory);
		//System.out.printf("max possible skills: %d\n",nSkillMax);

	 	adjustSlot(aSet);

	}

	// check total number of slots ,including weapon slots
	public int checkSlotMaxTheory()
	{
		int nSlot = 3;  //max weapon slot
		for(int i = 1; i <= 3; i++)
			nSlot += slots[i] * i;
		nSlot += slots[4] * slots[0]; //torso up regard as two or more slots
		return nSlot;
	}

	// check minimum possible slots Usage to fill gaps for each skill
	// return number of max possible activated skills (only these skills are considered later)
	// nSlotMaxTheory : theoretical max number of slots , including three weapon slots
	// slotUsage : slot usage for each skill, int[][4]: for 1-3 slots
	public int checkSlotMinUsage(Set aSet, int nSlotMaxTheory)
	{
		boolean lowRank = aSet.getLowRank();

		int nSlotNow = nSlotMaxTheory;
		for(int i = 0; i < numGap; i++)
		{
			int point = gapPoint[i];
			if(point <= 0)continue;
			Skill skill = mhagData.getSkill(gapSkillID[i]);
			for(int j = 3; j > 0; j--)
			{
				if(skill.getJewelID(lowRank, j) < 0)continue;
				int pointJewel = skill.getJewelSkillPoint(lowRank, j);
				nSlotNow -= point / pointJewel * j;
				point = point % pointJewel;
			}
			if(nSlotNow < 0)
				return i;
		}

		return numGap;
	}

	//adjust slot for the set
	//int nSkillMax: # of skills considered
	public void adjustSlot(Set aSet)
	{

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

	// optimizer data
	private int[] gapPoint = new int[100];
	private int[] gapSkillID = new int[100];
	private int numGap;
	private int[] slots = new int[5];    //slots summary based on number of slots
	// 0: num_torso; 1-3: # of x-slot pieces; 4: # of slots for torso up pierce

	//for auto-guard skill;
	//private int autoGuardID = -1;
	//private	String autoGuardClass = "";

}
