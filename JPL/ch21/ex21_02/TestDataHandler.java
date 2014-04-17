import java.io.*;
import java.lang.reflect.*;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestDataHandler extends TestCase {

	@Before
	public void setUp() {
		File file = new File("test1.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("test1");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testReadFile() {
		DataHandler dh = new DataHandler();
		WeakHashMap<File, byte[]> wr;
		byte[] b;

		b = dh.readFile(new File("test1.txt"));
		assertEquals(new String(b), "test1");

		Runtime.getRuntime().gc(); // 弱い参照はここで回収される
		wr = getLastFileReference(dh);
		b = wr.get(new File("test1.txt"));
		assertEquals(b, null);

		b = dh.readFile(new File("test1.txt")); // 再びデータを読み込む
		assertEquals(new String(b), "test1");
	}

	@SuppressWarnings("unchecked")
	private WeakHashMap<File, byte[]> getLastFileReference(DataHandler dh) {
		Field f = null;
		try {
			f = dh.getClass().getDeclaredField("lastData");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		WeakHashMap<File, byte[]> wr = null;
		try {
			f.setAccessible(true);
			wr = (WeakHashMap<File, byte[]>) f.get(dh);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return wr;
	}
}
