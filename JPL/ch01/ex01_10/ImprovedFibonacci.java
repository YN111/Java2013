// ex01_10 (P17)

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * �����v�f��'*'�����ăt�B�{�i�b�`����� �ŏ��̕��̗v�f��z��ɕێ�
	 */
	public static void main(String[] args) {
		Sequence arr[] = new Sequence[MAX_INDEX];
		String[] output = new String[MAX_INDEX]; // �o�͌��ʊi�[�p�z��

		for (int i = 0; i < MAX_INDEX; i++) {
			arr[i] = new Sequence();
		}

		arr[0].setSequence(1); // lo
		arr[1].setSequence(1); // hi
		int i = 2;
		while (i < MAX_INDEX) {
			arr[i].setSequence(arr[i - 1].getNum() + arr[i - 2].getNum()); // 1�O�̗v�f��2�O�̗v�f�����Z
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
