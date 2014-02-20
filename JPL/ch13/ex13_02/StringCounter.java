public class StringCounter {

	/**
	 * 文字列中に指定した文字列のパターンが出現する回数を数えます
	 * @param str 文字列
	 * @param pat 文字列のパターン
	 * @return 出現回数
	 */
	public static int count(String str, String pat) {
		int count = 0;
		int start = 0;
		while (true) {
			if (str.indexOf(pat, start) >= 0) {
				count++;
				start = str.indexOf(pat, start) + 1;
			} else { // 終端に達した
				return count;
			}
		}
	}

}
