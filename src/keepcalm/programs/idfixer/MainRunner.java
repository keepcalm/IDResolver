package keepcalm.programs.idfixer;

import java.io.BufferedWriter;
import java.io.PrintWriter;

public class MainRunner implements Runnable {

	@Override
	@SuppressWarnings("all")
	public void run() {
		try {
			Main.main(new String[] {"--config=" + (String) MainWindow.comboBox.getSelectedItem()});
		} catch (Exception e) {
			BufferedWriter b = new BufferedWriter(new DummyWriter());
			PrintWriter c = new PrintWriter(b);
			e.printStackTrace(c);
			
		}
		if (!DummyWriter.chars.isEmpty()) {
			GuiException.createException(DummyWriter.chars);
		}
	
	}

}
