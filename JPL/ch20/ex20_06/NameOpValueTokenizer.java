import java.io.*;

public class NameOpValueTokenizer {

	private static String FIRST = "first";
	private static String SECOND = "second";
	private static String THIRD = "third";
	private static String PLUS = "+";
	private static String MINUS = "-";
	private static String EQUAL = "=";

	private enum Name {
		FIRST, SECOND, THIRD;

		// 計算結果
		double score = 0.0;

		void calc(Op op, double value) {
			switch (op) {
			case PLUS:
				score += value;
				break;
			case MINUS:
				score -= value;
				break;
			case EQUAL:
				score = value;
				break;
			}
		}
	}

	private enum Op {
		PLUS, MINUS, EQUAL;
	}

	private Name selectName;
	private Op selectOp;

	/**
	 * "name op value"形式の入力を処理するメソッドです<br>
	 * <br>
	 * "name" は計算対象の単語です。"first", "second" または "third" を指定してください<br>
	 * "op" は演算子です。'+', '-' または '=' を指定してください<br>
	 * "value" は計算する値です。数値を指定してください<br>
	 * <br>
	 * すべての計算を終了すると結果を出力します
	 * @param source
	 */
	public void calc(Reader source) {
		StreamTokenizer in = new StreamTokenizer(source);
		// '+'と'='を単語文字とする
		// '-'はopでは単語文字、valueでは数値となるためwhile文内で用途に応じて設定する
		in.wordChars('+', '+');
		in.wordChars('=', '=');

		int type;
		try {
			while ((type = in.nextToken()) != StreamTokenizer.TT_EOF) {
				// nameをチェックする
				if (FIRST.equals(in.sval)) {
					selectName = Name.FIRST;
				} else if (SECOND.equals(in.sval)) {
					selectName = Name.SECOND;
				} else if (THIRD.equals(in.sval)) {
					selectName = Name.THIRD;
				} else {
					throw new IllegalArgumentException(
							"name は \"first\", \"second\", \"third\" のいずれかを指定してください");
				}

				// opをチェックする
				in.ordinaryChar('-'); // '-'を単語文字とする
				in.wordChars('-', '-');
				type = in.nextToken();

				if (PLUS.equals(in.sval)) {
					selectOp = Op.PLUS;
				} else if (MINUS.equals(in.sval)) {
					selectOp = Op.MINUS;
				} else if (EQUAL.equals(in.sval)) {
					selectOp = Op.EQUAL;
				} else {
					throw new IllegalArgumentException("op は \'+\', \'-\', \'=\' のいずれかを指定してください");
				}

				// valueをチェックする
				in.parseNumbers(); // '-'を数値とする
				type = in.nextToken();
				if (type != StreamTokenizer.TT_NUMBER) {
					throw new IllegalArgumentException("valueは数値を指定してください");
				}

				// 計算実行
				selectName.calc(selectOp, in.nval);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("first : " + Name.FIRST.score);
		System.out.println("second: " + Name.SECOND.score);
		System.out.println("third : " + Name.THIRD.score);
	}
}
