/*
 * Attrをネストしたクラスとする
 * 
 * <理由>
 * AttrクラスはAttributedインタフェースを実装したクラスによって操作される属性の
 * 集まりを保持したクラスであるため
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterable<Attributed.Attr> {
	protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

	public Iterator<Attr> iterator() {
		return attrs();
	}
}
