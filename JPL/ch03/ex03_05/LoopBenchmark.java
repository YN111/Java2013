public class LoopBenchmark extends Benchmark {
	
	private int loopCount = 0;	// ループの回数
	
	/**
	 * ループ回数を設定
	 * @param num ループ回数
	 */
	public void setLoopCount(int num) {
		loopCount = num;
	}

	/**
	 * 0からパラメータとして渡された値までループ
	 */
	@Override
	public void benchmark() {
		for (int i = 0; i < loopCount; i++) {
			// 何もしない
		}
	}
	
	public static void main(String[] args) {
		LoopBenchmark benchmark = new LoopBenchmark();
		benchmark.setLoopCount(1000);
		long time = benchmark.repeat(1);
		System.out.println(time);
	}
}
