public class GasTank implements EnergySource {

	private int fuel = 0; // 燃料の残量
	private final int MAX_FUEL; // タンクの容量

	/**
	 * コンストラクタ ガスタンクの容量を設定
	 */
	GasTank(int maxFuel) {
		if (maxFuel > 0) {
			MAX_FUEL = maxFuel;
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * 燃料補給
	 */
	@Override
	public void add(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		fuel += volume;
		// 充電可能最大値以上の電力が供給された場合
		if (fuel > MAX_FUEL) {
			fuel = MAX_FUEL;
		}
	}

	/**
	 * 燃料を消費する
	 */
	@Override
	public void remove(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		fuel -= volume;
		// ガス欠の場合
		if (fuel <= 0) {
			fuel = 0;
		}
	}

	/**
	 * ガス欠かどうか判定する
	 */
	@Override
	public boolean empty() {
		return (fuel <= 0 ? true : false);
	}
}
