package keepcalm.programs.idfixer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseWindowListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		arg0.getComponent().setVisible(false);
		//arg0.getComponent().
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
