import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MemoryChecker {

	/**
	 * ���p�\�ȃ������ʂ𒲂ׂ܂�
	 */
	public static void checkMemory() {
		Runtime rt = Runtime.getRuntime();
		System.out.println(rt.freeMemory());
		System.out.println(rt.totalMemory());
		System.out.println(rt.maxMemory());
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

	public static void main(String[] args) {
		System.out.println("===== �N���� =====================");
		checkMemory();
		System.out.println("=================================");
		System.out.println();

		// �e�ʂ̑傫���t�@�C�����o�C�g�z��ɓǂݍ���
		File file = new File("picture.jpg");

		@SuppressWarnings("unused")
		byte[] b = readByteFromFile(file);

		System.out.println("===== �I�u�W�F�N�g������ =============");
		checkMemory();
		System.out.println("=================================");
		System.out.println();

		b = null;
		Runtime.getRuntime().gc();

		System.out.println("===== GC�� =======================");
		checkMemory();
		System.out.println("=================================");
		System.out.println();
	}

}
