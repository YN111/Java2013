import java.io.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestFileInfo extends TestCase {

	private static final String PATH1 = "test1.txt";
	private static final String PATH2 = "test2.txt";

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のファイルを作成
		writeTestFile(PATH1);
		writeTestFile(PATH2);

		// テスト用ファイルの設定を変更
		File file = new File(PATH1);
		file.setLastModified(20140324230000L);
	}

	@Test
	public void testShowAllInfo() {
		FileInfo.showAllInfo(PATH1, new String[0]);
		System.out.flush();
		String result = baos.toString();
		assertTrue(result.contains("Name: test1.txt"));
		assertTrue(result.contains("Path: test1.txt"));
		assertTrue(result.contains("AbsolutePath: " + System.getProperty("user.dir")
				+ "\\test1.txt"));
		assertTrue(result.contains("CanonicalPath: " + System.getProperty("user.dir")
				+ "\\test1.txt"));
		assertTrue(result.contains("Parent: null"));
		assertTrue(result.contains("CanRead: true"));
		assertTrue(result.contains("CanWrite: true"));
		assertTrue(result.contains("IsFile: true"));
		assertTrue(result.contains("IsDirectory: false"));
		assertTrue(result.contains("IsHidden: false"));
		assertTrue(result.contains("LastModified: 20140324230000"));
		assertTrue(result.contains("Length: 4"));
	}

	@Test
	public void testShowAllInfoMultiArgs() {
		FileInfo.showAllInfo(PATH1, new String[] { PATH2 });
		System.out.flush();
		String result = baos.toString();
		assertTrue(result.contains("Name: test1.txt"));
		assertTrue(result.contains("Name: test2.txt"));
	}

	@Test
	public void testFileNotFound() {
		FileInfo.showAllInfo("test3.txt", new String[0]);
		System.out.flush();
		String result = baos.toString();
		assertTrue(result.contains("File not found"));
		assertTrue(result.contains("test3.txt"));
	}

	private void writeTestFile(String name) {
		FileWriter fw;
		try {
			fw = new FileWriter(name);
			fw.write("test");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			fail();
		}
	}
}
