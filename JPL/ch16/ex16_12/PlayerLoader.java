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
	 * �w�肳�ꂽ���O�̃��\�[�X�����݂���ΑΉ�����URL�I�u�W�F�N�g��Ԃ��܂�
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
			return null; // �N����͂����Ȃ�
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃t�H���_���ɂ��郊�\�[�X��S�ĕԂ��܂�
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
	 * �w�肳�ꂽ�N���X���ɑ΂���o�C�g�R�[�h��Ԃ��܂�
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
