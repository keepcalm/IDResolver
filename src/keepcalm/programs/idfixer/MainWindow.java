package keepcalm.programs.idfixer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import keepcalm.programs.idfixer.utils.ComboBoxSelectionListener;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

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
		setResizable(false);
		
		setTitle("IDResolver config");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblHi = new JLabel("Configuration");
		lblHi.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_lblHi = new GridBagConstraints();
		gbc_lblHi.gridwidth = 10;
		gbc_lblHi.insets = new Insets(0, 0, 5, 5);
		gbc_lblHi.gridx = 3;
		gbc_lblHi.gridy = 0;
		getContentPane().add(lblHi, gbc_lblHi);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addMouseListener(new BeginMouseListener());
		
		lblNewLabel = new JLabel("Configs to process first:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.insets = new Insets(50, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 1;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(comboBoxOpts));
		comboBox.setEditable(true);
		comboBox.addItemListener(new ComboBoxSelectionListener());
		comboBox.addFocusListener(new ComboBoxSelectionListener());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.ipadx = 10;
		gbc_comboBox.gridwidth = 11;
		gbc_comboBox.insets = new Insets(0, 10, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 6;
		gbc_panel.gridwidth = 13;
		gbc_panel.insets = new Insets(10, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox j = new JCheckBox("Choose a folder!");
		j.setEnabled(false);
		boxes.add(j);
		updateCheckBoxes();
		
		//Iterator it = 
		//for (int y = 4; )
		
		
		
	}
	
	
	public static void initaliseCheckBoxes(String[] names) {
		System.out.println("INIT...");
		panel.removeAll();
		boxes = new ArrayList<JCheckBox>();
		for (String j : names) {
			//System.out.println(j);
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
			System.out.println(cb.getText());
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
