package keepcalm.programs.idfixer;

import java.io.File;
import java.io.IOException;


public class Main {
	public static File configFolderToUse;
	private static String getArgumentFromArgv(String argvElement) throws GuiException {
		String[] argAndRes = argvElement.split("=",2);
		try {
			return argAndRes[1];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new GuiException("Bad argument: " + argvElement);
		}
	}
	/**
	 * @param args
	 * @throws GuiException 
	 * @throws IOException 
	 * @throws GuiNonFatalException 
	 */
	public static void main(String[] args) throws GuiException, GuiNonFatalException, IOException {
		System.out.println("Wir beginnen!");
		for (String arg : args) {
			
			if (arg.startsWith("--config=")) {
				
				configFolderToUse = new File( getArgumentFromArgv(arg) );
				
				if (!configFolderToUse.exists() || !configFolderToUse.isDirectory()) {
					
					throw new GuiException("The configuration folder needs to exist and needs to be a folder!");
				
				}
			}
		}
		if (configFolderToUse == null) {
			throw new GuiException("Need a configuration folder to use!");
		}
		//Thread theLoggerThread = new Thread(new LogWrapper());
		//theLoggerThread.setDaemon(true);
		//theLoggerThread.start();
		//Logging.init("IDResolver");
		beginConfigChecking(configFolderToUse);
		
		
		
		
		
	}
	@SuppressWarnings("unused")
	public static void beginConfigChecking(File cfgDir) throws GuiNonFatalException, IOException {
		configFolderToUse = cfgDir;
		IDTracker.init();
		GuiProgressUpdater gui = new GuiProgressUpdater();
		
		GuiProgressUpdater.window.getContentPane().invalidate();
		FolderDescender fr = new FolderDescender(configFolderToUse);
		fr.run();
		
		IDTracker.dumpIDs();
	}

}
