public class Garage implements Cloneable {
	private final int MAX; // ���ԉ\�ȑ䐔
	private int count = 0; // ���ݒ��Ԓ��̑䐔
	private Vehicle[] space;

	/**
	 * �R���X�g���N�^�@���ԉ\�ȍő�䐔���Z�b�g
	 */
	Garage(int maxParking) {
		if (maxParking < 1) {
			throw new AssertionError();
		}
		MAX = maxParking;
		space = new Vehicle[MAX];
	}

	/**
	 * ����
	 */
	public void in(Vehicle car) {
		if (count < MAX) {
			space[count++] = car;
		} else {
			System.out.println("���Ԃł�");
		}
	}

	/**
	 * �w�肳�ꂽID�̎Ԃ��o��
	 */
	public Vehicle out(int id) {
		Vehicle car = null;
		// �Ԃ�T��
		for (int i = 0; i < count; i++) {
			// ��������
			if (space[i].getId() == id) {
				car = space[i];
				// �z��̌��̗v�f��O�ɋl�߂�
				for (int j = i; j < count - 1; j++) {
					space[j] = space[j + 1];
				}
				space[count - 1] = null;
				count--;
				break;
			}
		}
		if (car == null)
			System.out.println("�w�肳�ꂽID�̎Ԃ�������܂���");
		return car;
	}

	/**
	 * �N���[�����쐬
	 */
	public Garage clone() {
		try {
			Garage garage = (Garage) super.clone();
			garage.space = space.clone(); // �z��̃R�s�[
			return garage;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	/**
	 * �N���[���̃e�X�g
	 */
	public static void main(String[] args) {
		Garage garage = new Garage(3);
		garage.in(new Vehicle(new Battery(100))); // ID = 0
		garage.in(new Vehicle(new Battery(200))); // ID = 1
		garage.in(new Vehicle(new Battery(300))); // ID = 2

		Garage clone = garage.clone();
		System.out.println("�R�s�[���̎Ԍɂ���o�ɂ��܂�");
		System.out.println("ID = " + garage.out(0).getId() + " �̎Ԃ��o�ɂ��܂���");
		System.out.println("ID = " + garage.out(1).getId() + " �̎Ԃ��o�ɂ��܂���");
		System.out.println("ID = " + garage.out(2).getId() + " �̎Ԃ��o�ɂ��܂���");
		// �R�s�[���̎Ԍɂ͋�ɂȂ���

		System.out.println("�N���[���̎Ԍɂ���o�ɂ��܂�");
		System.out.println("ID = " + clone.out(0).getId() + " �̎Ԃ��o�ɂ��܂���");
		System.out.println("ID = " + clone.out(1).getId() + " �̎Ԃ��o�ɂ��܂���");
		System.out.println("ID = " + clone.out(2).getId() + " �̎Ԃ��o�ɂ��܂���");
	}
}
