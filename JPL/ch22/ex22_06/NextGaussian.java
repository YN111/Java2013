import java.util.Random;

public class NextGaussian {

	// 棒グラフの最小値と最大値のデフォルト値
	// ガウス分布では99.73%の確率でこの範囲内に収まる
	// この範囲外の値が得られたらMIN_VALUE or MAX_VALUEが得られたものとして扱う
	private static double DEFAULT_MIN_VALUE = -3.0;
	private static double DEFAULT_MAX_VALUE = 3.0;

	// デフォルトの表示間隔
	private static double DEFAULT_INTERVAL = 0.05;

	/**
	 * nextGaussianを使用して乱数をtimes回発生させ、出現回数を0.05刻みで棒グラフで出力します。
	 * 棒グラフの最小値は-3.0、最大値は3.0になります。
	 * @param times 乱数の発生回数
	 */
	public static void verify(int times) {
		verify(times, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, DEFAULT_INTERVAL);
	}

	/**
	 * nextGaussianを使用して乱数をtimes回発生させ、出現回数をinterval刻みで棒グラフで出力します。
	 * @param times 乱数の発生回数
	 * @param minValue 棒グラフの最小値
	 * @param maxValue 棒グラフの最大値
	 * @param interval 表示間隔
	 */
	public static void verify(int times, double minValue, double maxValue, double interval) {
		// 出現回数を保持する配列
		// インデックスはnextGaussian()で得たdouble型に以下の式を適用して正の整数に変換した値
		// nextGaussian() / interval + (-minValue) / interval
		int indexNum = (int) ((maxValue - minValue) / interval);
		int[] column = new int[indexNum];
		Random r = new Random();

		int max = 0; // 出現回数の最大値

		for (int i = 0; i < times; i++) {
			int index = (int) (r.nextGaussian() / interval + (-minValue) / interval);
			if (index < 0) {
				index = 0;
			} else if (index >= indexNum) {
				index = indexNum - 1;
			}
			column[index]++;
			if (column[index] > max) {
				max = column[index];
			}
		}

		// 出力
		System.out.println("times:" + times + ", minValue:" + minValue + ", maxvalue:" + maxValue + ", interval:"
				+ interval);
		for (int i = max; i > 0; i--) {
			for (int j = 0; j < indexNum; j++) {
				if (column[j] >= i) {
					System.out.print('*');
				} else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}

		for (int j = 0; j < indexNum; j++) {
			System.out.print('-');
		}
		System.out.println();
	}

	public static void main(String[] args) {
		//		NextGaussian.verify(100000);
		NextGaussian.verify(100000, -5.0, 5.0, 0.1);
	}
}
