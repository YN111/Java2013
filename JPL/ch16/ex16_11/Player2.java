import java.util.ArrayList;

public class Player2 extends Player {

	private boolean[][] result = new boolean[3][3];

	@Override
	public void play(Game game) {
		select();
		print();
		game.setWinFlag(checkWin());
	}

	/**
	 * �����_����5�̃}�X��I�����A�ێ����܂�
	 */
	private void select() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			while (true) {
				int rand = (int) (Math.random() * 1000) % 9;
				if (!list.contains(rand)) {
					list.add(rand);
					break;
				}
			}
		}
		// �I�����ꂽ�}�X��ێ�
		for (Integer i : list) {
			result[i / 3][i % 3] = true;
		}
	}

	/**
	 * �Q�[���̌��ʂ�\�����܂�
	 */
	private void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (result[i][j]) {
					System.out.print("�� ");
				} else {
					System.out.print("�~ ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * �Q�[���̏��s�𔻒肵�܂�
	 * @return �����Ȃ�true�A�����Ȃ�false
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
