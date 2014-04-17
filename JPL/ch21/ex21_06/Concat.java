import java.io.*;
import java.util.*;

/* P463のConcatクラスを書き換え */
public class Concat {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			printStream(System.in);
		} else {
			InputStream fileIn;
			InputStream bufIn;
			List<InputStream> inputs = new ArrayList<InputStream>(args.length);
			for (String arg : args) {
				fileIn = new FileInputStream(arg);
				bufIn = new BufferedInputStream(fileIn);
				inputs.add(bufIn);
			}

			// MyEnumerationで書き換え
			MyEnumeration<InputStream> files = new MyEnumeration<InputStream>(inputs);
			while (files.hasMoreElements()) {
				printStream(files.nextElement());
			}
		}
	}

	/**
	 * 引数に指定されたストリームを出力します。
	 * @param in
	 * @throws IOException
	 */
	private static void printStream(InputStream in) throws IOException {
		int ch;
		while ((ch = in.read()) != -1) {
			System.out.print((char) ch);
		}
	}
}
