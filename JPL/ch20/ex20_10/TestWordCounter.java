import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestWordCounter extends TestCase {
	private static final String PATH = "test.txt";

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のファイルを作成
		FileWriter fw;
		try {
			fw = new FileWriter(PATH);
			fw.write("===test=== test file : This is a test file");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testShowCountResult() {
		WordCounter.showCountResult(PATH);
		System.out.flush();
		String result = baos.toString();
		assertTrue(result.contains("test:3"));
		assertTrue(result.contains("file:2"));
		assertTrue(result.contains("This:1"));
		assertTrue(result.contains("is:1"));
		assertTrue(result.contains("a:1"));
	}
}
