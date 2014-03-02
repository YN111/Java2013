import java.awt.*;
import java.awt.event.*;

/**
 * �_�C�A���O�Ɋւ���ݒ���s���N���X
 */
public class PropertyDialog extends Dialog implements ItemListener, ActionListener {

	private static final long serialVersionUID = 9200980135849309407L;

	// �e���X�g�̖��O
	public static final String LIST_FONT_TYPE = "FontType";
	public static final String LIST_FONT_STYLE = "FontStyle";
	public static final String LIST_FONT_SIZE = "FontSize";
	public static final String LIST_FONT_COLOR = "FontColor";
	public static final String LIST_ANALOG_COLOR = "AnalogColor";
	public static final String LIST_BACKGROUND_COLOR = "BackgroundColor";

	// �t�H���g�^�C�v
	public static final String FONT_MONOSPACED = "Monospaced";
	public static final String FONT_SERIF = "Serif";
	public static final String FONT_SANS_SERIF = "SansSerif";

	// �t�H���g�X�^�C��
	public static final String FONT_NORMAL = "�W��";
	public static final String FONT_BOLD = "����";
	public static final String FONT_ITALIC = "�Α�";

	// �F
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

	// �t�B�[���h
	private SettingDataHolder mDataHolder;
	private List fontTypeList;
	private List fontStyleList;
	private List fontSizeList;
	private List fontColorList;
	private List analogColorList;
	private List backgroundColorList;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // ������̃t�H���g

	// �e�����̏����l
	private String initialFontType;
	private int initialFontStyle;
	private int initialFontSize;
	private boolean initialRainbowFlag;
	private Color initialFontColor;
	private Color initialAnalogColor;
	private Color initialBackgroundColor;

	/**
	 * �R���X�g���N�^<br>
	 * �_�C�A���O�̍��ڂ�ݒ肵�A�\�����܂�
	 * @param owner
	 * @param d
	 */
	PropertyDialog(Clock owner, SettingDataHolder d) {
		super(owner);
		mDataHolder = d;
		setResizable(false); // �T�C�Y�ύX�s��
		setSize(300, 300);
		setLocation(owner.getX() + 20, owner.getY() + 20);
		setTitle("Clock - �v���p�e�B");

		// Close�C�x���g���󂯎�郊�X�i�[
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		// �e���ڂ̏����l��ێ�
		initValue();

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 0, 0);

		// �e���ڂ̐ݒ�
		initFontTypeList();
		initFontStyleList();
		initFontSizeList();
		initFontColorList();
		initAnalogColorList();
		initBackgroundColorList();

		// �e�v�f��ǉ�
		addLabel(0, 0, "�t�H���g", gbl, gbc);
		addList(1, 0, fontTypeList, gbl, gbc);
		addLabel(0, 1, "�X�^�C��", gbl, gbc);
		addList(1, 1, fontStyleList, gbl, gbc);
		addLabel(0, 2, "�T�C�Y", gbl, gbc);
		addList(1, 2, fontSizeList, gbl, gbc);
		addLabel(0, 3, "�����F", gbl, gbc);
		addList(1, 3, fontColorList, gbl, gbc);
		addLabel(0, 4, "�A�i���O���v�F", gbl, gbc);
		addList(1, 4, analogColorList, gbl, gbc);
		addLabel(0, 5, "�w�i�F", gbl, gbc);
		addList(1, 5, backgroundColorList, gbl, gbc);
		addBtn(0, 6, gbl, gbc);

