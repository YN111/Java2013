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
	 * 1�s���̕������ǂݍ���Ŕz��Ƃ��ĕԂ��܂�
	 * @return
	 * @throws IOException
	 */
	public int[] readLine() throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();

		int i;
		while ((i = read()) != -1 && i != '\n' && i != '\r') {
			list.add(i);
		}
		super.skip(CRLF.length() - 1); // ����read()���Ă΂ꂽ�Ƃ��ɉ��s��̍ŏ��̕�����ǂݎ���悤�ɂ���
		int[] line = new int[list.size()];
		for (int j = 0; j < line.length; j++) {
			line[j] = list.get(j);
		}
		return line;
	}
}
