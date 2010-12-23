
import java.util.Arrays;

public class Jewel {

	public void Jewel()
	{
		skillID = new int[2];
		skillPoint = new int[2];

		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
	}

	// get armor name
	public String getJewelName()
	{
		return jewelName;
	}

	private int jewelID = 0;
	private String jewelName = "";
	private boolean lowRank = false;
	private int numSlot = 0, numSkill = 0;
	private int[] skillID, skillPoint;

	static int jewelIDTot = 0;
}
