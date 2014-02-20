// ex01_09 (P17)

public class Fibonacci {
	
	static final int MAX_INDEX = 9;	// 要素数
	
	/**
	 * 値が50未満のフィボナッチ数列を表示
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = new int[MAX_INDEX];
		arr[0] = 1;
		arr[1] = 1;
		int i = 2;
		while (i < MAX_INDEX) {
			arr[i] = arr[i-2] + arr[i-1];
			i++;
		}
		for (int j = 0; j < arr.length; j++) {
			System.out.println(arr[j]);
		}
	}
}
