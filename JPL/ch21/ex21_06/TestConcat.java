import java.io.*;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestConcat extends TestCase {

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		// printlnメソッドの出力先をbaosに設定
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));

		// テスト用のテキストファイルを作成する
		for (int i = 0; i < 3; i++) {
			try {
				FileWriter fr = new FileWriter("test" + i + ".txt");
				fr.write("test" + i);
				fr.flush();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test() throws IOException {
		String[] args = new String[] { "test1.txt", "test2.txt", "test3.txt" };
		Concat.main(args);
		System.out.flush();
		assertEquals("test1test2test3", baos.toString());
	}

}
