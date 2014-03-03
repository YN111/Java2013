import junit.framework.TestCase;

import org.junit.Test;

public class TestObjectHolder extends TestCase {

	String obj0 = "obj0";
	String obj1 = "obj1";
	String[] obj2 = new String[1];

	@Test
	public void testAddObject() {
		ObjectHolder holder = new ObjectHolder();
		assertEquals(holder.addObject(obj0), "obj0");
		assertEquals(holder.addObject(obj1), "obj1");
		assertEquals(holder.addObject(obj2), "obj2");
	}

	@Test
	public void testGetObject() {
		ObjectHolder holder = new ObjectHolder();
		holder.addObject(obj0);
		holder.addObject(obj1);
		holder.addObject(obj2);
		assertEquals(holder.getObject("obj0"), obj0);
		assertEquals(holder.getObject("obj1"), obj1);
		assertEquals(holder.getObject("obj2"), obj2);
	}

}