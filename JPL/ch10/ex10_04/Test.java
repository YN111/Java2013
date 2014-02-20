import java.lang.reflect.Field;
import junit.framework.TestCase;

public class Test extends TestCase {

	public void testPascalTriangle() throws Exception {

		// 深さ12のパスカルの三角形をテスト
		PascalTriangle p12 = new PascalTriangle();
		p12.calc();

		// 12行目が正しいか検証する
		int[] pascal12 = new int[] { 1, 11, 55, 165, 330, 462, 462, 330, 165,
				55, 11, 1 };
		for (int i = 0; i < 12; i++) {
			assertEquals(pascal12[i], p12.getPascal()[11][i]);
		}

		p12.print();

		// コンストラクタで深さを変更する
		PascalTriangle p1 = new PascalTriangle(1);
		Class c = p1.getClass();
		Field f = c.getDeclaredField("depth");
		f.setAccessible(true);
		assertEquals(1, ((Integer) f.get(p1)).intValue());

		// コンストラクタで範囲外の値をセットする
		try {
			PascalTriangle pError = new PascalTriangle(0); // 範囲外
			super.fail(); // エラーが発生しないためテスト失敗
		} catch (AssertionError e) {
		}
	}
	
	public void testLoopBenchmark() {
		LoopBenchmark benchmark = new LoopBenchmark();
		benchmark.setLoopCount(1000);
		long time = benchmark.repeat(1);
		if (time <= 0)
			super.fail();
		System.out.println(time);
	}
}
