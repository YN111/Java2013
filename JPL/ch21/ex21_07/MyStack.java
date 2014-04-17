import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack {

	private ArrayList<Object> stack = new ArrayList<Object>();

	/**
	 * スタックに要素を追加します。
	 * @param element
	 */
	public void push(Object element) {
		stack.add(element);
	}

	/**
	 * スタックから要素を取り除きます。<br>
	 * 空の場合はEmptyStackExceptionをスローします。
	 * @return
	 */
	public Object pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/**
	 * スタックのトップの要素を取り除くことなく返します。<br>
	 * 空の場合はEmptyStackExceptionをスローします。
	 * @return
	 */
	public Object peek() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(0);
	}

	/**
	 * スタックが空かどうか判定します。
	 * @return
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * このスタックにあるオブジェクトの位置を 1 から始まるインデックスで返します。<br>
	 * オブジェクトが見つからない場合は-1を返します。
	 * @return
	 */
	public int search(Object o) {
		int index = stack.indexOf(o);
		return index == -1 ? -1 : stack.indexOf(o) + 1;
	}
}
