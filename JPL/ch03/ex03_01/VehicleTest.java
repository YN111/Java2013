import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void testVehicle() {
		Vehicle car1 = new Vehicle(); // �R���X�g���N�^��ID�̂ݐݒ�
		Vehicle car2 = new Vehicle("������"); // �R���X�g���N�^��ID�Ɩ��O��ݒ�
		car1.setSpeed(0);
		car1.setSpeed(-1); // �͈͊O�F�Z�b�g����Ȃ�
		car1.setDirection(200);
		car1.setDirection(-1); // �͈͊O�F�Z�b�g����Ȃ�
		car1.setDirection(361); // �͈͊O�F�Z�b�g����Ȃ�
		car1.setName("������");
		assertEquals(car1.getId(), 0);
		assertEquals(car2.getId(), 1);
		assertEquals(car1.getSpeed(), 0.0);
		assertEquals(car1.getDirection(), 200.0);
		assertEquals(car1.getName(), "������");
		assertEquals(car1.getMaxId(), 1);
		assertEquals(car2.getName(), "������");
		car1.changeSpeed(30); // �X�s�[�h�̕ύX
		assertEquals(car1.getSpeed(), 30.0);
		car1.stop(); // ��~
		assertEquals(car1.getSpeed(), 0.0);
		car1.turn(100.0);
		assertEquals(car1.getDirection(), 300.0);
		car1.turn(Vehicle.TURN_RIGHT);
		assertEquals(car1.getDirection(), 30.0);
		car1.turn(Vehicle.TURN_LEFT);
		assertEquals(car1.getDirection(), 300.0);
		car1.turn("INVALID_VALUE");
		assertEquals(car1.getDirection(), 300.0);
	}

	public void testPassengerVehicle() {
		PassengerVehicle car3 = new PassengerVehicle("������"); // �R���X�g���N�^��ID�Ɩ��O��ݒ�
		PassengerVehicle car4 = new PassengerVehicle(); // �R���X�g���N�^��ID�̂ݐݒ�
		car3.setSeatsNum(3);
		car3.setSeatsNum(0); // �͈͊O�F�Z�b�g����Ȃ�
		car3.setPeopleNum(2);
		car3.setPeopleNum(-1); // �͈͊O�F�Z�b�g����Ȃ�
		assertEquals(car3.getSeatsNum(), 3);
		assertEquals(car3.getPeopleNum(), 2);
	}
}
