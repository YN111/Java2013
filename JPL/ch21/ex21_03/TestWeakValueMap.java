import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class TestWeakValueMap extends TestCase {

	@Test
	public void testContainsValue() {
		Map<String, String> map = new WeakValueMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);

		map.put("1", v1.getValue()); // "test1"
		map.put("2", v2.getValue()); // "test2"
		assertTrue(map.containsValue("test1"));
		assertTrue(map.containsValue("test2"));

		v1 = null;
		System.gc(); // "test1"が回収される
		assertFalse(map.containsValue("test1"));
		assertTrue(map.containsValue("test2"));
	}

	@Test
	public void testEntrySet() {
		Map<String, String> map = new WeakValueMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);
		map.put("1", v1.getValue()); // "test1"
		map.put("2", v2.getValue()); // "test2"
		v1 = null;
		System.gc(); // "test1"が回収される

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("1")) {
				assertEquals(entry.getValue(), null); // 回収済み
			} else if (entry.getKey().equals("2")) {
				assertEquals(entry.getValue(), "test2"); // 未回収
			} else {
				fail();
			}
		}
	}

	@Test
	public void testGet() {
		Map<String, String> map = new WeakValueMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);
		map.put("1", v1.getValue()); // "test1"
		map.put("2", v2.getValue()); // "test2"
		v1 = null;
		System.gc(); // "test1"が回収される
		assertEquals(map.get("1"), null);
		assertEquals(map.get("2"), "test2");
	}

	@Test
	public void testPutAll() {
		Map<String, String> hashmap = new HashMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);
		hashmap.put("1", v1.getValue()); // "test1"
		hashmap.put("2", v2.getValue()); // "test2"

		Map<String, String> weakmap = new WeakValueMap<String, String>();
		weakmap.putAll(hashmap);

		hashmap = null;
		v1 = null;
		System.gc(); // "test1"が回収される
		assertEquals(weakmap.get("1"), null);
		assertEquals(weakmap.get("2"), "test2");
	}

	@Test
	public void testRemove() {
		Map<String, String> map = new WeakValueMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);
		map.put("1", v1.getValue()); // "test1"
		map.put("2", v2.getValue()); // "test2"
		v1 = null;
		System.gc(); // "test1"が回収される
		assertEquals(map.remove("1"), null);
		assertEquals(map.size(), 1);
		assertEquals(map.remove("2"), "test2");
		assertEquals(map.size(), 0);
	}

	@Test
	public void testValues() {
		Map<String, String> map = new WeakValueMap<String, String>();
		Value v1 = new Value(1);
		Value v2 = new Value(2);
		map.put("1", v1.getValue()); // "test1"
		map.put("2", v2.getValue()); // "test2"

		v1 = null;
		System.gc(); // "test1"が回収される
		Collection<String> values = map.values();
		assertFalse(values.contains("test1"));
		assertTrue(values.contains("test2"));
	}
}

class Value {
	private String value;

	public Value(int i) {
		value = "test" + i;
	}

	public String getValue() {
		return value;
	}
}