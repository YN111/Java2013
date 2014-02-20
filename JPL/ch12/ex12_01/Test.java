import junit.framework.TestCase;

public class Test extends TestCase {

	public void testLinkedList() {
		LinkedList<Number> list1 = new LinkedList<Number>();
		LinkedList<Number> list2 = new LinkedList<Number>(100);
		list1.setItem(3.0);
		list1.setNext(list2);
		list2.setNext(null);
		assertEquals(list1.getItem(), 3.0);
		assertEquals(list2.getItem(), 100);
		assertEquals(list1.getNext().getNext(), null);
		assertEquals(list1.toString(), "List : 3.0 -> 100");
		list1.getLength();
		assertEquals(list1.getLength(), 2);

		// 以下が追加部分のテスト
		try {
			assertEquals(list1.find(100), list2);
		} catch (ObjectNotFoundException e) {
			fail();
		}
		try {
			list1.find(50); // 見つからない
			fail();
		} catch (ObjectNotFoundException e) {
			assertEquals(e.objName, "50");
		}
	}

}
