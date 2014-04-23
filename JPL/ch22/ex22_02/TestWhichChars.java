import org.junit.Test;

import junit.framework.TestCase;

public class TestWhichChars extends TestCase {

	@Test
	public void testWhichChars() {
		String target = new WhichChars("test").toString();
		assertTrue(target.startsWith("["));
		assertTrue(target.endsWith("]"));
		assertTrue(target.contains("t"));
		assertTrue(target.contains("e"));
		assertTrue(target.contains("s"));
		assertEquals(target.length(), 5);
	}
}
