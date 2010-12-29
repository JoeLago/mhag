/**
 * @program MHAG
 * @ Charm Class
 * @version 1.0
 * @author Tifa@mh3
 */

import java.util.Arrays;

public class Charm {

	public Charm()
	{
		skillClass = new String[2];
		skillPoint = new int[2];

		Arrays.fill(skillClass, "");
		Arrays.fill(skillPoint, 0);
	}

    	// read Charm entry from a line
	public static int setCharmFromLine(String line, Charm charm)
	{
		// charm id
		charm.charmID = charmIDTot++; // start from 1

		int startPos = 0;
		int endPos = 0;
		int wordIndex = 0;
		String word = "";
		while(line != null )
		{
			endPos = MhagUtil.extractWordPos(line, startPos);
			word = MhagUtil.extractWord(line, startPos, endPos);
			//System.out.println(word);

			wordIndex++;

			if(wordIndex == 1)
			{
				// read Charm Class
				int nClass = Integer.valueOf(word);
				if((nClass < 1)||(nClass > 5))
				{
					return 1;
					// class range: 1-4 class,5 reserve
				}
				else
				{
					charm.charmClass = nClass;

				}

			}
			else if(wordIndex == 2)
			{
				// read # of slots
				int nSlot = Integer.valueOf(word);
				if((nSlot < 0)||(nSlot > 3))
				{
					return 1;  // jewel needs slot 0 - 3
				}
				else
				{
					charm.numSlot = nSlot;
				}
			}
			else if(wordIndex == 3)
			{
				// read low/high rank
				if(word.equals("L"))
				{
					charm.lowRank = true;
				}
				else if (word.equals("H"))
				{
					charm.lowRank = false;
				}
				else
				{
					return 1;
				}
			}
			else if(wordIndex == 4)
			{
				// read percentage point
				int nSlot = Integer.valueOf(word);
				if((nSlot < 0)||(nSlot > 100))
				{
					return 1;  // percentage [0, 100]
				}
				else
				{
					charm.percentage = nSlot;
				}
			}

			else
			{
				// read skills class/points

				if(endPos == -1)return 1; //error no skill point
				if(charm.numSkill == 2)return 1;  // skill <= 2

				// read Skill
				charm.skillClass[charm.numSkill] = word;
				startPos = endPos + 1;
				endPos = MhagUtil.extractWordPos(line, startPos);
				word = MhagUtil.extractWord
					(line, startPos, endPos);
				charm.skillPoint[charm.numSkill] =
					Integer.valueOf(word);
				charm.numSkill++;
			}

			if(endPos  == -1)
			{
				if(wordIndex <= 3)return 1;  // no skill ok
				break;
			}
			startPos = endPos + 1;
		}

		return 0;
	}

	// get charm name
	public String getCharmName() {return charmName;}

	// get charm ID
	public int getCharmID() {return charmID;}

	// set charm name
	public void setCharmName()
	{
		StringBuffer name = new StringBuffer("");

		String line = "";

		for (int i = 0; i < numSkill; i++)
		{
			line=String.format("%s%+d, ",
				skillClass[i],skillPoint[i]);
			name.append(line);
		}
		for (int i = 0; i < numSlot; i++)
		{
			name.append("O");
		}
		if(numSlot == 0)
		{
			charmName = name.substring(0, name.length() - 2);
		}
		else
		{
			charmName = name.toString();
		}
	}

	// get # of skills
	public int getNumSkill() {return numSkill;}

	// get skill class
	public String[] getSkillClass() {return skillClass;}

	// get skill point
	public int[] getSkillPoint() {return skillPoint;}

	// get charm class
	public int getCharmClass() {return charmClass;}

	// get percentage
	public int getPercentage() {return percentage;}

	// geta # of Slot
	public int getNumSlot() {return numSlot;}

	// geta low rank
	public boolean getLowRank() {return lowRank;}

	// set charm class
	public void setPercentage(int value) {percentage = value;}

	private int charmID = 0; // Charm ID
	private String charmName = ""; // skillClass + skillPoint
	private int charmClass = 0; // 1,2,3 for mh3
	private int numSlot = 0; // 0-3 slots
	private boolean lowRank = false;  // lr Y / hr N
	private int percentage = 0;  //percentage rate for the current charmClass
	private int numSkill = 0;  // two skills max
	private String[] skillClass; // A/B/C/D
	private int[] skillPoint;  // -10 : 10

	static int charmIDTot = 0;
}
