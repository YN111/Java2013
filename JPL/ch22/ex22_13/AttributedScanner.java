import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class AttributedScanner {

	/**
	 * "name=value"の形式の属性を探して、それらの属性をAttributedImplオブジェクトに保存します。
	 * "name=value"の形式に従わない入力がされると、IllegalArgumentExceptionをスローします。
	 * @param source
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static Attributed readAttrs(Reader source) throws IOException {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;
		in.useDelimiter("=");

		while (in.hasNext()) {
			if (attr != null) {
				// 次の要素は value が期待される
				String next = in.next();
				if (next.contains("=")) {
					throw new IllegalArgumentException();
				}

				attr.setValue(next);
				attr = null;
				in.nextLine(); // 改行をスキップ
				in.useDelimiter("=");

			} else {
				// 次の要素は key が期待される
				String next = in.next();
				if (next.contains("=")) {
					throw new IllegalArgumentException();
				}

				attr = new Attr(next);
				attrs.add(attr);
				in.skip("=");
				in.useDelimiter("\n");
			}
		}

		return attrs;
	}
}
