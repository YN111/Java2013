/* enumを用いるメリット
 * ex02_17では方向の選択をString型としていたため、turnメソッドでTURN_LEFT、TURN_RIGHT以外の
 * 引数が入力されたときのエラー処理が必要だった。
 * enumを用いれば、誤った引数が入力されると コンパイルエラーとなるため、この処理が不要となる。
 */

public class Vehicle {
	private static final int TURN_LEFT_ANGLE = 270; // 左折時の回転角度
	private static final int TURN_RIGHT_ANGLE = 90; // 右折時の回転角度
	private static int nextId = 0;
	private int id;
	private double speed;
	private double direction;
	private String name;

	// 左折 or 右折
	enum TurnDirection {
		TURN_LEFT, TURN_RIGHT;
	}

	Vehicle() {
		id = nextId++;
	}

	Vehicle(String name) {
		id = nextId++;
		this.name = name;
	}

	public void setSpeed(double speed) {
		if (speed >= 0) {
			this.speed = speed;
		}
	}

	public void setDirection(double direction) {
		if (direction >= 0 && direction <= 360) {
			this.direction = direction;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDirection() {
		return direction;
	}

	public String getName() {
		return name;
	}

	/**
	 * 今まで使われたIDの最大値を返す
	 * 
	 * @return
	 */
	public int getMaxId() {
		return nextId - 1;
	}

	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * スピードを0にする
	 */
	public void stop() {
		speed = 0;
	}

	/**
	 * 角度指定による方向転換
	 * 
	 * @param angle
	 */
	public void turn(double angle) {
		setDirection((getDirection() + angle) % 360);
	}

	/**
	 * TURN_LEFT or TURN_RIGHT による方向転換
	 * 
	 * @param angle
	 */
	public void turn(TurnDirection angle) {
		if (angle == TurnDirection.TURN_LEFT) {
			setDirection((getDirection() + TURN_LEFT_ANGLE) % 360);
		} else {
			setDirection((getDirection() + TURN_RIGHT_ANGLE) % 360);
		}
	}

	public String toString() {
		String info = "CAR1:ID=" + id + ", SPEED=" + speed + ", DIRECTION="
				+ direction + ", NAME=" + name;
		return info;
	}
}
