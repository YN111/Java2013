import junit.framework.TestCase;

public class Test extends TestCase {

	public void testMyString() {
		String[] arr = new String[3];
		arr = MyString.delimitedString("start<abcd><efgh><ijkl>end", '<', '>');
		assertEquals(arr[0], "<abcd>");
		assertEquals(arr[1], "<efgh>");
		assertEquals(arr[2], "<ijkl>");
		arr = MyString.delimitedString("start<abcd", '<', '>');
		assertEquals(arr[0], "<abcd");
	}

}