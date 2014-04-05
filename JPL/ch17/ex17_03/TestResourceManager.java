import junit.framework.TestCase;

import org.junit.Test;

public class TestResourceManager extends TestCase {

	@Test
	public void testCorrectKey() {
		Object key = "correct";
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key);
		try {
			r.use(key, new Object[0]);
		} catch (Exception e) {
			fail();
		}
		rm.shutdown();
	}

	@Test
	public void testReleaseKey() {
		Object key = "correct";
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key);
		key = null;
		System.gc();
		try {
			r.use(key, new Object[0]);
			fail();
		} catch (IllegalStateException e) {
		} catch (IllegalArgumentException e) {
			fail();
		}
		rm.shutdown();
	}

	@Test
	public void testWrongKey() {
		Object correctKey = "correct";
		Object wrongKey = "wrong";
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(correctKey);
		try {
			r.use(wrongKey, new Object[0]);
			fail();
		} catch (IllegalStateException e) {
			fail();
		} catch (IllegalArgumentException e) {
		}
		rm.shutdown();
	}
}
