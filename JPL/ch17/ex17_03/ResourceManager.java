import java.lang.ref.*;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	final Thread reaper;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		reaper = new ReaperThread();
		reaper.start();

		// リソースの初期化
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}

		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	class ReaperThread extends Thread {
		public void run() {
			while (true) {
				try {
					Reference<?> ref = queue.remove();
					Resource res = null;
					synchronized (ResourceManager.this) {
						res = refs.get(ref);
						refs.remove(ref);
					}
					res.release();
					ref.clear();
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	private static class ResourceImpl implements Resource {
		Object keyObj;
		boolean needsRelease = false;

		ResourceImpl(Object key) {
			keyObj = key;

			// 外部リソースの設定

			needsRelease = true;
		}

		@Override
		public void use(Object key, Object... args) {
			if (key == null) {
				throw new IllegalStateException("key is null");
			}

			if (!key.equals(keyObj)) {
				throw new IllegalArgumentException("wrong key");
			}

			// リソースの利用

		}

		@Override
		public void release() {
			if (needsRelease) {
				needsRelease = false;

				// リソースの解放

			}
		}
	}
}
