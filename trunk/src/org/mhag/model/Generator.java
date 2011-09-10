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

	public Generator()
	{
		Arrays.fill(effects, -1);
		Arrays.fill(skills, -1);
		Arrays.fill(triggers, 0);

		scoreParaDefault[0] = 8;  //defense
		scoreParaDefault[1] = -20;  // negative skill
		scoreParaDefault[2] = 50;  // postive skill 
		scoreParaDefault[3] = 3;  // unused slots
		scoreParaDefault[4] = -10; // weapon slots in use

		for(int i = 0; i < 5; i++)
			scorePara[i] = scoreParaDefault[i];

		Arrays.fill(includeOpt, false);
	}

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

		gen.initJewel(aSet.getLowRank());
		//gen.testJewelSkills();

		//for(int i = 0; i < 100000; i++)
			gen.genMain(aSet);

		aSet.calcSet(gen.mhag, gen.mhagData);
		System.out.println(aSet.getRate());
		//gen.numerateTest();

	}

	public void initJewel(boolean lowRank)
	{
		//setup jewel information
		Arrays.fill(jewelNeg, false);
		Arrays.fill(jewelNegSkillInd, 0);
		for(int i = 0; i < 10; i++)
			Arrays.fill(jewelIDUsed[i], 0);
		for(int i = 0; i < 10; i++)
			Arrays.fill(jewelPoint[i], 0);
		for(int i = 0; i < 10; i++)
			Arrays.fill(jewelNegPoint[i], 0);

		for(int i = 0; i < numEffectOpt; i++)
		{
			Skill skill = mhagData.getSkill(skills[i]);
			for(int j = 1; j <= 3; j++)
			{
				int jewelID = skill.getJewelID(lowRank, j);

				if(jewelID >= 0)
				{
					Jewel jewel = mhagData.getJewel(jewelID);
					jewelIDUsed[i][j] = jewelID;
					jewelPoint[i][j] = jewel.getSkillPoint()[0];
					if(jewel.getNumSkill() == 2)
					{
						int jewel2ndSkillID = jewel.getSkillID()[1];
						for(int k = 0; k < numEffectOpt; k++)  //check 2nd skill in skill list
						{
							if(jewel2ndSkillID == skills[k])
							{
								jewelNeg[i] = true;
								jewelNegSkillInd[i] = k;   // mhag data independent
								jewelNegPoint[i][j] = jewel.getSkillPoint()[1];
								break;
							}
						}
					}

				}

			}
		}
		checkOrder();

	}

	// check possible order of skills to fill jewels
	public void checkOrder()
	{
		//testJewelSkills();
		for(int i = 0; i < 11; i++)
			Arrays.fill(skillOrder[i], 0);
		for(int i = 0; i < 11; i++)
			Arrays.fill(cyclePoint[i], 0);

		/*
		for(int i = 0; i < numEffectOpt; i++)
		{
			Skill skill = mhagData.getSkill(skills[i]);
			String skillName = skill.getSkillName();
			System.out.printf("%3d,%-10s,%-5b,%3d\n",i,skillName,jewelNeg[i],jewelNegSkillInd[i]);
		}
		 */

		for(int i = 1; i <= numEffectOpt; i++)
		{
			//boolean[] linked  = new boolean[i];
			for(int j = 0; j < i; j++)
			{
				skillOrder[i][j] = j;
			}

			for(int j = 0; j < i; j++)
			{
				int skillInd = skillOrder[i][j];
				if(jewelNeg[skillInd])  // has negative skill, connecting skills
				{
					int skillIndNext = jewelNegSkillInd[skillInd];
					if(skillIndNext >= i)continue;  //out of range, no count

					int skillIndNextPlace = -1;
					for(int k = j+1; k < i; k++)  // find the next skill after current place
					{
						if(skillOrder[i][k] == skillIndNext)
						{
							skillIndNextPlace = k;
							break;
						}
					}
					if(skillIndNextPlace >= 0) // swap skill to the next place
					{
						for(int k = skillIndNextPlace; k > j+1; k--)
							skillOrder[i][k] = skillOrder[i][k-1];
						skillOrder[i][j+1] = skillIndNext;
						continue;
					}

					for(int k = 0; k < j; k++) // find the next skill before current place
					{
						if(skillOrder[i][k] == skillIndNext)
						{
							skillIndNextPlace = k;
							break;
						}
					}
					if(skillIndNextPlace >= 0)
					{
						int lastBreak = -1;
						for(int k = j-1; k >= skillIndNextPlace; k--) //check last break point
						{
							int skillIndK = skillOrder[i][k];
							if(!jewelNeg[skillIndK] || (jewelNegSkillInd[skillIndK] != skillOrder[i][k+1]))
							{
								lastBreak = k;
								break;
							}
						}
						if(lastBreak == -1) // no break point, it's a cycle, no swap
						{
							cyclePoint[i][skillIndNextPlace] = 1;
							for(int k = skillIndNextPlace + 1; k < j; k++)
								cyclePoint[i][k] = 2;
							cyclePoint[i][j] = 3;
						}
                        else  // has break point , swap all linked skill to the front
						{
							int insertPlace = skillIndNextPlace;
							if(cyclePoint[i][skillIndNextPlace] > 1)
							{
								for(int k = skillIndNextPlace; k >= 0; k--) // found the first cycle point
									if(cyclePoint[i][k] == 1)
										insertPlace = k;
							}

							for(int k = lastBreak + 1; k <= j; k++) // swap a block of skills & cycle info
							{
								int currentPlace = skillOrder[i][k];
								for(int l = k; l >= k - lastBreak + insertPlace ; l--)
								{
									skillOrder[i][l] = skillOrder[i][l-1];
									cyclePoint[i][l] = cyclePoint[i][l-1];
								}
								skillOrder[i][k-lastBreak+insertPlace-1] = currentPlace;
								cyclePoint[i][k-lastBreak+insertPlace-1] = 0;
							}

						}

					}

				}

			}
		}

		/*
		for(int i = 1; i <= numEffectOpt; i++)
			System.out.println(Arrays.toString(skillOrder[i])+
					Arrays.toString(cyclePoint[i]));
		System.exit(0);
		 */
	}

	// automatically generate skill list (for jewel optimization only)
	public void generateSkillList(Set newSet)
	{
		Set aSet = new Set();
		aSet.copySetMin(newSet);
		aSet.calcSet(mhag, mhagData);

		int nSkill = aSet.getNumSkill();
		if(nSkill <= 0)return;

		int[] gap = new int[nSkill];
		int[] eff = new int[nSkill];
		int[] trig = new int[nSkill];
		for(int i = 0; i < nSkill; i++)
		{
			int skillID = aSet.getSkillID()[i];
			int skillPoint = aSet.getSkillPoint()[i];
			Skill skill = mhagData.getSkill(skillID);
			int nEffect = skill.getNumEffect();
			int pointLeft = 0;
			int effectID = 0;
			int point = 0;
			for(int j = 0; j < nEffect; j++)
			{
				point = skill.getEffectTrigger(j);
				if(point < 0)continue;
				pointLeft = point - skillPoint;
				effectID = skill.getEffectID(j);
				if(pointLeft > 0)break;
			}
			if(skill.getJewelID(aSet.getLowRank(), 1)  == 2)
				gap[i] = (pointLeft + 1)/2;
			else
				gap[i] = pointLeft;
			eff[i] = effectID;
			trig[i] = point;
			//System.out.printf("%d:%d %d %d\n",i, aSet.getSkillID()[i], eff[i], gap[i]);
		}

		int[] index = new int[nSkill];
		index =	MhagUtil.sortIndex(nSkill, gap);
		//System.out.println(Arrays.toString(index));

		for(int i = 0; i < 10; i++)
		{
			if(i == nSkill - 1)break;
			int ind = index[nSkill - 1 - i];
			skills[i] = aSet.getSkillID()[ind];
			effects[i] = eff[ind];
			triggers[i] = trig[ind];
			numEffectOpt = i + 1;
			//System.out.printf("%d: %d %d\n", i, skills[i], gap[ind]);
		}
		//System.out.println(numEffectOpt);

	}

	public void genMain(Set aSet)
	{
		if(genMode == 0)  // jewel optimization
			setOptimizer(aSet);
		//else if(genMode == 1)  // set search
			//setOptimizer(aSet);
//		else
//			aSet = setSearch(aSet);

	}

	// generator main operator for gui, output set code
	public String[] genMainGui(MhagGui mhagGui, Set aSet, int repeat)
	{
		int num = 0;
		if(genMode == 0)
			num = 1;
		else
			num = numOptSet;

		String[] setCodes = new String[num];

		if(genMode == 0)  // jewel optimization
		{
			//System.out.println(numEffectOpt);
			//System.out.println(Arrays.toString(skills));
			//System.out.println(Arrays.toString(effects));
			//System.out.println(Arrays.toString(triggers));
			int max = mhagGui.getProgressBar().getMaximum();

			int score = 0;
			int progress = 1;
			Set genSet = new Set();
			for(int i = 0; i < repeat; i++)
			{
				if(i == repeat/max * progress)
				{
					mhagGui.getProgressBar().setValue(progress);
					progress++;
				}

				genSet.copySetMin(aSet);  //get Set
				//genSet.setRate(this);
				score = setOptimizer(genSet);
			}

			genSet.setSetName(String.format("Set 1 (%d)", score));
			setCodes[0] = genSet.getSetCode();
			//System.out.println(setCodes[0]);
			/*
			mhag.setLogOpt(0);
			mhag.setOutLog(System.out);
			genSet.calcSet(mhag, mhagData);
			 */
		}

		return setCodes;
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

			//aSet.calcSet(mhag, mhagData);   //calculate set
			aSet.setRate(this);
			aSet.setGapPoint(this);
			aSet.setSlots(mhagData, 0);
			int nSlotTheory = checkSlotTheory(aSet.getSlots());
			int nSkillMax = checkSlotMinUsage(aSet.getGapPoint(), nSlotTheory);

			count++;
		}
		System.out.println(count);

	}

	public void readGenInput(String fileIn) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "Reading Generator Options From Input File ...");

		String errorLine = "    Error in Input File, Please Check!";
		Scanner in = new Scanner(new File(fileIn));
		Arrays.fill(effects, -1);
		Arrays.fill(skills, -1);
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
				numEffectOpt= MhagUtil.extractInt(args, 10, values);
			else if(opt.equals("mode")) //generator mode
			{
	  			if(args.length() != 0)
					genMode = Integer.valueOf(args);
			}
			else if(opt.equals("weapon"))
			{
	  			if(args.length() != 0)
					numWeaponSlotOpt = Integer.valueOf(args);
				if(numWeaponSlotOpt < -1 && numWeaponSlotOpt > 3)
					numWeaponSlotOpt = -1;
			}
		}

		for(int i =0; i < numEffectOpt; i++)
		{
			effects[i] = values[i];
			skills[i] = mhagData.getEffect(effects[i]).getSkillID();
			triggers[i] = mhagData.getEffect(effects[i]).getEffectTrigger();
		}

		MhagUtil.logLine(mhag,String.format("Mode: %d",genMode));
		MhagUtil.logLine(mhag,String.format("# of Weapon Slots: %d",numWeaponSlotOpt));
		MhagUtil.logLine(mhag,String.format("# of Effects: %d",numEffectOpt));
		MhagUtil.logLine(mhag,"Effects: "+Arrays.toString(effects));
		MhagUtil.logLine(mhag,"Skills: "+Arrays.toString(skills));
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

	// test jewel skills (skills that are connected by jewel negative skills)
	public void testJewelSkills()
	{
		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			Skill skill = mhagData.getSkill(i);
			int skillID2nd = -1;
			for (int j = 1; j < 4; j++)
			{
				int jewelID = skill.getJewelID(false, j);
				if(jewelID < 0)continue;
				Jewel jewel = mhagData.getJewel(jewelID);
				if(jewel.getNumSkill() == 1)continue;
				skillID2nd = jewel.getSkillID()[1];
			}
			if(skillID2nd < 0)continue;
			Skill skill2 = mhagData.getSkill(skillID2nd);
			int skillID3rd = -1;
			for (int j = 1; j < 4; j++)
			{
				int jewelID = skill2.getJewelID(false, j);
				if(jewelID < 0)continue;
				Jewel jewel = mhagData.getJewel(jewelID);
				if(jewel.getNumSkill() == 1)continue;
				skillID3rd = jewel.getSkillID()[1];
			}

			if(skillID3rd < 0)
			{
				System.out.println(skill.getSkillName()+"---"+skill2.getSkillName());
			}
			else
			{
				Skill skill3 = mhagData.getSkill(skillID3rd);
				System.out.println(skill.getSkillName()+"---"+
						skill2.getSkillName()+"---"+skill3.getSkillName());
			}
		}

	}

	// Jewel Optimization
	public int setOptimizer(Set aSet)
	{
		int scoreMax = 0;

		if(numWeaponSlotOpt < 0 || numWeaponSlotOpt > 3)
		{
			Set bestSet = new Set();
			bestSet.copySet(aSet);
			for(int i = 0; i < 4; i++)
			{
				Set newSet = new Set();
				newSet.copySet(aSet);
				int score = setOptimizer(newSet, i);
				if(score > scoreMax)
				{
					scoreMax = score;
					bestSet.copySet(newSet);
				}
			}
			aSet.copySet(bestSet);
		}
		else
			scoreMax = setOptimizer(aSet, numWeaponSlotOpt);

		return scoreMax;
	}

	public int setOptimizer(Set aSet, int numWeaponSlot)
	{
		//aSet.save(mhag, mhagData, System.out);
		for(int i = 0; i < 7; i++)
			aSet.setNumJewel(i, 0);
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 3; j++)
				aSet.setJewelID(i, j, 0);
		if(numWeaponSlot > 0)
			aSet.setInUse(5, true);

		aSet.setRate(this);

		aSet.setSlots(mhagData, numWeaponSlot);
		aSet.setGapPoint(this);

		/*
		System.out.println(aSet.getRate());
		for(int i = 0; i < numEffectOpt; i++)
		{
			Skill skill = mhagData.getSkill(skills[i]);
			System.out.printf("%3d: %-10s %3d\n", i, skill.getSkillName(), aSet.getGapPoint(i));
		}
		System.out.println(Arrays.toString(aSet.getSlots()));
		System.out.println(Arrays.toString(aSet.getSlotInfo()));

		for(int i = 1; i <= numEffectOpt; i++)
			System.out.println(Arrays.toString(getSkillOrder(i))+
					Arrays.toString(getCyclePoint(i)));
		 */

		optJewel(aSet);
		aSet.setRate(this);
		//System.out.println(aSet.getRate());

		//aSet.calcSet(mhag, mhagData);
		return aSet.getRate();
	}

	// jewel Optimization
	public void optJewel(Set aSet)
	{
		//testJewelSkills();

		int nSlotTheory = checkSlotTheory(aSet.getSlots());
		int nSkillMax = checkSlotMinUsage(aSet.getGapPoint(), nSlotTheory);
		boolean success = false;

		/*
		System.out.printf("theoretic # of slots: %d\n", nSlotTheory);
		System.out.printf("max possible skills: %d\n",nSkillMax);
		System.out.println(Arrays.toString(jewelNeg));
		System.out.println("SkillID(neg):");
		System.out.println(Arrays.toString(jewelNegSkillInd));
		System.out.println("SkillPoint:");
		for (int i = 0; i < nSkillMax; i++)
			System.out.println(Arrays.toString(jewelPoint[i])+Arrays.toString(jewelNegPoint[i]));
		System.out.println(Arrays.toString(aSet.getSlotInfo()));
		 */

		for(int i = nSkillMax; i > 0; i--)
		{
			success = adjustSlot(aSet, i);
			if(success) break;
		}

	}

	// check total number of slots ,including weapon slots
	public int checkSlotTheory(int[] slots)
	{
		int nSlot = 0;
		for(int i = 1; i <= 3; i++)
			nSlot += slots[i] * i;
		nSlot += slots[4] * slots[0]; //torso up regard as two or more slots
		return nSlot;
	}

	// check minimum possible slots Usage to fill gaps for each skill
	// return number of max possible activated skills (only these skills are considered later)
	// nSlotMaxTheory : theoretical max number of slots , including weapon slots
	// slotUsage : slot usage for each skill, int[][4]: for 1-3 slots
	public int checkSlotMinUsage(int[] gapPoint, int nSlotTheory)
	{
		//boolean lowRank = aSet.getLowRank();

		int nSlotNow = nSlotTheory;
		for(int i = 0; i < numEffectOpt; i++)
		{
			int point = gapPoint[i];
			if(point <= 0)continue;
			for(int j = 3; j > 0; j--)
			{
				int pointJewel = jewelPoint[i][j];
				if(pointJewel == 0)continue;
				if(j == 1 && pointJewel == 2) // +2 jewel for 1 slot
				{
					nSlotNow -= (point + 1)/2;
					point -= nSlotNow * 2;
				}else
				{
					nSlotNow -= point / pointJewel * j;
					point = point % pointJewel;
				}

			}
			if(nSlotNow < 0)
				return i;
		}

		return numEffectOpt;
	}

	//adjust slot for the set
	//int nSkill: # of skills considered
	public boolean adjustSlot(Set aSet, int nSkill)
	{
		for(int i = 0; i < 7; i++)
		{
			aSet.setNumJewel(i, 0);
			for(int j = 0; j < 3; j++)
				aSet.setJewelID(i, j, 0);
		}
		int[] gapPointNow = new int[10];
		int[] slotsNow = new int[5];
		int[] slotInfoNow = new int[7];
		for(int i = 0; i < 10; i++)
			gapPointNow[i] = aSet.getGapPoint(i);
		for(int i = 0; i < 5; i++)
			slotsNow[i] = aSet.getSlots(i);
		for(int i = 0; i < 7; i++)
			slotInfoNow[i] = aSet.getSlotInfo(i);

		optTorsoUp(aSet, nSkill, gapPointNow, slotsNow, slotInfoNow);

		int currentSkillInd = 0;
		boolean success = false;
		while (currentSkillInd < nSkill)
		{
			int currentSkill = getSkillOrder(nSkill, currentSkillInd);
			int cycleID = getCyclePoint(nSkill, currentSkillInd);
			if(cycleID == 1)
			{
				// make a copy of slot information
				int cycleSkillInd = currentSkillInd;
				int cycleSkill = currentSkill;

				int[] gapPointBack = new int[10];
				int[] slotsBack = new int[5];
				int[] slotInfoBack = new int[7];
				int[] numJewelBack = new int[7];
				int[][] jewelIDBack = new int[7][3];
				System.arraycopy(gapPointNow, 0, gapPointBack, 0, 10);
				System.arraycopy(slotsNow, 0, slotsBack, 0, 5);
				System.arraycopy(slotInfoNow, 0, slotInfoBack, 0, 7);
				for(int i = 0; i < 7; i++)
					numJewelBack[i] = aSet.getNumJewel(i);
				for(int i = 0; i < 7; i++)
					for(int j = 0; j < 3; j++)
						jewelIDBack[i][j] = aSet.getJewelID(i,j);

				//try a cicle
				int trailNo = 1;
				success = false;
				do
				{
					if(trailNo == 1)
					{
						do  //normal run
						{
							currentSkill = getSkillOrder(nSkill, currentSkillInd);
							cycleID = getCyclePoint(nSkill, currentSkillInd);
							success = setJewelForSkill(aSet, currentSkill, gapPointNow, slotsNow, slotInfoNow);
							//System.out.printf("%3d %6b\n", currentSkillInd, success);
							if(!success) return false;
							currentSkillInd++;
						}while(cycleID != 3);

					}else
					{
						System.arraycopy(gapPointBack, 0, gapPointNow, 0, 10);
						System.arraycopy(slotsBack, 0, slotsNow, 0, 5);
					
						System.arraycopy(slotInfoBack, 0, slotInfoNow, 0, 7);
						for(int i = 0; i < 7; i++)
							aSet.setNumJewel(i, numJewelBack[i]);
						for(int i = 0; i < 7; i++)
							for(int j = 0; j < 3; j++)
								aSet.setJewelID(i, j, jewelIDBack[i][j]);
						currentSkillInd = cycleSkillInd;

						// enhanced run
						gapPointNow[cycleSkill] += trailNo -1; // add more points

						do  //normal run
						{
							currentSkill = getSkillOrder(nSkill, currentSkillInd);
							cycleID = getCyclePoint(nSkill, currentSkillInd);
							success = setJewelForSkill(aSet, currentSkill, gapPointNow, slotsNow, slotInfoNow);
							//System.out.printf("%3d %6b\n", currentSkillInd, success);
							if(!success) return false;
							if(currentSkill == cycleSkill)
								gapPointNow[cycleSkill] -= trailNo -1; // change it back
							currentSkillInd++;
						}while(cycleID != 3);

					}

					trailNo++;
				} while((gapPointNow[cycleSkill] > 0) && (trailNo <10));  // if too many tails , quit

			}
			else
			{
				success = setJewelForSkill(aSet, currentSkill, gapPointNow, slotsNow, slotInfoNow);
				//System.out.printf("%3d %6b\n", currentSkillInd, success);
				if(!success) return false;
				currentSkillInd++;
			}

			//System.out.printf("%d %d\n", currentSkill, cycleID);
			//System.out.println(Arrays.toString(gapPointNow));

		}

		/*
		System.out.println(Arrays.toString(aSet.getNumJewel()));
		for(int i = 0; i < 7; i++)
			System.out.println(Arrays.toString(aSet.getJewelID(i)));
		 */

		return true;
	}

	// check and set torso slots (if torso up) when # slots >= 2
	// the left torsoUp is just for 1 slot usage only, or there is no other better option
	public void optTorsoUp(Set aSet, int nSkill, int[] gapPointNow, int[] slotsNow, int[] slotInfoNow)
	{
		if(slotsNow[4] <= 1) return;

		boolean found = false;
		if(slotsNow[4] == 3)
		{
			found = setTorsoUp(aSet, 3, nSkill, gapPointNow, slotsNow, slotInfoNow);
			if(found)return;
		}
		if(slotsNow[4] >=2)
		{
			found = setTorsoUp(aSet, 2, nSkill, gapPointNow, slotsNow, slotInfoNow);
			//if(found)return;
		}
		return;
	}

	public boolean setTorsoUp(Set aSet, int nSlot, int nSkill, int[] gapPointNow, int[] slotsNow, int[] slotInfoNow)
	{
		for(int i = 0; i < nSkill; i++)
		{
			int point = jewelPoint[i][nSlot];
			if((point > 0) &&  //has jewel with nSlot number of slots
				(gapPointNow[i]+1 >= point*slotsNow[0]))
				// gapPoint +1 : it's possible to obtain 1 extra point, without wasting a slot
				// ex: 7 points, ooo +4 , o +1;
				// with normal method : ooo x1 , o x3  = 7 points
				// with torso up : ooX2 = 8 points
				// they use same number of slots
			{
				addJewel(aSet, i, nSlot, nSlot, true, gapPointNow, slotsNow, slotInfoNow);
				return true;
			}
		}
		return false;
	}

	public boolean setJewelForSkill(Set aSet, int currentSkill, int[] gapPointNow, int[] slotsNow, int[] slotInfoNow)
	{
		if(gapPointNow[currentSkill] <= 0)return true;
		int[] points = jewelPoint[currentSkill];

		for(int i = 3; i > 0 ; i--)
		{
			int pointJewel = points[i];
			if(pointJewel == 0)continue;

			while((gapPointNow[currentSkill] >= pointJewel) ||
					((gapPointNow[currentSkill] > 0) && (i == 1)) ||
					((jewelNeg[currentSkill] && (i >= 2) && (gapPointNow[currentSkill]+1 >= pointJewel))))
			{
				if(slotsNow[i] > 0)  // found matched piece
				{
					addJewel(aSet, currentSkill, i, i, false, gapPointNow, slotsNow, slotInfoNow);
				}
				else if ((i == 1) && (slotsNow[4] > 0) &&
					(((points[1] == 1) && (gapPointNow[currentSkill] >= slotsNow[0])) ||
					((points[1] == 2) && (gapPointNow[currentSkill]+1 >= slotsNow[0]*2))))   //check 1-slot torso up skill
				{
					addJewel(aSet, currentSkill, 1, 1, true, gapPointNow, slotsNow, slotInfoNow);
				}
				else if((i < 3) &&(slotsNow[i+1] > 0))  // check piece with 1 more slot
				{
					addJewel(aSet, currentSkill, i, i+1, false, gapPointNow, slotsNow, slotInfoNow);
				}
				else if ((i == 1) && slotsNow[i+2] > 0) // check piece with 2 more slot
				{
					addJewel(aSet, currentSkill, i, i+2, false, gapPointNow, slotsNow, slotInfoNow);
				}
				else if(slotsNow[4] > 0)  //check torso up if no match
				{
					addJewel(aSet, currentSkill, 1, 1, true, gapPointNow, slotsNow, slotInfoNow);
				}
				else
				{
					break;  // no solution
				}

			}

		}
		if(gapPointNow[currentSkill] > 0)
			return false;
		else
			return true;
	}

	// add jewel to the set, update slots, gapPoint and numSlots
	public void addJewel(Set aSet, int currentSkill, int nSlot, int pieceSlot, boolean ifTorso,
			int[] gapPointNow, int[] slotsNow, int[] slotInfoNow)
	{
		//System.out.printf(Arrays.toString(slotInfoNow));
		//System.out.printf(Arrays.toString(slotsNow));

		int bodyPart = 0;
		if(ifTorso)
		{
			slotsNow[4] -= nSlot;
			bodyPart = 1;
			gapPointNow[currentSkill] -= jewelPoint[currentSkill][nSlot]*slotsNow[0];
			if(jewelNeg[currentSkill])
				gapPointNow[jewelNegSkillInd[currentSkill]] -= jewelNegPoint[currentSkill][nSlot]*slotsNow[0];
		}
		else
		{
			slotsNow[pieceSlot] -= 1;
			int restSlot = pieceSlot - nSlot;
			if(restSlot > 0) slotsNow[restSlot] += 1;

			gapPointNow[currentSkill] -= jewelPoint[currentSkill][nSlot];
			if(jewelNeg[currentSkill])
				gapPointNow[jewelNegSkillInd[currentSkill]] -= jewelNegPoint[currentSkill][nSlot];

			for(int i = 0; i < 7; i++)
			{
				if(slotInfoNow[i] == pieceSlot)
				{
					if((i == 1) && (slotsNow[4] > 0))continue;
					bodyPart = i;
					break;
				}
			}
			//System.out.printf("here %d %d %d\n", bodyPart, nSlot, jewelIDUsed[currentSkill][nSlot]);
		}
		slotInfoNow[bodyPart] -= nSlot;

		int nJewel = aSet.getNumJewel(bodyPart) + 1;
		aSet.setNumJewel(bodyPart, nJewel);  
		aSet.setJewelID(bodyPart, nJewel - 1, jewelIDUsed[currentSkill][nSlot]);
		//System.out.printf("%d %d %d %d\n",currentSkill, nSlot, pieceSlot, bodyPart);
		//System.out.printf("%d %d %d\n",nJewel, jewelIDUsed[currentSkill][nSlot], slotsNow[4]);

	}

	public int[] getArmorList(int bodyPart)
	{
		return null;
	}

	public Mhag getMhag() {return mhag;}
	public MhagData getMhagData() {return mhagData;}
	public void setMhag(Mhag aMhag) {mhag = aMhag;}
	public void setMhagData(MhagData aMhagData) {mhagData = aMhagData;}

	public int getNumEffectOpt() {return numEffectOpt;}

	public void setNumEffectOpt(int num) {numEffectOpt = num;}

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

	public int getNumWeaponSlot() {return numWeaponSlotOpt;}
	public void setNumWeaponSlot(int nSlot) {numWeaponSlotOpt = nSlot;}

	public boolean getBlade() {return blade;}
	public void setBlade(boolean ifBlade) {blade = ifBlade;}

	public int getGenMode() {return genMode;}
	public void setGenMode(int mode) {genMode = mode;}

	public boolean[] getIncludeOpt() {return includeOpt;}
	public boolean getIncludeOpt(int ind) {return includeOpt[ind];}
	public void setIncludeOpt(int ind, boolean incl) {includeOpt[ind] = incl;}

	public int[] getJewelIDUsed(int ind) {return jewelIDUsed[ind];}
	public int getJewelIDUsed(int ind, int nSlot) {return jewelIDUsed[ind][nSlot];}

	public int[] getJewelPoint(int ind) {return jewelPoint[ind];}
	public int getJewelPoint(int ind, int nSlot) {return jewelPoint[ind][nSlot];}

	public int[] getJewelNegPoint(int ind) {return jewelNegPoint[ind];}
	public int getJewelNegPoint(int ind, int nSlot) {return jewelNegPoint[ind][nSlot];}

	public int[] getJewelNegSkillInd() {return jewelNegSkillInd;}
	public int getJewelNegSkillInd(int ind) {return jewelNegSkillInd[ind];}

	public boolean[] getJewelNeg() {return jewelNeg;}
	public boolean getJewelNeg(int ind) {return jewelNeg[ind];}

	public int[] getSkillOrder(int nSkill) {return skillOrder[nSkill];}
	public int getSkillOrder(int nSkill, int ind) {return skillOrder[nSkill][ind];}

	public int[] getCyclePoint(int nSkill) {return cyclePoint[nSkill];}
	public int getCyclePoint(int nSkill, int ind) {return cyclePoint[nSkill][ind];}

	public int[] getScorePara() {return scorePara;}
	public int getScorePara(int ind) {return scorePara[ind];}
	public void setScorePara(int ind, int value) {scorePara[ind] = value;}
	public void setScoreParaDefault()
	{
		for(int i = 0; i < 5; i++)
			scorePara[i] = scoreParaDefault[i];
	}

	public int getNumOptSet() {return numOptSet;}
	public void setNumOptSet(int value) {numOptSet = value;}
	public void setNumOptSetDefault() {numOptSet = numOptSetDefault;}


	private Mhag mhag;  //local mhag data
	private MhagData mhagData;  //local mhagData data
	// charm data for generator
  	private Charm[] charmList;

	//generator options
	private int genMode = 0; //mode 0: jewl only, 1: partial search , 2. full set search
	private boolean blade = true; // blademaster/gunner
	private boolean[] includeOpt = new boolean[4]; // lr, hr, piercing, charm
	private int numWeaponSlotOpt = 0;  //max # of weapon slots, as an input option

	//scoring parameters : defense, skillNeg, skillPos, skillPos2, slotLeft, slotWeapon
	private int[] scorePara = new int[5];
	private int[] scoreParaDefault = new int[5];
	private int numOptSet = 20;  //number of output sets
	private int numOptSetDefault = 20;  //number of output sets

	//generator data
	private int numEffectOpt = 0;
	private int[] effects = new int[10]; //effect list
	private int[] skills = new int[10];  //skill list for corresponding effects
	private int[] triggers = new int[10];  //trigger points, for fast access

	//possible jewels
	private int[][] jewelIDUsed = new int[10][4];  //jewelID values
	private int[][] jewelPoint = new int[10][4];  //jewelPoint values
	private int[][] jewelNegPoint = new int[10][4];  //jewelPoint negative values
	private int[] jewelNegSkillInd = new int[10];  //jewelSkill index [0..9]
	private boolean[] jewelNeg = new boolean[10];  //if negative value should be considered
	private int[][] skillOrder = new int[11][10]; // skill order
	private int[][] cyclePoint = new int[11][10];  //negative skill cycle point
	// 1: cycle start point; 2: cycle middle; 3: cycle end point; 0: others

}
