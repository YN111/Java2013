/*
 * ���s��������
 * 
 * <�ݒ肵���f�[�^>
 * doYield : true �� false �̗�����ݒ�i�e20����s�j
 * howOften �F 10
 * word �F "Did" �� "DiDNot" ��2���ݒ�
 * 
 * <����>
 * doYield��true��false�̂�����ɂ����Ă��A�o�͌��ʂ͖��񓯂��ł͂Ȃ�����
 */

public class Babble extends Thread {

	static boolean doYield; // ���̃X���b�h�Ɏ��s�����邩
	static int howOften; // �\�������
	private String word; // ���̃X���b�h�̒P��

	Babble(String whatToSay) {
		word = whatToSay;
	}

	@Override
	public void run() {
		for (int i = 0; i < howOften; i++) {
			System.out.println(word);
			if (doYield)
				Thread.yield(); // ���̃X���b�h�𑖂点��
		}
	}

	/**
	 * ���C�����\�b�h<br>
	 * �ȉ��̃t�H�[�}�b�g�ɏ]���Ĉ������w�肷��K�v������܂�
	 * @param args <br>
	 * args[0]:���̃X���b�h�Ɏ��s�����邩 (true or false)<br>
	 * args[1]:�\�������<br>
	 * args[2]�ȍ~�F�\������P��
	 */
	public static void main(String[] args) {
		doYield = new Boolean(args[0]).booleanValue();
		howOften = Integer.parseInt(args[1]);

		for (int i = 2; i < args.length; i++)
			new Babble(args[i]).start();
	}

}
