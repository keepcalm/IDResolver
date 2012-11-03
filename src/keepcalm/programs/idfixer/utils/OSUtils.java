package keepcalm.programs.idfixer.utils;

import java.io.File;

public class OSUtils {
	public static File getConfigurationFolder() {
		File theFile;
		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			theFile = new File(System.getenv("APPDATA").replace("\\", "/"), "/idresolver");
			
		}
		else {
			theFile = new File(System.getenv("HOME"), "/.idresolver");
		}
		System.out.println(theFile.getAbsolutePath());
		if (theFile.getAbsolutePath() == "/.idresolver") {
			// desperate
			theFile = new File(".idresolver");
		}
		if (!theFile.exists()) {
			try {
				theFile.mkdirs();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return theFile;
	}
}
