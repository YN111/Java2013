import java.util.Currency;
import java.util.Locale;

public class CurrencySign {

	/**
	 * 引数に指定された各ロケールで各通貨に対する通貨記号を表す表を表示します。
	 * @param locale
	 * @param currency
	 */
	public static void printCurrencySign(Locale[] locale, Currency[] currency) {
		for (Locale l : locale) {
			System.out.print(l + "\t");
			for (Currency c : currency) {
				System.out.print(c.getSymbol(l) + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Locale[] locale = new Locale[] { new Locale("en", "US"), new Locale("it", "IT"),
				new Locale("fr", "FR"), new Locale("zh", "CN"), new Locale("ru", "RU"),
				new Locale("ja", "JP") };
		Currency[] currency = new Currency[] { Currency.getInstance("JPY"), Currency.getInstance("USD"),
				Currency.getInstance("EUR"), Currency.getInstance("RUB"), Currency.getInstance("GBP"),
				Currency.getInstance("CNY") };
		printCurrencySign(locale, currency);
	}
}
