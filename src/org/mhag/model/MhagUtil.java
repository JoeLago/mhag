package org.mhag.model;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

/**
 * @program MHAG
 * @ MhagUtil Class , static shared methods
 * @version 1.1
 * @author Tifa@mh3
 *
 * add static method to enumerate indeces
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

		if(numArray[0].trim().equals(""))return 0;

		int num = 0;
		for (int i = 0; i < numArray.length; i++)
		{
			if(numArray[i].trim().equals(""))continue;
  			values[num] = Integer.valueOf(numArray[i].trim());
			num++;

			if(num == nMax) return nMax;
		}
		return num;
	}

	// log file input error
	public static void logLine(Mhag mhag, String line)
	{
		if(mhag.getLogOpt() != 2)
			mhag.getOutLog().println(line);
	}

	// simple bubble sort, return sorted array by index;
	public static int[] sortIndex(int length, int[] values)
	{
		int[] index = new int[length];
		for(int i = 0; i < length; i++)
		{
			index[i] = i;
		}

		int jmax = length -1;
		for (int i = 0; i < length -1; i++)
		{
			boolean change = false;
			for (int j = 0; j < jmax; j++)
			{
				if(values[index[j]] < values[index[j+1]])
				{
					change = true;
					int temp = index[j];
					index[j] = index[j+1];
					index[j+1] = temp;
				}
			}
			if(!change) break;
		}
		return index;
	}

	public static int[] sortIndex(int length, String[] values)
	{
		int[] index = new int[length];
		for(int i = 0; i < length; i++)
		{
			index[i] = i;
		}

		int jmax = length -1;
		for (int i = 0; i < length -1; i++)
		{
			boolean change = false;
			for (int j = 0; j < jmax; j++)
			{
				if(values[index[j]].compareTo(values[index[j+1]]) < 0)
				{
					change = true;
					int temp = index[j];
					index[j] = index[j+1];
					index[j+1] = temp;
				}
			}
			if(!change) break;
		}
		return index;
	}

	static PrintStream streamAppendFile(String filename) throws FileNotFoundException
	{
	    return new PrintStream(new BufferedOutputStream(
		    new FileOutputStream(new File(filename),true)));
	}

	// get index array from total index
	static int[] getIndexArray(int indexTot, int[] dimension )
	{
		int len = dimension.length;
		int[] indices = new int[len];
		Arrays.fill(indices, 0);

		for(int i = len -1; i > -1; i--)
		{
			indices[i] = indexTot%dimension[i];
			indexTot = indexTot / dimension[i];
		}
		return indices;
	}

	// add auto completion function to jComboBox ; add look and feel
	public static void setupAutoComplete(JComboBox comboBox)
	{
		comboBox.setEditable(true);
		JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
		editor.setDocument(new AutoComplete(comboBox));
	}

	public static void setupLAF()
	{
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("Label.background", Color.WHITE);
		UIManager.put("RadioButton.background", Color.WHITE);
		UIManager.put("CheckBox.background", Color.WHITE);
		UIManager.put("ProgressBar.background", Color.WHITE);
		UIManager.put("TabbedPane.background", Color.WHITE);
		UIManager.put("ScrollPane.background", Color.WHITE);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		UIManager.put("ScrollBarUI", "org.mhag.model.MyMetalScrollBarUI");
	}

}
