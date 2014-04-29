public class TestDoubleValueWriter {

	private static final int DOUBLE_NUM = 50;
	private static final int MAX_COLUMN = 11;
	private static final int LINE = 80;

	public static void main(String[] args) {
		double[] doubles = new double[DOUBLE_NUM];
		for (int i = 0; i < DOUBLE_NUM; i++) {
			if (i % 2 == 0) {
				doubles[i] = (double) i;
			} else {
				doubles[i] = (double) -i;
			}
		}

		for (int i = 1; i <= MAX_COLUMN; i++) {
			System.out.println("column : " + i);

			// 80文字目に目印を出力
			for (int j = 0; j < LINE - 1; j++) {
				System.out.print(" ");
			}
			System.out.println("↓80chars");

			DoubleValueWriter.print(doubles, i);
			System.out.println();
			System.out.println();
		}

	}
}
