public class PrintString {
	
	/**
	 * 2つのcharを引数にとり、それらの文字とそれらの間の文字を表示します
	 */
	public static void printBetweenTwoChars(char a, char b) {
		// a < b となるように順番を入れ替える
		if (a > b) {
			char buf = a;
			a = b;
			b = buf;
		}
		
		for (char c = a; c <= b; c++) {
			System.out.print(c);
		}
		System.out.print("\n");
	}
	
	/**
	 * メインメソッド
	 * 正しく表示されるかテストします
	 */
	public static void main(String[] args) {
		char A = 'A';
		char z = 'z';
		printBetweenTwoChars(A, z);
		
		char ja_a = 'あ';
		char ja_n = 'ん';
		printBetweenTwoChars(ja_n, ja_a);
	}
}
