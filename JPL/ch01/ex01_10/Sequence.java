// ex01_10 (P17)

/**
 * ”’l‚ÆŠï”E‹ô”‚Ì”»’èŒ‹‰Ê‚ğ•Û
 */
public class Sequence {
	private int num; // ”’l
	private boolean isEven; // ‹ô”‚È‚ç‚Îtrue

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
