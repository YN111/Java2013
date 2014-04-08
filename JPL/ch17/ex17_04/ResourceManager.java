import java.lang.ref.*;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

	enum State {
		INITIAL, RUN, SHUTDOWN;
	}

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	final Thread reaper;
	boolean shutdown = false;
	private State state = State.INITIAL;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		reaper = new ReaperThread();
		reaper.start();
		state = State.RUN;

		// リソースの初期化
		
	}

	/**
	 * 現在の状態を返します
	 * @return
	 */
	public State getState() {
		return state;
	}

	/**
	 * ResourceManagerを終了します
	 */
	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
		}
	}

	/**
	 * リソースを取得します
	 * @param key 取得用のキー
	 * @return
	 */
	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}

		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	/**
	 * 刈り取りスレッド
	 */
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

					// shutdownが呼ばれた後に全てのリソースが解放されたら終了
					if (shutdown && refs.size() == 0) {
						state = ResourceManager.State.SHUTDOWN;
						break;
					}

				} catch (InterruptedException e) {
				}
			}
		}
	}

	private static class ResourceImpl implements Resource {
		WeakReference<Object> keyRef;
		boolean needsRelease = false;

		ResourceImpl(Object key) {
			keyRef = new WeakReference<Object>(key);

			// 外部リソースの設定

			needsRelease = true;
		}

		/**
		 * リソースを取得します<br>
		 * キーは、ResourceManager#getResourceの引数に指定したものを指定してください<br>
		 * 異なるキーを入力するとIllegalArgumentExceptionがスローされます<br>
		 */
		@Override
		public void use(Object key, Object... args) {
			if (key == null || !key.equals(keyRef.get())) {
				throw new IllegalArgumentException("wrong key");
			}

			// リソースの利用

		}

		/**
		 * リソースを解放します
		 */
		@Override
		public void release() {
			if (needsRelease) {
				needsRelease = false;

				// リソースの解放

				keyRef.clear();
			}
		}
	}
}
