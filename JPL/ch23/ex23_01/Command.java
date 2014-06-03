import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Command {

	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	public static void plugTogether(final InputStream in, final OutputStream out) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i;
				try {
					while ((i = in.read()) != -1) {
						out.write(i);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void plugTogether(OutputStream out, InputStream in) {
		plugTogether(in, out);
	}

}
