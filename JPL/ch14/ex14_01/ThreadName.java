public class ThreadName {

	/**
	 * このメソッドが呼び出されたスレッドの名前を表示します
	 */
	public static void showThreadName() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		showThreadName();
	}

}
