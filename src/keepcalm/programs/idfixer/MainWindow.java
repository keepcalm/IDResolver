package keepcalm.programs.idfixer;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings({"serial", "unused"})
public class MainWindow extends JFrame {
	public static JComboBox comboBox;
	private JPanel contentPane;
	public static MainWindow instance;
	public static String[] comboBoxOpts;
	
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
		
		setTitle("IDResolver config");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblHi = new JLabel("Configuration");
		lblHi.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_lblHi = new GridBagConstraints();
		gbc_lblHi.gridwidth = 6;
		gbc_lblHi.insets = new Insets(0, 60, 5, 5);
		gbc_lblHi.gridx = 2;
		gbc_lblHi.gridy = 1;
		getContentPane().add(lblHi, gbc_lblHi);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addMouseListener(new BeginMouseListener());
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.insets = new Insets(50, 0, 5, 5);
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(comboBoxOpts));
		comboBox.setEditable(true);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.ipadx = 10;
		gbc_comboBox.gridwidth = 10;
		gbc_comboBox.insets = new Insets(50, 10, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		getContentPane().add(comboBox, gbc_comboBox);
		
		//Iterator it = 
		//for (int y = 4; )
		
		
		
	}

}
