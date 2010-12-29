
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextArea;

/**
 * @program MHAG
 * @ extend PrintStream Class for TextArea
 * @version 1.0
 * @author Tifa@mh3
 */
public class TextAreaPrintStream extends PrintStream
{
	private JTextArea textArea;
	
	public TextAreaPrintStream(JTextArea area, OutputStream out)
	{
		super(out);
		textArea = area;
	}

	@Override
	public void println(String string)
	{
		textArea.append(string+"\n");
	}

	@Override
	public void print(String string)
	{
		textArea.append(string);
	}

	public void reset()
	{
		textArea.setText("");
	}

}
