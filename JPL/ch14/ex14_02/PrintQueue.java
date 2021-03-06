import java.util.LinkedList;

public class PrintQueue {

	private LinkedList<PrintJob> list = new LinkedList<PrintJob>();

	/**
	 * 要素を追加します
	 * @param j
	 */
	public void add(PrintJob j) {
		list.add(j);
	}

	/**
	 * 要素を削除し、先頭の要素を返します
	 * 要素が存在しない場合はnullが返ります
	 * @return
	 */
	public PrintJob remove() {
		if (list.size() == 0)
			return null;

		return list.remove();
	}

}
