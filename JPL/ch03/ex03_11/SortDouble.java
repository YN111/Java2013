/*
 * �T�u�N���X��doSort���\�b�h����double�^�̔z����`���A���̔z��������Ƃ���
 * sort()���\�b�h���Ăяo���B
 * ���̂Ƃ��Asort()���\�b�h���ŁAcurMetrics.init()���Ă΂�邽�߁A���g���N�X��
 * �S��0�ɏ����������B
 * �����h�����߁Asort()���\�b�h��2��Ă΂ꂽ���A�\�[�g�����s���Ȃ��悤�ɏC��
 * ����B
 */

abstract class SortDouble {
	private double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * �S�\�[�g�����邽�߂ɌĂяo�����
	 */
	public final SortMetrics sort(double[] data) {
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
	protected final double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * �g���N���X���v�f���r���邽��
	 */
	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		double d1 = values[i];
		double d2 = values[j];
		if (d1 == d2)
			return 0;
		else
			return (d1 < d2 ? -1 : 1);
	}

	/**
	 * �g���N���X���v�f���������邽��
	 */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
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
			compareCnt, // 2�̗v�f�̔�r��
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

class SimpleSortDouble extends SortDouble {
	private boolean flg = false;

	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0)
					swap(i, j);
			}
		}
		if (flg == false) {
			flg = true;
			double[] d = { 0 };
			sort(d);
		}
	}
}
