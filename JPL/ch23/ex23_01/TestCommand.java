import java.io.IOException;

public class TestCommand {

	public static void main(String[] args) throws InterruptedException {
		try {
			Process proc = Command.userProg("ipconfig");
			proc.waitFor();
			proc.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
