import java.util.LinkedList;

public class PrintQueue {

	private LinkedList<PrintJob> list = new LinkedList<PrintJob>();

	/**
	 * �v�f��ǉ����܂�
	 * @param j
	 */
	public void add(PrintJob j) {
		list.add(j);
	}

	/**
	 * �v�f���폜���A�擪�̗v�f��Ԃ��܂�
	 * �v�f�����݂��Ȃ��ꍇ��null���Ԃ�܂�
	 * @return
	 */
	public PrintJob remove() {
		if (list.size() == 0)
			return null;

		return list.remove();
	}

}
