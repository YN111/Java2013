// ex02_07 (P47)

import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void test() {
		Vehicle car1 = new Vehicle(); // �R���X�g���N�^��ID�̂ݐݒ�
		Vehicle car2 = new Vehicle("������"); // �R���X�g���N�^��ID�Ɩ��O��ݒ�
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "������";
		assertEquals(car1.id, 0);
		assertEquals(car2.id, 1);
		assertEquals(car1.speed, 0.0);
		assertEquals(car1.direction, 200.0);
		assertEquals(car1.name, "������");
		assertEquals(car2.name, "������");
	}

	public void testMain() {
		Vehicle.main(null);
	}
}
