package keepcalm.programs.idfixer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IConfigFileExaminer {
	public int getTotalItems();
	
	public List<File> getFilesToExamine();
	
	public void examineNextFile() throws GuiNonFatalException, IOException;
}
