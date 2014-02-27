public class Main {

	/**
	 * メインメソッド<br>
	 * 以下の3つのスレッドを起動します<br>
	 *  (1)経過時間表示スレッド<br>
	 *  (2)15秒ごとにメッセージを表示するスレッド<br>
	 *  (3)7秒ごとにメッセージを表示するスレッド
	 * @param args
	 */
	public static void main(String[] args) {
		TimerThread timerThread = new TimerThread(); // 経過時間表示スレッド
		MessageThread msgThread15 = new MessageThread("msg1", 15, timerThread); // メッセージ表示スレッド(15秒)
		MessageThread msgThread7 = new MessageThread("msg2", 7, timerThread); // メッセージ表示スレッド(7秒)

		new Thread(timerThread).start();
		new Thread(msgThread15).start();
		new Thread(msgThread7).start();
	}

}
