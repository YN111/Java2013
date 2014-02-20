public class MaskPrint {
	public static void main(String[] args) {
		new Y();
	}
}

abstract class X {
	{
		print("フィールドにデフォルト値が設定される");
	}

	protected int xMask = 0x00ff;
	protected int fullMask;

	{
		print("Xのフィールドの初期化");
	}

	public X() {
		fullMask = xMask;
		print("Xのコンストラクタが実行される");
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	/**
	 * マスクの値の変化を出力する
	 * 
	 * @param 表示する文字列
	 */
	abstract void print(String message);
}

class Y extends X {

	private static int step = 0; // ステップ数
	protected int yMask = 0xff00;

	{
		print("Yのフィールドの初期化");
	}

	public Y() {
		fullMask |= yMask;
		print("Yのコンストラクタが実行される");
	}

	/**
	 * マスクの値の変化を出力する
	 * 
	 * @param 表示する文字列
	 */
	protected void print(String message) {
		System.out.printf("%d %-30s 0x%04x 0x%04x 0x%04x %n", step++, message,
				xMask, yMask, fullMask);
	}
}