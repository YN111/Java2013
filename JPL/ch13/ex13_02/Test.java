import junit.framework.TestCase;

public class Test extends TestCase {

	public void testCharCounter() {
		assertEquals(StringCounter.count("abcdabcdd", "abc"), 2);
		assertEquals(StringCounter.count("aa", "ab"), 0);
		assertEquals(StringCounter.count("aaaaa", "aa"), 4);
	}

}