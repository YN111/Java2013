public class HelloWorld {
	public static void main(String[] args) {
		// 表示に必要な文字のUnicodeエスケープシーケンス
		char H = '\u0048';
		char e = '\u0065';
		char l = '\u006c';
		char o = '\u006f';
		char W = '\u0057';
		char r = '\u0072';
		char d = '\u0064';
		char comma = '\u002c';	// コンマ
		char space = '\u0020';	// スペース
		
		System.out.println(String.valueOf(H) + e + l + l + o + comma + space + W + o + r + l + d);
	}
}
