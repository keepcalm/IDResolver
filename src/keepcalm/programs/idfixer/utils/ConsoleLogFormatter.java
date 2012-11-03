package keepcalm.programs.idfixer.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class ConsoleLogFormatter extends Formatter {
	private SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public String format(LogRecord record) {
		
		StringBuilder msg = new StringBuilder();
		msg.append(dateform.format(Long.valueOf(record.getMillis())));
		Level lvl = record.getLevel();
		
		if (lvl == Level.FINEST)
        {
            msg.append(" [FINEST] ");
        }
        else if (lvl == Level.FINER)
        {
            msg.append(" [FINER] ");
        }
        else if (lvl == Level.FINE)
        {
            msg.append(" [FINE] ");
        }
        else if (lvl == Level.INFO)
        {
            msg.append(" [INFO] ");
        }
        else if (lvl == Level.WARNING)
        {
            msg.append(" [WARNING] ");
        }
        else if (lvl == Level.SEVERE)
        {
            msg.append(" [SEVERE] ");
        }
        else if (lvl == Level.SEVERE)
        {
            msg.append(" [" + lvl.getLocalizedName() + "] ");
        }

        /*if (record.getLoggerName() != null)
        {
            msg.append("["+record.getLoggerName()+"] ");
        }
        else
        {
            msg.append("[] ");
        }*/
		Throwable thr = record.getThrown();
		if (thr != null)
        {
            StringWriter thrDump = new StringWriter();
            thr.printStackTrace(new PrintWriter(thrDump));
            msg.append(thrDump.toString());
        }

		msg.append(record.getMessage());
		msg.append("\n");
		return msg.toString();
	}

}
