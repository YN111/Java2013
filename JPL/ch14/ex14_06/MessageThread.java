public class MessageThread implements Runnable {

	private String msg;
	private int intervalSec;
	private int currentSec;
	private TimerThread timerThread;

	/**
	 * �R���X�g���N�^
	 * @param msg �\�����郁�b�Z�[�W
	 * @param intervalSec ���b�Z�[�W��\������Ԋu
	 * @param timerThread �����\���X���b�h�̃I�u�W�F�N�g�ւ̎Q��
	 */
	MessageThread(String msg, int intervalSec, TimerThread timerThread) {
		this.msg = msg;
		this.intervalSec = intervalSec;
		this.timerThread = timerThread;
	}

	/**
	 * �R���X�g���N�^�Ŏw�肳�ꂽ�Ԋu�Ń��b�Z�[�W��\�����܂�
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
