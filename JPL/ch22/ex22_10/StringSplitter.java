import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringSplitter {

	/**
	 * 文字列をトークンに分解し、リストとして返します。<br>
	 * #から始まるコメントは無視されます。
	 * @param source
	 */
	public static List<String> split(Reader source) {
		List<String> list = new ArrayList<String>();
		Scanner in = new Scanner(source);
		in.useDelimiter(in.delimiter().toString() + "|#.*"); // デリミタにコメント文字を追加

		while (in.hasNext()) {
			String next = in.next();
			if (!next.equals("")) {
				list.add(next);
			}
		}

		return list;
	}
}
