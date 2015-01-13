package com.example.javafinal.game;

import java.util.EnumMap;
import java.util.Map;

import com.example.javafinal.R;
import com.example.javafinal.game.GameManager.GameState;
import com.example.javafinal.game.Position.Horizonal;
import com.example.javafinal.game.Position.Vertical;
import com.example.javafinal.game.target.Target;
import com.example.javafinal.game.target.TargetType;
import com.example.javafinal.util.Logger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * ゲーム用の画面を定義するクラスです。
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = "GameView";

	// =================================
	// ゲーム画面
	// =================================

	// 画面の幅に対する各オブジェクトのサイズ
	private static final float WIDTH_CHARGE_BUTTON = 0.2f;
	private static final float WIDTH_GUN = 0.2f;
	private static final float WIDTH_DIRECTION_SWITCH = 0.35f;
	private static final float WIDTH_TARGET = 0.3f;
	private static final float FONT_SIZE_TIME = 0.05f;
	private static final float FONT_SIZE_SCORE = 0.05f;

	// 画面の高さに対する各オブジェクトのサイズ
	private static final float HEIGHT_BULLET = 0.015f;

	// 各オブジェクトのマージン（画面の幅に対する比率）
	private static final float MARGIN_BOTTOM_CHARGE_BUTTON = 0.21f;
	private static final float MARGIN_RIGHT_CHARGE_BUTTON = 0.21f;
	private static final float MARGIN_BOTTOM_GUN = 0.21f;
	private static final float MARGIN_BOTTOM_DIRECTION_SWITCH = 0.15f;
	private static final float MARGIN_LEFT_DIRECTION_SWITCH = 0;
	private static final float MARGIN_BOTTOM_BULLET = 0.35f;
	private static final float MARGIN_RIGHT_BULLET = 0.1f;
	private static final float MARGIN_TOP_TIME = 0.1f;
	private static final float MARGIN_LEFT_TIME = 0.05f;
	private static final float MARGIN_TOP_SCORE = 0.17f;
	private static final float MARGIN_LEFT_SCORE = 0.05f;
	private static final float MARGIN_BOTTOM_TARGET = 0.8f;
	private static final float SPACE_HORIZONAL_TARGET = 0f; // 左右の間隔
	private static final float SPACE_VERTICAL_TARGET = 0.08f; // 上下の間隔

	// =================================
	// スコア表示画面
	// =================================

	// 画面の幅に対する各オブジェクトのサイズ
	private static final float FONT_SIZE_SCORE_VIEW = 0.1f;

	// 各オブジェクトのマージン（画面の幅に対する比率）
	private static final float MARGIN_TOP_SCORE_VIEW = 0.4f;
	private static final float MARGIN_LEFT_SCORE_VIEW = 0.2f;
	private static final float SPACE_SCORE_VIEW = 0.15f; // 要素の間隔

	// =================================
	// フィールド
	// =================================
	private GameManager mGameManager;
	private int mWidth;
	private int mHeight;
	private Thread mPaintThread;
	private SurfaceHolder mHolder;

	// 背景
	private Bitmap mBackground;

	// 銃
	private Bitmap mGun; // オブジェクト
	private float mGunX; // 表示位置X座標
	private float mGunY; // 表示位置Y座標

	// 攻撃方向変更スイッチ
	private Map<Horizonal, Bitmap> mDirSwitch; // オブジェクト
	private float mDirectionSwitchX; // 表示位置X座標
	private float mDirectionSwitchY; // 表示位置Y座標

	// チャージボタン
	private Bitmap mChargeButton; // オブジェクト
	private float mChargeButtonX; // 表示位置X座標
	private float mChargeButtonY; // 表示位置Y座標

	// 弾丸残数
	private Bitmap mBullet;
	private float mBulletX; // 表示位置X座標
	private float mBulletY; // 表示位置Y座標（一番下）

	// 標的
	private Map<TargetType, Bitmap> mTarget; // オブジェクト
	private Map<Horizonal, Float> mTargetX; // 表示位置X座標
	private Map<Vertical, Float> mTargetY; // 表示位置Y座標

	// 残り時間
	private float mTimeX; // 表示位置X座標
	private float mTimeY; // 表示位置Y座標

	// スコア
	private float mScoreX; // 表示位置X座標
	private float mScoreY; // 表示位置Y座標

	public GameView(Context context) {
		super(context);
		init();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * GameViewの初期化処理を行います。
	 */
	private void init() {
		mGameManager = new GameManager();
		mHolder = getHolder();
		mHolder.addCallback(this);

		// リソースの読み込み
		Resources res = getContext().getResources();
		mGun = BitmapFactory.decodeResource(res, R.drawable.gun);
		mBullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
		mChargeButton = BitmapFactory.decodeResource(res, R.drawable.charge);

		mDirSwitch = new EnumMap<Horizonal, Bitmap>(Horizonal.class);
		mDirSwitch.put(Horizonal.Left, BitmapFactory.decodeResource(res, R.drawable.direction_left));
		mDirSwitch.put(Horizonal.Center, BitmapFactory.decodeResource(res, R.drawable.direction_center));
		mDirSwitch.put(Horizonal.Right, BitmapFactory.decodeResource(res, R.drawable.direction_right));

		mTarget = new EnumMap<TargetType, Bitmap>(TargetType.class);
		mTarget.put(TargetType.Weak, BitmapFactory.decodeResource(res, R.drawable.target_weak));
		mTarget.put(TargetType.Normal, BitmapFactory.decodeResource(res, R.drawable.target_normal));
		mTarget.put(TargetType.Strong, BitmapFactory.decodeResource(res, R.drawable.target_strong));
		mTarget.put(TargetType.Dummy, BitmapFactory.decodeResource(res, R.drawable.target_dummy));
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Logger.printDebugLog(TAG, "surfaceChanged width[" + width + "], height[" + height + "]");
		mWidth = width;
		mHeight = height;

		// 背景画像をセンターに合わせる
		mBackground = createBackgroundBitmap(Horizonal.Center);
		updateBitmapSize(width, height);
		updateBitmapPosition(width, height);

		mGameManager.start();
		mPaintThread = new PaintThread();
		mPaintThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mPaintThread = null;
		mGameManager.forceStop();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public synchronized boolean onTouchEvent(MotionEvent event) {
		if (mGameManager.playing()) {
			float x = event.getX();
			float y = event.getY();
			if (touchGunArea(x, y)) {
				updateView(mGameManager.gunShot());
			} else if (touchChargeArea(x, y)) {
				updateView(mGameManager.chargeBullet());
			} else {
				Horizonal direction = touchDirectionSwitchArea(x, y);
				if (direction != null) {
					// 背景画像を更新する
					mBackground = createBackgroundBitmap(direction);
					updateView(mGameManager.changeGunPosition(direction));
				}
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 各オブジェクトのサイズを更新します。
	 * @param width 画面の幅
	 * @param height 画面の高さ
	 */
	private void updateBitmapSize(int width, int height) {
		// 背景：全画面表示
		mBackground = Bitmap.createScaledBitmap(mBackground, width, height, false);

		// チャージボタン：幅=高さ
		int chargeBtnSize = (int) (width * WIDTH_CHARGE_BUTTON);
		mChargeButton = Bitmap.createScaledBitmap(mChargeButton, chargeBtnSize, chargeBtnSize, false);

		// 銃：幅=高さ
		int gunSize = (int) (width * WIDTH_GUN);
		mGun = Bitmap.createScaledBitmap(mGun, gunSize, gunSize, false);

		// 銃弾：幅=高さ*2
		int bulletHeight = (int) (height * HEIGHT_BULLET);
		int bulletWidth = bulletHeight * 2;
		mBullet = Bitmap.createScaledBitmap(mBullet, bulletWidth, bulletHeight, false);

		// 攻撃方向スイッチ：幅=高さ*3
		int dirSwWidth = (int) (width * WIDTH_DIRECTION_SWITCH);
		int dirSwHeight = dirSwWidth / 3;
		Bitmap leftDirSw = Bitmap.createScaledBitmap(mDirSwitch.get(Horizonal.Left), dirSwWidth, dirSwHeight, false);
		Bitmap centDirSw = Bitmap.createScaledBitmap(mDirSwitch.get(Horizonal.Center), dirSwWidth, dirSwHeight, false);
		Bitmap rightDirSw = Bitmap.createScaledBitmap(mDirSwitch.get(Horizonal.Right), dirSwWidth, dirSwHeight, false);
		mDirSwitch.clear();
		mDirSwitch.put(Horizonal.Left, leftDirSw);
		mDirSwitch.put(Horizonal.Center, centDirSw);
		mDirSwitch.put(Horizonal.Right, rightDirSw);

		// 標的：幅=高さ/2
		int targetWidth = (int) (width * WIDTH_TARGET);
		int targetHeight = targetWidth * 2;
		Bitmap weak = Bitmap.createScaledBitmap(mTarget.get(TargetType.Weak), targetWidth, targetHeight, false);
		Bitmap normal = Bitmap.createScaledBitmap(mTarget.get(TargetType.Normal), targetWidth, targetHeight, false);
		Bitmap strong = Bitmap.createScaledBitmap(mTarget.get(TargetType.Strong), targetWidth, targetHeight, false);
		Bitmap dummy = Bitmap.createScaledBitmap(mTarget.get(TargetType.Dummy), targetWidth, targetHeight, false);
		mTarget.clear();
		mTarget.put(TargetType.Weak, weak);
		mTarget.put(TargetType.Normal, normal);
		mTarget.put(TargetType.Strong, strong);
		mTarget.put(TargetType.Dummy, dummy);
	}

	/**
	 * 各オブジェクトの表示位置を更新します。
	 * @param width 画面の幅
	 * @param height 画面の高さ
	 */
	private void updateBitmapPosition(int width, int height) {
		mGunX = (width / 2) - (mGun.getWidth() / 2);
		mGunY = height - (width * MARGIN_BOTTOM_GUN);
		mChargeButtonX = width * (1 - MARGIN_RIGHT_CHARGE_BUTTON);
		mChargeButtonY = height - (width * MARGIN_BOTTOM_CHARGE_BUTTON);
		mBulletX = width * (1 - MARGIN_RIGHT_BULLET);
		mBulletY = height - (width * MARGIN_BOTTOM_BULLET);
		mDirectionSwitchX = width * MARGIN_LEFT_DIRECTION_SWITCH;
		mDirectionSwitchY = height - (width * MARGIN_BOTTOM_DIRECTION_SWITCH);
		mTimeX = width * MARGIN_LEFT_TIME;
		mTimeY = width * MARGIN_TOP_TIME;
		mScoreX = width * MARGIN_LEFT_SCORE;
		mScoreY = width * MARGIN_TOP_SCORE;

		// 標的
		mTargetX = new EnumMap<Horizonal, Float>(Horizonal.class);
		float targetWidth = mTarget.get(TargetType.Weak).getWidth();
		float centerX = (width / 2) - (targetWidth / 2);
		float spaceX = width * SPACE_HORIZONAL_TARGET;
		mTargetX.put(Horizonal.Left, centerX - spaceX - targetWidth);
		mTargetX.put(Horizonal.Center, centerX);
		mTargetX.put(Horizonal.Right, centerX + spaceX + targetWidth);

		mTargetY = new EnumMap<Vertical, Float>(Vertical.class);
		float bottomY = height - width * MARGIN_BOTTOM_TARGET;
		float spaceY = width * SPACE_VERTICAL_TARGET;
		mTargetY.put(Vertical.Bottom, bottomY);
		mTargetY.put(Vertical.CenterBottom, bottomY - spaceY);
		mTargetY.put(Vertical.CenterTop, bottomY - spaceY * 2);
		mTargetY.put(Vertical.Top, bottomY - spaceY * 3);
	}

	/**
	 * 背景画像のBitmapオブジェクトを生成します。
	 * @param direction 攻撃方向
	 * @return 攻撃方向に対応した背景画像。引数をnullにした場合はデフォルトの背景画像
	 */
	private Bitmap createBackgroundBitmap(Horizonal direction) {
		Resources res = getContext().getResources();
		Bitmap background = null;
		if (direction == Horizonal.Left) {
			background = BitmapFactory.decodeResource(res, R.drawable.background_left);
		} else if (direction == Horizonal.Center) {
			background = BitmapFactory.decodeResource(res, R.drawable.background_center);
		} else if (direction == Horizonal.Right) {
			background = BitmapFactory.decodeResource(res, R.drawable.background_right);
		} else {
			background = BitmapFactory.decodeResource(res, R.drawable.background);
		}
		return Bitmap.createScaledBitmap(background, mWidth, mHeight, false);
	}

	/**
	 * 銃が表示されているエリアがタッチされたかを返します。
	 * @param touchX タッチされたx座標
	 * @param touchY　タッチされたy座標
	 * @return true:タッチされた、false:タッチされない
	 */
	private boolean touchGunArea(float touchX, float touchY) {
		float left = mGunX;
		float right = left + mGun.getWidth();
		float top = mGunY;
		float bottom = top + mGun.getHeight();
		return left <= touchX && touchX <= right && top <= touchY && touchY <= bottom;
	}

	/**
	 * 攻撃方向変更スイッチが押された場合、攻撃方向を返します。
	 * @param touchX タッチされたx座標
	 * @param touchY タッチされたy座標
	 * @return 攻撃方向。タッチされた座標が範囲外の場合はnull
	 */
	private Horizonal touchDirectionSwitchArea(float touchX, float touchY) {
		int width = mDirSwitch.get(Horizonal.Left).getWidth();
		float left = mDirectionSwitchX;
		float right = left + width;
		float top = mDirectionSwitchY;
		float bottom = top + mDirSwitch.get(Horizonal.Left).getHeight();
		if (top <= touchY && touchY <= bottom) {
			if (left <= touchX && touchX <= (left + width / 3)) {
				return Horizonal.Left;
			} else if ((left + width / 3) < touchX && touchX <= (left + width / 3 * 2)) {
				return Horizonal.Center;
			} else if ((left + width / 3 * 2) < touchX && touchX <= right) {
				return Horizonal.Right;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 銃弾補充マークが表示されているエリアがタッチされたかを返します。
	 * @param touchX タッチされたx座標
	 * @param touchY　タッチされたy座標
	 * @return true:タッチされた、false:タッチされない
	 */
	private boolean touchChargeArea(float touchX, float touchY) {
		float left = mChargeButtonX;
		float right = left + mChargeButton.getWidth();
		float top = mChargeButtonY;
		float bottom = top + mChargeButton.getHeight();
		return left <= touchX && touchX <= right && top <= touchY && touchY <= bottom;
	}

	/**
	 * 描画を更新します。
	 */
	private void updateView(GameState state) {
		Canvas c = mHolder.lockCanvas();
		if (c == null) {
			return;
		}
		Paint paint = new Paint();

		// 背景
		c.drawBitmap(mBackground, 0, 0, paint);

		// 標的：後方から順に描画する
		for (Vertical vertical : Vertical.values()) {
			for (Target t : state.targets) {
				Vertical posV = t.getPosition().getVertical();
				if (posV == vertical) {
					Horizonal posH = t.getPosition().getHorizonal();
					c.drawBitmap(mTarget.get(t.getType()), mTargetX.get(posH), mTargetY.get(posV), paint);
				}
			}
		}

		// ボタン類
		c.drawBitmap(mGun, mGunX, mGunY, paint);
		c.drawBitmap(mChargeButton, mChargeButtonX, mChargeButtonY, paint);
		c.drawBitmap(mDirSwitch.get(mGameManager.getGunPosition()), mDirectionSwitchX, mDirectionSwitchY, paint);

		// 銃弾の残数
		float bulletY = mBulletY;
		int bulletHeight = mBullet.getHeight();
		for (int i = 0; i < state.quantitiyBullet; i++) {
			c.drawBitmap(mBullet, mBulletX, bulletY, paint);
			bulletY -= bulletHeight;
		}

		// 残り時間
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(mWidth * FONT_SIZE_TIME);
		c.drawText("TIME:" + mGameManager.getTimeLeft() / 1000, mTimeX, mTimeY, paint);

		// スコア
		paint.setTextSize(mWidth * FONT_SIZE_SCORE);
		c.drawText("SCORE:" + mGameManager.getScore(), mScoreX, mScoreY, paint);

		mHolder.unlockCanvasAndPost(c);

		if (mGameManager.getTimeLeft() == 0) {
			showScoreView();
		}
	}

	/**
	 * スコア画面を表示します。
	 */
	private void showScoreView() {
		Canvas c = mHolder.lockCanvas();
		if (c == null) {
			return;
		}
		Paint paint = new Paint();

		// 背景
		c.drawBitmap(mBackground, 0, 0, paint);
		mBackground = createBackgroundBitmap(null); // 背景画像をデフォルトに戻す
		c.drawBitmap(mBackground, 0, 0, paint);

		float textSize = mWidth * FONT_SIZE_SCORE_VIEW;
		float drawX = mWidth * MARGIN_LEFT_SCORE_VIEW;
		float drawY = mWidth * MARGIN_TOP_SCORE_VIEW;
		paint.setAntiAlias(true);

		// スコア
		String textScore = "SCORE:" + mGameManager.getScore();
		paintTextWithEdge(textScore, c, paint, drawX, drawY, textSize, Color.RED, Color.WHITE);

		// 各オブジェクトの消滅回数
		float space = mWidth * SPACE_SCORE_VIEW;
		for (TargetType type : TargetType.values()) {
			String text = "  " + type.name() + ":" + mGameManager.getKnockedCount(type);
			drawY += space;
			paintTextWithEdge(text, c, paint, drawX, drawY, textSize, Color.BLUE, Color.WHITE);
		}

		mHolder.unlockCanvasAndPost(c);
	}

	/**
	 * 縁取りのあるテキストを描画します。
	 */
	private void paintTextWithEdge(String text, Canvas c, Paint paint, float x, float y, float textSize, int color,
			int edgeColor) {
		paint.setColor(edgeColor);
		paint.setTextSize(textSize);
		float edgeWidth = textSize / 30 + 1;
		c.drawText(text, x - edgeWidth, y, paint); // 左
		c.drawText(text, x + edgeWidth, y, paint); // 右
		c.drawText(text, x, y - edgeWidth, paint); // 上
		c.drawText(text, x, y + edgeWidth, paint); // 下
		c.drawText(text, x - edgeWidth, y + edgeWidth, paint); // 左上
		c.drawText(text, x - edgeWidth, y - edgeWidth, paint); // 左下
		c.drawText(text, x + edgeWidth, y + edgeWidth, paint); // 右上
		c.drawText(text, x + edgeWidth, y - edgeWidth, paint); // 右下
		paint.setColor(color);
		c.drawText(text, x, y, paint);
	}

	/**
	 * ゲーム用の描画を更新するスレッド。
	 */
	private class PaintThread extends Thread {
		@Override
		public synchronized void run() {
			Logger.printDebugLog(TAG, "paintThread start");
			while (mGameManager.playing()) {
				Logger.printDebugLog(TAG, "paintThread running");
				updateView(mGameManager.updateTarget());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// ゲーム終了
			if (mGameManager.getTimeLeft() == 0) {
				showScoreView(); // 「戻る」ボタン使用時はスコア画面を表示しない
			}
			Logger.printDebugLog(TAG, "paintThread end");
		}
	}

}
