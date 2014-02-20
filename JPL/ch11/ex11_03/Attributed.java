/*
 * Attrがジェネリックになることによって、Attributedインタフェースもジェネリックにしなければ
 * ならない
 * （もしくは、AttributedインタフェースでAttrの型パラメータを特定の引数で置き換える）
 * 
 * Attributedオブジェクトについては、これまでAttrのvalueがObject型となっていたため、
 * 誤った型のデータを追加する操作が可能だった。また、この誤りには、findまたはremove
 * メソッドで取り出した値を正しい型にキャストする際にClassCastExceptionがスローされ
 * るまで気付けなかった。
 * しかし、ここでジェネリック型を用いることで、Attributedオブジェクトに誤った型を入れた際
 * にコンパイルエラーとなる
 * さらに、findまたはremoveメソッドでデータを取り出す際にキャストが不要となる
 */

import java.util.*;

public interface Attributed<T> {
	void add(Attr<T> newAttr);

	Attr<T> find(String attrName);

	Attr<T> remove(String attrName);

	java.util.Iterator<Attr<T>> attrs();
}

class AttributedImpl<T> implements Attributed<T>, Iterable<Attr<T>> {

	protected Map<String, Attr<T>> attrTable = new HashMap<String, Attr<T>>();

	@Override
	public Iterator<Attr<T>> iterator() {
		return attrs();
	}

	@Override
	public void add(Attr<T> newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr<T> find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr<T> remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr<T>> attrs() {
		return attrTable.values().iterator();
	}

	public static void main(String[] args) {
		Attributed<String> attributed = new AttributedImpl<String>();
		Attr<String> attrS = new Attr<String>("name", "value");
		attributed.add(attrS);
		String value = attrS.getValue(); // キャスト不要

		Attr<Integer> attrI = new Attr<Integer>("name", 10);
		attributed.add(attrI); // コンパイルエラー
	}
}