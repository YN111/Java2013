import java.io.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestFileListViewer extends TestCase {
	private static final String PATH_TXT1 = "test1.txt";
	private static final String PATH_TXT2 = "test2.txt";
	private static final String PATH_TMP = "test3.tmp";

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のファイルを作成
		FileWriter fw;
		try {
			fw = new FileWriter(PATH_TXT1);
			fw.close();
			fw = new FileWriter(PATH_TXT2);
			fw.close();
			fw = new FileWriter(PATH_TMP);
			fw.close();
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testShowCountResult() {
		FileListViewer.showFilterFileList(".", ".txt");
		System.out.flush();
		String result = baos.toString();
		assertTrue(result.contains(PATH_TXT1));
		assertTrue(result.contains(PATH_TXT2));
		assertFalse(result.contains(PATH_TMP));
	}

}
