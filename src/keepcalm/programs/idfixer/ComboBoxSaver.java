package keepcalm.programs.idfixer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import keepcalm.programs.idfixer.utils.OSUtils;

public class ComboBoxSaver {
	
	public static List<String> comboBoxOptions = new ArrayList<String>();
	private static File theFile;
	private static boolean enabled = true;
	
	public static void init() throws IOException {
		theFile = new File(OSUtils.getConfigurationFolder(), "/idres_folders.txt");
		theFile.createNewFile();
	}
	
	public static void load() throws IOException {
		if (!enabled)
			return;
		FileReader f = new FileReader(theFile);
		BufferedReader j = new BufferedReader(f);
		String line;
		while ((line = j.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			// stop duplicates
			if (comboBoxOptions.contains(line)) {
				continue;
			}
			comboBoxOptions.add(line);
		}
		j.close();
		f.close();
	}
	
	public static void add(String str) {
		comboBoxOptions.add(str);
	}
	
	public static void save() throws IOException {
		if (!enabled)
			return;
		FileWriter f = new FileWriter(theFile);
		BufferedWriter j = new BufferedWriter(f);
		j.write("# ID resolver previously selected configuration folders");
		j.newLine();
		
		for (String opt : comboBoxOptions) {
			
			Logging.logger.info(opt);
			j.write(opt);
			j.newLine();
		}
		j.close();
		f.close();
	}
	
}
