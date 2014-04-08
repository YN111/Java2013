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

	@Test
	public void testShutdown() {
		Key key1 = new Key();
		Key key2 = new Key();
		ResourceManager rm = new ResourceManager();
		Resource r1 = rm.getResource(key1.getKey());
		Resource r2 = rm.getResource(key2.getKey());
		r1.use(key1.getKey(), new Object[0]);
		r2.use(key2.getKey(), new Object[0]);
		rm.shutdown();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(rm.getState(), ResourceManager.State.RUN); // まだ生きてる

		// r1を解放
		key1 = null;
		System.gc();
		try {
			Thread.sleep(100); // 刈り取りスレッドが実行されるのを待機
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(rm.getState(), ResourceManager.State.RUN); // まだ生きてる

		// r2を解放
		key2 = null;
		System.gc();
		try {
			Thread.sleep(100); // 刈り取りスレッドが実行されるのを待機
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(rm.getState(), ResourceManager.State.SHUTDOWN); // 終了

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
