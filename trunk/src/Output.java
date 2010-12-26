
import java.io.PrintStream;

/**
 * @program MHAG
 * @ Outout Class
 * @version 1.0
 * @author Tifa@mh3
 */
public class Output {

	// output head title

	public static void head(int outForm, PrintStream outSave, String setName,
		boolean lowRank, boolean blade)
	{
		if(outForm == 0)
			headTEXT(outSave, setName, lowRank, blade);
		else
			headHTML(outSave, setName, lowRank, blade);
	}


	public static void headTEXT(PrintStream outSave, String setName,
		boolean lowRank, boolean blade)
	{
		String title = new String();
		if(lowRank)
			title = "Low Rank";
		else
			title = "High Rank";

		String title2 = new String();
		if(blade)
			title2 = "Blademaster";
		else
			title2 = "Gunner";

		outSave.println(splitter1);
		outSave.printf("%-40s%-12s%-12s\n", setName, title, title2);
		outSave.println(splitter2);

	}

	public static void headHTML(PrintStream outSave, String setName,
		boolean lowRank, boolean blade)
	{

	}

	// convert # of slots to a word
	public static String slotWord(int outForm, int numSlot)
	{
		if(outForm == 0)
			return slotWordTEXT(numSlot);
		else
			return slotWordHTML(numSlot);
	}

	public static String slotWordTEXT(int numSlot)
	{
		if(numSlot == 1)
		{
			return "O--";
		}
		else if(numSlot == 2)
		{
			return "OO-";
		}
		else if(numSlot == 3)
		{
			return "OOO";
		}
		else
		{
			return "---";
		}
	}

	public static String slotWordHTML(int numSlot)
	{
		if(numSlot == 1)
		{
			return "O";
		}
		else if(numSlot == 2)
		{
			return "OO";
		}
		else if(numSlot == 3)
		{
			return "OOO";
		}
		else
		{
			return "";
		}

	}

	// output weapon

	public static void weapon(int outForm, PrintStream outSave,
		String slots, String[] jewels)
	{
		if(outForm == 0)
			weaponTEXT(outSave, slots, jewels);
		else
			weaponHTML(outSave, slots, jewels);
	}

	public static void weaponTEXT(PrintStream outSave, String slots,
		String[] jewels)
	{
		outSave.printf("%-40s%3s   %s %s %s\n","Weapon",slots,
			jewels[0],jewels[1],jewels[2]);
	}

	public static void weaponHTML(PrintStream outSave, String slots,
		String[] jewels)
	{

	}

	// output armor

	public static void armor(int outForm, PrintStream outSave, int bodyPart,
		String title, String slots, String[] jewels)
	{
		if(outForm == 0)
			armorTEXT(outSave, bodyPart, title, slots, jewels);
		else
			armorHTML(outSave, bodyPart, title, slots, jewels);
	}

	public static void armorTEXT(PrintStream outSave, int bodyPart,
		String title, String slots, String[] jewels)
	{
		outSave.printf("%-40s%3s   %s %s %s\n",title,slots,
			jewels[0],jewels[1],jewels[2]);
	}

	public static void armorHTML(PrintStream outSave, int bodyPart,
		String title,String slots, String[] jewels)
	{

	}

	// output charm

	public static void charm(int outForm, PrintStream outSave,
		String title, String slots, String[] jewels)
	{
		if(outForm == 0)
			charmTEXT(outSave, title, slots, jewels);
		else
			charmHTML(outSave, title, slots, jewels);
	}

	public static void charmTEXT(PrintStream outSave,
		String title, String slots, String[] jewels)
	{
		outSave.printf("%-40s%3s   %s %s %s\n",title,slots,
			jewels[0],jewels[1],jewels[2]);
		outSave.println(splitter2);
	}

	public static void charmHTML(PrintStream outSave,
		String title,String slots, String[] jewels)
	{

	}

	// output 2nd head line
	public static void head2nd(int outForm, PrintStream outSave)
	{
		if(outForm == 0)
			head2ndTEXT(outSave);
		else
			head2ndHTML(outSave);
	}

	public static void head2ndTEXT(PrintStream outSave)
	{
		outSave.printf("%20s%3s %3s %3s %3s %3s %3s %3s %3s\n",
			" ","WEP","HEA","CHE","ARM","WAI","LEG","CHA","TOT");
	}

