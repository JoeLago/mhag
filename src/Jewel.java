/* @program MHAG
 * @ Jewel Class
 * @version 1.0
 * @author Tifa@mh3
 */

import java.util.Arrays;

public class Jewel {

	public Jewel()
	{
		skillID = new int[2];
		skillPoint = new int[2];

		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
	}

	// get jewel name
	public String getJewelName()
	{
		return jewelName;
	}

	private int jewelID = 0; // Jewel ID
	private String jewelName = ""; //Jewel Name
	private boolean lowRank = false; // lr Y/ hr N
	private int numSlot = 0;  // 0 -3 slots
	private int numSkill = 0; // 2 skills max
	private int[] skillID;  //  Skill ID
	private int[] skillPoint;  // 1st positve; 2nd negative

	static int jewelIDTot = 0;
}
