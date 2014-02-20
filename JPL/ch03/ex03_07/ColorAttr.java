public class ColorAttr extends Attr {
	private ScreenColor myColor; // 変換された色

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor(); // 色情報を設定
	}

	public ColorAttr(String name) {
		this(name, "transparent"); // デフォルトカラーとして透明を設定
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	@Override
	public Object setValue(Object newValue) {
		// スーパークラスのsetValueを最初に行う
		Object retval = super.setValue(newValue); // valueを更新
		decodeColor(); // myColorを設定
		return retval;
	}

	/**
	 * 値を記述ではなくScreenColorに設定する
	 */
	public ScreenColor setValue(ScreenColor newValue) {
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/**
	 * 変換されたScreenColorオブジェクトを返す
	 */
	public ScreenColor getColor() {
		return myColor;
	}

	/**
	 * getValue()で得られる記述からScreenColorを設定する
	 */
	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	/**
	 * name, valueともに値が一致すればtrueを返す
	 */
	public boolean equals(ColorAttr color) {
		if ((this.getName().equals(color.getName()))
				&& (this.getValue().equals(color.getValue())))
			return true;
		else
			return false;
	}

	/**
	 * name, valueの値に基づいてハッシュコードを返す
	 */
	public int hashCode() {
		return (getName().hashCode() + getValue().hashCode()) / 2;
	}
}

class ScreenColor {
	private Object color;

	/**
	 * 色を設定するコンストラクタ
	 */
	ScreenColor(Object color) {
		this.color = color;
	}

	/**
	 * 色を取得する
	 */
	public Object getScreenColor() {
		return color;
	}

	@Override
	public int hashCode() {
		return getScreenColor().hashCode();
	}
}
