import java.awt.Color;
import java.awt.Font;

/**
 * 時計の表示に関する設定値を保持するクラス
 */
public class SettingDataHolder {

	public static final int MENUBAR_HEIGHT = 40; // メニューバーの高さ

	// フォントに関する設定値を格納するフィールド
	private String fontType = Font.MONOSPACED;
	private int fontStyle = Font.PLAIN;
	private int timeFontSize = 150; // 時刻部分の文字サイズ
	private int dateFontSize = 50; // 日付部分の文字サイズ
	private Font timeFont = new Font(fontType, fontStyle, timeFontSize);
	private Font dateFont = new Font(fontType, fontStyle, dateFontSize);

	// 色に関する設定値を格納するフィールド
	private Color fontColor = Color.BLUE; // 文字色
	private Color analogColor = Color.LIGHT_GRAY; // アナログ時計の色
	private Color backgroundColor = Color.BLACK; // 背景色
	private boolean rainbowFlg = false; // 虹色設定の場合にtrueになる

	// 表示位置に関する設定値を格納するフィールド
	private int guiWidth; // GUIの幅
	private int guiHeight; // GUIの高さ
	private int hourX; // 時刻表示のx座標（時）
	private int hourColonX; // 時刻表示のx座標（時と分の間のコロン）
	private int minX; // 時刻表示のx座標（分）
	private int minColonX; // 時刻表示のx座標（分と秒の間のコロン）
	private int secX; // 時刻表示のx座標（秒）
	private int timeY; // 時刻表示のy座標
	private int dateX; // 日付表示のx座標
	private int dateY; // 日付表示のy座標
	private int analogX; // アナログ時計の中心X座標
	private int analogY; // アナログ時計の中心Y座標
	private int radius; // アナログ時計の半径

	// 文字の幅と高さを格納するフィールド
	private int strNumWidth; // 数字の幅
	private int strColonWidth; // コロンの幅
	private int strHeight; // 高さ

	// アナログ時計の描画サイズを格納するフィールド
	private float secLineWidth; // 秒針の幅
	private float minLineWidth; // 分針の幅
	private float hourLineWidth; // 時針の幅
	private int centerOvalRadius; // 中心に表示する円の半径
	private int outerOvalRadius; // 外側に表示する円の半径

	/**
	 * 文字の大きさに応じて表示位置を更新します
	 * @param strNumWidth 数字の幅
	 * @param strColonWidth コロンの幅
	 * @param strHeight 文字の高さ
	 * @param dateWidth 日付表示部分の幅
	 */
	public void renewViewPoint(int strNumWidth, int strColonWidth, int strHeight, int dateWidth) {
		// 文字の幅と高さの値を格納
		this.strNumWidth = strNumWidth;
		this.strColonWidth = strColonWidth;
		this.strHeight = strHeight;

		// GUIのサイズ
		guiWidth = strNumWidth * 8 + strColonWidth * 2;
		guiHeight = strHeight * 39 / 10 + MENUBAR_HEIGHT;

		// アナログ時計の表示位置
		analogX = guiWidth / 2;
		analogY = strHeight + MENUBAR_HEIGHT;
		radius = strHeight * 8 / 10;

		// デジタル時計の表示位置
		hourX = strNumWidth;
		hourColonX = strNumWidth * 3;
		minX = strNumWidth * 3 + strColonWidth;
		minColonX = strNumWidth * 5 + strColonWidth;
		secX = strNumWidth * 5 + strColonWidth * 2;
		timeY = analogY + strHeight * 2;

		// 日付の表示位置
		dateX = guiWidth / 2 - dateWidth / 2;
		dateY = timeY + strHeight * 5 / 10;

		// アナログ時計の針の幅
		secLineWidth = strHeight / 75;
		minLineWidth = secLineWidth * 2;
		hourLineWidth = secLineWidth * 3.5f;
		if (secLineWidth < 1.0f)
			secLineWidth = 1.0f;
		if (minLineWidth < 2.0f)
			minLineWidth = 2.0f;
		if (hourLineWidth < 3.0f)
			hourLineWidth = 3.0f;

		// アナログ時計の中心と外側の円の半径
		centerOvalRadius = strHeight / 9;
		outerOvalRadius = centerOvalRadius / 2;
		if (centerOvalRadius < 6)
			centerOvalRadius = 6;
		if (outerOvalRadius < 4)
			outerOvalRadius = 4;
	}

