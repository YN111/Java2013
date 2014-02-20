/* 定数固有のメソッドの利用について：推奨しない
 * Colorオブジェクトを返すだけの実装ならば、練習問題6.4のようにgetColor()メソッドで
 * 取り出す方がコーディング量が少なかったため
 */

public enum TrafficLight {
	RED {
		Color getColor() {
			return new Color("Red", 255, 0, 0);
		}
	},
	YELLOW {
		Color getColor() {
			return new Color("Yellow", 255, 255, 0);
		}
	},
	GREEN {
		Color getColor() {
			return new Color("Green", 0, 255, 0);
		}
	};
	
	// このenumが定義するメソッドの宣言
	abstract Color getColor();
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
