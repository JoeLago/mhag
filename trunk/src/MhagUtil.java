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

}
