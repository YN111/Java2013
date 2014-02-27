/*
 * 実行した結果
 * 
 * <設定したデータ>
 * doYield : true と false の両方を設定（各20回実行）
 * howOften ： 10
 * word ： "Did" と "DiDNot" の2語を設定
 * 
 * <結果>
 * doYieldがtrueとfalseのいずれにおいても、出力結果は毎回同じではなかった
 */

public class Babble extends Thread {

	static boolean doYield; // 他のスレッドに実行を譲るか
	static int howOften; // 表示する回数
	private String word; // このスレッドの単語

	Babble(String whatToSay) {
		word = whatToSay;
	}

	@Override
	public void run() {
		for (int i = 0; i < howOften; i++) {
			System.out.println(word);
			if (doYield)
				Thread.yield(); // 他のスレッドを走らせる
		}
	}

	/**
	 * メインメソッド<br>
	 * 以下のフォーマットに従って引数を指定する必要があります
	 * @param args <br>
	 * args[0]:他のスレッドに実行を譲るか (true or false)<br>
	 * args[1]:表示する回数<br>
	 * args[2]以降：表示する単語
	 */
	public static void main(String[] args) {
		doYield = new Boolean(args[0]).booleanValue();
		howOften = Integer.parseInt(args[1]);

		for (int i = 2; i < args.length; i++)
			new Babble(args[i]).start();
	}

}
