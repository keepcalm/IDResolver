package keepcalm.programs.idfixer;

import static java.awt.Component.BOTTOM_ALIGNMENT;
import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GuiProgressUpdater {
	private static JFrame window;
	public static JProgressBar progBar;
	private static Box box;
	public static JButton button;
	public static JLabel currentActivity;
	private static int items = 0;
	private static int curProg = 0;
	private static final String format = "Fixing file %s (item %s of %s)";
	public static final String htmlFormat = "<html><b><font size=\"+1\">%s</font></b></html>";
	private static String curFile = "Unknown";
	
	
	public static void updateActivity(String newText) {
		currentActivity.setText(String.format(htmlFormat, new Object[] {newText}));
	}
	
	public static void changeFile(String filename) {
		changeFile(filename, 0);
	}
	public static void increaseProg() {
		curProg++;
		progBar.setString(String.format(format, new Object[] {curFile, 0, items}));
		
	}
	public static void setItems(int maxItems) {
		if (maxItems > 0) {
			progBar.setIndeterminate(false);
			progBar.setMaximum(maxItems);
		}
		else {
			progBar.setIndeterminate(true);
			progBar.setMaximum(-1);
		}
		progBar.setString(String.format(format, new Object[] {curFile, 0, items}));
	}
	
	public static void changeFile(String fileName, int maxItems) {
		if (maxItems == 0) {
			progBar.setIndeterminate(true);
			progBar.setMaximum(-1);
		}
		else {
			progBar.setIndeterminate(false);
			progBar.setMaximum(maxItems);
		}
		
		if (fileName == "" || fileName == null) {
			fileName = "Unknown";
		}
		curFile = fileName;
		items = maxItems;
		curProg = 0;
		progBar.setString(String.format(format, new Object[] {curFile, curProg, items})); 
	}
	
	public static void toggleBarIndeterminate() {
		progBar.setIndeterminate(!progBar.isIndeterminate());
	}
	
	public static void updateProgress(int itemsCompleted) {
		progBar.setValue(itemsCompleted);
		
		progBar.setString(String.format(format, new Object[] {curFile, itemsCompleted, items})); 
	}
	
	public GuiProgressUpdater() {
		
		window = new JFrame("Configuration fixer progress");
		window.setLocationByPlatform(true);
		window.setLocation(60, 70);
		box = Box.createVerticalBox();
		window.add(box);
		currentActivity = new JLabel(String.format(htmlFormat, new Object[] {"Setting up..."}));
		
		progBar = new JProgressBar();
		progBar.setString("Preparing to start...");
		progBar.setFont(progBar.getFont().deriveFont(18F));
		progBar.setStringPainted(true);
		progBar.setIndeterminate(true);
		progBar.setSize(370, 100);
		button = new JButton("Stop!");
		button.addMouseListener(new QuitListener());
		//b.setSize(40, 80);
		//window.getContentPane().setLayout(new FlowLayout());
		//box.add(Box.createRigidArea(new Dimension((window.getSize().width / 2), 10)));
		box.add(Box.createRigidArea(new Dimension(0,10)));
		//txt.setAlignmentY(CENTER_ALIGNMENT);
		box.add(currentActivity, TOP_ALIGNMENT + CENTER_ALIGNMENT);
		
		box.add(Box.createRigidArea(new Dimension(0,10)));
		box.add(progBar, CENTER_ALIGNMENT);
		box.add(Box.createRigidArea(new Dimension(0,10)));
		box.add(button, BOTTOM_ALIGNMENT + CENTER_ALIGNMENT);
		box.add(Box.createRigidArea(new Dimension(0,10)));
		window.setSize(400, 120);
		//window.setBounds(900, 350, 400, 200);
		
		window.setVisible(true);
		
		
	}
	
	
}
