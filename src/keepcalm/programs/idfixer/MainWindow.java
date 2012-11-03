package keepcalm.programs.idfixer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import keepcalm.programs.idfixer.utils.ComboBoxSelectionListener;

@SuppressWarnings({"serial", "unused"})
public class MainWindow extends JFrame {
	public static JComboBox comboBox;
	private JPanel contentPane;
	public static MainWindow instance;
	public static String[] comboBoxOpts;
	private static JList list;
	public static List<JCheckBox> boxes = new ArrayList<JCheckBox>();
	private static JPanel panel;
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		//ComboBoxSaver.load(new File())
		ComboBoxSaver.init();
		ComboBoxSaver.load();
		Logging.init();
		List<String> l = new ArrayList<String>();
		l.add("");
		l.addAll(ComboBoxSaver.comboBoxOptions);
		comboBoxOpts = l.toArray(new String[0]);
		MainWindow frame = new MainWindow();
		frame.setVisible(true);
		instance = frame;
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		setTitle("IDResolver config");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblHi = new JLabel("Configuration");
		lblHi.setBounds(137, 0, 156, 24);
		lblHi.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(lblHi);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.setBounds(375, 49, 58, 25);
		btnNewButton.addMouseListener(new BeginMouseListener());
		
		lblNewLabel = new JLabel("Configs to process first:");
		lblNewLabel.setBounds(108, 79, 170, 15);
		getContentPane().add(lblNewLabel);
		getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setBounds(56, 49, 298, 24);
		comboBox.setModel(new DefaultComboBoxModel(comboBoxOpts));
		comboBox.setEditable(true);
		comboBox.addItemListener(new ComboBoxSelectionListener());
		comboBox.addFocusListener(new ComboBoxSelectionListener());
		getContentPane().add(comboBox);
		
		
		panel = new JPanel();
		JScrollPane scrollp = new JScrollPane(panel);
		scrollp.setBounds(46, 109, 350, 125);
		//panel.setMaximumSize(new Dimension(350,-1));
		panel.setBackground(Color.WHITE);
		getContentPane().add(scrollp);
		
		panel.setLayout(new FlowLayout());
		
		
		JCheckBox j = new JCheckBox("Choose a folder!");
		j.setEnabled(false);
		boxes.add(j);
		updateCheckBoxes();
		
		//Iterator it = 
		//for (int y = 4; )
		
		
		
	}
	
	
	public static void initaliseCheckBoxes(String[] names) {
		Logging.logger.info("INIT...");
		panel.removeAll();
		boxes = new ArrayList<JCheckBox>();
		for (String j : names) {
			//Logging.logger.info(j);
			JCheckBox cb = new JCheckBox(j);
			if (j.startsWith("extrabiomes")) {
				// recommend support for extrabiomes
				cb.setSelected(true);
			}
			boxes.add(cb);
		}
	}

	public static void updateCheckBoxes() {
		panel.setVisible(false);
		for (JCheckBox cb : boxes) {
			Logging.logger.info(cb.getText());
			
			panel.add(cb);
			cb.setVisible(true);
		}
		panel.invalidate();
		panel.setVisible(true);
		
	}
	
	/**
	 * @deprecated
	 * @param newContents
	 */
	@Deprecated
	public static void updateListContents(final String[] newContents) {
		AbstractListModel j = new AbstractListModel() {
			String[] values = newContents;
			@Override
			public Object getElementAt(int index) {
				return values[index];
			}

			@Override
			public int getSize() {
				return values.length;
			}
			
		};
		list.setModel(j);
	}

	public static void clearBoxes() {
		
	}
}
