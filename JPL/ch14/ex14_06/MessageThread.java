public class MessageThread implements Runnable {

	private String msg;
	private int intervalSec;
	private int currentSec;
	private TimerThread timerThread;

	/**
	 * コンストラクタ
	 * @param msg 表示するメッセージ
	 * @param intervalSec メッセージを表示する間隔
	 * @param timerThread 時刻表示スレッドのオブジェクトへの参照
	 */
	MessageThread(String msg, int intervalSec, TimerThread timerThread) {
		this.msg = msg;
		this.intervalSec = intervalSec;
		this.timerThread = timerThread;
	}

	/**
	 * コンストラクタで指定された間隔でメッセージを表示します
	 */
	@Override
	public void run() {
		synchronized (timerThread) {
			while (true) {
				try {
					timerThread.wait();
					if (currentSec++ == (intervalSec - 1)) {
						System.out.println(msg);
						currentSec = 0;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
