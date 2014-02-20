// ex02_06 (P43)

public class LinkedList {
	Object obj;
	LinkedList next;

	public static void main(String[] args) {
		// 乗り物のインスタンスを定義
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		Vehicle car3 = new Vehicle();

		// 乗り物の情報を設定
		car1.id = car1.nextId++;
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "PRIUS";

		car2.id = car1.nextId++;
		car2.speed = 60;
		car2.direction = 100;
		car2.name = "BENZ";

		car3.id = car1.nextId++;
		car3.speed = 60;
		car3.direction = 100;
		car3.name = "HIJET";

		// リストのインスタンスを定義
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		LinkedList list3 = new LinkedList();

		// オブジェクトを乗り物に設定
		list1.obj = car1;
		list2.obj = car2;
		list3.obj = car3;

		// nextを設定
		list1.next = list2;
		list2.next = list3;
		list3.next = null;
	}
}
