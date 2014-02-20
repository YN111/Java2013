import junit.framework.TestCase;

public class LinkedListTest extends TestCase {
	public void test() {
		SimpleObject obj1 = new SimpleObject(1, "old1");
		SimpleObject obj2 = new SimpleObject(2, "old2");
		LinkedList list1 = new LinkedList(obj1);
		LinkedList list2 = new LinkedList(obj2);
		list1.setNext(list2);
		list2.setNext(null);

		LinkedList clone = list1.clone(); // �N���[�����쐬
		assertSame(list1.getObj(), clone.getObj());
		assertSame(list1.getNext().getObj(), clone.getNext().getObj());

		// �Q�Ƃ��Ă���I�u�W�F�N�g�ɑ΂���ύX
		obj1.setData(1, "new1");
		obj2.setData(2, "new2");
		assertSame(list1.getObj(), clone.getObj());
		assertSame(list1.getNext().getObj(), clone.getNext().getObj());

		// ���X�g�ɑ΂���ύX
		list1.setObj(new SimpleObject(3, "3"));
		list2.setObj(new SimpleObject(4, "4"));
		assertNotSame(list1.getObj(), clone.getObj());
		assertNotSame(list1.getNext().getObj(), clone.getNext().getObj());
	}
}

class SimpleObject {
	private int data1;
	private String data2;

	SimpleObject(int data1, String data2) {
		this.data1 = data1;
		this.data2 = data2;
	}

	public void setData(int data1, String data2) {
		this.data1 = data1;
		this.data2 = data2;
	}

	// �o�͗p
	public String toString() {
		return (data1 + "," + data2);
	}
}
