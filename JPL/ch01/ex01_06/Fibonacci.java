// ex01_06 (P7)

/**
 * �l��50�����̃t�B�{�i�b�`������^�C�g���t���ŕ\��
 */
public class Fibonacci {
	static final String TITLE = "Fibonacci sequence";
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi;	// �V����hi
			lo = hi - lo;	// �V����lo�́i���v - �Â�lo�j�A���Ȃ킿�Â�hi
		}
	}
}
