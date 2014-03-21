import java.io.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestNameOpValueTokenizer extends TestCase {

	private ByteArrayOutputStream baos;
	private String crlf = System.getProperty("line.separator");

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));
	}

	@Test
	public void testCalc() {
		StringReader sr = new StringReader("first + 1\nsecond - 2\nthird = 3\nfirst + -4");
		NameOpValueTokenizer nov = new NameOpValueTokenizer();
		nov.calc(sr);
		System.out.flush();
		String actual = baos.toString();
		String expect = "first : -3.0" + crlf + "second: -2.0" + crlf + "third : 3.0" + crlf;
		assertEquals(expect, actual);
	}

	@Test
	public void testIllegalName() {
		StringReader sr = new StringReader("firs + 1\nsecond - 2\nthird = 3\nfirst + -4");
		NameOpValueTokenizer nov = new NameOpValueTokenizer();
		try {
			nov.calc(sr);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testIllegalOp() {
		StringReader sr = new StringReader("first * 1\nsecond - 2\nthird = 3\nfirst + -4");
		NameOpValueTokenizer nov = new NameOpValueTokenizer();
		try {
			nov.calc(sr);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testIllegalValue() {
		StringReader sr = new StringReader("first + a\nsecond - 2\nthird = 3\nfirst + -4");
		NameOpValueTokenizer nov = new NameOpValueTokenizer();
		try {
			nov.calc(sr);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
