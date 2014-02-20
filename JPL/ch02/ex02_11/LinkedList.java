// ex02_11 (P51)

public class LinkedList {
	Object obj;
	LinkedList next;
	
	LinkedList() {
	}
	
	LinkedList(Object obj) {
		this.obj = obj;
	}
	
	public String toString() {
		String info = new String();
		info = "List : ";
		info += obj.toString();
		LinkedList nextList = next;
		while (nextList != null) {
			info += " -> ";
			info += nextList.obj.toString();
			nextList = nextList.next;
		}
		return info;
	}
}
