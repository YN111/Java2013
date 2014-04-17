import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * コンストラクタで指定したListIteratorからStringオブジェクトを読み出して、<br>
 * 指定された長さより短いオブジェクトだけを返すListIterator型です。
 */
public class ShortStrings implements ListIterator<String> {

	private enum Operation {
		NEXT, PREVIOUS;
	}

	private Operation lastOperation = null; // 前回の操作
	private ListIterator<String> strings; // 元の文字列
	private String nextShort; // 次が不明ならばnull
	private String prevShort; // 前が不明ならばnull
	private final int maxLen; // この長さ以下の文字列だけ返す

	/**
	 * コンストラクタ<br>
	 * ShortStringsクラスの各メソッドは、maxLenより短いStringオブジェクトだけを返します。
	 * @param strings
	 * @param maxLen
	 */
	public ShortStrings(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		this.nextShort = null;
		this.prevShort = null;
	}

	/**
	 * 次の要素が存在する場合はtrue、存在しない場合はfalseを返します。
	 */
	@Override
	public boolean hasNext() {
		if (nextShort != null) { // すでに見つけている
			return true;
		}

		while (strings.hasNext()) {
			nextShort = strings.next();
			if (nextShort.length() <= maxLen) {
				return true;
			}
		}
		nextShort = null; // 見つけられなかった
		return false;
	}

	/**
	 * 前の要素が存在する場合はtrue、存在しない場合はfalseを返します。
	 */
	@Override
	public boolean hasPrevious() {
		if (prevShort != null) { // すでに見つけている
			return true;
		}

		while (strings.hasPrevious()) {
			prevShort = strings.previous();
			if (prevShort.length() <= maxLen) {
				return true;
			}
		}
		prevShort = null; // 見つけられなかった
		return false;
	}

	/**
	 * 次の要素を返します。
	 * 存在しない場合は、NoSuchElementExceptionがスローされます
	 * @throw NoSuchElementException
	 */
	@Override
	public String next() {
		if (nextShort == null && !hasNext()) {
			throw new NoSuchElementException();
		}
		String n = nextShort; // nextShortを記憶
		nextShort = null; // nextShortを消費
		lastOperation = Operation.NEXT;
		return n;
	}

	/**
	 * 前の要素を返します。
	 * 存在しない場合は、NoSuchElementExceptionがスローされます
	 * @throw NoSuchElementException
	 */
	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious()) {
			throw new NoSuchElementException();
		}
		String n = prevShort; // prevShortを記憶
		prevShort = null; // prevShortを消費
		lastOperation = Operation.PREVIOUS;
		return n;
	}

	/**
	 * nextまたはpreviousによって呼び出された最後の要素を削除します。<br>
	 * 1度もnextまたはpreviousを呼び出していない場合は、IllegalStateExceptionがスローされます。
	 */
	@Override
	public void remove() {
		if (lastOperation == null) {
			throw new IllegalStateException();
		} else if (lastOperation == Operation.NEXT) {
			// 1つ前の要素を削除する
			previous();
			strings.remove();
		} else {
			// 1つ後の要素を削除する
			next();
			strings.remove();
		}
	}

	/**
	 * 次にnextを呼び出したときに返される要素のインデックスを返します。<br>
	 * 次の要素がない場合は、リストのサイズを返します。
	 */
	@Override
	public int nextIndex() {
		return hasNext() ? strings.nextIndex() - 1 : strings.nextIndex();
	}

	/**
	 * 次にpreviousを呼び出したときに返される要素のインデックスを返します。<br>
	 * 要素がない場合は、-1を返します。
	 */
	@Override
	public int previousIndex() {
		hasPrevious();
		return hasPrevious() ? strings.previousIndex() + 1 : -1;
	}

	/**
	 * このメソッドは非サポートです。
	 */
	@Override
	public void add(String item) {
		throw new UnsupportedOperationException();
	}

	/**
	 * このメソッドはサポートされていません
	 */
	@Override
	public void set(String item) {
		throw new UnsupportedOperationException();
	}
}
