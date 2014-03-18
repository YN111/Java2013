import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;

public class TestTranslateByte extends TestCase {

	@Test
	public void testReadNoArgs() {
		InputStream is = new ByteArrayInputStream("test".getBytes());
		TranslateByte tb = new TranslateByte(is, (byte) 't', (byte) 'T');
		try {
			assertEquals(tb.read(), 'T');
			assertEquals(tb.read(), 'e');
			assertEquals(tb.read(), 's');
			assertEquals(tb.read(), 'T');
			assertEquals(tb.read(), -1);
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testReadOneArg() {
		InputStream is = new ByteArrayInputStream("test".getBytes());
		TranslateByte tb = new TranslateByte(is, (byte) 't', (byte) 'T');
		byte[] buf = new byte[4];
		try {
			int result = tb.read(buf);
			assertEquals(result, 4);
			assertEquals(buf[0], 'T');
			assertEquals(buf[1], 'e');
			assertEquals(buf[2], 's');
			assertEquals(buf[3], 'T');
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testReadThreeArgs() {
		InputStream is = new ByteArrayInputStream("test".getBytes());
		TranslateByte tb = new TranslateByte(is, (byte) 't', (byte) 'T');
		byte[] buf = new byte[10];
		try {
			int result = tb.read(buf, 5, 4);
			assertEquals(result, 4);
			assertEquals(buf[5], 'T');
			assertEquals(buf[6], 'e');
			assertEquals(buf[7], 's');
			assertEquals(buf[8], 'T');
		} catch (IOException e) {
			fail();
		}
	}
}
