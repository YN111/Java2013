public class NumberFormatter {

	/**
	 * 10進数を含む文字列を指定された桁数ごとに指定された区切り文字で区切られた文字に変換します
	 * @param num 10進数を含む文字列
	 * @param delimiter 区切り文字
	 * @param digit 桁数
	 * @return 3桁ごとにカンマで区切られた数
	 * @throws NumberFormatException 文字列が数値に変換できない場合
	 */
	public static String insertComma(String num, String delimiter, int digit)
			throws NumberFormatException {
		Integer.parseInt(num); // numがIntegerに変換できないとNumberFormatExceptionがスローされる

		StringBuilder sb = new StringBuilder(num);
		int insertPoint = (num.length() % digit == 0) ? digit : num.length() % digit;

		while (insertPoint < sb.length()) {
			sb.insert(insertPoint, delimiter);
			insertPoint += (digit + 1);
		}
		return sb.toString();
	}

}
