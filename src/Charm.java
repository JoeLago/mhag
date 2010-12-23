/* @program MHAG
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

	// get armor name
	public String getCharmName()
	{
		return charmName;
	}

	private int charmID = 0; // Charm ID
	private String charmName = ""; // skillClass + skillPoint
	private int charmClass = 0; // 1,2,3 for mh3
	private int numSlot = 0; // 0-3 slots
	private boolean lowrank = false;  // lr Y / hr N
	private int pecentage = 0;  //percentage rate for the current charmClass
	private int numSkill = 0;  // two skills max
	private String[] skillClass; // A/B/C/D
	private int[] skillPoint;  // -10 : 10

	static int charmIDTot = 0;
}
