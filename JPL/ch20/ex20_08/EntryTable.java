import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class EntryTable {

	/**
	 * %%�Ŏn�܂�s�ŕ�������Ă���G���g���[�����t�@�C����ǂ݂���<br>
	 * �e�G���g���[�������_���ɏo�͂��܂�
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
	 * %%�ŕ�������Ă���t�@�C���̊e�G���g���̊J�n�ʒu�����e�[�u�����쐬���܂�
	 * @param raf
	 */
	private static ArrayList<Long> createTable(RandomAccessFile raf) {
		ArrayList<Long> table = new ArrayList<Long>();
		table.add(0L);
		try {
			String line;
			while ((line = raf.readLine()) != null) {
				if (!line.startsWith("%%")) { // %%�Ŏn�܂�Ȃ��s�͏���
					table.remove(table.size() - 1);
				}
				table.add(raf.getFilePointer());
			}
		} catch (EOFException e) {
			// �ǂݍ��ݏI��
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
