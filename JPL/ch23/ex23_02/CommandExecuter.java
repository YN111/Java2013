import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecuter {

	/**
	 * 引数に指定されたコマンドを実行し、結果を出力します。
	 * @param commands
	 * @return
	 * @throws IOException
	 */
	public static void executeCommand(String[] commands) throws IOException {
		Runtime r = Runtime.getRuntime();
		Process process = r.exec(commands, null);
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		int lineNum = 1;
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(lineNum++ + " " + line);
		}
		while ((line = err.readLine()) != null) {
			System.out.println(lineNum++ + " " + line);
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("引数に実行するコマンドを指定してください");
			return;
		}

		try {
			executeCommand(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
