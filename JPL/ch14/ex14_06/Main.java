public class Main {

	/**
	 * ���C�����\�b�h<br>
	 * �ȉ���3�̃X���b�h���N�����܂�<br>
	 *  (1)�o�ߎ��ԕ\���X���b�h<br>
	 *  (2)15�b���ƂɃ��b�Z�[�W��\������X���b�h<br>
	 *  (3)7�b���ƂɃ��b�Z�[�W��\������X���b�h
	 * @param args
	 */
	public static void main(String[] args) {
		TimerThread timerThread = new TimerThread(); // �o�ߎ��ԕ\���X���b�h
		MessageThread msgThread15 = new MessageThread("msg1", 15, timerThread); // ���b�Z�[�W�\���X���b�h(15�b)
		MessageThread msgThread7 = new MessageThread("msg2", 7, timerThread); // ���b�Z�[�W�\���X���b�h(7�b)

		new Thread(timerThread).start();
		new Thread(msgThread15).start();
		new Thread(msgThread7).start();
	}

}
