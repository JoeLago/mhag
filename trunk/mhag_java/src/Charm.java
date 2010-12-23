
import java.util.Arrays;

public class Charm {

	public void Charm()
	{
		skillClass = new String[2];
		skillPoint = new int[2];

		Arrays.fill(skillClass, "");
		Arrays.fill(skillPoint, 0);
	}

	// get armor name
	public String getCharmName()
	{
		return charmName;
	}

	private int charmID = 0;
	private String charmName = "";
	private boolean lowrank = false;
	private int numSlot=0, numSkill = 0, charmClass = 0, pecentage = 0;
	private String[] skillClass;
	private int[] skillPoint;

	static int charmIDTot = 0;
}
