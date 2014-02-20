import junit.framework.TestCase;

public class GarageTest extends TestCase {
	public void testGarage() {
		Garage garage = new Garage(3); // 3台駐車可能な車庫を作成

		// inメソッドのテスト
		garage.in(new Vehicle(new Battery(100))); // ID = 0
		garage.in(new Vehicle(new Battery(200))); // ID = 1
		garage.in(new Vehicle(new Battery(300))); // ID = 2
		garage.in(new Vehicle(new Battery(400))); // 4台目はエラーメッセージを表示

		// outメソッドのテスト
		assertEquals(garage.out(0).getId(), 0);
		assertEquals(garage.out(1).getId(), 1);
		assertEquals(garage.out(2).getId(), 2);
		garage.out(1); // 存在しないIDを指定するとエラーメッセージを表示
		
		// クローンのテストはGarage.javaのmainメソッドで実施している
	}
}