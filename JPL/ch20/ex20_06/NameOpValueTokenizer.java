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

		// �v�Z����
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
	 * "name op value"�`���̓��͂��������郁�\�b�h�ł�<br>
	 * <br>
	 * "name" �͌v�Z�Ώۂ̒P��ł��B"first", "second" �܂��� "third" ���w�肵�Ă�������<br>
	 * "op" �͉��Z�q�ł��B'+', '-' �܂��� '=' ���w�肵�Ă�������<br>
	 * "value" �͌v�Z����l�ł��B���l���w�肵�Ă�������<br>
	 * <br>
	 * ���ׂĂ̌v�Z���I������ƌ��ʂ��o�͂��܂�
	 * @param source
	 */
	public void calc(Reader source) {
		StreamTokenizer in = new StreamTokenizer(source);
		// '+'��'='��P�ꕶ���Ƃ���
		// '-'��op�ł͒P�ꕶ���Avalue�ł͐��l�ƂȂ邽��while�����ŗp�r�ɉ����Đݒ肷��
		in.wordChars('+', '+');
		in.wordChars('=', '=');

		int type;
		try {
			while ((type = in.nextToken()) != StreamTokenizer.TT_EOF) {
				// name���`�F�b�N����
				if (FIRST.equals(in.sval)) {
					selectName = Name.FIRST;
				} else if (SECOND.equals(in.sval)) {
					selectName = Name.SECOND;
				} else if (THIRD.equals(in.sval)) {
					selectName = Name.THIRD;
				} else {
					throw new IllegalArgumentException(
							"name �� \"first\", \"second\", \"third\" �̂����ꂩ���w�肵�Ă�������");
				}

				// op���`�F�b�N����
				in.ordinaryChar('-'); // '-'��P�ꕶ���Ƃ���
				in.wordChars('-', '-');
				type = in.nextToken();

				if (PLUS.equals(in.sval)) {
					selectOp = Op.PLUS;
				} else if (MINUS.equals(in.sval)) {
					selectOp = Op.MINUS;
				} else if (EQUAL.equals(in.sval)) {
					selectOp = Op.EQUAL;
				} else {
					throw new IllegalArgumentException("op �� \'+\', \'-\', \'=\' �̂����ꂩ���w�肵�Ă�������");
				}

				// value���`�F�b�N����
				in.parseNumbers(); // '-'�𐔒l�Ƃ���
				type = in.nextToken();
				if (type != StreamTokenizer.TT_NUMBER) {
					throw new IllegalArgumentException("value�͐��l���w�肵�Ă�������");
				}

				// �v�Z���s
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
