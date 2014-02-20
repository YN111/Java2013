/*
 * アクセッサの各メソッドをfinalとする
 * <理由>
 * (1) アクセッサは値の設定または取得が目的であるため、サブクラスで実装を変更する必要がない
 * (2) 使用頻度が高く、インライン展開による処理の高速化のメリットが大きい
 * 
 * ただし、以下のメソッドはfinalとしない
 * ■ setSpeed、getSpeedメソッド
 *     → 乗り物の種類により速度の上限が変化する 可能性があり、サブクラスで実装が変更される可能性があるため
 * ■ getMaxIdメソッド
 *     → 登録が解除されたIDを再利用するなど、IDの振り方がサブクラスで変更される可能性があるため
 * 
 */

public class Vehicle {
	static final String TURN_LEFT = "left";
	static final String TURN_RIGHT = "right";
	private static final int TURN_LEFT_ANGLE = 270; // 左折時の回転角度
	private static final int TURN_RIGHT_ANGLE = 90; // 右折時の回転角度
	private static int nextId = 0;
	private int id;
	private double speed;
	private double direction;
	private String name;

	/**
	 * 引数なしコンストラクタ
	 */
	Vehicle() {
		id = nextId++;
	}

	/**
	 * 名前を設定するコンストラクタ
	 */
	Vehicle(String name) {
		id = nextId++;
		this.name = name;
	}

	/**
	 * スピードのセッタ
	 */
	public void setSpeed(double speed) {
		if (speed >= 0) {
			this.speed = speed;
		}
	}

	/**
	 * 方向のセッタ
	 */
	public final void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	/**
	 * 名前のセッタ
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * IDを返す
	 */
	public final int getId() {
		return id;
	}

	/**
	 * スピードを返す
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * 方向を返す
	 */
	public final double getDirection() {
		return direction;
	}

	/**
	 * 名前を返す
	 */
	public final String getName() {
		return name;
	}

	/**
	 * 今まで使われたIDの最大値を返す
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	/**
	 * スピードを変更する
	 */
	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * スピードをリセット（停止）する
	 */
	public void stop() {
		speed = 0;
	}

	/**
	 * 角度を指定し方向転換する
	 */
	public void turn(double angle) {
		setDirection((getDirection() + angle) % 360);
	}

	/**
	 * TURN_LEFT または TURN_RIGHT を指定し方向転換する
	 */
	public void turn(String angle) {
		if (angle.equals("left")) {
			setDirection((getDirection() + TURN_LEFT_ANGLE) % 360);
		} else if (angle.equals("right")) {
			setDirection((getDirection() + TURN_RIGHT_ANGLE) % 360);
		} else {
			return;
		}
	}

	/**
	 * 情報を出力する
	 */
	public String toString() {
		String info = "VEHICLE INFO: ID=" + id + ", SPEED=" + speed
				+ ", DIRECTION=" + direction + ", NAME=" + name;
		return info;
	}
}
