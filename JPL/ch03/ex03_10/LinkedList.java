public class LinkedList implements Cloneable {
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
			nextList = nextList.getNext();
		}
		return info;
	}

	/**
	 * ���X�g���̗v�f�̐���Ԃ�
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

	/**
	 * �N���[�������
	 */
	public LinkedList clone() {
		try {
			LinkedList list = (LinkedList) super.clone();
			list.obj = this.obj; // ���̃I�u�W�F�N�g�Ɠ����l���Q�Ƃ���
			if (this.getNext() != null)
				list.setNext(this.getNext().clone());
			return list;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
