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
	 * 1s•ª‚Ì•¶š—ñ‚ğ“Ç‚İ‚ñ‚Å”z—ñ‚Æ‚µ‚Ä•Ô‚µ‚Ü‚·
	 * @return
	 * @throws IOException
	 */
	public int[] readLine() throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();

		int i;
		while ((i = read()) != -1 && i != '\n' && i != '\r') {
			list.add(i);
		}
		super.skip(CRLF.length() - 1); // Ÿ‚Éread()‚ªŒÄ‚Î‚ê‚½‚Æ‚«‚É‰üsŒã‚ÌÅ‰‚Ì•¶š‚ğ“Ç‚İæ‚ê‚é‚æ‚¤‚É‚·‚é
		int[] line = new int[list.size()];
		for (int j = 0; j < line.length; j++) {
			line[j] = list.get(j);
		}
		return line;
	}
}
