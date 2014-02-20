public class BitCount {

	// 課題で作成したアルゴリズム
	public static int bitCount1(int num) {
		int count = 0;

		for (int i = 0; i < 32; i++)
			if ((num >> i & 1) == 1)
				count++;

		return count;
	}

	// 公開されているアルゴリズム
	// http://www.docjar.com/html/api/java/lang/Integer.java.html (line 1132)
	public static int bitCount2(int i) {
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}

	// メインメソッド 速度の比較
	public static void main(String[] args) {
		long start = System.nanoTime();
		BitCount.bitCount1(100);
		long end = System.nanoTime();
		System.out.println("作成したアルゴリズム： " + (end - start));

		start = System.nanoTime();
		BitCount.bitCount2(100);
		end = System.nanoTime();
		System.out.println("公開されているアルゴリズム： " + (end - start));

	}
}
