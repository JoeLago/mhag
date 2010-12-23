
import java.util.Arrays;
import java.lang.Math;

public class Armor {

	public Armor()
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

	// calculate max possible armor pieces for one part
	public int getArmorMax()
	{
		int num = 0;
		for(int i = 0; i < 5 ; i++)
		{
			if(armorIDTot[i] > num)
			{
				num = armorIDTot[i];
			}
		}
		return num;
	}

	private int armorID = 0;  // Armor ID
	private String armorName = "";   // Armor Piece Name
	private String bladerOrGunner = "";  // Blader/Gunner/All
	private boolean lowRank = false; //lr Y / hr N
	private int bodyPart = 0;  // Head/Chest/Arms/Waist/Legs
	private int numSlot = 0;   // # of slots: 0 - 3
	private int[] defense;  // 0: lr, 1: hr defense
	private int[] resist;  // 0-4: Fire/Water/Ice/Thunder/Dragon
	private int numSkill = 0;  // # of Skill types (max 5 for all mh3 armor)
	private int[] skillID;  // Skill ID
	private int[] skillPoint; // Skill Points

	static int[] armorIDTot;
	static
	{
		armorIDTot = new int[5];
		for (int i : armorIDTot)
			armorIDTot[i] = 0;
	}
}
