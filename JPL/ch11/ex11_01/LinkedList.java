// ex02_16を変更

public class LinkedList<E> {
	private E item;
	private LinkedList<E> next;

	LinkedList() {
	}

	/**
	 * コンストラクタ
	 * リストに要素を設定します
	 * @param item 要素
	 */
	LinkedList(E item) {
		this.item = item;
	}

	/**
	 * リストに要素を設定します
	 * @param item 要素
	 */
	public void setItem(E item) {
		this.item = item;
	}

	/**
	 * 次のリストへのリンクを設定します
	 * @param next 次のリスト
	 */
	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	/**
	 * 要素を取得します
	 * @return 要素
	 */
	public E getItem() {
		return item;
	}

	/**
	 * 次のリストを取得します
	 * @return 次の要素
	 */
	public LinkedList<E> getNext() {
		return next;
	}

	@Override
	public String toString() {
		String info = new String();
		info = "List : ";
		info += item.toString();
		LinkedList<E> nextList = next;
		while (nextList != null) {
			info += " -> ";
			info += nextList.getItem().toString();
			nextList = nextList.getNext();
		}
		return info;
	}

	/**
	 * リスト内の要素の数を返します
	 * @return 要素の数
	 */
	public int getLength() {
		int length = 1;
		LinkedList<E> list = this;
		while (list.getNext() != null) {
			list = list.getNext();
			length++;
		}
		return length;
	}
}
