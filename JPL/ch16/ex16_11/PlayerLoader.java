import java.io.*;

public class PlayerLoader extends ClassLoader {

	/**
	 * 指定されたクラスのバイトコードをロードします
	 * @param name
	 * @return
	 */
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] buf;
		try {
			buf = bytesForClass(name);
			return defineClass(name, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}

	}

	/**
	 * 指定されたクラス名に対応するクラスのバイトコードを返します
	 * @param name
	 * @return
	 */
	protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
		FileInputStream in = null;
		try {
			in = streamFor(name + ".class");
			int length = in.available();
			if (length == 0) {
				throw new ClassNotFoundException(name);
			}
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * 指定されたクラス名に対応するクラスのFileInputStreamを返します
	 * @param name
	 * @return
	 */
	private FileInputStream streamFor(String name) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fis;
	}
}
