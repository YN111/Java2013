public class ThreadViewer {

	private long interval = 1000;

	/**
	 * �\���Ԋu��ݒ肵�܂�
	 * @param interval �\���Ԋu(msec)
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * �����Ŏw�肳�ꂽ�X���b�h�O���[�v���̃X���b�h�ƃX���b�h�O���[�v�̊K�w��<br>
	 * ����I�ɕ\������X���b�h���J�n���܂�<br>
	 * �\���Ԋu�̃f�t�H���g�l��1sec�ł�<br>
	 * setInterval���\�b�h�ŕ\���Ԋu��ύX�ł��܂�
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

		// �q�O���[�v���擾
		int activeGroupCount = group.activeGroupCount();
		ThreadGroup[] activeGroups = new ThreadGroup[activeGroupCount];
		group.enumerate(activeGroups, false);

		for (ThreadGroup activeGroup : activeGroups)
			if (activeGroup != null)
				printThreadTree(activeGroup, nest + 1); // �ċA

		// �O���[�v���̃X���b�h���擾
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
