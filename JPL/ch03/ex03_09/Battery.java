public class Battery extends EnergySource {

	private int power = 0; // �o�b�e���[�̎c��
	private final int MAX_POWER; // �ő�[�d�\��

	/**
	 * �R���X�g���N�^ �ő�[�d�\�ʂ�ݒ�
	 */
	Battery(int maxPower) {
		if (maxPower > 0) {
			MAX_POWER = maxPower;
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * �[�d
	 */
	@Override
	public void add(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		power += volume;
		// �[�d�\�ő�l�ȏ�̓d�͂��������ꂽ�ꍇ
		if (power > MAX_POWER) {
			power = MAX_POWER;
		}
	}

	/**
	 * �d�͂������
	 */
	@Override
	public void remove(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		power -= volume;
		// �K�X���̏ꍇ
		if (power <= 0) {
			power = 0;
		}
	}

	/**
	 * �o�b�e���[���󂩂ǂ������肷��
	 */
	@Override
	public boolean empty() {
		return (power <= 0 ? true : false);
	}
}
