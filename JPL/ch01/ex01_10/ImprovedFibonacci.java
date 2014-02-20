// ex01_10 (P17)

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけてフィボナッチ数列の 最初の方の要素を配列に保持
	 */
	public static void main(String[] args) {
		Sequence arr[] = new Sequence[MAX_INDEX];
		String[] output = new String[MAX_INDEX]; // 出力結果格納用配列

		for (int i = 0; i < MAX_INDEX; i++) {
			arr[i] = new Sequence();
		}

		arr[0].setSequence(1); // lo
		arr[1].setSequence(1); // hi
		int i = 2;
		while (i < MAX_INDEX) {
			arr[i].setSequence(arr[i - 1].getNum() + arr[i - 2].getNum()); // 1つ前の要素と2つ前の要素を加算
			i++;
		}

		String mark;
		for (int j = 0; j < MAX_INDEX; j++) {
			if (arr[j].getIsEven() == true) {
				mark = " *";
			} else {
				mark = "";
			}
			output[j] = (MAX_INDEX - j) + ": " + arr[j].getNum() + mark;
		}
	}
}
