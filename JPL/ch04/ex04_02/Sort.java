public interface Sort {

	/**
	 * ソートを実行しメトリクスを取得する
	 */
	SortMetrics sort(Object[] data);

	/**
	 * メトリクスを取得する
	 */
	SortMetrics getMetrics();

}
