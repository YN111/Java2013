import junit.framework.TestCase;

public class LinkedListTest extends TestCase {
	public void test() {
		SimpleObject obj1 = new SimpleObject(1, "old1");
		SimpleObject obj2 = new SimpleObject(2, "old2");
		LinkedListImpl list1 = new LinkedListImpl(obj1);
		LinkedListImpl list2 = new LinkedListImpl(obj2);
		list1.setNext(list2);
		list2.setNext(null);

		LinkedListImpl clone = list1.clone(); // クローンを作成
		assertSame(list1.getObj(), clone.getObj());
		assertSame(list1.getNext().getObj(), clone.getNext().getObj());

		// 参照しているオブジェクトに対する変更
		obj1.setData(1, "new1");
		obj2.setData(2, "new2");
		assertSame(list1.getObj(), clone.getObj());
		assertSame(list1.getNext().getObj(), clone.getNext().getObj());

		// リストに対する変更
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

	// 出力用
	public String toString() {
		return (data1 + "," + data2);
	}
}
