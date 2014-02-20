public class PascalTriangle {
	private int depth = 12; // 深さ（デフォルト値は12）
	private int[][] pascal; // パスカルの三角形の計算結果を格納する配列

	/**
	 * コンストラクタ
	 * 深さ12のパスカルの三角形を作成します
	 */
	PascalTriangle() {
		pascal = new int[depth][]; // 配列を初期化
		for (int i = 0; i < depth; i++)
			pascal[i] = new int[i + 1]; // 各行を適切な長さの配列とする
	}

	/**
	 * コンストラクタ
	 * 深さを12から変更する場合に使用します
	 * @param depth 深さ
	 */
	PascalTriangle(int depth) {
		if (depth < 1)
			throw new AssertionError();
		else
			this.depth = depth; // 深さを変更

		pascal = new int[depth][]; // 配列を初期化
		for (int i = 0; i < depth; i++) {
			pascal[i] = new int[i + 1]; // 各行を適切な長さの配列とする
		}
	}

	/**
	 * パスカルの三角形を計算します
	 */
	public void calc() {
		pascal[0][0] = 1; // 頂点の値を設定

		for (int i = 1; i < pascal.length; i++) {
			for (int j = 0; j < pascal[i].length; j++) {

				/* if (j == 0) // 各行の左端
					pascal[i][j] = pascal[i - 1][j];
				else if (j == pascal[i].length - 1) // 各行の右端
					pascal[i][j] = pascal[i - 1][j - 1];
				else
					pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j]; */

				// 条件演算子を使用するよう変更
				pascal[i][j] = (j == 0 || j == pascal[i].length - 1) ? 1 : pascal[i - 1][j - 1]
						+ pascal[i - 1][j];
			}
		}
	}

	/**
	 * パスカルの三角形の配列を取得します
	 * @return パスカルの三角形の配列
	 */
	public int[][] getPascal() {
		return pascal;
	}

	/**
	 * パスカルの三角形を表示します
	 */
	public void print() {
		for (int i = 0; i < pascal.length; i++) {
			for (int j = 0; j < pascal[i].length; j++) {
				System.out.print(pascal[i][j]);
				if (j != pascal[i].length - 1)
					System.out.print(", ");
			}
			System.out.printf("%n");
		}
	}
}
