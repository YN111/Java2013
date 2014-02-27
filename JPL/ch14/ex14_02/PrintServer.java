public class PrintServer implements Runnable {

	private final PrintQueue requests = new PrintQueue();
	private Thread printThread;

	public PrintServer() {
		printThread = new Thread(this);
		printThread.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (!Thread.currentThread().equals(printThread))
			throw new AssertionError("このメソッドは印刷スレッド以外のスレッドから使用できません");

		for (;;) {
			realPrint(requests.remove());
			waiting(100); // ポーリング
		}
	}

	private void realPrint(PrintJob job) {
		if (job == null)
			return;
		System.out.println("印刷を完了しました");
	}

	private void waiting(long millis) {
		try {
			Thread.sleep(millis); // 一定時間待機
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// テスト
	public static void main(String[] args) {
		PrintServer server = new PrintServer();
		server.print(new PrintJob());
		server.print(new PrintJob());
		server.run();
	}

}
