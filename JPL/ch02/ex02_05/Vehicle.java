// ex02_05 (P43)

public class Vehicle {
	static int nextId = 0;
	int id;
	double speed;
	double direction;
	String name;
	
	public static void main(String[] args) {
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		Vehicle car3 = new Vehicle();

		car1.id = car1.nextId++;
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "Alice";

		car2.id = car1.nextId++;
		car2.speed = 60;
		car2.direction = 100;
		car2.name = "Bob";

		car3.id = car1.nextId++;
		car3.speed = 60;
		car3.direction = 100;
		car3.name = "Charlie";

		System.out.println("CAR1:ID=" + car1.id + ", SPEED=" + car1.speed
				+ ", DIRECTION=" + car1.direction + ", NAME=" + car1.name);
		System.out.println("CAR2:ID=" + car2.id + ", SPEED=" + car2.speed
				+ ", DIRECTION=" + car2.direction + ", NAME=" + car2.name);
		System.out.println("CAR3:ID=" + car3.id + ", SPEED=" + car3.speed
				+ ", DIRECTION=" + car3.direction + ", NAME=" + car3.name);
	}
}
