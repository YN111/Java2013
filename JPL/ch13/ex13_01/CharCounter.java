public class CharCounter {

	/**
	 * 引数として指定された文字が文字列中に出現する回数を数えます
	 * @param str 文字列
	 * @param ch 数える対象となる文字
	 * @return 出現回数
	 */
	public static int count(String str, char ch) {
		int count = 0;
		int start = 0;
		while (true) {
			if (str.indexOf(ch, start) >= 0) {
				count++;
				start = str.indexOf(ch, start) + 1;
			} else { // 終端に達した
				return count;
			}
		}
	}

}
