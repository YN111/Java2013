// ex02_10 (P51)

import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void test() {
		Vehicle car1 = new Vehicle(); // コンストラクタでIDのみ設定
		Vehicle car2 = new Vehicle("いいい"); // コンストラクタでIDと名前を設定
		car1.speed = 0;
		car1.direction = 200;
		car1.name = "あああ";
		assertEquals(car1.id, 0);
		assertEquals(car2.id, 1);
		assertEquals(car1.speed, 0.0);
		assertEquals(car1.direction, 200.0);
		assertEquals(car1.name, "あああ");
		assertEquals(car1.getMaxId(), 1);
		assertEquals(car2.name, "いいい");

		// 以下が追加部分のテスト
		assertEquals(car1.toString(),
				"CAR1:ID=0, SPEED=0.0, DIRECTION=200.0, NAME=あああ");
	}
}
