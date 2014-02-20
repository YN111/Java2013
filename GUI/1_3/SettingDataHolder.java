import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * ���v�̕\���Ɋւ���ݒ�l��ێ�����N���X�ł�
 */
public class SettingDataHolder {

	public static final int MENUBAR_HEIGHT = 40; // ���j���[�o�[�̍���

	// �t�H���g�Ɋւ���ݒ�l���i�[����t�B�[���h
	private String fontType = Font.MONOSPACED;
	private int fontStyle = Font.PLAIN;
	private int timeFontSize = 120; // ���������̕����T�C�Y
	private int dateFontSize = 40; // ���t�����̕����T�C�Y
	private Font timeFont = new Font(fontType, fontStyle, timeFontSize);
	private Font dateFont = new Font(fontType, fontStyle, dateFontSize);

	// �F�Ɋւ���ݒ�l���i�[����t�B�[���h
	private Color fontColor = Color.BLUE; // �����F
	private Color analogColor = Color.LIGHT_GRAY; // �A�i���O���v�̐F
	private Color backgroundColor = Color.BLACK; // �w�i�F
	private float opacity = 0.9f; // �w�i�F�̕s�����x
	private boolean rainbowFlg = false; // ���F�ݒ�̏ꍇ��true�ɂȂ�

	// �\���ʒu�Ɋւ���ݒ�l���i�[����t�B�[���h
	private int guiWidth; // GUI�̕�
	private int guiHeight; // GUI�̍���
	private boolean roundRectangle = true; // �p�ە`������邩
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
	 * �t�H���g�^�C�v��ݒ肵�܂�
	 * @param type �t�H���g�^�C�v
	 */
	public void setFontType(String type) {
		fontType = type;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �t�H���g�X�^�C����ݒ肵�܂�
	 * @param style �t�H���g�X�^�C��
	 */
	public void setFontStyle(int style) {
		fontStyle = style;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �t�H���g�T�C�Y��ݒ肵�܂�
	 * �����Ɏ��������̕����T�C�Y��ݒ肷�邱�ƂŁA���t�����̃T�C�Y���X�V���܂�
	 * @param size ���������̕����T�C�Y
	 */
	public void setFontSize(int size) {
		timeFontSize = size;
		dateFontSize = size / 3;
		// �t�H���g�̐ݒ���X�V
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * �����F��ݒ肵�܂�
	 * @param color �����F
	 */
	public void setFontColor(Color color) {
		fontColor = color;
	}

	/**
	 * ���F�t���O��ݒ肵�܂�
	 * @param flg ���F�t���O
	 */
	public void setRainbowFlg(boolean flg) {
		rainbowFlg = flg;
	}

	/**
	 * �A�i���O���v�̐F��ݒ肵�܂�
	 * @param color �A�i���O���v�F
	 */
	public void setAnalogColor(Color color) {
		analogColor = color;
	}

	/**
	 * �w�i�F��ݒ肵�܂�
	 * @param color �w�i�F
	 */
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	 * �w�i�F�̕s�����x��ݒ肵�܂�
	 * @param opacity �w�i�̓��ߐ�
	 */
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	/**
	 * �p�ە`���ON/OFF��ݒ肵�܂�
	 * @param b true:�p��, false:�l�p�`
	 */
	public void setRoundRectangle(boolean b) {
		roundRectangle = b;
	}

	/**
	 * �����̑傫���ɉ����Ċe�A�C�e���̕\���ݒ���X�V���܂�
	 * @param strNumWidth �����̕�
	 * @param strColonWidth �R�����̕�
	 * @param strHeight �����̍���
	 * @param dateWidth ���t�\�������̕�
	 */
	public void updateItemPosition(int strNumWidth, int strColonWidth, int strHeight, int dateWidth) {
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
	 * �����\�������̃t�H���g���擾���܂�
	 * @return �����\�������̃t�H���g
	 */
	public Font getTimeFont() {
		return timeFont;
	}

	/**
	 * ���t�\�������̃t�H���g���擾���܂�
	 * @return ���t�\�������̃t�H���g
	 */
	public Font getDateFont() {
		return dateFont;
	}

	/**
	 * �����F���擾���܂�
	 * @return �����F
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * ���F�t���O���擾���܂�
	 * @return ���F�t���O
	 */
	public boolean isRainbow() {
		return rainbowFlg;
	}

	/**
	 * �A�i���O���v�F���擾���܂�
	 * @return �A�i���O���v�F
	 */
	public Color getAnalogColor() {
		return analogColor;
	}

	/**
	 * �w�i�F���擾���܂�
	 * @return �w�i�F
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * �w�i�F�̕s�����x���擾���܂�
	 * @return �w�i�̓��ߐ�
	 */
	public float getOpacity() {
		return opacity;
	}

	/**
	 * ���l�̕����擾���܂�
	 * @return ���l�̕�
	 */
	public int getStrNumWidth() {
		return strNumWidth;
	}

	/**
	 * �R�����̕����擾���܂�
	 * @return �R�����̕�
	 */
	public int getStrColonWidth() {
		return strColonWidth;
	}

	/**
	 * �����̍������擾���܂�
	 * @return �����̍���
	 */
	public int getStrHeight() {
		return strHeight;
	}

	/**
	 * GUI�����擾���܂�
	 * @return GUI�̕�
	 */
	public int getGuiWidth() {
		return guiWidth;
	}

	/**
	 * GUI�������擾���܂�
	 * @return GUI�̍���
	 */
	public int getGuiHeight() {
		return guiHeight;
	}

	/**
	 * �p�ە`���ON/OFF���擾���܂�
	 * @return true:ON, false:OFF
	 */
	public boolean roundRectangle() {
		return roundRectangle;
	}

	/**
	 * �p�ێl�p�`�`��p�̐}�`��Ԃ��܂�
	 * @return GUI�̍���
	 */
	public RoundRectangle2D.Float getRoundRectangleShape() {
		return new RoundRectangle2D.Float(0, 0, guiWidth, guiHeight, guiWidth / 3, guiHeight / 3);
	}

	/**
	 * �l�p�`�`��p�̐}�`��Ԃ��܂�
	 * @return GUI�̍���
	 */
	public Rectangle2D.Float getRectangleShape() {
		return new Rectangle2D.Float(0, 0, guiWidth, guiHeight);
	}

	/**
	 * �A�i���O���v�̒��Sx���W���擾���܂�
	 * @return �A�i���O���v�̒��Sx���W
	 */
	public int getAnalogX() {
		return analogX;
	}

	/**
	 * �A�i���O���v�̒��Sy���W���擾���܂�
	 * @return �A�i���O���v�̒��Sy���W
	 */
	public int getAnalogY() {
		return analogY;
	}

	/**
	 * �A�i���O���v�̔��a���擾���܂�
	 * @return �A�i���O���v�̔��a
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * �����\����x���W�̂��擾���܂��i���j
	 * @return �����\����x���W�i���j
	 */
	public int getHourX() {
		return hourX;
	}

	/**
	 * �����\����x���W���擾���܂��i���ƕ��̊Ԃ̃R�����j
	 * @return �����\����x���W�i���ƕ��̊Ԃ̃R�����j
	 */
	public int getHourColonX() {
		return hourColonX;
	}

	/**
	 * �����\����x���W���擾���܂��i���j
	 * @return �����\����x���W�i���j
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * �����\����x���W���擾���܂��i���ƕb�̊Ԃ̃R�����j
	 * @return �����\����x���W�i���ƕb�̊Ԃ̃R�����j
	 */
	public int getMinColonX() {
		return minColonX;
	}

	/**
	 * �����\����x���W���擾���܂��i�b�j
	 * @return �����\����x���W�i�b�j
	 */
	public int getSecX() {
		return secX;
	}

	/**
	 * �����\����y���W���擾���܂�
	 * @return �����\����y���W
	 */
	public int getTimeY() {
		return timeY;
	}

	/**
	 * ���t�\����x���W���擾���܂�
	 * @return ���t�\����x���W
	 */
	public int getDateX() {
		return dateX;
	}

	/**
	 * ���t�\����y���W���擾���܂�
	 * @return ���t�\����y���W
	 */
	public int getDateY() {
		return dateY;
	}

	/**
	 * �b�j�̕����擾���܂�
	 * @return �b�j�̕�
	 */
	public float getSecLineWidth() {
		return secLineWidth;
	}

	/**
	 * ���j�̕����擾���܂�
	 * @return ���j�̕�
	 */
	public float getMinLineWidth() {
		return minLineWidth;
	}

	/**
	 * ���j�̕����擾���܂�
	 * @return ���j�̕�
	 */
	public float getHourLineWidth() {
		return hourLineWidth;
	}

	/**
	 * �A�i���O���v�̒��S�̉~�̔��a���擾���܂�
	 * @return �A�i���O���v�̒��S�̉~�̔��a
	 */
	public int getCenterOvalRadius() {
		return centerOvalRadius;
	}

	/**
	 * �A�i���O���v�̊O���̉~�̔��a���擾���܂�
	 * @return �A�i���O���v�̊O���̉~�̔��a
	 */
	public int getOuterOvalRadius() {
		return outerOvalRadius;
	}
}
