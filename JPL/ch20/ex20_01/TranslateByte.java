import java.io.*;

public class TranslateByte {

	public static void translate(InputStream in, OutputStream out, byte from, byte to)
			throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
		out.flush();
	}

	public static void main(String[] args) {
		byte[] input = "test".getBytes();
		byte from = (byte) 't';
		byte to = (byte) 'T';

		try {
			// ByteArray
			InputStream byteArrIs = new ByteArrayInputStream(input);
			OutputStream byteArrOs = new ByteArrayOutputStream();
			translate(byteArrIs, byteArrOs, from, to);
			System.out.println(byteArrOs.toString());

			// File
			InputStream fileIs = new FileInputStream("test.txt");
			OutputStream fileOs = new FileOutputStream("test_out.txt");
			translate(fileIs, fileOs, from, to);
			// 書き込めたか調べる
			FileReader fReader = new FileReader("test_out.txt");
			int ch;
			while ((ch = fReader.read()) != -1) {
				System.out.print((char) ch);
			}
			System.out.println();

			// Buffered
			InputStream bufByteArrIs = new ByteArrayInputStream(input);
			OutputStream bufByteArrOs = new ByteArrayOutputStream();
			InputStream bufIs = new BufferedInputStream(bufByteArrIs);
			OutputStream bufOs = new BufferedOutputStream(bufByteArrOs);
			translate(bufIs, bufOs, from, to);
			System.out.println(bufByteArrOs.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
