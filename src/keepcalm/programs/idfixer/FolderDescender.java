package keepcalm.programs.idfixer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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
	private static class FolderMatcher implements FilenameFilter {

		@Override
		public boolean accept(File arg0, String arg1) {
			File dir = new File(arg0, arg1);
			return dir.isDirectory();
		}

	}

	private File folder;
	List<File> folders;
	List<File> files;
	private boolean isChild;
	public FolderDescender(File folder) {
		this.folder = folder;
		folders = Arrays.asList(folder.listFiles(new FolderMatcher()));
		files = Arrays.asList(folder.listFiles(new ConfigFileFilter()));
		isChild = false;
	}
	private FolderDescender(File folder, boolean child) {
		this(folder);
		this.isChild = child;
	}
	public void run() throws GuiNonFatalException, IOException {
		System.out.println("Inspect folder: " + folder.getAbsolutePath());
		int indexOfEB = folders.indexOf(new File(Main.configFolderToUse, "/extrabiomes"));
		if (indexOfEB > -1 && !isChild) {
			System.out.println("Detected ExtraBiomes!");
			FolderDescender f = new FolderDescender(folders.get(indexOfEB), true);
			f.run();
			try {
			folders.remove(indexOfEB);
			}
			catch(Exception e) {
				//e.printStackTrace();
				;
			}
		}
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

	}
}
