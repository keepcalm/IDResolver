package keepcalm.programs.idfixer;

import java.io.IOException;
import java.io.Writer;

public class DummyWriter extends Writer {
	public static String chars = "";
	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		chars += String.copyValueOf(cbuf);
	}

}
