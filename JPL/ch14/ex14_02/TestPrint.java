import org.junit.Test;
import junit.framework.TestCase;

public class TestPrint extends TestCase {

	@Test
	public void testPrintServer() throws Exception {
		PrintServer server = new PrintServer();
		try {
			server.run();
			fail();
		} catch (AssertionError e) {
		}
	}

}