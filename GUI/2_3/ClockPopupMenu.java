import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ポップアップメニューに関する設定を行うクラスです
 */
public class ClockPopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 8037236915904985141L;

	// 各メニューの名前
	public static final String MENU_FONT_TYPE = "フォントタイプ";
	public static final String MENU_FONT_STYLE = "フォントスタイル";
	public static final String MENU_FONT_SIZE = "フォントサイズ";
	public static final String MENU_FONT_COLOR = "文字色";
	public static final String MENU_ANALOG_COLOR = "アナログ時計色";
	public static final String MENU_BACKGROUND_COLOR = "背景";
	public static final String MENU_OPACITY = "透過性";
	public static final String MENU_FORM = "形状";
	public static final String MENU_FINISH = "終了";

	// フォントタイプ
	public static final String FONT_MONOSPACED = "Monospaced";
	public static final String FONT_SERIF = "Serif";
	public static final String FONT_SANS_SERIF = "SansSerif";

	// フォントスタイル
	public static final String FONT_NORMAL = "標準";
	public static final String FONT_BOLD = "太字";
	public static final String FONT_ITALIC = "斜体";

	// フォントサイズ
	public static final int FONT_SIZE_MIN = 50;
	private static final int FONT_SIZE_MAX = 180;
	private static final int FONT_SIZE_INTERVAL = 10;

	// 色・画像
	public static final String COLOR_SYSTEM = "システムカラー";
	public static final String COLOR_WHITE = "白";
	public static final String COLOR_BLACK = "黒";
	public static final String COLOR_GRAY = "グレー";
	public static final String COLOR_RED = "赤";
	public static final String COLOR_BLUE = "青";
	public static final String COLOR_YELLOW = "黄";
	public static final String COLOR_GREEN = "緑";
	public static final String COLOR_ORANGE = "オレンジ";
	public static final String COLOR_PINK = "ピンク";
	public static final String COLOR_CYAN = "水色";
	public static final String COLOR_RAINBOW = "虹色";
	public static final String DEFAULT_PICTURE = "デフォルト画像";
	public static final String SELECT_PICTURE = "画像を選択";

	// 透過性
	public static final int OPACITY_MIN = 0;
	private static final int OPACITY_MAX = 90;
	private static final int OPACITY_INTERVAL = 10;

	// 形状
	public static final String FORM_RECTANGLE = "四角形";
	public static final String FORM_ROUND_RECTANGLE = "角丸四角形";

	// フィールド
	private SettingDataHolder mDataHolder;
	private Font strFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13); // 文字列のフォント

	ClockPopupMenu(SettingDataHolder holder) {
		mDataHolder = holder;

		// 各メニュー項目の追加
		add(createMenuFontType());
		add(createMenuFontStyle());
		add(createMenuFontSize());
		add(createMenuFontColor());
		add(createMenuAnalogColor());
		add(createMenuBackground());
		add(createMenuOpacity());
		add(createMenuForm());

		// 終了ボタンの設定
		JMenuItem menuFinish = new JMenuItem(MENU_FINISH);
		menuFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(menuFinish);

		// メニューリスナーの設定
		addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// Nothing to do
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// ポップアップメニュー起動時は背景を不透明にする
				// これをしないとポップアップメニューも半透明で表示されてしまう
				// ポップアップメニューを閉じるときにrestoreOpacityを呼び出す
				mDataHolder.restoreOpacity();
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				mDataHolder.clearOpacity();
			}
		});
	}

	/**
	 * フォントタイプの設定メニューを生成します。
	 */
	private JMenu createMenuFontType() {
		JMenu menu = new JMenu(MENU_FONT_TYPE);
		menu.setFont(strFont);
		menu.add(createMenuItemFontType(FONT_MONOSPACED, Font.MONOSPACED));
		menu.add(createMenuItemFontType(FONT_SERIF, Font.SERIF));
		menu.add(createMenuItemFontType(FONT_SANS_SERIF, Font.SANS_SERIF));
		return menu;
	}

	/**
	 * フォントスタイルの設定メニューを生成します。
	 */
	private JMenu createMenuFontStyle() {
		JMenu menu = new JMenu(MENU_FONT_STYLE);
		menu.setFont(strFont);
		menu.add(createMenuItemFontStyle(FONT_NORMAL, Font.PLAIN));
		menu.add(createMenuItemFontStyle(FONT_BOLD, Font.BOLD));
		menu.add(createMenuItemFontStyle(FONT_ITALIC, Font.ITALIC));
		return menu;
	}

	/**
	 * フォントサイズの設定メニューを生成します。
	 */
	private JMenu createMenuFontSize() {
		JMenu menu = new JMenu(MENU_FONT_SIZE);
		menu.setFont(strFont);
		for (int i = FONT_SIZE_MIN; i <= FONT_SIZE_MAX; i += FONT_SIZE_INTERVAL) {
			menu.add(createMenuItemFontSize(i));
		}
		return menu;
	}

	/**
	 * 文字色の設定メニューを生成します。
	 */
	private JMenu createMenuFontColor() {
		JMenu menu = new JMenu(MENU_FONT_COLOR);
		menu.setFont(strFont);
		menu.add(createMenuItemFontColor(COLOR_WHITE, Color.WHITE));
		menu.add(createMenuItemFontColor(COLOR_BLACK, Color.BLACK));
		menu.add(createMenuItemFontColor(COLOR_GRAY, Color.GRAY));
		menu.add(createMenuItemFontColor(COLOR_RED, Color.RED));
		menu.add(createMenuItemFontColor(COLOR_BLUE, Color.BLUE));
		menu.add(createMenuItemFontColor(COLOR_YELLOW, Color.YELLOW));
		menu.add(createMenuItemFontColor(COLOR_GREEN, Color.GREEN));
		menu.add(createMenuItemFontColor(COLOR_ORANGE, new Color(255, 102, 0)));
		menu.add(createMenuItemFontColor(COLOR_PINK, Color.PINK));
		menu.add(createMenuItemFontColor(COLOR_CYAN, Color.CYAN));
		menu.add(createMenuItemFontColorRainbow(COLOR_RAINBOW));
		return menu;
	}

	/**
	 * アナログ時計色の設定メニューを生成します。
	 */
	private JMenu createMenuAnalogColor() {
		JMenu menu = new JMenu(MENU_ANALOG_COLOR);
		menu.setFont(strFont);
		menu.add(createMenuItemAnalogColor(COLOR_WHITE, Color.WHITE));
		menu.add(createMenuItemAnalogColor(COLOR_BLACK, Color.BLACK));
		menu.add(createMenuItemAnalogColor(COLOR_GRAY, Color.GRAY));
		menu.add(createMenuItemAnalogColor(COLOR_RED, Color.RED));
		menu.add(createMenuItemAnalogColor(COLOR_BLUE, Color.BLUE));
		menu.add(createMenuItemAnalogColor(COLOR_YELLOW, Color.YELLOW));
		menu.add(createMenuItemAnalogColor(COLOR_GREEN, Color.GREEN));
		menu.add(createMenuItemAnalogColor(COLOR_ORANGE, new Color(255, 102, 0)));
		menu.add(createMenuItemAnalogColor(COLOR_PINK, Color.PINK));
		menu.add(createMenuItemAnalogColor(COLOR_CYAN, Color.CYAN));
		return menu;
	}

	/**
	 * 背景の設定メニューを生成します。
	 */
	private JMenu createMenuBackground() {
		JMenu menu = new JMenu(MENU_BACKGROUND_COLOR);
		menu.setFont(strFont);
		menu.add(createMenuItemBgDefaultPicture(DEFAULT_PICTURE));
		menu.add(createMenuItemBgSelectPicture(SELECT_PICTURE));
		menu.add(createMenuItemBgColor(COLOR_SYSTEM, new Color(SystemColor.desktop.getRGB())));
		menu.add(createMenuItemBgColor(COLOR_WHITE, Color.WHITE));
		menu.add(createMenuItemBgColor(COLOR_BLACK, Color.BLACK));
		menu.add(createMenuItemBgColor(COLOR_GRAY, Color.GRAY));
		menu.add(createMenuItemBgColor(COLOR_RED, Color.RED));
		menu.add(createMenuItemBgColor(COLOR_BLUE, Color.BLUE));
		menu.add(createMenuItemBgColor(COLOR_YELLOW, Color.YELLOW));
		menu.add(createMenuItemBgColor(COLOR_GREEN, Color.GREEN));
		menu.add(createMenuItemBgColor(COLOR_ORANGE, new Color(255, 102, 0)));
		menu.add(createMenuItemBgColor(COLOR_PINK, Color.PINK));
		menu.add(createMenuItemBgColor(COLOR_CYAN, Color.CYAN));
		return menu;
	}

	/**
	 * 透過性の設定メニューを生成します。
	 */
	private JMenu createMenuOpacity() {
		JMenu menu = new JMenu(MENU_OPACITY);
		menu.setFont(strFont);
		for (int i = OPACITY_MIN; i <= OPACITY_MAX; i += OPACITY_INTERVAL) {
			menu.add(createMenuItemOpacity(i));
		}
		return menu;
	}

	/**
	 * 形状の設定メニューを初期化します。
	 */
	private JMenu createMenuForm() {
		JMenu menu = new JMenu(MENU_FORM);
		menu.setFont(strFont);
		menu.add(createMenuItemForm(FORM_RECTANGLE, false));
		menu.add(createMenuItemForm(FORM_ROUND_RECTANGLE, true));
		return menu;
	}

	/**
	 * フォントタイプのメニューアイテムを生成します。
	 * @param label メニュー項目として表示する文字列
	 * @param fontType フォントタイプ
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemFontType(String label, final String fontType) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setFontType(fontType);
			}
		});
		return item;
	}

	/**
	 * フォントスタイルのメニューアイテムを生成します。
	 * @param label メニュー項目として表示する文字列
	 * @param fontStyle フォントスタイル
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemFontStyle(String label, final int fontStyle) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setFontStyle(fontStyle);
			}
		});
		return item;
	}

	/**
	 * フォントサイズのメニューアイテムを生成します。
	 * @param fontSize フォントサイズ
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemFontSize(final int fontSize) {
		JMenuItem item = new JMenuItem(String.valueOf(fontSize));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setFontSize(fontSize);
			}
		});
		return item;
	}

	/**
	 * 文字色のメニューアイテムを生成します。<br>
	 * 文字色を虹色に設定する場合は、createMenuItemFontColorRainbowメソッドを使用する。
	 * @param label メニュー項目として表示する文字列
	 * @param color 文字色
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemFontColor(String label, final Color color) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setRainbowFlg(false); // 虹色フラグを解除
				mDataHolder.setFontColor(color);
			}
		});
		return item;
	}

	/**
	 * 文字色を虹色に設定する場合のメニューアイテムを生成します。<br>
	 * 文字色を単色に設定する場合は、createMenuItemFontColorメソッドを使用する。
	 * @param label メニュー項目として表示する文字列
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemFontColorRainbow(String label) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setRainbowFlg(true);
			}
		});
		return item;
	}

	/**
	 * アナログ時計色のメニューアイテムを生成します。
	 * @param label メニュー項目として表示する文字列
	 * @param color アナログ時計色
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemAnalogColor(String label, final Color color) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setAnalogColor(color);
			}
		});
		return item;
	}

	/**
	 * 背景に単色を使用する場合のメニューアイテムを生成します。<br>
	 * 背景にデフォルト画像を使用する場合はcreateMenuItemBgDefaultPictureメソッドを使用します。<br>
	 * 背景にファイルから選択した画像を使用する場合はcreateMenuItemBgSelectPictureメソッドを使用します。
	 * @param label メニュー項目として表示する文字列
	 * @param color 背景色
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemBgColor(String label, final Color color) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setPictureFlg(false); // 画像利用フラグを解除
				mDataHolder.setBackgroundColor(color);
			}
		});
		return item;
	}

	/**
	 * 背景にデフォルト画像を使用する場合のメニューアイテムを生成します。<br>
	 * 背景に単色を使用する場合はcreateMenuItemBgColorメソッドを使用します。<br>
	 * 背景にファイルから選択した画像を使用する場合はcreateMenuItemBgSelectPictureメソッドを使用します。
	 * @param label メニュー項目として表示する文字列
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemBgDefaultPicture(String label) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setPictureFlg(true);
				mDataHolder.setDefaultPicture();
			}
		});
		return item;
	}

	/**
	 * 背景にファイルから選択した画像を使用する場合のメニューアイテムを生成します。<br>
	 * 背景に単色を使用する場合はcreateMenuItemBgColorメソッドを使用します。<br>
	 * 背景にデフォルト画像を使用する場合はcreateMenuItemBgDefaultPictureメソッドを使用します。
	 * @param label メニュー項目として表示する文字列
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemBgSelectPicture(String label) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setPictureFlg(true);
				// エクスプローラを表示しJPEG画像を選択可能にする
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(ClockPopupMenu.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ImageIcon icon;
					icon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					mDataHolder.setPicture(icon.getImage());
				}
				// 選択をキャンセルした場合はデフォルト画像を表示する
				else {
					mDataHolder.setDefaultPicture();
				}
			}
		});
		return item;
	}

	/**
	 * 透過性のメニューアイテムを生成します。
	 * @param opacity 透過率
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemOpacity(final int opacity) {
		JMenuItem item = new JMenuItem(String.valueOf(opacity));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setOpacity(1.0f - (float) opacity / 100);
			}
		});
		return item;
	}

	/**
	 * 形状のメニューアイテムを生成します。
	 * @param label メニュー項目として表示する文字列
	 * @param isRound 角丸四角形の場合はtrue, 四角形の場合はfalseを指定
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItemForm(String label, final boolean isRound) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mDataHolder.setRoundRectangle(isRound);
			}
		});
		return item;
	}
}
