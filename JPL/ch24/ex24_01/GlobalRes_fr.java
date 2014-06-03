import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

public class GlobalRes_fr extends ResourceBundle {

	@Override
	public Enumeration<String> getKeys() {
		Vector<String> table = new Vector<String>();
		table.add(GlobalRes.HELLO);
		table.add(GlobalRes.GOODBYE);
		return table.elements();
	}

	@Override
	public Object handleGetObject(String key) {
		if (key.equals(GlobalRes.HELLO)) {
			return "Bonjour.";
		} else if (key.equals(GlobalRes.GOODBYE)) {
			return "Au revoir.";
		} else {
			throw new AssertionError();
		}
	}
}
