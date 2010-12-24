/* @program MHAG
 * @ MhagData Class , store MHAG data
 * @version 1.0
 * @author Tifa@mh3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MhagData {

	public MhagData()
	{
	}

	// read data file
	public void readFile(Mhag mhag) throws FileNotFoundException
	{
		readSkill(mhag);
		genEffectList();

		genRefSkill();
		genRefEffect();
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
		if(mhag.getLogOpt() != 2)
			mhag.getOutLog().printf
				("Total Number of Skills : %d\n",nMax);

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
			ioErr = Skill.readSkillLine(line, skillList[skillIndex]);
			if(ioErr != 0)
			{
				if(mhag.getLogOpt() != 2)
					mhag.getOutLog().printf
						("Error Found in Skill File\n");
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
				effectList[n].getEffectFromSkill(skill, j);
				n++;
			}
		}

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

	// write skill reference file
	public void genRefEffect() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(fileRefEffect);
		for (int i = 0; i < Effect.effectIDTot; i++)
		{
			out.printf("%3d: %s\n",effectList[i].getEffectID(),
				effectList[i].getEffectName());
		}
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

	// Some Constants
	private final String emptyName = "-----";

	private Armor[] armorList;
	private Skill[] skillList;
	private Jewel[] jewelList;
	private Charm[] charmList;
	private Effect[] effectList;
}
