public class SyncCalculator {
	private static int currentNum = 0;

	/**
	 * �l�����Z���ĐV���Ȓl��\�����܂�
	 * @param num ���Z����l
	 */
	public static synchronized void add(int num) {
		currentNum += num;
		System.out.println(currentNum);
	}

}
