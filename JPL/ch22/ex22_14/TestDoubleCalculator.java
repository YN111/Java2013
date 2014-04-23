import org.junit.Test;

import junit.framework.TestCase;

public class TestDoubleCalculator extends TestCase{
	
	@Test
	public void testSum() {
		assertEquals(DoubleCalculator.sum("err err 10.0"), 10.0);
		assertEquals(DoubleCalculator.sum("10 10.0 10.0f"), 30.0);
		assertEquals(DoubleCalculator.sum("1.0e2 1.0e2"), 200.0);
	}

}
