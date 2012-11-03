package keepcalm.programs.idfixer.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import keepcalm.programs.idfixer.FolderDescender;
import keepcalm.programs.idfixer.MainWindow;

public class ComboBoxSelectionListener implements ItemListener, FocusListener {

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		System.out.println("Runnning...");
		String selectedItem = (String) arg0.getItem();
		File selectedConfigurationFolder = new File(selectedItem);
		if (selectedConfigurationFolder.exists() == false ) {
			System.out.println("D'oh!");
			return;
		}
		List<File> cfgs = Arrays.asList(selectedConfigurationFolder.listFiles(new ConfigFileFilter()));
		List<File> folders = Arrays.asList(selectedConfigurationFolder.listFiles(new FolderDescender.FolderMatcher()));
		List<String> names = new ArrayList<String>();
		
		Iterator<File> it = cfgs.iterator();
		while (it.hasNext()) {
			File j = it.next();
			names.add(j.getName());
		}
		it = folders.iterator();
		while (it.hasNext()) {
			File j = it.next();
			names.add(j.getName());
		}
		MainWindow.clearBoxes();
		MainWindow.initaliseCheckBoxes(names.toArray(new String[0]));
		MainWindow.updateCheckBoxes();
		
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {
		System.out.println("Runnning...");
		String selectedItem = (String) MainWindow.comboBox.getSelectedItem();
		System.out.println("Still running...");
		System.out.println(selectedItem);
		File selectedConfigurationFolder = new File(selectedItem);
		System.out.println(selectedConfigurationFolder.getAbsolutePath());
		if (selectedConfigurationFolder.exists() == false ) {
			return;
		}
		List<File> cfgs = Arrays.asList(selectedConfigurationFolder.listFiles(new ConfigFileFilter()));
		List<File> folders = Arrays.asList(selectedConfigurationFolder.listFiles(new FolderDescender.FolderMatcher()));
		List<String> names = new ArrayList<String>();
		
		Iterator<File> it = cfgs.iterator();
		while (it.hasNext()) {
			File j = it.next();
			System.out.println(j.getName());
			names.add(j.getName());
		}
		it = folders.iterator();
		while (it.hasNext()) {
			File j = it.next();
			System.out.println(j.getName());
			names.add(j.getName());
		}
		
		MainWindow.updateListContents(names.toArray(new String[0]));
	}

}
