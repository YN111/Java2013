import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Attr {

	@Sample1
	@Sample2
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	@Sample3
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

	@Override
	public String toString() {
		return name + "='" + value + "'";
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample1 {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample2 {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample3 {
}
