public enum TrafficLight {

	RED(new Color("Red", 255, 0, 0)),
	YELLOW(new Color("Yello", 255, 255, 0)),
	GREEN(new Color("Green", 0, 255, 0));

	Color color;

	TrafficLight(Color color) {
		this.color = color;
	}

	/**
	 * 引数に対応するColorオブジェクトを取得
	 */
	public Color getColor() {
		return color;
	}
}

class Color {
	private String name; // 色の名前
	private int[] rgb = new int[3]; // RGB

	Color(String name, int r, int g, int b) {
		this.name = name;
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
	}

	/**
	 * 色の名前を取得
	 */
	public String getName() {
		return name;
	}

	/**
	 * 色のRGBを配列として取得
	 */
	public int[] getRgb() {
		return rgb;
	}
}
