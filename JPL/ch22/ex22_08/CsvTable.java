import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CsvTable {

	static final int CELLS = 4;

	/**
	 * CSVファイルを分割します。<br>
	 * 各行の要素の数は4個です。<br>
	 * 要素数を変更したい場合は、readCSVTable(Readable source, int cellNum)を使用します。
	 * @param source カンマ区切りのデータ
	 * @param cells 1行の要素数
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readCSVTable(Readable source) throws IOException {
		return readCSVTable(source, CELLS);
	}

	/**
	 * CSVファイルを分割します。<br>
	 * 各行の要素の数はcellNum個です。
	 * @param source カンマ区切りのデータ
	 * @param cellNum 1行の要素数
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readCSVTable(Readable source, int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();

		// パターンを生成
		StringBuilder exp = new StringBuilder();
		exp.append("^");
		for (int i = 0; i < cellNum; i++) {
			exp.append("(.*),");
		}
		exp.deleteCharAt(exp.length() - 1);
		Pattern pat = Pattern.compile(exp.toString(), Pattern.MULTILINE);

		while (in.hasNextLine()) {
			String line = in.findInLine(pat);

			// 空行を読み飛ばす
			if (line == null) {
				String next = in.nextLine();
				if ("".equals(next)) {
					continue;
				} else {
					throw new IOException("input format error");
				}
			}

			// カンマ数がcellNumと一致しない場合は例外をスロー
			if (line.split(",").length != cellNum) {
				throw new IOException("input format error");
			}

			String[] cells = new String[cellNum];
			MatchResult match = in.match();
			for (int i = 0; i < cellNum; i++) {
				cells[i] = match.group(i + 1);
			}
			vals.add(cells);
			in.nextLine(); // 改行を読み飛ばし
		}

		IOException ex = in.ioException();
		if (ex != null) {
			throw ex;
		}

		return vals;
	}
}
