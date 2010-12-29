/**
 * @program MHAG
 * @ Skill Class
 * @version 1.0
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
				skill.skillClass = word;

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
				skill.effectTrigger[skill.numEffect] =
					Integer.valueOf(word);
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

	// get skill class
	public String getSkillClass() {return skillClass;}

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

	private int skillID = 0; // Skill ID
	private String skillName = "";  // Skill Name
	private String skillClass = "";  // A/B/C/D
	private int numEffect = 0;  // # Effects , 6 max, 3 pos ,3 neg
	private String[] effectName;   // Effect Name
	private int[] effectID;   // Effect IDs
	private int[] effectTrigger;  //skill points to tigger effect

	static int skillIDTot = 0;
}
