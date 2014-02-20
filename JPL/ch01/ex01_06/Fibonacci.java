// ex01_06 (P7)

/**
 * 値が50未満のフィボナッチ数列をタイトル付きで表示
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
			hi = lo + hi;	// 新しいhi
			lo = hi - lo;	// 新しいloは（合計 - 古いlo）、すなわち古いhi
		}
	}
}
