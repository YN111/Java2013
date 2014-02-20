// ex02_11 (P51)

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {
	public void test() {
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList(100);
		list1.obj = "String1";
		list1.next = list2;
		list2.next = null;
		assertEquals(list1.obj, "String1");
		assertEquals(list2.obj, 100);
		assertEquals((list1.next).next, null);
		//System.out.println(list1.toString());
		assertEquals(list1.toString(),"List : String1 -> 100");
	}
}
