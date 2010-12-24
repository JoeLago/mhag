/* @program MHAG
 * @ Effect Class
 * @version 1.0
 * @author Tifa@mh3
 */

public class Effect {

	public Effect()
	{
	}

	// get effect list
	public void getEffectFromSkill(Skill skill, int ithEffect)
	{
		effectID = effectIDTot;
		effectName = skill.getEffectName()[ithEffect];
		effectTrigger =skill.getEffectTrigger()[ithEffect];
		effectIDTot++;
		//System.out.printf("%d\n", effectIDTot);
	}

	// get effect ID
	public int getEffectID()
	{
		return effectID;
	}

	// get effect name
	public String getEffectName()
	{
		return effectName;
	}


	private int effectID = 0;  // Effect ID
	private String effectName = "";   // Effect Name
	private int skillID = 0;  // SKill ID
	private int effectTrigger = 0; // Trigger Point

	static int effectIDTot = 0;
}