	/**
	 * フォントタイプのセッタ
	 */
	public void setFontType(String type) {
		fontType = type;
		// フォントの設定を更新
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * フォントスタイルのセッタ
	 */
	public void setFontStyle(int style) {
		fontStyle = style;
		// フォントの設定を更新
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * フォントサイズのセッタ
	 * @param size 時刻部分の文字サイズ
	 */
	public void setFontSize(int size) {
		timeFontSize = size;
		dateFontSize = size / 3;
		// フォントの設定を更新
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * 文字色のセッタ
	 */
	public void setFontColor(Color color) {
		fontColor = color;
	}

	/**
	 * 虹色フラグのセッタ
	 */
	public void setRainbowFlg(boolean flg) {
		rainbowFlg = flg;
	}

	/**
	 * アナログ時計の色のセッタ
	 */
	public void setAnalogColor(Color color) {
		analogColor = color;
	}

	/**
	 * 背景色のセッタ
	 */
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	 * フォントタイプのゲッタ
	 */
	public String getFontType() {
		return fontType;
	}

	/**
	 * フォントスタイルのゲッタ
	 */
	public int getFontStyle() {
		return fontStyle;
	}
	
	/**
	 * フォントサイズのゲッタ<br>
	 * 時刻表示部分のフォントサイズが返されます
	 */
	public int getFontSize() {
		return timeFontSize;
	}

	/**
	 * 時刻表示部分のフォントのゲッタ
	 */
	public Font getTimeFont() {
		return timeFont;
	}

	/**
	 * 日付表示部分のフォントのゲッタ
	 */
	public Font getDateFont() {
		return dateFont;
	}

	/**
	 * 文字色のゲッタ
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * 虹色フラグのゲッタ
	 */
	public boolean isRainbow() {
		return rainbowFlg;
	}

	/**
	 * アナログ時計色のゲッタ
	 */
	public Color getAnalogColor() {
		return analogColor;
	}

	/**
	 * 背景色のゲッタ
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * 数値の幅のゲッタ
	 */
	public int getStrNumWidth() {
		return strNumWidth;
	}

	/**
	 * コロンの高さのゲッタ
	 */
	public int getStrColonWidth() {
		return strColonWidth;
	}

	/**
	 * 文字の高さのゲッタ
	 */
	public int getStrHeight() {
		return strHeight;
	}

	/**
	 * GUI幅のゲッタ
	 */
	public int getGuiWidth() {
		return guiWidth;
	}

	/**
	 * GUI高さのゲッタ
	 */
	public int getGuiHeight() {
		return guiHeight;
	}

	/**
	 * アナログ時計の中心x座標のゲッタ
	 */
	public int getAnalogX() {
		return analogX;
	}

	/**
	 * アナログ時計の中心y座標のゲッタ
	 */
	public int getAnalogY() {
		return analogY;
	}

	/**
	 * アナログ時計の半径のゲッタ
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * 時刻表示のx座標のゲッタ（時）
	 */
	public int getHourX() {
		return hourX;
	}

	/**
	 * 時刻表示のx座標のゲッタ（時と分の間のコロン）
	 */
	public int getHourColonX() {
		return hourColonX;
	}

	/**
	 * 時刻表示のx座標のゲッタ（分）
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * 時刻表示のx座標のゲッタ（分と秒の間のコロン）
	 */
	public int getMinColonX() {
		return minColonX;
	}

	/**
	 * 時刻表示のx座標のゲッタ（秒）
	 */
	public int getSecX() {
		return secX;
	}

	/**
	 * 時刻表示のy座標のゲッタ
	 */
	public int getTimeY() {
		return timeY;
	}

	/**
	 * 日付表示のx座標のゲッタ
	 */
	public int getDateX() {
		return dateX;
	}

	/**
	 * 日付表示のy座標のゲッタ
	 */
	public int getDateY() {
		return dateY;
	}

	/**
	 * 秒針の幅のゲッタ
	 */
	public float getSecLineWidth() {
		return secLineWidth;
	}

	/**
	 * 分針の幅のゲッタ
	 */
	public float getMinLineWidth() {
		return minLineWidth;
	}

	/**
	 * 時針の幅のゲッタ
	 */
	public float getHourLineWidth() {
		return hourLineWidth;
	}

	/**
	 * アナログ時計の中心の円の半径ゲッタ
	 */
	public int getCenterOvalRadius() {
		return centerOvalRadius;
	}

	/**
	 * アナログ時計の外側の円の半径ゲッタ
	 */
	public int getOuterOvalRadius() {
		return outerOvalRadius;
	}
}
