public class ThreadName {

	/**
	 * ���̃��\�b�h���Ăяo���ꂽ�X���b�h�̖��O��\�����܂�
	 */
	public static void showThreadName() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		showThreadName();
	}

}
