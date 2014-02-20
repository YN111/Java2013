// ex02_02 (P37)

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {
	public void test() {
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		list1.obj = "String1";
		list2.obj = 100;
		list1.next = list2;
		list2.next = null;
		assertEquals(list1.obj, "String1");
		assertEquals(list2.obj, 100);
		assertEquals((list1.next).next, null);
	}
}
