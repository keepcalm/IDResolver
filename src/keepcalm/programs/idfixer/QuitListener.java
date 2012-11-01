package keepcalm.programs.idfixer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class QuitListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		arg0.consume();
		System.exit(0);
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
