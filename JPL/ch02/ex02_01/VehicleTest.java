// ex02_01 (P37)

import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void test() {
		Vehicle car = new Vehicle();
		car.speed = 0;
		car.direction = 200;
		car.name = "‚ ‚ ‚ ";
		assertEquals(car.speed, 0.0);
		assertEquals(car.direction, 200.0);
		assertEquals(car.name, "‚ ‚ ‚ ");
	}
}
