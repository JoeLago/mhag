
import java.util.Arrays;

public class Skill {

	public void Skill()
	{
		effectID = new int[6];
		effectTrigger = new int[6];

		Arrays.fill(effectID, 0);
		Arrays.fill(effectTrigger, 0);
	}

	// get armor name
	public String getSkillName()
	{
		return skillName;
	}

	private int skillID = 0;
	private String skillName = "";
	private String skillClass ="C";
	private int numEffect = 0;
	private int[] effectID, effectTrigger;

	static int skillIDTot = 0;
}
