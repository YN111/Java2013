package com.example.javafinal.game.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.javafinal.game.Position;
import com.example.javafinal.util.Logger;

/**
 * 画面に表示される標的を管理するクラスです。
 */
public class TargetManager {

	private static final String TAG = "TargeManager";

	/** 同時に表示できる標的の数 */
	private static final int MAX_TARGET_SIZE = 4;

	/** 表示中の標的のリスト */
	private List<Target> mTargets;

	/** 標的を消滅させた回数 */
	private Map<TargetType, Integer> mKnockedCount;

	/** 標的を生成した回数 */
	private int mCount;

	public TargetManager() {
		mTargets = Collections.synchronizedList(new ArrayList<Target>());
		mKnockedCount = new EnumMap<TargetType, Integer>(TargetType.class);
		for (TargetType type : TargetType.values()) {
			mKnockedCount.put(type, 0);
		}
	}

	/**
	 * 標的の情報を返します。
	 * @return 標的の情報
	 */
	public List<Target> getTargets() {
		return new ArrayList<Target>(mTargets);
	}

	/**
	 * 標的の情報を更新します。
	 */
	public void updateTarget() {
		deleteDammyTarget(3);
		addTarget();
		addTarget();
	}

	/**
	 * 銃が発射された時の処理を定義します。<br>
	 * @param horizonal 銃の表示位置
	 * @return 獲得した得点
	 */
	public int gunShot(Position.Horizonal horizonal) {
		Target attackedTarget = null;
		int score = 0;

		synchronized (mTargets) {
			for (Target target : mTargets) {
				// 水平位置が異なる
				if (target.getPosition().getHorizonal() != horizonal) {
					continue;
				}

				// 最も手前に表示されている標的が攻撃対象
				if (attackedTarget == null
						|| target.getPosition().getVertical().compareTo(attackedTarget.getPosition().getVertical()) > 0) {
					attackedTarget = target;
				}
			}

			if (attackedTarget != null) {
				int lifePoint = attackedTarget.attack();
				if (lifePoint == 0) {
					score = attackedTarget.getScore();
					mTargets.remove(attackedTarget);
					// 消滅させた回数を更新
					int knockedCount = mKnockedCount.get(attackedTarget.getType()) + 1;
					mKnockedCount.put(attackedTarget.getType(), knockedCount);
				}
			}
		}
		return score;
	}

	/**
	 * 標的を消滅させた回数を取得します。
	 * @param type 標的の種類
	 * @return 消滅させた回数
	 */
	public int getKnockedCount(TargetType type) {
		return mKnockedCount.get(type);
	}

	/**
	 * ダミー標的の削除を試みます。<br>
	 * 引数で指定された割合でダミー標的は削除されます。
	 * 本メソッドの呼び出し1回に対して削除されるダミー標的は最大で1つです。
	 * @param ratio 割合（ratio分の1の確率で削除される）
	 */
	private void deleteDammyTarget(int ratio) {
		Target removeTarget = null;
		synchronized (mTargets) {
			for (Target target : mTargets) {
				if (target.isDammy()) {
					int random = new Random().nextInt(ratio);
					if (random == 0) {
						removeTarget = target;
						break;
					}
				}
			}
			if (removeTarget != null) {
				mTargets.remove(removeTarget);
			}
		}
	}

	/**
	 * 標的の追加を試みます。<br>
	 * このメソッドの呼び出し1回で追加される標的は最大1つです。
	 */
	private void addTarget() {
		synchronized (mTargets) {
			if (mTargets.size() >= MAX_TARGET_SIZE) {
				// これ以上標的を追加できない
				return;
			}

			Position position = decidePosition();
			if (!exist(position)) {
				Target target = createTarget(position);
				mTargets.add(target);
				Logger.printDebugLog(TAG, "newTarget: " + target);
			}
		}
	}

	/**
	 * 標的を生成します。
	 * @return 標的
	 */
	private Target createTarget(Position position) {
		TargetType type = null;
		// 標的の種類を決める
		int num = mCount++ % 10;
		if (num % 3 == 0) {
			type = TargetType.Weak;
		} else if (num % 3 == 2) {
			type = TargetType.Dummy;
		} else if (num == 1 || num == 7) {
			type = TargetType.Normal;
		} else if (num == 4) {
			type = TargetType.Strong;
		}
		return new Target(type, position);
	}

	/**
	 * 標的の表示位置をランダムに決定します。
	 * @return 表示位置
	 */
	private Position decidePosition() {
		Random random = new Random();

		// 水平
		Position.Horizonal[] horizonals = Position.Horizonal.values();
		int rnd = Math.abs(random.nextInt());
		Position.Horizonal horizonal = horizonals[rnd % horizonals.length];

		// 垂直
		Position.Vertical[] verticals = Position.Vertical.values();
		rnd = Math.abs(random.nextInt());
		Position.Vertical vertical = verticals[rnd % verticals.length];

		return new Position(horizonal, vertical);
	}

	/**
	 * 指定された位置に標的が存在するかを返します。
	 * @param horizonal　水平位置
	 * @param vertical 垂直位置
	 */
	private boolean exist(Position position) {
		Position.Horizonal horizonal = position.getHorizonal();
		Position.Vertical vertical = position.getVertical();
		synchronized (mTargets) {
			for (Target target : mTargets) {
				if (target.getPosition().getHorizonal() == horizonal && target.getPosition().getVertical() == vertical) {
					return true;
				}
			}
		}
		return false;
	}

}
