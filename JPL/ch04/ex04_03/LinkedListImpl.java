public class LinkedListImpl implements LinkedList, Cloneable {
	private Object obj;
	private LinkedListImpl next;

	LinkedListImpl() {
	}

	LinkedListImpl(Object obj) {
		this.obj = obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setNext(LinkedList next) {
		this.next = (LinkedListImpl)next;
	}

	public Object getObj() {
		return obj;
	}

	public LinkedListImpl getNext() {
		return next;
	}

	public String toString() {
		String info = new String();
		info = "List : ";
		info += obj.toString();
		LinkedList nextList = getNext();
		while (nextList != null) {
			info += " -> ";
			info += nextList.getObj().toString();
			nextList = nextList.getNext();
		}
		return info;
	}

	/**
	 * リスト内の要素の数を返す
	 */
	public int getLength() {
		int length = 1;
		LinkedListImpl l = this;
		while (l.getNext() != null) {
			l = l.getNext();
			length++;
		}
		return length;
	}

	/**
	 * クローンを作る
	 */
	public LinkedListImpl clone() {
		try {
			LinkedListImpl list = (LinkedListImpl) super.clone();
			if (this.getNext() != null)
				list.setNext(((LinkedListImpl) this.getNext()).clone());
			return list;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
