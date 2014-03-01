import java.lang.reflect.*;

public class ClassInfoPrinter {

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("引数にクラス名を指定してください");
		}

		print(args[0]);
	}

	/**
	 * クラス宣言を表示します
	 * @param cls
	 */
	public static void print(String clsName) {
		Class<?> cls = null;
		try {
			cls = Class.forName(clsName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		printClass(cls);
		printField(cls);
		printConstructor(cls);
		printMethod(cls);

		System.out.println("}");
	}

	/**
	 * クラスの情報を表示します
	 * @param cls
	 */
	private static void printClass(Class<?> cls) {
		System.out.println("class " + cls.getSimpleName() + " {");
		System.out.println();
	}

	/**
	 * クラスに含まれるフィールドを宣言通りに表示します
	 * @param cls
	 */
	private static void printField(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			// 修飾子
			int modif = f.getModifiers();
			String modifStr = Modifier.toString(modif);
			System.out.print("    " + modifStr);
			if (modifStr.length() > 0) {
				System.out.print(" ");
			}

			// 型
			System.out.print(f.getType().getSimpleName() + " ");

			// 名前
			System.out.print(f.getName());

			// 定数の値
			if (Modifier.isFinal(modif) && Modifier.isStatic(modif) && !f.getType().isArray()) {
				f.setAccessible(true);
				try {
					System.out.print(" = " + f.get(cls));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			System.out.println(";");
		}
		System.out.println();
	}

	/**
	 * クラスに含まれるコンストラクタを宣言通りに表示します
	 * @param cls
	 */
	private static void printConstructor(Class<?> cls) {
		Constructor<?>[] consts = cls.getConstructors();
		for (Constructor<?> c : consts) {
			// 修飾子
			String modif = Modifier.toString(c.getModifiers());
			System.out.print("    " + modif);
			if (modif.length() > 0) {
				System.out.print(" ");
			}

			// 名前
			System.out.print(c.getDeclaringClass().getSimpleName());

			// 引数・例外
			System.out.print(argsToString(c.getParameterTypes()));
			System.out.print(exceptionToString(c.getExceptionTypes()));
			
			System.out.println(" {}");
		}
		System.out.println();
	}

	/**
	 * クラスに含まれるメソッドを宣言通りに表示します
	 * @param cls
	 */
	private static void printMethod(Class<?> cls) {
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			// 修飾子
			String modif = Modifier.toString(m.getModifiers());
			System.out.print("    " + modif);
			if (modif.length() > 0) {
				System.out.print(" ");
			}

			// 戻り値
			System.out.print(m.getReturnType().getSimpleName() + " ");

			// 名前
			System.out.print(m.getName());

			// 引数・例外
			System.out.print(argsToString(m.getParameterTypes()));
			System.out.print(exceptionToString(m.getExceptionTypes()));

			System.out.println(" {}");
		}
		System.out.println();
	}

	/**
	 * コンストラクタ、メソッドの引数を表す文字列を返します
	 * @param types 引数の一覧
	 * @return
	 */
	private static String argsToString(Type[] types) {
		StringBuilder ret = new StringBuilder();
		ret.append("(");
		for (int i = 0; i < types.length; i++) {
			ret.append(typeToString(types[i]));
			ret.append(" arg" + i);
			ret.append(", ");
		}
		if (types.length > 0) {
			ret.deleteCharAt(ret.length() - 1);
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

}
