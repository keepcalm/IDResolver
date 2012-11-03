package keepcalm.programs.idfixer;

import java.io.File;

import keepcalm.programs.idfixer.utils.OSUtils;


/**
 * Wrapper around configuration
 * @author keepcalm
 *
 */
public class IDConfig extends Configuration {

	public IDConfig(File targetFile) {
		super(targetFile);
		load();
	}
	public IDConfig() {
		this(new File(OSUtils.getConfigurationFolder(), "IDResolver.cfg"));
	}
	
	
	
}
