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
	 * ç°Ç‹Ç≈égÇÌÇÍÇΩIDÇÃç≈ëÂílÇï‘Ç∑
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
