/**
 * @program MHAG
 * @ Set Class , a set & stats
 * @version 1.0
 * @author Tifa@mh3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Set {

	public Set()
	{
		armorID = new int[5];
		jewelID = new int[7][3];
		charmSkillID = new int[2];
		numJewel = new int[7];
		inUse = new boolean[7];

		Arrays.fill(armorID, 0);
		Arrays.fill(charmSkillID, 0);
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
	public String getSetName()
	{
		return setName;
	}

	// set set name
	public void setSetName(String aSetName)
	{
		 setName = aSetName;
	}

	// get low/high rank
	public boolean getLowRank()
	{
		return lowRank;
	}

	// set low/high rank
	public void setLowRank(boolean aLowRank)
	{
		lowRank = aLowRank;
	}

	// get blader/gunner info
	public boolean getBlade()
	{
		return blade;
	}

	// set blader/gunner info
	public void sedBlade(boolean aBlade)
	{
		blade = aBlade;
	}

	// get armor id
	public int[] getArmorID()
	{
		return armorID;
	}

	// set armor id
	public void setArmorID(int[] aArmorID)
	{
		armorID = aArmorID;
	}

	// get jewel id
	public int[][] getJewelID()
	{
		return jewelID;
	}

	// set jewel id
	public void setJewelID(int[][] aJewelID)
	{
		jewelID = aJewelID;
	}

	// get charm id
	public int getCharmID()
	{
		return charmID;
	}

	// set charm id
	public void setCharmID(int aCharmID)
	{
		charmID = aCharmID;
	}

	// get charm # of Skill
	public int getNumCharmSkill()
	{
		return numCharmSkill;
	}

	// set charm # of skill
	public void setNumCharmSKill(int aNumCharmSkill)
	{
		numCharmSkill = aNumCharmSkill;
	}

	// get charm Skill id
	public int[] getCharmSkillID()
	{
		return charmSkillID;
	}

	// set charm skill id
	public void setCharmSKillID(int[] aCharmSkillID)
	{
		charmSkillID = aCharmSkillID;
	}

	// get in use set pieces
	public boolean getInUse(int part)
	{
		return inUse[part];
	}

	// set in use set pieces
	public void setInUse(int part, boolean useOrNot)
	{
		inUse[part] = useOrNot;
	}

	// get number of slots
	public int getNumSlot(int part)
	{
		return numJewel[part];
	}

	// get number of slots
	public void setNumSlot(int part, int value)
	{
		numJewel[part] = value;
	}

	// get outputs

	// get set defense
	public int getDefense()
	{
		return defense;
	}

	// get set resist
	public int[] getResist()
	{
		return resist;
	}

	// get num of Skills of the set
	public int getNumSkill()
	{
		return numSkill;
	}

	// get Skill IDs of the set
	public int[] getSkillID()
	{
		return skillID;
	}

	// get Skill Points of the set
	public int[] getSkillPoint()
	{
		return skillPoint;
	}

	// get num of effects (activated skills) of the set
	public int getNumEffect()
	{
		return numEffect;
	}

	// get effects ID(activated skills) of the set
	public int[] getEffectID()
	{
		return effectID;
	}

	// get effect skill index (map effect list to skillID of the set)
	public int[] getEffectSkillIndex()
	{
		return effectSkillIndex;
	}

	// get number of troso up of the set
	public int getNumTorso()
	{
		return numTorso;
	}

	// get set rate
	public int getRate()
	{
		return rate;
	}

	// set a set from input file (simple input version)
	public void setSetFromFile(Mhag mhag, String file) throws FileNotFoundException
	{
		MhagUtil.logLine(mhag, "Reading Set From Input File ...");
		init();

		String errorLine = "    Error in Input File, Please Check!";
		Scanner in = new Scanner(new File(file));
		int num = 0;
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
			else if(opt.equals("charm"))  //charm id
			{
				if(args.length() != 0)
				{
					charmID = Integer.valueOf(args);
					inUse[6] = true;
				}
			}
			else if(opt.equals("charm skill"))  //charm skill
			{
				numCharmSkill =
					MhagUtil.extractInt(args, 2, values);
				for (int i = 0; i < numCharmSkill; i++)
					charmSkillID[i] = values[i];
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
				line.append(partCode + " ");
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
			line.append(charmID);  //charm id
			line.append(" ");
			line.append(numCharmSkill); // charm skill
			line.append(" ");
			for (int j = 0; j < numCharmSkill; j++)
			{
				line.append(charmSkillID[j]);
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
		for(int i = 1; i < 7; i++)
			Arrays.fill(jewelID[i], 0);
		charmID = 0;
		numCharmSkill = 0;
		Arrays.fill(charmSkillID, 0);
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

	}
	// set a set from code (batch version)
	public void setSetFromCode(Mhag mhag, String line)
	{
		init();
		String errorLine = "    Error in Batch Inputs, Please Check!";

		// first word before : set name
		int splitPos = MhagUtil.extractWordPos(line, 0);
		if( splitPos == -1)  MhagUtil.logLine(mhag, errorLine);
		String word = MhagUtil.extractWord(line, 0, splitPos);
		if(word.equals(""))
			setName = unNamedSet;
		else
			setName = word;

		// the rest part : set parameters
		word = MhagUtil.extractWord(line, splitPos +1, -1);

		String[] wordArray = word.split(" ");
		int numWords = wordArray.length;

		if(numWords < 2) MhagUtil.logLine(mhag, errorLine);

		if(wordArray[0].equals("H"))
			lowRank = false;
		else if(wordArray[0].equals("L"))
			lowRank = true;

		if(wordArray[1].equals("B"))
			blade = false;
		else if(wordArray[1].equals("G"))
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
				charmID = Integer.valueOf(wordNow);
				inUse[6] = true;

				wordNow = wordArray[++wordIndex];
				numCharmSkill = Integer.valueOf(wordNow);
				for (int j = 0; j < numCharmSkill; j++)
				{
					wordNow = wordArray[++wordIndex];
					charmSkillID[j] = Integer.valueOf(wordNow);
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
			}
			wordIndex++;

		}

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

			// charm
			if(inUse[6])
			{
				Charm charm = mhagData.getCharm(charmID);
				if(!charm.getLowRank())
				{
					MhagUtil.logLine(mhag,errLine);
					return false;
				}
			}
		}

		// B/G check
		if(blade)
		{
			String errLine2  = "   Error! not for Blademaster!";
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(armor.getBladeOrGunner().equals("G"))
				{
					MhagUtil.logLine(mhag,errLine2);
					return false;
				}
			}
		}
		else
		{
			String errLine3  = "   Error! not for Gunner!";
			for(int i = 0; i < 5; i++)
			{
				if(!inUse[i])continue;
				Armor armor = mhagData.getArmor(i, armorID[i]);
				if(armor.getBladeOrGunner().equals("B"))
				{
					MhagUtil.logLine(mhag,errLine3);
					return false;
				}
			}

		}

		// slots check

		int nSlot = 0;
		int nSlotArmor = 0;
		String  errLine4 = "   Error! Jewels require too many slots";

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
				MhagUtil.logLine(mhag,errLine4);
				return false;
			}
		}

		// weapon slots  < 3
		if( numJewel[5] != 0)
		{
			nSlot = 0;
			for(int j = 0; j < numJewel[5]; j++)
			{
				Jewel jewel = mhagData.
					getJewel(jewelID[5][j]);
				nSlot += jewel.getNumSlot();
			}
			if(nSlot > 3)
			{
				MhagUtil.logLine(mhag,errLine4);
				return false;
			}
		}

		//charm slots
		if(inUse[6])
		{
			Charm charm = mhagData.getCharm(charmID);

			int nSlotCharm = charm.getNumSlot();

			nSlot = 0;
			for(int j = 0; j < numJewel[6]; j++)
			{
				Jewel jewel = mhagData.
					getJewel(jewelID[6][j]);
				nSlot += jewel.getNumSlot();
			}
			if(nSlot > nSlotArmor)
			{
				MhagUtil.logLine(mhag,errLine4);
				return false;
			}
		}

		//charm skills
		if(inUse[6])
		{
			Charm charm = mhagData.getCharm(charmID);
			for (int i = 0; i < numCharmSkill; i++)
			{
				Skill skill = mhagData.getSkill(charmSkillID[i]);
				String sClass1 = skill.getSkillClass();
				if(!charm.getSkillClass()[i].equals(sClass1))
				{
					MhagUtil.logLine(mhag,
						"   Error! Charm Skill Class incorrect!");
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
		String line2 = "   Total Resist : " +
			Arrays.toString(resist);
		MhagUtil.logLine(mhag, line2);

		// add skills
		addSkills(mhag, mhagData);
		MhagUtil.logLine(mhag, "   Skill List : ");
		for (int i =0; i < numSkill; i++)
		{
			Skill skill = mhagData.getSkill(skillID[i]);
			String line3 = String.format("%3d : %-10s  %+3d",
				i, skill.getSkillName(),skillPoint[i]);
			MhagUtil.logLine(mhag, line3);
		}

		// check skill effects
		checkEffects(mhagData);
		MhagUtil.logLine(mhag, "   Effect List : ");
		for (int i =0; i < numEffect; i++)
		{
			Effect effect = mhagData.getEffect(effectID[i]);
			String line4 = String.format("%3d : %-10s",
				i, effect.getEffectName());
			MhagUtil.logLine(mhag, line4);
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
		String line = String.format("   # of Torso Up : %d",numTorso);
		MhagUtil.logLine(mhag, line);

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

				if((i == 2)&&(numTorso != 0))
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

				if((i == 2)&&(numTorso != 0))
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
			Charm charm = mhagData.getCharm(charmID);

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

				{
					skillPoint[pos] += charm.getSkillPoint
						()[j];
				}

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
				for(int j = 1; j < nEffect; j++)
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
	public void sortSkill(Mhag mhag, MhagData mhagData)
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
				eSkillInd[num] = ind;
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

	public void save(Mhag mhag, MhagData mhagData)
	{
		sortSkill(mhag, mhagData);  //sort Skill for outputs


	}

	//Inputs
	private String setName = unNamedSet;  // User-defined Set Name
	private boolean lowRank = false; // lr T / hr F
	private boolean blade = true; // Blade true, or Gunner false
	private int[] armorID;  // Armor ID for 5 Pieces
	private int[][] jewelID;  // Jewel IDs for 5 Pieces, Weapon & Charm
	private int charmID = 0;  // Charm ID
	private int numCharmSkill = 0; // number of skills on Charm
	private int[] charmSkillID; // 2 Skill IDs on Charm
	private boolean[] inUse; //  armor in use.  5: weapon, not apply
	private int[] numJewel; //  # of Jewel for each piece
	// armor/slot Use: [0,4] armor; [5] weapon; [6] jewel;
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

	static final String unNamedSet = "Unnamed Set";

}
