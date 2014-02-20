// ex01_15 (P25)

public class newSimpleLookup implements newLookup {
	private static final int MAX_ARR_SIZE = 100;

	private String[] names = new String[MAX_ARR_SIZE];	// 名前
	private Object[] values = new Object[MAX_ARR_SIZE];	// 値
	private static int num = 0; // 要素数

	@Override
	public Object find(String name) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num; i++) {
			if (names[i].equals(name)) {
				return values[i];
			}
		}
		return null; // 見つからなかった
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
					// 配列の後ろの要素を前に詰める
					names[j] = names[j + 1];
					values[j] = values[j + 1];
				}
				names[num-1] = null;
				values[num-1] = null;
				num--;	// 要素数を1減らす
			}
			i++;
		}
		if (i == num) {	// 名前と一致する要素が見つからなかった
			System.out.println("Failure: オブジェクトが見つかりません");
		}
	}
}
