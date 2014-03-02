import java.awt.*;
import java.awt.event.*;

/**
 * ダイアログに関する設定を行うクラス
 */
public class PropertyDialog extends Dialog implements ItemListener, ActionListener {

	private static final long serialVersionUID = 9200980135849309407L;

	// 各リストの名前
	public static final String LIST_FONT_TYPE = "FontType";
	public static final String LIST_FONT_STYLE = "FontStyle";
	public static final String LIST_FONT_SIZE = "FontSize";
	public static final String LIST_FONT_COLOR = "FontColor";
	public static final String LIST_ANALOG_COLOR = "AnalogColor";
	public static final String LIST_BACKGROUND_COLOR = "BackgroundColor";

	// フォントタイプ
	public static final String FONT_MONOSPACED = "Monospaced";
	public static final String FONT_SERIF = "Serif";
	public static final String FONT_SANS_SERIF = "SansSerif";

	// フォントスタイル
	public static final String FONT_NORMAL = "標準";
	public static final String FONT_BOLD = "太字";
	public static final String FONT_ITALIC = "斜体";

	// 色
	public static final String COLOR_WHITE = "白";
	public static final String COLOR_BLACK = "黒";
	public static final String COLOR_GRAY = "グレー";
	public static final String COLOR_RED = "赤";
	public static final String COLOR_BLUE = "青";
	public static final String COLOR_YELLOW = "黄";
	public static final String COLOR_GREEN = "緑";
	public static final String COLOR_PINK = "ピンク";
	public static final String COLOR_CYAN = "水色";
	public static final String COLOR_RAINBOW = "虹色";

	// フィールド
	private SettingDataHolder mDataHolder;
	private List fontTypeList;
	private List fontStyleList;
	private List fontSizeList;
	private List fontColorList;
	private List analogColorList;
	private List backgroundColorList;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // 文字列のフォント

	// 各属性の初期値
	private String initialFontType;
	private int initialFontStyle;
	private int initialFontSize;
	private boolean initialRainbowFlag;
	private Color initialFontColor;
	private Color initialAnalogColor;
	private Color initialBackgroundColor;

	/**
	 * コンストラクタ<br>
	 * ダイアログの項目を設定し、表示します
	 * @param owner
	 * @param d
	 */
	PropertyDialog(Clock owner, SettingDataHolder d) {
		super(owner);
		mDataHolder = d;
		setResizable(false); // サイズ変更不可
		setSize(300, 300);
		setLocation(owner.getX() + 20, owner.getY() + 20);
		setTitle("Clock - プロパティ");

		// Closeイベントを受け取るリスナー
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		// 各項目の初期値を保持
		initValue();

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 0, 0);

		// 各項目の設定
		initFontTypeList();
		initFontStyleList();
		initFontSizeList();
		initFontColorList();
		initAnalogColorList();
		initBackgroundColorList();

		// 各要素を追加
		addLabel(0, 0, "フォント", gbl, gbc);
		addList(1, 0, fontTypeList, gbl, gbc);
		addLabel(0, 1, "スタイル", gbl, gbc);
		addList(1, 1, fontStyleList, gbl, gbc);
		addLabel(0, 2, "サイズ", gbl, gbc);
		addList(1, 2, fontSizeList, gbl, gbc);
		addLabel(0, 3, "文字色", gbl, gbc);
		addList(1, 3, fontColorList, gbl, gbc);
		addLabel(0, 4, "アナログ時計色", gbl, gbc);
		addList(1, 4, analogColorList, gbl, gbc);
		addLabel(0, 5, "背景色", gbl, gbc);
		addList(1, 5, backgroundColorList, gbl, gbc);
		addBtn(0, 6, gbl, gbc);

		setVisible(true);
	}

	/**
	 * ボタンを押したときの処理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {
			// OKボタン
			setVisible(false);
		} else {
			// キャンセルボタン：全て初期値に戻す
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
	 * アイテムが選択された時の処理
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// フォントタイプの変更
		if (((List) e.getSource()).getName().equals(LIST_FONT_TYPE)) {
			String selected = fontTypeList.getSelectedItem();
			changeFontType(selected);
		}

		// フォントスタイルの変更
		if (((List) e.getSource()).getName().equals(LIST_FONT_STYLE)) {
			String selected = fontStyleList.getSelectedItem();
			changeFontStyle(selected);
		}

		// フォントサイズの変更
		if (((List) e.getSource()).getName().equals(LIST_FONT_SIZE)) {
			String selected = fontSizeList.getSelectedItem();
			mDataHolder.setFontSize(Integer.parseInt(selected));
		}

		// 文字色の変更
		if (((List) e.getSource()).getName().equals(LIST_FONT_COLOR)) {
			String selected = fontColorList.getSelectedItem();
			changeFontColor(selected);
		}

		// アナログ時計色の変更
		if (((List) e.getSource()).getName().equals(LIST_ANALOG_COLOR)) {
			String selected = analogColorList.getSelectedItem();
			changeAnalogColor(selected);
		}

		// 背景色の変更
		if (((List) e.getSource()).getName().equals(LIST_BACKGROUND_COLOR)) {
			String selected = backgroundColorList.getSelectedItem();
			changeBackgroundColor(selected);
		}
	}

	/**
	 * 各項目の初期値を保持します
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
	 * フォントタイプの設定項目を初期化します
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
	 * フォントスタイルの設定項目をの初期化します
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
	 * フォントサイズの設定項目の初期化
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
	 * 文字色の設定項目の初期化
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
	 * アナログ時計色の設定項目の初期化
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
	 * 背景色の設定項目の初期化
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
	 * GridBagLayoutにラベルを追加します
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
	 * GridBagLayoutにリストを追加します
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
	 * GridBagLayoutにボタンを追加します
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
		Button cancelBtn = new Button("キャンセル");
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
	 * 現在のフォントタイプのインデックスを返します
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
	 * 現在のフォントスタイルのインデックスを返します
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
	 * 現在のフォントサイズのインデックスを返します
	 * @return
	 */
	private int currentFontSizeIndex() {
		int fontSize = mDataHolder.getFontSize();
		return fontSize / 10 - 5;
	}

	/**
	 * 現在の文字色のインデックスを返します
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
	 * 現在のアナログ時計色のインデックスを返します
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
	 * 現在の背景色のインデックスを返します
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
	 * フォントを変更します
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
	 * フォントスタイルを変更します
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
	 * 文字色を変更します
	 * @param selected
	 */
	private void changeFontColor(String selected) {
		mDataHolder.setRainbowFlg(false); // 虹色フラグを一旦解除する
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
			mDataHolder.setRainbowFlg(true); // 虹色
	}

	/**
	 * アナログ時計の色を変更します
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
	 * 背景色を変更します
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
