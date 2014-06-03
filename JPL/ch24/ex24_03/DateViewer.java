import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateViewer {

	/**
	 * 引数で指定された日付の文字列を表示可能な全ての形式で表示します。
	 */
	public static void printDate(String date, Locale locale) {
		DateFormat df = DateFormat.getDateInstance();
		DateFormat shortFmt = DateFormat.getDateInstance(DateFormat.FULL, locale);
		DateFormat mediumFmt = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		DateFormat longFmt = DateFormat.getDateInstance(DateFormat.LONG, locale);
		DateFormat fullFmt = DateFormat.getDateInstance(DateFormat.FULL, locale);

		Date parse = null;
		try {
			parse = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(shortFmt.format(parse));
		System.out.println(mediumFmt.format(parse));
		System.out.println(longFmt.format(parse));
		System.out.println(fullFmt.format(parse));
	}

	public static void main(String[] args) {
		System.out.println("=== JAPAN ===");
		printDate("2014/11/11", Locale.JAPAN);
		System.out.println();
		System.out.println("=== US ===");
		printDate("2014/11/11", Locale.US);
	}
}
