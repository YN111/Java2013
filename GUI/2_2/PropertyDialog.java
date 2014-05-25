import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private SettingDataHolder mDataHolder;
	private JList mFontTypeList;
	private JList mFontStyleList;
	private JList mFontSizeList;
	private JList mFontColorList;
	private JList mAnalogColorList;
	private JList mBackgroundColorList;
	private Font mStringFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // 文字列のフォント
	private Font mButtonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15); // ボタンのフォント
	private Dimension mListSize = new Dimension(120, 120);

	PropertyDialog(ClockFrame owner, SettingDataHolder holder) {
		super(owner);
		mDataHolder = holder;
		setResizable(false); // サイズ変更不可
		setSize(500, 450);
		setTitle("Clock - プロパティ");

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
		addList(0, 1, mFontTypeList, gbl, gbc);
		addLabel(1, 0, "スタイル", gbl, gbc);
		addList(1, 1, mFontStyleList, gbl, gbc);
		addLabel(2, 0, "サイズ", gbl, gbc);
		addList(2, 1, mFontSizeList, gbl, gbc);
		addLabel(0, 2, "文字色", gbl, gbc);
		addList(0, 3, mFontColorList, gbl, gbc);
		addLabel(1, 2, "アナログ時計色", gbl, gbc);
		addList(1, 3, mAnalogColorList, gbl, gbc);
		addLabel(2, 2, "背景", gbl, gbc);
		addList(2, 3, mBackgroundColorList, gbl, gbc);

		// OKボタン
		JButton btn = new JButton("OK");
		btn.setFont(mButtonFont);
		btn.addActionListener(new ButtonActionListener());
		addBtn(2, 4, btn, gbl, gbc);
	}

	/**
	 * フォントタイプの設定項目を初期化します
	 */
	private void initFontTypeList() {
		String[] items = { FONT_SERIF, FONT_SANS_SERIF, FONT_MONOSPACED };
		mFontTypeList = new JList(items);
		mFontTypeList.setSelectedIndex(1);
		mFontTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mFontTypeList.addListSelectionListener(new FontTypeListener());
	}

	/**
	 * フォントスタイルの設定項目を初期化します
	 */
	private void initFontStyleList() {
		String[] items = { FONT_NORMAL, FONT_BOLD, FONT_ITALIC };
		mFontStyleList = new JList(items);
		mFontStyleList.setSelectedIndex(0);
		mFontStyleList.addListSelectionListener(new FontStyleListener());
	}

	/**
	 * フォントサイズの設定項目を初期化します
	 */
	private void initFontSizeList() {
		String[] items = { "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150", "160",
				"170", "180", "190", "200" };
		mFontSizeList = new JList(items);
		mFontSizeList.setSelectedIndex(10);
		mFontSizeList.addListSelectionListener(new FontSizeListener());
	}

	/**
	 * 文字色の設定項目を初期化します
	 */
	private void initFontColorList() {
		String[] items = { COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED, COLOR_BLUE, COLOR_YELLOW,
				COLOR_GREEN, COLOR_PINK, COLOR_CYAN, COLOR_RAINBOW };
		mFontColorList = new JList(items);
		mFontColorList.setSelectedIndex(3);
		mFontColorList.addListSelectionListener(new FontColorListener());
	}

	/**
	 * アナログ時計色の設定項目を初期化します
	 */
	private void initAnalogColorList() {
		String[] items = { COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED, COLOR_BLUE, COLOR_YELLOW,
				COLOR_GREEN, COLOR_PINK, COLOR_CYAN };
		mAnalogColorList = new JList(items);
		mAnalogColorList.setSelectedIndex(3);
		mAnalogColorList.addListSelectionListener(new AnalogColorListener());
	}

	/**
	 * 背景色の設定項目を初期化します
	 */
	private void initBackgroundColorList() {
		String[] items = { DEFAULT_PICTURE, SELECT_PICTURE, COLOR_WHITE, COLOR_BLACK, COLOR_GRAY, COLOR_RED,
				COLOR_BLUE, COLOR_YELLOW, COLOR_GREEN, COLOR_PINK, COLOR_CYAN };
		mBackgroundColorList = new JList(items);
		mBackgroundColorList.setSelectedIndex(0);
		mBackgroundColorList.addListSelectionListener(new BackgroundColorListener());
	}

	/**
	 * 指定された文字列でJLabelを生成し、GridBagLayoutの指定された位置に追加します
	 */
	private void addLabel(int x, int y, String title, GridBagLayout gbl, GridBagConstraints gbc) {
		JLabel label = new JLabel(title);
		label.setFont(mStringFont);
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

	/**
	 * 指定されたJListをGridBagLayoutの指定された位置に追加します
	 */
	private void addList(int x, int y, JList list, GridBagLayout gbl, GridBagConstraints gbc) {
		JScrollPane sp = new JScrollPane(list);
		sp.setPreferredSize(mListSize);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbl.setConstraints(sp, gbc);
		add(sp);
	}

	/**
	 * 指定されたJButtonをGridBagLayoutの指定された位置に追加します
	 */
	private void addBtn(int x, int y, JButton btn, GridBagLayout gbl, GridBagConstraints gbc) {
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

	/**
	 * フォントタイプのリストが選択された時の処理を定義します
	 */
	private class FontTypeListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = mFontTypeList.getSelectedValue().toString();
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
	private class FontStyleListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = mFontStyleList.getSelectedValue().toString();
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
	private class FontSizeListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = mFontSizeList.getSelectedValue().toString();
			mDataHolder.setFontSize(Integer.parseInt(selected));
		}
	}

	/**
	 * 文字色のリストが選択された時の処理を定義します
	 */
	private class FontColorListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = mFontColorList.getSelectedValue().toString();
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
	private class AnalogColorListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = mAnalogColorList.getSelectedValue().toString();
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
	private class BackgroundColorListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			mDataHolder.setPictureFlg(false); // 背景画像フラグを一旦解除
			String selected = mBackgroundColorList.getSelectedValue().toString();
			if (selected.equals(DEFAULT_PICTURE)) {
				mDataHolder.setPictureFlg(true);
				mDataHolder.setDefaultPicture();
			} else if (selected.equals(SELECT_PICTURE)) {
				mDataHolder.setPictureFlg(true);
				// エクスプローラを表示しJPEG画像を選択可能にする
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(PropertyDialog.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ImageIcon icon;
					icon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					mDataHolder.setPicture(icon.getImage());
				}
				// 選択をキャンセルした場合はデフォルト画像を表示する
				else {
					mDataHolder.setDefaultPicture();
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
	private class ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}
