import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecuter {

	/**
	 * 引数に指定されたコマンドを実行し、結果を出力します。<br>
	 * 出力文字列中に指定された文字列が現れたら、その時点で出力を終了します。
	 * @param commands 実行するコマンド
	 * @param exitWord 出力結果に現れたら出力を終了させる文字列
	 * @return
	 * @throws IOException
	 */
	public static void executeCommand(String[] commands, String exitWord) throws IOException {
		Runtime r = Runtime.getRuntime();
		Process process = r.exec(commands, null);
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		int lineNum = 1;
		String line;
		while ((line = in.readLine()) != null) {
			if (line.contains(exitWord)) {
				return;
			}
			System.out.println(lineNum++ + " " + line);
		}
		while ((line = err.readLine()) != null) {
			if (line.contains(exitWord)) {
				return;
			}
			System.out.println(lineNum++ + " " + line);
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("引数に実行するコマンドを指定してください");
			return;
		}

		try {
			executeCommand(args, "c");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
