// ex01_15 (P25)

public class newSimpleLookup implements newLookup {
	private static final int MAX_ARR_SIZE = 100;

	private String[] names = new String[MAX_ARR_SIZE];	// ���O
	private Object[] values = new Object[MAX_ARR_SIZE];	// �l
	private static int num = 0; // �v�f��

	@Override
	public Object find(String name) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num; i++) {
			if (names[i].equals(name)) {
				return values[i];
			}
		}
		return null; // ������Ȃ�����
	}

	@Override
	public void add(String name, Object value) {
		// TODO Auto-generated method stub
		names[num] = name;
		values[num] = value;
		num++;
	}

	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		int i = 0;
		while (i < num) {
			if (names[i].equals(name)) {
				for (int j = i; j < num - 1; j++) {
					// �z��̌��̗v�f��O�ɋl�߂�
					names[j] = names[j + 1];
					values[j] = values[j + 1];
				}
				names[num-1] = null;
				values[num-1] = null;
				num--;	// �v�f����1���炷
			}
			i++;
		}
		if (i == num) {	// ���O�ƈ�v����v�f��������Ȃ�����
			System.out.println("Failure: �I�u�W�F�N�g��������܂���");
		}
	}
}
