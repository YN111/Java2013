import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * 時計の表示に関する設定値を保持するクラスです
 */
public class SettingDataHolder {

	public static final int MENUBAR_HEIGHT = 40; // メニューバーの高さ

	// フォントに関する設定値を格納するフィールド
	private String fontType = Font.MONOSPACED;
	private int fontStyle = Font.PLAIN;
	private int timeFontSize = 120; // 時刻部分の文字サイズ
	private int dateFontSize = 40; // 日付部分の文字サイズ
	private Font timeFont = new Font(fontType, fontStyle, timeFontSize);
	private Font dateFont = new Font(fontType, fontStyle, dateFontSize);

	// 色に関する設定値を格納するフィールド
	private Color fontColor = Color.BLUE; // 文字色
	private Color analogColor = Color.LIGHT_GRAY; // アナログ時計の色
	private Color backgroundColor = Color.BLACK; // 背景色
	private float opacity = 0.9f; // 背景色の不透明度
	private boolean rainbowFlg = false; // 虹色設定の場合にtrueになる

	// 表示位置に関する設定値を格納するフィールド
	private int guiWidth; // GUIの幅
	private int guiHeight; // GUIの高さ
	private boolean roundRectangle = true; // 角丸描画をするか
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
	 * フォントタイプを設定します
	 * @param type フォントタイプ
	 */
	public void setFontType(String type) {
		fontType = type;
		// フォントの設定を更新
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * フォントスタイルを設定します
	 * @param style フォントスタイル
	 */
	public void setFontStyle(int style) {
		fontStyle = style;
		// フォントの設定を更新
		timeFont = new Font(fontType, fontStyle, timeFontSize);
		dateFont = new Font(fontType, fontStyle, dateFontSize);
	}

	/**
	 * フォントサイズを設定します
	 * 引数に時刻部分の文字サイズを設定することで、日付部分のサイズも更新します
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
	 * 文字色を設定します
	 * @param color 文字色
	 */
	public void setFontColor(Color color) {
		fontColor = color;
	}

	/**
	 * 虹色フラグを設定します
	 * @param flg 虹色フラグ
	 */
	public void setRainbowFlg(boolean flg) {
		rainbowFlg = flg;
	}

	/**
	 * アナログ時計の色を設定します
	 * @param color アナログ時計色
	 */
	public void setAnalogColor(Color color) {
		analogColor = color;
	}

	/**
	 * 背景色を設定します
	 * @param color 背景色
	 */
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	 * 背景色の不透明度を設定します
	 * @param opacity 背景の透過性
	 */
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	/**
	 * 角丸描画のON/OFFを設定します
	 * @param b true:角丸, false:四角形
	 */
	public void setRoundRectangle(boolean b) {
		roundRectangle = b;
	}

	/**
	 * 文字の大きさに応じて各アイテムの表示設定を更新します
	 * @param strNumWidth 数字の幅
	 * @param strColonWidth コロンの幅
	 * @param strHeight 文字の高さ
	 * @param dateWidth 日付表示部分の幅
	 */
	public void updateItemPosition(int strNumWidth, int strColonWidth, int strHeight, int dateWidth) {
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
	 * 時刻表示部分のフォントを取得します
	 * @return 時刻表示部分のフォント
	 */
	public Font getTimeFont() {
		return timeFont;
	}

	/**
	 * 日付表示部分のフォントを取得します
	 * @return 日付表示部分のフォント
	 */
	public Font getDateFont() {
		return dateFont;
	}

	/**
	 * 文字色を取得します
	 * @return 文字色
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * 虹色フラグを取得します
	 * @return 虹色フラグ
	 */
	public boolean isRainbow() {
		return rainbowFlg;
	}

	/**
	 * アナログ時計色を取得します
	 * @return アナログ時計色
	 */
	public Color getAnalogColor() {
		return analogColor;
	}

	/**
	 * 背景色を取得します
	 * @return 背景色
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * 背景色の不透明度を取得します
	 * @return 背景の透過性
	 */
	public float getOpacity() {
		return opacity;
	}

	/**
	 * 数値の幅を取得します
	 * @return 数値の幅
	 */
	public int getStrNumWidth() {
		return strNumWidth;
	}

	/**
	 * コロンの幅を取得します
	 * @return コロンの幅
	 */
	public int getStrColonWidth() {
		return strColonWidth;
	}

	/**
	 * 文字の高さを取得します
	 * @return 文字の高さ
	 */
	public int getStrHeight() {
		return strHeight;
	}

	/**
	 * GUI幅を取得します
	 * @return GUIの幅
	 */
	public int getGuiWidth() {
		return guiWidth;
	}

	/**
	 * GUI高さを取得します
	 * @return GUIの高さ
	 */
	public int getGuiHeight() {
		return guiHeight;
	}

	/**
	 * 角丸描画のON/OFFを取得します
	 * @return true:ON, false:OFF
	 */
	public boolean roundRectangle() {
		return roundRectangle;
	}

	/**
	 * 角丸四角形描画用の図形を返します
	 * @return GUIの高さ
	 */
	public RoundRectangle2D.Float getRoundRectangleShape() {
		return new RoundRectangle2D.Float(0, 0, guiWidth, guiHeight, guiWidth / 3, guiHeight / 3);
	}

	/**
	 * 四角形描画用の図形を返します
	 * @return GUIの高さ
	 */
	public Rectangle2D.Float getRectangleShape() {
		return new Rectangle2D.Float(0, 0, guiWidth, guiHeight);
	}

	/**
	 * アナログ時計の中心x座標を取得します
	 * @return アナログ時計の中心x座標
	 */
	public int getAnalogX() {
		return analogX;
	}

	/**
	 * アナログ時計の中心y座標を取得します
	 * @return アナログ時計の中心y座標
	 */
	public int getAnalogY() {
		return analogY;
	}

	/**
	 * アナログ時計の半径を取得します
	 * @return アナログ時計の半径
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * 時刻表示のx座標のを取得します（時）
	 * @return 時刻表示のx座標（時）
	 */
	public int getHourX() {
		return hourX;
	}

	/**
	 * 時刻表示のx座標を取得します（時と分の間のコロン）
	 * @return 時刻表示のx座標（時と分の間のコロン）
	 */
	public int getHourColonX() {
		return hourColonX;
	}

	/**
	 * 時刻表示のx座標を取得します（分）
	 * @return 時刻表示のx座標（分）
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * 時刻表示のx座標を取得します（分と秒の間のコロン）
	 * @return 時刻表示のx座標（分と秒の間のコロン）
	 */
	public int getMinColonX() {
		return minColonX;
	}

	/**
	 * 時刻表示のx座標を取得します（秒）
	 * @return 時刻表示のx座標（秒）
	 */
	public int getSecX() {
		return secX;
	}

	/**
	 * 時刻表示のy座標を取得します
	 * @return 時刻表示のy座標
	 */
	public int getTimeY() {
		return timeY;
	}

	/**
	 * 日付表示のx座標を取得します
	 * @return 日付表示のx座標
	 */
	public int getDateX() {
		return dateX;
	}

	/**
	 * 日付表示のy座標を取得します
	 * @return 日付表示のy座標
	 */
	public int getDateY() {
		return dateY;
	}

	/**
	 * 秒針の幅を取得します
	 * @return 秒針の幅
	 */
	public float getSecLineWidth() {
		return secLineWidth;
	}

	/**
	 * 分針の幅を取得します
	 * @return 分針の幅
	 */
	public float getMinLineWidth() {
		return minLineWidth;
	}

	/**
	 * 時針の幅を取得します
	 * @return 時針の幅
	 */
	public float getHourLineWidth() {
		return hourLineWidth;
	}

	/**
	 * アナログ時計の中心の円の半径を取得します
	 * @return アナログ時計の中心の円の半径
	 */
	public int getCenterOvalRadius() {
		return centerOvalRadius;
	}

	/**
	 * アナログ時計の外側の円の半径を取得します
	 * @return アナログ時計の外側の円の半径
	 */
	public int getOuterOvalRadius() {
		return outerOvalRadius;
	}
}
