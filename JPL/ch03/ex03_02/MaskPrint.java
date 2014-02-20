public class MaskPrint {
	public static void main(String[] args) {
		new Y();
	}
}

abstract class X {
	{
		print("�t�B�[���h�Ƀf�t�H���g�l���ݒ肳���");
	}

	protected int xMask = 0x00ff;
	protected int fullMask;

	{
		print("X�̃t�B�[���h�̏�����");
	}

	public X() {
		fullMask = xMask;
		print("X�̃R���X�g���N�^�����s�����");
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	/**
	 * �}�X�N�̒l�̕ω����o�͂���
	 * 
	 * @param �\�����镶����
	 */
	abstract void print(String message);
}

class Y extends X {

	private static int step = 0; // �X�e�b�v��
	protected int yMask = 0xff00;

	{
		print("Y�̃t�B�[���h�̏�����");
	}

	public Y() {
		fullMask |= yMask;
		print("Y�̃R���X�g���N�^�����s�����");
	}

	/**
	 * �}�X�N�̒l�̕ω����o�͂���
	 * 
	 * @param �\�����镶����
	 */
	protected void print(String message) {
		System.out.printf("%d %-30s 0x%04x 0x%04x 0x%04x %n", step++, message,
				xMask, yMask, fullMask);
	}
}