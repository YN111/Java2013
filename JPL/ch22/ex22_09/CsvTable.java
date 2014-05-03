import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CsvTable {

	/**
	 * 指定されたパターンでCSVファイルを分割します。<br>
	 * 各行の要素の数はcellNum個です。<br>
	 * パターンにマッチしない行は無視されます。
	 * @param source カンマ区切りのデータ
	 * @param cellNum 1行の要素数
	 * @param exp 分割パターン
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readCSVTable(Readable source, int cellNum, String exp) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);

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
