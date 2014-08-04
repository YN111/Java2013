import java.awt.Color;

public class ColorUtil {

	public static final String COLOR_WHITE = "白";
	public static final String COLOR_BLACK = "黒";
	public static final String COLOR_GRAY = "グレー";
	public static final String COLOR_RED = "赤";
	public static final String COLOR_BLUE = "青";
	public static final String COLOR_YELLOW = "黄";
	public static final String COLOR_GREEN = "緑";
	public static final String COLOR_PINK = "ピンク";
	public static final String COLOR_CYAN = "水色";
	public static final String COLOR_RAINBOW = "虹色";
	public static final String DEFAULT_PICTURE = "デフォルト画像";
	public static final String SELECT_PICTURE = "画像を選択";

	/**
	 * カラーオブジェクトを文字列に変換します
	 */
	public static String convertColorToString(Color color) {
		if (color.equals(Color.WHITE)) {
			return COLOR_WHITE;
		} else if (color.equals(Color.BLACK)) {
			return COLOR_BLACK;
		} else if (color.equals(Color.LIGHT_GRAY)) {
			return COLOR_GRAY;
		} else if (color.equals(Color.RED)) {
			return COLOR_RED;
		} else if (color.equals(Color.BLUE)) {
			return COLOR_BLUE;
		} else if (color.equals(Color.YELLOW)) {
			return COLOR_YELLOW;
		} else if (color.equals(Color.GREEN)) {
			return COLOR_GREEN;
		} else if (color.equals(Color.PINK)) {
			return COLOR_PINK;
		} else if (color.equals(Color.CYAN)) {
			return COLOR_CYAN;
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * 文字列をカラーオブジェクトに変換します
	 */
	public static Color convertStringToColor(String str) {
		if (COLOR_WHITE.equals(str)) {
			return Color.WHITE;
		} else if (COLOR_BLACK.equals(str)) {
			return Color.BLACK;
		} else if (COLOR_GRAY.equals(str)) {
			return Color.LIGHT_GRAY;
		} else if (COLOR_RED.equals(str)) {
			return Color.RED;
		} else if (COLOR_BLUE.equals(str)) {
			return Color.BLUE;
		} else if (COLOR_YELLOW.equals(str)) {
			return Color.YELLOW;
		} else if (COLOR_GREEN.equals(str)) {
			return Color.GREEN;
		} else if (COLOR_PINK.equals(str)) {
			return Color.PINK;
		} else if (COLOR_CYAN.equals(str)) {
			return Color.CYAN;
		} else if (COLOR_RAINBOW.equals(str)) {
			return Color.ORANGE;
		} else if (DEFAULT_PICTURE.equals(str)) {
			return Color.GRAY;
		} else if (SELECT_PICTURE.equals(str)) {
			return Color.GRAY;
		} else {
			throw new AssertionError();
		}
	}
}
