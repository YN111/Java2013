/*
 * �A�N�Z�b�T�̊e���\�b�h��final�Ƃ���
 * <���R>
 * (1) �A�N�Z�b�T�͒l�̐ݒ�܂��͎擾���ړI�ł��邽�߁A�T�u�N���X�Ŏ�����ύX����K�v���Ȃ�
 * (2) �g�p�p�x�������A�C�����C���W�J�ɂ�鏈���̍������̃����b�g���傫��
 * 
 * �������A�ȉ��̃��\�b�h��final�Ƃ��Ȃ�
 * �� setSpeed�AgetSpeed���\�b�h
 *     �� ��蕨�̎�ނɂ�葬�x�̏�����ω����� �\��������A�T�u�N���X�Ŏ������ύX�����\�������邽��
 * �� getMaxId���\�b�h
 *     �� �o�^���������ꂽID���ė��p����ȂǁAID�̐U������T�u�N���X�ŕύX�����\�������邽��
 * 
 */

public class Vehicle {
	static final String TURN_LEFT = "left";
	static final String TURN_RIGHT = "right";
	private static final int TURN_LEFT_ANGLE = 270; // ���܎��̉�]�p�x
	private static final int TURN_RIGHT_ANGLE = 90; // �E�܎��̉�]�p�x
	private static int nextId = 0;
	private int id;
	private double speed;
	private double direction;
	private String name;

	/**
	 * �����Ȃ��R���X�g���N�^
	 */
	Vehicle() {
		id = nextId++;
	}

	/**
	 * ���O��ݒ肷��R���X�g���N�^
	 */
	Vehicle(String name) {
		id = nextId++;
		this.name = name;
	}

	/**
	 * �X�s�[�h�̃Z�b�^
	 */
	public void setSpeed(double speed) {
		if (speed >= 0) {
			this.speed = speed;
		}
	}

	/**
	 * �����̃Z�b�^
	 */
	public final void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	/**
	 * ���O�̃Z�b�^
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * ID��Ԃ�
	 */
	public final int getId() {
		return id;
	}

	/**
	 * �X�s�[�h��Ԃ�
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * ������Ԃ�
	 */
	public final double getDirection() {
		return direction;
	}

	/**
	 * ���O��Ԃ�
	 */
	public final String getName() {
		return name;
	}

	/**
	 * ���܂Ŏg��ꂽID�̍ő�l��Ԃ�
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	/**
	 * �X�s�[�h��ύX����
	 */
	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * �X�s�[�h�����Z�b�g�i��~�j����
	 */
	public void stop() {
		speed = 0;
	}

	/**
	 * �p�x���w�肵�����]������
	 */
	public void turn(double angle) {
		setDirection((getDirection() + angle) % 360);
	}

	/**
	 * TURN_LEFT �܂��� TURN_RIGHT ���w�肵�����]������
	 */
	public void turn(String angle) {
		if (angle.equals("left")) {
			setDirection((getDirection() + TURN_LEFT_ANGLE) % 360);
		} else if (angle.equals("right")) {
			setDirection((getDirection() + TURN_RIGHT_ANGLE) % 360);
		} else {
			return;
		}
	}

	/**
	 * �����o�͂���
	 */
	public String toString() {
		String info = "VEHICLE INFO: ID=" + id + ", SPEED=" + speed
				+ ", DIRECTION=" + direction + ", NAME=" + name;
		return info;
	}
}
