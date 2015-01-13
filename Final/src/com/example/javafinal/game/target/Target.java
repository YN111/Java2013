package com.example.javafinal.game.target;

import com.example.javafinal.game.Position;

/**
 * 画面に表示される標的について定義するクラスです。
 */
public class Target {

	/** 標的の種類 */
	private final TargetType mType;

	/** 標的の配置 */
	private final Position mPosition;

	/** 標的の残りのライフポイント */
	private int mLifePointLeft;

	/**
	 * コンストラクタ
	 * @param lifePoint ライフポイント（消滅までの攻撃数）
	 * @param score 消滅させると獲得する得点
	 */
	public Target(TargetType type, Position position) {
		mType = type;
		mPosition = position;
		mLifePointLeft = type.lifePoint;
	}

	/**
	 * 標的の種類を取得します。
	 * @return 標的の位置
	 */
	public TargetType getType() {
		return mType;
	}

	/**
	 * 標的の位置を取得します。
	 * @return 標的の位置
	 */
	public Position getPosition() {
		return mPosition;
	}

	/**
	 * 標的を消滅させたときに得られる得点を返します。
	 * @return 得点
	 */
	public int getScore() {
		return mType.score;
	}

	/**
	 * 現在のライフポイントを返します。
	 * @return ライフポイント
	 */
	public int getLifePoint() {
		return mLifePointLeft;
	}

	/**
	 * 標的を攻撃します。標的のライフポイントが1減少します。
	 * @return 標的の残りのライフポイント
	 */
	public int attack() {
		return mLifePointLeft > 0 ? --mLifePointLeft : 0;
	}

	/**
	 * ダミー標的の場合はtrueを返します。
	 * @return true:ダミー、false:ダミーではない
	 */
	public boolean isDammy() {
		return mType.isDummy;
	}

	@Override
	public String toString() {
		return "type[" + mType + "] horizonal[" + mPosition.getHorizonal() + "] vertical[" + mPosition.getVertical()
				+ "] life[" + mLifePointLeft + "]";
	}

}
