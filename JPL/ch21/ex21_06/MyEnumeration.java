import java.util.Collection;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class MyEnumeration<E> implements Enumeration<E> {

	private Object[] arr;
	private int offset = 0;

	public MyEnumeration(Collection<E> col) {
		arr = col.toArray();
	}

	/**
	 * 次の要素が存在するかを判定します。
	 */
	@Override
	public boolean hasMoreElements() {
		return offset < arr.length;
	}

	/**
	 * 次の要素を返します。<br>
	 * 要素が存在しない場合は、NoSuchElementExceptionがスローされます。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E nextElement() {
		if (!hasMoreElements()) {
			throw new NoSuchElementException();
		}
		return (E) arr[offset++];
	}
}
