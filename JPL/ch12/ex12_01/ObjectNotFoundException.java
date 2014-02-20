/*
 * nullを返すより例外を使用するほうが好ましい理由：
 * nullチェックを忘れると、nullが格納されたオブジェクトを誤って使用したまま次の処理に進んでしまうため
 * 
 * 何か付け加えるとしたら、どのようなデータを追加すべきか：
 * 見つけようとしていたオブジェクトの名前
 */

public class ObjectNotFoundException extends Exception {

	public final String objName;

	public ObjectNotFoundException(String objName) {
		super("No object named \'" + objName + "\' found");
		this.objName = objName;
	}

}
