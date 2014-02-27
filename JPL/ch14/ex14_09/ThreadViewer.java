public class ThreadViewer {

	private long interval = 1000;

	/**
	 * 表示間隔を設定します
	 * @param interval 表示間隔(msec)
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * 引数で指定されたスレッドグループ内のスレッドとスレッドグループの階層を<br>
	 * 定期的に表示するスレッドを開始します<br>
	 * 表示間隔のデフォルト値は1secです<br>
	 * setIntervalメソッドで表示間隔を変更できます
	 */
	public void start(final ThreadGroup group) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					printThreadTree(group, 0);
					System.out.println();

					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void printThreadTree(ThreadGroup group, int nest) {
		for (int i = 0; i < nest; i++)
			System.out.print("  ");
		System.out.println("ThreadGroup: " + group.getName());

		// 子グループを取得
		int activeGroupCount = group.activeGroupCount();
		ThreadGroup[] activeGroups = new ThreadGroup[activeGroupCount];
		group.enumerate(activeGroups, false);

		for (ThreadGroup activeGroup : activeGroups)
			if (activeGroup != null)
				printThreadTree(activeGroup, nest + 1); // 再帰

		// グループ内のスレッドを取得
		int activeCount = group.activeCount();
		Thread[] actives = new Thread[activeCount];
		group.enumerate(actives, false);

		for (Thread active : actives) {
			if (active != null) {
				for (int i = 0; i < nest; i++)
					System.out.print("  ");
				System.out.println("Thread : " + active.getName());
			}
		}
	}
}
