public class PrintJob {

	private static long nextId = 0;
	private long jobId;

	PrintJob() {
		jobId = nextId++;
	}

	/**
	 * ジョブIDを取得します
	 * @return ジョブID
	 */
	public long getJobId() {
		return jobId;
	}

}
