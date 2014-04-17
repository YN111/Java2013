import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * ファイルを開いて1行ずつ読み込み、各行をString.compareToを使用してソートされた
 * Listに保存します。
 */
public class SortedFileReader {

	public static List<String> readFile(String filePath) {
		List<String> lines = new LinkedList<String>();

		try {
			FileReader fr = new FileReader(filePath);
			LineFilterReader lfr = new LineFilterReader(fr);
			int[] line = null;
			String lineStr = null;

			// ストリームの終端に達するまで繰り返し
			while ((line = lfr.readLine()) != null) {
				lineStr = convertIntToString(line);

				// 初回のみ
				if (lines.size() == 0) {
					lines.add(lineStr);
					continue;
				}

				// 要素が昇順になるように追加
				int listSize = lines.size();
				for (int i = 0; i < listSize; i++) {
					if (lineStr.compareTo(lines.get(i)) < 0) {
						lines.add(i, lineStr);
						break;
					}
				}
				if (listSize == lines.size()) {
					lines.add(lineStr);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	/**
	 * int型の配列を文字列に変換します<br>
	 * @param str
	 * @return
	 */
	private static String convertIntToString(int[] str) {
		StringBuilder sb = new StringBuilder("");
		for (int c : str) {
			sb.append((char) c);
		}
		return sb.toString();
	}

}
