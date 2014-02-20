abstract class SortHarness {
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * �S�\�[�g�����邽�߂ɌĂяo�����@data��Comparable�C���^�t�F�[�X���������Ă���K�v������
	 */
	public final SortMetrics sort(Object[] data) {
		values = data;
		curMetrics.init();
		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}

	/**
	 * �g���N���X���v�f�̐���m�邽��
	 */
	protected final int getDataLength() {
		return values.length;
	}

	/**
	 * �g���N���X���v�f�𒲂ׂ邽��
	 */
	protected final Object probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * �g���N���X���v�f���r���邽��
	 */
	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		Object obj1 = values[i];
		Object obj2 = values[j];
		if (obj1 == obj2)
			return 0;
		else {
			return ((Comparable) obj1).compareTo((Comparable) obj2);
		}
	}

	/**
	 * �g���N���X���v�f���������邽��
	 */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	/**
	 * �g���N���X���������� -- �\�[�g����̂ɗ��p�����
	 */
	protected abstract void doSort();
}

final class SortMetrics implements Cloneable {
	public long probeCnt, // �P���ȃf�[�^�l�̒�����
			compareCnt, // 2�̗v�f�̔�r����
			swapCnt; // 2�̗v�f�̌�����

	public void init() {
		probeCnt = swapCnt = compareCnt = 0;
	}

	public String toString() {
		return probeCnt + " probes " + compareCnt + " compares " + swapCnt
				+ " swaps";
	}

	/**
	 * �N���[�����쐬����
	 */
	public SortMetrics clone() {
		try {
			return (SortMetrics) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}

class SimpleSortHarness extends SortHarness {
	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0)
					swap(i, j);
			}
		}
	}
}
