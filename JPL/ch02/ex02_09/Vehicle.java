// ex02_09 (P50)

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
	 */
	public int getMaxId() {
		return nextId - 1;
	}
}
