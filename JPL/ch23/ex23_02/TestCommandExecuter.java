import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestCommandExecuter extends TestCase {

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));
	}

	@Test
	public void testExecuteCommand() {
		try {
			String expected = "1 java version \"" + System.getProperty("java.version") + "\"";
			CommandExecuter.executeCommand(new String[] { "java", "-version" });
			System.out.flush();
			String actual = baos.toString();
			int lineSeparator = actual.indexOf(System.getProperty("line.separator"));
			assertEquals(expected, actual.substring(0, lineSeparator));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
