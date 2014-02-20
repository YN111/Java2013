import junit.framework.TestCase;

public class BenchmarkTest extends TestCase {

	public void testLoopBenchmark() {
		LoopBenchmark benchmark = new LoopBenchmark();
		benchmark.setLoopCount(1000);
		long time = benchmark.repeat(1);
		if (time <= 0)
			super.fail();
		System.out.println(time);
	}

}
