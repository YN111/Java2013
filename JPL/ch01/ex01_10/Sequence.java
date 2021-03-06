// ex01_10 (P17)

/**
 * 数値と奇数・偶数の判定結果を保持
 */
public class Sequence {
	private int num; // 数値
	private boolean isEven; // 偶数ならばtrue

	public void setSequence(int num) {
		this.num = num;
		if (num % 2 == 0) {
			isEven = true;
		} else {
			isEven = false;
		}
	}

	public int getNum() {
		return num;
	}

	public boolean getIsEven() {
		return isEven;
	}
}
