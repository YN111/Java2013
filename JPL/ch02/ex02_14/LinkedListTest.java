// ex02_14 (P58)

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {
	public void test() {
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList(100);
		list1.setObj("String1");
		list1.setNext(list2);
		list2.setNext(null);
		assertEquals(list1.getObj(), "String1");
		assertEquals(list2.getObj(), 100);
		assertEquals(list1.getNext().getNext(), null);
		//System.out.println(list1.toString());
		assertEquals(list1.toString(),"List : String1 -> 100");
		
	}
}
