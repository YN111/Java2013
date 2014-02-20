/*
 * ex07_03の問題
 * do-while文は使用していない
 * <理由> 深さが1のパスカルの三角形を計算する際に、whileループが1度でも実行されると正しい結果を得られないため
 */

public class PascalTriangle {
	private int depth = 12; // 深さ（デフォルト値は12）
	private int[][] pascal; // パスカルの三角形の計算結果を格納する配列

	/**
	 * 引数なしコンストラクタ
	 */
	PascalTriangle() {
		pascal = new int[depth][]; // 配列を初期化

		int i = 0;
		while (i < depth) {
			pascal[i] = new int[i + 1]; // 各行を適切な長さの配列とする
			i++;
		}
	}

	/**
	 * コンストラクタ。深さを12から変更する場合に使用
	 */
	PascalTriangle(int depth) {
		if (depth < 1)
			throw new AssertionError();
		else
			this.depth = depth; // 深さを変更

		pascal = new int[depth][]; // 配列を初期化

		int i = 0;
		while (i < depth) {
			pascal[i] = new int[i + 1]; // 各行を適切な長さの配列とする
			i++;
		}
	}

	/**
	 * パスカルの三角形を計算
	 */
	public void calc() {
		pascal[0][0] = 1; // 頂点の値を設定

		int i = 1;
		while (i < pascal.length) {
			int j = 0;
			while (j < pascal[i].length) {
				if (j == 0) // 各行の左端
					pascal[i][j] = pascal[i - 1][j];
				else if (j == pascal[i].length - 1) // 各行の右端
					pascal[i][j] = pascal[i - 1][j - 1];
				else
					pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
				j++;
			}
			i++;
		}
	}

	/**
	 * パスカルの三角形の配列を取得
	 */
	public int[][] getPascal() {
		return pascal;
	}

	/**
	 * 結果を表示する
	 */
	public void print() {
		int i = 0;
		while (i < pascal.length) {
			int j = 0;
			while (j < pascal[i].length) {
				System.out.print(pascal[i][j]);
				if (j != pascal[i].length - 1)
					System.out.print(", ");
				j++;
			}
			System.out.printf("%n");
			i++;
		}
	}
}
