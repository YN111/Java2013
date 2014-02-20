import java.util.Calendar;

/**
 * 日付と時刻の取得を行うユーティリティクラスです
 */
public class ClockUtil {

	/**
	 * 現在の日付を文字列として取得します
	 * @return 日付の文字列
	 */
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "/" + month + "/" + date;
	}

	/**
	 * 現在の時刻（時）を取得します
	 * @return 時刻（時）
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 現在の時刻（分）を取得します
	 * @return 時刻（分）
	 */
	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 現在の時刻（秒）を取得します
	 * @return 時刻（秒）
	 */
	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 現在の時刻（ミリ秒）を取得します
	 * @return 時刻（ミリ秒）
	 */
	public static int getMilliSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}
}
