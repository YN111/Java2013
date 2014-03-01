import java.util.HashMap;

public class ObjectHolder {

	private HashMap<String, Object> objMap = new HashMap<String, Object>();
	private int keyCount = 0;

	/**
	 * �I�u�W�F�N�g��ێ����܂�
	 * @param obj �ێ�����I�u�W�F�N�g
	 * @return �Ăяo���p�̃L�[
	 */
	public String addObject(Object obj) {
		String key = "obj" + keyCount++;
		objMap.put(key, obj);
		return key;
	}

	/**
	 * �I�u�W�F�N�g���擾���܂�<br>
	 * �I�u�W�F�N�g�����݂��Ȃ��ꍇ��null���Ԃ�܂�
	 * @param key �L�[
	 * @return
	 */
	public Object getObject(String key) {
		return objMap.get(key);
	}
}
