import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MemoryChecker {

	/**
	 * 利用可能なメモリ量を調べます
	 */
	public static void checkMemory() {
		Runtime rt = Runtime.getRuntime();
		System.out.println(rt.freeMemory());
		System.out.println(rt.totalMemory());
		System.out.println(rt.maxMemory());
	}

	/**
	 * ファイルを読み込み、バイト配列を返します
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
		System.out.println("===== 起動時 =====================");
		checkMemory();
		System.out.println("=================================");
		System.out.println();

		// 容量の大きいファイルをバイト配列に読み込む
		File file = new File("picture.jpg");

		@SuppressWarnings("unused")
		byte[] b = readByteFromFile(file);

		System.out.println("===== オブジェクト生成後 =============");
		checkMemory();
		System.out.println("=================================");
		System.out.println();

		b = null;
		Runtime.getRuntime().gc();

		System.out.println("===== GC後 =======================");
		checkMemory();
		System.out.println("=================================");
		System.out.println();
	}

}
