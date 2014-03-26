import java.io.File;

public class FileListViewer {

	/**
	 * �w�肳�ꂽ�f�B���N�g������w�肳�ꂽ�ڔ�������t�@�C����\�����܂�
	 * @param dir
	 * @param suffix
	 */
	public static void showFilterFileList(String dir, String suffix) {
		File file = new File(dir);
		String[] files = file.list(new SuffixFilter(suffix));
		System.out.println(files.length + " dir(s):");
		for (String fileName : files) {
			System.out.println("\t" + fileName);
		}
	}
}
