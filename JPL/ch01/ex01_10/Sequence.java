// ex01_10 (P17)

/**
 * ���l�Ɗ�E�����̔��茋�ʂ�ێ�
 */
public class Sequence {
	private int num; // ���l
	private boolean isEven; // �����Ȃ��true

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
