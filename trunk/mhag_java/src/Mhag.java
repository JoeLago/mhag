
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Mhag
{
	/* @program MHAG
	 * @version 0.5
	 * @author Tifa@mh3
	 */

	// MHAG command-line help
	public static void showHelp()
	{
		System.out.println("Usage: javac Mhag <cal/bat/gen/ref> in " +
			"<input.dat> out <result.dat> log <''/log/off>" +
			"format <text/html>");
		System.exit(0);
	}

	// read MHAG arguments
	public void procArg(String[] args){


		int i = 0;
		while ((args.length != 0) &&(i < args.length))
		{
			if(args[i].equals("method"))
			{
				i++;
				if(i >= args.length) showHelp();
				else if(args[i].equals("cal"))
				{
					method = 0;
				}
				else if(args[i].equals("bat"))
				{
					method = 1;
				}
				else if(args[i].equals("gen"))
				{
					method = 2;
				}
				else if(args[i].equals("ref"))
				{
					method = 3;
				}
				else
				{
					showHelp();
				}
			}
			else if(args[i].equals("in"))
			{
				i++;
				if(i >= args.length) showHelp();
				else fileIn = args[i];
			}
			else if(args[i].equals("out"))
			{
				i++;
				if(i >= args.length) showHelp();
				else fileOut = args[i];
			}
			else if(args[i].equals("log"))
			{
				i++;
				if(i >= args.length) showHelp();
				if(args[i].equals("off"))
				{
					logOpt = 2;
				}
				else
				{
					logOpt = 1;
					fileLog = args[i];
				}
			}
			else if(args[i].equals("format"))
			{
				i++;
				if(i >= args.length) showHelp();
				if(args[i].equals("html"))
				{
					outFormat = 1;
				}
				else
				{
					outFormat = 0;
				}
			}
			else if(args[i].startsWith("#"))
			{}
			else    // error
			{
				showHelp();
			}
			i++;
		}
		if( outFormat == 0)
		{
			fileOut = fileOut +".txt";
		}
		else
		{
			fileOut = fileOut + ".html";
		}

	}
	
	// set up MHAG log file
	public void prepareLogFile() throws FileNotFoundException
	{
		if(logOpt == 0)
		{
			outLog = System.out;
		}
		else if (logOpt == 1)
		{
			outLog = new PrintStream(fileLog);
		}
	}

	// output MHAG arguments
	public void showMhagArgs()
	{
		if(logOpt == 2)return;
		outLog.printf("Auguments: \n");
		outLog.printf("Method flag: %d\n",method);
		outLog.printf("FileIn: %s\n",fileIn);
		outLog.printf("FileOut: %s\n",fileOut);
		outLog.printf("FileLog: %s\n",fileLog);
		outLog.printf("LogOPT flag: %s\n",logOpt);
		outLog.printf("OutFormat flag: %d\n",outFormat);
		outLog.println("");
	}

	//output MHAG welcome info
	public void showMhagInfo()
	{
		if(logOpt == 2)return;
		String head = "";
		for (int i = 0; i < 80; i++)
		{
			head=head+"=";
		}
		outLog.println(head);
		outLog.println("MHAG: ver 0.5");
		outLog.println("Monster Hunter Armor Generator");
		outLog.println("By Tifa@mh3, Dec 2010");
		outLog.println("http://www.youtube.com/mh3journey");
		outLog.println(head);
		outLog.println("");
	}

	//init process
	public static void init(Mhag mhag, String[] args)
		throws FileNotFoundException
	{
		mhag.procArg(args);
		mhag.prepareLogFile();

		mhag.showMhagInfo();
		mhag.showMhagArgs();

		MhagData mhagData = new MhagData();
		mhagData.readFile(mhag);
	}

	// get log option
	public int getLogOpt()
	{
		return logOpt;
	}

	// get out object for log outputs
	public PrintStream getOutLog()
	{
		return outLog;
	}

	public String getFileOut()
	{
		return fileOut;
	}

	public static void main(String[] args) throws FileNotFoundException
	{

		Mhag mhag = new Mhag();

		mhag.init(mhag,args);

//		String dir = System.getProperty("user.dir");
//		System.out.println(dir);

	}

	private int method = 0;  // MHAG method 0: caluclator; 1: batch;
				 // 2: generator; 3: reference;
	private String fileIn = "input.dat";  // MHAG default input file
	private String fileOut = "result";  // MHAG default output file
	private String fileLog = "log";  // MHAG default log file
	private int logOpt = 0 ;  //log information option "": screen
			         // "log": file 2: "off" : off
	private int outFormat = 0; // Output Format 0: text; 1: html
	private PrintStream outLog = null;  // object for writing log

	// set to public for io access
}
