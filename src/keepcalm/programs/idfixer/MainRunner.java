package keepcalm.programs.idfixer;

import java.io.PrintWriter;

public class MainRunner implements Runnable {

	@Override
	@SuppressWarnings("all")
	public void run() {
		try {
			Main.main(new String[] {"--config=" + (String) MainWindow.comboBox.getSelectedItem()});
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter c = new PrintWriter(new DummyWriter());
			e.printStackTrace(c);
			
		}
		if (!DummyWriter.chars.isEmpty()) {
			GuiException.createException(DummyWriter.chars);
		}
	
	}

}
