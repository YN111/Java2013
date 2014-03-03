import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class InterpretUtil {

	/* =======================================================================
	 *   コンストラクタ
	 * ======================================================================= */

	/**
	 * 指定されたコンストラクタの情報を文字列として返します
	 * @param con
	 * @return
	 */
	public static String constructorToString(Constructor<?> con) {
		StringBuilder ret = new StringBuilder();
		String modif = Modifier.toString(con.getModifiers()); // 修飾子
		ret.append(modif);
		if (modif.length() > 0) {
			ret.append(" ");
		}
		ret.append(con.getDeclaringClass().getSimpleName()); // 名前
		ret.append(argsToString(con.getParameterTypes())); // 引数
		ret.append(exceptionToString(con.getExceptionTypes())); // 例外
		return ret.toString();
	}

	/**
	 * 指定されたコンストラクタを呼び出し、オブジェクトを生成します<br>
	 * @param con コンストラクタ
	 * @param args 呼び出し時の引数（カンマ区切りで指定）
	 * @param holder 保持オブジェクトのデータホルダー
	 * @throws Throwable 
	 */
	public static Object createNewObject(Constructor<?> con, String args, ObjectHolder holder)
			throws Throwable {
		con.setAccessible(true);
		Type[] paramTypes = con.getParameterTypes();
		if (paramTypes.length == 0 && args.length() != 0) {
			throw new UserInputException("引数の数が不正なためオブジェクト生成を中止しました");
		}

		Object[] paramObject = null;
		if (paramTypes.length > 0) {
			String[] paramStrings = args.split(",");
			paramObject = new Object[paramStrings.length];
			for (int i = 0; i < paramObject.length; i++) {
				paramObject[i] = convertStringToObject(paramTypes[i], paramStrings[i], holder);
			}
		}

		Object newObject;
		try {
			newObject = con.newInstance(paramObject);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (InstantiationException e) {
			throw new InstantiationException(e.toString());
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException(e.toString());
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}

		return newObject;
	}

	/* =======================================================================
	 *   フィールド
	 * ======================================================================= */

	/**
	 * 指定されたオブジェクトの指定されたフィールドの情報を文字列として返します
	 * @param f
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String fieldToString(Field f, Object obj) throws IllegalArgumentException,
			IllegalAccessException {
		f.setAccessible(true);
		Object value = f.get(obj);

		StringBuilder ret = new StringBuilder();
		String modif = Modifier.toString(f.getModifiers());
		ret.append(modif);
		if (modif.length() > 0) {
			ret.append(" ");
		}
		ret.append(f.getType().getSimpleName());
		ret.append(" ");
		ret.append(f.getName());
		ret.append(" = ");
		ret.append(value);

		return ret.toString();
	}

	/**
	 * 指定されたオブジェクトが持つフィールドの一覧を返します
	 * @param obj
	 * @return
	 */
	public static Field[] getFields(Object obj) {
		HashSet<Field> fieldSet = new HashSet<Field>();
		fieldSet.addAll(Arrays.asList(obj.getClass().getFields()));
		fieldSet.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
		Field[] fields = new Field[fieldSet.size()];
		fieldSet.toArray(fields);
		Arrays.sort(fields, new MemberComparator()); // ソート
		return fields;
	}

	/**
	 * 指定されたオブジェクトの指定されたフィールドの値を取得します
	 * @param f
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getFieldValue(Field f, Object obj) throws IllegalArgumentException,
			IllegalAccessException {
		f.setAccessible(true);
		return f.get(obj);
	}

	/**
	 * 指定されたフィールドの値を指定されたオブジェクトに更新します
	 * @param f
	 * @param obj フィールドを書き換える操作をするオブジェクト
	 * @param newValue 書き換え後の値
	 * @param holder 保持オブジェクトのデータホルダー
	 * @throws Exception 入力された値が不正な場合にスローされます
	 */
	public static void updateField(Field f, Object obj, String newValue, ObjectHolder holder)
			throws Exception {
		Object newObject = convertStringToObject(f.getGenericType(), newValue, holder);
		f.setAccessible(true);
		f.set(obj, newObject);
	}

	/* =======================================================================
	 *   メソッド
	 * ======================================================================= */

	/**
	 * 指定されたオブジェクトが持つメソッドの一覧を返します
	 * @param obj
	 * @return
	 */
	public static Method[] getMethods(Object obj) {
		HashSet<Method> methodSet = new HashSet<Method>();
		methodSet.addAll(Arrays.asList(obj.getClass().getMethods()));
		methodSet.addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));
		Method[] methods = new Method[methodSet.size()];
		methodSet.toArray(methods);
		Arrays.sort(methods, new MemberComparator()); // ソート
		return methods;
	}

	/**
	 * 指定されたメソッドの情報を文字列として返します
	 * @param m
	 * @return
	 */
	public static String methodToString(Method m) {
		StringBuilder ret = new StringBuilder();
		String modif = Modifier.toString(m.getModifiers()); // 修飾子
		ret.append(modif);
		if (modif.length() > 0) {
			ret.append(" ");
		}
		ret.append(m.getReturnType().getSimpleName()); // 戻り値
		ret.append(" ");
		ret.append(m.getName()); // 名前
		ret.append(argsToString(m.getParameterTypes())); // 引数
		ret.append(exceptionToString(m.getExceptionTypes())); // 例外
		return ret.toString();
	}

	/**
	 * 指定されたメソッドを呼び出し、戻り値のオブジェクトを返します<br>
	 * @param m メソッド
	 * @param obj メソッド呼び出しを行うオブジェクト
	 * @param args 呼び出し時の引数（カンマ区切りで指定）
	 * @param holder 保持オブジェクトのデータホルダー
	 * @throws Throwable 
	 */
	public static Object invokeMethod(Method m, Object obj, String args, ObjectHolder holder)
			throws Throwable {
		m.setAccessible(true);
		Type[] paramTypes = m.getGenericParameterTypes();
		if (paramTypes.length == 0 && args.length() != 0) {
			throw new UserInputException("引数の数が不正なためメソッド呼び出しを中止しました");
		}

		Object[] paramObject = null;

		if (paramTypes.length > 0) {
			String[] paramStrings = args.split(",");
			if (paramStrings.length != paramTypes.length) {
				throw new UserInputException("引数の数が不正なためメソッド呼び出しを中止しました");
			}
			paramObject = new Object[paramStrings.length];
			for (int i = 0; i < paramObject.length; i++) {
				paramObject[i] = convertStringToObject(paramTypes[i], paramStrings[i], holder);
			}
		}

		Object newObject;
		try {
			newObject = m.invoke(obj, paramObject);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException(e.toString());
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}

		return newObject;
	}

	/* =======================================================================
	 *   配列
	 * ======================================================================= */

	/**
	 * 配列オブジェクトを生成します
	 * @param cls
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	public static Object createNewArray(String type, String size) throws Exception {
		Class<?> cls;

		// クラス名の取得
		// 基本データ型の場合
		if (type.equals("boolean")) {
			cls = boolean.class;
		} else if (type.equals("byte")) {
			cls = byte.class;
		} else if (type.equals("char")) {
			cls = char.class;
		} else if (type.equals("short")) {
			cls = short.class;
		} else if (type.equals("int")) {
			cls = int.class;
		} else if (type.equals("long")) {
			cls = long.class;
		} else if (type.equals("float")) {
			cls = float.class;
		} else if (type.equals("double")) {
			cls = double.class;
		}
		// 参照型の場合
		else {
			cls = Class.forName(type);
		}

		// サイズの取得
		String[] sizeSplit = size.split(",");
		int length = sizeSplit.length;
		if (length > 2) {
			throw new UserInputException("配列の次元は2次元までで指定してください");
		}

		// 1次元配列の場合
		if (length == 1) {
			try {
				int dimension = Integer.parseInt(sizeSplit[0]);
				return Array.newInstance(cls, dimension);
			} catch (NumberFormatException e) {
				throw new UserInputException("配列のサイズは数値で入力してください");
			}
		}
		// 2次元配列の場合
		else if (length == 2) {
			try {
				int[] dimension = new int[2];
				dimension[0] = Integer.parseInt(sizeSplit[0]);
				dimension[1] = Integer.parseInt(sizeSplit[1]);
				return Array.newInstance(cls, dimension);
			} catch (NumberFormatException e) {
				throw new UserInputException("配列のサイズは数値で入力してください");
			}
		}
		// その他
		else {
			throw new UserInputException("配列の次元は2次元までで指定してください");
		}
	}

	/**
	 * 指定された配列の要素の更新値を取得します
	 * @param arr 配列
	 * @param index 書き換えるindex
	 * @param newValue 書き換え後の値
	 * @param holder 保持オブジェクトのデータホルダー
	 * @throws Exception 入力された値が不正な場合にスローされます
	 */
	public static Object getUpdateArrayItem(Object arr, String newValue, ObjectHolder holder)
			throws Exception {
		return convertStringToObject(arr.getClass(), newValue, holder);
	}

	/**
	 * 指定された配列が基本データ型の配列かを検査します<br>
	 * 2次元配列まで対応しています
	 * @param arr
	 * @return 基本データ型の場合はtrue、それ以外はfalse
	 */
	public static boolean isPrimitiveArray(Object arr) {
		return arr.getClass().equals(boolean[].class) || arr.getClass().equals(boolean[][].class)
				|| arr.getClass().equals(byte[].class) || arr.getClass().equals(byte[][].class)
				|| arr.getClass().equals(char[].class) || arr.getClass().equals(char[][].class)
				|| arr.getClass().equals(short[].class) || arr.getClass().equals(short[][].class)
				|| arr.getClass().equals(int[].class) || arr.getClass().equals(int[][].class)
				|| arr.getClass().equals(long[].class) || arr.getClass().equals(long[][].class)
				|| arr.getClass().equals(float[].class) || arr.getClass().equals(float[][].class)
				|| arr.getClass().equals(double[].class) || arr.getClass().equals(double[][].class);
	}

	/**
	 * 指定された配列の次元を返します<br>
	 * 2次元配列まで対応しています
	 * @param arr
	 * @return
	 */
	public static int getArrayDimension(Object arr) {
		String clsName = arr.getClass().getSimpleName();
		// クラス名に含まれる[]の数によって判定する
		return (clsName.length() - clsName.replaceAll("\\[\\]", "").length()) / 2;
	}

	/* =======================================================================
	 *   privateメソッド
	 * ======================================================================= */

	/**
	 * Typeの名前を表す文字列を返します
	 * @param type
	 * @return
	 */
	private static String typeToString(Type type) {
		if (type.getClass() == Class.class) {
			return ((Class<?>) type).getSimpleName();
		} else {
			return type.toString();
		}
	}

	/**
	 * コンストラクタ、メソッドの引数を表す文字列を返します
	 * @param types 引数の一覧
	 * @return
	 */
	private static String argsToString(Type[] types) {
		StringBuilder ret = new StringBuilder();
		ret.append("(");
		for (Type type : types) {
			ret.append(typeToString(type));
			ret.append(",");
		}
		if (types.length > 0) {
			ret.deleteCharAt(ret.length() - 1);
		}
		ret.append(")");
		return ret.toString();
	}

	/**
	 * コンストラクタ、メソッドの例外を表す文字列を返します
	 * @param types 例外の一覧
	 * @return
	 */
	private static String exceptionToString(Type[] types) {
		StringBuilder ret = new StringBuilder("");
		if (types.length > 0) {
			ret.append(" throws ");
			for (Type exceptionType : types) {
				ret.append(typeToString(exceptionType));
				ret.append(",");
			}
			ret.deleteCharAt(ret.length() - 1);
		}
		return ret.toString();
	}

	/**
	 * 指定された文字列に対応するオブジェクトを取得します
	 * @param type
	 * @param value
	 * @param holder
	 * @return 対応するオブジェクト（オブジェクトが見つからない場合はnull）
	 * @throws UserInputException
	 */
	private static Object convertStringToObject(Type type, String value, ObjectHolder holder)
			throws Exception {
		// 基本データ型の場合
		if (value.length() < 3 || !value.substring(0, 3).equals("obj")) {
			if (value.equals("null")) {
				return null;
			}

			if (type.equals(Boolean.class) || type.equals(boolean.class)
					|| type.equals(Boolean[].class) || type.equals(boolean[].class)
					|| type.equals(Boolean[][].class) || type.equals(boolean[][].class)) {
				return Boolean.valueOf(value);

			} else if (type.equals(Byte.class) || type.equals(byte.class)
					|| type.equals(Byte[].class) || type.equals(byte[].class)
					|| type.equals(Byte[][].class) || type.equals(byte[][].class)) {
				return Byte.valueOf(value);

			} else if (type.equals(Character.class) || type.equals(char.class)
					|| type.equals(Character[].class) || type.equals(char[].class)
					|| type.equals(Character[][].class) || type.equals(char[][].class)) {
				return convertCharacter(value);

			} else if (type.equals(Short.class) || type.equals(short.class)
					|| type.equals(Short[].class) || type.equals(short[].class)
					|| type.equals(Short[][].class) || type.equals(short[][].class)) {
				return Short.valueOf(value);

			} else if (type.equals(Integer.class) || type.equals(int.class)
					|| type.equals(Integer[].class) || type.equals(int[].class)
					|| type.equals(Integer[][].class) || type.equals(int[][].class)) {
				return Integer.valueOf(value);

			} else if (type.equals(Long.class) || type.equals(long.class)
					|| type.equals(Long[].class) || type.equals(long[].class)
					|| type.equals(Long[][].class) || type.equals(long[][].class)) {
				if (value.charAt(value.length() - 1) == 'l'
						|| value.charAt(value.length() - 1) == 'L') {
					return Long.valueOf(value.substring(0, value.length() - 1));
				} else {
					return Long.valueOf(value);
				}

			} else if (type.equals(Double.class) || type.equals(double.class)
					|| type.equals(Double[].class) || type.equals(double[].class)
					|| type.equals(Double[][].class) || type.equals(double[].class)) {
				return Double.valueOf(value);

			} else if (type.equals(Float.class) || type.equals(float.class)
					|| type.equals(Float[].class) || type.equals(float[].class)
					|| type.equals(Float[][].class) || type.equals(float[][].class)) {
				if (value.charAt(value.length() - 1) == 'f'
						|| value.charAt(value.length() - 1) == 'F') {
					return Float.valueOf(value.substring(0, value.length() - 1));
				} else {
					return Float.valueOf(value);
				}

			} else if (type.equals(String.class) || type.equals(String[].class)
					|| type.equals(String[][].class)) {
				return convertString(value);

			} else {
				// Object型等の場合は引数の形式で返す方を判別する
				if (value.equals("true")) {
					return true;
				} else if (value.equals("false")) {
					return false;
				} else if ((value.charAt(0) == '\'') && (value.charAt(value.length() - 1) == '\'')) {
					return convertCharacter(value);
				} else if ((value.charAt(0) == '\"') && (value.charAt(value.length() - 1) == '\"')) {
					return convertString(value);
				} else if ((value.charAt(value.length() - 1)) == 'l'
						|| (value.charAt(value.length() - 1)) == 'L') {
					return Long.valueOf(value.substring(0, value.length() - 1));
				} else if ((value.charAt(value.length() - 1)) == 'f'
						|| (value.charAt(value.length() - 1)) == 'F') {
					return Float.valueOf(value.substring(0, value.length() - 1));
				}

				try {
					int ret = Integer.parseInt(value);
					return ret;
				} catch (NumberFormatException e) {
				}

				try {
					double ret = Double.parseDouble(value);
					return ret;
				} catch (NumberFormatException e) {
				}

				return value;
			}
		}

		// 参照型の場合（既に保持されているオブジェクトから指定された場合）
		else {
			Object obj = holder.getObject(value);
			if (obj != null) {
				return obj;
			} else {
				throw new UserInputException("指定されたオブジェクトは存在しません");
			}
		}
	}

	/**
	 * 引数に指定された文字列をchar型に変換します<br>
	 * 文字列は '' で囲まれている必要があります
	 * @param value
	 * @return
	 * @throws UserInputException 
	 */
	private static char convertCharacter(String value) throws UserInputException {
		if (value.charAt(0) != '\'' || value.charAt(value.length() - 1) != '\'') {
			throw new UserInputException("入力された値が不正です：char型は \'\' で囲んでください");
		} else {
			// 最初と最後の''を取り除く
			String charStr = value.substring(1, value.length() - 1);
			// エスケープシーケンス
			if (charStr.charAt(0) == '\\') {
				switch (charStr.charAt(1)) {
				case 'n':
					return '\n';
				case 't':
					return '\t';
				case 'b':
					return '\b';
				case 'r':
					return '\r';
				case 'f':
					return '\f';
				case '\\':
					return '\\';
				case '\'':
					return '\'';
				case '\"':
					return '\"';
				default:
					throw new UserInputException("入力された値が不正です：char型に変換できない値です");
				}
			} else {
				if (charStr.length() != 1) {
					throw new UserInputException("入力された値が不正です：char型に変換できない値です");
				} else {
					return charStr.charAt(0);
				}
			}
		}
	}

	/**
	 * 引数に指定された文字列のエスケープシーケンスを変換します<br>
	 * @param value
	 * @return
	 * @throws UserInputException
	 */
	private static String convertString(String value) throws UserInputException {
		if (value.charAt(0) != '\"' || value.charAt(value.length() - 1) != '\"') {
			throw new UserInputException("入力された値が不正です：String型は \"\" で囲んでください");
		} else {
			StringBuilder ret = new StringBuilder();
			for (int i = 1; i < value.length() - 1; i++) {
				char c = value.charAt(i);
				if (c == '\\') {
					i++;
					char escape = value.charAt(i);
					switch (escape) {
					case 'n':
						ret.append('\n');
						break;
					case 't':
						ret.append('\t');
						break;
					case 'b':
						ret.append('\b');
						break;
					case 'r':
						ret.append('\r');
						break;
					case 'f':
						ret.append('\f');
						break;
					case '\'':
						ret.append('\'');
						break;
					case '\"':
						ret.append('\"');
						break;
					case '\\':
						ret.append('\\');
						break;
					default:
						ret.append('\\');
						i--;
					}
				} else {
					ret.append(c);
				}
			}
			return ret.toString();
		}
	}
}

/**
 * フィールド、メソッドをソートするルールを定義しているクラスです
 */
class MemberComparator implements Comparator<Member> {
	@Override
	public int compare(Member m1, Member m2) {
		String s1 = m1.getName();
		String s2 = m2.getName();
		return s1.compareTo(s2);
	}
}
