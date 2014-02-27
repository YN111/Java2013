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
		sb.append("class java.awt.Frame.AccessibleAWTFrame <- nested class (java.awt.Frame)");
		sb.append(crlf);
		sb.append(" extends java.awt.Window.AccessibleAWTWindow <- nested class (java.awt.Window)");
		sb.append(crlf);
		sb.append("  extends java.awt.Container.AccessibleAWTContainer <- nested class (java.awt.Container)");
		sb.append(crlf);
		sb.append("   extends java.awt.Component.AccessibleAWTComponent <- nested class (java.awt.Component)");
		sb.append(crlf);
		sb.append("    implements java.io.Serializable");
		sb.append(crlf);
		sb.append("    implements javax.accessibility.AccessibleComponent");
		sb.append(crlf);
		sb.append("    extends javax.accessibility.AccessibleContext");
		sb.append(crlf);
		expect = sb.toString();
	}

	@After
	public void tearDown() {
		System.setOut(ps);
	}

	@Test
	public void testSyncCalc() {
		String[] params = { "java.awt.Frame$AccessibleAWTFrame" };
		TypeDesc.main(params);
		System.out.flush();
		String actual = baos.toString();
		assertEquals(expect, actual);
		assertEquals(1, 1);
	}
}