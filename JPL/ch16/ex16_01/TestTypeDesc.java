import junit.framework.TestCase;
import org.junit.*;
import java.io.*;

public class TestTypeDesc extends TestCase {
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
		sb.append("class java.util.HashMap<K, V, \b\b>");
		sb.append(crlf);
		sb.append(" implements java.util.Map<K, V, \b\b>");
		sb.append(crlf);
		sb.append(" implements java.lang.Cloneable");
		sb.append(crlf);
		sb.append(" implements java.io.Serializable");
		sb.append(crlf);
		sb.append(" extends java.util.AbstractMap<K, V, \b\b>");
		sb.append(crlf);
		sb.append("  implements java.util.Map<K, V, \b\b>");
		sb.append(crlf);
		expect = sb.toString();
	}

	@After
	public void tearDown() {
		System.setOut(ps);
	}

	@Test
	public void testSyncCalc() {
		String[] params = { "java.util.HashMap" };
		TypeDesc.main(params);
		System.out.flush();
		String actual = baos.toString();
		assertEquals(expect, actual);
		assertEquals(1, 1);
	}
}