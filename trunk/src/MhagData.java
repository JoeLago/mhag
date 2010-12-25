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
		indexSkillClass = new int[5][Skill.skillIDTot];
		numSkillClass = new int[5];
		Arrays.fill(numSkillClass, 0);
		for (int i = 0; i < 5; i++)
			Arrays.fill(indexSkillClass[i], 0);
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
			indexSkillClass[classID][numSkillClass[classID]++] =
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
	}

	// write skill class file
	public void genRefSkillClass() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefSkillClass);

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < numSkillClass[i]; j++)
			{
				out.printf("%3d %3d: %s\n",i,j,
					skillList[indexSkillClass[i][j]].
					getSkillName());
			}
		}

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

	// Some Constants
	private final String emptyName = "-----";

	// data
	private Armor[][] armorList;
	private Skill[] skillList;
	private Jewel[] jewelList;
	private Charm[] charmList;
	private Effect[] effectList;

	// indeces
	private int[][] indexSkillClass;  // skill id list
	private int[] numSkillClass;  // number skill class
}
