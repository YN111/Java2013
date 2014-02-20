import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Clock extends Frame {

	private static final int WIDTH = 800; // GUI�̕�
	private static final int HEIGHT = 600; // GUI�̍���
	private static final int CENTER_X = 400; // �A�i���O���v�̒��SX���W
	private static final int CENTER_Y = 170; // �A�i���O���v�̒��SY���W
	private static final int RADIUS = 110; // �A�i���O���v�̔��a
	private static final float SEC_LINE_WIDTH = 2.0f; // �b�j�̕�
	private static final float MIN_LINE_WIDTH = 4.0f; // ���j�̕�
	private static final float HOUR_LINE_WIDTH = 7.0f; // ���j�̕�
	private static final int TIME_X = 120; // �f�W�^�����v�̋N�_X���W
	private static final int TIME_Y = 460; // �f�W�^�����v�̋N�_Y���W
	private static final int TIME_STR_SIZE = 150; // �f�W�^�����v�̕����T�C�Y
	private static final int DATE_X = 300; // ���t�����̋N�_X���W
	private static final int DATE_Y = 530; // ���t�����̋N�_Y���W
	private static final int DATE_STR_SIZE = 40; // ���t�����̕����T�C�Y
	private final Color digiColor = new Color(0, 51, 204); // �f�W�^�����v���̐F(#0033cc)
	private final Color anaColor = new Color(170, 170, 170); // �A�i���O���v���̐F(#AAAAAA)
	private static int preSec; // �O��paint���\�b�h���Ă΂ꂽ���̕b

	Image imgBuffer = null; // ������h�~�p�̃o�b�t�@
	Graphics2D gBuffer = null;

	/**
	 * �R���X�g���N�^�FGUI�̐ݒ�Ɓ~�{�^���ŏI�����鏈�����L�q
	 */
	Clock() {
		// �^�C�g��
		super("Clock");

		// �T�C�Y�ƐF���w��
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);

		// Close�C�x���g���󂯎�郊�X�i�[
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); // ���v�̕\�����I��
			}
		});
	}

	public void paint(Graphics g) {

		// �o�b�t�@�p�̃C���[�W�����
		if (imgBuffer == null) {
			imgBuffer = createImage(WIDTH, HEIGHT);
			gBuffer = null;
		}

		// �o�b�t�@�p�̃O���t�B�b�N�����
		if (gBuffer == null)
			gBuffer = (Graphics2D) imgBuffer.getGraphics();

		// �o�b�t�@���N���A����
		gBuffer.clearRect(0, 0, WIDTH, HEIGHT);

		// �w�i�F��ݒ�
		gBuffer.setColor(Color.BLACK);

		// �A���`�G�C���A�XON
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// �������擾
		int hour = getHour();
		int min = getMinute();
		int sec = getSecond();
		int millisecond = getMilliSecond();
		preSec = sec;

		// �f�W�^�����������i�A�j���[�V��������j
		gBuffer.setColor(digiColor); // �����F�̐ݒ�
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, TIME_STR_SIZE)); // �t�H���g�̐ݒ�

		// ��
		if (min == 59 && sec == 59 && millisecond > 600) {
			int hourY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int hourNewY = hourY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", hour), TIME_X, hourY);
			if (hour == 23) {
				gBuffer.drawString("00", TIME_X, hourNewY);
			} else {
				gBuffer.drawString(String.format("%02d", hour + 1), TIME_X,
						hourNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", hour), TIME_X, TIME_Y);
		}
		gBuffer.drawString(":", TIME_X + 140, TIME_Y);

		// ��
		if (sec == 59 && millisecond > 600) {
			int minY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int minNewY = minY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", min), TIME_X + 210, minY);
			if (min == 59) {
				gBuffer.drawString("00", TIME_X + 210, minNewY);
			} else {
				gBuffer.drawString(String.format("%02d", min + 1),
						TIME_X + 210, minNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", min), TIME_X + 210, TIME_Y);
		}
		gBuffer.drawString(":", TIME_X + 350, TIME_Y);

		// �b
		if (millisecond > 600) {
			int secY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int secNewY = secY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", sec), TIME_X + 420, secY);
			if (sec == 59) {
				gBuffer.drawString("00", TIME_X + 420, secNewY);
			} else {
				gBuffer.drawString(String.format("%02d", sec + 1),
						TIME_X + 420, secNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", sec), TIME_X + 420, TIME_Y);
		}

		// �������\���̈悩��͂ݏo�镔����h��Ԃ�
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(0, TIME_Y, WIDTH, TIME_Y + TIME_STR_SIZE);
		gBuffer.fillRect(0, 0, WIDTH, TIME_Y - TIME_STR_SIZE);

		// ���t����
		gBuffer.setColor(digiColor); // �����F�̐ݒ�
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, DATE_STR_SIZE)); // �t�H���g�̐ݒ�
		gBuffer.drawString(getDate(), DATE_X, DATE_Y); // ������̕\��

		// �A�i���O���v����
		// �\���F��ݒ�
		gBuffer.setColor(anaColor);

		// ���S��\��
		gBuffer.fillOval(CENTER_X - 8, CENTER_Y - 8, 16, 16);

		// �O�g��\��
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		for (int i = 0; i < 12; i++) {
			double x = CENTER_X + (Math.sin(Math.toRadians(i * 30))) * RADIUS;
			double y = CENTER_Y - (Math.cos(Math.toRadians(i * 30))) * RADIUS;
			gBuffer.fillOval((int) x - 4, (int) y - 4, 8, 8);
		}

		// �e�j�̊p�x���v�Z
		double secX = CENTER_X + (Math.sin(Math.toRadians(sec * 6))) * RADIUS
				* 0.9;
		double secY = CENTER_Y - (Math.cos(Math.toRadians(sec * 6))) * RADIUS
				* 0.9;
		double minX = CENTER_X
				+ (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS
				* 0.75;
		double minY = CENTER_Y
				- (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS
				* 0.75;
		double hourX = CENTER_X
				+ (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12))))
				* RADIUS * 0.6;
		double hourY = CENTER_Y
				- (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12))))
				* RADIUS * 0.6;

		// �b�j��\��
		gBuffer.setStroke(new BasicStroke(SEC_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) secX, (int) secY);

		// ���j��\��
		gBuffer.setStroke(new BasicStroke(MIN_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) minX, (int) minY);

		// ���j��\��
		gBuffer.setStroke(new BasicStroke(HOUR_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) hourX, (int) hourY);

		// �`��
		g.drawImage(imgBuffer, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * ���݂̓��t���擾����
	 * 
	 * @return GUI�ɕ\��������t�����̕�����
	 */
	private static String getDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "/" + month + "/" + date;
	}

	/**
	 * ���݂̎����i���j���擾����
	 * 
	 * @return Hour
	 */
	private static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ���݂̎����i���j���擾����
	 * 
	 * @return Minute
	 */
	private static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * ���݂̎����i�b�j���擾����
	 * 
	 * @return Second
	 */
	private static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	
	/**
	 * ���݂̎����i�~���b�j���擾����
	 * 
	 * @return MilliSecond
	 */
	private static int getMilliSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * ���C�����\�b�h ���v�̃T�C�Y���w�肵�ĕ\������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.show();
		while (true) {
			// ���Ԋu���Ƃɏ����X�V
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clock.repaint();
		}
	}
}
