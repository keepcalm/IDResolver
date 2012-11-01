package keepcalm.programs.idfixer.utils;

import java.io.File;
import java.io.FilenameFilter;

public class ConfigFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File arg0, String arg1) {
		// NEI and InvTweaks configs are bugged - don't use forge's configuration
		if (arg1.endsWith(".cfg") && !arg1.toLowerCase().contains("nei") && !arg1.toLowerCase().contains("invtweaks")) {
			return true;
		}
		return false;
	}

}
