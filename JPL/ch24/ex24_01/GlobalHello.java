import java.util.Locale;
import java.util.ResourceBundle;

public class GlobalHello {
	public static void main(String[] args) {
		Locale.setDefault(Locale.TAIWAN);
		ResourceBundle res = ResourceBundle.getBundle("GlobalRes");

		System.out.println("=== default ===");
		System.out.println(res.getString(GlobalRes.HELLO));
		System.out.println(res.getString(GlobalRes.GOODBYE));

		Locale.setDefault(new Locale("en"));
		res = ResourceBundle.getBundle("GlobalRes");
		System.out.println("=== en ===");
		System.out.println(res.getString(GlobalRes.HELLO));
		System.out.println(res.getString(GlobalRes.GOODBYE));

		Locale.setDefault(new Locale("en", "AU"));
		res = ResourceBundle.getBundle("GlobalRes");
		System.out.println("=== en_AU ===");
		System.out.println(res.getString(GlobalRes.HELLO));
		System.out.println(res.getString(GlobalRes.GOODBYE));

		Locale.setDefault(new Locale("fr"));
		res = ResourceBundle.getBundle("GlobalRes");
		System.out.println("=== fr ===");
		System.out.println(res.getString(GlobalRes.HELLO));
		System.out.println(res.getString(GlobalRes.GOODBYE));

		Locale.setDefault(new Locale("ja"));
		res = ResourceBundle.getBundle("GlobalRes");
		System.out.println("=== ja ===");
		System.out.println(res.getString(GlobalRes.HELLO));
		System.out.println(res.getString(GlobalRes.GOODBYE));
	}
}
