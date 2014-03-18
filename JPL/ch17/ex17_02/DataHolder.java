import java.io.*;
import java.lang.ref.*;

public class DataHolder {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	byte[] readFile(File file) {
		byte[] data;

		// �f�[�^���L�����Ă��邩���ׂ�
		if (lastFile != null && file.equals(lastFile.get())) {
			data = lastData.get();
			if (data != null) {
				return data;
			}
		}

		// �L�����Ă��Ȃ��̂œǂݍ���
		data = readByteFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	/**
	 * �t�@�C����ǂݍ��݁A�o�C�g�z���Ԃ��܂�
	 * @param file
	 * @return
	 */
	private static byte[] readByteFromFile(File file) {
		byte[] b = new byte[1];
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			while (fis.read(b) > 0) {
				baos.write(b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}
