package keepcalm.programs.idfixer.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class ConfigFileFilter implements FilenameFilter {
	private List<String> fileNamesToIgnore = new ArrayList<String>();
	
	public ConfigFileFilter(List<String> filesToExclude) {
		fileNamesToIgnore = filesToExclude;
	}

	public ConfigFileFilter() {}

	@Override
	public boolean accept(File arg0, String arg1) {
		if (arg1.endsWith(".cfg") && !arg1.toLowerCase().contains("nei") && !arg1.toLowerCase().contains("invtweaks") && !fileNamesToIgnore.contains(arg1)) {
			return true;
		}
		return false;
	}

}
