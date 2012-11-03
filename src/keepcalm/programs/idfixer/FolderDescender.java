package keepcalm.programs.idfixer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import keepcalm.programs.idfixer.utils.ConfigFileFilter;
/**
 * 
 * The overarching descender to use - runs everything
 * 
 * @author keepcalm
 *
 */
public class FolderDescender {
	public static class FolderMatcher implements FilenameFilter {
		
		@Override
		public boolean accept(File arg0, String arg1) {
			File dir = new File(arg0, arg1);
			if (topPriority.contains(arg1)) {
				return false;
			}
			return dir.isDirectory();
		}

	}

	private File folder;
	public static final List<String> topPriority = new ArrayList<String>();
	List<File> folders;
	List<File> files;
	private boolean isChild;
	
	public FolderDescender(File folder) {
		// hard-coded support
		topPriority.add("extrabiomes");
		this.folder = folder;
		folders = Arrays.asList(folder.listFiles(new FolderMatcher()));
		files = Arrays.asList(folder.listFiles(new ConfigFileFilter(topPriority)));
		isChild = false;
	}
	private FolderDescender(File folder, boolean child) {
		this(folder);
		this.isChild = child;
	}
	public void run() throws GuiNonFatalException, IOException {
		System.out.println("Inspect folder: " + folder.getAbsolutePath());
		Iterator<String> tp = topPriority.iterator();
		// we don't run this if we're a child
		while (tp.hasNext() && isChild == false) {
			File cfgFile = new File(Main.configFolderToUse, tp.next());
			if (!cfgFile.exists()) {
				System.out.println("Skipping non-existant file or folder: " + cfgFile.getName());
				continue;
			}
			if (cfgFile.isDirectory()) {
				FolderDescender f = new FolderDescender(cfgFile, true);
				f.run();
			}
			else {
				ForgeFileReader.examineFile(cfgFile);
			}
		}
		System.out.println("CONTINUING...");
		Iterator<File> it = folders.iterator();
		while (it.hasNext()) {
			File t = it.next();
			FolderDescender f = new FolderDescender(t, true);
			f.run();
		}
		it = files.iterator();
		while (it.hasNext()) {
			File t = it.next();
			ForgeFileReader.examineFile(t);
		}

		if (!isChild) {
			System.out.println("Finished!");
			GuiProgressUpdater.updateActivity("Done!");
			GuiProgressUpdater.progBar.setVisible(false);
			GuiProgressUpdater.button.setText("Quit!");
			GuiProgressUpdater.button.addMouseListener(new QuitListener());
			while (true)
				;
		}
		else {
			// allow continuation
			return;
		}

	}
}
