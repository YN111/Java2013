import java.io.CharArrayReader;
import java.io.IOException;
import java.util.List;

public class Benchmark {

	private static final String PATTERN1 = "^(.*),(.*),(.*),(.*)"; // P569の例
	private static final String PATTERN2 = "([^,*].*),([^,*]*),([^,*]*),([^,*]*)"; // P286の例
	private static final String PATTERN3 = "^(.+?),(.+?),(.+?),(.+)"; // 最短一致
	private static final String PATTERN4 = "^(.+),(.+),(.+),(.+)"; // 最長一致

	public static void main(String[] args) throws IOException {
		test(PATTERN1, 4);
		test(PATTERN2, 4);
		test(PATTERN3, 4);
		test(PATTERN4, 4);

		Readable inShort = new CharArrayReader(createShortCsv().toCharArray());
		Readable inLong = new CharArrayReader(createLongCsv().toCharArray());

		System.out.println("Short String");
		System.out.printf("PATTERN1 : % 5d msec   " + PATTERN1 + "\n", measureTime(inShort, 4, PATTERN1));
		System.out.printf("PATTERN2 : % 5d msec   " + PATTERN2 + "\n", measureTime(inShort, 4, PATTERN2));
		System.out.printf("PATTERN3 : % 5d msec   " + PATTERN3 + "\n", measureTime(inShort, 4, PATTERN3));
		System.out.printf("PATTERN4 : % 5d msec   " + PATTERN4 + "\n", measureTime(inShort, 4, PATTERN4));

		System.out.println("\nLong String");
		System.out.printf("PATTERN1 : % 5d msec   " + PATTERN1 + "\n", measureTime(inLong, 4, PATTERN1));
		System.out.printf("PATTERN2 : % 5d msec   " + PATTERN2 + "\n", measureTime(inLong, 4, PATTERN2));
		System.out.printf("PATTERN3 : % 5d msec   " + PATTERN3 + "\n", measureTime(inLong, 4, PATTERN3));
		System.out.printf("PATTERN4 : % 5d msec   " + PATTERN4 + "\n", measureTime(inLong, 4, PATTERN4));
	}

	/**
	 * カンマ間の文字列が短いCSVデータを生成します。
	 * @return
	 */
	private static String createShortCsv() {
		StringBuilder inShortBuilder = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			inShortBuilder.append("a,b,c,d\n");
		}
		return inShortBuilder.toString();
	}

	/**
	 * カンマ間の文字列が長いCSVデータを生成します。
	 * @return
	 */
	private static String createLongCsv() {
		StringBuilder inLongBuilder = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 1000; j++) {
				inLongBuilder.append('a');
			}
			inLongBuilder.append(',');
			for (int j = 0; j < 1000; j++) {
				inLongBuilder.append('b');
			}
			inLongBuilder.append(',');
			for (int j = 0; j < 1000; j++) {
				inLongBuilder.append('c');
			}
			inLongBuilder.append(',');
			for (int j = 0; j < 1000; j++) {
				inLongBuilder.append('d');
			}
			inLongBuilder.append('\n');
		}
		return inLongBuilder.toString();
	}

	/**
	 * 指定された引数でreadCSVTableを100000回実行した時間を計測します。
	 * @param source
	 * @param cellNum
	 * @param exp
	 * @return
	 * @throws IOException
	 */
	private static long measureTime(Readable source, int cellNum, String exp) throws IOException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			CsvTable.readCSVTable(source, 4, exp);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	/**
	 * 指定された文字列が正しくCSVファイルを分割できるかチェックします
	 */
	private static void test(String exp, int cellNum) {
		Readable in = new CharArrayReader("a1,a2,a3,a4\nb1,b2,b3,b4\n".toCharArray());
		try {
			List<String[]> result = CsvTable.readCSVTable(in, 4, exp);
			String[] line0 = result.get(0);
			String[] line1 = result.get(1);
			if (!line0[0].equals("a1")) {
				throw new AssertionError();
			}
			if (!line0[1].equals("a2")) {
				throw new AssertionError();
			}
			if (!line0[2].equals("a3")) {
				throw new AssertionError();
			}
			if (!line0[3].equals("a4")) {
				System.out.println(line0[3]);
				throw new AssertionError();
			}
			if (!line1[0].equals("b1")) {
				throw new AssertionError();
			}
			if (!line1[1].equals("b2")) {
				throw new AssertionError();
			}
			if (!line1[2].equals("b3")) {
				throw new AssertionError();
			}
			if (!line1[3].equals("b4")) {
				throw new AssertionError();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
