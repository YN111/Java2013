import java.io.*;

import org.junit.Before;

import junit.framework.TestCase;

public class FindWordTest extends TestCase {

	private static final String FILE_PATH = "test.txt";
	private static final String SAMPLE_TEXT = "This is a sample text.\nsearch word is TEST.\nThis is a sample text.\nsearch word is TEST.\nThis is a sample text.";

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のテキストファイルを作成する
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write(SAMPLE_TEXT);
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFindAndPrint() {

		String crlf = System.getProperty("line.separator");
		String expect = "line:2  search word is TEST." + crlf + "line:4  search word is TEST."
				+ crlf;

		try {
			FindWord.findAndPrint(FILE_PATH, "TEST");
		} catch (IOException e) {
			fail();
		}

		System.out.flush();
		String actual = baos.toString();
		assertEquals(expect, actual);
	}
}
