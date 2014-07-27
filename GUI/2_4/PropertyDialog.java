import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ダイアログに関する設定を行うクラス
 */
public class PropertyDialog extends JDialog {
	private static final long serialVersionUID = 1L;

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
	public static final String DEFAULT_PICTURE = "デフォルト画像";
	public static final String SELECT_PICTURE = "画像を選択";

	// フィールド
	private ClockFrame mOwner;
	private SettingDataHolder mDataHolder;
	private JComboBox mFontTypeList;
	private JComboBox mFontStyleList;
	private JComboBox mFontSizeList;
	private JComboBox mFontColorList;
	private JComboBox mAnalogColorList;
	private JComboBox mBackgroundColorList;
	private Font mTextFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // 文字列のフォント
	private Dimension mListSize = new Dimension(130, 25);
	private boolean mFileChooserLockFlag = false; // このフラグがtrueの間はファイル選択画面を表示しない

	// 各属性の初期値（キャンセルボタンが押されたときに戻す値）
	private String mOriginFontType;
	private int mOriginFontStyle;
	private int mOriginFontSize;
	private boolean mOriginRainbowFlag;
	private Color mOriginFontColor;
	private Color mOriginAnalogColor;
	private boolean mOriginPictureFlag;
	private Color mOriginBackgroundColor;

	// 各属性の初期値のインデックス（キャンセルボタンが押されたときに戻す値）
	private int mOriginFontTypeIndex;
	private int mOriginFontStyleIndex;
	private int mOriginFontSizeIndex;
	private int mOriginFontColorIndex;
	private int mOriginAnalogColorIndex;
	private int mOriginBackgroundColorIndex;

	PropertyDialog(ClockFrame owner, SettingDataHolder holder) {
		super(owner);
		mOwner = owner;
		mDataHolder = holder;
		setResizable(false); // サイズ変更不可
		setSize(300, 300);
		setTitle("Clock - プロパティ");

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// 各リストの初期化
		mFontTypeList = createFontTypeList();
		mFontStyleList = createFontStyleList();
		mFontSizeList = createFontSizeList();
		mFontColorList = createFontColorList();
		mAnalogColorList = createAnalogColorList();
		mBackgroundColorList = createBackgroundColorList();

		// 各要素を追加
		addLabel(0, 0, "フォント", gbl, gbc);
		addList(1, 0, mFontTypeList, gbl, gbc);
		addLabel(0, 1, "スタイル", gbl, gbc);
		addList(1, 1, mFontStyleList, gbl, gbc);
		addLabel(0, 2, "サイズ", gbl, gbc);
		addList(1, 2, mFontSizeList, gbl, gbc);
		addLabel(0, 3, "文字色", gbl, gbc);
		addList(1, 3, mFontColorList, gbl, gbc);
		addLabel(0, 4, "アナログ時計色", gbl, gbc);
		addList(1, 4, mAnalogColorList, gbl, gbc);
		addLabel(0, 5, "背景色", gbl, gbc);
		addList(1, 5, mBackgroundColorList, gbl, gbc);
		addButton(0, 6, gbl, gbc);
	}

	@Override
	public void setVisible(boolean b) {
		if (b == true) {
			// 各属性の初期値を保持する
			mOriginFontType = mDataHolder.getTimeFont().getName();
			mOriginFontStyle = mDataHolder.getTimeFont().getStyle();
			mOriginFontSize = mDataHolder.getTimeFont().getSize();
			mOriginRainbowFlag = mDataHolder.isRainbow();
			mOriginFontColor = mDataHolder.getFontColor();
			mOriginAnalogColor = mDataHolder.getAnalogColor();
			mOriginPictureFlag = mDataHolder.isPicture();
			mOriginBackgroundColor = mDataHolder.getBackgroundColor();

			// 各設定項目のインデックス初期値を保持する
			mOriginFontTypeIndex = mFontTypeList.getSelectedIndex();
			mOriginFontStyleIndex = mFontStyleList.getSelectedIndex();
			mOriginFontSizeIndex = mFontSizeList.getSelectedIndex();
			mOriginFontColorIndex = mFontColorList.getSelectedIndex();
			mOriginAnalogColorIndex = mAnalogColorList.getSelectedIndex();
			mOriginBackgroundColorIndex = mBackgroundColorList.getSelectedIndex();

			// 表示位置
			setLocation(mOwner.getX() + 20, mOwner.getY() + 20);
		}

		super.setVisible(b);
	}

