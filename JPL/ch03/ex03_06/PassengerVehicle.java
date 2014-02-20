public class PassengerVehicle extends Vehicle {

	private int seats; // �V�[�g��
	private int people; // �����Ă���l��

	/**
	 * ���͌��̂ݐݒ肷��R���X�g���N�^
	 */
	PassengerVehicle(EnergySource energy) {
		super(energy);
	}

	/**
	 * ���͌��Ɩ��O��ݒ肷��R���X�g���N�^
	 */
	PassengerVehicle(EnergySource energy, String name) {
		super(energy, name);
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
}
