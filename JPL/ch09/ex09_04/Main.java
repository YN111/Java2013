// 答え合わせ用
public class Main {
	public static void main(String[] args) {
		System.out.println(0.0 % 0.0); // int型, 6
		System.out.println(3 << 2L - 1); // int型, 6
		System.out.println((3L << 2) - 1); // long型, 11L
		System.out.println(10 < 12 == 6 > 17); // boolean型, false
		System.out.println(10 << 12 == 6 >> 17); // boolean型, false
		System.out.println(13.5e-1 % Float.POSITIVE_INFINITY); // double型, 13.5e-1
		System.out.println(Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY); // double型, NaN
		System.out.println(Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY); // double型, Infinity
		System.out.println(0.0 / -0.0 == -0.0 / 0.0); // boolean型, false （左辺も右辺もNaN）
		System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE); // int型, -1
		System.out.println(Long.MAX_VALUE + 5); // long型, Long.MIN_VALUE + 4
		System.out.println((short) 5 * (byte) 10); // int型, 50
		int i = 3;
		System.out.println(i < 15 ? 1.72e3f : 0); // float型, 1.72e3f
		System.out.println(i++ + i++ + --i); // int型, 11
	}

}
