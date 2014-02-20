import junit.framework.TestCase;

public class Test extends TestCase {

	public void testAttr() {
		Attr<String> test1 = new Attr<String>("name1", "value1");
		Attr<String> test2 = new Attr<String>("name2");
		test2.setValue("value2");
		assertEquals(test2.setValue("value2_new"), "value2");
		assertEquals(test1.getName(), "name1");
		assertEquals(test1.getValue(), "value1");
		assertEquals(test2.getName(), "name2");
		assertEquals(test2.getValue(), "value2_new");
		assertEquals(test1.toString(), "name1='value1'");
	}

}
