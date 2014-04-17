import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Test;

public class TestMyEnumeration extends TestCase {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		MyEnumeration<Integer> enu = new MyEnumeration<Integer>(list);
		assertTrue(enu.hasMoreElements());
		assertEquals((int) enu.nextElement(), 10);
		assertTrue(enu.hasMoreElements());
		assertEquals((int) enu.nextElement(), 20);
		assertFalse(enu.hasMoreElements());
		try {
			enu.nextElement();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

}
