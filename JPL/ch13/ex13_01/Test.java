import junit.framework.TestCase;

public class Test extends TestCase {

	public void testCharCounter() {
		assertEquals(CharCounter.count("abcdabcdd", 'a'), 2);
		assertEquals(CharCounter.count("", 'a'), 0);
	}

}