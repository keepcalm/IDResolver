package keepcalm.programs.idfixer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import keepcalm.programs.idfixer.utils.ConfigFileFilter;
import keepcalm.programs.idfixer.utils.MathsHelper;

public class ForgeFileReader implements IConfigFileExaminer {
	@SuppressWarnings("unused")
	private File folder;
	private List<File> configFiles;
	private Iterator<File> fileIt;
	
	public ForgeFileReader(File folder) {
		this.folder = folder;
		this.configFiles = Arrays.asList(folder.listFiles(new ConfigFileFilter()));
		this.fileIt = configFiles.iterator();
		
	}
	
	public void startReading() {
		while (true)
			try {
				this.examineNextFile();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public int getTotalItems() {
		return configFiles.size();
	}

	@Override
	public List<File> getFilesToExamine() {
		return configFiles;
	}

	@Override
	public void examineNextFile() throws GuiNonFatalException, IOException {
		if (!fileIt.hasNext()) {
			System.out.println("Finished!");
			GuiProgressUpdater.updateActivity("Done!");
			GuiProgressUpdater.progBar.setVisible(false);
			GuiProgressUpdater.button.setText("Quit!");
			GuiProgressUpdater.button.addMouseListener(new QuitListener());
			while (true)
				;
		}
		examineFile(fileIt.next());
	}
	
	public static void examineFile(File theFile) throws GuiNonFatalException, IOException {
		
		System.out.println("Examining file: " + theFile.getPath());
		GuiProgressUpdater.changeFile(theFile.getName());
		GuiProgressUpdater.updateActivity("Loading file...");
		boolean doBlocks = true;
		boolean doItems = true;
		Configuration cfg = new Configuration(theFile.getAbsoluteFile());
		cfg.load();
		Map<String,Property> blocks = cfg.categories.get("block");
		Map<String,Property> items = cfg.categories.get("item");
		int blocksize;
		int itemsize;
		if (blocks == null || blocks.isEmpty()) {
			System.out.println("No blocks!");
			doBlocks = false;
			blocksize = 0;
		}
		else 
			blocksize = blocks.size();
		if (items == null || items.isEmpty()) {
			System.out.println("No Items");
			doItems = false;
			if (!doBlocks)
				return;
			itemsize = 0;
		}
		else
			itemsize = items.size();
		int totalSize = itemsize + blocksize;
		GuiProgressUpdater.setItems(totalSize);
		if (doBlocks) {
			GuiProgressUpdater.updateActivity("Processing blocks in " + theFile.getName());
			int baseBlockID = 0;
			for (Property val : blocks.values()) {
				GuiProgressUpdater.increaseProg();
				if (!val.isIntValue()) {
					System.out.println("Skipping " + val.getName() + ", not an int");
				}
				if (IDTracker.isBlockIDAvailable(val.getInt())) {
					
					if (baseBlockID == 0)
						baseBlockID = val.getInt();
					// leave config alone
					System.out.println(val.getName() + " is OK");
					IDTracker.setBlockIDUsed(val.getInt());
					continue;
				}
				else {
					System.out.println("Fixing property: " + val.getName());
					if (baseBlockID > 0) {
						if (MathsHelper.isIntInRange(baseBlockID, 10, IDTracker.getNextBlockID())) {
							// ok
							val.value = String.valueOf(IDTracker.getNextBlockID());
							IDTracker.setBlockIDUsed(IDTracker.getNextBlockID());
						}
						else {
							// meh
							baseBlockID = IDTracker.getNextBlockID();
							val.value = String.valueOf(baseBlockID);
							IDTracker.setBlockIDUsed(baseBlockID);
						}
					}
					else {
						// meh
						baseBlockID = IDTracker.getNextBlockID();
						val.value = String.valueOf(baseBlockID);
						IDTracker.setBlockIDUsed(baseBlockID);
					}
				}
			}
			cfg.categories.put("block", blocks);
			cfg.save();
		}
		if (doItems) {
			GuiProgressUpdater.updateActivity("Processing items in " + theFile.getName());
			int baseBlockID = 0;
			for (Property val : items.values()) {
				GuiProgressUpdater.increaseProg();
				System.out.println(val.getName() + ":");
				if (IDTracker.isItemIDAvailable(val.getInt())) {
					if (baseBlockID == 0)
						baseBlockID = val.getInt();
					// leave config alone
					IDTracker.setItemIDUsed(val.getInt());
					continue;
				}
				else {
					if (baseBlockID > 0) {
						if (MathsHelper.isIntInRange(baseBlockID, 10, IDTracker.getNextItemID())) {
							// ok
							val.value = String.valueOf(IDTracker.getNextBlockID());
							IDTracker.setItemIDUsed(IDTracker.getNextBlockID());
						}
						else {
							// meh
							baseBlockID = IDTracker.getNextBlockID();
							val.value = String.valueOf(baseBlockID);
							IDTracker.setItemIDUsed(baseBlockID);
						}
					}
				}
			}
			cfg.categories.put("item", items);
			cfg.save();
		}
		cfg.save();
		return;
	}

}
