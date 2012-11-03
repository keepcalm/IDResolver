package keepcalm.programs.idfixer;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GuiException extends Exception {

	/**
	 * 
	 */
	
	public static void createException(String err) {
		try {
			throw new GuiException(err);
		} catch (GuiException e) {
			;
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	public GuiException(String error) {
		System.out.println(error);
		
		JFrame frame = new JFrame();
		frame.setName("ID resolver error");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea err = new JTextArea(error);
		err.setEditable(false);
		JButton but = new JButton("Done");
		but.addMouseListener(new QuitListener());
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
