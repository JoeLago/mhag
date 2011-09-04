package org.mhag.model;

import java.util.Arrays;

/**
 * @program MHAG
 * @ Charm Class
 * @version 1.0
 * @author Tifa@mh3
 *
 */

public class Charm {

	public Charm()
	{
		skillID = new int[2];
		skillPoint = new int[2];
		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
	}

	public void init()
	{
		charmName = "";
		lowRank = false;
		numSlot = 0;
		numSkill = 0;
		Arrays.fill(skillID, 0);
		Arrays.fill(skillPoint, 0);
	}

	public void setCharmFromLine(String line, MhagData mhagData)
	{
		int startPos = 0;
		int endPos = 0;
		// first skill or slots
		endPos = line.indexOf(",",startPos);
		if(endPos == -1)
		{
			parseSlots(line.substring(startPos));
			if(numSlot != 0)
				numSkill = 0;
			else
			{
				parseSkill(0, line.substring(startPos), mhagData);
				numSkill = 1;
			}
			return;
		}
		else
		{
			parseSkill(0, line.substring(startPos, endPos), mhagData);
		}

		// second skill or slots
		startPos = endPos + 1;
		endPos = line.indexOf(",",startPos);
		if(endPos == -1)
		{
			parseSlots(line.substring(startPos));
			if(numSlot != 0)
				numSkill = 1;
			else
			{
				parseSkill(1, line.substring(startPos), mhagData);
				numSkill = 2;
			}
		}
		else
		{
			parseSkill(1, line.substring(startPos, endPos), mhagData);
			parseSlots(line.substring(endPos+1));
			numSkill = 2;
		}

	}

	public void parseSkill(int skillIndex, String line, MhagData mhagData)
	{
		int gap = line.indexOf(" +",0);
		String name = "";
		if(gap > 0)
		{
			name = line.substring(0, gap).trim();
			skillID[skillIndex] = mhagData.getSkillIDFromName(name);
			skillPoint[skillIndex] = Integer.parseInt(line.substring(gap + 2));
		}
		else
		{
			gap = line.indexOf(" -",0);
			if(gap > 0)
			{
				name = line.substring(0, gap).trim();
				skillID[skillIndex] = mhagData.getSkillIDFromName(name);
				skillPoint[skillIndex] = -Integer.parseInt(line.substring(gap + 2));
			}
		}
	}

	public void parseSlots(String line)
	{
		numSlot = 0;
		for(int i = 0; i < line.length(); i++)
		{
			if(line.substring(i, i+1).equals("O"))
				numSlot++;
		}
	}

	// check if the charm can be found in low rank
	public void setLowRank(MhagData mhagData)
	{
		for(int i = 0; i < numSkill; i++)
		{
			Skill skill = mhagData.getSkill(skillID[i]);
			if(skill.getMaxSkillPoint(true, numSlot) < skillPoint[i])
			{
				lowRank = false;
				return;
			}
		}
		lowRank = true;
	}

	// set charm name
	public void setCharmName(MhagData mhagData)
	{
		StringBuilder name = new StringBuilder("");

		String line = "";

		for (int i = 0; i < numSkill; i++)
		{
			Skill skill = mhagData.getSkill(skillID[i]);
			if(skillPoint[i] > 0)
				line=String.format("%s +%d, ",
					skill.getSkillName(),skillPoint[i]);
			else if(skillPoint[i] < 0)
				line=String.format("%s %d, ",
					skill.getSkillName(),skillPoint[i]);
			else
				continue;
			name.append(line);
		}
		for (int i = 0; i < numSlot; i++)
		{
			name.append("O");
		}
		if(numSlot == 0)
		{
			charmName = name.substring(0, name.length() - 2);
		}
		else
		{
			charmName = name.toString();
		}
	}

	public String getCharmName() {return charmName;}
	public boolean getLowRank() {return lowRank;}
	public int getNumSlot() {return numSlot;}
	public int getNumSkill() {return numSkill;}
	public int getSkillID(int ind) {return skillID[ind];}
	public int getSkillPoint(int ind) {return skillPoint[ind];}

	public void setCharmName(String name) {charmName = name;}
	public void setLowRank(boolean ifLowRank) {lowRank = ifLowRank;}
	public void setNumSlot(int nSlot) {numSlot = nSlot;}
	public void setNumSkill(int nSkill) {numSkill = nSkill;}
	public void setSkillID(int ind, int value) {skillID[ind] = value;}
	public void setSkillPoint(int ind, int value) {skillPoint[ind] = value;}

	private String charmName = ""; //charm name
	private boolean lowRank = false;  //if charm can be obtained in low rank

	private int numSlot = 0; // # of charm slot
	private int numSkill = 0; // number of skills on Charm
	private int[] skillID; // 2 Skill IDs on Charm
	private int[] skillPoint; // 2 Skill Point for Charm Skills
}
