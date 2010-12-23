/* @program MHAG
 * @ MhagData Class , store MHAG data
 * @version 1.0
 * @author Tifa@mh3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MhagData {

	public MhagData()
	{
	}

	// read data file
	public void readFile(Mhag mhag) throws FileNotFoundException
	{
		readSkill(mhag);
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
			skillIndex++;
		}

	}


	// Constants for file names
	private final String dir = "data/";
	private final String fileArmor = dir+"armor.dat";
	private final String fileJewel = dir+"jewel.dat";
	private final String fileSkill = dir+"skill.dat";
	private final String fileCharm = dir+"charm.dat";

	// Some Constants
	private final String emptyName = "-----";

	private Armor[] armorList;
	private Skill[] skillList;
	private Jewel[] jewelList;
	private Charm[] charmList;
	private Effect[] effectList;
}
