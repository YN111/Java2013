// ex02_06 (P43)

import junit.framework.TestCase;

public class Test extends TestCase {
	public void testLinkedList() {
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

	public void testVehicle() {
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		car1.id = car1.nextId++;
		car2.id = car2.nextId++;
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "‚ ‚ ‚ ";
		assertEquals(car1.speed, 0.0);
		assertEquals(car1.direction, 200.0);
		assertEquals(car1.name, "‚ ‚ ‚ ");
		assertEquals(car1.id, 0);
		assertEquals(car2.id, 1);
	}

	public void testMain() {
		LinkedList.main(null);
	}
}