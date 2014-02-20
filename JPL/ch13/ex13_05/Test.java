import junit.framework.TestCase;

public class Test extends TestCase {

	public void testNumberFormatter() {
		assertEquals(NumberFormatter.insertComma("1"), "1");
		assertEquals(NumberFormatter.insertComma("1000"), "1,000");
		assertEquals(NumberFormatter.insertComma("100000"), "100,000");

		try {
			NumberFormatter.insertComma("a");
			fail();
		} catch (NumberFormatException e) {
		}
	}
}