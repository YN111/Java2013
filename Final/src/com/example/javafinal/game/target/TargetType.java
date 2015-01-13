package com.example.javafinal.game.target;

public enum TargetType {

	/** 
	 * 弱：1回の攻撃で消滅。消滅させると10点獲得。
	 */
	Weak("Weak", 1, 10, false),

	/** 
	 * 普通：3回の攻撃で消滅。消滅させると50点獲得。
	 */
	Normal("Normal", 3, 50, false),

	/** 
	 * 強：5回の攻撃で消滅。消滅させると100点獲得。
	 */
	Strong("Strong", 5, 100, false),

	/** 
	 * ダミー：1回の攻撃で消滅。消滅させると50点減点。
	 */
	Dummy("Dummy", 1, -50, true);

	/** 標的のタイプ */
	final String type;

	/** ライフポイント（何回の攻撃で消滅するか） */
	final int lifePoint;

	/** 消滅させた時に加点される得点 */
	final int score;

	/** ダミー標的か */
	final boolean isDummy;

	private TargetType(String type, int lifePoint, int score, boolean isDummy) {
		this.type = type;
		this.lifePoint = lifePoint;
		this.score = score;
		this.isDummy = isDummy;
	}

}
