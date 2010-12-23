
import java.util.Arrays;

public class Set {

	public Set()
	{
		armorID = new int[5];
		jewelID = new int[7][3];
		charmSkillID = new int[2];

		Arrays.fill(armorID, 0);
		Arrays.fill(jewelID, 0);
		Arrays.fill(charmSkillID, 0);
	}

	// get armor name
	public String getSetName()
	{
		return setName;
	}

	//Inputs
	private String setName = "";  // User-defined Set Name
	private boolean lowRank = false; // lr T / hr F
	private String bladerOrGunner = "B"; // Blader B, or Gunner G
	private int[] armorID;  // Armor ID for 5 Pieces
	private int[][] jewelID;  // Jewel IDs for 5 Pieces, Weapon & Charm
	private int charmID = 0;  // Charm ID
	private int[] charmSkillID; // 2 Skill IDs on Charm
	//Outputs
	private int defense = 0;  // Total Defense
	private int[] resist; // Total Resist
	private int numSkill = 0; // # Skills Involved
	private int[] skillID; // All Skill ID
	private int[] skillPoint; // All Skill ID
	private int numEffect = 0; // # of Activated Skills
	private int[] effectID; // all Effect IDs (max 8)
	private int[] effectSkillIndex; // Skill Indeces in the List skillID
	private int  numTorso = 0; // # of Torso Up
	private int rate = 0; // # Reserved for sets Evaluation
}
