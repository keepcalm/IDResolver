package keepcalm.programs.idfixer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/** 
 * Tracks IDs - the lowest item ID that isn't a vanilla item will be above 4096.
 * @author keepcalm
 *
 */
public class IDTracker {
	private static boolean[] blockIDs = new boolean[4096];
	private static boolean[] itemIDs = new boolean[32000];
	private static IDTracker instance;
	
	private static final int SHIFTED_ITEM_INDEX = 4096;
	private static final int SHIFTED_BLOCK_INDEX = 1;
	
	
	
	private IDTracker() {
		instance = this;
		
		Arrays.fill(blockIDs, false);
		Arrays.fill(itemIDs, false);
		for (int i = 0; i < 145; i++) {
			//System.out.println("The block ID " + (i + 1) + " is not available!");
			blockIDs[i] = true;
		}
		
		for (int i = 256; i <= 404; i++) {
			blockIDs[i] = true;
		}
		
		for (int i = 2256; i < 2267; i++) {
			blockIDs[i] = true;
		}
	}
	/** 
	 * Ensure that an IDTracker instance is set up.
	 */
	public static void init() {
		if (instance == null) {
			new IDTracker();
		}
		
		return;
	}
	
	public static boolean isBlockIDAvailable(int id) {
		System.out.print("BlockID " + id);
		if (id <= 0 || id > 4096 || blockIDs[id - SHIFTED_BLOCK_INDEX]) {
			System.out.println(" is not available!");
			return false;
		}
		System.out.println(" is available!");
		return true;
	}
	
	public static boolean isItemIDAvailable(int id) {
		System.out.print("ItemID " + id);
		if (id <= 0) {
			System.out.println(" isn't available!");
			return false;
		}
		if (id <= SHIFTED_ITEM_INDEX && System.getProperty("idres.needBigItemID", "0") == "0") {
			System.out.println(" is too small for my liking!");
			return false;
		}
		System.out.println(" is OK");
		if (id > SHIFTED_ITEM_INDEX) {
			return itemIDs[id - (SHIFTED_ITEM_INDEX + 1)];
		}
		return itemIDs[id];
	}
	/**
	 * Try and set a blockID as used.
	 * 
	 * @param id
	 * @return true if the ID was successfully used
	 */
	public static boolean setBlockIDUsed(int id) {
		if (id > SHIFTED_BLOCK_INDEX) 
			id -= SHIFTED_BLOCK_INDEX;
		if (id <= 0) {
			return false;
		}
		if (blockIDs[id] == false) {
			blockIDs[id] = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Try and set an itemID as used.
	 * 
	 * @param id
	 * @return true if the ID was successfully used
	 */
	public static boolean setItemIDUsed(int id) {
		id -= SHIFTED_ITEM_INDEX;
		if (id <= 0) {
			return false;
		}
		if (itemIDs[id] == false) {
			itemIDs[id] = true;
			return true;
		}
		return false;
	}
	/** 
	 * Returns the next available block id with a free spot in the next ID above.
	 * @return 0 if no more block ids
	 */
	public static int getNextBlockID() {
		int i;
		for (i = 0; i < blockIDs.length; i++) {
			if (i + 1 == blockIDs.length) {
				return 0;
			}
			if (blockIDs[i] == false && blockIDs[i + 1] == false) {
				break;
			}
		}
		return SHIFTED_BLOCK_INDEX + i;
	}
	/** 
	 * Returns the next available item id with a free spot in the next ID above.
	 * @return 0 if no more block ids
	 */
	public static int getNextItemID() {
		int i;
		for (i = 0; i < itemIDs.length; i++) {
			if (i + 1 == itemIDs.length) {
				System.out.println("derp");
				return 0;
			}
			if (itemIDs[i] == false && itemIDs[i + 1] == false) {
				System.out.println("woo!");
				break;
			}
		}
		System.out.println("ITEMID REQ -> " + i);
		return SHIFTED_ITEM_INDEX + i;
	}
	
	public static void dumpIDs() {
		FileWriter fw;
		try {
			fw = new FileWriter(System.getProperty("user.home") + "/ID_res_dump.out");
			
			fw.write("Block IDs:\n");
			
			for (int i = 0; i < blockIDs.length; i++) {
				fw.write((i + SHIFTED_BLOCK_INDEX) + " used: " + String.valueOf(blockIDs[i]));
				fw.write("\n");
			}	
			
			for (int i = 0; i < itemIDs.length; i++) {
				fw.write((i + SHIFTED_ITEM_INDEX) + " used: " + String.valueOf(itemIDs[i]));
				fw.write("\n");
			}	
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
