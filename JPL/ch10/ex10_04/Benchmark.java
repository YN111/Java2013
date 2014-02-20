/*
 * ex03_05の問題
 * do-while文は使用していない
 * <理由> countとして0が渡された場合に、正しい結果を得られないため
 */

public abstract class Benchmark {
	abstract void benchmark();

	public final long repeat(int count) {
		long start = System.nanoTime();
		int i = 0;
		while (i < count) {
			benchmark();
			i++;
		}
		return (System.nanoTime() - start);
	}
}
