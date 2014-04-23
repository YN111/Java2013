import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import junit.framework.TestCase;

import org.junit.Test;

public class TestAttributedImpl extends TestCase {

	@Test
	public void testObserver() {
		AttributedImpl<String> attributed = new AttributedImpl<String>();
		Eye eye = new Eye(attributed);
		Attr<String> val1 = new Attr<String>("key1", "val1");
		Attr<String> val2 = new Attr<String>("key2", "val2");

		attributed.add(val1);
		assertEquals(eye.attrTable.size(), 1);
		assertEquals(eye.attrTable.get("key1").getValue(), "val1");

		attributed.add(val2);
		assertEquals(eye.attrTable.size(), 2);
		assertEquals(eye.attrTable.get("key2").getValue(), "val2");

		attributed.remove("key1");
		assertEquals(eye.attrTable.size(), 1);
		assertEquals(eye.attrTable.get("key2").getValue(), "val2");
	}

	/**
	 * 監視用クラス
	 */
	private class Eye implements Observer {
		private AttributedImpl<String> watching;
		private Map<String, Attr<String>> attrTable;

		public Eye(AttributedImpl<String> target) {
			watching = target;
			target.addObserver(this);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Observable target, Object map) {
			if (target != watching) {
				throw new IllegalArgumentException();
			}
			attrTable = (Map<String, Attr<String>>) map;
		}
	}
}
