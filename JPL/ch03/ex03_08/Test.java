import junit.framework.TestCase;

public class Test extends TestCase {

	public void testBattery() {
		// コンストラクタのテスト
		try {
			Battery errorBattery = new Battery(-1); // 範囲外
			super.fail(); // エラーが発生しないためテスト失敗
		} catch (AssertionError e) {
		}
		Battery battery = new Battery(100);

		// addメソッドのテスト
		try {
			battery.add(-1); // 範囲外
			super.fail();
		} catch (AssertionError e) {
		}
		assertEquals(battery.empty(), true); // 初期状態ではバッテリーは空
		battery.add(1);
		assertEquals(battery.empty(), false);
		battery.add(100); // 上限値を超えるためpower=100となる

		// removeメソッドのテスト
		try {
			battery.remove(-1); // 範囲外
			super.fail();
		} catch (AssertionError e) {
		}
		battery.remove(99);
		assertEquals(battery.empty(), false);
		battery.remove(1);
		assertEquals(battery.empty(), true); // バッテリーが空になる
	}

	public void testGasTank() {
		// コンストラクタのテスト
		try {
			GasTank errorGasTank = new GasTank(-1); // 範囲外
			super.fail(); // エラーが発生しないためテスト失敗
		} catch (AssertionError e) {
		}
		GasTank gasTank = new GasTank(40);

		// addメソッドのテスト
		try {
			gasTank.add(-1); // 範囲外
			super.fail();
		} catch (AssertionError e) {
		}
		assertEquals(gasTank.empty(), true); // 初期状態ではバッテリーは空
		gasTank.add(1);
		assertEquals(gasTank.empty(), false);
		gasTank.add(40); // 上限値を超えるためpower=100となる

		// removeメソッドのテスト
		try {
			gasTank.remove(-1); // 範囲外
			super.fail();
		} catch (AssertionError e) {
		}
		gasTank.remove(39);
		assertEquals(gasTank.empty(), false);
		gasTank.remove(1);
		assertEquals(gasTank.empty(), true); // バッテリーが空になる
	}

	public void testVehicle() {
		Battery battery = new Battery(100);
		Vehicle car1 = new Vehicle(battery); // コンストラクタで動力源のバッテリーを設定
		Vehicle car2 = new Vehicle(battery, "いいい"); // コンストラクタで動力源のガスと名前を設定

		car1.setSpeed(0);
		car1.setSpeed(-1); // 範囲外：セットされない
		car1.setDirection(200);
		car1.setDirection(-1); // 範囲外：セットされない
		car1.setDirection(361); // 範囲外：セットされない
		car1.setName("あああ");
		assertEquals(car1.getId(), 0);
		assertEquals(car2.getId(), 1);
		assertEquals(car1.getSpeed(), 0.0);
		assertEquals(car1.getDirection(), 200.0);
		assertEquals(car1.getName(), "あああ");
		assertEquals(car1.getMaxId(), 1);
		assertEquals(car2.getName(), "いいい");
		car1.changeSpeed(30); // スピードの変更
		assertEquals(car1.getSpeed(), 30.0);
		car1.stop(); // 停止
		assertEquals(car1.getSpeed(), 0.0);
		car1.turn(100.0);
		assertEquals(car1.getDirection(), 300.0);
		car1.turn(Vehicle.TURN_RIGHT);
		assertEquals(car1.getDirection(), 30.0);
		car1.turn(Vehicle.TURN_LEFT);
		assertEquals(car1.getDirection(), 300.0);
		car1.turn("INVALID_VALUE");
		assertEquals(car1.getDirection(), 300.0);
		
		// 動力源設定のテスト
		car1.start();
		assertEquals(car1.getStartFlag(), false); // ガス欠
		battery.add(50); // 燃料補充
		car1.start();
		assertEquals(car1.getStartFlag(), true); // 起動可能になる
		
		// クローンのテスト
		Vehicle car1clone = null;
		try {
			car1clone = car1.clone();
		} catch (CloneNotSupportedException e) {
			super.fail();
		}
		assertEquals(car1clone.getId(), 2);	// IDは単純にコピーされない
		assertEquals(car1clone.getDirection(), car1.getDirection());
		assertEquals(car1clone.getSpeed(), car1.getSpeed());
		assertEquals(car1clone.getName(), car1.getName());
		assertEquals(car1clone.getDirection(), car1.getDirection());
		car1clone.changeSpeed(40);	// クローンで値を変更しても元のオブジェクトの値は変わらない
		assertEquals(car1.getSpeed(), 0.0);
	}

	public void testPassengerVehicle() {
		PassengerVehicle car3 = new PassengerVehicle(new Battery(40), "ううう");
		PassengerVehicle car4 = new PassengerVehicle(new GasTank(100));
		car3.setSeatsNum(3);
		car3.setSeatsNum(0); // 範囲外：セットされない
		car3.setPeopleNum(2);
		car3.setPeopleNum(-1); // 範囲外：セットされない
		assertEquals(car3.getSeatsNum(), 3);
		assertEquals(car3.getPeopleNum(), 2);
		
		// クローンのテスト
		PassengerVehicle car3clone = null;
		try {
			car3clone = car3.clone();
		} catch (CloneNotSupportedException e) {
			super.fail();
		}
		assertEquals(car3clone.getSeatsNum(), car3.getSeatsNum());
		assertEquals(car3clone.getPeopleNum(), car3.getPeopleNum());
	}
}
