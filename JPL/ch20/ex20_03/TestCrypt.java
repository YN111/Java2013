import java.io.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCrypt extends TestCase {

	@Test
	public void testCryptNoArgs() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		EncryptOutputStream eos = new EncryptOutputStream(os);
		try {
			eos.write((byte) 't');
			eos.flush();
		} catch (IOException e) {
			fail();
		}
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		DecryptInputStream dis = new DecryptInputStream(is);
		try {
			assertEquals(dis.read(), (byte) 't');
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testCryptOneArg() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		EncryptOutputStream eos = new EncryptOutputStream(os);
		try {
			eos.write("test".getBytes());
			eos.flush();
		} catch (IOException e) {
			fail();
		}
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		DecryptInputStream dis = new DecryptInputStream(is);
		byte[] buf = new byte[4];
		try {
			dis.read(buf);
			assertEquals(buf[0], (byte) 't');
			assertEquals(buf[1], (byte) 'e');
			assertEquals(buf[2], (byte) 's');
			assertEquals(buf[3], (byte) 't');
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testCryptThreeArgs() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		EncryptOutputStream eos = new EncryptOutputStream(os);
		try {
			eos.write("test".getBytes());
			eos.flush();
		} catch (IOException e) {
			fail();
		}
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		DecryptInputStream dis = new DecryptInputStream(is);
		byte[] buf = new byte[10];
		try {
			dis.read(buf, 4, 4);
			assertEquals(buf[4], (byte) 't');
			assertEquals(buf[5], (byte) 'e');
			assertEquals(buf[6], (byte) 's');
			assertEquals(buf[7], (byte) 't');
		} catch (IOException e) {
			fail();
		}
	}
}
