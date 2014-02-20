import junit.framework.TestCase;

public class Test extends TestCase {

	public void testBattery() {
		// �R���X�g���N�^�̃e�X�g
		try {
			Battery errorBattery = new Battery(-1); // �͈͊O
			super.fail(); // �G���[���������Ȃ����߃e�X�g���s
		} catch (AssertionError e) {
		}
		Battery battery = new Battery(100);

		// add���\�b�h�̃e�X�g
		try {
			battery.add(-1); // �͈͊O
			super.fail();
		} catch (AssertionError e) {
		}
		assertEquals(battery.empty(), true); // ������Ԃł̓o�b�e���[�͋�
		battery.add(1);
		assertEquals(battery.empty(), false);
		battery.add(100); // ����l�𒴂��邽��power=100�ƂȂ�

		// remove���\�b�h�̃e�X�g
		try {
			battery.remove(-1); // �͈͊O
			super.fail();
		} catch (AssertionError e) {
		}
		battery.remove(99);
		assertEquals(battery.empty(), false);
		battery.remove(1);
		assertEquals(battery.empty(), true); // �o�b�e���[����ɂȂ�
	}

	public void testGasTank() {
		// �R���X�g���N�^�̃e�X�g
		try {
			GasTank errorGasTank = new GasTank(-1); // �͈͊O
			super.fail(); // �G���[���������Ȃ����߃e�X�g���s
		} catch (AssertionError e) {
		}
		GasTank gasTank = new GasTank(40);

		// add���\�b�h�̃e�X�g
		try {
			gasTank.add(-1); // �͈͊O
			super.fail();
		} catch (AssertionError e) {
		}
		assertEquals(gasTank.empty(), true); // ������Ԃł̓o�b�e���[�͋�
		gasTank.add(1);
		assertEquals(gasTank.empty(), false);
		gasTank.add(40); // ����l�𒴂��邽��power=100�ƂȂ�

		// remove���\�b�h�̃e�X�g
		try {
			gasTank.remove(-1); // �͈͊O
			super.fail();
		} catch (AssertionError e) {
		}
		gasTank.remove(39);
		assertEquals(gasTank.empty(), false);
		gasTank.remove(1);
		assertEquals(gasTank.empty(), true); // �o�b�e���[����ɂȂ�
	}

	public void testVehicle() {
		Battery battery = new Battery(100);
		Vehicle car1 = new Vehicle(battery); // �R���X�g���N�^�œ��͌��̃o�b�e���[��ݒ�
		Vehicle car2 = new Vehicle(battery, "������"); // �R���X�g���N�^�œ��͌��̃K�X�Ɩ��O��ݒ�
		car1.start();
		assertEquals(car1.getStartFlag(), false); // �K�X��
		battery.add(50); // �R����[
		car1.start();
		assertEquals(car1.getStartFlag(), true); // �N���\�ɂȂ�

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
		PassengerVehicle car3 = new PassengerVehicle(new Battery(40), "������");
		PassengerVehicle car4 = new PassengerVehicle(new GasTank(100));
		car3.setSeatsNum(3);
		car3.setSeatsNum(0); // �͈͊O�F�Z�b�g����Ȃ�
		car3.setPeopleNum(2);
		car3.setPeopleNum(-1); // �͈͊O�F�Z�b�g����Ȃ�
		assertEquals(car3.getSeatsNum(), 3);
		assertEquals(car3.getPeopleNum(), 2);
	}
}
