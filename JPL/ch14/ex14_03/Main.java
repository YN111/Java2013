public class Main {

	/**
	 * ���C�����\�b�h<br>
	 * �����̃X���b�h�𐶐�����add���\�b�h���J��Ԃ��Ăяo���܂�
	 * @param args
	 */
	public static void main(String[] args) {
		final SyncCalculator sc = new SyncCalculator();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					sc.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					sc.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					sc.add(1);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					sc.add(1);
				}
			}
		}).start();
	}

}
