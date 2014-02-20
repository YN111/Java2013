import junit.framework.TestCase;

public class TrafficLightTest extends TestCase{
	public void testTrafficLight() {
		Color red = TrafficLight.RED.getColor();
		assertEquals(red.getName(), "Red");
		assertEquals(red.getRgb()[0], 255);
		assertEquals(red.getRgb()[1], 0);
		assertEquals(red.getRgb()[2], 0);
		
		Color yellow = TrafficLight.YELLOW.getColor();
		assertEquals(yellow.getName(), "Yellow");
		assertEquals(yellow.getRgb()[0], 255);
		assertEquals(yellow.getRgb()[1], 255);
		assertEquals(yellow.getRgb()[2], 0);
		
		Color greeen = TrafficLight.GREEN.getColor();
		assertEquals(greeen.getName(), "Green");
		assertEquals(greeen.getRgb()[0], 0);
		assertEquals(greeen.getRgb()[1], 255);
		assertEquals(greeen.getRgb()[2], 0);
	}
}
