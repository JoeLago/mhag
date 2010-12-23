/* @program MHAG
 * @ Effect Class
 * @version 1.0
 * @author Tifa@mh3
 */

public class Effect {

	public Effect()
	{
	}

	// get armor name
	public String getEffeectName()
	{
		return effectName;
	}

	private int effectID = 0;  // Effect ID
	private String effectName = "";   // Effect Name
	private int skillID = 0;  // SKill ID
	private int effectTrigger = 0; // Trigger Point

	static int effectIDTot = 0;
}
