// ex02_05 (P43)

import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void test() {
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "‚ ‚ ‚ ";
		assertEquals(car1.speed, 0.0);
		assertEquals(car1.direction, 200.0);
		assertEquals(car1.name, "‚ ‚ ‚ ");
		car1.id = car1.nextId++;
		car2.id = car2.nextId++;
		assertEquals(car1.id, 0);
		assertEquals(car2.id, 1);
	}

	public void testMain() {
		Vehicle.main(null);
	}
}
