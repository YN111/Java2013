public class Battery implements EnergySource {

	private int power = 0; // バッテリーの残量
	private final int MAX_POWER; // 最大充電可能量

	/**
	 * コンストラクタ 最大充電可能量を設定
	 */
	Battery(int maxPower) {
		if (maxPower > 0) {
			MAX_POWER = maxPower;
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * 充電
	 */
	@Override
	public void add(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		power += volume;
		// 充電可能最大値以上の電力が供給された場合
		if (power > MAX_POWER) {
			power = MAX_POWER;
		}
	}

	/**
	 * 電力を消費する
	 */
	@Override
	public void remove(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		power -= volume;
		// ガス欠の場合
		if (power <= 0) {
			power = 0;
		}
	}

	/**
	 * バッテリーが空かどうか判定する
	 */
	@Override
	public boolean empty() {
		return (power <= 0 ? true : false);
	}
}
