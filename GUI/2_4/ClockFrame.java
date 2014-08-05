import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.*;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = -6978261079542346180L;

	private static final int DEFAULT_GUI_WIDTH = 655; // GUIの幅
	private static final int DEFAULT_GUI_HEIGHT = 520; // GUIの高さ

	private static final String PROPERTY = "プロパティ";
	private static final String RESET = "初期値に戻す";

	private static final String KEY_LOCTION_X = "key_lc_x";
	private static final String KEY_LOCTION_Y = "key_lc_y";
	private static final String KEY_FONT_TYPE = "key_fnt_tp";
	private static final String KEY_FONT_STYLE = "key_fnt_stl";
	private static final String KEY_FONT_SIZE = "key_fnt_sz";
	private static final String KEY_RAINBOW_FLAG = "key_rainbow_flg";
	private static final String KEY_FONT_COLOR = "key_fnt_clr";
	private static final String KEY_ANALOG_COLOR = "key_ana_clr";
	private static final String KEY_PICTURE_FLAG = "key_picture_flg";
	private static final String KEY_BACKGROUND_COLOR = "key_bg_clr";
	private static final String KEY_PICTURE_PATH = "key_bg_pict_path";
	private static final String KEY_DEFAULT_PICT_FLAG = "key_bg_pict_def_flg";
	private static final String KEY_FONT_TYPE_INDEX = "key_fnt_tp_idx";
	private static final String KEY_FONT_STYLE_INDEX = "key_fnt_stl_idx";
	private static final String KEY_FONT_SIZE_INDEX = "key_fnt_sz_idx";
	private static final String KEY_FONT_COLOR_INDEX = "key_fnt_clr_idx";
	private static final String KEY_ANALOG_COLOR_INDEX = "key_ana_clr_idx";
	private static final String KEY_BACKGROUND_COLOR_INDEX = "key_bg_clr_idx";

	private ClockPanel mContentPane;
	private PropertyDialog mPropertyDialog;
	private SettingDataHolder mDataHolder;
	private Preferences prefs = Preferences.userNodeForPackage(getClass());

	ClockFrame() {
		super("Clock");
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setResizable(false);

		// 終了時の処理
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowClosingAdapter());

		// 表示位置
		centerize();

		// メニューバーの初期化
		mDataHolder = new SettingDataHolder();
		mPropertyDialog = new PropertyDialog(this, mDataHolder);
		initMenu();

		// 設定値の復元
		restoreSettingValue();

		mContentPane = new ClockPanel(this, mDataHolder);
		setContentPane(mContentPane);
		setBackground(Color.BLACK);
		new TimerThread().start();
	}

	/**
	 * メニューバーの設定を行う
	 */
	private void initMenu() {
		MenuBar bar = new MenuBar();
		bar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		Menu menu = new Menu("メニュー");

		// プロパティボタンを追加
		MenuItem property = new MenuItem(PROPERTY);
		property.addActionListener(new PropertyActionListener());
		menu.add(property);
		bar.add(menu);

		// 初期化ボタンを追加
		MenuItem reset = new MenuItem(RESET);
		reset.addActionListener(new ResetActionListener());
		menu.add(reset);

		setMenuBar(bar);
	}

	/**
	 * ウィンドウを中央に表示します
	 */
	private void centerize() {
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int x = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int y = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(x, y);
	}

	/**
	 * 保存されていた値を読み出して設定します
	 */
	private void restoreSettingValue() {
		// 各属性の設定値を復元
		String fontType = prefs.get(KEY_FONT_TYPE, null);
		int fontStyle = prefs.getInt(KEY_FONT_STYLE, -1);
		int fontSize = prefs.getInt(KEY_FONT_SIZE, -1);
		boolean rainbowFlg = prefs.getBoolean(KEY_RAINBOW_FLAG, false);
		String fontColor = prefs.get(KEY_FONT_COLOR, null);
		String analogColor = prefs.get(KEY_ANALOG_COLOR, null);
		boolean pictureFlg = prefs.getBoolean(KEY_PICTURE_FLAG, true); // デフォルトは背景画像利用
		String bgColor = prefs.get(KEY_BACKGROUND_COLOR, null);
		String picturePath = prefs.get(KEY_PICTURE_PATH, null);
		boolean defaultPictureFlg = prefs.getBoolean(KEY_DEFAULT_PICT_FLAG, true);

		mDataHolder.setRainbowFlg(rainbowFlg);
		mDataHolder.setPictureFlg(pictureFlg);
		mDataHolder.setDefaultPictureFlg(defaultPictureFlg);
		if (defaultPictureFlg) {
			mDataHolder.resetPicture();
		}

		if (fontType != null) {
			mDataHolder.setFontType(fontType);
		}

		if (fontStyle > 0) {
			mDataHolder.setFontStyle(fontStyle);
		}

		if (fontSize > 0) {
			mDataHolder.setFontSize(fontSize);
		}

		if (fontColor != null) {
			mDataHolder.setFontColor(ColorUtil.convertStringToColor(fontColor));
		}

		if (analogColor != null) {
			mDataHolder.setAnalogColor(ColorUtil.convertStringToColor(analogColor));
		}

		if (bgColor != null) {
			mDataHolder.setBackgroundColor(ColorUtil.convertStringToColor(bgColor));
		}

		if (mDataHolder.isPicture() && picturePath != null) {
			if (mDataHolder.isDefaultPicture()) {
				mDataHolder.resetPicture();
			} else {
				mDataHolder.setPicturePath(picturePath);
				ImageIcon icon = new ImageIcon(picturePath);
				mDataHolder.setPicture(icon.getImage());
			}
		}

		// 表示位置を復元
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int defaultX = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int defaultY = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(prefs.getInt(KEY_LOCTION_X, defaultX), prefs.getInt(KEY_LOCTION_Y, defaultY));
	}

	/**
	 * prefsに保存されていたフォントタイプのインデックスを返します。
	 */
	int getFontTypeRestoredIndex() {
		return prefs.getInt(KEY_FONT_TYPE_INDEX, 1);
	}

	/**
	 * prefsに保存されていたフォントスタイルのインデックスを返します。
	 */
	int getFontStyleRestoredIndex() {
		return prefs.getInt(KEY_FONT_STYLE_INDEX, 0);
	}

	/**
	 * prefsに保存されていたフォントサイズのインデックスを返します。
	 */
	int getFontSizeRestoredIndex() {
		return prefs.getInt(KEY_FONT_SIZE_INDEX, 7);
	}

	/**
	 * prefsに保存されていた文字色のインデックスを返します。
	 */
	int getFontColorRestoredIndex() {
		return prefs.getInt(KEY_FONT_COLOR_INDEX, 3);
	}

	/**
	 * prefsに保存されていたアナログ時計色のインデックスを返します。
	 */
	int getAnalogColorRestoredIndex() {
		return prefs.getInt(KEY_ANALOG_COLOR_INDEX, 3);
	}

	/**
	 * prefsに保存されていた背景色のインデックスを返します。
	 */
	int getBackgroundColorRestoredIndex() {
		return prefs.getInt(KEY_BACKGROUND_COLOR_INDEX, 0);
	}

	/**
	 * 一定間隔ごとに時計の表示を更新します
	 */
	private class TimerThread extends Thread {
		@Override
		public void run() {
			while (true) {
				mContentPane.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * メニューバーのプロパティボタンが押された時の処理を定義します
	 */
	private class PropertyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mPropertyDialog.setVisible(true);
		}
	}

	/**
	 * メニューバーの初期化ボタンが押された時の処理を定義します
	 */
	private class ResetActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 表示位置の初期化
			centerize();

			// 設定値の初期化
			mDataHolder.reset();

			// ダイアログの初期化
			mPropertyDialog.setVisible(false);
			prefs.putInt(KEY_FONT_TYPE_INDEX, 1);
			prefs.putInt(KEY_FONT_STYLE_INDEX, 0);
			prefs.putInt(KEY_FONT_SIZE_INDEX, 7);
			prefs.putInt(KEY_FONT_COLOR_INDEX, 3);
			prefs.putInt(KEY_ANALOG_COLOR_INDEX, 3);
			prefs.putInt(KEY_BACKGROUND_COLOR_INDEX, 0);
			mPropertyDialog = new PropertyDialog(ClockFrame.this, mDataHolder);
		}
	}

	/**
	 * ウィンドウを閉じるときに設定値を保存する処理を定義します
	 */
	private class WindowClosingAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			// 表示位置を保存
			prefs.putInt(KEY_LOCTION_X, ClockFrame.this.getX());
			prefs.putInt(KEY_LOCTION_Y, ClockFrame.this.getY());

			// 各属性の設定値を保存
			prefs.put(KEY_FONT_TYPE, mDataHolder.getTimeFont().getName());
			prefs.putInt(KEY_FONT_STYLE, mDataHolder.getTimeFont().getStyle());
			prefs.putInt(KEY_FONT_SIZE, mDataHolder.getTimeFont().getSize());
			prefs.put(KEY_FONT_COLOR, ColorUtil.convertColorToString(mDataHolder.getFontColor()));
			prefs.putBoolean(KEY_RAINBOW_FLAG, mDataHolder.isRainbow());
			prefs.put(KEY_ANALOG_COLOR, ColorUtil.convertColorToString(mDataHolder.getAnalogColor()));
			prefs.putBoolean(KEY_PICTURE_FLAG, mDataHolder.isPicture());
			prefs.put(KEY_BACKGROUND_COLOR, ColorUtil.convertColorToString(mDataHolder.getBackgroundColor()));
			prefs.putBoolean(KEY_DEFAULT_PICT_FLAG, mDataHolder.isDefaultPicture());

			String picturePath = mDataHolder.getPicturePath();
			if (picturePath != null) {
				prefs.put(KEY_PICTURE_PATH, picturePath);
			}

			// ダイアログ上の設定項目の選択中インデックスを保存
			prefs.putInt(KEY_FONT_TYPE_INDEX, mPropertyDialog.getFontTypeSelectedIndex());
			prefs.putInt(KEY_FONT_STYLE_INDEX, mPropertyDialog.getFontStyleSelectedIndex());
			prefs.putInt(KEY_FONT_SIZE_INDEX, mPropertyDialog.getFontSizeSelectedIndex());
			prefs.putInt(KEY_FONT_COLOR_INDEX, mPropertyDialog.getFontColorSelectedIndex());
			prefs.putInt(KEY_ANALOG_COLOR_INDEX, mPropertyDialog.getAnalogColorSelectedIndex());
			prefs.putInt(KEY_BACKGROUND_COLOR_INDEX, mPropertyDialog.getBackgroundColorSelectedIndex());
		}
	}

	public static void main(String[] args) {
		new ClockFrame().setVisible(true);
	}
}