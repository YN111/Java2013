// ex01_08 (P14)

import junit.framework.TestCase;

public class PointTest extends TestCase{
	public void testSetPoint() {
        Point point = new Point();
        point.setPoint(100.0, 50.0);
        assertEquals(point.getX(),100.0);
        assertEquals(point.getY(),50.0);
    }
}
