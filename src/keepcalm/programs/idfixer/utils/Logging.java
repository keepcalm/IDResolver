package keepcalm.programs.idfixer.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Logging {
	public static Logger logger;
	
	
	public static void init() {
		logger = Logger.getLogger("IDResolver");
		// maybe stop STDOUT output?
		logger.setUseParentHandlers(false);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new ConsoleLogFormatter());
		logger.addHandler(ch);
		FMLLogFormatter j = new FMLLogFormatter();
		try {
			FileHandler fh = new FileHandler(new File(OSUtils.getConfigurationFolder(), "IDResolver-%g.log").getAbsolutePath());
			fh.setFormatter(j);
			logger.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
