// ex01_14 (P23)

public class Walkman {
	private enum Statement {STOP, RESUME, PLAY};
	private static Statement nowStatement;
	private static int volume;
	
	// �{�����[���ݒ�
	public static void setVolume(int vol) {
		volume = vol;
	}
	
	// �{�����[���擾
	public static int getVolume() {
		return volume;
	}
	
	// ��Ԑݒ�
	public static void setStatement (Statement st) {
		nowStatement = st;
	}
	
	// ��Ԏ擾
	public static Statement getStatement() {
		return nowStatement;
	}
	
	// �Đ�
	public void playMusic() {
		// �[�q1�ɉ��M�����o�͂���
	}
}
