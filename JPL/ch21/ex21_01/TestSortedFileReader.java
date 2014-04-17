import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestSortedFileReader extends TestCase {

	private static final String CRLF = System.getProperty("line.separator");

	private static final String FILE_PATH = "test.txt";
	private static final String SAMPLE_TEXT = "a:line1" + CRLF + "h:line2" + CRLF + "f:line3" + CRLF + "b:line4" + CRLF
			+ "e:line5" + CRLF + "j:line6";

	@Before
	public void setUp() {
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

	@Test
	public void testReadFile() {
		List<String> lines = SortedFileReader.readFile(FILE_PATH);
		assertEquals(lines.size(), 6);
		assertEquals(lines.get(0), "a:line1");
		assertEquals(lines.get(1), "b:line4");
		assertEquals(lines.get(2), "e:line5");
		assertEquals(lines.get(3), "f:line3");
		assertEquals(lines.get(4), "h:line2");
		assertEquals(lines.get(5), "j:line6");
	}
}