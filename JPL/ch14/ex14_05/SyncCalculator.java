public class SyncCalculator {
	private static int currentNum = 0;

	/**
	 * �l�����Z���ĐV���Ȓl��\�����܂�
	 * @param num ���Z����l
	 */
	public static void add(int num) {
		synchronized (SyncCalculator.class) {
			currentNum += num;
			System.out.println(currentNum);
		}
	}

}
