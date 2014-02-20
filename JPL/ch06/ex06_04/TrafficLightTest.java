import junit.framework.TestCase;

public class TrafficLightTest extends TestCase{
	public void testTrafficLight() {
		Color red = TrafficLight.RED.getColor();
		assertEquals(red.getName(), "Red");
		assertEquals(red.getRgb()[0], 255);
		assertEquals(red.getRgb()[1], 0);
		assertEquals(red.getRgb()[2], 0);
	}
}
