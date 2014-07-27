import java.awt.*;

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
	private Color mFontColor = Color.RED; // 文字色
	private Color mAnalogColor = Color.RED; // アナログ時計の色
	private Color mBackgroundColor = Color.BLACK; // 背景色
	private boolean mRainbowFlg = false; // 虹色設定の場合にtrueになる
	private boolean mPictureFlg = true; // 背景に画像を利用する場合にtrueになる
	private Image mPicture; // 背景画像のオブジェクト
	private Image mDefaultPicture; // デフォルト背景画像のオブジェクト
	private String mPictureFilePath; // 背景画像のファイルパス
	private String mDefaultPictureFilePath; // デフォルト背景画像のファイルパス

	// 表示位置に関する設定値を格納するフィールド
	private int mGuiWidth; // GUIの幅
	private int mGuiHeight; // GUIの高さ
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
	 * コンストラクタ
	 */
	public SettingDataHolder() {
		// デフォルト背景画像の設定
		mDefaultPictureFilePath = getClass().getClassLoader().getResource("picture.jpg").getPath();
		ImageIcon icon;
		try {
			icon = new ImageIcon(mDefaultPictureFilePath);
			mDefaultPicture = icon.getImage();
		} catch (NullPointerException e) {
			// ファイルが見つからなかった
			// 背景画像なしで時計が表示される
		}
		mPicture = mDefaultPicture;
		mPictureFilePath = mDefaultPictureFilePath;
	}

	/**
	 * 設定値をすべて初期値に戻します
	 */
	public void reset() {
		mFontType = Font.SANS_SERIF;
		mFontStyle = Font.PLAIN;
		mTimeFontSize = 120; // 時刻部分の文字サイズ
		mDateFontSize = 40; // 日付部分の文字サイズ
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
		mFontColor = Color.RED; // 文字色
		mAnalogColor = Color.RED; // アナログ時計の色
		mBackgroundColor = Color.BLACK; // 背景色
		mRainbowFlg = false; // 虹色設定の場合にtrueになる
		mPictureFlg = true; // 背景に画像を利用する場合にtrueになる
		mPicture = mDefaultPicture;
	}

	/**
	 * フォントタイプのセッタ
	 */
	public void setFontType(String type) {
		mFontType = type;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * フォントスタイルのセッタ
	 */
	public void setFontStyle(int style) {
		mFontStyle = style;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * フォントサイズのセッタ
	 * @param size 時刻部分の文字サイズ
	 */
	public void setFontSize(int size) {
		mTimeFontSize = size;
		mDateFontSize = size / 3;
		mTimeFont = new Font(mFontType, mFontStyle, mTimeFontSize);
		mDateFont = new Font(mFontType, mFontStyle, mDateFontSize);
	}

	/**
	 * 文字色のセッタ
	 */
	public void setFontColor(Color color) {
		mFontColor = color;
	}

	/**
	 * 虹色フラグのセッタ
	 */
	public void setRainbowFlg(boolean flg) {
		mRainbowFlg = flg;
	}

	/**
	 * アナログ時計の色のセッタ
	 */
	public void setAnalogColor(Color color) {
		mAnalogColor = color;
	}

	/**
	 * 背景色のセッタ
	 */
	public void setBackgroundColor(Color color) {
		mBackgroundColor = color;
	}

	/**
	 * 背景画像利用フラグのセッタ
	 */
	public void setPictureFlg(boolean flg) {
		mPictureFlg = flg;
	}

	/**
	 * 背景画像のセッタ
	 */
	public void setPicture(Image picture) {
		mPicture = picture;
	}

	/**
	 * 背景画像のファイルパスのセッタ
	 */
	public void setPicturePath(String filePath) {
		mPictureFilePath = filePath;
	}

	/**
	 * 背景画像をデフォルト画像に指定
	 */
	public void resetPicture() {
		mPicture = mDefaultPicture;
		mPictureFilePath = mDefaultPictureFilePath;
	}

	/**
	 * 文字の大きさに応じて各コンポーネントの表示位置を更新します
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
	 * 時刻表示部分のフォントのゲッタ
	 */
	public Font getTimeFont() {
		return mTimeFont;
	}

	/**
	 * 日付表示部分のフォントのゲッタ
	 */
	public Font getDateFont() {
		return mDateFont;
	}

	/**
	 * 文字色のゲッタ
	 */
	public Color getFontColor() {
		return mFontColor;
	}

	/**
	 * 虹色フラグのゲッタ
	 */
	public boolean isRainbow() {
		return mRainbowFlg;
	}

	/**
	 * 背景画像利用フラグのゲッタ
	 */
	public boolean isPicture() {
		return mPictureFlg;
	}

	/**
	 * 背景画像のゲッタ
	 */
	public Image getPicture() {
		return mPicture;
	}

	/**
	 * 背景画像のファイルパスのゲッタ
	 */
	public String getPicturePath() {
		return mPictureFilePath;
	}

	/**
	 * アナログ時計色のゲッタ
	 */
	public Color getAnalogColor() {
		return mAnalogColor;
	}

	/**
	 * 背景色のゲッタ
	 */
	public Color getBackgroundColor() {
		return mBackgroundColor;
	}

	/**
	 * 文字列の幅のゲッタ
	 */
	public int getStringWidth() {
		return mStringWidth;
	}

	/**
	 * 文字の高さのゲッタ
	 */
	public int getStrHeight() {
		return mStringHeight;
	}

	/**
	 * GUI幅のゲッタ
	 */
	public int getGuiWidth() {
		return mGuiWidth;
	}

	/**
	 * GUI高さのゲッタ
	 */
	public int getGuiHeight() {
		return mGuiHeight;
	}

	/**
	 * アナログ時計の中心x座標のゲッタ
	 */
	public int getAnalogX() {
		return mAnalogX;
	}

	/**
	 * アナログ時計の中心y座標のゲッタ
	 */
	public int getAnalogY() {
		return mAnalogY;
	}

	/**
	 * アナログ時計の半径のゲッタ
	 */
	public int getRadius() {
		return mAnalogRadius;
	}

	/**
	 * 時刻表示のx座標のゲッタ
	 */
	public int getTimeX() {
		return mTimeX;
	}

	/**
	 * 時刻表示のy座標のゲッタ
	 */
	public int getTimeY() {
		return mTimeY;
	}

	/**
	 * 日付表示のx座標のゲッタ
	 */
	public int getDateX() {
		return mDateX;
	}

	/**
	 * 日付表示のy座標のゲッタ
	 */
	public int getDateY() {
		return mDateY;
	}

	/**
	 * 秒針の幅のゲッタ
	 */
	public float getSecLineWidth() {
		return mSecLineWidth;
	}

	/**
	 * 分針の幅のゲッタ
	 */
	public float getMinLineWidth() {
		return mMinLineWidth;
	}

	/**
	 * 時針の幅のゲッタ
	 */
	public float getHourLineWidth() {
		return mHourLineWidth;
	}

	/**
	 * アナログ時計の外側の円の半径ゲッタ
	 */
	public int getOuterOvalRadius() {
		return mOuterOvalRadius;
	}

	/**
	 * アナログ時計の中心の円の半径ゲッタ
	 */
	public int getCenterOvalRadius() {
		return mCenterOvalRadius;
	}

	/**
	 * アナログ時計の目盛りの円の半径ゲッタ
	 */
	public int getDivisionRadius() {
		return mDivisionsRadius;
	}
}
