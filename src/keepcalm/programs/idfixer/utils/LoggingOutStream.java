package keepcalm.programs.idfixer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingOutStream extends ByteArrayOutputStream
{
	private Logger log;
	private StringBuilder currentMessage;

	public LoggingOutStream(Logger log)
	{
		this.log = log;
		this.currentMessage = new StringBuilder();
	}

	@Override
	public void flush() throws IOException
	{
		String record;
		super.flush();
		record = this.toString();
		super.reset();

		currentMessage.append(record.replace(FMLLogFormatter.LINE_SEPARATOR, "\n"));
		if (currentMessage.lastIndexOf("\n")>=0)
		{
			// Are we longer than just the line separator?
			if (currentMessage.length()>1)
			{
				// Trim the line separator
				currentMessage.setLength(currentMessage.length()-1);
				log.log(Level.INFO, currentMessage.toString());
			}
			currentMessage.setLength(0);
		}
	}
}
