/**
 * @program MHAG
 * @ Skill Class
 * @version 2.1
 * add generator support
 * support new talisman system
 * @author Tifa@mh3
 */

import java.util.Arrays;

public class Skill {

	public Skill()
	{
		effectID = new int[6];
		effectTrigger = new int[6];
		effectName = new String[6];

		Arrays.fill(effectID, 0);
		Arrays.fill(effectTrigger, 0);
		Arrays.fill(effectName, "");
		for(int i = 0; i < 2; i++)
		{
			Arrays.fill(jewelID[i], -1);
			Arrays.fill(jewelSkillPoint[i], 0);
			Arrays.fill(maxSkillPoint[i], 0);
		}
	}

    	// read skill entry from a line
	public static int setSkillFromLine(String line, Skill skill)
	{
		// skill id
 		skill.skillID = skillIDTot++;  //start from 1;

		int startPos = 0;
		int endPos = 0;
		int wordIndex = 0;
		skill.numEffect = 0;
		String word = "";
		while(line != null )
		{
			endPos = MhagUtil.extractWordPos(line, startPos);
			word = MhagUtil.extractWord(line, startPos, endPos);
			//System.out.println(word);

			wordIndex++;

			if(wordIndex == 1)
			{
				// read Skill Name
				skill.skillName = word;

			}
			else if(wordIndex == 2)
			{
				// read Skill Class
				int[] numbers = new int [8];
				int nMax = 0;

				MhagUtil.extractInt(word, nMax, numbers);
				for ( int i = 0; i < 2; i++)
					for ( int j = 0; j < 4; j++)
						skill.maxSkillPoint[i][j] = numbers[i*4+j];


			}
			else
			{
				// read effects name/trigger points

				if(endPos == -1)return 1; //error no skill point
				if(skill.numEffect == 6)return 1; // effect <= 6

				// read effects
				skill.effectName[skill.numEffect] = word;
				startPos = endPos + 1;
				endPos = MhagUtil.extractWordPos(line, startPos);
				word = MhagUtil.extractWord
					(line, startPos, endPos);
				int trigger = Integer.valueOf(word);
				skill.effectTrigger[skill.numEffect] = trigger;

				if(trigger < 0) skill.hasNegative = true;

				skill.numEffect++;
			}

			if(endPos  == -1)
			{
				if(wordIndex <= 2)return 1;  // error no effect
				break;
			}
			startPos = endPos + 1;
		}

		return 0;
	}

	// get Skill ID
	public int getSkillID() {return skillID;}

	// get skill name
	public String getSkillName() {return skillName;}

	// get max skill point
	public int[][] getMaxSkillPoint() {return maxSkillPoint;}

	public int getMaxSkillPoint(boolean lowRank, int nSlot)
	{
		if(lowRank)
			return maxSkillPoint[0][nSlot];
		else
			return maxSkillPoint[1][nSlot];
	}

	// get number of effects
	public int getNumEffect() {return numEffect;}

	// get Effect Name
	public String[] getEffectName() {return effectName;}

	// get Effect ID
	public int[] getEffectID() {return effectID;}

	// set Effect ID
	public void setEffectID(int index, int id) {effectID[index] = id;}

	// get Effect Trigger Points
	public int[] getEffectTrigger() {return effectTrigger;}

	// get JewelID
	public int[] getJewelID(boolean lowRank)
	{
		if(lowRank)
			return jewelID[0];
		else
			return jewelID[1];
	}

	public int getJewelID(boolean lowRank, int nSlot)
	{
		if(lowRank)
			return jewelID[0][nSlot];
		else
			return jewelID[1][nSlot];
	}

	// set JewelID
	public void setJewelID(boolean lowRank, int nSlot, int id)
	{
		if(lowRank)
			jewelID[0][nSlot] = id;
		else
			jewelID[1][nSlot] = id;
	}

	// get JewelSkillPoint
	public int[] getJewelSkillPoint(boolean lowRank)
	{
		if(lowRank)
			return jewelSkillPoint[0];
		else
			return jewelSkillPoint[1];
	}

	public int getJewelSkillPoint(boolean lowRank, int nSlot)
	{
		if(lowRank)
			return jewelSkillPoint[0][nSlot];
		else
			return jewelSkillPoint[1][nSlot];
	}

	// set JewelSkillPoint
	public void setJewelSkillPoint(boolean lowRank, int nSlot, int points)
	{
		if(lowRank)
			jewelSkillPoint[0][nSlot] = points;
		else
			jewelSkillPoint[1][nSlot] = points;
	}

	// get/set hasNegative
	public boolean getHasNegative() {return hasNegative;}

	public void setHasNegative(boolean ifNeg) {hasNegative = ifNeg;}

	// rule out blade/gunner specific negative skills, note: this works for mhtri and mhp3rd
	public boolean getBGSpec(boolean blade)
	{
		if(blade)
			if("Precision Recoil Reload Spd".contains(skillName))
				return false;
			else
				return true;
		else
			if("FastCharge Sharpener Sharpness".contains(skillName))
				return false;
			else
				return true;
	}

	private int skillID = 0; // Skill ID
	private String skillName = "";  // Skill Name
//	private String skillClass = "";  // A/B/C/D  
	private int[][] maxSkillPoint = new int [2][4]; //max point (1st index: low rank 0, high rank 1)
							// 2nd index: 0 - 3 slots
	private int numEffect = 0;  // # Effects , 6 max, 3 pos ,3 neg
	private String[] effectName;   // Effect Name
	private int[] effectID;   // Effect IDs
	private int[] effectTrigger;  //skill points to tigger effect

	//generator data
	private int[][] jewelID = new int[2][4]; //jewelID for the skill (1st index: lowrank 0, highrank 1)
	private int[][] jewelSkillPoint = new int[2][4]; // skill points on the jewel
	private boolean hasNegative = false; // has negative effect or not

	static int skillIDTot = 0;
}
