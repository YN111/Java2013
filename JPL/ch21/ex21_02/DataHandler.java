import java.io.*;
import java.util.WeakHashMap;

public class DataHandler {
	private WeakHashMap<File, byte[]> lastData = new WeakHashMap<File, byte[]>();
	
	byte[] readFile(File file) {
		byte[] data;
		
		// データを記憶しているか調べる
		if (lastData.containsKey(file)) {
			data = lastData.get(file);
			if (data != null) {
				return data;
			}
		}
		
		// 記憶していないので読み込む
		data = readByteFromFile(file);
		lastData.put(file, data);
		return data;
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
}
