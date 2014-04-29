import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringSplitter {

	/**
	 * 文字列をトークンに分解し、リストとして返します。<br>
	 * #から始まるコメントは無視されます。
	 * @param source
	 */
	public static List<String> split(Reader source) {
		List<String> list = new ArrayList<String>();
		Scanner in = new Scanner(source);
		Pattern COMMENT = Pattern.compile("#.*");

		while (in.hasNext()) {
			if (in.hasNext(COMMENT)) {
				in.nextLine(); // コメントを読み飛ばす
			} else {
				list.add(in.next());
			}
		}

		return list;
	}
}
