// ex02_07 (P47)

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
	
	public static void main(String[] args) {
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle("Bob");
		Vehicle car3 = new Vehicle("Charlie");

		car1.speed = 0;
		car1.direction = 200;
		car1.name = "Alice";

		car2.speed = 60;
		car2.direction = 100;

		car3.speed = 60;
		car3.direction = 100;

		System.out.println("CAR1:ID=" + car1.id + ", SPEED=" + car1.speed
				+ ", DIRECTION=" + car1.direction + ", NAME=" + car1.name);
		System.out.println("CAR2:ID=" + car2.id + ", SPEED=" + car2.speed
				+ ", DIRECTION=" + car2.direction + ", NAME=" + car2.name);
		System.out.println("CAR3:ID=" + car3.id + ", SPEED=" + car3.speed
				+ ", DIRECTION=" + car3.direction + ", NAME=" + car3.name);
	}
}
