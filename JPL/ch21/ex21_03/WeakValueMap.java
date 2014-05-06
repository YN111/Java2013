import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeakValueMap<K, V> implements Map<K, V> {
	private HashMap<K, WeakReference<V>> mBaseMap = new HashMap<K, WeakReference<V>>();

	public void clear() {
		mBaseMap.clear();
	}

	public boolean containsKey(Object key) {
		return mBaseMap.containsKey(key);
	}

	/**
	 * 指定された要素がマップに含まれている場合にtrueを返します。
	 * 要素が既に回収されている場合は、falseが返ります。
	 * @param value
	 * @return
	 */
	public boolean containsValue(Object value) {
		for (WeakReference<V> weakVal : mBaseMap.values()) {
			if (value.equals(weakVal.get())) {
				return true;
			}
		}
		return false;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Map<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, WeakReference<V>> entry : mBaseMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue().get());
		}
		return map.entrySet();
	}

	/**
	 * 指定されたキーに対応する要素を返します。
	 * 要素が既に回収されている場合は、nullが返ります。
	 * @param key
	 * @return
	 */
	public V get(Object key) {
		WeakReference<V> wr = mBaseMap.get(key);
		return wr == null ? null : wr.get();
	}

	public boolean isEmpty() {
		return mBaseMap.isEmpty();
	}

	public Set<K> keySet() {
		return mBaseMap.keySet();
	}

	public V put(K key, V value) {
		WeakReference<V> result = mBaseMap.put(key, new WeakReference<V>(value));
		return result == null ? null : result.get();
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		for (K key : m.keySet()) {
			mBaseMap.put(key, new WeakReference<V>(m.get(key)));
		}
	}

	/**
	 * 指定された要素をマップから削除し、削除された要素を返します。<br>
	 * 要素が既に回収されている場合はnullが返ります。
	 * @param key
	 * @return
	 */
	public V remove(Object key) {
		return mBaseMap.remove(key).get();
	}

	public int size() {
		return mBaseMap.size();
	}

	public Collection<V> values() {
		Map<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, WeakReference<V>> entry : mBaseMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue().get());
		}
		return map.values();
	}
}
