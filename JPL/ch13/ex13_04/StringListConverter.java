import java.util.ArrayList;

public class StringListConverter {

	private ArrayList<Object> list = new ArrayList<Object>();

	/**
	 * 引数で指定されたデータをリストに追加します
	 * リストは "type value" の形式に従っている必要があります
	 * typeはラッパークラスの1つであり、valueはtypeのコンストラクタで変換可能な文字列です
	 * 複数のデータを追加する場合は各行を"\n"で区切ります
	 * @param items リストに追加するデータ
	 */
	public void add(String items) {
		String[] line = items.split("\n"); // 各行に分割

		// リストに要素を格納
		for (String str : line) {
			if (str.indexOf(" ") != str.lastIndexOf(" ") || str.indexOf(" ") < 0)
				throw new IllegalArgumentException("引数は \"type value\" の形式に従っている必要があります");

			String[] typeValue = str.split(" "); // typeとvalueに分割	
			
			
			if (typeValue[0].equals("Boolean"))
				list.add(new Boolean(typeValue[1]));
			else if (typeValue[0].equals("Byte"))
				list.add(new Byte(typeValue[1]));
			else if (typeValue[0].equals("Short"))
				list.add(new Short(typeValue[1]));
			else if (typeValue[0].equals("Integer"))
				list.add(new Integer(typeValue[1]));
			else if (typeValue[0].equals("Long"))
				list.add(new Long(typeValue[1]));
			else if (typeValue[0].equals("Float"))
				list.add(new Float(typeValue[1]));
			else if (typeValue[0].equals("Double"))
				list.add(new Double(typeValue[1]));
			else if (typeValue[0].equals("Character")) {
				if (typeValue[1].length() != 1)
					throw new IllegalArgumentException("valueの値が不正です");
				else
					list.add(new Character(typeValue[1].charAt(0)));
			} else
				throw new IllegalArgumentException("typeの値が不正です");
		}
		
		// リストの要素を表示
		for (Object o : list)
			System.out.println(o.getClass() + " : " + o);
	}
	
	/**
	 * リストを取得します
	 * @return リスト
	 */
	public ArrayList<Object> getList() {
		return list;
	}
}
