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
			throw new AssertionError("���̃��\�b�h�͈���X���b�h�ȊO�̃X���b�h����g�p�ł��܂���");

		for (;;) {
			realPrint(requests.remove());
			waiting(100); // �|�[�����O
		}
	}

	private void realPrint(PrintJob job) {
		if (job == null)
			return;
		System.out.println("������������܂���");
	}

	private void waiting(long millis) {
		try {
			Thread.sleep(millis); // ��莞�ԑҋ@
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// �e�X�g
	public static void main(String[] args) {
		PrintServer server = new PrintServer();
		server.print(new PrintJob());
		server.print(new PrintJob());
		server.run();
	}

}