	public static void head2ndHTML(PrintStream outSave)
	{

	}

	// output defense

	public static void defense(int outForm, PrintStream outSave,
		String title, int[] values, int def, String bonusTitle)
	{
		if(outForm == 0)
			defenseTEXT(outSave, title, values, def, bonusTitle);
		else
			defenseHTML(outSave, title, values, def, bonusTitle);
	}

	public static void defenseTEXT(PrintStream outSave, String title,
		int[] values, int def, String bonusTitle)
	{
		outSave.printf("%-20s%3s %3d %3d %3d %3d %3d %3s %3d %s\n",
			title,nan,values[0],values[1],values[2],values[3],values[4],
			nan,def,bonusTitle);
	}

	public static void defenseHTML(PrintStream outSave, String title,
		int[] value, int def, String bonusTitle)
	{

	}

	// output resist

	public static void resist(int outForm, PrintStream outSave, int resistInd,
		String title, int[] values, int res, String bonusTitle)
	{
		if(outForm == 0)
			resistTEXT(outSave, resistInd, title, values, res, bonusTitle);
		else
			resistHTML(outSave, resistInd, title, values, res, bonusTitle);
	}

	public static void resistTEXT(PrintStream outSave, int resistInd, String title,
		int[] values, int res, String bonusTitle)
	{
		outSave.printf("%-20s%3s %3d %3d %3d %3d %3d %3s %3d %s\n",
			title,nan,values[0],values[1],values[2],values[3],values[4],
			nan,res,bonusTitle);
		if(resistInd == 4)
			outSave.println(splitter2);
	}

	public static void resistHTML(PrintStream outSave, int resistInd, String title,
		int[] value, int res, String bonusTitle)
	{

	}

	// output torso up skill
	public static void torso(int outForm, PrintStream outSave,
		String[] torsoupList)
	{
		if(outForm == 0)
			torsoTEXT(outSave, torsoupList);
		else
			torsoHTML(outSave, torsoupList);
	}

	public static void torsoTEXT(PrintStream outSave, String[] list)
	{
		outSave.printf("%-20s%3s %3s %3s %3s %3s %3s %3s %3s\n",
			"Skills: Torso Up",list[0],list[1],list[2],list[3],
			list[4],list[5],list[6],list[7]);
	}

	public static void torsoHTML(PrintStream outSave, String[] torsoupList)
	{

	}

	// output skill

	public static void skill(int outForm, PrintStream outSave, int index,
		String title, int[] values, String effectName)
	{
		if(outForm == 0)
			skillTEXT(outSave, index, title, values, effectName);
		else
			skillHTML(outSave, index, title, values, effectName);
	}

	public static void skillTEXT(PrintStream outSave, int index, String title,
		int[] values, String effectName)
	{
		String[] str = new String[8];
		for (int i =0; i < 8; i++)
		{
			if(values[i] == 0)
				str[i] =  "---";
			else
				str[i] = String.valueOf(values[i]);
		}

		outSave.printf("%-20s%3s %3s %3s %3s %3s %3s %3s %3s %s\n",
			title,str[0],str[1],str[2],str[3],str[4],str[5],
			str[6],str[7],effectName);
	}

	public static void skillHTML(PrintStream outSave, int index, String title,
		int[] values, String effectName)
	{

	}

	// output end line
	public static void end(int outForm, PrintStream outSave)
	{
		if(outForm == 0)
			endTEXT(outSave);
		else
			endHTML(outSave);
	}

	public static void endTEXT(PrintStream outSave)
	{
		outSave.println(splitter1);
	}

	public static void endHTML(PrintStream outSave)
	{

	}

	// output header for batch output
	public static void batchHead(int outForm, PrintStream outSave, int num)
	{
		if(outForm == 0)
			batchHeadTEXT(outSave, num);
		else
			batchHeadHTML(outSave, num);
	}

	public static void batchHeadTEXT(PrintStream outSave, int num)
	{
		outSave.printf("Set : %d\n", num);
	}

	public static void batchHeadHTML(PrintStream outSave, int num)
	{

	}


	public static String splitter1 = "========================================" +
		"========================================";
	public static String splitter2 = "----------------------------------------" +
		"----------------------------------------";
	public static String nan = "---";
}
