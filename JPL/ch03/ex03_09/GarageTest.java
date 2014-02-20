import junit.framework.TestCase;

public class GarageTest extends TestCase {
	public void testGarage() {
		Garage garage = new Garage(3); // 3�䒓�ԉ\�ȎԌɂ��쐬

		// in���\�b�h�̃e�X�g
		garage.in(new Vehicle(new Battery(100))); // ID = 0
		garage.in(new Vehicle(new Battery(200))); // ID = 1
		garage.in(new Vehicle(new Battery(300))); // ID = 2
		garage.in(new Vehicle(new Battery(400))); // 4��ڂ̓G���[���b�Z�[�W��\��

		// out���\�b�h�̃e�X�g
		assertEquals(garage.out(0).getId(), 0);
		assertEquals(garage.out(1).getId(), 1);
		assertEquals(garage.out(2).getId(), 2);
		garage.out(1); // ���݂��Ȃ�ID���w�肷��ƃG���[���b�Z�[�W��\��
		
		// �N���[���̃e�X�g��Garage.java��main���\�b�h�Ŏ��{���Ă���
	}
}