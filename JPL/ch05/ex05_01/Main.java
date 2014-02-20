/*
 * Attrをネストしたクラスとする
 * 
 * <理由>
 * AttrクラスはAttributedインタフェースを実装したクラスによって操作される属性の
 * 集まりを保持したクラスであるため
 */

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		AttributedImpl attribute = new AttributedImpl();
		attribute.add(new Attributed.Attr("One", 1));
		attribute.add(new Attributed.Attr("Two", 2));
		attribute.add(new Attributed.Attr("Three", 3));
		attribute.add(new Attributed.Attr("Four", 4));
		attribute.add(new Attributed.Attr("Five", 5));
		attribute.remove("Three");

		for (Iterator<Attributed.Attr> i = attribute.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}
}
