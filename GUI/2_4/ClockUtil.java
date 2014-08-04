import java.util.Calendar;

public class ClockUtil {

	/**
	 * 現在の日付を文字列として取得します
	 */
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		StringBuilder builder = new StringBuilder();
		builder.append(cal.get(Calendar.YEAR));
		builder.append("/");
		builder.append(cal.get(Calendar.MONTH) + 1);
		builder.append("/");
		builder.append(cal.get(Calendar.DATE));
		builder.append("　");

		// 曜日
		int week = cal.get(Calendar.DAY_OF_WEEK);
		switch (week) {
		case 1:
			builder.append("SUN");
			break;
		case 2:
			builder.append("MON");
			break;
		case 3:
			builder.append("TUE");
			break;
		case 4:
			builder.append("WED");
			break;
		case 5:
			builder.append("THU");
			break;
		case 6:
			builder.append("FRI");
			break;
		case 7:
			builder.append("SAT");
			break;
		default:
			builder.append("SUN");
		}

		return builder.toString();
	}

	/**
	 * 現在の時刻を文字列として取得します
	 */
	public static String getTime() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("%02d", getHour()));
		builder.append(":");
		builder.append(String.format("%02d", getMinute()));
		builder.append(":");
		builder.append(String.format("%02d", getSecond()));
		return builder.toString();
	}

	/**
	 * 現在の時刻（時）を取得します
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 現在の時刻（分）を取得します
	 */
	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 現在の時刻（秒）を取得します
	 */
	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 現在の時刻（ミリ秒）を取得します
	 */
	public static int getMilliSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}

}
