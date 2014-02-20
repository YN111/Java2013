import java.awt.*;
import java.awt.event.*;

/**
 * ダイアログに関する設定を行うクラス
 */
public class PropertyDialog extends Dialog implements ItemListener, ActionListener {

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
	private Font strFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // 文字列のフォント
	private Font btnFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15); // ボタンのフォント

	PropertyDialog(Clock owner, SettingDataHolder d) {
		super(owner);
		mDataHolder = d;
		setResizable(false); // サイズ変更不可
		setSize(500, 420);
		setTitle("Clock - プロパティ");

		// Closeイベントを受け取るリスナー
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		// 各項目の設定
		initFontTypeList();
		initFontStyleList();
		initFontSizeList();
		initFontColorList();
		initAnalogColorList();
		initBackgroundColorList();

		addLabel(0, 0, "フォント", gbl, gbc);
		addList(0, 1, fontTypeList, gbl, gbc);
		addLabel(1, 0, "スタイル", gbl, gbc);
		addList(1, 1, fontStyleList, gbl, gbc);
		addLabel(2, 0, "サイズ", gbl, gbc);
		addList(2, 1, fontSizeList, gbl, gbc);
		addLabel(0, 2, "文字色", gbl, gbc);
		addList(0, 3, fontColorList, gbl, gbc);
		addLabel(1, 2, "アナログ時計色", gbl, gbc);
		addList(1, 3, analogColorList, gbl, gbc);
		addLabel(2, 2, "背景色", gbl, gbc);
		addList(2, 3, backgroundColorList, gbl, gbc);

		// OKボタン
		Button btn = new Button("OK");
		btn.setFont(btnFont);
		btn.addActionListener(this);
		addBtn(2, 4, btn, gbl, gbc);
	}

	/**
	 * フォントタイプの設定項目の初期化
	 */
	public void initFontTypeList() {
		fontTypeList = new List();
		fontTypeList.setName(LIST_FONT_TYPE);
		fontTypeList.setFont(strFont);
		fontTypeList.add(FONT_MONOSPACED);
		fontTypeList.add(FONT_SERIF);
		fontTypeList.add(FONT_SANS_SERIF);
		fontTypeList.select(0);
		fontTypeList.addItemListener(this);
	}

	/**
	 * フォントスタイルの設定項目の初期化
	 */
	public void initFontStyleList() {
		fontStyleList = new List();
		fontStyleList.setName(LIST_FONT_STYLE);
		fontStyleList.setFont(strFont);
		fontStyleList.add(FONT_NORMAL);
		fontStyleList.add(FONT_BOLD);
		fontStyleList.add(FONT_ITALIC);
		fontStyleList.select(0);
		fontStyleList.addItemListener(this);
	}

	/**
	 * フォントサイズの設定項目の初期化
	 */
	public void initFontSizeList() {
		fontSizeList = new List();
		fontSizeList.setName(LIST_FONT_SIZE);
		fontSizeList.setFont(strFont);
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
		fontSizeList.select(10);
		fontSizeList.addItemListener(this);
	}

	/**
	 * 文字色の設定項目の初期化
	 */
	public void initFontColorList() {
		fontColorList = new List(10);
		fontColorList.setName(LIST_FONT_COLOR);
		fontColorList.setFont(strFont);
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
		fontColorList.select(4);
		fontColorList.addItemListener(this);
	}

	/**
	 * アナログ時計色の設定項目の初期化
	 */
	public void initAnalogColorList() {
		analogColorList = new List(10);
		analogColorList.setName(LIST_ANALOG_COLOR);
		analogColorList.setFont(strFont);
		analogColorList.add(COLOR_WHITE);
		analogColorList.add(COLOR_BLACK);
		analogColorList.add(COLOR_GRAY);
		analogColorList.add(COLOR_RED);
		analogColorList.add(COLOR_BLUE);
		analogColorList.add(COLOR_YELLOW);
		analogColorList.add(COLOR_GREEN);
		analogColorList.add(COLOR_PINK);
		analogColorList.add(COLOR_CYAN);
		analogColorList.select(2);
		analogColorList.addItemListener(this);
	}

	/**
	 * 背景色の設定項目の初期化
	 */
	public void initBackgroundColorList() {
		backgroundColorList = new List(10);
		backgroundColorList.setName(LIST_BACKGROUND_COLOR);
		backgroundColorList.setFont(strFont);
		backgroundColorList.add(COLOR_WHITE);
		backgroundColorList.add(COLOR_BLACK);
		backgroundColorList.add(COLOR_GRAY);
		backgroundColorList.add(COLOR_RED);
		backgroundColorList.add(COLOR_BLUE);
		backgroundColorList.add(COLOR_YELLOW);
		backgroundColorList.add(COLOR_GREEN);
		backgroundColorList.add(COLOR_PINK);
		backgroundColorList.add(COLOR_CYAN);
		backgroundColorList.select(1);
		backgroundColorList.addItemListener(this);
	}

	/**
	 * OKボタンを押したときの処理
	 */
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}

	/**
	 * アイテムがクリックされた時の処理
	 */
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

	// GridBagLayoutにラベルを追加する
	private void addLabel(int x, int y, String title, GridBagLayout gbl, GridBagConstraints gbc) {
		Label label = new Label(title);
		label.setFont(strFont);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(15, 0, 0, 0);
		gbl.setConstraints(label, gbc);
		add(label);
	}

	// GridBagLayoutにリストを追加する
	private void addList(int x, int y, List list, GridBagLayout gbl, GridBagConstraints gbc) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbl.setConstraints(list, gbc);
		add(list);
	}

	// GridBagLayoutにボタンを追加する
	private void addBtn(int x, int y, Button btn, GridBagLayout gbl, GridBagConstraints gbc) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(btn, gbc);
		add(btn);
	}

	// フォントを変更する
	private void changeFontType(String selected) {
		if (selected.equals(FONT_MONOSPACED))
			mDataHolder.setFontType(Font.MONOSPACED);
		else if (selected.equals(FONT_SERIF))
			mDataHolder.setFontType(Font.SERIF);
		else
			mDataHolder.setFontType(Font.SANS_SERIF);
	}

	// フォントスタイルを変更する
	private void changeFontStyle(String selected) {
		if (selected.equals(FONT_NORMAL))
			mDataHolder.setFontStyle(Font.PLAIN);
		else if (selected.equals(FONT_BOLD))
			mDataHolder.setFontStyle(Font.BOLD);
		else
			mDataHolder.setFontStyle(Font.ITALIC);
	}

	// 文字色を変更する
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

	// アナログ時計の色を変更する
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

	// 背景色を変更する
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
