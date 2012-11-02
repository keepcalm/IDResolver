package keepcalm.programs.idfixer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class BeginMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!((String)MainWindow.comboBox.getSelectedItem()).startsWith("Choose one")) {
			// process
			String targDir = (String) MainWindow.comboBox.getSelectedItem();
			File file = new File(targDir);
			if (file.isDirectory() && file.list().length > 0) {
				arg0.getComponent().getParent().setVisible(false);
				try {
					Main.beginConfigChecking(file);
				} catch (GuiNonFatalException e) {
					// non-fatal
				} catch (IOException e) {
					e.printStackTrace();
				}
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
