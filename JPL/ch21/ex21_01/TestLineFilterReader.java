import java.io.CharArrayReader;
import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.Test;

import junit.framework.TestCase;

public class TestLineFilterReader extends TestCase {

	private static final String CRLF = System.getProperty("line.separator");

	@Test
	public void testReadLine() {
		String input = "test1" + CRLF + "TEST2";
		CharArrayReader car = new CharArrayReader(input.toCharArray());
		LineFilterReader lfr = new LineFilterReader(car);
		try {
			int[] read1 = lfr.readLine();
			int[] read2 = lfr.readLine();
			assertEquals(read1[0], 't');
			assertEquals(read1[1], 'e');
			assertEquals(read1[2], 's');
			assertEquals(read1[3], 't');
			assertEquals(read1[4], '1');
			assertEquals(read2[0], 'T');
			assertEquals(read2[1], 'E');
			assertEquals(read2[2], 'S');
			assertEquals(read2[3], 'T');
			assertEquals(read2[4], '2');
			assertEquals(lfr.read(), -1);
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testConvertIntToString() {
		String input = "test1" + CRLF + "TEST2";
		CharArrayReader car = new CharArrayReader(input.toCharArray());
		LineFilterReader lfr = new LineFilterReader(car);
		try {
			int[] read1 = lfr.readLine();
			int[] read2 = lfr.readLine();

			Method m;
			try {
				m = SortedFileReader.class.getDeclaredMethod("convertIntToString", int[].class);
				m.setAccessible(true);
				String result1 = (String) m.invoke(SortedFileReader.class, read1);
				String result2 = (String) m.invoke(SortedFileReader.class, read2);
				assertEquals(result1, "test1");
				assertEquals(result2, "TEST2");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			fail();
		}
	}
}
