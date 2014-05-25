import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;

import junit.framework.TestCase;

public class TestCommandExecuter extends TestCase {
	
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));
	}

	public void testExecuteCommand() {
		try {
			String expected = "1 java version \"" + System.getProperty("java.version") + "\"";
			CommandExecuter.executeCommand(new String[] { "java", "-version" }, "VM");
			System.out.flush();
			String actual = baos.toString();
			int lineSeparator = actual.indexOf(System.getProperty("line.separator"));
			assertEquals(expected, actual.substring(0, lineSeparator));
			assertFalse(actual.contains("VM"));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
