import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class LineFilterReader extends FilterReader {

	private static final String CRLF = System.getProperty("line.separator");

	public LineFilterReader(Reader r) {
		super(r);
	}

	/**
	 * 1行分の文字列を読み込んで配列として返します<br>
	 * ストリームの終端に達した場合はnullを返します
	 * @return
	 * @throws IOException
	 */
	public int[] readLine() throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 0;
		while ((i = read()) != '\n' && i != '\r') {
			if (i == -1) { // ファイルが終端に達した
				if (list.size() == 0) {
					return null;
				}
				break;
			}
			list.add(i);
		}
		super.skip(CRLF.length() - 1); // 次にread()が呼ばれたときに改行後の最初の文字を読み取れるようにする
		int[] line = new int[list.size()];
		for (int j = 0; j < line.length; j++) {
			line[j] = list.get(j);
		}
		return line;
	}
}
