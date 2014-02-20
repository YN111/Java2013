public class GasTank extends EnergySource {

	private int fuel = 0; // �R���̎c��
	private final int MAX_FUEL; // �^���N�̗e��

	/**
	 * �R���X�g���N�^ �K�X�^���N�̗e�ʂ�ݒ�
	 */
	GasTank(int maxFuel) {
		if (maxFuel > 0) {
			MAX_FUEL = maxFuel;
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * �R���⋋
	 */
	@Override
	public void add(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		fuel += volume;
		// �[�d�\�ő�l�ȏ�̓d�͂��������ꂽ�ꍇ
		if (fuel > MAX_FUEL) {
			fuel = MAX_FUEL;
		}
	}

	/**
	 * �R���������
	 */
	@Override
	public void remove(int volume) {
		if (volume < 0) {
			throw new AssertionError();
		}
		fuel -= volume;
		// �K�X���̏ꍇ
		if (fuel <= 0) {
			fuel = 0;
		}
	}

	/**
	 * �K�X�����ǂ������肷��
	 */
	@Override
	public boolean empty() {
		return (fuel <= 0 ? true : false);
	}
}
