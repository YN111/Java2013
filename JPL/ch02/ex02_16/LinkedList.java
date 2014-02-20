// ex02_16 (P59)

public class LinkedList {
	private Object obj;
	private LinkedList next;

	LinkedList() {
	}

	LinkedList(Object obj) {
		this.obj = obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

	public Object getObj() {
		return obj;
	}

	public LinkedList getNext() {
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
			nextList = nextList.next;
		}
		return info;
	}
	
	/**
	 * ƒŠƒXƒg“à‚Ì—v‘f‚Ì”‚ğ•Ô‚·
	 * @return
	 */
	public int getLength() {
		int length = 1;
		LinkedList l = this;
		while (l.getNext() != null) {
			l = l.getNext();
			length++;
		}
		return length;
	}
}
