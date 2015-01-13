package com.example.javafinal.game;

import java.util.List;

import com.example.javafinal.game.Position.Horizonal;
import com.example.javafinal.game.gun.Gun;
import com.example.javafinal.game.target.Target;
import com.example.javafinal.game.target.TargetManager;
import com.example.javafinal.game.target.TargetType;
import com.example.javafinal.util.Logger;

/**
 * ゲームの状態を管理するクラスです。
 */
public class GameManager {

	private static final String TAG = "GameManager";
	private static final int PLAY_TIME = 40 * 1000;

	private Gun mGun;
	private TargetManager mTargetManager;
	private int mScore = 0;
	private int mTimeLeft = PLAY_TIME;
	private boolean mPlaying;

	/**
	 * ゲーム開始前の場合に、ゲームを開始します。
	 */
	public void start() {
		// フィールドの初期化
		mGun = new Gun();
		mTargetManager = new TargetManager();
		mScore = 0;
		mTimeLeft = PLAY_TIME;
		mPlaying = true;

		// 時間計測スレッド起動
		new Thread(new Runnable() {
			@Override
			public void run() {
				Logger.printDebugLog(TAG, "start");
				final int sleep = 1000;
				while (mPlaying) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mTimeLeft -= sleep;
					Logger.printDebugLog(TAG, "time:" + mTimeLeft);
					if (mTimeLeft <= 0) {
						mPlaying = false;
						Logger.printDebugLog(TAG, "finish");
					}
				}
			}
		}).start();
	}

	/**
	 * ゲームを強制終了します。
	 */
	public void forceStop() {
		mPlaying = false;
	}

	/**
	 * プレイ中かどうかを返します。
	 * @return true:プレイ中、false:プレイ中でない
	 */
	public boolean playing() {
		return mPlaying;
	}

	/**
	 * 標的の情報を更新します。
	 * @return ゲームの状態
	 */
	public synchronized GameState updateTarget() {
		mTargetManager.updateTarget();
		return new GameState(mGun, mTargetManager);
	}

	/**
	 * 銃弾をチャージします。
	 * @return ゲームの状態
	 */
	public GameState chargeBullet() {
		Logger.printDebugLog(TAG, "chargeBullet");
		mGun.charge();
		return new GameState(mGun, mTargetManager);
	}

	/**
	 * 銃が発射された時の処理を定義します。
	 * @return ゲームの状態
	 */
	public GameState gunShot() {
		Logger.printDebugLog(TAG, "gunShot");
		mGun.shot();
		if (mGun.getQuantitiyBullet() > 0) {
			mScore += mTargetManager.gunShot(mGun.getPosition());
		}
		return new GameState(mGun, mTargetManager);
	}

	/**
	 * 銃の攻撃位置を更新します。
	 * @return 銃の表示位置
	 */
	public GameState changeGunPosition(Horizonal pos) {
		mGun.changePosition(pos);
		return new GameState(mGun, mTargetManager);
	}

	/**
	 * 銃の攻撃位置を取得します。
	 * @return 銃の表示位置
	 */
	public Horizonal getGunPosition() {
		return mGun.getPosition();
	}

	/**
	 * 得点を取得します。
	 * @return 得点
	 */
	public int getScore() {
		return mScore;
	}

	/**
	 * 残りのプレイ時間を返します。
	 * @return 残り時間（ミリ秒）
	 */
	public int getTimeLeft() {
		return mTimeLeft;
	}

	/**
	 * 標的を消滅させた回数を返します。
	 * @param type 標的の種類
	 * @return 消滅させた回数
	 */
	public int getKnockedCount(TargetType type) {
		return mTargetManager.getKnockedCount(type);
	}

	/**
	 * ゲームの状態について定義するクラスです。
	 */
	static class GameState {

		final int quantitiyBullet;
		final Position.Horizonal gunPosition;
		final List<Target> targets;

		GameState(Gun gun, TargetManager targetManager) {
			quantitiyBullet = gun.getQuantitiyBullet();
			gunPosition = gun.getPosition();
			targets = targetManager.getTargets();
		}
	}

}
