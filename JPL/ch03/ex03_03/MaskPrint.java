/*
 * (1) スーパークラスでMaskの値を取得する抽象クラスを作る
 * (2) これを拡張したクラスでオーバーライドする
 */

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
		getYMask();
		print("拡張したクラスから値を取得する");
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	abstract int getYMask();

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

	public int getYMask() {
		return yMask;
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