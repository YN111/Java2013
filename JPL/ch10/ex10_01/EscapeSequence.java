public class EscapeSequence {

	/**
	 * 特殊文字を対応するJava言語の表現に置き換えて出力します
	 * @param s 特殊文字を置き換える文字列
	 */
	public static void printEscapeSequence(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '\n')
				System.out.print("\\n"); // 改行
			else if (c == '\t')
				System.out.print("\\t"); // タブ
			else if (c == '\b')
				System.out.print("\\b"); // バックスペース
			else if (c == '\r')
				System.out.print("\\r"); // 復帰
			else if (c == '\f')
				System.out.print("\\f"); // フォームフィード
			else if (c == '\\')
				System.out.print("\\\\"); // バックスラッシュ
			else if (c == '\'')
				System.out.print("\\\'"); // シングルクオテーション
			else if (c == '\"')
				System.out.print("\\\""); // ダブルクオテーション
			else if (c >= '\000' && c <= '\377')
				System.out.printf("\\%03o", s.codePointAt(i)); // 8進数によるchar
			else
				System.out.print(s.charAt(i));
		}
		System.out.print("\n");
	}

	/**
	 * メインメソッド
	 * 正しく表示されるかテストします
	 */
	public static void main(String[] args) {
		String str = "\n\t\b\r\f\\\'\"\000\377";
		System.out.println("特殊文字を出力しない⇒" + str);
		printEscapeSequence("特殊文字を出力する⇒" + str);
	}

}
