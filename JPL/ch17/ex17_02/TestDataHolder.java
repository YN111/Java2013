import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestDataHolder extends TestCase {

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
		DataHolder dh = new DataHolder();
		WeakReference<File> wr;
		byte[] b;

		b = dh.readFile(new File("test1.txt"));
		assertEquals(new String(b), "test1");

		wr = getLastFileReference(dh);
		assertEquals(wr.get(), new File("test1.txt"));
		b = dh.readFile(new File("test1.txt"));
		assertEquals(new String(b), "test1");

		Runtime.getRuntime().gc(); // 弱い参照はここで回収される
		wr = getLastFileReference(dh);
		assertEquals(wr.get(), null);
		b = dh.readFile(new File("test1.txt"));
		assertEquals(new String(b), "test1");
	}

	@SuppressWarnings("unchecked")
	private WeakReference<File> getLastFileReference(DataHolder dh) {
		Field f = null;
		try {
			f = dh.getClass().getDeclaredField("lastFile");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		WeakReference<File> wr = null;
		try {
			f.setAccessible(true);
			wr = (WeakReference<File>) f.get(dh);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return wr;
	}
}
