package keepcalm.programs.idfixer;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GuiNonFatalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuiNonFatalException(String error) {
		System.out.println(error);
		
		JDialog frame = new JDialog();
		frame.setName("ID resolver error");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JTextArea err = new JTextArea(error);
		JButton but = new JButton("OK");
		but.addMouseListener(new CloseWindowListener());
		
		//frame.setLayout(new FlowLayout());
		frame.setSize(100, 200);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(err, JFrame.CENTER_ALIGNMENT);
		frame.getContentPane().add(but, Component.BOTTOM_ALIGNMENT);
		frame.pack();
		frame.setVisible(true);
		
		while (true)
			;
		
	}

}
