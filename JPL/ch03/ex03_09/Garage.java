public class Garage implements Cloneable {
	private final int MAX; // 駐車可能な台数
	private int count = 0; // 現在駐車中の台数
	private Vehicle[] space;

	/**
	 * コンストラクタ　駐車可能な最大台数をセット
	 */
	Garage(int maxParking) {
		if (maxParking < 1) {
			throw new AssertionError();
		}
		MAX = maxParking;
		space = new Vehicle[MAX];
	}

	/**
	 * 入庫
	 */
	public void in(Vehicle car) {
		if (count < MAX) {
			space[count++] = car;
		} else {
			System.out.println("満車です");
		}
	}

	/**
	 * 指定されたIDの車を出庫
	 */
	public Vehicle out(int id) {
		Vehicle car = null;
		// 車を探す
		for (int i = 0; i < count; i++) {
			// 見つかった
			if (space[i].getId() == id) {
				car = space[i];
				// 配列の後ろの要素を前に詰める
				for (int j = i; j < count - 1; j++) {
					space[j] = space[j + 1];
				}
				space[count - 1] = null;
				count--;
				break;
			}
		}
		if (car == null)
			System.out.println("指定されたIDの車が見つかりません");
		return car;
	}

	/**
	 * クローンを作成
	 */
	public Garage clone() {
		try {
			Garage garage = (Garage) super.clone();
			garage.space = space.clone(); // 配列のコピー
			return garage;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	/**
	 * クローンのテスト
	 */
	public static void main(String[] args) {
		Garage garage = new Garage(3);
		garage.in(new Vehicle(new Battery(100))); // ID = 0
		garage.in(new Vehicle(new Battery(200))); // ID = 1
		garage.in(new Vehicle(new Battery(300))); // ID = 2

		Garage clone = garage.clone();
		System.out.println("コピー元の車庫から出庫します");
		System.out.println("ID = " + garage.out(0).getId() + " の車を出庫しました");
		System.out.println("ID = " + garage.out(1).getId() + " の車を出庫しました");
		System.out.println("ID = " + garage.out(2).getId() + " の車を出庫しました");
		// コピー元の車庫は空になった

		System.out.println("クローンの車庫から出庫します");
		System.out.println("ID = " + clone.out(0).getId() + " の車を出庫しました");
		System.out.println("ID = " + clone.out(1).getId() + " の車を出庫しました");
		System.out.println("ID = " + clone.out(2).getId() + " の車を出庫しました");
	}
}
