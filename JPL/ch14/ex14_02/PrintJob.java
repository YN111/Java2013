public class PrintJob {

	private static long nextId = 0;
	private long jobId;

	PrintJob() {
		jobId = nextId++;
	}

	/**
	 * �W���uID���擾���܂�
	 * @return �W���uID
	 */
	public long getJobId() {
		return jobId;
	}

}
