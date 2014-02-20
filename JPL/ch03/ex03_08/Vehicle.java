public class Vehicle implements Cloneable{
	static final String TURN_LEFT = "left";
	static final String TURN_RIGHT = "right";
	private static final int TURN_LEFT_ANGLE = 270; // 左折時の回転角度
	private static final int TURN_RIGHT_ANGLE = 90; // 右折時の回転角度
	private static int nextId = 0;
	private int id;
	private double speed;
	private double direction;
	private String name;
	private boolean startFlag = false; // 動力源が空でないことを保証する
	private final EnergySource energy; // 動力源

	/**
	 * 動力源のみ設定するコンストラクタ
	 */
	Vehicle(EnergySource energy) {
		id = nextId++;
		this.energy = energy;
	}

	/**
	 * 動力源と名前を設定するコンストラクタ
	 */
	Vehicle(EnergySource energy, String name) {
		id = nextId++;
		this.energy = energy;
		this.name = name;
	}
	
	/**
	 * IDのセッタ（cloneを作成する時に使用）
	 */
	public void setId() {
		id = nextId++;
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
	public void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	/**
	 * 名前のセッタ
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * IDを返す
	 */
	public int getId() {
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
	public double getDirection() {
		return direction;
	}

	/**
	 * 名前を返す
	 */
	public String getName() {
		return name;
	}

	/**
	 * 今まで使われたIDの最大値を返す
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	/**
	 * 動作可能フラグの値を返す
	 */
	public boolean getStartFlag() {
		return startFlag;
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
	 * 動力源が空かチェックする
	 */
	public void start() {
		if (energy.empty()) {
			startFlag = false; // ガス欠
		} else {
			startFlag = true; // 動作可能
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
	
	/**
	 * クローンを作成する
	 * IDのみ新たな番号を割り振る
	 */
	public Vehicle clone() throws CloneNotSupportedException {
		Vehicle clone = (Vehicle) super.clone();
		clone.setId();
		return clone;
	}
}
