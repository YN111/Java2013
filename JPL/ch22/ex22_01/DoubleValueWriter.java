import java.util.Formatter;

public class DoubleValueWriter {

	private static final int CHAR_NUM_PER_LINE = 80;

	/**
	 * 浮動小数点を整列して出力します。<br>
	 * columnは1行あたりの列数です。1行の最大文字数を80文字とし、各列を均等な幅で出力します。<br>
	 * columnに指定できる値は1から11までです。それ以外の値を指定するとIllegalArgumentExceptionをスローします。
	 * @param doubles 浮動小数点の配列
	 * @param column 列数
	 */
	public static void print(double[] doubles, int column) {
		int width = CHAR_NUM_PER_LINE / column; // 各列の幅
		if (width < 1 || width < 7) { //  表示幅は最低7文字分必要
			throw new IllegalArgumentException();
		}

		int precision;
		if (width < 8) {
			precision = 0;
		} else {
			precision = width - 8;
		}

		String fmtPtn = "% ." + precision + "e ";
		Formatter fmt = new Formatter(System.out);

		for (int i = 0; i < doubles.length; i++) {
			fmt.format(fmtPtn, doubles[i]);
			if (i % column == (column - 1)) {
				System.out.println();
			}
		}
	}
}
