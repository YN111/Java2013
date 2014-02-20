public class Vehicle implements Cloneable{
	static final String TURN_LEFT = "left";
	static final String TURN_RIGHT = "right";
	private static final int TURN_LEFT_ANGLE = 270; // ���܎��̉�]�p�x
	private static final int TURN_RIGHT_ANGLE = 90; // �E�܎��̉�]�p�x
	private static int nextId = 0;
	private int id;
	private double speed;
	private double direction;
	private String name;
	private boolean startFlag = false; // ���͌�����łȂ����Ƃ�ۏ؂���
	private final EnergySource energy; // ���͌�

	/**
	 * ���͌��̂ݐݒ肷��R���X�g���N�^
	 */
	Vehicle(EnergySource energy) {
		id = nextId++;
		this.energy = energy;
	}

	/**
	 * ���͌��Ɩ��O��ݒ肷��R���X�g���N�^
	 */
	Vehicle(EnergySource energy, String name) {
		id = nextId++;
		this.energy = energy;
		this.name = name;
	}
	
	/**
	 * ID�̃Z�b�^�iclone���쐬���鎞�Ɏg�p�j
	 */
	public void setId() {
		id = nextId++;
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
	public void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	/**
	 * ���O�̃Z�b�^
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ID��Ԃ�
	 */
	public int getId() {
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
	public double getDirection() {
		return direction;
	}

	/**
	 * ���O��Ԃ�
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���܂Ŏg��ꂽID�̍ő�l��Ԃ�
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	/**
	 * ����\�t���O�̒l��Ԃ�
	 */
	public boolean getStartFlag() {
		return startFlag;
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
	 * ���͌����󂩃`�F�b�N����
	 */
	public void start() {
		if (energy.empty()) {
			startFlag = false; // �K�X��
		} else {
			startFlag = true; // ����\
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
	
	/**
	 * �N���[�����쐬����
	 * ID�̂ݐV���Ȕԍ�������U��
	 */
	public Vehicle clone() throws CloneNotSupportedException {
		Vehicle clone = (Vehicle) super.clone();
		clone.setId();
		return clone;
	}
}
