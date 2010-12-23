
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

		skillID = skillIDTot;  // skillID  start from 1
		skillIDTot++;
	}

	//extract word from a line
	private static int extractWordPos(String line, int startPos)
	{
		return line.indexOf(":",startPos);
	}

	private static String extractWord(String line, int startPos, int endPos)
	{
		String word;
		if(endPos == -1)
		{
			word = line.substring(startPos);
		}
		else
		{
			word = line.substring(startPos, endPos - 1);
		}
		return word.trim();
	}

    	// read skill entry from a line
	public static int readSkillLine(String line, Skill skill)
	{
		// skill id
		skill.skillID = skillIDTot;

		int startPos = 0;
		int endPos = 0;
		int wordIndex = 0;
		skill.numEffect = 0;
		String word = "";
		while(line != null )
		{
			endPos = extractWordPos(line, startPos);
			word = extractWord(line, startPos, endPos);
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
				if(endPos == -1)return 1;

				// read effects
				skill.effectName[skill.numEffect] = word;
				startPos = endPos + 1;
				endPos = extractWordPos(line, startPos);
				word = extractWord(line, startPos, endPos);
				skill.effectTrigger[skill.numEffect] =
					Integer.valueOf(word);
				skill.numEffect++;
			}


			if(endPos  == -1)
			{
				if(wordIndex <= 2)return 1;
				break;
			}
			startPos = endPos + 1;
		}

		return 0;
	}


	// get armor name
	public String getSkillName()
	{
		return skillName;
	}

	// get Effect Name
	public String[] getEffectName()
	{
		return effectName;
	}

	private int skillID = 0; // Skill ID
	private String skillName = "";  // Skill Name
	private String skillClass = "";  // A/B/C/D
	private int numEffect = 0;  // # Effects , 6 max, 3 pos ,3 neg
	private String[] effectName;   // Effect Name
	private int[] effectID;   // Effect IDs
	private int[] effectTrigger;  //skill points to tigger effect

	static int skillIDTot = 0;
}
