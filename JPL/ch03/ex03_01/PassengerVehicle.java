public class PassengerVehicle extends Vehicle {

	private int seats; // シート数
	private int people; // 座っている人数

	/**
	 * 引数なしコンストラクタ
	 */
	PassengerVehicle() {
		super();
	}

	/**
	 * 名前を設定するコンストラクタ
	 */
	PassengerVehicle(String name) {
		super(name);
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
	 * mainメソッド
	 */
	public static void main(String[] args) {
		PassengerVehicle car1 = new PassengerVehicle("Tom");
		car1.setSpeed(100);
		car1.setDirection(180);
		car1.setSeatsNum(5);
		car1.setPeopleNum(3);

		PassengerVehicle car2 = new PassengerVehicle("Ken");
		car2.setSpeed(50);
		car2.setDirection(90);
		car2.setSeatsNum(5);
		car2.setPeopleNum(2);

		System.out.println(car1);
		System.out.println(car2);
	}
}
