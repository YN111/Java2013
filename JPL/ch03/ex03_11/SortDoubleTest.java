import junit.framework.TestCase;

public class SortDoubleTest extends TestCase {
	public void testSort() {
		double[] originalData = { 3.0, 2.0, 8.0, 4.0, 3.0 }; // ソート元
		double[] sortData = { 2.0, 3.0, 3.0, 4.0, 8.0 }; // ソート後
		
		SortDouble dsort = new SimpleSortDouble();
		SortMetrics metrics = dsort.sort(originalData); // ソート実行
		
		System.out.println("===== ORIGINAL =====");
		for (int i = 0; i < 5; i++) {
			assertEquals(originalData[i], sortData[i]);
			System.out.println(originalData[i]);
		}
		
		System.out.println("Compare: " + metrics.compareCnt);
		System.out.println("Probe: " + metrics.probeCnt);
		System.out.println("Swap: " + metrics.swapCnt);
	}

	public void testSortRevision() {
		double[] originalData = { 3.0, 2.0, 8.0, 4.0, 3.0 }; // ソート元
		double[] sortData = { 2.0, 3.0, 3.0, 4.0, 8.0 }; // ソート後
		
		SortDoubleRevision dsort = new SimpleSortDoubleRevision();
		SortMetricsRevision metrics = null;
		metrics = dsort.sort(originalData); // ソート実行

		System.out.println();
		System.out.println("===== REVISION =====");
		
		for (int i = 0; i < 5; i++) {
			assertEquals(originalData[i], sortData[i]);
			System.out.println(originalData[i]);
		}
		
		assertTrue(metrics.compareCnt != 0);
		assertTrue(metrics.swapCnt != 0);
		System.out.println("Compare: " + metrics.compareCnt);
		System.out.println("Probe: " + metrics.probeCnt);
		System.out.println("Swap: " + metrics.swapCnt);
	}
}