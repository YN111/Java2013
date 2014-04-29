import java.util.Random;

public class Dice {

	private static final int DEFAULT_SHAKE_TIMES = 10000;

	/**
	 * diceNum個のサイコロを同時に振った時の可能性のある合計値ごとの理論的確率を計算します。<br>
	 * 戻り値の配列はインデックスが合計値、中身が確率を表します。<br>
	 * 本メソッドでは確率の計算で乱数を10000回発生させます。<br>
	 * 乱数の発生回数を変更したい場合は、shake(int diceNum, int times)メソッドを使用します。
	 * @param diceNum サイコロ数
	 * @return
	 */
	public static double[] shake(int diceNum) {
		return shake(diceNum, DEFAULT_SHAKE_TIMES);
	}

	/**
	 * diceNum個のサイコロを同時に振った時の可能性のある合計値ごとの理論的確率を計算します。<br>
	 * 戻り値の配列はインデックスが合計値、中身が確率を表します。<br>
	 * @param diceNum サイコロ数
	 * @times times 乱数の発生回数
	 * @return
	 */
	public static double[] shake(int diceNum, int times) {
		double[] probability = new double[diceNum * 6];
		Random r = new Random();
		double reciprocalOfTimes = 1.0 / times;

		for (int i = 0; i < times; i++) {
			int sum = 0;
			for (int j = 0; j < diceNum; j++) {
				sum += (Math.abs(r.nextInt()) % 6 + 1);
			}
			probability[sum - 1] += reciprocalOfTimes;
		}

		return probability;
	}

	public static void main(String[] args) {
		int diceNum = 2;

		double[] result = shake(diceNum);
		for (int i = diceNum - 1; i < result.length; i++) {
			System.out.println((i + 1) + ": " + result[i]);
		}
	}
}
