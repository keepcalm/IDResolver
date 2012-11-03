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
		this.folder = folder;
		folders = Arrays.asList(folder.listFiles(new FolderMatcher()));
		Thread.yield();
		files = Arrays.asList(folder.listFiles(new ConfigFileFilter(topPriority)));
		Thread.yield();
		isChild = false;
	}
	private FolderDescender(File folder, boolean child) {
		this(folder);
		this.isChild = child;
	}
	public void run() throws GuiNonFatalException, IOException {
		Logging.logger.info("Inspect folder: " + folder.getAbsolutePath());
		// necessary to stop concurrentModificationExceptions
		List<String> theAllNewTP = Arrays.asList(topPriority.toArray(new String[0]));
		Iterator<String> tp = theAllNewTP.iterator();
		// we don't run this if we're a child
		if (!this.isChild) {
			while (tp.hasNext()) {
				Thread.yield();
				String nxt = tp.next();

				File cfgFile = new File(Main.configFolderToUse, nxt);
				Logging.logger.info(cfgFile.getAbsolutePath());
				if (!cfgFile.exists()) {
					Logging.logger.info("Skipping non-existant file or folder: " + cfgFile.getName());
				}
				else if (cfgFile.isDirectory()) {
					FolderDescender f = new FolderDescender(cfgFile, true);
					f.run();
					f = null;
					Logging.logger.info("Back!");
					if (tp.hasNext())
						continue;
					else
						break;

				}
				else {
					ForgeFileReader.examineFile(cfgFile);
				}
				Logging.logger.info("Hi!");
			}
		}
		Logging.logger.info("CONTINUING..." + isChild);
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
		Logging.logger.info("Complete!");
		if (!isChild) {
			Logging.logger.info("Finished!");
			GuiProgressUpdater.updateActivity("Done!");
			GuiProgressUpdater.progBar.setVisible(false);
			GuiProgressUpdater.button.setText("Quit!");
			GuiProgressUpdater.button.addMouseListener(new QuitListener());

		}
		else {
			// allow continuation
			return;
		}

	}
}
