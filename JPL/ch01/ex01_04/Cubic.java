// ex01_04 (P5)

/** 
 * 1000以下の立法数を表示
 */
public class Cubic {
	private static final int MAX = 1000;
	
	public static void main(String[] args) {
		int index = 1;
		int score = 0;
		while (score < MAX) {
			score = index * index * index;
			System.out.println(index + ": " + score);
			index++;
		}
	}
}
