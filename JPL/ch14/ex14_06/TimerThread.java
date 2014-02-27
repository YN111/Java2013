public class TimerThread implements Runnable {

	public static final int SHOW_INTERVAL = 1000; // �o�ߎ��Ԃ̕\���Ԋu(msec)
	private long elapsedTime = 0; // �o�ߎ���(msec)

	/**
	 * 1000msec���ƂɌo�ߎ��Ԃ�\�����܂�
	 */
	@Override
	public synchronized void run() {
		while (true) {
			try {
				wait(SHOW_INTERVAL); // 1sec�ҋ@
				elapsedTime += SHOW_INTERVAL;
				System.out.println(elapsedTime / 1000);
				notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
