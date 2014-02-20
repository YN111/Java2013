import junit.framework.TestCase;

public class Test extends TestCase {

	public void testScreenColor() {
		assertEquals(new ScreenColor("BLUE").getScreenColor(), "BLUE");
	}

	// 新たに実装した equals()　と hashCode() のみテスト
	public void testColorAttr() {
		ColorAttr test1 = new ColorAttr("Apple", "RED");
		ColorAttr test2 = new ColorAttr("Apple", "RED");
		assertTrue(test1.equals(test2));
		assertEquals(test1.hashCode(), test2.hashCode());

		ColorAttr test3 = new ColorAttr("DEFAULT");
		ColorAttr test4 = new ColorAttr("DEFAULT");
		assertTrue(test3.equals(test4));
		assertEquals(test3.hashCode(), test4.hashCode());

		ColorAttr test5 = new ColorAttr("BLUE", new ScreenColor("BLUE"));
		ColorAttr test6 = new ColorAttr("BLUE", new ScreenColor("BLUE"));
		assertTrue(test5.equals(test6));
		assertEquals(test5.hashCode(), test6.hashCode());
		assertFalse(test1.equals(test6));
	}
}
