import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestCsvTable extends TestCase {
	
	private static final String FILE_PATH = "test.csv";
	
	@Before
	public void setUp() {
		// テスト用のテキストファイルを作成する
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write("test1,test2,test3\n");
			fr.write("test4,test5,test6\n");
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadCSVTable() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			List<String[]> list = CsvTable.readCSVTable(reader, 3);
			String[] s1 = list.get(0);
			String[] s2 = list.get(1);
			assertEquals(s1[0], "test1");
			assertEquals(s1[1], "test2");
			assertEquals(s1[2], "test3");
			assertEquals(s2[0], "test4");
			assertEquals(s2[1], "test5");
			assertEquals(s2[2], "test6");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
