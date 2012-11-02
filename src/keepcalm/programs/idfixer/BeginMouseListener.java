package keepcalm.programs.idfixer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeginMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!((String)MainWindow.comboBox.getSelectedItem()).isEmpty()) {
			// process
			List<String> objs = new ArrayList<String>();
			objs.addAll(ComboBoxSaver.comboBoxOptions);
			objs.add(((String)MainWindow.comboBox.getSelectedItem()));
			ComboBoxSaver.comboBoxOptions = objs;
			try {
				ComboBoxSaver.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String targDir = (String) MainWindow.comboBox.getSelectedItem();
			File file = new File(targDir);
			if (file.isDirectory() && file.list().length > 0) {
				//MainWindow.instance.setVisible(false);
				//try {
				//Thread.sleep(1000);
				Thread mainThread = new Thread(new MainRunner());
				mainThread.start();

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
