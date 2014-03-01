import java.util.HashMap;

public class ObjectHolder {

	private HashMap<String, Object> objMap = new HashMap<String, Object>();
	private int keyCount = 0;

	/**
	 * オブジェクトを保持します
	 * @param obj 保持するオブジェクト
	 * @return 呼び出し用のキー
	 */
	public String addObject(Object obj) {
		String key = "obj" + keyCount++;
		objMap.put(key, obj);
		return key;
	}

	/**
	 * オブジェクトを取得します<br>
	 * オブジェクトが存在しない場合はnullが返ります
	 * @param key キー
	 * @return
	 */
	public Object getObject(String key) {
		return objMap.get(key);
	}
}
