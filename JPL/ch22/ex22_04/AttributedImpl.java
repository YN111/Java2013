// ex11_03を変更

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl<T> extends Observable implements Attributed<T>, Iterable<Attr<T>> {

	protected Map<String, Attr<T>> attrTable = new HashMap<String, Attr<T>>();

	@Override
	public Iterator<Attr<T>> iterator() {
		return attrs();
	}

	@Override
	public void add(Attr<T> newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
		setChanged();
		notifyObservers(attrTable); // 変更通知
	}

	@Override
	public Attr<T> find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr<T> remove(String attrName) {
		Attr<T> attr = attrTable.remove(attrName);
		setChanged();
		notifyObservers(attrTable); // 変更通知
		return attr;
	}

	@Override
	public Iterator<Attr<T>> attrs() {
		return attrTable.values().iterator();
	}
}