// ex02_06 (P43)

public class LinkedList {
	Object obj;
	LinkedList next;

	public static void main(String[] args) {
		// ��蕨�̃C���X�^���X���`
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		Vehicle car3 = new Vehicle();

		// ��蕨�̏���ݒ�
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

		// ���X�g�̃C���X�^���X���`
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		LinkedList list3 = new LinkedList();

		// �I�u�W�F�N�g����蕨�ɐݒ�
		list1.obj = car1;
		list2.obj = car2;
		list3.obj = car3;

		// next��ݒ�
		list1.next = list2;
		list2.next = list3;
		list3.next = null;
	}
}
