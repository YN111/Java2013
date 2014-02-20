public interface Map<K, V> {
	
	void clear();

	boolean containsKey(Object key);

	boolean containsValue(Object value);

	boolean equals(Object o);

	V get(Object key);

	int hashCode();

	boolean isEmpty();

	Set<K> keySet();

	V put(K key, V value);

	void putAll(Map<? extends K, ? extends V> m);

	V remove(Object key);

	int size();

	Collection<V> values();
	
}
