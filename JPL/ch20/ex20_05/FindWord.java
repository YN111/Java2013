import java.io.*;

public class FindWord {
	
	/**
	 * 指定されたファイルを読み込んで指定された単語を検索します<br>
	 * 単語が発見されたすべての行を、行の前に行番号をつけて表示します
	 * @param filePath 単語検索を行うファイルのファイルパス
	 * @param match 検索対象の単語
	 * @throws IOException
	 */
	public static void findAndPrint(String filePath, String match) throws IOException {
		FileReader fileIn = new FileReader(filePath);
		LineNumberReader in = new LineNumberReader(fileIn);
		String line;
		while ((line = in.readLine()) != null) {
			if (line.indexOf(match) != -1) {
				System.out.println("line:" + in.getLineNumber() + "  " + line);
			}
		}
	}
	
}
