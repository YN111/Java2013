import java.awt.*;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Clock extends Window implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 7288719385020825637L;

	public static final int DEFAULT_GUI_WIDTH = 600; // GUI���̏����l
	public static final int DEFAULT_GUI_HEIGHT = 510; // GUI�����̏����l
	public static final int MAX_GUI_WIDTH = 1920; // GUI���̍ő�l
	public static final int MAX_GUI_HEIGHT = 1080; // GUI�����̍ő�l

	private Image imgBuffer = null; // ������h�~�p�̃o�b�t�@
	private Graphics2D gBuffer = null;
	private SettingDataHolder mDataHolder;
	private MyPopupMenu mPopupMenu;
	private Point startDragPos; // �h���b�O�̋N�_���W
	private Point startGuiPos; // �h���b�O�J�n���̉�ʂ̍��W
	private boolean leftClickFlag = false; // ���N���b�N�̃t���O

	/**
	 * �R���X�g���N�^
	 * GUI�̐ݒ�Ɓ~�{�^���ɂ��I��������ݒ肵�܂�
	 */
	Clock() {
		super(new Frame());

		// �T�C�Y�ƐF���w��
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setBackground(Color.BLACK);

		// �N�����̕\���ʒu���w��
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int x = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int y = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(x, y);

		mDataHolder = new SettingDataHolder();
		mPopupMenu = new MyPopupMenu(mDataHolder);
		add(mPopupMenu); // �|�b�v�A�b�v���j���[��o�^
		addMouseListener(this); // �}�E�X�C�x���g�̃��X�i�[�o�^ 
		addMouseMotionListener(this); // ���[�V�����C�x���g�̃��X�i�[�o�^
	}

	@Override
	public void paint(Graphics g) {
		// �����̕��ɉ����ĕ\���̐ݒ�l���X�V
		FontMetrics fm = g.getFontMetrics(mDataHolder.getTimeFont());
		int strNumWidth = fm.stringWidth("8"); // �����̕�
		int strColonWidth = fm.stringWidth(":"); // �R�����̕�
		int strHeight = fm.getAscent();
		fm = g.getFontMetrics(mDataHolder.getDateFont());
		int dateWidth = fm.stringWidth(ClockUtil.getDate());
		mDataHolder.updateItemPosition(strNumWidth, strColonWidth, strHeight, dateWidth);

		// �E�B���h�E�T�C�Y�̕ύX
		changeWindowSize();

		// �w�i�̕s�����x�ƌ`���ύX
		setOpacity(mDataHolder.getOpacity());
		if (mDataHolder.roundRectangle())
			setShape(mDataHolder.getRoundRectangleShape());
		else
			setShape(mDataHolder.getRectangleShape());

		// �o�b�t�@�p�̃C���[�W�����
		if (imgBuffer == null) {
			imgBuffer = createImage(MAX_GUI_WIDTH, MAX_GUI_HEIGHT);
			gBuffer = null;
		}

		// �o�b�t�@�p�̃O���t�B�b�N�����
		if (gBuffer == null)
			gBuffer = (Graphics2D) imgBuffer.getGraphics();

		// �o�b�t�@���N���A����
		gBuffer.clearRect(0, 0, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());

		// �w�i�F���X�V
		gBuffer.setColor(mDataHolder.getBackgroundColor());
		gBuffer.fillRect(0, 0, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());

		// �A���`�G�C���A�XON
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// �������擾
		int hour = ClockUtil.getHour();
		int min = ClockUtil.getMinute();
		int sec = ClockUtil.getSecond();
		int msec = ClockUtil.getMilliSecond();

		// �f�W�^����������
		if (mDataHolder.isRainbow()) {
			// ���F�̐ݒ���s��
			LinearGradientPaint gradient = new LinearGradientPaint(new Point2D.Float(0,
					mDataHolder.getTimeY() * 11 / 10), new Point2D.Float(
					mDataHolder.getStrHeight() / 2, mDataHolder.getTimeY() * 11 / 10
							+ mDataHolder.getStrHeight() / 2), RainbowUtil.DIST,
					RainbowUtil.COLORS, MultipleGradientPaint.CycleMethod.NO_CYCLE,
					ColorSpaceType.LINEAR_RGB, RainbowUtil.getAffineTransform());
			gBuffer.setPaint(gradient);
		} else {
			gBuffer.setColor(mDataHolder.getFontColor()); // �����F��P�F�Őݒ�
		}
		gBuffer.setFont(mDataHolder.getTimeFont()); // �t�H���g�̐ݒ�

		// �\���ʒu��ݒ�
		int digitalX; // x���W
		int digitalY = mDataHolder.getTimeY(); // y���W
		int digiBeforeY = digitalY - mDataHolder.getStrHeight() * (msec - 600) / 400; // �A�j���[�V�����\�����̌Â�����
		int digiAfterY = digiBeforeY + mDataHolder.getStrHeight(); // �A�j���[�V�����\�����̐V��������

		// ��
		digitalX = mDataHolder.getHourX(); // �`��ʒu��x���W
		if (min == 59 && sec == 59 && msec > 600) {
			gBuffer.drawString(String.format("%02d", hour), digitalX, digiBeforeY);
			if (hour == 23) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", hour + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", hour), digitalX, digitalY);
		}
		digitalX = mDataHolder.getHourColonX(); // �`��ʒu��x���W
		gBuffer.drawString(":", digitalX, digitalY);

		// ��
		digitalX = mDataHolder.getMinX(); // �`��ʒu��x���W
		if (sec == 59 && msec > 600) {
			gBuffer.drawString(String.format("%02d", min), digitalX, digiBeforeY);
			if (min == 59) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", min + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", min), digitalX, digitalY);
		}
		digitalX = mDataHolder.getMinColonX(); // �`��ʒu��x���W
		gBuffer.drawString(":", digitalX, digitalY);

		// �b
		digitalX = mDataHolder.getSecX(); // �`��ʒu��x���W
		if (msec > 600) {
			gBuffer.drawString(String.format("%02d", sec), digitalX, digiBeforeY);
			if (sec == 59) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", sec + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", sec), digitalX, digitalY);
		}

		// �������\���̈悩��͂ݏo�镔����h��Ԃ�
		gBuffer.setColor(mDataHolder.getBackgroundColor());
		gBuffer.fillRect(0, digitalY + 5, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
		gBuffer.fillRect(0, 0, mDataHolder.getGuiWidth(), digitalY - mDataHolder.getStrHeight());

		// ���t����
		if (mDataHolder.isRainbow())
			gBuffer.setColor(Color.BLUE); // ���F�ݒ莞�͐F�ɐݒ�
		else
			gBuffer.setColor(mDataHolder.getFontColor()); // �����F�̐ݒ�
		gBuffer.setFont(mDataHolder.getDateFont()); // �t�H���g�̐ݒ�
		gBuffer.drawString(ClockUtil.getDate(), mDataHolder.getDateX(), mDataHolder.getDateY()); // ������𒆉��ɕ\��

		// �A�i���O���v����
		// �\���ʒu��ݒ�
		int analogX = mDataHolder.getAnalogX();
		int analogY = mDataHolder.getAnalogY();
		int radius = mDataHolder.getRadius();

		// �\���F��ݒ�
		gBuffer.setColor(mDataHolder.getAnalogColor());

		// ���S��\��
		int centerR = mDataHolder.getCenterOvalRadius(); // �~�̔��a
		gBuffer.fillOval(analogX - centerR / 2, analogY - centerR / 2, centerR, centerR);

		// �O�g��\��
		int outerR = mDataHolder.getOuterOvalRadius(); // �~�̔��a
		for (int i = 0; i < 12; i++) {
			double x = analogX + (Math.sin(Math.toRadians(i * 30))) * radius;
			double y = analogY - (Math.cos(Math.toRadians(i * 30))) * radius;
			gBuffer.fillOval((int) x - outerR / 2, (int) y - outerR / 2, outerR, outerR);
		}

		// �e�j�̊p�x���v�Z
		double secX = analogX + (Math.sin(Math.toRadians(sec * 6))) * radius * 0.9;
		double secY = analogY - (Math.cos(Math.toRadians(sec * 6))) * radius * 0.9;
		double minX = analogX + (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius
				* 0.75;
		double minY = analogY - (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius
				* 0.75;
		double hourX = analogX + (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius
				* 0.6;
		double hourY = analogY - (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius
				* 0.6;

		// �b�j��\��
		gBuffer.setStroke(new BasicStroke(mDataHolder.getSecLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) secX, (int) secY);

		// ���j��\��
		gBuffer.setStroke(new BasicStroke(mDataHolder.getMinLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) minX, (int) minY);

		// ���j��\��
		gBuffer.setStroke(new BasicStroke(mDataHolder.getHourLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) hourX, (int) hourY);

		// �`��
		g.drawImage(imgBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (leftClickFlag == true) { // ���h���b�O
			int dx = e.getXOnScreen() - startDragPos.x;
			int dy = e.getYOnScreen() - startDragPos.y;
			setLocation(startGuiPos.x + dx, startGuiPos.y + dy);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // ���N���b�N
			startDragPos = e.getLocationOnScreen();
			startGuiPos = getLocation();
			leftClickFlag = true;
		}
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) // ���N���b�N�������ꂽ
			leftClickFlag = false;
		else if (e.isPopupTrigger()) // �|�b�v�A�b�v�g���K�[
			mPopupMenu.show(this, e.getX(), e.getY());
	}

	/**
	 * ���C�����\�b�h
	 * ���v��\�����܂�
	 */
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.setVisible(true);
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

	/**
	 * �E�B���h�E�̃T�C�Y��ύX���܂�
	 */
	private void changeWindowSize() {
		setSize(mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
	}
}
