import java.util.Comparator;

public interface SortedMap<K, V> extends Map<K, V> {

	Comparator<? super K> comparator();

	K firstKey();

	SortedMap<K, V> headMap(K toKey);

	Set<K> keySet();

	K lastKey();

	SortedMap<K, V> subMap(K fromKey, K toKey);

	SortedMap<K, V> tailMap(K fromKey);

	Collection<V> values();
}
