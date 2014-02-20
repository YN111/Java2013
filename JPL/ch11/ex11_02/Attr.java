// ex03_07を変更

public class Attr<T> {
	private final String name;
	private T value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}

	public Object setValue(T newValue) {
		T oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}
}
