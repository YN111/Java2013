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
		car1.changeSpeed(30); // スピードの変更
		assertEquals(car1.getSpeed(), 30.0);
		car1.stop(); // 停止
		assertEquals(car1.getSpeed(), 0.0);

		// 以下が変更箇所のテスト
		car1.turn(100.0);
		assertEquals(car1.getDirection(), 300.0);
		car1.turn(Vehicle.TurnDirection.TURN_RIGHT);
		assertEquals(car1.getDirection(), 30.0);
		car1.turn(Vehicle.TurnDirection.TURN_LEFT);
		assertEquals(car1.getDirection(), 300.0);
	}
}
