package com.example.javafinal.game;

/**
 * 画面上の要素の配置場所について定義します。
 */
public class Position {

	/**
	 * 水平方向
	 */
	public enum Horizonal {

		/**
		 * 最も左側の列
		 */
		Left,

		/**
		 * 中央列
		 */
		Center,

		/**
		 * 最も右側の列
		 */
		Right;
	}

	/**
	 * 垂直方向
	 */
	public enum Vertical {

		/**
		 * 最も上
		 */
		Top,

		/**
		 * 上から2番目
		 */
		CenterTop,

		/**
		 * 下から2番目
		 */
		CenterBottom,

		/**
		 * 最も下
		 */
		Bottom;
	}

	/** 水平方向の配置 */
	private Horizonal mHorizonal;

	/** 垂直方向の配置 */
	private Vertical mVertical;

	/**
	 * コンストラクタ
	 * @param horizonal 水平方向の配置場所
	 * @param vertical 垂直方向の配置場所
	 */
	public Position(Horizonal horizonal, Vertical vertical) {
		mHorizonal = horizonal;
		mVertical = vertical;
	}

	/**
	 * 水平方向の配置場所を取得します。
	 * @return 水平方向の配置
	 */
	public Horizonal getHorizonal() {
		return mHorizonal;
	}

	/**
	 * 垂直方向の配置場所を取得します。
	 * @return 水平方向の配置
	 */
	public Vertical getVertical() {
		return mVertical;
	}

}
