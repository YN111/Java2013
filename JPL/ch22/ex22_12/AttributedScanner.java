import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class AttributedScanner {

	/**
	 * "name=value"の形式の属性を探して、それらの属性をAttributedImplオブジェクトに保存します。
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static Attributed readAttrs(Reader source) throws IOException {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		String exp = "^(.*)\\=(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);

		while (in.hasNextLine()) {
			in.findInLine(pat);
			MatchResult match = in.match();
			attrs.add(new Attr(match.group(1), match.group(2)));
			in.nextLine(); // 改行を読み飛ばす
		}

		return attrs;
	}
}
