import java.util.ArrayList;

public class MyString {

	/**
	 * サブ文字列を出力する
	 */
	public static String[] delimitedString(String from, char start, char end) {
		ArrayList<String> arr = new ArrayList<String>();
		int startPos = from.indexOf(start);
		int endPos = from.indexOf(end, startPos + 1);

		while (true) {
			if (startPos == -1) { // 開始文字が見つからない
				break;
			} else if (endPos == -1) { // 終了文字が見つからない
				arr.add(from.substring(startPos));
				break;
			} else {
				arr.add(from.substring(startPos, endPos + 1));
				startPos = from.indexOf(start, endPos + 1); // 次の区切り文字の開始位置を取得
				endPos = from.indexOf(end, startPos + 1);// 次の区切り文字の終了位置を取得
			}
		}

		// ArrayListの中身を配列に格納
		String[] subString = new String[arr.size()];
		int i = 0;
		for (String s : arr) {
			subString[i] = s;
			i++;
		}

		return subString;
	}

}
