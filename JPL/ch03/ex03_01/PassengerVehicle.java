public class PassengerVehicle extends Vehicle {

	private int seats; // �V�[�g��
	private int people; // �����Ă���l��

	/**
	 * �����Ȃ��R���X�g���N�^
	 */
	PassengerVehicle() {
		super();
	}

	/**
	 * ���O��ݒ肷��R���X�g���N�^
	 */
	PassengerVehicle(String name) {
		super(name);
	}

	/**
	 * �V�[�g�̐��̃Z�b�^
	 */
	public void setSeatsNum(int num) {
		if (num > 0) {
			seats = num;
		}
	}

	/**
	 * ���ݍ����Ă���l���̃Z�b�^
	 */
	public void setPeopleNum(int num) {
		if (num >= 0) {
			people = num;
		}
	}

	/**
	 * �V�[�g�̐���Ԃ�
	 */
	public int getSeatsNum() {
		return seats;
	}

	/**
	 * ���ݍ����Ă���l����Ԃ�
	 */
	public int getPeopleNum() {
		return people;
	}

	/**
	 * �����o�͂���
	 */
	@Override
	public String toString() {
		String info = super.toString() + "  �yThis car has " + getSeatsNum()
				+ " seats and riding " + getPeopleNum() + " people now�z";
		return info;
	}

	/**
	 * main���\�b�h
	 */
	public static void main(String[] args) {
		PassengerVehicle car1 = new PassengerVehicle("Tom");
		car1.setSpeed(100);
		car1.setDirection(180);
		car1.setSeatsNum(5);
		car1.setPeopleNum(3);

		PassengerVehicle car2 = new PassengerVehicle("Ken");
		car2.setSpeed(50);
		car2.setDirection(90);
		car2.setSeatsNum(5);
		car2.setPeopleNum(2);

		System.out.println(car1);
		System.out.println(car2);
	}
}
