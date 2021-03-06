// ex02_10 (P51)

public class Vehicle {
	static int nextId = 0;
	int id;
	double speed;
	double direction;
	String name;

	Vehicle() {
		id = nextId++;
	}

	Vehicle(String name) {
		id = nextId++;
		this.name = name;
	}

	/**
	 * 今まで使われたIDの最大値を返す
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
