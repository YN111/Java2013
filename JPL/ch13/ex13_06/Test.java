import junit.framework.TestCase;

public class Test extends TestCase {

	public void testNumberFormatter() {
		assertEquals(NumberFormatter.insertComma("1", ",", 2), "1");
		assertEquals(NumberFormatter.insertComma("1000", ",", 2), "10,00");
		assertEquals(NumberFormatter.insertComma("10000", ",", 2), "1,00,00");

		try {
			NumberFormatter.insertComma("a", ",", 2);
			fail();
		} catch (NumberFormatException e) {
		}
	}
}