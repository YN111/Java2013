import java.io.*;

public class FileInfo {

	/**
	 * 引数で渡されたパスが表すファイルが持つ全ての情報を表示します
	 * @param path
	 * @param pathList
	 */
	public static void showAllInfo(String path, String... pathList) {
		try {
			System.out.println(getFileInfo(path));
			if (pathList != null) {
				for (String str : pathList) {
					System.out.println(getFileInfo(str));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getFileInfo(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			return "File not found : " + path;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("===== FILE INFORMATION ========\n");
		sb.append("Name: " + file.getName() + "\n");
		sb.append("Path: " + file.getPath() + "\n");
		sb.append("AbsolutePath: " + file.getAbsolutePath() + "\n");
		sb.append("CanonicalPath: " + file.getCanonicalPath() + "\n");
		sb.append("Parent: " + file.getParent() + "\n");
		sb.append("CanRead: " + file.canRead() + "\n");
		sb.append("CanWrite: " + file.canWrite() + "\n");
		sb.append("IsFile: " + file.isFile() + "\n");
		sb.append("IsDirectory: " + file.isDirectory() + "\n");
		sb.append("IsHidden: " + file.isHidden() + "\n");
		sb.append("LastModified: " + file.lastModified() + "\n");
		sb.append("Length: " + file.length() + "\n");
		sb.append("================================\n");
		sb.append("\n");

		return sb.toString();
	}
}
