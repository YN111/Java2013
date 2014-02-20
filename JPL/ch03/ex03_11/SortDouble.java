/*
 * サブクラスのdoSortメソッド内でdouble型の配列を定義し、この配列を引数として
 * sort()メソッドを呼び出す。
 * このとき、sort()メソッド内で、curMetrics.init()が呼ばれるため、メトリクスが
 * 全て0に初期化される。
 * これを防ぐため、sort()メソッドが2回呼ばれた時、ソートを実行しないように修正
 * する。
 */

abstract class SortDouble {
	private double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * 全ソートをするために呼び出される
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
	 * 拡張クラスが要素の数を知るため
	 */
	protected final int getDataLength() {
		return values.length;
	}

	/**
	 * 拡張クラスが要素を調べるため
	 */
	protected final double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * 拡張クラスが要素を比較するため
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
	 * 拡張クラスが要素を交換するため
	 */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
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
			compareCnt, // 2つの要素の比較回数
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
