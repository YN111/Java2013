import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {

	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays;
		int s = 0;
		for (E[] array : arrays) {
			s += array.length;
		}
		size = s;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		int off = 0; // コレクションの先頭からのオフセット
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				return arrays[i][index - off];
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public E set(int index, E value) {
		int off = 0; // コレクションの先頭からのオフセット
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				E ret = arrays[i][index - off];
				arrays[i][index - off] = value;
				return ret;
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ABLListIterator();
	}

	/**
	 * ArrayBenchListに対するイテレータです。
	 */
	private class ABLListIterator implements ListIterator<E> {

		private int off; // リストの先頭からのオフセット
		private int array; // 現在処理している配列
		private int pos; // 現在の配列内の位置

		ABLListIterator() {
			off = 0;
			array = 0;
			pos = 0;
			// 最初から空の配列を読み飛ばす
			for (array = 0; array < arrays.length; array++) {
				if (arrays[array].length > 0) {
					break;
				}
			}
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return off + pos < size();
		}

		@Override
		public boolean hasPrevious() {
			return off + pos > 0;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E ret = arrays[array][pos++];

			// 次の要素まで進める
			while (pos >= arrays[array].length) {
				off += arrays[array++].length;
				pos = 0;
				if (array >= arrays.length) {
					break;
				}
			}

			return ret;
		}

		@Override
		public int nextIndex() {
			return off + pos;
		}

		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}

			// 前の要素に戻す
			pos--;
			while (pos < 0) {
				if (array - 1 < 0) {
					break;
				}
				off -= arrays[--array].length;
				pos = arrays[array].length - 1;
			}

			E ret = arrays[array][pos];
			return ret;
		}

		@Override
		public int previousIndex() {
			return hasPrevious() ? off + pos - 1 : -1;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
}
