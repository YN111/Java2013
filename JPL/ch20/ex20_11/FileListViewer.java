import java.io.File;

public class FileListViewer {

	/**
	 * 指定されたディレクトリから指定された接尾語を持つファイルを表示します
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
