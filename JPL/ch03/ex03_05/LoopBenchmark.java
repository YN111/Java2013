public class LoopBenchmark extends Benchmark {
	
	private int loopCount = 0;	// ���[�v�̉�
	
	/**
	 * ���[�v�񐔂�ݒ�
	 * @param num ���[�v��
	 */
	public void setLoopCount(int num) {
		loopCount = num;
	}

	/**
	 * 0����p�����[�^�Ƃ��ēn���ꂽ�l�܂Ń��[�v
	 */
	@Override
	public void benchmark() {
		for (int i = 0; i < loopCount; i++) {
			// �������Ȃ�
		}
	}
	
	public static void main(String[] args) {
		LoopBenchmark benchmark = new LoopBenchmark();
		benchmark.setLoopCount(1000);
		long time = benchmark.repeat(1);
		System.out.println(time);
	}
}
