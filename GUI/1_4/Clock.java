import java.awt.*;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.prefs.Preferences;

public class Clock extends Frame implements ActionListener {

	private static final long serialVersionUID = -643616571828677302L;
	public static final int DEFAULT_GUI_WIDTH = 750; // GUI���̏����l
	public static final int DEFAULT_GUI_HEIGHT = 600; // GUI�����̏����l
	public static final int MAX_GUI_WIDTH = 1920; // GUI���̍ő�l
	public static final int MAX_GUI_HEIGHT = 1080; // GUI�����̍ő�l

	private Image imgBuffer = null; // ������h�~�p�̃o�b�t�@
	private Graphics2D gBuffer = null;
	private SettingDataHolder mDataHolder;
	private Preferences prefs = Preferences.userNodeForPackage(getClass());

	/**
	 * �R���X�g���N�^�FGUI�̐ݒ�Ɓ~�{�^���ŏI�����鏈�����L�q
	 */
	Clock() {
		super("Clock");
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setResizable(false);
		setBackground(Color.BLACK);
		initMenu();
		mDataHolder = new SettingDataHolder();
		setDefaultValue();

		// Close�C�x���g���󂯎�郊�X�i�[
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �e������ۑ�
				prefs.putInt("x", getX());
				prefs.putInt("y", getY());
				prefs.put("fontType", mDataHolder.getFontType());
				prefs.putInt("fontStyle", mDataHolder.getFontStyle());
				prefs.putInt("fontSize", mDataHolder.getFontSize());
				prefs.put("fontColor", convertColorToString(mDataHolder.getFontColor()));
				prefs.putBoolean("rainbowFlag", mDataHolder.isRainbow());
				prefs.put("fontColor", convertColorToString(mDataHolder.getFontColor()));
				prefs.put("analogColor", convertColorToString(mDataHolder.getAnalogColor()));
				prefs.put("backgroundColor", convertColorToString(mDataHolder.getBackgroundColor()));

				// ���v�̕\�����I��
				System.exit(0);
			}
		});
	}

	/**
	 * �����̕��ɉ����ăE�B���h�E��K�؂ȃT�C�Y�ɕύX
	 */
	private void changeWindowSize() {
		setSize(mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
	}

	/**
	 * ���j���[�o�[�̐ݒ���s��
	 */
	private void initMenu() {
		MenuBar bar = new MenuBar();
		bar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		Menu menu = new Menu("���j���[");
		MenuItem property = new MenuItem("�v���p�e�B");
		MenuItem initialize = new MenuItem("������Ԃɖ߂�");

		// ���X�i�[�ǉ�
		property.addActionListener(this);
		initialize.addActionListener(this);

		// ���ڂ�ǉ�
		menu.add(property);
		menu.add(initialize);
		bar.add(menu);

		// �\��
		this.setMenuBar(bar);
	}

	/**
	 * �ۑ�����Ă����l��ǂݏo���Đݒ肵�܂�
	 */
	private void setDefaultValue() {
		setLocation(prefs.getInt("x", 0), prefs.getInt("y", 0));
		mDataHolder.setFontType(prefs.get("fontType", Font.MONOSPACED));
		mDataHolder.setFontStyle(prefs.getInt("fontStyle", Font.PLAIN));
		mDataHolder.setFontSize(prefs.getInt("fontSize", 150));
		mDataHolder.setRainbowFlg(prefs.getBoolean("rainbowFlag", false));
		mDataHolder.setFontColor(convertStringToColor(prefs.get("fontColor", "BLUE")));
		mDataHolder.setAnalogColor(convertStringToColor(prefs.get("analogColor", "GRAY")));
		mDataHolder.setBackgroundColor(convertStringToColor(prefs.get("backgroundColor", "BLACK")));
	}

	/**
	 * �J���[�I�u�W�F�N�g�𕶎���ɕϊ����܂�
	 */
	private String convertColorToString(Color color) {
		if (color.equals(Color.WHITE))
			return "WHITE";
		else if (color.equals(Color.BLACK))
			return "BLACK";
		else if (color.equals(Color.LIGHT_GRAY))
			return "GRAY";
		else if (color.equals(Color.RED))
			return "RED";
		else if (color.equals(Color.BLUE))
			return "BLUE";
		else if (color.equals(Color.YELLOW))
			return "YELLOW";
		else if (color.equals(Color.GREEN))
			return "GREEN";
		else if (color.equals(Color.PINK))
			return "PINK";
		else if (color.equals(Color.CYAN))
			return "CYAN";
		else
			throw new AssertionError();
	}

	/**
	 * ��������J���[�I�u�W�F�N�g�ɕϊ����܂�
	 */
	private Color convertStringToColor(String str) {
		if ("WHITE".equals(str))
			return Color.WHITE;
		else if ("BLACK".equals(str))
			return Color.BLACK;
		else if ("GRAY".equals(str))
			return Color.LIGHT_GRAY;
		else if ("RED".equals(str))
			return Color.RED;
		else if ("BLUE".equals(str))
			return Color.BLUE;
		else if ("YELLOW".equals(str))
			return Color.YELLOW;
		else if ("GREEN".equals(str))
			return Color.GREEN;
		else if ("PINK".equals(str))
			return Color.PINK;
		else if ("CYAN".equals(str))
			return Color.CYAN;
		else
			throw new AssertionError();
	}

	/**
	 * ���j���[�A�C�e�����N���b�N���ꂽ���̏���
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�v���p�e�B")) {
			// �v���p�e�B�_�C�A���O��\��
			new PropertyDialog(this, mDataHolder);

		} else {
			// ������Ԃɖ߂�
			setLocation(0, 0);
			mDataHolder.setFontType(Font.MONOSPACED);
			mDataHolder.setFontStyle(Font.PLAIN);
			mDataHolder.setFontSize(150);
			mDataHolder.setRainbowFlg(false);
			mDataHolder.setFontColor(Color.BLUE);
			mDataHolder.setAnalogColor(Color.LIGHT_GRAY);
			mDataHolder.setBackgroundColor(Color.BLACK);
		}
	}

	/**
	 * �`����s��
	 */
	public void paint(Graphics g) {
		// �����̕��ɉ����ĕ\���̐ݒ�l���X�V
		FontMetrics fm = g.getFontMetrics(mDataHolder.getTimeFont());
		int strNumWidth = fm.stringWidth("8"); // �����̕�
		int strColonWidth = fm.stringWidth(":"); // �R�����̕�
		int strHeight = fm.getAscent();
		fm = g.getFontMetrics(mDataHolder.getDateFont());
		int dateWidth = fm.stringWidth(ClockUtil.getDate());
		mDataHolder.renewViewPoint(strNumWidth, strColonWidth, strHeight, dateWidth);

		// �E�B���h�E�T�C�Y�̕ύX
		changeWindowSize();

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
		int millisecond = ClockUtil.getMilliSecond();

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
		int digiBeforeY = digitalY - mDataHolder.getStrHeight() * (millisecond - 600) / 400; // �A�j���[�V�����\�����̌Â�����
		int digiAfterY = digiBeforeY + mDataHolder.getStrHeight(); // �A�j���[�V�����\�����̐V��������

		// ��
		digitalX = mDataHolder.getHourX(); // �`��ʒu��x���W
		if (min == 59 && sec == 59 && millisecond > 600) {
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
		if (sec == 59 && millisecond > 600) {
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
		if (millisecond > 600) {
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

	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * ���C�����\�b�h ���v�̃T�C�Y���w�肵�ĕ\������
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
}
