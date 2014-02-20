public class PassengerVehicle extends Vehicle {

	private int seats; // シート数
	private int people; // 座っている人数

	/**
	 * 動力源のみ設定するコンストラクタ
	 */
	PassengerVehicle(EnergySource energy) {
		super(energy);
	}

	/**
	 * 動力源と名前を設定するコンストラクタ
	 */
	PassengerVehicle(EnergySource energy, String name) {
		super(energy, name);
	}

	/**
	 * シートの数のセッタ
	 */
	public void setSeatsNum(int num) {
		if (num > 0) {
			seats = num;
		}
	}

	/**
	 * 現在座っている人数のセッタ
	 */
	public void setPeopleNum(int num) {
		if (num >= 0) {
			people = num;
		}
	}

	/**
	 * シートの数を返す
	 */
	public int getSeatsNum() {
		return seats;
	}

	/**
	 * 現在座っている人数を返す
	 */
	public int getPeopleNum() {
		return people;
	}

	/**
	 * 情報を出力する
	 */
	@Override
	public String toString() {
		String info = super.toString() + "  【This car has " + getSeatsNum()
				+ " seats and riding " + getPeopleNum() + " people now】";
		return info;
	}
	
	/**
	 * クローンを作成する
	 * IDのみ新たな番号を割り振る
	 */
	public PassengerVehicle clone() throws CloneNotSupportedException {
		PassengerVehicle clone = (PassengerVehicle) super.clone();
		clone.setId();
		return clone;
	}
}
