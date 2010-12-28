/**
 * @program MHAG
 * @ MhagData Class , store MHAG data
 * @version 1.0
 * @author Tifa@mh3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class MhagData {

	public MhagData()
	{
	}

	// read data file
	public void readFile(Mhag mhag) throws FileNotFoundException
	{
		readSkill(mhag);
		readJewel(mhag);
		readArmor(mhag);
		readCharm(mhag);
	}

	// process data right after access
	public void dataPreProc()
	{
		genEffectList();
		preProcessJewelList();
		preProcessArmorList();
		preProcessCharmList();
		preProcessSkillList();
	}

	// process data right after access
	public void showReference(Mhag mhag) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "Gernerate Reference Files");
		genRefSkill();
		genRefEffect();
		genRefJewel();
		genRefArmor();
		genRefCharm();
		genRefSkillClass();
		genRefCompleteSet();
	}

	// read skill from skill file
	public void readSkill(Mhag mhag) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(fileSkill));

		// check total # of skills
		int nMax = 0;
		String line = "";
		while(in.hasNext())
		{
			line = in.nextLine();
			if(line.startsWith("#"))continue;
			nMax++;
		}
		String logLine =  String.format
			("Total Number of Skills : %d",nMax);
		MhagUtil.logLine(mhag, logLine);

		in.close();

		skillList = new Skill[nMax];
		for (int i = 0; i < nMax; i++)
		{
			skillList[i] = new Skill();

		}
		Scanner in2 = new Scanner(new File(fileSkill));

		// read skill entry
		int ioErr = 0;
		int skillIndex = 0;
		while(in2.hasNext())
		{
			line = in2.nextLine();
			if(line.startsWith("#"))continue;
			// System.out.printf("%d\n", skillIndex);
			// System.out.println(line);
			ioErr = Skill.setSkillFromLine
				(line, skillList[skillIndex]);
			if(ioErr != 0)
			{
				MhagUtil.logLine(mhag,
					"Error Found in Skill File");
				return;
			}
			skillIndex++;
		}

	}

	// generate effect list
	public void genEffectList()
	{
		int nMax = 0;
		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			nMax += skillList[i].getNumEffect();
		}
		effectList = new Effect[nMax];

		for (int i = 0; i < nMax; i++)
		{
			effectList[i] = new Effect();
		}

		int n = 0;
		for (int i =0; i < Skill.skillIDTot; i++)
		{
			Skill skill = skillList[i];
			for (int j = 0; j < skill.getNumEffect(); j++)
			{
				effectList[n].setEffectFromSkill(skill, j);
				skill.setEffectID(j,n);
				n++;
			}
		}

	}

	// pre process jewel list (skill id from skill name )
	public void preProcessJewelList()
	{
		for (int i = 0; i < Jewel.jewelIDTot; i++)
		{
			Jewel aJewel = jewelList[i];
			int[] id = new int[2];
			Arrays.fill(id, 0);

			for (int j = 0; j < aJewel.getNumSkill(); j++)
			{
				id[j] = getSkillIDFromName
					(aJewel.getSkillName()[j]);
			}
			aJewel.setSkillID(id);

		}

	}

	// pre process Armor list (skill id from skill name )
	public void preProcessArmorList()
	{
		String armorName = "";
		for (int i = 0; i < Armor.getArmorMax(); i++)
		{
			for (int j = 0; j < 5; j++)
			{
				Armor armor = armorList[j][i];
				int[] id = new int[5];
				Arrays.fill(id, 0);
				for (int k = 0; k < armor.getNumSkill(); k++)
				{
					id[k] = getSkillIDFromName
						(armor.getSkillName()[k]);
				}
				armor.setSkillID(id);
			}

		}

	}

	// pre process skill list (genate skill list in class)
	public void preProcessSkillList()
	{
		indexSkillInClass = new int[5][Skill.skillIDTot];
		numSkillInClass = new int[5];
		Arrays.fill(numSkillInClass, 0);
		for (int i = 0; i < 5; i++)
			Arrays.fill(indexSkillInClass[i], 0);
		String classStr = "ABCD";
		String className = "";
		int classID = 0;

		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			className = skillList[i].getSkillClass();
			classID = classStr.indexOf(className);
			if( classID == -1)
			{
				classID = 4;
			}
			indexSkillInClass[classID][numSkillInClass[classID]++] =
				skillList[i].getSkillID();
		}

	}

	// pre process charm list (charm name , percentage )
	public void preProcessCharmList()
	{
		int[] numInClass = new int[5];
		Arrays.fill(numInClass, 0);
		int charmClass = 0;
		for (int i = 0; i < Charm.charmIDTot; i++)
		{
			Charm charm = charmList[i];
			charm.setCharmName();

			numInClass[charm.getCharmClass()] += 1;
		}

		// if no percentage, use average value
		for (int i = 0; i < Charm.charmIDTot; i++)
		{
			Charm charm = charmList[i];
			if(charm.getPercentage() == 0)
			{
				charmClass = charm.getCharmClass();
				charm.setPercentage(100/numInClass[charmClass]);
			}
		}

	}

	// read jewel from jewel file
	public void readJewel(Mhag mhag) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(fileJewel));

		// check total # of skills
		int nMax = 0;
		String line = "";
		while(in.hasNext())
		{
			line = in.nextLine();
			if(line.startsWith("#"))continue;
			nMax++;
		}
		String logLine = String.format
			("Total Number of Jewels : %d",nMax);
		MhagUtil.logLine(mhag,logLine);

		in.close();

		jewelList = new Jewel[nMax];
		for (int i = 0; i < nMax; i++)
		{
			jewelList[i] = new Jewel();

		}
		Scanner in2 = new Scanner(new File(fileJewel));

		// read Jewel entry
		int ioErr = 0;
		int jewelIndex = 0;
		while(in2.hasNext())
		{
			line = in2.nextLine();
			if(line.startsWith("#"))continue;
			// System.out.printf("%d\n", jewelIndex);
			// System.out.println(line);
			ioErr = Jewel.setJewelFromLine
				(line, jewelList[jewelIndex]);
			if(ioErr != 0)
			{
				MhagUtil.logLine(mhag,
					"Error Found in Jewel File");
				return;
			}
			jewelIndex++;
		}

	}

	// read armor from armor file
	public void readArmor(Mhag mhag) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(fileArmor));

		// check total # of skills
		int nMax[] = new int[5];
		Arrays.fill(nMax, 0);
		int nBodyPart = 0;

		String line = "";
		while(in.hasNext())
		{
			line = in.nextLine();
			if(line.startsWith("#"))continue;
			String word = Armor.getBodyPartFromLine(line);
			nBodyPart = Armor.convertBodyPart(word);
			nMax[nBodyPart] += 1;
		}
		String logLine = String.format
			("Total Number of Armors : "
				+Arrays.toString(nMax));
		MhagUtil.logLine(mhag, logLine);
		in.close();

		// find max # of pieces of one part
		int nMax5 = 0;
		for(int i = 0; i < 5 ; i++)
		{
			if(nMax[i] > nMax5)
			{
				nMax5 = nMax[i];
			}
		}
		// System.out.println(nMax5);
		armorList = new Armor[5][nMax5];
		for (int i = 0; i < 5; i++)
		{
			for (int j =0; j < nMax5; j++)
			{
				armorList[i][j] = new Armor();
			}
		}

		Scanner in2 = new Scanner(new File(fileArmor));
		// read Armor entry
		int ioErr = 0;
		int[] armorIndex = new int[5];
		Arrays.fill(armorIndex, 0);

		while(in2.hasNext())
		{
			line = in2.nextLine();
			if(line.startsWith("#"))continue;
			// System.out.println(Arrays.toString(armorIndex));
			// System.out.println(line);

			String word = Armor.getBodyPartFromLine(line);
			nBodyPart = Armor.convertBodyPart(word);

			ioErr = Armor.setArmorFromLine(line, nBodyPart,
				armorList[nBodyPart][armorIndex[nBodyPart]]);
			if(ioErr != 0)
			{
				MhagUtil.logLine(mhag,
					"Error Found in Armor File");
				return;
			}
			armorIndex[nBodyPart]++;
		}

	}

	// read charm from charm file
	public void readCharm(Mhag mhag) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(fileCharm));

		// check total # of skills
		int nMax = 0;
		String line = "";
		while(in.hasNext())
		{
			line = in.nextLine();
			if(line.startsWith("#"))continue;
			nMax++;
		}

		String logLine = String.format
			("Total Number of Jewels : %d",nMax);
		MhagUtil.logLine(mhag,logLine);
		in.close();

		charmList = new Charm[nMax];
		for (int i = 0; i < nMax; i++)
		{
			charmList[i] = new Charm();

		}
		Scanner in2 = new Scanner(new File(fileCharm));

		// read Charm entry
		int ioErr = 0;
		int charmIndex = 0;
		while(in2.hasNext())
		{
			line = in2.nextLine();
			if(line.startsWith("#"))continue;
			// System.out.printf("%d\n", charmIndex);
			// System.out.println(line);
			ioErr = Charm.setCharmFromLine
				(line, charmList[charmIndex]);
			if(ioErr != 0)
			{
				MhagUtil.logLine(mhag,
					"Error Found in Jewel File");
				return;
			}
			charmIndex++;
		}

	}

	// get Skill ID, from a skill name
	public int getSkillIDFromName(String skillName)
	{
		int skillID = 0;
		if(skillName.equals("Torso Up"))return -1; // torso up -1
		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			if(skillList[i].getSkillName().equals(skillName))
			{
				skillID = skillList[i].getSkillID();
				break;
			}

		}
		return skillID;
	}

	// write skill reference file
	public void genRefSkill() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefSkill);
		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			out.printf("%3d: %s\n",skillList[i].getSkillID(),
				skillList[i].getSkillName());
		}
		out.close();
	}

	// write effect reference file
	public void genRefEffect() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefEffect);
		for (int i = 0; i < Effect.effectIDTot; i++)
		{
			out.printf("%3d: %s\n",effectList[i].getEffectID(),
				effectList[i].getEffectName());
		}
		out.close();
	}

	// write jewel reference file
	public void genRefJewel() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefJewel);
		for (int i = 0; i < Jewel.jewelIDTot; i++)
		{
			Jewel jewel = jewelList[i];
			if(jewel.getNumSkill() == 2)
			{
				out.printf("%3d: %-20s %s %+d, %s %+d\n",
					jewelList[i].getJewelID(),
					jewelList[i].getJewelName(),
					jewelList[i].getSkillName()[0],
					jewelList[i].getSkillPoint()[0],
					jewelList[i].getSkillName()[1],
					jewelList[i].getSkillPoint()[1]);
			}
			else
			{
				out.printf("%3d: %-20s %s %+d\n",
					jewelList[i].getJewelID(),
					jewelList[i].getJewelName(),
					jewelList[i].getSkillName()[0],
					jewelList[i].getSkillPoint()[0]);
			}
		}
		out.close();
	}

	// write Armor reference file
	public void genRefArmor() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefArmor);
		for (int i = 0; i < Armor.getArmorMax(); i++)
		{
			String[] armorNames = new String[5];
			Arrays.fill(armorNames, "");

			for (int j = 0; j < 5; j++)
			{
				armorNames[j] = armorList[j][i].getArmorName();
			}
  			out.printf("%3d: %s     %s     %s     %s     %s\n", i,
  				armorNames[0],armorNames[1],armorNames[2],
  				armorNames[3],armorNames[4]);
		}
		out.close();
	}

	// write charm reference file
	public void genRefCharm() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefCharm);
		for (int i = 0; i < Charm.charmIDTot; i++)
		{
			out.printf("%3d: %s\n",charmList[i].getCharmID(),
				charmList[i].getCharmName());
			//	charmList[i].getPercentage());
		}
		out.close();
	}

	// write skill class file
	public void genRefSkillClass() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefSkillClass);

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < numSkillInClass[i]; j++)
			{
				out.printf("%3d %3d: %s\n",i,j,
					skillList[indexSkillInClass[i][j]].
					getSkillName());
			}
		}
		out.close();
	}

	// write input file for complete set
	public void genRefCompleteSet() throws FileNotFoundException
	{
		int nMax = Armor.getArmorMax();
		String[] codeBook = new String[nMax];
		int[] defenseList = new int[nMax];
		boolean[] blade = new boolean[nMax];

		Arrays.fill(codeBook, "");
		Arrays.fill(defenseList, 0);
		Arrays.fill(blade, false) ;

		boolean[][] checked = new boolean[5][nMax];
		for(int i =0; i < 5; i++)
			Arrays.fill(checked[i], false);

		int num = 0; // total number of full set
		int[] armorID = new int[5];  //armor IDs

		for(int i = 0; i < 5; i++) // from head piece first
		{
			for(int j = 0; j< Armor.armorIDTot[i]; j++)
			{
				if(checked[i][j])continue;
				Arrays.fill(armorID, -1);
				armorID[i] = j;
				Armor armor = armorList[i][j];
				String armorName = armor.getArmorName();
				int pos = armorName.indexOf(" ");
				if(pos == -1)
				{
					System.out.println("Error!");
					continue;
				}

				String armor1stWord = armorName.
					substring(0,pos).trim();
				boolean plusSet = true;
				if(armorName.indexOf("+") == -1)
					plusSet = false;
				int defense = armor.getDefenseHighRank();

				// check chest/arm/waist/leg

				for(int k = 1; k < 5; k++)
				{
					for(int jk =0; jk < armor.armorIDTot[k]; jk++)
					{
						if(checked[k][jk])continue;
						Armor armor2 = armorList[k][jk];
						String armor2Name = armor2.getArmorName();
						if(!armor2Name.contains(armor1stWord))continue;
						if((!plusSet) && armor2Name.contains("+"))continue;
						if((plusSet) && (!armor2Name.contains("+")))continue;
						if(armor2.getDefenseHighRank() != defense)continue;
						armorID[k] = jk;
						break;
					}
				}

				int numFound = 0;
				for(int k =0; k < 5; k++)
				{
					if(armorID[k] != -1)
						numFound++;
				}
				if(numFound <= 1)continue;  //armor set has at least 2 pieces

				// add set

				StringBuffer code = new StringBuffer("");
				String setName = armor.getSetName();

				code.append(setName + " :");

				if(armor.getDefenseLowRank() == 0) // high rank
				{
					defenseList[num] = defense;
					code.append(" H");

				}
				else
				{
					defenseList[num] = armor.getDefenseLowRank();
					code.append(" L");
				}

				for(int k = 1; k < 5; k++) // other than head piece
				{
					if(armorID[k] != -1)
					{
						Armor armor2 = armorList[k][armorID[k]];
						if(armor2.getBladeOrGunner().equals("G"))
						{
							code.append(" G");
							blade[num] = false;
						}
						else
						{
							code.append(" B");
							blade[num] = true;
						}
						break;
					}
				}

				for(int k = 0; k < 5; k++)
				{
					if(armorID[k] == -1)continue;
					checked[k][armorID[k]] = true;
					code.append(" "+Armor.partFull.charAt(k));
					code.append(" "+String.valueOf(armorID[k]));
					code.append(" 0");
				}

				codeBook[num] = new String(code.toString());

				//System.out.println(code.toString());
				num++;

			}
		}

		//sort based on defense
		int[] index = MhagUtil.sortIndex(num, defenseList);

		PrintStream outBlade = new PrintStream(fileCompleteBlade);
		PrintStream outGunner = new PrintStream(fileCompleteGunner);

		for(int i = num - 1; i > -1 ; i--)
		{
			int j = index[i];
			if(blade[j])
				outBlade.println(codeBook[j]);
			else
				outGunner.println(codeBook[j]);
		}
		outBlade.close();
		outGunner.close();

	}

	// calculator (one input version)
	public void calculator(Mhag mhag) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "");
		MhagUtil.logLine(mhag, "Method: MHAG Set Calcualtor");

		Set aSet = new Set();  //create a new set
		aSet.setSetFromFile(mhag, mhag.getFileIn()); //read set

		MhagUtil.logLine(mhag, "Set Code:");
		String code = aSet.getSetCode();   //get set code
		MhagUtil.logLine(mhag, code);

		boolean pass = aSet.checkSet(mhag, this);  //check set
		if(!pass)
		{
			MhagUtil.logLine(mhag, "Error! Please Check!");
			System.exit(0);
		}

		aSet.calcSet(mhag, this);   //calculate set

		// prepare output file
		PrintStream outSave = new PrintStream(mhag.getFileOut());
		Output.init(mhag.getOutFormat(), outSave);

		aSet.save(mhag, this, outSave);  // save results

		Output.close(mhag.getOutFormat(), outSave);
		outSave.close();
	}

	// batach galcualator (multiple code input)
	public void batchCalc(Mhag mhag) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "");
		MhagUtil.logLine(mhag, "Method: MHAG Batch Calcualtor");

		Set aSet = new Set();  //create set data

		Scanner in = new Scanner(new File(mhag.getFileIn()));
		PrintStream outSave = new PrintStream(mhag.getFileOut());
		Output.init(mhag.getOutFormat(), outSave);

		int num = 0;
		while (in.hasNext())
		{
			num++;
			MhagUtil.logLine(mhag, "");
			MhagUtil.logLine(mhag, String.format("Set: %d", num));

			String line = in.nextLine();

			aSet.setSetFromCode(mhag, line); //read set

			boolean pass = aSet.checkSet(mhag, this);  //check set
			if(!pass)
			{
				MhagUtil.logLine(mhag, "Error! Please Check!");
				System.exit(0);
			}

			aSet.calcSet(mhag, this);   //calculate set

			Output.batchHead(mhag.getOutFormat(), outSave, num);
			aSet.save(mhag, this, outSave);  // save results

		}
		Output.close(mhag.getOutFormat(), outSave);
		outSave.close();
	}

	// get armor class
	public Armor getArmor(int bodyPart, int armorID)
	{
		return armorList[bodyPart][armorID];
	}

	// get jewel class
	public Jewel getJewel(int jewelID)
	{
		return jewelList[jewelID];
	}

	// get effect class
	public Effect getEffect(int effectID)
	{
		return effectList[effectID];
	}

	// get Skill class
	public Skill getSkill(int skillID)
	{
		return skillList[skillID];
	}

	// get charm class
	public Charm getCharm(int charmID)
	{
		return charmList[charmID];
	}

	// get number of skill in a class
	public int getNumSkillInClass(int nClass)
	{
		return numSkillInClass[nClass];
	}

	// get skills in a class
	public int[] getSkillInClass(int nClass)
	{
		return indexSkillInClass[nClass];
	}

	// get armor list (index copy, name can be got from armorList)
	// sorted by armorName;
	public int[] getArmorList(boolean lowRank, boolean blade, 
		boolean female, int bodyPart)
	{
		int nMax = Armor.armorIDTot[bodyPart];
		int[] index = new int[nMax];
		int num = 0;
		for (int i = 0; i < nMax; i++)
		{
			Armor armor = armorList[bodyPart][i];
			if(lowRank && (!armor.getLowRank()))continue;
			if(blade && (armor.getBladeOrGunner().equals("G")))continue;
			if((!blade) && (armor.getBladeOrGunner().equals("B")))continue;

			index[num] = i;
			num++;
		}

		String[] nameStr =  getArmorListName(bodyPart, female, num, index);

		int[] indNew = MhagUtil.sortIndex(num, nameStr);

		int[] indFinal = new int[num + 1]; // add null
		indFinal[0] = -1;
		for(int i = 0; i < num; i++)
			indFinal[i + 1] = index[indNew[num - 1 - i]];

		return indFinal;
	}

	public String[] getArmorListName(int bodyPart, boolean female,
		int num, int[] index)
	{
		String[] nameStr = new String[num];
		for(int i = 0; i < num; i++)
		{
			String armorName  = armorList[bodyPart][index[i]].getArmorName();

			int pos = armorName.indexOf("/");
			if(pos == - 1)
				nameStr[i] = new String(armorName);
			else
			{
				if(female)
					nameStr[i] = new String(armorName.substring(pos+1).trim());
				else
					nameStr[i] = new String(armorName.substring(0,pos-1).trim());
			}
		}
		return nameStr;
	}

//	generate menu list
	public String[] getArmorListMenu(int bodyPart, boolean female,
		int num, int[] index)
	{
		String[] nameStr = new String[num];
		nameStr[0] = new String("---");
		for(int i = 1; i < num ; i++)  //first 1 is null;
		{
			String armorName  = armorList[bodyPart][index[i]].getArmorName();

			int pos = armorName.indexOf("/");
			if(pos == - 1)
				nameStr[i] = new String(armorName);
			else
			{
				if(female)
					nameStr[i] = new String(armorName.substring(pos+1).trim());
				else
					nameStr[i] = new String(armorName.substring(0,pos-1).trim());
			}
		}
		return nameStr;
	}

	public int[] getJewelList(boolean lowRank, int nSlot)
	{
		int nMax = Jewel.jewelIDTot;
		int[] index = new int[nMax];
		String[] nameStr =  new String[nMax];
		int num = 0;
		for (int i = 0; i < nMax; i++)
		{
			Jewel jewel = jewelList[i];
			if(lowRank && (!jewel.getLowRank()))continue;
			if(jewel.getNumSlot() > nSlot)continue;

			index[num] = i;
			nameStr[num] = jewel.getJewelNameShort();
			num++;
		}


		int[] indNew = MhagUtil.sortIndex(num, nameStr);

		int[] indFinal = new int[num + 1]; // add null
		indFinal[0] = -1;
		for(int i = 0; i < num; i++)
			indFinal[i + 1] = index[indNew[num - 1 - i]];

		return indFinal;
	}

	public int[] getSkillList(String skillClass)
	{
		int nMax = Skill.skillIDTot;
		int[] index = new int[nMax];
		String[] nameStr =  new String[nMax];
		int num = 0;
		for (int i = 0; i < nMax; i++)
		{
			Skill skill = skillList[i];
			if(!skill.getSkillClass().equals(skillClass))continue;

			index[num] = i;
			nameStr[num] = skill.getSkillName();
			num++;
		}


		int[] indNew = MhagUtil.sortIndex(num, nameStr);

		int[] indFinal = new int[num + 1]; // add null
		indFinal[0] = -1;
		for(int i = 0; i < num; i++)
			indFinal[i + 1] = index[indNew[num - 1 - i]];

		return indFinal;
	}

	public int[] getCharmList(boolean lowRank)
	{
		int nMax = Charm.charmIDTot;
		int[] index = new int[nMax];
		String[] nameStr =  new String[nMax];
		int num = 0;
		for (int i = 0; i < nMax; i++)
		{
			Charm charm = charmList[i];
			if(lowRank && (!charm.getLowRank()))continue;

			index[num] = i;
			nameStr[num] = charm.getCharmName();
			num++;
		}


		int[] indNew = MhagUtil.sortIndex(num, nameStr);

		int[] indFinal = new int[num + 1]; // add null
		indFinal[0] = -1;
		for(int i = 0; i < num; i++)
			indFinal[i + 1] = index[indNew[num - 1 - i]];

		return indFinal;
	}

	// Constants for file names
	private final String dirData = "data/";
	private final String fileArmor = dirData+"armor.dat";
	private final String fileJewel = dirData+"jewel.dat";
	private final String fileSkill = dirData+"skill.dat";
	private final String fileCharm = dirData+"charm.dat";
	private final String dirRef = "reference/";
	private final String fileRefArmor = dirRef+"ref_armor.dat";
	private final String fileRefJewel = dirRef+"ref_jewel.dat";
	private final String fileRefSkill = dirRef+"ref_skill.dat";
	private final String fileRefCharm = dirRef+"ref_charm.dat";
	private final String fileRefEffect = dirRef+"ref_effect.dat";
	private final String fileRefSkillClass = dirRef+"ref_skill_class.dat";
	private final String fileCompleteBlade = dirRef+"blade_sets.input";
	private final String fileCompleteGunner = dirRef+"gunner_sets.input";

	// data
	private Armor[][] armorList;
	private Skill[] skillList;
	private Jewel[] jewelList;
	private Charm[] charmList;
	private Effect[] effectList;

	// indeces
	private int[][] indexSkillInClass;  // skill id list
	private int[] numSkillInClass;  // number skill in class

	// Some Constants
	static String emptyName = "-----";

}
