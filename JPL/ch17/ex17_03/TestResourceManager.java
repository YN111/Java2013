import junit.framework.TestCase;

import org.junit.Test;

public class TestResourceManager extends TestCase {

	@Test
	public void testCorrectKey() {
		Key key = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key.getKey());
		try {
			r.use(key.getKey(), new Object[0]);
		} catch (IllegalArgumentException e) {
			fail();
		}
		rm.shutdown();
	}

	@Test
	public void testRelease() {
		Key key = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key.getKey());
		r.release();
		try {
			r.use(key.getKey(), new Object[0]);
			fail();
		} catch (IllegalArgumentException e) {
		}
		rm.shutdown();
	}

	@Test
	public void testReleaseByKey() {
		Key key = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key.getKey());
		r.use(key.getKey(), new Object[0]);
		key = null;
		System.gc();
		try {
			Thread.sleep(100); // 刈り取りスレッドが実行されるのを待機
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(rm.refs.size(), 0);
		rm.shutdown();
	}

	@Test
	public void testWrongKey() {
		Key correctKey = new Key();
		Key wrongKey = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(correctKey);
		try {
			r.use(wrongKey, new Object[0]);
			fail();
		} catch (IllegalArgumentException e) {
		}
		rm.shutdown();
	}
}

/**
 * キー発行用のクラス
 */
class Key {
	private Object obj = Math.random();

	public Object getKey() {
		return obj;
	}
}
