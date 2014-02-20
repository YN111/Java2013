import java.awt.Color;
import java.awt.Font;

/**
 * ���v�̕\���Ɋւ���ݒ�l��ێ�����N���X
 */
public class SettingDataHolder {

	public static final int MENUBAR_HEIGHT = 40; // ���j���[�o�[�̍���

	// �t�H���g�Ɋւ���ݒ�l���i�[����t�B�[���h
	private String fontType = Font.MONOSPACED;
	private int fontStyle = Font.PLAIN;
	private int timeFontSize = 150; // ���������̕����T�C�Y
	private int dateFontSize = 50; // ���t�����̕����T�C�Y
	private Font timeFont = new Font(fontType, fontStyle, timeFontSize);
	private Font dateFont = new Font(fontType, fontStyle, dateFontSize);

	// �F�Ɋւ���ݒ�l���i�[����t�B�[���h
	private Color fontColor = Color.BLUE; // �����F
	private Color analogColor = Color.LIGHT_GRAY; // �A�i���O���v�̐F
	private Color backgroundColor = Color.BLACK; // �w�i�F
	private boolean rainbowFlg = false; // ���F�ݒ�̏ꍇ��true�ɂȂ�

	// �\���ʒu�Ɋւ���ݒ�l���i�[����t�B�[���h
	private int guiWidth; // GUI�̕�
	private int guiHeight; // GUI�̍���
	private int hourX; // �����\����x���W�i���j
	private int hourColonX; // �����\����x���W�i���ƕ��̊Ԃ̃R�����j
	private int minX; // �����\����x���W�i���j
	private int minColonX; // �����\����x���W�i���ƕb�̊Ԃ̃R�����j
	private int secX; // �����\����x���W�i�b�j
	private int timeY; // �����\����y���W
	private int dateX; // ���t�\����x���W
	private int dateY; // ���t�\����y���W
	private int analogX; // �A�i���O���v�̒��SX���W
	private int analogY; // �A�i���O���v�̒��SY���W
	private int radius; // �A�i���O���v�̔��a

	// �����̕��ƍ������i�[����t�B�[���h
	private int strNumWidth; // �����̕�
	private int strColonWidth; // �R�����̕�
	private int strHeight; // ����

	// �A�i���O���v�̕`��T�C�Y���i�[����t�B�[���h
	private float secLineWidth; // �b�j�̕�
	private float minLineWidth; // ���j�̕�
	private float hourLineWidth; // ���j�̕�
	private int centerOvalRadius; // ���S�ɕ\������~�̔��a
	private int outerOvalRadius; // �O���ɕ\������~�̔��a

