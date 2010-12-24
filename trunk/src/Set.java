/**
 * @program MHAG
 * @ Set Class , a set & stats
 * @version 1.0
 * @author Tifa@mh3
 */

import java.util.Arrays;

public class Set {

	public Set()
	{
		armorID = new int[5];
		jewelID = new int[7][3];
		charmSkillID = new int[2];
		inUse = new boolean[7][4];

		Arrays.fill(armorID, 0);
		Arrays.fill(jewelID, 0);
		Arrays.fill(charmSkillID, 0);
		Arrays.fill(inUse, false);
	}

	// get/set inputs

	// get set name
	public String getSetName()
	{
		return setName;
	}

	// set set name
	public void setSetName(String aSetName)
	{
		 setName = aSetName;
	}

	// get low/high rank
	public boolean getLowRank()
	{
		return lowRank;
	}

	// set low/high rank
	public void setLowRank(boolean aLowRank)
	{
		lowRank = aLowRank;
	}

	// get blader/gunner info
	public boolean getBlader()
	{
		return blader;
	}

	// set blader/gunner info
	public void sedBlader(boolean aBlader)
	{
		blader = aBlader;
	}

	// get armor id
	public int[] getArmorID()
	{
		return armorID;
	}

	// set armor id
	public void setArmorID(int[] aArmorID)
	{
		armorID = aArmorID;
	}

	// get jewel id
	public int[][] getJewelID()
	{
		return jewelID;
	}

	// set jewel id
	public void setJewelID(int[][] aJewelID)
	{
		jewelID = aJewelID;
	}

	// get charm id
	public int getCharmID()
	{
		return charmID;
	}

	// set charm id
	public void setCharmID(int aCharmID)
	{
		charmID = aCharmID;
	}

	// get charm Skill id
	public int[] getCharmSkillID()
	{
		return charmSkillID;
	}

	// set charm skill id
	public void setCharmSKillID(int[] aCharmSkillID)
	{
		charmSkillID = aCharmSkillID;
	}

	// get in use set pieces
	public boolean getInUse(int part, int partDetails)
	{
		return inUse[part][partDetails];
	}

	// set in use set pieces
	public void setInUse(int part, int partDetails)
	{
		inUse[part][partDetails] = true;
	}

	// set descard set pieces
	public void setNotInUse(int part, int partDetails)
	{
		inUse[part][partDetails] = false;
	}

	// get outputs

	// get set defense
	public int getDefense()
	{
		return defense;
	}

	// get set resist
	public int[] getResist()
	{
		return resist;
	}

	// get num of Skills of the set
	public int getNumSkill()
	{
		return numSkill;
	}

	// get Skill IDs of the set
	public int[] getSkillID()
	{
		return skillID;
	}

	// get Skill Points of the set
	public int[] getSkillPoint()
	{
		return skillPoint;
	}

	// get num of effects (activated skills) of the set
	public int getNumEffect()
	{
		return numEffect;
	}

	// get effects ID(activated skills) of the set
	public int[] getEffectID()
	{
		return effectID;
	}

	// get effect skill index (map effect list to skillID of the set)
	public int[] getEffectSkillIndex()
	{
		return effectSkillIndex;
	}

	// get number of troso up of the set
	public int getNumTorso()
	{
		return numTorso;
	}

	// get set rate
	public int getRate()
	{
		return rate;
	}

	//Inputs
	private String setName = "";  // User-defined Set Name
	private boolean lowRank = false; // lr T / hr F
	private boolean blader = true; // Blader true, or Gunner false
	private int[] armorID;  // Armor ID for 5 Pieces
	private int[][] jewelID;  // Jewel IDs for 5 Pieces, Weapon & Charm
	private int charmID = 0;  // Charm ID
	private int[] charmSkillID; // 2 Skill IDs on Charm
	private boolean[][] inUse; //  armor/jewel in use.
	// in Use: [0][0,4] armor; [0][6] charm; [1,3][0,6] jewel;
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
