import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

	/**
	 * 入力ファイルを単語に分解して、そのファイル内で各単語の出現数を数えて表示します
	 * @param filePath
	 */
	public static void showCountResult(String filePath) {
		try {
			FileReader source = new FileReader(filePath);
			Map<String, Integer> result = count(source);
			for (String word : result.keySet()) {
				System.out.println(word + ":" + result.get(word));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定されたReaderに含まれる単語の数をカウントし、マップとして返します<br>
	 * @param source
	 * @return Map：keyが単語、valueが出現数です
	 */
	private static Map<String, Integer> count(Reader source) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		StreamTokenizer in = new StreamTokenizer(source);

		int type;
		try {
			while ((type = in.nextToken()) != StreamTokenizer.TT_EOF) {
				if (type == StreamTokenizer.TT_WORD) {
					if (countMap.containsKey(in.sval)) {
						int tmp = countMap.get(in.sval);
						countMap.put(in.sval, tmp + 1);
					} else {
						countMap.put(in.sval, 1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return countMap;
	}
}
