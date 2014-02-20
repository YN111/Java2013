public class NumberFormatter {

	/**
	 * 10進数を含む文字列を右から3桁ごとにカンマで区切られた数に変換します
	 * @param num 10進数を含む文字列
	 * @return 3桁ごとにカンマで区切られた数
	 * @throws NumberFormatException 文字列が数値に変換できない場合
	 */
	public static String insertComma(String num) throws NumberFormatException {
		Integer.parseInt(num); // numがIntegerに変換できないとNumberFormatExceptionがスローされる

		StringBuilder sb = new StringBuilder(num);
		int insertPoint = (num.length() % 3 == 0) ? 3 : num.length() % 3;

		while (insertPoint < sb.length()) {
			sb.insert(insertPoint, ",");
			insertPoint += 4;
		}
		return sb.toString();
	}

}
