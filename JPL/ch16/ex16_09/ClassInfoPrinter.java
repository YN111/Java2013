import java.lang.reflect.*;

public class ClassInfoPrinter {

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("�����ɃN���X�����w�肵�Ă�������");
		}

		print(args[0]);
	}

	/**
	 * �N���X�錾��\�����܂�
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
	 * �N���X�̏���\�����܂�
	 * @param cls
	 */
	private static void printClass(Class<?> cls) {
		System.out.println("class " + cls.getSimpleName() + " {");
		System.out.println();
	}

	/**
	 * �N���X�Ɋ܂܂��t�B�[���h��錾�ʂ�ɕ\�����܂�
	 * @param cls
	 */
	private static void printField(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			// �C���q
			int modif = f.getModifiers();
			String modifStr = Modifier.toString(modif);
			System.out.print("    " + modifStr);
			if (modifStr.length() > 0) {
				System.out.print(" ");
			}

			// �^
			System.out.print(f.getType().getSimpleName() + " ");

			// ���O
			System.out.print(f.getName());

			// �萔�̒l
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
	 * �N���X�Ɋ܂܂��R���X�g���N�^��錾�ʂ�ɕ\�����܂�
	 * @param cls
	 */
	private static void printConstructor(Class<?> cls) {
		Constructor<?>[] consts = cls.getConstructors();
		for (Constructor<?> c : consts) {
			// �C���q
			String modif = Modifier.toString(c.getModifiers());
			System.out.print("    " + modif);
			if (modif.length() > 0) {
				System.out.print(" ");
			}

			// ���O
			System.out.print(c.getDeclaringClass().getSimpleName());

			// �����E��O
			System.out.print(argsToString(c.getParameterTypes()));
			System.out.print(exceptionToString(c.getExceptionTypes()));
			
			System.out.println(" {}");
		}
		System.out.println();
	}

	/**
	 * �N���X�Ɋ܂܂�郁�\�b�h��錾�ʂ�ɕ\�����܂�
	 * @param cls
	 */
	private static void printMethod(Class<?> cls) {
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			// �C���q
			String modif = Modifier.toString(m.getModifiers());
			System.out.print("    " + modif);
			if (modif.length() > 0) {
				System.out.print(" ");
			}

			// �߂�l
			System.out.print(m.getReturnType().getSimpleName() + " ");

			// ���O
			System.out.print(m.getName());

			// �����E��O
			System.out.print(argsToString(m.getParameterTypes()));
			System.out.print(exceptionToString(m.getExceptionTypes()));

			System.out.println(" {}");
		}
		System.out.println();
	}

	/**
	 * �R���X�g���N�^�A���\�b�h�̈�����\���������Ԃ��܂�
	 * @param types �����̈ꗗ
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
	 * �R���X�g���N�^�A���\�b�h�̗�O��\���������Ԃ��܂�
	 * @param types ��O�̈ꗗ
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
	 * Type�̖��O��\���������Ԃ��܂�
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
