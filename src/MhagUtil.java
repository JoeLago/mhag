
import java.util.Arrays;

/**
 * @program MHAG
 * @ MhagUtil Class , static shared methods
 * @version 1.0
 * @author Tifa@mh3
 */

public class MhagUtil {

	//extract word from a line
	public static int extractWordPos(String line, int startPos)
	{
		return line.indexOf(":",startPos);
	}

	public static String extractWord(String line, int startPos, int endPos)
	{
		String word;
		if(endPos == -1)
		{
			word = line.substring(startPos);
		}
		else
		{
			word = line.substring(startPos, endPos);
		}
		return word.trim();
	}

	//extract integer numbers from a line, return num of values
	public static int extractInt(String line, int nMax, int[] values)
	{
		Arrays.fill(values, 0);
		String[] numArray = line.split(" ");

		if(numArray[0].equals(""))return 0;

		int num = numArray.length;
		for (int i = 0; i < Math.min(num, nMax); i++)
  			values[i] = Integer.parseInt(numArray[i]);
		return num;
	}

	// log file input error
	public static void logLine(Mhag mhag, String line)
	{
		if(mhag.getLogOpt() != 2)
			mhag.getOutLog().println(line);
	}

}
