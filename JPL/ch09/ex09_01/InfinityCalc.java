public class InfinityCalc {
	
	/**
	 * 引数で与えられた値の加減乗除を計算し表示します
	 * @param x オペランド1
	 * @param y オペランド2
	 */
	public static void showCalcResult(double x, double y) {
		System.out.println(x + " + " + y + " = " + (x + y));
		System.out.println(x + " - " + y + " = " + (x - y));
		System.out.println(x + " * " + y + " = " + (x * y));
		System.out.println(x + " / " + y + " = " + (x / y));
	}

	public static void main(String[] args) {
		double pos = Double.POSITIVE_INFINITY;
		double neg = Double.NEGATIVE_INFINITY;

		System.out.println("===== POSITIVE POSITIVE =====");
		showCalcResult(pos, pos);
		System.out.println();

		System.out.println("===== POSITIVE NEGATIVE =====");
		showCalcResult(pos, neg);
		System.out.println();

		System.out.println("===== NEGATIVE POSITIVE =====");
		showCalcResult(neg, pos);
		System.out.println();

		System.out.println("===== NEGATIVE NEGATIVE =====");
		showCalcResult(neg, neg);
		System.out.println();
	}
}
