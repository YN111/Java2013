// ex02_18 (P64)


import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void testVehicle() {
		Vehicle car1 = new Vehicle(); // コンストラクタでIDのみ設定
		Vehicle car2 = new Vehicle("いいい"); // コンストラクタでIDと名前を設定
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
		assertEquals(car1.toString(),
				"CAR1:ID=0, SPEED=0.0, DIRECTION=200.0, NAME=あああ");
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
	}

	// 以降が変更箇所のテスト
	public void testMain() {
		Vehicle.main(null);
		String[] str = new String[1];
		str[0] = "Alice";
		Vehicle.main(str);
	}
}
