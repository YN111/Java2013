import java.awt.Color;

public class ColorUtil {

	/**
	 * カラーオブジェクトを文字列に変換します
	 */
	public static String convertColorToString(Color color) {
		if (color.equals(Color.WHITE)) {
			return "WHITE";
		} else if (color.equals(Color.BLACK)) {
			return "BLACK";
		} else if (color.equals(Color.LIGHT_GRAY)) {
			return "GRAY";
		} else if (color.equals(Color.RED)) {
			return "RED";
		} else if (color.equals(Color.BLUE)) {
			return "BLUE";
		} else if (color.equals(Color.YELLOW)) {
			return "YELLOW";
		} else if (color.equals(Color.GREEN)) {
			return "GREEN";
		} else if (color.equals(Color.PINK)) {
			return "PINK";
		} else if (color.equals(Color.CYAN)) {
			return "CYAN";
		} else {
			throw new AssertionError();
		}
	}

	/**
	 * 文字列をカラーオブジェクトに変換します
	 */
	public static Color convertStringToColor(String str) {
		if ("WHITE".equals(str)) {
			return Color.WHITE;
		} else if ("BLACK".equals(str)) {
			return Color.BLACK;
		} else if ("GRAY".equals(str)) {
			return Color.LIGHT_GRAY;
		} else if ("RED".equals(str)) {
			return Color.RED;
		} else if ("BLUE".equals(str)) {
			return Color.BLUE;
		} else if ("YELLOW".equals(str)) {
			return Color.YELLOW;
		} else if ("GREEN".equals(str)) {
			return Color.GREEN;
		} else if ("PINK".equals(str)) {
			return Color.PINK;
		} else if ("CYAN".equals(str)) {
			return Color.CYAN;
		} else {
			throw new AssertionError();
		}
	}

}
