public class SyncCalculator {
	private int currentNum = 0;

	/**
	 * �l�����Z���ĐV���Ȓl��\�����܂�
	 * @param num ���Z����l
	 */
	public synchronized void add(int num) {
		currentNum += num;
		System.out.println(currentNum);
	}

}
