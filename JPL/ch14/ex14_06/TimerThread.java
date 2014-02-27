public class TimerThread implements Runnable {

	public static final int SHOW_INTERVAL = 1000; // 経過時間の表示間隔(msec)
	private long elapsedTime = 0; // 経過時間(msec)

	/**
	 * 1000msecごとに経過時間を表示します
	 */
	@Override
	public synchronized void run() {
		while (true) {
			try {
				wait(SHOW_INTERVAL); // 1sec待機
				elapsedTime += SHOW_INTERVAL;
				System.out.println(elapsedTime / 1000);
				notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
