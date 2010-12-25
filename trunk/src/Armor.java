/**
 * @program MHAG
 * @ Armor Class
 * @version 1.0
 * @author Tifa@mh3
 */

import java.util.Arrays;

public class Armor {

	public Armor()
	{
		defense = new int[2];
		resist = new int[5];
		skillID = new int[5];
		skillName = new String[5];
		skillPoint = new int[5];

		Arrays.fill(defense, 0);
		Arrays.fill(resist, 0);
		Arrays.fill(skillID, 0);
		Arrays.fill(skillName, "");
		Arrays.fill(skillPoint, 0);
	}

	// read armor entry from a line, get part info
	public static String getBodyPartFromLine(String line)
	{
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

			// read Armor Class
			if(wordIndex == 3)break;
			startPos = endPos + 1;
		}
		return word;
	}

	// read armor entry from a line
	// nBodyPart : body part Index (use & check)
	public static int setArmorFromLine(String line, int nBodyPart,
		Armor armor)
	{
		// armor id
		armor.armorID = armorIDTot[nBodyPart]++;

		int startPos = 0;
		int endPos = 0;
		int wordIndex = 0;
		String word = "";
		int resistIndex = 0;
		while(line != null )
		{
			endPos = MhagUtil.extractWordPos(line, startPos);
			word = MhagUtil.extractWord(line, startPos, endPos);
			//System.out.println(word);

			wordIndex++;

			if(wordIndex == 1)
			{
				// read armor Name
				armor.armorName = word;

			}
			else if(wordIndex == 2)
			{
				// read blader/gunner/all
				String strBGA = "BGA";
				if(strBGA.indexOf(word) != -1)
				{
					armor.bladeOrGunner = word;
				}
				else
				{
					return 1;  // incorrect BGA;
				}
			}
			else if(wordIndex == 3)
			{
				//Body Part (check)
				if( nBodyPart == convertBodyPart(word))
				{
					armor.bodyPart = nBodyPart;
				}
				else
				{
					return 1;  // inconsistent body part ind
				}
			}
			else if(wordIndex == 4)
			{
				// read low rank defense

				if(word.equals("--"))
				{
					armor.lowRank = false;
					armor.defense[0] = 0;
				}
				else
				{
					armor.lowRank = true;
					int def = 0;
					def = Integer.valueOf(word);
					if((def <= 0)||(def > 200))
					{
						return 1; // incorrect defense
					}
					else
					{
						armor.defense[0] = def;
					}
				}
			}
			else if(wordIndex == 5)
			{
				// read high rank defense

				int def = 0;
				def = Integer.valueOf(word);
				if((def <= 0)||(def > 200))
				{
					return 1; // incorrect defense
				}
				else
				{
					armor.defense[1] = def;
				}
			}
			else if (wordIndex == 6)
			{
				// read # of slots

				int nSlot = Integer.valueOf(word);
				if((nSlot < 0)||(nSlot > 3))
				{
					return 1;  // armor slot 0 - 3
				}
				else
				{
					armor.numSlot = nSlot;
				}
			}
			else if ((wordIndex >= 7 )&&(wordIndex <= 11))
			{
				// read reisistance

				int nResist = Integer.valueOf(word);
				if((nResist < -20)||(nResist > 20))
				{
					return 1;  // armor resist range[-20,20]
				}
				else
				{
					armor.resist[resistIndex] = nResist;
					resistIndex++;
				}
			}
			else
			{
				// read skills name/points

				// Torso Up exception (no skill point)
				if( word.equals("Torso Up"))
				{
					armor.skillName[armor.numSkill] = word;
					armor.numSkill++;
					break;
				}

				if(endPos == -1)return 1;  // no skill point
				if(armor.numSkill == 5)return 1;  // skill <= 5

				// read Skill
				armor.skillName[armor.numSkill] = word;
				startPos = endPos + 1;
				endPos = MhagUtil.extractWordPos(line, startPos);
				word = MhagUtil.extractWord
					(line, startPos, endPos);
				armor.skillPoint[armor.numSkill] =
					Integer.valueOf(word);
				armor.numSkill++;
			}

			if(endPos  == -1)
			{
				if(wordIndex <= 10)return 1;  // no skill ok
				break;
			}
			startPos = endPos + 1;
		}
		return 0;
	}

	// calculate max possible armor pieces for one part
	public static int getArmorMax()
	{
		int num = 0;
		for(int i = 0; i < 5 ; i++)
		{
			if(armorIDTot[i] > num)
			{
				num = armorIDTot[i];
			}
		}
		return num;
	}


	// get armor name
	public String getArmorName()
	{
		return armorName;
	}

	// get armor ID
	public int getArmorID()
	{
		return armorID;
	}

	// get number of skill
	public int getNumSkill()
	{
		return numSkill;
	}

	// get skill names
	public String[] getSkillName()
	{
		return skillName;
	}

	// get skill points
	public int[] getSkillPoint()
	{
		return skillPoint;
	}

	// get skill IDs
	public int[] getSkillID()
	{
		return skillID;
	}

	// geta low rank
	public boolean getLowRank()
	{
		return lowRank;
	}

	// set skill ID in an armor
	public void setSkillID(int[] id)
	{
		for (int i = 0; i < 5; i++)
		{
			skillID[i] = id[i];
		}
	}

	// get blader/gunner info
	public String getBladeOrGunner()
	{
		return bladeOrGunner;
	}

	// get body part
	public int getBodyPart()
	{
		return bodyPart;
	}

	// get # of slots
	public int getNumSlot()
	{
		return numSlot;
	}

	// get lowrank defense
	public int getDefenseLowRank()
	{
		return defense[0];
	}

	// get highrank defense
	public int getDefenseHighRank()
	{
		return defense[1];
	}

	// get resist
	public int[] getResist()
	{
		return resist;
	}

	// convert armor part character to index
	public static int convertBodyPart(String aBodyPart)
	{
		return Armor.partFull.indexOf(aBodyPart);
	}

	// convert armor part index to character
	public static String convertBodyPart(int index)
	{
		return partFull.substring(index,index);
	}

	private int armorID = 0;  // Armor ID
	private String armorName = "";   // Armor Piece Name
	private String bladeOrGunner = "";  // Blade/Gunner/All
	private boolean lowRank = false; //lr Y / hr N
	private int bodyPart = 0;  // Head/Chest/Arms/Waist/Legs
	private int numSlot = 0;   // # of slots: 0 - 3
	private int[] defense;  // 0: lr, 1: hr defense
	private int[] resist;  // 0-4: Fire/Water/Ice/Thunder/Dragon
	private int numSkill = 0;  // # of Skill types (max 5 for all mh3 armor)
	private int[] skillID;  // Skill ID
	private String[] skillName;  // Skill Name
	private int[] skillPoint; // Skill Points

	static int[] armorIDTot;
	static final String partFull = "HCAWL";
	static
	{
		armorIDTot = new int[5];
		Arrays.fill(armorIDTot, 0);
	}
}
