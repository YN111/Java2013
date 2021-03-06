// ex02_13 (P58)

import junit.framework.TestCase;

public class VehicleTest extends TestCase {
	public void test() {
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
	}
}
