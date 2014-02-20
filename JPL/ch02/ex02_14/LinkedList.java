// ex02_14 (P58)
// obj�t�B�[���h�Anext�t�B�[���h�Ƃ��ɕύX���������\�b�h������

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
}
