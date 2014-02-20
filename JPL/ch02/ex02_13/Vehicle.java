// ex02_13 (P58)
// nextId��id�͕ύX��F�߂Ȃ����߃Z�b�^�݂͐��Ȃ�

public class Vehicle {
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

	public String toString() {
		String info = "CAR1:ID=" + id + ", SPEED=" + speed + ", DIRECTION="
				+ direction + ", NAME=" + name;
		return info;
	}
}
