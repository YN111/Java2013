// ex01_03 (P5)

public class Fibonacci {
	/**
	 * �l��50�����̃t�B�{�i�b�`�����\��
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println("Fibonacci sequence");
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi;	// �V����hi
			lo = hi - lo;	// �V����lo�́i���v - �Â�lo�j�A���Ȃ킿�Â�hi
		}
	}
}
