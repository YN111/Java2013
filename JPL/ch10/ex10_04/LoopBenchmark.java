/*
 * ex03_05の問題
 * do-while文は使用していない
 * <理由> loopCountとして0が渡された場合に、正しい結果を得られないため
 */


public class LoopBenchmark extends Benchmark {
	
	private int loopCount = 0;	// ループの回数
	
	/**
	 * ループ回数を設定します
	 * @param num ループ回数
	 */
	public void setLoopCount(int num) {
		loopCount = num;
	}

	/**
	 * 0からパラメータとして渡された値までループします
	 */
	@Override
	public void benchmark() {
		int i = 0;
		while (i < loopCount) {
			i++;
		}
	}
	
	public static void main(String[] args) {
		LoopBenchmark benchmark = new LoopBenchmark();
		benchmark.setLoopCount(1000);
		long time = benchmark.repeat(1);
		System.out.println(time);
	}
}
