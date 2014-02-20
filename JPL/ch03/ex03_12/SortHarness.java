abstract class SortHarness {
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * 全ソートをするために呼び出される　dataはComparableインタフェースを実装している必要がある
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
	 * 拡張クラスが要素の数を知るため
	 */
	protected final int getDataLength() {
		return values.length;
	}

	/**
	 * 拡張クラスが要素を調べるため
	 */
	protected final Object probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * 拡張クラスが要素を比較するため
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
	 * 拡張クラスが要素を交換するため
	 */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	/**
	 * 拡張クラスが実装する -- ソートするのに利用される
	 */
	protected abstract void doSort();
}

final class SortMetrics implements Cloneable {
	public long probeCnt, // 単純なデータ値の調査回数
			compareCnt, // 2つの要素の比較解す
			swapCnt; // 2つの要素の交換回数

	public void init() {
		probeCnt = swapCnt = compareCnt = 0;
	}

	public String toString() {
		return probeCnt + " probes " + compareCnt + " compares " + swapCnt
				+ " swaps";
	}

	/**
	 * クローンを作成する
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
