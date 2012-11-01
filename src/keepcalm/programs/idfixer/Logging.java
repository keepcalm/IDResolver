package keepcalm.programs.idfixer;

import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import keepcalm.programs.idfixer.utils.FMLLogFormatter;
import keepcalm.programs.idfixer.utils.LoggingOutStream;

public class Logging {
	private static class ConsoleLogWrapper extends Handler
    {
        @Override
        public void publish(LogRecord record)
        {
            boolean currInt = Thread.interrupted();
            try
            {
                ConsoleLogThread.recordQueue.put(record);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (currInt)
            {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void flush()
        {

        }

        @Override
        public void close() throws SecurityException
        {
        }

    }
	private static class ConsoleLogThread implements Runnable
    {
        static ConsoleHandler wrappedHandler = new ConsoleHandler();
        static LinkedBlockingQueue<LogRecord> recordQueue = new LinkedBlockingQueue<LogRecord>();
        @Override
        public void run()
        {
            do
            {
                LogRecord lr;
                try
                {
                    lr = recordQueue.take();
                    wrappedHandler.publish(lr);
                }
                catch (InterruptedException e)
                {
                    //e.printStackTrace(errCache);
                    Thread.interrupted();
                    // Stupid
                }
            }
            while (true);
        }
    }
	
	private static Logger logger = null;
	public static final String logFileNamePattern = "IDResolver-%g.log";

	public static void init(String name) {
		logger = Logger.getLogger(name);
		logger.addHandler(new ConsoleLogWrapper());
		//logger.setLevel(Level.INFO);
		FMLLogFormatter formatter = new FMLLogFormatter();
		File logPath = new File(Main.configFolderToUse.getParentFile().getAbsolutePath(), logFileNamePattern);
        FileHandler fileHandler;
        
        Logger stdout = Logger.getLogger("STDOUT");
        Logger stderr = Logger.getLogger("STDERR");
        stdout.setParent(logger);
        stderr.setParent(logger);
        
		try {
			fileHandler = new FileHandler(logPath.getPath(), 0, 3);
			fileHandler.setFormatter(formatter);
	        fileHandler.setLevel(Level.ALL);
	        logger.addHandler(fileHandler);
		} catch (Exception ex) {
			;
		}
        
		System.setOut(new PrintStream(new LoggingOutStream(stdout)));
		System.setErr(new PrintStream(new LoggingOutStream(stderr)));
		//logger.info("Logging initialized!");
		
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	
}
