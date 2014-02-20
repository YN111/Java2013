// ex02_18 (P64)

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

	Vehicle() {
		id = nextId++;
	}

	Vehicle(String name) {
		id = nextId++;
		this.name = name;
	}

	public void setSpeed(double speed) {
		if (speed >= 0) {
			this.speed = speed;
		}
	}

	public void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDirection() {
		return direction;
	}

	public String getName() {
		return name;
	}

	/**
	 * ���܂Ŏg��ꂽID�̍ő�l��Ԃ�
	 * @return
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * �X�s�[�h��0�ɐݒ�
	 */
	public void stop() {
		speed = 0;
	}

	/**
	 * �p�x�w��ɂ������]��
	 * @param angle
	 */
	public void turn(double angle) {
		setDirection((getDirection() + angle) % 360);
	}

	/**
	 * TURN_LEFT or TURN_RIGHT �ɂ������]��
	 * @param angle
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

	public String toString() {
		String info = "CAR1:ID=" + id + ", SPEED=" + speed + ", DIRECTION="
				+ direction + ", NAME=" + name;
		return info;
	}

	public static void main(String[] args) {
		if (args != null) {
			for (String str : args) {
				Vehicle car = new Vehicle(str);
				System.out.println(car.toString());
			}
		} else {
			System.out.println("���L�҂̖��O�������ɐݒ肵�Ă�������");
		}
	}
}