	/**
	 * フォントタイプの設定項目リストを生成します
	 */
	private JComboBox createFontTypeList() {
		String[] items = { FONT_SERIF, FONT_SANS_SERIF, FONT_MONOSPACED };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(1);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new FontTypeListener());
		return cb;
	}

	/**
	 * フォントスタイルの設定項目リストを生成します
	 */
	private JComboBox createFontStyleList() {
		String[] items = { FONT_NORMAL, FONT_BOLD, FONT_ITALIC };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(0);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new FontStyleListener());
		return cb;
	}

	/**
	 * フォントサイズの設定項目リストを生成します
	 */
	private JComboBox createFontSizeList() {
		String[] items = { "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150", "160", "170", "180",
				"190", "200" };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(7);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new FontSizeListener());
		return cb;
	}

	/**
	 * 文字色の設定項目リストを生成します
	 */
	private JComboBox createFontColorList() {
		String[] items = { COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED, COLOR_BLUE, COLOR_YELLOW, COLOR_GREEN,
				COLOR_PINK, COLOR_CYAN, COLOR_RAINBOW };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(3);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new FontColorListener());
		return cb;
	}

	/**
	 * アナログ時計色の設定項目リストを生成します
	 */
	private JComboBox createAnalogColorList() {
		String[] items = { COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED, COLOR_BLUE, COLOR_YELLOW, COLOR_GREEN,
				COLOR_PINK, COLOR_CYAN };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(3);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new AnalogColorListener());
		return cb;
	}

	/**
	 * 背景色の設定項目リストを生成します
	 */
	private JComboBox createBackgroundColorList() {
		String[] items = { DEFAULT_PICTURE, SELECT_PICTURE, COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED,
				COLOR_BLUE, COLOR_YELLOW, COLOR_GREEN, COLOR_PINK, COLOR_CYAN };
		JComboBox cb = new JComboBox(items);
		cb.setSelectedIndex(0);
		cb.setPreferredSize(mListSize);
		cb.addActionListener(new BackgroundColorListener());
		return cb;
	}

	/**
	 * GridBagLayoutにラベルを追加します
	 */
	private void addLabel(int x, int y, String title, GridBagLayout gbl, GridBagConstraints gbc) {
		JLabel label = new JLabel(title);
		label.setFont(mTextFont);
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
	 * 指定されたJComboBoxをGridBagLayoutの指定された位置に追加します
	 */
	private void addList(int x, int y, JComboBox list, GridBagLayout gbl, GridBagConstraints gbc) {
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
	 * GridBagLayoutにOKボタンとキャンセルボタンを追加します
	 * @param x
	 * @param y
	 * @param gbl
	 * @param gbc
	 */
	private void addButton(int x, int y, GridBagLayout gbl, GridBagConstraints gbc) {
		Dimension btnSize = new Dimension(100, 25);

		// OKボタン生成
		JButton okBtn = new JButton("OK");
		okBtn.setFont(mTextFont);
		okBtn.setPreferredSize(btnSize);
		okBtn.addActionListener(new OKButtonListener());

		// キャンセルボタン生成
		JButton cancelBtn = new JButton("キャンセル");
		cancelBtn.setFont(mTextFont);
		cancelBtn.setPreferredSize(btnSize);
		cancelBtn.addActionListener(new CancelButtonListener());
		Panel pane = new Panel();
		pane.add(okBtn);
		pane.add(cancelBtn);

		// 追加
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbl.setConstraints(pane, gbc);
		add(pane);
	}

	/**
	 * フォントタイプのリストが選択された時の処理を定義します
	 */
	private class FontTypeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			if (selected.equals(FONT_MONOSPACED)) {
				mDataHolder.setFontType(Font.MONOSPACED);
			} else if (selected.equals(FONT_SERIF)) {
				mDataHolder.setFontType(Font.SERIF);
			} else {
				mDataHolder.setFontType(Font.SANS_SERIF);
			}
		}
	}

	/**
	 * フォントスタイルのリストが選択された時の処理を定義します
	 */
	private class FontStyleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			if (selected.equals(FONT_NORMAL)) {
				mDataHolder.setFontStyle(Font.PLAIN);
			} else if (selected.equals(FONT_BOLD)) {
				mDataHolder.setFontStyle(Font.BOLD);
			} else {
				mDataHolder.setFontStyle(Font.ITALIC);
			}
		}
	}

	/**
	 * フォントサイズのリストが選択された時の処理を定義します
	 */
	private class FontSizeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			mDataHolder.setFontSize(Integer.parseInt(selected));
		}
	}

	/**
	 * 文字色のリストが選択された時の処理を定義します
	 */
	private class FontColorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			mDataHolder.setRainbowFlg(false); // 虹色フラグを一旦解除する
			if (selected.equals(COLOR_WHITE)) {
				mDataHolder.setFontColor(Color.WHITE);
			} else if (selected.equals(COLOR_BLACK)) {
				mDataHolder.setFontColor(Color.BLACK);
			} else if (selected.equals(COLOR_GRAY)) {
				mDataHolder.setFontColor(Color.LIGHT_GRAY);
			} else if (selected.equals(COLOR_RED)) {
				mDataHolder.setFontColor(Color.RED);
			} else if (selected.equals(COLOR_BLUE)) {
				mDataHolder.setFontColor(Color.BLUE);
			} else if (selected.equals(COLOR_YELLOW)) {
				mDataHolder.setFontColor(Color.YELLOW);
			} else if (selected.equals(COLOR_GREEN)) {
				mDataHolder.setFontColor(Color.GREEN);
			} else if (selected.equals(COLOR_PINK)) {
				mDataHolder.setFontColor(Color.PINK);
			} else if (selected.equals(COLOR_CYAN)) {
				mDataHolder.setFontColor(Color.CYAN);
			} else {
				mDataHolder.setRainbowFlg(true); // 虹色
			}
		}
	}

	/**
	 * アナログ時計色のリストが選択された時の処理を定義します
	 */
	private class AnalogColorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			if (selected.equals(COLOR_WHITE)) {
				mDataHolder.setAnalogColor(Color.WHITE);
			} else if (selected.equals(COLOR_BLACK)) {
				mDataHolder.setAnalogColor(Color.BLACK);
			} else if (selected.equals(COLOR_GRAY)) {
				mDataHolder.setAnalogColor(Color.LIGHT_GRAY);
			} else if (selected.equals(COLOR_RED)) {
				mDataHolder.setAnalogColor(Color.RED);
			} else if (selected.equals(COLOR_BLUE)) {
				mDataHolder.setAnalogColor(Color.BLUE);
			} else if (selected.equals(COLOR_YELLOW)) {
				mDataHolder.setAnalogColor(Color.YELLOW);
			} else if (selected.equals(COLOR_GREEN)) {
				mDataHolder.setAnalogColor(Color.GREEN);
			} else if (selected.equals(COLOR_PINK)) {
				mDataHolder.setAnalogColor(Color.PINK);
			} else {
				mDataHolder.setAnalogColor(Color.CYAN);
			}
		}
	}

	/**
	 * 背景色のリストが選択された時の処理を定義します
	 */
	private class BackgroundColorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			mDataHolder.setPictureFlg(false); // 背景画像フラグを一旦解除
			if (selected.equals(DEFAULT_PICTURE)) {
				mDataHolder.setPictureFlg(true);
				mDataHolder.resetPicture();
			} else if (selected.equals(SELECT_PICTURE)) {
				if (!mFileChooserLockFlag) {
					mDataHolder.setPictureFlg(true);
					// エクスプローラを表示しJPEG画像を選択可能にする
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(PropertyDialog.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						ImageIcon icon;
						String filePath = chooser.getSelectedFile().getAbsolutePath();
						mDataHolder.setPicturePath(filePath);
						icon = new ImageIcon(filePath);
						mDataHolder.setPicture(icon.getImage());
					}
					// 選択をキャンセルした場合はデフォルト画像を表示する
					else {
						mDataHolder.resetPicture();
					}
				}

			} else if (selected.equals(COLOR_WHITE)) {
				mDataHolder.setBackgroundColor(Color.WHITE);
			} else if (selected.equals(COLOR_BLACK)) {
				mDataHolder.setBackgroundColor(Color.BLACK);
			} else if (selected.equals(COLOR_GRAY)) {
				mDataHolder.setBackgroundColor(Color.LIGHT_GRAY);
			} else if (selected.equals(COLOR_RED)) {
				mDataHolder.setBackgroundColor(Color.RED);
			} else if (selected.equals(COLOR_BLUE)) {
				mDataHolder.setBackgroundColor(Color.BLUE);
			} else if (selected.equals(COLOR_YELLOW)) {
				mDataHolder.setBackgroundColor(Color.YELLOW);
			} else if (selected.equals(COLOR_GREEN)) {
				mDataHolder.setBackgroundColor(Color.GREEN);
			} else if (selected.equals(COLOR_PINK)) {
				mDataHolder.setBackgroundColor(Color.PINK);
			} else {
				mDataHolder.setBackgroundColor(Color.CYAN);
			}
		}
	}

	/**
	 * OKボタンが押された時の処理を定義します
	 */
	private class OKButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	/**
	 * キャンセルボタンが押された時の処理を定義します
	 */
	private class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 各設定値を元の値に戻す
			mDataHolder.setFontType(mOriginFontType);
			mDataHolder.setFontStyle(mOriginFontStyle);
			mDataHolder.setFontSize(mOriginFontSize);
			mDataHolder.setRainbowFlg(mOriginRainbowFlag);
			mDataHolder.setFontColor(mOriginFontColor);
			mDataHolder.setAnalogColor(mOriginAnalogColor);
			mDataHolder.setPictureFlg(mOriginPictureFlag);
			mDataHolder.setBackgroundColor(mOriginBackgroundColor);

			// 選択中インデックスを元の値に戻す
			mFontTypeList.setSelectedIndex(mOriginFontTypeIndex);
			mFontStyleList.setSelectedIndex(mOriginFontStyleIndex);
			mFontSizeList.setSelectedIndex(mOriginFontSizeIndex);
			mFontColorList.setSelectedIndex(mOriginFontColorIndex);
			mAnalogColorList.setSelectedIndex(mOriginAnalogColorIndex);
			mBackgroundColorList.setSelectedIndex(mOriginBackgroundColorIndex);

			setVisible(false);
		}
	}

	/**
	 * フォントタイプの選択中インデックスのセッタ
	 */
	public void setFontTypeSelectedIndex(int index) {
		mFontTypeList.setSelectedIndex(index);
	}

	/**
	 * フォントスタイルの選択中インデックスのセッタ
	 */
	public void setFontStyleSelectedIndex(int index) {
		mFontStyleList.setSelectedIndex(index);
	}

	/**
	 * フォントサイズの選択中インデックスのセッタ
	 */
	public void setFontSizeSelectedIndex(int index) {
		mFontSizeList.setSelectedIndex(index);
	}

	/**
	 * 文字色の選択中インデックスのセッタ
	 */
	public void setFontColorSelectedIndex(int index) {
		mFontColorList.setSelectedIndex(index);
	}

	/**
	 * アナログ時計色の選択中インデックスのセッタ
	 */
	public void setAnalogColorSelectedIndex(int index) {
		mAnalogColorList.setSelectedIndex(index);
	}

	/**
	 * 背景色の選択中インデックスのセッタ
	 */
	public void setBackgroundColorSelectedIndex(int index) {
		// このメソッドはPreferenceに保持されていた値を復元するときに呼ばれる
		// 画像ファイルのパスもPreferenceに保持しているため、再選択は不要
		mFileChooserLockFlag = true;
		mBackgroundColorList.setSelectedIndex(index);
		mFileChooserLockFlag = false;
	}

	/**
	 * フォントタイプの選択中インデックスのゲッタ
	 */
	public int getFontTypeSelectedIndex() {
		return mFontTypeList.getSelectedIndex();
	}

	/**
	 * フォントスタイルの選択中インデックスのゲッタ
	 */
	public int getFontStyleSelectedIndex() {
		return mFontStyleList.getSelectedIndex();
	}

	/**
	 * フォントサイズの選択中インデックスのゲッタ
	 */
	public int getFontSizeSelectedIndex() {
		return mFontSizeList.getSelectedIndex();
	}

	/**
	 * 文字色の選択中インデックスのゲッタ
	 */
	public int getFontColorSelectedIndex() {
		return mFontColorList.getSelectedIndex();
	}

	/**
	 * アナログ時計色の選択中インデックスのゲッタ
	 */
	public int getAnalogColorSelectedIndex() {
		return mAnalogColorList.getSelectedIndex();
	}

	/**
	 * 背景色の選択中インデックスのゲッタ
	 */
	public int getBackgroundColorSelectedIndex() {
		return mBackgroundColorList.getSelectedIndex();
	}
}
