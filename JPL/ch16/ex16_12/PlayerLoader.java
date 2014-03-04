import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

public class PlayerLoader extends ClassLoader {

	@Override
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
	 * 指定された名前のリソースが存在すれば対応するURLオブジェクトを返します
	 */
	@Override
	@SuppressWarnings("deprecation")
	public java.net.URL findResource(String name) {
		File f = new File(name);
		if (!f.exists()) {
			return null;
		}
		try {
			return f.toURL();
		} catch (java.net.MalformedURLException e) {
			return null; // 起きるはずがない
		}
	}

	/**
	 * 指定された名前のフォルダ内にあるリソースを全て返します
	 */
	@Override
	@SuppressWarnings("deprecation")
	public Enumeration<URL> findResources(String name) {
		File file = new File(name);
		if (!file.exists()) {
			return null;
		}

		File[] files = file.listFiles();
		Vector<URL> v = new Vector<URL>();
		for (File f : files) {
			try {
				v.add(f.toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return v.elements();
	}

	/**
	 * 指定されたクラスをに対するバイトコードを返します
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
