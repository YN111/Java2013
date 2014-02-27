public class Main {

	/**
	 * メインメソッド<br>
	 * 複数のスレッドを生成してaddメソッドを繰り返し呼び出します
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					SyncCalculator.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					SyncCalculator.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					SyncCalculator.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					SyncCalculator.add(1);
				}
			}
		}).start();
	}

}
