/*
 * Attrをネストしたクラスとする
 * 
 * <理由>
 * AttrクラスはAttributedインタフェースを実装したクラスによって操作される属性の
 * 集まりを保持したクラスであるため
 */

public interface Attributed {
	void add(Attr newAttr);

	Attr find(String attrName);

	Attr remove(String attrName);

	java.util.Iterator<Attr> attrs();

	// Attrをネストしたクラスとする
	class Attr {
		private final String name;
		private Object value = null;

		public Attr(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

		public Object setValue(Object newValue) {
			Object oldVal = value;
			value = newValue;
			return oldVal;
		}

		public String toString() {
			return name + "='" + value + "'";
		}
	}
}