	/**
	 * �t�H���g�^�C�v�̃Z�b�^
	 */
	public void setFontType(String type) {
		fontType = type;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �t�H���g�X�^�C���̃Z�b�^
	 */
	public void setFontStyle(int style) {
		fontStyle = style;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �t�H���g�T�C�Y�̃Z�b�^
	 * 
	 * @param size
	 *            ���������̕����T�C�Y
	 */
	public void setFontSize(int size) {
		timeFontSize = size;
		dateFontSize = size / 3;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �����F�̃Z�b�^
	 */
	public void setFontColor(Color color) {
		fontColor = color;
	}

	/**
	 * ���F�t���O�̃Z�b�^
	 */
	public void setRainbowFlg(boolean flg) {
		rainbowFlg = flg;
	}

	/**
	 * �A�i���O���v�̐F�̃Z�b�^
	 */
	public void setAnalogColor(Color color) {
		analogColor = color;
	}

	/**
	 * �w�i�F�̃Z�b�^
	 */
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	 * �����̑傫���ɉ����ĕ\���ʒu���X�V����
	 * 
	 * @param strNumWidth
	 *            �����̕�
	 * @param strColonWidth
	 *            �R�����̕�
	 * @param strHeight
	 *            �����̍���
	 * @param dateWidth
	 *            ���t�\�������̕�
	 */
	public void renewViewPoint(int strNumWidth, int strColonWidth, int strHeight, int dateWidth) {
		// �����̕��ƍ����̒l���i�[
		this.strNumWidth = strNumWidth;
		this.strColonWidth = strColonWidth;
		this.strHeight = strHeight;

		// GUI�̃T�C�Y
		guiWidth = strNumWidth * 8 + strColonWidth * 2;
		guiHeight = strHeight * 39 / 10 + MENUBAR_HEIGHT;

		// �A�i���O���v�̕\���ʒu
		analogX = guiWidth / 2;
		analogY = strHeight + MENUBAR_HEIGHT;
		radius = strHeight * 8 / 10;

		// �f�W�^�����v�̕\���ʒu
		hourX = strNumWidth;
		hourColonX = strNumWidth * 3;
		minX = strNumWidth * 3 + strColonWidth;
		minColonX = strNumWidth * 5 + strColonWidth;
		secX = strNumWidth * 5 + strColonWidth * 2;
		timeY = analogY + strHeight * 2;

		// ���t�̕\���ʒu
		dateX = guiWidth / 2 - dateWidth / 2;
		dateY = timeY + strHeight * 5 / 10;

		// �A�i���O���v�̐j�̕�
		secLineWidth = strHeight / 75;
		minLineWidth = secLineWidth * 2;
		hourLineWidth = secLineWidth * 3.5f;
		if (secLineWidth < 1.0f)
			secLineWidth = 1.0f;
		if (minLineWidth < 2.0f)
			minLineWidth = 2.0f;
		if (hourLineWidth < 3.0f)
			hourLineWidth = 3.0f;

		// �A�i���O���v�̒��S�ƊO���̉~�̔��a
		centerOvalRadius = strHeight / 9;
		outerOvalRadius = centerOvalRadius / 2;
		if (centerOvalRadius < 6)
			centerOvalRadius = 6;
		if (outerOvalRadius < 4)
			outerOvalRadius = 4;
	}

	/**
	 * �����\�������̃t�H���g�̃Q�b�^
	 */
	public Font getTimeFont() {
		return timeFont;
	}

	/**
	 * ���t�\�������̃t�H���g�̃Q�b�^
	 */
	public Font getDateFont() {
		return dateFont;
	}

	/**
	 * �����F�̃Q�b�^
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * ���F�t���O�̃Q�b�^
	 */
	public boolean isRainbow() {
		return rainbowFlg;
	}

	/**
	 * �A�i���O���v�F�̃Q�b�^
	 */
	public Color getAnalogColor() {
		return analogColor;
	}

	/**
	 * �w�i�F�̃Q�b�^
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * ���l�̕��̃Q�b�^
	 */
	public int getStrNumWidth() {
		return strNumWidth;
	}

	/**
	 * �R�����̍����̃Q�b�^
	 */
	public int getStrColonWidth() {
		return strColonWidth;
	}

	/**
	 * �����̍����̃Q�b�^
	 */
	public int getStrHeight() {
		return strHeight;
	}

	/**
	 * GUI���̃Q�b�^
	 */
	public int getGuiWidth() {
		return guiWidth;
	}

	/**
	 * GUI�����̃Q�b�^
	 */
	public int getGuiHeight() {
		return guiHeight;
	}

	/**
	 * �A�i���O���v�̒��Sx���W�̃Q�b�^
	 */
	public int getAnalogX() {
		return analogX;
	}

	/**
	 * �A�i���O���v�̒��Sy���W�̃Q�b�^
	 */
	public int getAnalogY() {
		return analogY;
	}

	/**
	 * �A�i���O���v�̔��a�̃Q�b�^
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * �����\����x���W�̃Q�b�^�i���j
	 */
	public int getHourX() {
		return hourX;
	}

	/**
	 * �����\����x���W�̃Q�b�^�i���ƕ��̊Ԃ̃R�����j
	 */
	public int getHourColonX() {
		return hourColonX;
	}

	/**
	 * �����\����x���W�̃Q�b�^�i���j
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * �����\����x���W�̃Q�b�^�i���ƕb�̊Ԃ̃R�����j
	 */
	public int getMinColonX() {
		return minColonX;
	}

	/**
	 * �����\����x���W�̃Q�b�^�i�b�j
	 */
	public int getSecX() {
		return secX;
	}

	/**
	 * �����\����y���W�̃Q�b�^
	 */
	public int getTimeY() {
		return timeY;
	}

	/**
	 * ���t�\����x���W�̃Q�b�^
	 */
	public int getDateX() {
		return dateX;
	}

	/**
	 * ���t�\����y���W�̃Q�b�^
	 */
	public int getDateY() {
		return dateY;
	}

	/**
	 * �b�j�̕��̃Q�b�^
	 */
	public float getSecLineWidth() {
		return secLineWidth;
	}

	/**
	 * ���j�̕��̃Q�b�^
	 */
	public float getMinLineWidth() {
		return minLineWidth;
	}

	/**
	 * ���j�̕��̃Q�b�^
	 */
	public float getHourLineWidth() {
		return hourLineWidth;
	}

	/**
	 * �A�i���O���v�̒��S�̉~�̔��a�Q�b�^
	 */
	public int getCenterOvalRadius() {
		return centerOvalRadius;
	}

	/**
	 * �A�i���O���v�̊O���̉~�̔��a�Q�b�^
	 */
	public int getOuterOvalRadius() {
		return outerOvalRadius;
	}
}
