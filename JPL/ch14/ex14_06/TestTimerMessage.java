import junit.framework.TestCase;
import org.junit.*;
import java.io.*;

public class TestTimerMessage extends TestCase {
	private ByteArrayOutputStream baos;
	private PrintStream ps;
	private String expect;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		ps = System.out;
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		String crlf = System.getProperty("line.separator"); // 改行コード
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < 100; i++) {
			sb.append(i);
			if (i % 7 == 0)
				sb.append(crlf + "msg2");
			if (i % 15 == 0)
				sb.append(crlf + "msg1");
			sb.append(crlf);
		}
		expect = sb.toString();
	}

	@After
	public void tearDown() {
		System.setOut(ps);
	}

	@Test
	public void testSyncCalc() {
		Main.main(null);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.flush();
		String actual = baos.toString();
		assertEquals(expect, actual);
	}
}