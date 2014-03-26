import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class EntryTable {

	/**
	 * %%で始まる行で分割されているエントリーを持つファイルを読みこみ<br>
	 * 各エントリーをランダムに出力します
	 * @param path
	 * @throws IOException 
	 */
	public static void randomPrint(String path) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		ArrayList<Long> table = createTable(raf);

		Random rd = new Random();
		int size = table.size();
		for (int i = 0; i < size - 1; i++) {
			raf.seek(table.remove(rd.nextInt(table.size() - 1)));
			System.out.println(raf.readLine());
		}
	}

	/**
	 * %%で分割されているファイルの各エントリの開始位置を持つテーブルを作成します
	 * @param raf
	 */
	private static ArrayList<Long> createTable(RandomAccessFile raf) {
		ArrayList<Long> table = new ArrayList<Long>();
		table.add(0L);
		try {
			String line;
			while ((line = raf.readLine()) != null) {
				if (!line.startsWith("%%")) { // %%で始まらない行は消す
					table.remove(table.size() - 1);
				}
				table.add(raf.getFilePointer());
			}
		} catch (EOFException e) {
			// 読み込み終了
		} catch (IOException e) {
			e.printStackTrace();
		}
		return table;
	}

	public static void main(String[] args) {
		try {
			EntryTable.randomPrint("test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
