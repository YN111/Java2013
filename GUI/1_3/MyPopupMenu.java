import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * �|�b�v�A�b�v���j���[�Ɋւ���ݒ���s���N���X�ł�
 */
public class MyPopupMenu extends PopupMenu implements ActionListener {
	private static final long serialVersionUID = 8037236915904985141L;

	// �e���j���[�̖��O
	public static final String MENU_FONT_TYPE = "�t�H���g�^�C�v";
	public static final String MENU_FONT_STYLE = "�t�H���g�X�^�C��";
	public static final String MENU_FONT_SIZE = "�t�H���g�T�C�Y";
	public static final String MENU_FONT_COLOR = "�����F";
	public static final String MENU_ANALOG_COLOR = "�A�i���O���v�F";
	public static final String MENU_BACKGROUND_COLOR = "�w�i�F";
	public static final String MENU_OPACITY = "���ߐ�";
	public static final String MENU_FORM = "�`��";
	public static final String MENU_FINISH = "�I��";

	// �t�H���g�^�C�v
	public static final String FONT_MONOSPACED = "Monospaced";
	public static final String FONT_SERIF = "Serif";
	public static final String FONT_SANS_SERIF = "SansSerif";

	// �t�H���g�X�^�C��
	public static final String FONT_NORMAL = "�W��";
	public static final String FONT_BOLD = "����";
	public static final String FONT_ITALIC = "�Α�";

	// �F
	public static final String COLOR_SYSTEM = "�V�X�e���J���[";
	public static final String COLOR_WHITE = "��";
	public static final String COLOR_BLACK = "��";
	public static final String COLOR_GRAY = "�O���[";
	public static final String COLOR_RED = "��";
	public static final String COLOR_BLUE = "��";
	public static final String COLOR_YELLOW = "��";
	public static final String COLOR_GREEN = "��";
	public static final String COLOR_PINK = "�s���N";
	public static final String COLOR_CYAN = "���F";
	public static final String COLOR_RAINBOW = "���F";

	// �`��
	public static final String FORM_RECTANGLE = "�l�p�`";
	public static final String FORM_ROUND_RECTANGLE = "�p�ێl�p�`";

	// �t�B�[���h
	private SettingDataHolder mDataHolder;
	private Menu menuFontType;
	private Menu menuFontStyle;
	private Menu menuFontSize;
	private Menu menuFontColor;
	private Menu menuAnalogColor;
	private Menu menuBackgroundColor;
	private Menu menuOpacity;
	private Menu menuForm;
	private MenuItem menuFinish;
	private Font strFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // ������̃t�H���g

	MyPopupMenu(SettingDataHolder d) {
		super();
		this.mDataHolder = d;

		// �e���ڂ̐ݒ�
		initFontTypeList();
		initFontStyleList();
		initFontSizeList();
		initFontColorList();
		initAnalogColorList();
		initBackgroundColorList();
		initOpacity();
		initForm();

		add(menuFontType);
		add(menuFontStyle);
		add(menuFontSize);
		add(menuFontColor);
		add(menuAnalogColor);
		add(menuBackgroundColor);
		add(menuOpacity);
		add(menuForm);

		// �I���{�^���̐ݒ�
		menuFinish = new MenuItem(MENU_FINISH);
		menuFinish.addActionListener(this);
		add(menuFinish);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �t�H���g�^�C�v�̕ύX
		if (((MenuItem) e.getSource()).getLabel().equals(MENU_FONT_TYPE))
			changeFontType(e.getActionCommand());

		// �t�H���g�X�^�C���̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_FONT_STYLE))
			changeFontStyle(e.getActionCommand());

		// �t�H���g�T�C�Y�̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_FONT_SIZE))
			mDataHolder.setFontSize(Integer.parseInt(e.getActionCommand()));

		// �����F�̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_FONT_COLOR))
			changeFontColor(e.getActionCommand());

		// �A�i���O���v�F�̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_ANALOG_COLOR))
			changeAnalogColor(e.getActionCommand());

		// �w�i�F�̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_BACKGROUND_COLOR))
			changeBackgroundColor(e.getActionCommand());

		// ���ߐ��̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_OPACITY))
			mDataHolder.setOpacity(1.0f - Float.parseFloat(e.getActionCommand()) / 100);

		// �`��̕ύX
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_FORM))
			changeForm(e.getActionCommand());

		// �v���O�����̏I��
		else if (((MenuItem) e.getSource()).getLabel().equals(MENU_FINISH))
			System.exit(0);

		else
			return;
	}

	/**
	 * �t�H���g�^�C�v�̐ݒ荀�ڂ̏�����
	 */
	private void initFontTypeList() {
		menuFontType = new Menu(MENU_FONT_TYPE);
		menuFontType.setFont(strFont);
		menuFontType.add(FONT_MONOSPACED);
		menuFontType.add(FONT_SERIF);
		menuFontType.add(FONT_SANS_SERIF);
		menuFontType.addActionListener(this);
	}

	/**
	 * �t�H���g�X�^�C���̐ݒ荀�ڂ̏�����
	 */
	private void initFontStyleList() {
		menuFontStyle = new Menu(MENU_FONT_STYLE);
		menuFontStyle.setFont(strFont);
		menuFontStyle.add(FONT_NORMAL);
		menuFontStyle.add(FONT_BOLD);
		menuFontStyle.add(FONT_ITALIC);
		menuFontStyle.addActionListener(this);
	}

	/**
	 * �t�H���g�T�C�Y�̐ݒ荀�ڂ̏�����
	 */
	private void initFontSizeList() {
		menuFontSize = new Menu(MENU_FONT_SIZE);
		menuFontSize.setFont(strFont);
		menuFontSize.add("50");
		menuFontSize.add("60");
		menuFontSize.add("70");
		menuFontSize.add("80");
		menuFontSize.add("90");
		menuFontSize.add("100");
		menuFontSize.add("110");
		menuFontSize.add("120");
		menuFontSize.add("130");
		menuFontSize.add("140");
		menuFontSize.add("150");
		menuFontSize.add("160");
		menuFontSize.add("170");
		menuFontSize.add("180");
		menuFontSize.add("190");
		menuFontSize.add("200");
		menuFontSize.addActionListener(this);
	}

	/**
	 * �����F�̐ݒ荀�ڂ̏�����
	 */
	private void initFontColorList() {
		menuFontColor = new Menu(MENU_FONT_COLOR);
		menuFontColor.setFont(strFont);
		menuFontColor.add(COLOR_WHITE);
		menuFontColor.add(COLOR_BLACK);
		menuFontColor.add(COLOR_GRAY);
		menuFontColor.add(COLOR_RED);
		menuFontColor.add(COLOR_BLUE);
		menuFontColor.add(COLOR_YELLOW);
		menuFontColor.add(COLOR_GREEN);
		menuFontColor.add(COLOR_PINK);
		menuFontColor.add(COLOR_CYAN);
		menuFontColor.add(COLOR_RAINBOW);
		menuFontColor.addActionListener(this);
	}

	/**
	 * �A�i���O���v�F�̐ݒ荀�ڂ̏�����
	 */
	private void initAnalogColorList() {
		menuAnalogColor = new Menu(MENU_ANALOG_COLOR);
		menuAnalogColor.setFont(strFont);
		menuAnalogColor.add(COLOR_WHITE);
		menuAnalogColor.add(COLOR_BLACK);
		menuAnalogColor.add(COLOR_GRAY);
		menuAnalogColor.add(COLOR_RED);
		menuAnalogColor.add(COLOR_BLUE);
		menuAnalogColor.add(COLOR_YELLOW);
		menuAnalogColor.add(COLOR_GREEN);
		menuAnalogColor.add(COLOR_PINK);
		menuAnalogColor.add(COLOR_CYAN);
		menuAnalogColor.addActionListener(this);
	}

	/**
	 * �w�i�F�̐ݒ荀�ڂ̏�����
	 */
	private void initBackgroundColorList() {
		menuBackgroundColor = new Menu(MENU_BACKGROUND_COLOR);
		menuBackgroundColor.setFont(strFont);
		menuBackgroundColor.add(COLOR_SYSTEM);
		menuBackgroundColor.add(COLOR_WHITE);
		menuBackgroundColor.add(COLOR_BLACK);
		menuBackgroundColor.add(COLOR_GRAY);
		menuBackgroundColor.add(COLOR_RED);
		menuBackgroundColor.add(COLOR_BLUE);
		menuBackgroundColor.add(COLOR_YELLOW);
		menuBackgroundColor.add(COLOR_GREEN);
		menuBackgroundColor.add(COLOR_PINK);
		menuBackgroundColor.add(COLOR_CYAN);
		menuBackgroundColor.addActionListener(this);
	}

	/**
	 * ���ߐ��̐ݒ荀�ڂ̏�����
	 */
	private void initOpacity() {
		menuOpacity = new Menu(MENU_OPACITY);
		menuOpacity.setFont(strFont);
		menuOpacity.add("0");
		menuOpacity.add("10");
		menuOpacity.add("20");
		menuOpacity.add("30");
		menuOpacity.add("40");
		menuOpacity.add("50");
		menuOpacity.add("60");
		menuOpacity.add("70");
		menuOpacity.add("80");
		menuOpacity.add("90");
		menuOpacity.addActionListener(this);
	}

	/**
	 * �`��̐ݒ荀�ڂ̏�����
	 */
	private void initForm() {
		menuForm = new Menu(MENU_FORM);
		menuForm.setFont(strFont);
		menuForm.add(FORM_RECTANGLE);
		menuForm.add(FORM_ROUND_RECTANGLE);
		menuForm.addActionListener(this);
	}

	/**
	 * �t�H���g��ύX���܂�
	 * @param selected
	 */
	private void changeFontType(String selected) {
		if (selected.equals(FONT_MONOSPACED))
			mDataHolder.setFontType(Font.MONOSPACED);
		else if (selected.equals(FONT_SERIF))
			mDataHolder.setFontType(Font.SERIF);
		else
			mDataHolder.setFontType(Font.SANS_SERIF);
	}

	/**
	 * �t�H���g�X�^�C����ύX���܂�
	 * @param selected
	 */
	private void changeFontStyle(String selected) {
		if (selected.equals(FONT_NORMAL))
			mDataHolder.setFontStyle(Font.PLAIN);
		else if (selected.equals(FONT_BOLD))
			mDataHolder.setFontStyle(Font.BOLD);
		else
			mDataHolder.setFontStyle(Font.ITALIC);
	}

	/**
	 * �����F��ύX���܂�
	 * @param selected
	 */
	private void changeFontColor(String selected) {
		mDataHolder.setRainbowFlg(false); // ���F�t���O����U��������
		if (selected.equals(COLOR_WHITE))
			mDataHolder.setFontColor(Color.WHITE);
		else if (selected.equals(COLOR_BLACK))
			mDataHolder.setFontColor(Color.BLACK);
		else if (selected.equals(COLOR_GRAY))
			mDataHolder.setFontColor(Color.LIGHT_GRAY);
		else if (selected.equals(COLOR_RED))
			mDataHolder.setFontColor(Color.RED);
		else if (selected.equals(COLOR_BLUE))
			mDataHolder.setFontColor(Color.BLUE);
		else if (selected.equals(COLOR_YELLOW))
			mDataHolder.setFontColor(Color.YELLOW);
		else if (selected.equals(COLOR_GREEN))
			mDataHolder.setFontColor(Color.GREEN);
		else if (selected.equals(COLOR_PINK))
			mDataHolder.setFontColor(Color.PINK);
		else if (selected.equals(COLOR_CYAN))
			mDataHolder.setFontColor(Color.CYAN);
		else
			mDataHolder.setRainbowFlg(true); // ���F
	}

	/**
	 * �A�i���O���v�̐F��ύX���܂�
	 * @param selected
	 */
	private void changeAnalogColor(String selected) {
		if (selected.equals(COLOR_WHITE))
			mDataHolder.setAnalogColor(Color.WHITE);
		else if (selected.equals(COLOR_BLACK))
			mDataHolder.setAnalogColor(Color.BLACK);
		else if (selected.equals(COLOR_GRAY))
			mDataHolder.setAnalogColor(Color.LIGHT_GRAY);
		else if (selected.equals(COLOR_RED))
			mDataHolder.setAnalogColor(Color.RED);
		else if (selected.equals(COLOR_BLUE))
			mDataHolder.setAnalogColor(Color.BLUE);
		else if (selected.equals(COLOR_YELLOW))
			mDataHolder.setAnalogColor(Color.YELLOW);
		else if (selected.equals(COLOR_GREEN))
			mDataHolder.setAnalogColor(Color.GREEN);
		else if (selected.equals(COLOR_PINK))
			mDataHolder.setAnalogColor(Color.PINK);
		else
			mDataHolder.setAnalogColor(Color.CYAN);
	}

	/**
	 * �w�i�F��ύX���܂�
	 * @param selected
	 */
	private void changeBackgroundColor(String selected) {
		if (selected.equals(COLOR_SYSTEM))
			mDataHolder.setBackgroundColor(new Color(SystemColor.desktop.getRGB()));
		else if (selected.equals(COLOR_WHITE))
			mDataHolder.setBackgroundColor(Color.WHITE);
		else if (selected.equals(COLOR_BLACK))
			mDataHolder.setBackgroundColor(Color.BLACK);
		else if (selected.equals(COLOR_GRAY))
			mDataHolder.setBackgroundColor(Color.LIGHT_GRAY);
		else if (selected.equals(COLOR_RED))
			mDataHolder.setBackgroundColor(Color.RED);
		else if (selected.equals(COLOR_BLUE))
			mDataHolder.setBackgroundColor(Color.BLUE);
		else if (selected.equals(COLOR_YELLOW))
			mDataHolder.setBackgroundColor(Color.YELLOW);
		else if (selected.equals(COLOR_GREEN))
			mDataHolder.setBackgroundColor(Color.GREEN);
		else if (selected.equals(COLOR_PINK))
			mDataHolder.setBackgroundColor(Color.PINK);
		else
			mDataHolder.setBackgroundColor(Color.CYAN);
	}

	/**
	 * �`���ύX���܂�
	 * @param selected
	 */
	private void changeForm(String selected) {
		if (selected.equals(FORM_RECTANGLE))
			mDataHolder.setRoundRectangle(false);
		else
			mDataHolder.setRoundRectangle(true);
	}
}
