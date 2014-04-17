import java.lang.reflect.Field;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

public class TestMyStack extends TestCase {

	@Test
	public void testStack() {
		MyStack stack = new MyStack();
		Field f = null;
		try {
			f = MyStack.class.getDeclaredField("stack");
			f.setAccessible(true);
			@SuppressWarnings("unchecked")
			ArrayList<Object> list = (ArrayList<Object>) f.get(stack);

			assertTrue(stack.isEmpty());
			stack.push(0);
			assertFalse(stack.isEmpty());
			assertEquals(list.size(), 1);
			stack.push(1);
			assertEquals(list.size(), 2);
			stack.push(2);
			assertEquals(stack.search(1), 2);
			assertEquals(stack.search(4), -1);
			assertEquals(list.size(), 3);
			assertEquals(stack.pop(), 2);
			assertEquals(stack.peek(), 0);
			assertEquals(list.size(), 2);
			assertEquals(stack.pop(), 1);
			assertEquals(list.size(), 1);
			assertEquals(stack.pop(), 0);
			assertEquals(list.size(), 0);
		} catch (Exception e) {
			fail();
		}
	}
}