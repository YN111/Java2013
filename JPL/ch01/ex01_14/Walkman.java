// ex01_14 (P23)

public class Walkman {
	private enum Statement {STOP, RESUME, PLAY};
	private static Statement nowStatement;
	private static int volume;
	
	// ボリューム設定
	public static void setVolume(int vol) {
		volume = vol;
	}
	
	// ボリューム取得
	public static int getVolume() {
		return volume;
	}
	
	// 状態設定
	public static void setStatement (Statement st) {
		nowStatement = st;
	}
	
	// 状態取得
	public static Statement getStatement() {
		return nowStatement;
	}
	
	// 再生
	public void playMusic() {
		// 端子1に音信号を出力する
	}
}
