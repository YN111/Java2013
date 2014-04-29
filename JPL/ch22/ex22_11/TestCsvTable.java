import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestCsvTable extends TestCase {

	private static final String NORMAL_FILE_PATH = "normal.csv";
	private static final String ERROR_FILE_PATH = "error.csv";

	@Before
	public void setUp() {
		// テスト用のテキストファイルを作成する
		try {
			// 空行を含む
			FileWriter fr = new FileWriter(NORMAL_FILE_PATH);
			fr.write("test1,test2,test3\n");
			fr.write("1,-2,3.0\n"); // 数値が正しく読み込めるかチェック
			fr.write("test4,test5,test6\n\n\n");
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// カンマ数が規定よりも多い
			FileWriter fr = new FileWriter(ERROR_FILE_PATH);
			fr.write("test1,test2,test3,err\n");
			fr.write("test4,test5,test6,err\n");
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadCSVTable() {
		try {
			FileReader reader = new FileReader(NORMAL_FILE_PATH);
			List<String[]> list = CsvTable.readCSVTable(reader, 3);
			String[] s1 = list.get(0);
			String[] s2 = list.get(1);
			String[] s3 = list.get(2);
			assertEquals(s1[0], "test1");
			assertEquals(s1[1], "test2");
			assertEquals(s1[2], "test3");
			assertEquals(s2[0], "1");
			assertEquals(s2[1], "-2");
			assertEquals(s2[2], "3.0");
			assertEquals(s3[0], "test4");
			assertEquals(s3[1], "test5");
			assertEquals(s3[2], "test6");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReadCSVTableIllealCellNum() {
		try {
			FileReader reader = new FileReader(ERROR_FILE_PATH);
			CsvTable.readCSVTable(reader, 3);
			fail();
		} catch (IOException e) {
		}
	}
}
