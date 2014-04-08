import java.lang.ref.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * リソースを管理するクラスです。<br>
 * getResourceメソッドでリソースを取得できます。最初にリソースを取得する際に、任意のキーを設定する必要があります。<br>
 * このキーを用いて再びgetResourceメソッドを呼ぶと、初回と同じResourceオブジェクトを取得できます。<br>
 * また、getResourceメソッドでは、既に設定されているキーオブジェクトが到達可能か確認します。<br>
 * 到達不可能なキーオブジェクトが存在した場合は、対応するリソースを回収します。<br>
 * shutdownメソッドでは、キーの到達可否に関わらず、全てのリソースを回収します。
 */
public final class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();

		// リソースの初期化

	}

	/**
	 * 全てのリソースを解放します
	 */
	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;

			Set<Reference<?>> keySet = refs.keySet();
			for (Reference<?> ref : keySet) {
				Resource res = (Resource) refs.get(ref);
				res.release();
			}
			refs.clear();
		}
	}

	/**
	 * リソースを取得するメソッドです<br>
	 * キーが既に設定されている場合は、対応するリソースオブジェクトを返します<br>
	 * キーが到達不可能になったリソースがあれば解放します
	 * @param key 取得用のキー
	 * @return
	 */
	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}

		pollQueue(); // 不要なリソースを解放

		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	/**
	 * キーが到達不可能になったリソースがあれば解放します
	 */
	private void pollQueue() {
		Reference<?> garbageRef;
		while ((garbageRef = queue.poll()) != null) {
			Resource res = null;
			synchronized (ResourceManager.this) {
				res = refs.get(garbageRef);
				refs.remove(garbageRef);
			}
			res.release();
			garbageRef.clear();
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
