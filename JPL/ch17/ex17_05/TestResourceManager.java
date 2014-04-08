import java.lang.reflect.Field;

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
	public void testReleaseDirect() {
		Key key = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r = rm.getResource(key.getKey());
		r.release();
		assertFalse(resourceNeedsRelease(r)); // rが解放されているか
	}

	@Test
	public void testReleaseByGetResource() {
		Key key1 = new Key();
		Key key2 = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r1 = rm.getResource(key1.getKey());
		Resource r2 = rm.getResource(key2.getKey());
		r1.use(key1.getKey(), new Object[0]);
		r2.use(key2.getKey(), new Object[0]);
		key1 = null;
		key2 = null;
		System.gc();

		Key newKey = new Key();
		// GCが走るまで待機
		while (rm.refs.size() > 1) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			rm.getResource(newKey.getKey()); // リソースが回収される
		}

		assertFalse(resourceNeedsRelease(r1)); // r1が解放されているか
		assertFalse(resourceNeedsRelease(r2)); // r2が解放されているか
	}

	@Test
	public void testReleaseByShutdown() {
		Key key1 = new Key();
		Key key2 = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r1 = rm.getResource(key1.getKey());
		Resource r2 = rm.getResource(key2.getKey());
		r1.use(key1.getKey(), new Object[0]);
		r2.use(key2.getKey(), new Object[0]);
		rm.shutdown();
		System.gc();
		assertEquals(rm.refs.size(), 0); // マップが空になっているか
		assertFalse(resourceNeedsRelease(r1)); // r1が解放されているか
		assertFalse(resourceNeedsRelease(r2)); // r2が解放されているか
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

	/**
	 * ResourceクラスのneedsReleaseフィールドの値を返します
	 * @return
	 */
	private boolean resourceNeedsRelease(Resource res) {
		Field f = null;
		try {
			f = res.getClass().getDeclaredField("needsRelease");
		} catch (Exception e) {
			fail();
		}

		boolean needsRelease = true;
		try {
			f.setAccessible(true);
			needsRelease = f.getBoolean(res);
		} catch (Exception e) {
			fail();
		}

		return needsRelease;
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
