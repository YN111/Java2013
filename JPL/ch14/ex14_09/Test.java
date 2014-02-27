/*
 * テスト用のスレッドの存在期間一覧
 * 
 *          0  1  2  3  4  5  6  7  8  9 (sec)
 * main1  : <=============>|  |  |  |  |
 * main2  : <=>|  |  |  |  |  |  |  |  |
 * main3  : |  |  |  <==========>|  |  |
 * subAa1 : <=======>|  |  |  |  |  |  |
 * subAa2 : |  |  |  <================>|
 * subAb  : |  |  |  |  |  |  <=======>|
 * subB1  : <===================>|  |  |
 * subB2  : |  |  |  |  |  |  <=>|  |  |
 */

public class Test {

	public static void main(String[] args) {
		ThreadGroup groupMain = new ThreadGroup("Main");
		ThreadGroup groupSubA = new ThreadGroup(groupMain, "SubA");
		ThreadGroup groupSubAa = new ThreadGroup(groupSubA, "SubAa");
		ThreadGroup groupSubAb = new ThreadGroup(groupSubA, "SubAb");
		ThreadGroup groupSubB = new ThreadGroup(groupMain, "SubB");
		ThreadViewer viewer = new ThreadViewer();

		Thread main1 = new Thread(groupMain, new Runnable() {
			@Override
			public void run() {
				waiting(5000);
			}
		}, "main1");
		main1.start(); // 5秒で消滅

		Thread main2 = new Thread(groupMain, new Runnable() {
			@Override
			public void run() {
				waiting(1000);
			}
		}, "main2");
		main2.start(); // 1秒で消滅

		Thread subAa1 = new Thread(groupSubAa, new Runnable() {
			@Override
			public void run() {
				waiting(3000);
			}
		}, "subAa1");
		subAa1.start(); // 3秒で消滅

		Thread subB1 = new Thread(groupSubA, new Runnable() {
			@Override
			public void run() {
				waiting(7000);
			}
		}, "subB1");
		subB1.start(); // 7秒で消滅

		viewer.start(groupMain); // 表示を開始
		waiting(3000);

		Thread main3 = new Thread(groupMain, new Runnable() {
			@Override
			public void run() {
				waiting(4000);
			}
		}, "main3");
		main3.start(); // 3秒後に開始し、4秒で消滅

		Thread subAa2 = new Thread(groupSubAa, new Runnable() {
			@Override
			public void run() {
				waiting(6000);
			}
		}, "subAa2");
		subAa2.start(); // 3秒後に開始し、6秒で消滅

		waiting(3000);

		Thread subAb = new Thread(groupSubAb, new Runnable() {
			@Override
			public void run() {
				waiting(3000);
			}
		}, "subAb");
		subAb.start(); // 6秒後に開始し、3秒で消滅

		Thread subB2 = new Thread(groupSubB, new Runnable() {
			@Override
			public void run() {
				waiting(1000);
			}
		}, "subB2");
		subB2.start(); // 6秒後に開始し、1秒で消滅
	}

	/**
	 * 指定された時間カレントスレッドをスリープします
	 * @param millis
	 */
	public static void waiting(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
