import java.io.*;

public class FindWord {
	
	/**
	 * �w�肳�ꂽ�t�@�C����ǂݍ���Ŏw�肳�ꂽ�P����������܂�<br>
	 * �P�ꂪ�������ꂽ���ׂĂ̍s���A�s�̑O�ɍs�ԍ������ĕ\�����܂�
	 * @param filePath �P�ꌟ�����s���t�@�C���̃t�@�C���p�X
	 * @param match �����Ώۂ̒P��
	 * @throws IOException
	 */
	public static void findAndPrint(String filePath, String match) throws IOException {
		FileReader fileIn = new FileReader(filePath);
		LineNumberReader in = new LineNumberReader(fileIn);
		String line;
		while ((line = in.readLine()) != null) {
			if (line.indexOf(match) != -1) {
				System.out.println("line:" + in.getLineNumber() + "  " + line);
			}
		}
	}
	
}
