package ex19_02;

/**
 * 連結リストを表すクラスです。<br>
 * setObjメソッドでリストの要素を設定でき、getObjメソッドで取得できます。<br>
 * setNextメソッドで次のリストを設定でき、getNextメソッドで取得できます。
 */
public class LinkedList implements Cloneable {
	/**
	 * リストが保持している要素
	 */
	private Object obj;

	/**
	 * 次のリスト
	 */
	private LinkedList next;

	/**
	 * 指定された要素を持つリストを作成します
	 * @param obj リストの要素
	 */
	public LinkedList(Object obj) {
		this.obj = obj;
	}

	/**
	 * リストの要素を設定します
	 * @param obj リストの要素
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * リストの要素を返します
	 * @return 要素
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 次のリストを設定します
	 * @param next 次のリスト
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}

	/**
	 * 次のリストを返します
	 * @return 次のリスト
	 */
	public LinkedList getNext() {
		return next;
	}

	/**
	 * このリストの文字列表現を返します
	 * @return リストの文字列表現
	 */
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
	 * リストに含まれる要素の数を返します
	 * @return 要素の数
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
	 * このリストのコピーを作成して返します
	 * @return リストのコピー
	 */
	public LinkedList clone() {
		try {
			LinkedList list = (LinkedList) super.clone();
			list.obj = this.obj; // 元のオブジェクトと同じ値を参照する
			if (this.getNext() != null)
				list.setNext(this.getNext().clone());
			return list;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
