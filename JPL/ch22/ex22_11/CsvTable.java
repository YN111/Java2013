import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

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
	public static List<String[]> readCSVTable(Reader source) throws IOException {
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
	public static List<String[]> readCSVTable(Reader source, int cellNum) throws IOException {
		List<String[]> vals = new ArrayList<String[]>();
		StreamTokenizer in = new StreamTokenizer(source);
		in.eolIsSignificant(true);
		// 数値は単語として扱う
		in.ordinaryChars('0', '9');
		in.ordinaryChar('-');
		in.ordinaryChar('.');
		in.wordChars('0', '9');
		in.wordChars('-', '-');
		in.wordChars('.', '.');

		// カンマを空白とみなしてスペースを空白とみなさない
		in.ordinaryChar(' ');
		in.whitespaceChars(',', ',');

		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			// 空行を読み飛ばす
			if (in.ttype == StreamTokenizer.TT_EOL) {
				continue;
			}

			String[] cells = new String[cellNum];
			for (int i = 0; i < cellNum; i++) {
				if (in.ttype == StreamTokenizer.TT_WORD) {
					cells[i] = in.sval;
				} else if (in.ttype == StreamTokenizer.TT_NUMBER) {
					// 数値は単語文字として扱っている
					throw new AssertionError();
				} else {
					throw new IOException("input format error");
				}
				in.nextToken();
			}

			// 1行にcellNum以上の要素がある場合は例外をスロー
			if (in.ttype != StreamTokenizer.TT_EOL) {
				throw new IOException("input format error");
			}

			vals.add(cells);
		}

		return vals;
	}
}
