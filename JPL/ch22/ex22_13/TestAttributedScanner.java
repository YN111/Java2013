import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.Test;

public class TestAttributedScanner extends TestCase {

	@Test
	public void testReadAttrs() {
		StringBuilder in = new StringBuilder();
		in.append("test1=1\n");
		in.append("test2=t2\n");
		Reader reader = new CharArrayReader(in.toString().toCharArray());
		try {
			Attributed attrs = AttributedScanner.readAttrs(reader);
			Iterator<Attr> it = attrs.attrs();
			Attr attr = it.next();
			assertEquals(attr.getName(), "test1");
			assertEquals(attr.getValue(), "1");
			attr = it.next();
			assertEquals(attr.getName(), "test2");
			assertEquals(attr.getValue(), "t2");
			assertFalse(it.hasNext());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReadAttrsIllegalInput() {
		StringBuilder in = new StringBuilder();
		in.append("test1==1\n");
		Reader reader = new CharArrayReader(in.toString().toCharArray());
		try {
			AttributedScanner.readAttrs(reader);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {

		}
	}
}
