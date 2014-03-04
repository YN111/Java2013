import java.util.ArrayList;

public class Player1 extends Player {

	private boolean[][] result = new boolean[3][3];

	@Override
	public void play(Game game) {
		select();
		print();
		game.setWinFlag(checkWin());
	}

	/**
	 * ランダムに4つのマスを選択し、保持します
	 */
	private void select() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			while (true) {
				int rand = (int) (Math.random() * 1000) % 9;
				if (!list.contains(rand)) {
					list.add(rand);
					break;
				}
			}
		}
		// 選択されたマスを保持
		for (Integer i : list) {
			result[i / 3][i % 3] = true;
		}
	}

	/**
	 * ゲームの結果を表示します
	 */
	private void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (result[i][j]) {
					System.out.print("○ ");
				} else {
					System.out.print("× ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * ゲームの勝敗を判定します
	 * @return 勝ちならtrue、負けならfalse
	 */
	private boolean checkWin() {
		return (result[0][0] == true && result[0][1] == true && result[0][2] == true)
				|| (result[1][0] == true && result[1][1] == true && result[1][2] == true)
				|| (result[2][0] == true && result[2][1] == true && result[2][2] == true)
				|| (result[0][0] == true && result[1][0] == true && result[2][0] == true)
				|| (result[0][1] == true && result[1][1] == true && result[2][1] == true)
				|| (result[0][2] == true && result[1][2] == true && result[2][2] == true)
				|| (result[0][0] == true && result[1][1] == true && result[2][2] == true)
				|| (result[0][2] == true && result[1][1] == true && result[2][0] == true);
	}
}
