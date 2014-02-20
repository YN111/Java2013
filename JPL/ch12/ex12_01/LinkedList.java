// ex11_01を変更

public class LinkedList<E> {
	private E item;
	private LinkedList<E> next;

	LinkedList() {
	}

	LinkedList(E item) {
		this.item = item;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	public E getItem() {
		return item;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	/**
	 * 引数として指定されたアイテムを持つLinkedListオブジェクトを返す <br>
	 * 見つからなかった場合はObjectNotFindExceptionをスローする
	 */
	public LinkedList<E> find(E item) throws ObjectNotFoundException {
		LinkedList<E> list = this;
		while (list != null) {
			if (list.getItem().equals(item))
				return list;
			list = list.getNext();
		}
		throw new ObjectNotFoundException(item.toString());
	}

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
	 * リスト内の要素の数を返す
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
