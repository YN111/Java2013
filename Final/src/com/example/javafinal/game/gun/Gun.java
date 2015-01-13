package com.example.javafinal.game.gun;

import com.example.javafinal.game.Position;

/**
 * 銃の情報を定義します。
 */
public class Gun {

	/** 銃弾の最大数 */
	private static final int MAX_BULLET_COUNT = 20;

	/** 銃弾の残数 */
	private int mQuantity = MAX_BULLET_COUNT;

	/** 銃の配置 */
	private Position.Horizonal mPosition = Position.Horizonal.Center;

	/**
	 * 銃弾を発射可能かを返します。
	 * @return true:発射可能、false:発射不可
	 */
	public boolean enable() {
		return mQuantity > 0;
	}

	/**
	 * 銃弾の残数を返します。
	 * @return 銃弾の残数
	 */
	public int getQuantitiyBullet() {
		return mQuantity;
	}

	/**
	 * 弾をチャージします。
	 * @return 銃弾の残数
	 */
	public int charge() {
		mQuantity = MAX_BULLET_COUNT;
		return mQuantity;
	}

	/**
	 * 弾を発射します。
	 * @return 銃弾の残数
	 */
	public int shot() {
		return mQuantity > 0 ? --mQuantity : 0;
	}

	/**
	 * 銃の配置情報を取得します。
	 * @return 銃の配置情報
	 */
	public Position.Horizonal getPosition() {
		return mPosition;
	}

	/**
	 * 銃の配置を変更します。
	 * @param pos
	 * @return
	 */
	public void changePosition(Position.Horizonal pos) {
		mPosition = pos;
	}

	@Override
	public String toString() {
		return "bullet[" + mQuantity + "] position[" + mPosition.ordinal() + "]";
	}
}
