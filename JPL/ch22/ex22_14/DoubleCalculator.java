public class DoubleCalculator {

	/**
	 * 浮動小数点を含む文字列を受け取り、数字の合計を返します。<br>
	 * 区切り文字は空白とする必要があります。<br>
	 * 文字列が浮動小数点に変換できない場合、該当箇所は無視されます。
	 * @param input
	 * @return
	 */
	public static double sum(String input) {
		String[] doubles = input.split(" ");

		double sum = 0;
		for (String str : doubles) {
			double d = 0.0;
			try {
				d = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				// 無視する
			}
			sum += d;
		}

		return sum;
	}

}
