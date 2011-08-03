/**
 * @program MHAG
 * @ Set Class , a set & stats
 * @version 1.1
 * support new talisman system
 * @author Tifa@mh3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Set {

	public Set()
	{
		armorID = new int[5];
		jewelID = new int[7][3];
		charmSkillID = new int[2];
		charmSkillPoint = new int[2];
		numJewel = new int[7];
		inUse = new boolean[7];

		Arrays.fill(armorID, 0);
		Arrays.fill(charmSkillID, 0);
		Arrays.fill(charmSkillPoint, 0);
		Arrays.fill(inUse, false);
		Arrays.fill(numJewel, 0);
		for(int i = 0; i < 7; i++)
		{
			Arrays.fill(jewelID[i], 0);
		}
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 3; j++)
			{

				jewelID[i][j] = 0;
			}
		}
	}

	// get/set inputs

	// get set name
	public String getSetName() {return setName;}

	// set set name
	public void setSetName(String aSetName) {setName = aSetName;}

	// get low/high rank
	public boolean getLowRank() {return lowRank;}

	// set low/high rank
	public void setLowRank(boolean aLowRank) {lowRank = aLowRank;}

	// get blader/gunner info
	public boolean getBlade() {return blade;}

	// set blader/gunner info
	public void setBlade(boolean aBlade) {blade = aBlade;}

	// get armor id
	public int[] getArmorID() {return armorID;}

	// set armor id
	public void setArmorID(int[] aArmorID) {armorID = aArmorID;}

	// set armor id for one bodyPart
	public void setArmorID(int bodyPart, int aArmorID)
	{
		armorID[bodyPart] = aArmorID;
	}

	// get jewel id
	public int[][] getJewelID() {return jewelID;}

	// set jewel id
	public void setJewelID(int[][] aJewelID) {jewelID = aJewelID;}

	public void setJewelID(int bodyPart, int[] aJewelID)
	{
		for(int i = 0; i < 3; i++)
			jewelID[bodyPart][i] = aJewelID[i];
	}

	public void setJewelID(int bodyPart, int slotInd, int aJewelID)
	{
		jewelID[bodyPart][slotInd] = aJewelID;
	}

	// get charm # of slot
	public int getNumCharmSlot() {return numCharmSlot;}

	// set charm # of slot
	public void setNumCharmSlot(int aNumCharmSlot)
	{
		numCharmSlot = aNumCharmSlot;
	}

	// get charm # of Skill
	public int getNumCharmSkill() {return numCharmSkill;}

	// set charm # of skill
	public void setNumCharmSkill(int aNumCharmSkill)
	{
		numCharmSkill = aNumCharmSkill;
	}

	// get charm Skill id
	public int[] getCharmSkillID() {return charmSkillID;}

	// set charm skill id
	public void setCharmSkillID(int[] aCharmSkillID)
	{
		charmSkillID = aCharmSkillID;
	}

	public void setCharmSkillID(int skillNo, int aCharmSkillID)
	{
		charmSkillID[skillNo] = aCharmSkillID;
	}

	// get charm Skill id
	public int[] getCharmSkillPoint() {return charmSkillPoint;}

	// set charm skill id
	public void setCharmSkillPoint(int[] aCharmSkillPoint)
	{
		charmSkillPoint = aCharmSkillPoint;
	}

	public void setCharmSkillPoint(int skillNo, int aCharmSkillPoint)
	{
		charmSkillPoint[skillNo] = aCharmSkillPoint;
	}

	// get charm name (with skill)
	public String getCharmNameWithSkill(MhagData mhagData)
	{
		if(!inUse[6])
			return MhagData.emptyName;
		else
		{
			StringBuffer title = new StringBuffer("");
			for (int i = 0; i < numCharmSkill; i++)
			{
				Skill skill = mhagData.getSkill(charmSkillID[i]);
				title.append(skill.getSkillName());

				String strPoint = String.format("%+3d",
					charmSkillPoint[i]);
				title.append(strPoint);
				if(i == numCharmSkill - 1)
					title.append(" ");
				else
					title.append(", ");
			}
			return title.toString();
		}
	}

	// get in use set pieces
	public boolean getInUse(int part) {return inUse[part];}

	// set in use set pieces
	public void setInUse(int part, boolean useOrNot)
	{
		inUse[part] = useOrNot;
	}

	// get number of slots
	public int getNumJewel(int part) {return numJewel[part];}

	// get number of slots
	public void setNumJewel(int part, int value)
	{
		numJewel[part] = value;
	}

	// get outputs

	// get set defense
	public int getDefense() {return defense;}

	// get set resist
	public int[] getResist() {return resist;}

	// get num of Skills of the set
	public int getNumSkill() {return numSkill;}

	// get Skill IDs of the set
	public int[] getSkillID() {return skillID;}

	// get Skill Points of the set
	public int[] getSkillPoint() {return skillPoint;}

	// get num of effects (activated skills) of the set
	public int getNumEffect() {return numEffect;}

	// get effects ID(activated skills) of the set
	public int[] getEffectID() {return effectID;}

	// get effect skill index (map effect list to skillID of the set)
	public int[] getEffectSkillIndex() {return effectSkillIndex;}

	// get number of troso up of the set
	public int getNumTorso() {return numTorso;}

	// get set rate
	public int getRate() {return rate;}

	// get gender
	public boolean getFemale() {return female;}

	// set gender
	public void setFemale(boolean ifFemale) {female = ifFemale;}

	// get jewel name
	public String[] getJewelName(MhagData mhagData, int bodyPart)
	{
		String[] jewelName = new String[3];
		Arrays.fill(jewelName, "");

		for(int i = 0; i < numJewel[bodyPart]; i++)
		{
			Jewel jewel = mhagData.getJewel(jewelID[bodyPart][i]);
			jewelName[i] = jewel.getJewelName();
		}
		return jewelName;
	}

	// get jewel name short
	public String[] getJewelNameShort(MhagData mhagData, int bodyPart)
	{
		String[] jewelName = new String[3];
		Arrays.fill(jewelName, "");

		for(int i = 0; i < numJewel[bodyPart]; i++)
		{
			Jewel jewel = mhagData.getJewel(jewelID[bodyPart][i]);
			jewelName[i] = jewel.getJewelNameShort();
		}
		return jewelName;
	}

	// set a set from input file (simple input version)
	public void setSetFromFile(Mhag mhag, String file) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "Reading Set From Input File ...");
		init();

		String errorLine = "    Error in Input File, Please Check!";
		Scanner in = new Scanner(new File(file));
		int[] values = new int[3];
		Arrays.fill(values, 0);

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

			if(opt.equals("rank"))  // low/high rank
			{
				if((args.startsWith("l")) ||
					(args.startsWith("L")))
					lowRank = true;
				else if((args.startsWith("h")) ||
					(args.startsWith("H")))
					lowRank = false;
				else
					MhagUtil.logLine(mhag, errorLine);
			}
			else if(opt.equals("blade or gunner"))  // B/G
			{
				if((args.startsWith("b")) ||
					(args.startsWith("B")))
					blade = true;
				else if((args.startsWith("g")) ||
					(args.startsWith("G")))
					blade = false;
				else
					MhagUtil.logLine(mhag, errorLine);
			}
			else if(opt.equals("set name"))  // set name
			{
				if(args.length() == 0)
					setName = unNamedSet;
				else
					setName = args;
			}
			else if(opt.equals("head part"))  //  head id
			{
	  			if(args.length() != 0)
				{
					armorID[0] = Integer.valueOf(args);
					inUse[0] = true;
				}
			}
			else if(opt.equals("chest part"))  // chest id
			{
	  			if(args.length() != 0)
				{
					armorID[1] = Integer.valueOf(args);
					inUse[1] = true;
				}
			}
			else if(opt.equals("arm part"))  // arm id
			{
	  			if(args.length() != 0)
				{
					armorID[2] = Integer.valueOf(args);
					inUse[2] = true;
				}
			}
			else if(opt.equals("waist part"))  // waist id
			{
	  			if(args.length() != 0)
				{
					armorID[3] = Integer.valueOf(args);
					inUse[3] = true;
				}
			}
			else if(opt.equals("leg part"))  // leg id
			{
	  			if(args.length() != 0)
				{
					armorID[4] = Integer.valueOf(args);
					inUse[4] = true;
				}
			}
			else if(opt.equals("head jewel"))  // head jewel
			{
				numJewel[0] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[0]; i++)
					jewelID[0][i] = values[i];
			}
			else if(opt.equals("chest jewel"))  // chest jewel
			{
				numJewel[1] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[1]; i++)
					jewelID[1][i] = values[i];
			}
			else if(opt.equals("arm jewel"))  // arm jewel
			{
				numJewel[2] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[2]; i++)
					jewelID[2][i] = values[i];
			}
			else if(opt.equals("waist jewel"))  // waist jewel
			{
				numJewel[3] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[3]; i++)
					jewelID[3][i] = values[i];
			}
			else if(opt.equals("leg jewel"))  // leg jewel
			{
				numJewel[4] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[4]; i++)
					jewelID[4][i] = values[i];
			}
			else if(opt.equals("weapon jewel"))  // weapon jewel
			{
				numJewel[5] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[5]; i++)
					jewelID[5][i] = values[i];
				if(numJewel[5] != 0) inUse[5] = true;
			}
			else if(opt.equals("charm jewel"))  // charm jewel
			{
				numJewel[6] = MhagUtil.extractInt(args, 3, values);
				for (int i = 0; i < numJewel[6]; i++)
					jewelID[6][i] = values[i];
			}
			else if(opt.equals("charm slot"))  //charm # of slot
			{
				if(args.length() != 0)
				{
					numCharmSlot =  Integer.valueOf(args);
					if(numCharmSlot >  0)
						inUse[6] = true;
				}
			}
			else if(opt.equals("charm skill"))  //charm skill
			{
				if(args.length() != 0)
				{
					inUse[6] = true;
					numCharmSkill =
						MhagUtil.extractInt(args, 2, values);
					for (int i = 0; i < numCharmSkill; i++)
						charmSkillID[i] = values[i];
				}
			}
			else if(opt.equals("charm skill point"))  //charm slill point
			{
				if(args.length() != 0)
				{
					inUse[6] = true;
					numCharmSkill =
						MhagUtil.extractInt(args, 2, values);
					for (int i = 0; i < numCharmSkill; i++)
						charmSkillPoint[i] = values[i];
				}
			}
		}
	}

	// get coding of a set (batch version code)
	public String getSetCode()
	{
		StringBuffer line = new StringBuffer(setName);

		line.append(" : ");

		// low/high rank
		if(lowRank)
			line.append("L ");
		else
			line.append("H ");

		// blade or gunner
		if(blade)
			line.append("B ");
		else
			line.append("G ");

		// armor part
		for (int i = 0; i < 5; i++)
		{
			if(inUse[i])
			{
				String partCode = Armor.partFull.substring(i,i+1);
				line.append(partCode);
				line.append(" ");
				line.append(armorID[i]);
				line.append(" ");
				line.append(numJewel[i]);
				line.append(" ");
				for (int j = 0; j < numJewel[i]; j++)
				{
					line.append(jewelID[i][j]);
					line.append(" ");
				}
			}
		}

		// weapon part
		if(numJewel[5] != 0)  //weapon in use
		{
			line.append("X ");
			line.append(numJewel[5]);
			line.append(" ");
			for (int j = 0; j < numJewel[5]; j++)
			{
				line.append(jewelID[5][j]);
				line.append(" ");
			}
		}

		// charm part
		if(inUse[6])  //charm in use
		{
			line.append("Y ");
			line.append(numCharmSlot);
			line.append(" ");
			line.append(numCharmSkill); // charm skill
			line.append(" ");
			for (int j = 0; j < numCharmSkill; j++)
			{
				line.append(charmSkillID[j]);
				line.append(" ");
				line.append(charmSkillPoint[j]);
				line.append(" ");
			}
			line.append(numJewel[6]);  // charm jewel
			line.append(" ");
			for (int j = 0; j < numJewel[6]; j++)
			{
				line.append(jewelID[6][j]);
				line.append(" ");
			}
		}

		return line.toString();
	}

	// initialize set
	public void init()
	{
		setName = unNamedSet;
		lowRank = false;
		blade = true;
		Arrays.fill(armorID, 0);
		for(int i = 0; i < 7; i++)
			Arrays.fill(jewelID[i], 0);
		numCharmSlot = 0;
		numCharmSkill = 0;
		Arrays.fill(charmSkillID, 0);
		Arrays.fill(charmSkillPoint, 0);
		Arrays.fill(inUse, false);
		Arrays.fill(numJewel, 0);

		// outputs
		resist = new int[5];
		skillID = new int[100];
		skillPoint = new int[100];
		effectID = new int[8];
		effectSkillIndex = new int[8];
		Arrays.fill(resist, 0);
		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
		Arrays.fill(effectID, 0);
		Arrays.fill(effectSkillIndex, 0);

		// hidden
		female = false;

	}
	// set a set from code (batch version)
	public boolean setSetFromCode(Mhag mhag, String line)
	{
		if(line == null)return false;
		init();
		String errorLine = "    Error in Batch Inputs, Please Check!";

		// first word before : set name
		int splitPos = MhagUtil.extractWordPos(line, 0);
		if( splitPos == -1) 
		{
			MhagUtil.logLine(mhag, errorLine);
			return false;
		}
		String word = MhagUtil.extractWord(line, 0, splitPos);
		if(word.equals(""))
			setName = unNamedSet;
		else
			setName = word;

		// the rest part : set parameters
		word = MhagUtil.extractWord(line, splitPos +1, -1);

		String[] wordArray = word.split(" ");
		int numWords = wordArray.length;

		if(numWords < 2)
		{
			MhagUtil.logLine(mhag, errorLine);
			return false;
		}

		if(wordArray[0].equals("H"))
			lowRank = false;
		else if(wordArray[0].equals("L"))
			lowRank = true;

		if(wordArray[1].equals("G"))
			blade = false;
		else if(wordArray[1].equals("B"))
			blade = true;

		int wordIndex = 2;  //start from the 3rd word
		String wordNow = "";
		while(wordIndex < numWords)
		{
			String opt = wordArray[wordIndex];
			int partID = Armor.partFull.indexOf(opt);

			if(partID != -1) //armor parts
			{
				wordNow = wordArray[++wordIndex];
				armorID[partID] = Integer.valueOf(wordNow);
				inUse[partID] = true;

				wordNow = wordArray[++wordIndex];
				numJewel[partID] = Integer.valueOf(wordNow);

				for (int j = 0; j < numJewel[partID]; j++)
				{
					wordNow = wordArray[++wordIndex];
					jewelID[partID][j] =
						Integer.valueOf(wordNow);
				}
			}
			else if (opt.equals("X")) //weapon
			{
				wordNow = wordArray[++wordIndex];
				numJewel[5] = Integer.valueOf(wordNow);
				inUse[5] = true;

				for (int j = 0; j < numJewel[5]; j++)
				{
					wordNow = wordArray[++wordIndex];
					jewelID[5][j] = Integer.valueOf(wordNow);
				}
			}
			else if (opt.equals("Y")) //charm
			{
				wordNow = wordArray[++wordIndex];
				numCharmSlot = Integer.valueOf(wordNow);
				inUse[6] = true;

				wordNow = wordArray[++wordIndex];
				numCharmSkill = Integer.valueOf(wordNow);
				for (int j = 0; j < numCharmSkill; j++)
				{
					wordNow = wordArray[++wordIndex];
					charmSkillID[j] = Integer.valueOf(wordNow);
					wordNow = wordArray[++wordIndex];
					charmSkillPoint[j] = Integer.valueOf(wordNow);
				}

				wordNow = wordArray[++wordIndex];
				numJewel[6] = Integer.valueOf(wordNow);
				for (int j = 0; j < numJewel[6]; j++)
				{
					wordNow = wordArray[++wordIndex];
					jewelID[6][j] = Integer.valueOf(wordNow);
				}
			}
			else
			{

				MhagUtil.logLine(mhag, errorLine);
				return false;
			}
			wordIndex++;

		}
		return true;

	}

	// check set consistency
	public boolean checkSet(Mhag mhag, MhagData mhagData)
	{
		MhagUtil.logLine(mhag, "Checking Set Consistentcy...");

		//low rank check
		if(lowRank)
		{
			String  errLine = "   Error! not low rank armor piece";

			// armor
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(!armor.getLowRank())
				{
					MhagUtil.logLine(mhag,errLine);
					return false;
				}
			}

			// jewel
			for(int i = 0; i < 7; i++)
			{
				if(!inUse[i])continue;
				if(numJewel[i] == 0)continue;
				for(int j = 0; j < numJewel[i]; j++)
				{
					Jewel jewel = mhagData.
						getJewel(jewelID[i][j]);
					if(!jewel.getLowRank())
					{
						MhagUtil.logLine(mhag,errLine);
						return false;
					}
				}
			}

		}

		// B/G check
		if(blade)
		{
			String errLine  = "   Error! not for Blademaster!";
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(armor.getBladeOrGunner().equals("G"))
				{
					MhagUtil.logLine(mhag,errLine);
					return false;
				}
			}
		}
		else
		{
			String errLine  = "   Error! not for Gunner!";
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(armor.getBladeOrGunner().equals("B"))
				{
					MhagUtil.logLine(mhag,errLine);
					return false;
				}
			}

		}

		// slots check

		int nSlot = 0;
		int nSlotArmor = 0;
		String  errLine = "   Error! Jewels require too many slots";

		//armor slots
		for(int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			nSlotArmor = armor.getNumSlot();

			nSlot = 0;
			for(int j = 0; j < numJewel[i]; j++)
			{
				Jewel jewel = mhagData.
					getJewel(jewelID[i][j]);
				nSlot += jewel.getNumSlot();
			}
			if(nSlot > nSlotArmor)
			{
				MhagUtil.logLine(mhag,errLine);
				return false;
			}
		}

		// weapon slots  < 3
		if( numJewel[5] != 0)
		{
			nSlot = calWeaponSlot(mhagData);
			if(nSlot > 3)
			{
				MhagUtil.logLine(mhag,errLine);
				return false;
			}
		}

		//charm slots
		if(inUse[6])
		{
			// low rank slot number < 3
			if(lowRank && (numCharmSlot == 3))
			{
				MhagUtil.logLine(mhag,errLine);
				return false;
			}

			nSlot = 0;
			for(int j = 0; j < numJewel[6]; j++)
			{
				Jewel jewel = mhagData.
					getJewel(jewelID[6][j]);
				nSlot += jewel.getNumSlot();
			}
			if(nSlot > numCharmSlot)
			{
				MhagUtil.logLine(mhag,errLine);
				return false;
			}
		}

		//charm skills
		if(inUse[6])
		{
			for (int i = 0; i < numCharmSkill; i++)
			{
				Skill skill = mhagData.getSkill(charmSkillID[i]);
				if(charmSkillPoint[i] > skill.getMaxSkillPoint(lowRank, numCharmSlot))
				{
					MhagUtil.logLine(mhag,
						"   Error! Charm Skill Point inconsistent!");
					return false;
				}
				if((i == 1) &&
					(charmSkillID[0] == charmSkillID[1]))
				{
					MhagUtil.logLine(mhag,
						"   Error! Charm Skill should be different!");
					return false;
				}
			}
		}

		MhagUtil.logLine(mhag,"   Pass!");
		return true;
	}

	// calculate set stats
	public void calcSet(Mhag mhag, MhagData mhagData)
	{
		MhagUtil.logLine(mhag, "Calulating Armor Stats...");

		// defense
		defense = 0;
		if(lowRank)
		{
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				defense += armor.getDefenseLowRank();
			}
		}
		else
		{
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				defense += armor.getDefenseHighRank();
			}
		}
		String line = String.format("   Total Defense : %d",defense);
		MhagUtil.logLine(mhag, line);

		// element resistance
		Arrays.fill(resist, 0);
		for(int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			for(int j = 0; j < 5; j++)
				resist[j] += armor.getResist()[j];
		}
		line = "   Total Resist : " + Arrays.toString(resist);
		MhagUtil.logLine(mhag, line);

		// add skills
		addSkills(mhag, mhagData);
		line = String.format("   # of Torso Up : %d",numTorso);
		MhagUtil.logLine(mhag, line);

		MhagUtil.logLine(mhag, "   Skill List : ");
		for (int i =0; i < numSkill; i++)
		{
			Skill skill = mhagData.getSkill(skillID[i]);
			line = String.format("%3d : %-10s  %+3d", i, skill.getSkillName(), skillPoint[i]);
			MhagUtil.logLine(mhag, line);
		}

		// check skill effects
		checkEffects(mhagData);
		MhagUtil.logLine(mhag, "   Effect List : ");
		for (int i =0; i < numEffect; i++)
		{
			Effect effect = mhagData.getEffect(effectID[i]);
			line = String.format("%3d : %-10s", i, effect.getEffectName());
			MhagUtil.logLine(mhag, line);
		}
	}

	// add skill points for a set, calcualte stats
	public void addSkills(Mhag mhag, MhagData mhagData)
	{
		// check number of torso up
		numTorso = 0;
		for (int i = 0; i < 5; i++)
		{
			if( i == 2)continue;
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			if(( armor.getNumSkill() == 1) &&
				( armor.getSkillID()[0] == -1))
				numTorso++;
		}

		// check skills
		int[] listMapping  = new int[Skill.skillIDTot];
		boolean[] ifMapping = new boolean[Skill.skillIDTot];
		Arrays.fill(listMapping, 0);
		Arrays.fill(ifMapping, false);
		numSkill = 0;
		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);

		// add armor pieces
		for (int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			for (int j =0; j < armor.getNumSkill(); j++)
			{

				int id = armor.getSkillID()[j];
				int pos;

				if(id < 0)continue;
				// check skill index
				if(!ifMapping[id])  // not assigned
				{
					pos = numSkill++;
					listMapping[id] = pos;
					ifMapping[id] = true;
					skillID[pos] = id;
				}
				else
				{
					pos = listMapping[id];
				}

				// add ppints

				if((i == 1)&&(numTorso != 0))
				{
					skillPoint[pos] += armor.getSkillPoint
						()[j] * (numTorso + 1);
				}
				else
				{
					skillPoint[pos] += armor.getSkillPoint
						()[j];
				}

			}
		}

		// add jewels
		for (int i = 0; i < 7; i++){
		for (int k = 0; k < numJewel[i]; k++)
		{
			Jewel jewel = mhagData.getJewel(jewelID[i][k]);
			for (int j =0; j < jewel.getNumSkill(); j++)
			{
				int id = jewel.getSkillID()[j];
				int pos;
				// check skill index
				if(!ifMapping[id])  // not assigned
				{
					pos = numSkill++;
					listMapping[id] = pos;
					ifMapping[id] = true;
					skillID[pos] = id;
				}
				else
				{
					pos = listMapping[id];
				}

				// add ppints

				if((i == 1)&&(numTorso != 0))
				{
					skillPoint[pos] += jewel.getSkillPoint
						()[j] * (numTorso + 1);
				}
				else
				{
					skillPoint[pos] += jewel.getSkillPoint
						()[j];
				}

			}
		}
		}

		// add charm points
		if( inUse[6]){

			for (int j =0; j < numCharmSkill; j++)
			{
				int id = charmSkillID[j];
				int pos;
				// check skill index
				if(!ifMapping[id])  // not assigned
				{
					pos = numSkill++;
					listMapping[id] = pos;
					ifMapping[id] = true;
					skillID[pos] = id;
				}
				else
				{
					pos = listMapping[id];
				}

				// add ppints
				skillPoint[pos] += charmSkillPoint[j];

			}
		}

	}

	// check skill effects
	public void checkEffects(MhagData mhagData)
	{
		numEffect = 0;
		Arrays.fill(effectID, 0);
		Arrays.fill(effectSkillIndex, 0);

		for(int i =0; i < numSkill; i++)
		{
			int id = skillID[i];
			int point = skillPoint[i];
			Skill skill = mhagData.getSkill(id);
			int nEffect = skill.getNumEffect();

			if(point >= 10 ) //positive skills
			{
				for(int j = nEffect -1; j > -1; j--)
				{
					int trigger = skill.getEffectTrigger()[j];
					if((trigger > 0) && (trigger <= point))
					{
						effectID[numEffect] =
							skill.getEffectID()[j];
						effectSkillIndex[numEffect] = i;
						numEffect++;
						break;
					}
				}
			}
			else if (point <= -10) //nagative skills
			{
				for(int j = 0; j < nEffect; j++)
				{
					int trigger = skill.getEffectTrigger()[j];
					if((trigger < 0) && (trigger >= point))
					{
						effectID[numEffect] =
							skill.getEffectID()[j];
						effectSkillIndex[numEffect] = i;
						numEffect++;
						break;
					}
				}

			}

		}

	}

	// sort skills based on points & effects, only used for outputs
	public void sortSkill()
	{
		int[] index = new int[numSkill];
		index =	MhagUtil.sortIndex(numSkill, skillPoint);

		// temp skill/effect data
		int[] sPoint = new int[100];
		int[] sID = new int[100];
		int[] eID = new int[8];
		int[] eSkillInd = new int[8];
		boolean[] active = new boolean[numSkill];

		Arrays.fill(sPoint, 0);
		Arrays.fill(sID, 0);
		Arrays.fill(eID, 0);
		Arrays.fill(eSkillInd, 0);
		Arrays.fill(active, false);

		// effect skills
		int num = 0;
		for (int i = 0; i < numSkill; i++)
		{
			for (int j = 0; j < numEffect; j++)
			{
				int ind = effectSkillIndex[j];
				if(index[i] != ind)continue;

				eID[num] = effectID[j];
				eSkillInd[num] = num;
				sID[num] = skillID[ind];
				sPoint[num] = skillPoint[ind];
				active[ind] = true;
				num++;
			}
		}

		effectID = eID;
		effectSkillIndex = eSkillInd;

		// other skills
		for (int i = 0; i < numSkill; i++)
		{
			int ind = index[i];
			if(active[ind])continue;
			sID[num] = skillID[ind];
			sPoint[num] = skillPoint[ind];
			num++;
		}
		skillID = sID;
		skillPoint = sPoint;

	}

	public int calWeaponSlot(MhagData mhagData)
	{
		int nSlot = 0;
		for (int i = 0; i < numJewel[5]; i++)
		{
			Jewel jewel = mhagData.
				getJewel(jewelID[5][i]);
			nSlot += jewel.getNumSlot();
		}
		return nSlot;
	}

	// check stat bonus from activated skills
	public int[] checkSkillBonus(MhagData mhagData)
	{
		int[] bonus = new int[6]; // [0,4] resist, 5 defense
		Arrays.fill(bonus, 0);

		for(int i = 0; i < numEffect; i++)
		{
			Effect effect = mhagData.getEffect(effectID[i]);
			String effectName = effect.getEffectName();

			// check defense skills
			if(effectName.contains("Defense"))
			{
				if(effectName.contains("L"))
					bonus[5] = 20;
				else if(effectName.contains("M"))
					bonus[5] = 15;
				else if(effectName.contains("S"))
					bonus[5] = 10;

				if(effectName.contains("Down"))
					bonus[5] = -bonus[5];
			}

			// check element resist skills
			int id = 0;
			boolean checkElements = true;
			if(effectName.contains("Fire Res"))
				id = 0;
			else if(effectName.contains("Water Res"))
				id = 1;
			else if(effectName.contains("Ice Res"))
				id = 2;
			else if(effectName.contains("Thunder Res"))
				id = 3;
			else if(effectName.contains("Dragon Res"))
				id = 4;
			else
				checkElements = false;

			if(checkElements)
			{
				if(effectName.contains("+20"))
					bonus[id] = 20;
				else if(effectName.contains("+15"))
					bonus[id] = 15;
				else if(effectName.contains("+10"))
					bonus[id] = 10;
				else if(effectName.contains("-10"))
					bonus[id] = -10;
				else if(effectName.contains("-15"))
					bonus[id] = -15;
            else if(effectName.contains("Down")) //p3
               bonus[id] = -10;
            else if(effectName.contains("Up (S)")) //p3
               bonus[id] =  5;
            else if(effectName.contains("Up (L)")) //p3
               bonus[id] = 10;
			}

		}
		return bonus;

	}

	// check stat bonus from activated skills
	public static boolean ifSkillBonus(int[] bonus)
	{
		for(int i = 0; i < 6; i++)
		{
			if(bonus[i] != 0)
				return true;
		}
		return false;
	}

	// extract skill point info for a skill
	public int[] extractPoints(MhagData mhagData, int index)
	{
		int[] points = new int[8];  //0: weapon, [1,5]: armor, 6: jewel 7: total
		Arrays.fill(points, 0);

		//check armor
		for (int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			for (int j = 0; j < armor.getNumSkill(); j++)
			{
				if(armor.getSkillID()[j] == index)
				{
					if(i == 1)
						points[i+1] += (numTorso + 1)*
							armor.getSkillPoint()[j];
					else
						points[i+1] +=
							armor.getSkillPoint()[j];
					break;
				}

			}
		}

		// check jewel
		// weapon jewel
		for(int k = 0; k < numJewel[5]; k++)
		{
			Jewel jewel = mhagData.getJewel(jewelID[5][k]);
			for(int j = 0; j < jewel.getNumSkill(); j++)
			{
				if(jewel.getSkillID()[j] == index)
				{
					points[0] += jewel.getSkillPoint()[j];
					break;
				}
			}
		}

		// armor jewel
		for(int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			for(int k = 0; k < numJewel[i]; k++)
			{
				Jewel jewel = mhagData.getJewel(jewelID[i][k]);
				for(int j = 0; j < jewel.getNumSkill(); j++)
				{
					if(jewel.getSkillID()[j] == index)
					{
						if(i == 1)
							points[i+1] += (numTorso + 1)*
								jewel.getSkillPoint()[j];
						else
							points[i+1] +=
								jewel.getSkillPoint()[j];
						break;
					}
				}
			}
		}


		// charm jewel
		for(int k = 0; k < numJewel[6]; k++)
		{
			Jewel jewel = mhagData.getJewel(jewelID[6][k]);
			for(int j = 0; j < jewel.getNumSkill(); j++)
			{
				if(jewel.getSkillID()[j] == index)
				{
					points[6] += jewel.getSkillPoint()[j];
					break;
				}
			}
		}

		// charm skills
		if(inUse[6])
		{
			for(int i = 0; i < numCharmSkill; i++)
			{
				if(charmSkillID[i] == index)
				{
					points[6] += charmSkillPoint[i];
					//break;  (it's actually not correct,
					//have two same skills in a jewel
				}
			}
		}

		// total
		for(int i = 0; i < numSkill; i++)
		{
			if(skillID[i] == index)
			{
				points[7] = skillPoint[i];
				break;
			}
		}

		return points;
	}

	// get list displaying Torso Up
	public String[] getListTorsoUp(MhagData mhagData)
	{
		String[] torsoList = new String[8];
		Arrays.fill(torsoList,"---");
		for(int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor = mhagData.getArmor(i, armorID[i]);
			if(armor.getNumSkill() == 1)
			{
				if(armor.getSkillID()[0] == -1)
					torsoList[i+1] = "  E";
			}
		}
		return torsoList;
	}

	public void save(Mhag mhag, MhagData mhagData, PrintStream outSave)
	{
		sortSkill();  //sort Skill for outputs

		if(mhag.getOutFormat() == 0)
			MhagUtil.logLine(mhag, "Save Armor Set in TEXT Format");
		else
			MhagUtil.logLine(mhag, "Save Armor Set in HTML Format");

		// part 1 :  Setup Inforamtion
		// head line
		Output.head(mhag.getOutFormat(), outSave, setName, lowRank, blade);

		// weapon line
		int nSlotWeapon = calWeaponSlot(mhagData);
		String slots = Output.slotWord(mhag.getOutFormat(), nSlotWeapon);

		String[] jewels = new String[3];
		jewels = getJewelNameShort(mhagData, 5);

		Output.weapon(mhag.getOutFormat(), outSave, slots, jewels);

		// armor lines
		for(int i = 0; i < 5; i++)
		{
			String title = new String();
			if(inUse[i])
			{
				Armor armor = mhagData.getArmor(i, armorID[i]);
				title = armor.getArmorName();  //armor name
				int nSlot = armor.getNumSlot();
				slots = Output.slotWord(mhag.getOutFormat(), nSlot);

				jewels = getJewelNameShort(mhagData, i);
			}
			else
			{
				title = MhagData.emptyName;
				slots = "";
				Arrays.fill(jewels, "");
			}
			Output.armor(mhag.getOutFormat(), outSave, 
				title, slots, jewels);
		}

		// charm line

		String title = "";
		if(inUse[6])
		{
			title = getCharmNameWithSkill(mhagData);
			slots = Output.slotWord(mhag.getOutFormat(), numCharmSlot);
			jewels = getJewelNameShort(mhagData, 6);
		}
		else
		{
			title = MhagData.emptyName;
			slots = "";
			Arrays.fill(jewels, "");
		}
		Output.charm(mhag.getOutFormat(), outSave,
				title, slots, jewels);

		// Part 2 : Table (defense, elements, skills)
		// head line 2
		Output.head2nd(mhag.getOutFormat(), outSave);

		// check possible skill bonus (def up/down, element resist)
		int[] bonus = checkSkillBonus(mhagData);
		String bonusTitle = "";

		// defense line
		title = "Max Defense";

		int[] values = new int[5];
		Arrays.fill(values, 0);
		for(int i = 0; i < 5; i++)
		{
			if(inUse[i])
			{
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(lowRank)
					values[i] = armor.getDefenseLowRank();
				else
					values[i] = armor.getDefenseHighRank();
			}
		}

		if(bonus[5] != 0)
		{
			defense += bonus[5];
			if(bonus[5] > 0)
				bonusTitle = "<up>";
			else if(bonus[5] < 0)
				bonusTitle = "<down>";
		}

		Output.defense(mhag.getOutFormat(), outSave, title,
			values, defense, bonusTitle, bonus);

		// resistence
		String[] title5 = new String[5];
		title5[0] = "Resist: Fire";
		title5[1] = "        Water";
		title5[2] = "        Ice";
		title5[3] = "        Thunder";
		title5[4] = "        Dragon";

		for(int i = 0; i < 5; i++)
		{
			Arrays.fill(values, 0);
			for(int j = 0; j < 5; j++)
			{
				if(inUse[j])
				{
					Armor armor = mhagData.getArmor
						(j, armorID[j]);
					values[j] = armor.getResist()[i];
				}
			}
			bonusTitle = "";
			if(bonus[i] != 0 )
			{
				resist[i] += bonus[i];
				if(bonus[i] > 0)
					bonusTitle = "<up>";
				else if(bonus[i] < 0)
					bonusTitle = "<down>";
			}

			Output.resist(mhag.getOutFormat(), outSave, i, title5[i],
				values, resist[i], bonusTitle);
		}

		// skill points

		// check troso up
		if(numTorso > 0)
		{
			String[] torsoList = getListTorsoUp(mhagData);
  			Output.torso(mhag.getOutFormat(), outSave, torsoList,
				numSkill);
		}

		// skill points
		String effectName = "---";
		for(int i = 0; i < numSkill; i++)
		{
			int ifEff = 0;
			//skill effects
			if(i < numEffect)
			{
				Effect effect = mhagData.getEffect(effectID[i]);
				effectName = "* " + effect.getEffectName();
				if(skillPoint[i] > 0)
					ifEff = 1;
				else
					ifEff = -1;
			}
			else
				effectName = "  ---";

			//skill points
			values = extractPoints(mhagData, skillID[i]);

			//skill title
			Skill skill = mhagData.getSkill(skillID[i]);
			if((i == 0)&&(numTorso == 0))
			{
				title = "Skills: "+skill.getSkillName();
				Output.skill(mhag.getOutFormat(), outSave, true, title,
					values, effectName, ifEff, numSkill);
			}
			else
			{
				title = "        "+skill.getSkillName();
				Output.skill(mhag.getOutFormat(), outSave, false, title,
					values, effectName, ifEff, numSkill);
			}


		}

		// end
		Output.end(mhag.getOutFormat(), outSave);

		MhagUtil.logLine(mhag, "Armor Set Saved!");

	}

	// calculate set stats (simple version)
	public void quickCalcSet(Mhag mhag, MhagData mhagData)
	{
		// defense
		defense = 0;
		if(lowRank)
		{
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				defense += armor.getDefenseLowRank();
			}
		}
		else
		{
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				defense += armor.getDefenseHighRank();
			}
		}

		rate += defense / 8;  //high defense has more bonus

		// add skills
		addSkills(mhag, mhagData);
	}

	// check skill effects (for rating)
	public void rateEffects(Generator gen)
	{
		numEffect = 0;
		Arrays.fill(effectID, 0);
		Arrays.fill(effectSkillIndex, 0);

		for(int i =0; i < numSkill; i++)
		{
			int id = skillID[i];
			int point = skillPoint[i];

			int skillInd = matchID(gen.getSkills(),id);
			Skill skill = gen.getMhagData().getSkill(id);

			if(skillInd == -1)
			{
				if(skill.getHasNegative()) //always check nagative skills
				{
					if(point <= -10) //active negative effects
						if(skill.getBGSpec(blade))
							rate -= 20; //punish negative effects
				}
			}
			else //check required skills
			{
				Effect effect = gen.getMhagData().getEffect(gen.getEffects(skillInd));
				int trigger = effect.getEffectTrigger();

				if(trigger <= point) //triggered
				{
					if(skillInd <= 3) // first 4 skills
						rate += 50 + trigger;  //larger bonus
					else
						rate += 30 + trigger;  //the rest 6, fewer bonus
				}
				else //not triggered, plus the available points;
				{
					rate += point;
				}

			}

		}

	}

	public static int matchID(int[] skills, int id)
	{
		for(int i = 0; i < skills.length; i++)
		{
			if(skills[i] == id)
				return i;
		}
		return -1;
	}

	public void rateSlots(Generator gen)
	{
		int nSlot;
		for(int i = 0; i < 5; i++)  //armor slots
		{
			if(!inUse[i])continue;
			nSlot = gen.getMhagData().getArmor(i, armorID[i]).getNumSlot();
			for(int j = 0 ; j < numJewel[i]; j++)
			{
				Jewel jewel = gen.getMhagData().getJewel(jewelID[i][j]);
				nSlot -= jewel.getNumSlot();
			}
			if(nSlot > 0)
				rate += 3; //bonus for unused slots
		}

		if(inUse[5])  //weapon slots
		{
			nSlot = 0;
			for(int j = 0 ; j < numJewel[5]; j++)
			{
				Jewel jewel = gen.getMhagData().getJewel(jewelID[5][j]);
				nSlot += jewel.getNumSlot();
			}
			rate -= 5; //penalty for used slots (less weapon of choice)
		}

	}

	public void setRate(Generator gen)
	{
		rate = 0;
		quickCalcSet(gen.getMhag(), gen.getMhagData()); //plus rate defense;
		rateEffects(gen);  // rate skills
		rateSlots(gen);  //rate slot usage
		//rateCharm(gen);  //rate Charm , not supported in current mhag
	}

	public void checkSlot(MhagData mhagData, int[] slots)
	{
		Arrays.fill(slots, 0);
		for(int i = 0; i < 5; i++)
		{
			if(!inUse[i])continue;
			Armor armor =  mhagData.getArmor(i, armorID[i]);
			int nSlot = armor.getNumSlot();
			if((numTorso != 0) && (i == 2))
				slots[4] = nSlot;
			else
				slots[nSlot]++;

		}
		slots[numCharmSlot]++;

		//slots[0] for torso up
		slots[0] = numTorso;
	}

	//Inputs
	private String setName = unNamedSet;  // User-defined Set Name
	private boolean lowRank = false; // lr T / hr F
	private boolean blade = true; // Blade true, or Gunner false
	private int[] armorID;  // Armor ID for 5 Pieces
	private int[][] jewelID;  // Jewel IDs for 5 Pieces, Weapon & Charm
//	private int charmID = 0;  // Charm ID
	// charm info
	private int numCharmSlot = 0; // # of charm slot
	private int numCharmSkill = 0; // number of skills on Charm
	private int[] charmSkillID; // 2 Skill IDs on Charm
	private int[] charmSkillPoint; // 2 Skill Point for Charm Skills

	private boolean[] inUse; //  armor in use.  5: weapon, not apply
	private int[] numJewel; //  # of Jewel for each piece
	// armor/slot Use: [0,4] armor; [5] weapon; [6] charm;
	//Outputs
	private int defense = 0;  // Total Defense
	private int[] resist; // Total Resist
	private int numSkill = 0; // # Skills Involved
	private int[] skillID; // All Skill ID
	private int[] skillPoint; // All Skill ID
	private int numEffect = 0; // # of Activated Skills
	private int[] effectID; // all Effect IDs (max 8)
	private int[] effectSkillIndex; // Skill Indeces in the List skillID
	private int  numTorso = 0; // # of Torso Up
	private int rate = 0; // # Reserved for sets Evaluation

	// hidden variable
	private boolean female = false;  //gender, only used in GUI

	static final String unNamedSet = "Unnamed Set";

}
