import java.io.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestEntryTable extends TestCase {

	private static final String PATH = "test.txt";
	private static final String EXPECT1 = "%%test -1";
	private static final String EXPECT2 = "%%test --2";
	private static final String EXPECT3 = "%%test ---3";
	private static final String EXPECT4 = "%%test ----4";
	private static final String EXPECT5 = "%%test -----5";
	private static final String ILLEGAL = "IllegalLine";

	private ByteArrayOutputStream baos;
	private String crlf = System.getProperty("line.separator");

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のファイルを作成
		FileWriter fw;
		try {
			fw = new FileWriter(PATH);
			fw.write(EXPECT1 + crlf + ILLEGAL + crlf + EXPECT2 + crlf + EXPECT3 + crlf + EXPECT4
					+ crlf + EXPECT5);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testRandomPrint() {
		try {
			EntryTable.randomPrint(PATH);
			System.out.flush();
			String result = baos.toString();
			assertTrue(result.contains(EXPECT1));
			assertTrue(result.contains(EXPECT2));
			assertTrue(result.contains(EXPECT3));
			assertTrue(result.contains(EXPECT4));
			assertTrue(result.contains(EXPECT5));
			assertFalse(result.contains(ILLEGAL));
		} catch (IOException e) {
			fail();
		}
	}
}
