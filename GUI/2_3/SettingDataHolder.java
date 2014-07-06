import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 時計の表示に関する設定値を保持するクラス
 */
public class SettingDataHolder {

	private static final int MENUBAR_HEIGHT = 40; // メニューバーの高さ

	// フォントに関する設定値を格納するフィールド
	private String mFontType = Font.SANS_SERIF;
	private int mFontStyle = Font.PLAIN;
	private int mTimeFontSize = 120; // 時刻部分の文字サイズ
	private int mDateFontSize = 40; // 日付部分の文字サイズ
	private Font mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
	private Font mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);

	// 色に関する設定値を格納するフィールド
	private Color mFontColor = new Color(255, 102, 0); // 文字色
	private Color mAnalogColor = new Color(255, 102, 0); // アナログ時計の色
	private Color mBackgroundColor = Color.BLACK; // 背景色
	private float mOpacity = 1.0f; // 背景色の不透明度
	private float mOpacityTemp; // 背景色の不透明度の一時保存用
	private boolean mRainbowFlg = false; // 虹色設定の場合にtrueになる
	private boolean mPictureFlg = true; // 背景に画像を利用する場合にtrueになる
	private Image mPicture; // 背景画像のオブジェクト
	private Image mDefaultPicture; // デフォルト背景画像のオブジェクト

	// 表示位置に関する設定値を格納するフィールド
	private int mGuiWidth; // GUIの幅
	private int mGuiHeight; // GUIの高さ
	private boolean mRoundRectangle = true; // 角丸描画をするか
	private int mTimeX; // 時刻表示のx座標
	private int mTimeY; // 時刻表示のy座標
	private int mDateX; // 日付表示のx座標
	private int mDateY; // 日付表示のy座標
	private int mAnalogX; // アナログ時計の中心X座標
	private int mAnalogY; // アナログ時計の中心Y座標
	private int mAnalogRadius; // アナログ時計の半径

	// 文字の幅と高さを格納するフィールド
	private int mStringWidth;
	private int mStringHeight;

	// アナログ時計の描画サイズを格納するフィールド
	private float mSecLineWidth; // 秒針の幅
	private float mMinLineWidth; // 分針の幅
	private float mHourLineWidth; // 時針の幅
	private int mOuterOvalRadius; // 外側の円の半径
	private int mCenterOvalRadius; // 中心に表示する円の半径
	private int mDivisionsRadius; // 目盛り用の円の半径

	/**
	 * 背景画像の初期値を設定します。
	 */
	public SettingDataHolder() {
		URL url = getClass().getClassLoader().getResource("picture.jpg");
		ImageIcon icon;
		try {
			icon = new ImageIcon(url);
			mDefaultPicture = icon.getImage();
		} catch (NullPointerException e) {
			// ファイルが見つからなかった
			// 背景画像なしで時計が表示される
		}
		mPicture = mDefaultPicture;
	}

	/**
	 * フォントタイプを設定します。
	 */
	public void setFontType(String type) {
		mFontType = type;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * フォントスタイルを設定します。
	 */
	public void setFontStyle(int style) {
		mFontStyle = style;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * フォントサイズを設定します。
	 * @param size 時刻部分の文字サイズ
	 */
	public void setFontSize(int size) {
		mTimeFontSize = size;
		mDateFontSize = size / 3;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * 文字色を設定します。
	 */
	public void setFontColor(Color color) {
		mFontColor = color;
	}

	/**
	 * 虹色フラグを設定します。
	 * @param flg 文字色を虹色に設定する場合はtrue, それ以外はfalse
	 */
	public void setRainbowFlg(boolean flg) {
		mRainbowFlg = flg;
	}

	/**
	 * アナログ時計の色を設定します。
	 */
	public void setAnalogColor(Color color) {
		mAnalogColor = color;
	}

	/**
	 * 背景色を設定します。
	 */
	public void setBackgroundColor(Color color) {
		mBackgroundColor = color;
	}

	/**
	 * 背景色の透過性を設定します。
	 */
	public void setOpacity(float opacity) {
		mOpacity = opacity;
	}

	/**
	 * 背景画像利用フラグを設定します。
	 * @param flg 背景に画像を設定する場合はtrue, それ以外はfalse
	 */
	public void setPictureFlg(boolean flg) {
		mPictureFlg = flg;
	}

	/**
	 * 背景画像を設定します。
	 */
	public void setPicture(Image picture) {
		mPicture = picture;
	}

	/**
	 * 背景画像をデフォルト画像に指定します。
	 */
	public void setDefaultPicture() {
		mPicture = mDefaultPicture;
	}

	/**
	 * 角丸四角形描画のON/OFFを設定します
	 * @param round true:ON, false:OFF
	 */
	public void setRoundRectangle(boolean flg) {
		mRoundRectangle = flg;
	}

	/**
	 * ウィンドウを不透明に設定します。<br>
	 * 透過性を戻す場合はrestoreOpacityメソッドを使用します。
	 */
	public void clearOpacity() {
		mOpacityTemp = mOpacity;
		setOpacity(1.0f);
	}

	/**
	 * ウィンドウを不透明をclearOpacityメソッドが呼ばれる前の値に戻します。
	 */
	public void restoreOpacity() {
		setOpacity(mOpacityTemp);
	}

	/**
	 * 文字の大きさに応じて各コンポーネントの表示位置を更新します。
	 * @param strWidth 文字列の幅
	 * @param strHeight 文字の高さ
	 * @param dateWidth 日付表示部分の幅
	 */
	public void updateComponentPosition(int strWidth, int strHeight, int dateWidth) {
		strHeight = (int) (mTimeFontSize * 1.25);

		// 文字の幅と高さの値を保持
		mStringWidth = strWidth;
		mStringHeight = strHeight;

		// GUIのサイズ
		mGuiWidth = (int) (strWidth * 1.4);
		mGuiHeight = (int) (strHeight * 3.2) + MENUBAR_HEIGHT;

		// アナログ時計の表示位置
		mAnalogRadius = (int) (strHeight * 0.65);
		mAnalogX = mGuiWidth / 2;
		mAnalogY = strHeight;

		// デジタル時計の表示位置
		mTimeX = (int) (strWidth * 0.2);
		mTimeY = mAnalogY + (int) (strHeight * 1.6);

		// 日付の表示位置
		mDateX = mGuiWidth / 2 - dateWidth / 2;
		mDateY = mTimeY + (int) (strHeight * 0.35);

		// アナログ時計の針の幅
		mSecLineWidth = strHeight / 75;
		mMinLineWidth = mSecLineWidth * 2;
		mHourLineWidth = mSecLineWidth * 3.5f;
		if (mSecLineWidth < 1.0f) {
			mSecLineWidth = 1.0f;
		}
		if (mMinLineWidth < 2.0f) {
			mMinLineWidth = 2.0f;
		}
		if (mHourLineWidth < 3.0f) {
			mHourLineWidth = 3.0f;
		}

		// アナログ時計の外側の円、中心、目盛りの半径
		mOuterOvalRadius = (int) (mAnalogRadius * 1.2);
		mCenterOvalRadius = strHeight / 18;
		mDivisionsRadius = mCenterOvalRadius / 2;
		if (mCenterOvalRadius < 3) {
			mCenterOvalRadius = 3;
		}
		if (mDivisionsRadius < 2) {
			mDivisionsRadius = 2;
		}
	}

	/**
	 * 時刻表示部分のフォントを取得します。
	 */
	public Font getTimeFont() {
		return mTimeFont;
	}

	/**
	 * 日付表示部分のフォントを取得します。
	 */
	public Font getDateFont() {
		return mDateFont;
	}

	/**
	 * 文字色を取得します。
	 */
	public Color getFontColor() {
		return mFontColor;
	}

	/**
	 * 虹色フラグを取得します。
	 * @return 文字色を虹色に設定している場合はtrue, それ以外はfalse
	 */
	public boolean isRainbow() {
		return mRainbowFlg;
	}

	/**
	 * 背景画像を利用するか否かを返します。
	 * @return 背景に画像を利用する場合はtrue, しない場合はfalse
	 */
	public boolean isPicture() {
		return mPictureFlg;
	}

	/**
	 * 背景画像を取得します。
	 */
	public Image getPicture() {
		return mPicture;
	}

	/**
	 * アナログ時計色を取得します。
	 */
	public Color getAnalogColor() {
		return mAnalogColor;
	}

	/**
	 * 背景色を取得します。
	 */
	public Color getBackgroundColor() {
		return mBackgroundColor;
	}

	/**
	 * 背景色の不透明度を取得します。
	 */
	public float getOpacity() {
		return mOpacity;
	}

	/**
	 * 文字列の幅を取得します。
	 */
	public int getStringWidth() {
		return mStringWidth;
	}

	/**
	 * 文字列の高さを取得します。
	 */
	public int getStrHeight() {
		return mStringHeight;
	}

	/**
	 * GUIの幅を取得します。
	 */
	public int getGuiWidth() {
		return mGuiWidth;
	}

	/**
	 * GUIの高さを取得します。
	 */
	public int getGuiHeight() {
		return mGuiHeight;
	}

	/**
	 * 角丸四角形描画のON/OFFを取得します。
	 * @return true:ON, false:OFF
	 */
	public boolean roundRectangle() {
		return mRoundRectangle;
	}

	/**
	 * 角丸四角形描画用の図形を取得します。
	 * @return GUIの高さ
	 */
	public RoundRectangle2D.Float getRoundRectangleShape() {
		return new RoundRectangle2D.Float(0, 0, mGuiWidth, mGuiHeight, mGuiWidth / 3,
				mGuiHeight / 3);
	}

	/**
	 * 四角形描画用の図形を取得します。
	 * @return GUIの高さ
	 */
	public Rectangle2D.Float getRectangleShape() {
		return new Rectangle2D.Float(0, 0, mGuiWidth, mGuiHeight);
	}

	/**
	 * アナログ時計の中心x座標を取得します。
	 */
	public int getAnalogX() {
		return mAnalogX;
	}

	/**
	 * アナログ時計の中心y座標を取得します。
	 */
	public int getAnalogY() {
		return mAnalogY;
	}

	/**
	 * アナログ時計の半径を取得します。
	 */
	public int getRadius() {
		return mAnalogRadius;
	}

	/**
	 * 時刻表示のx座標を取得します。
	 */
	public int getTimeX() {
		return mTimeX;
	}

	/**
	 * 時刻表示のy座標を取得します。
	 */
	public int getTimeY() {
		return mTimeY;
	}

	/**
	 * 日付表示のx座標を取得します。
	 */
	public int getDateX() {
		return mDateX;
	}

	/**
	 * 日付表示のy座標を取得します。
	 */
	public int getDateY() {
		return mDateY;
	}

	/**
	 * 秒針の幅を取得します。
	 */
	public float getSecLineWidth() {
		return mSecLineWidth;
	}

	/**
	 * 分針の幅を取得します。
	 */
	public float getMinLineWidth() {
		return mMinLineWidth;
	}

	/**
	 * 時針の幅を取得します。
	 */
	public float getHourLineWidth() {
		return mHourLineWidth;
	}

	/**
	 * アナログ時計の外側の円の半径を取得します。
	 */
	public int getOuterOvalRadius() {
		return mOuterOvalRadius;
	}

	/**
	 * アナログ時計の中心の円の半径を取得します。
	 */
	public int getCenterOvalRadius() {
		return mCenterOvalRadius;
	}

	/**
	 * アナログ時計の目盛りの円の半径を取得します。
	 */
	public int getDivisionRadius() {
		return mDivisionsRadius;
	}
}