		setVisible(true);
	}

	/**
	 * �{�^�����������Ƃ��̏���
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {
			// OK�{�^��
			setVisible(false);
		} else {
			// �L�����Z���{�^���F�S�ď����l�ɖ߂�
			mDataHolder.setFontType(initialFontType);
			mDataHolder.setFontStyle(initialFontStyle);
			mDataHolder.setFontSize(initialFontSize);
			mDataHolder.setRainbowFlg(initialRainbowFlag);
			mDataHolder.setFontColor(initialFontColor);
			mDataHolder.setAnalogColor(initialAnalogColor);
			mDataHolder.setBackgroundColor(initialBackgroundColor);
			setVisible(false);
		}
	}

	/**
	 * �A�C�e�����I�����ꂽ���̏���
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// �t�H���g�^�C�v�̕ύX
		if (((List) e.getSource()).getName().equals(LIST_FONT_TYPE)) {
			String selected = fontTypeList.getSelectedItem();
			changeFontType(selected);
		}

		// �t�H���g�X�^�C���̕ύX
		if (((List) e.getSource()).getName().equals(LIST_FONT_STYLE)) {
			String selected = fontStyleList.getSelectedItem();
			changeFontStyle(selected);
		}

		// �t�H���g�T�C�Y�̕ύX
		if (((List) e.getSource()).getName().equals(LIST_FONT_SIZE)) {
			String selected = fontSizeList.getSelectedItem();
			mDataHolder.setFontSize(Integer.parseInt(selected));
		}

		// �����F�̕ύX
		if (((List) e.getSource()).getName().equals(LIST_FONT_COLOR)) {
			String selected = fontColorList.getSelectedItem();
			changeFontColor(selected);
		}

		// �A�i���O���v�F�̕ύX
		if (((List) e.getSource()).getName().equals(LIST_ANALOG_COLOR)) {
			String selected = analogColorList.getSelectedItem();
			changeAnalogColor(selected);
		}

		// �w�i�F�̕ύX
		if (((List) e.getSource()).getName().equals(LIST_BACKGROUND_COLOR)) {
			String selected = backgroundColorList.getSelectedItem();
			changeBackgroundColor(selected);
		}
	}

	/**
	 * �e���ڂ̏����l��ێ����܂�
	 */
	private void initValue() {
		initialFontType = mDataHolder.getFontType();
		initialFontStyle = mDataHolder.getFontStyle();
		initialFontSize = mDataHolder.getFontSize();
		initialRainbowFlag = mDataHolder.isRainbow();
		initialFontColor = mDataHolder.getFontColor();
		initialAnalogColor = mDataHolder.getAnalogColor();
		initialBackgroundColor = mDataHolder.getBackgroundColor();
	}

	/**
	 * �t�H���g�^�C�v�̐ݒ荀�ڂ����������܂�
	 */
	private void initFontTypeList() {
		fontTypeList = new List(1);
		fontTypeList.setName(LIST_FONT_TYPE);
		fontTypeList.setFont(font);
		fontTypeList.add(FONT_MONOSPACED);
		fontTypeList.add(FONT_SERIF);
		fontTypeList.add(FONT_SANS_SERIF);
		fontTypeList.select(currentFontTypeIndex());
		fontTypeList.addItemListener(this);
	}

	/**
	 * �t�H���g�X�^�C���̐ݒ荀�ڂ��̏��������܂�
	 */
	private void initFontStyleList() {
		fontStyleList = new List(1);
		fontStyleList.setName(LIST_FONT_STYLE);
		fontStyleList.setFont(font);
		fontStyleList.add(FONT_NORMAL);
		fontStyleList.add(FONT_BOLD);
		fontStyleList.add(FONT_ITALIC);
		fontStyleList.select(currentFontStyleIndex());
		fontStyleList.addItemListener(this);
	}

	/**
	 * �t�H���g�T�C�Y�̐ݒ荀�ڂ̏�����
	 */
	private void initFontSizeList() {
		fontSizeList = new List(1);
		fontSizeList.setName(LIST_FONT_SIZE);
		fontSizeList.setFont(font);
		fontSizeList.add("50");
		fontSizeList.add("60");
		fontSizeList.add("70");
		fontSizeList.add("80");
		fontSizeList.add("90");
		fontSizeList.add("100");
		fontSizeList.add("110");
		fontSizeList.add("120");
		fontSizeList.add("130");
		fontSizeList.add("140");
		fontSizeList.add("150");
		fontSizeList.add("160");
		fontSizeList.add("170");
		fontSizeList.add("180");
		fontSizeList.add("190");
		fontSizeList.add("200");
		fontSizeList.select(currentFontSizeIndex());
		fontSizeList.addItemListener(this);
	}

	/**
	 * �����F�̐ݒ荀�ڂ̏�����
	 */
	private void initFontColorList() {
		fontColorList = new List(1);
		fontColorList.setName(LIST_FONT_COLOR);
		fontColorList.setFont(font);
		fontColorList.add(COLOR_WHITE);
		fontColorList.add(COLOR_BLACK);
		fontColorList.add(COLOR_GRAY);
		fontColorList.add(COLOR_RED);
		fontColorList.add(COLOR_BLUE);
		fontColorList.add(COLOR_YELLOW);
		fontColorList.add(COLOR_GREEN);
		fontColorList.add(COLOR_PINK);
		fontColorList.add(COLOR_CYAN);
		fontColorList.add(COLOR_RAINBOW);
		fontColorList.select(currentFontColorIndex());
		fontColorList.addItemListener(this);
	}

	/**
	 * �A�i���O���v�F�̐ݒ荀�ڂ̏�����
	 */
	private void initAnalogColorList() {
		analogColorList = new List(1);
		analogColorList.setName(LIST_ANALOG_COLOR);
		analogColorList.setFont(font);
		analogColorList.add(COLOR_WHITE);
		analogColorList.add(COLOR_BLACK);
		analogColorList.add(COLOR_GRAY);
		analogColorList.add(COLOR_RED);
		analogColorList.add(COLOR_BLUE);
		analogColorList.add(COLOR_YELLOW);
		analogColorList.add(COLOR_GREEN);
		analogColorList.add(COLOR_PINK);
		analogColorList.add(COLOR_CYAN);
		analogColorList.select(currentAnalogColorIndex());
		analogColorList.addItemListener(this);
	}

	/**
	 * �w�i�F�̐ݒ荀�ڂ̏�����
	 */
	private void initBackgroundColorList() {
		backgroundColorList = new List(1);
		backgroundColorList.setName(LIST_BACKGROUND_COLOR);
		backgroundColorList.setFont(font);
		backgroundColorList.add(COLOR_WHITE);
		backgroundColorList.add(COLOR_BLACK);
		backgroundColorList.add(COLOR_GRAY);
		backgroundColorList.add(COLOR_RED);
		backgroundColorList.add(COLOR_BLUE);
		backgroundColorList.add(COLOR_YELLOW);
		backgroundColorList.add(COLOR_GREEN);
		backgroundColorList.add(COLOR_PINK);
		backgroundColorList.add(COLOR_CYAN);
		backgroundColorList.select(currentBackgroundColorIndex());
		backgroundColorList.addItemListener(this);
	}

	/**
	 * GridBagLayout�Ƀ��x����ǉ����܂�
	 * @param x
	 * @param y
	 * @param title
	 * @param gbl
	 * @param gbc
	 */
	private void addLabel(int x, int y, String title, GridBagLayout gbl, GridBagConstraints gbc) {
		Label label = new Label(title);
		label.setFont(font);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.5d;
		gbc.weighty = 0.5d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(label, gbc);
		add(label);
	}

	/**
	 * GridBagLayout�Ƀ��X�g��ǉ����܂�
	 * @param x
	 * @param y
	 * @param list
	 * @param gbl
	 * @param gbc
	 */
	private void addList(int x, int y, List list, GridBagLayout gbl, GridBagConstraints gbc) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.5d;
		gbc.weighty = 0.5d;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(list, gbc);
		add(list);
	}

	/**
	 * GridBagLayout�Ƀ{�^����ǉ����܂�
	 * @param x
	 * @param y
	 * @param gbl
	 * @param gbc
	 */
	private void addBtn(int x, int y, GridBagLayout gbl, GridBagConstraints gbc) {
		Dimension btnSize = new Dimension(80, 25);
		Button okBtn = new Button("OK");
		okBtn.setFont(font);
		okBtn.setPreferredSize(btnSize);
		okBtn.addActionListener(this);
		Button cancelBtn = new Button("�L�����Z��");
		cancelBtn.setFont(font);
		cancelBtn.setPreferredSize(btnSize);
		cancelBtn.addActionListener(this);
		Panel pane = new Panel();
		pane.add(okBtn);
		pane.add(cancelBtn);

		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(pane, gbc);
		add(pane);
	}

	/**
	 * ���݂̃t�H���g�^�C�v�̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentFontTypeIndex() {
		String fontType = mDataHolder.getFontType();
		if (fontType.equals(Font.MONOSPACED))
			return 0;
		else if (fontType.equals(Font.SERIF))
			return 1;
		else if (fontType.equals(Font.SANS_SERIF))
			return 2;
		else
			throw new AssertionError();
	}

	/**
	 * ���݂̃t�H���g�X�^�C���̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentFontStyleIndex() {
		int fontStyle = mDataHolder.getFontStyle();
		if (fontStyle == Font.PLAIN)
			return 0;
		else if (fontStyle == Font.BOLD)
			return 1;
		else if (fontStyle == Font.ITALIC)
			return 2;
		else
			throw new AssertionError();
	}

	/**
	 * ���݂̃t�H���g�T�C�Y�̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentFontSizeIndex() {
		int fontSize = mDataHolder.getFontSize();
		return fontSize / 10 - 5;
	}

	/**
	 * ���݂̕����F�̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentFontColorIndex() {
		if (mDataHolder.isRainbow())
			return 9;

		Color fontColor = mDataHolder.getFontColor();
		if (fontColor.equals(Color.WHITE))
			return 0;
		else if (fontColor.equals(Color.BLACK))
			return 1;
		else if (fontColor.equals(Color.LIGHT_GRAY))
			return 2;
		else if (fontColor.equals(Color.RED))
			return 3;
		else if (fontColor.equals(Color.BLUE))
			return 4;
		else if (fontColor.equals(Color.YELLOW))
			return 5;
		else if (fontColor.equals(Color.GREEN))
			return 6;
		else if (fontColor.equals(Color.PINK))
			return 7;
		else if (fontColor.equals(Color.CYAN))
			return 8;
		else
			throw new AssertionError();
	}

	/**
	 * ���݂̃A�i���O���v�F�̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentAnalogColorIndex() {
		Color analogColor = mDataHolder.getAnalogColor();

		if (analogColor.equals(Color.WHITE))
			return 0;
		else if (analogColor.equals(Color.BLACK))
			return 1;
		else if (analogColor.equals(Color.LIGHT_GRAY))
			return 2;
		else if (analogColor.equals(Color.RED))
			return 3;
		else if (analogColor.equals(Color.BLUE))
			return 4;
		else if (analogColor.equals(Color.YELLOW))
			return 5;
		else if (analogColor.equals(Color.GREEN))
			return 6;
		else if (analogColor.equals(Color.PINK))
			return 7;
		else if (analogColor.equals(Color.CYAN))
			return 8;
		else
			throw new AssertionError();
	}

	/**
	 * ���݂̔w�i�F�̃C���f�b�N�X��Ԃ��܂�
	 * @return
	 */
	private int currentBackgroundColorIndex() {
		Color backgroundColor = mDataHolder.getBackgroundColor();
		if (backgroundColor.equals(Color.WHITE))
			return 0;
		else if (backgroundColor.equals(Color.BLACK))
			return 1;
		else if (backgroundColor.equals(Color.LIGHT_GRAY))
			return 2;
		else if (backgroundColor.equals(Color.RED))
			return 3;
		else if (backgroundColor.equals(Color.BLUE))
			return 4;
		else if (backgroundColor.equals(Color.YELLOW))
			return 5;
		else if (backgroundColor.equals(Color.GREEN))
			return 6;
		else if (backgroundColor.equals(Color.PINK))
			return 7;
		else if (backgroundColor.equals(Color.CYAN))
			return 8;
		else
			throw new AssertionError();
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
		if (selected.equals(COLOR_WHITE))
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
}
