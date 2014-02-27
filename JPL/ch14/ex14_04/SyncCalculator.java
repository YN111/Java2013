public class SyncCalculator {
	private static int currentNum = 0;

	/**
	 * 値を加算して新たな値を表示します
	 * @param num 加算する値
	 */
	public static synchronized void add(int num) {
		currentNum += num;
		System.out.println(currentNum);
	}

}
