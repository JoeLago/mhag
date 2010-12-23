
import java.util.Arrays;

public class Armor {

	public void Armor()
	{
		defense = new int[2];
		resist = new int[5];
		skillID = new int[5];
		skillPoint = new int[5];

		Arrays.fill(defense, 0);
		Arrays.fill(resist, 0);
		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
	}

	// get armor name
	public String getArmorName()
	{
		return armorName;
	}

	private int armorID = 0;
	private String armorName = "";
	private String bladerOrGunner = "A";
	private boolean lowRank = false;
	private int bodyPart = 0, numSlot = 0, numSkill = 0;
	private int[] defense, resist, skillID, skillPoint;

	static int[] armorIDTot;
	static
	{
		armorIDTot = new int[5];
		for (int i : armorIDTot)
			armorIDTot[i] = 0;
	}
}
