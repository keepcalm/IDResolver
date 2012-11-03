package keepcalm.programs.idfixer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import keepcalm.programs.idfixer.utils.ComboBoxSelectionListener;
import java.awt.GridBagLayout;

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
		try {
		frame.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
		scrollp.setBounds(30, 109, 400, 125);
		//panel.setMaximumSize(new Dimension(350,-1));
		//panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		getContentPane().add(scrollp);
		FlowLayout pl = new FlowLayout();
		
		//JCheckBox c = new JCheckBox("blah");
		//panel.add(c);
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
		int column = 0;
		int row = 0;
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
		int column = 0,row = 0;
		for (JCheckBox cb : boxes) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridheight = 1;
			gbc.gridwidth = 2;
			gbc.gridx = column;
			gbc.gridy = row;
			//cb.setHorizontalAlignment(SwingConstants.WEST);
			Logging.logger.info(cb.getText());
			//cb.se
			panel.add(cb,gbc);
			cb.setVisible(true);
			column += 2;
			if (column % 4 == 0) {
				row++;
				column = 0;
			}
			
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
