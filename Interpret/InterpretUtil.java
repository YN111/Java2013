import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class InterpretUtil {

	/* =======================================================================
	 *   �R���X�g���N�^
	 * ======================================================================= */

	/**
	 * �w�肳�ꂽ�R���X�g���N�^�̏��𕶎���Ƃ��ĕԂ��܂�
	 * @param con
	 * @return
	 */
	public static String constructorToString(Constructor<?> con) {
		StringBuilder ret = new StringBuilder();
		String modif = Modifier.toString(con.getModifiers()); // �C���q
		ret.append(modif);
		if (modif.length() > 0) {
			ret.append(" ");
		}
		ret.append(con.getDeclaringClass().getSimpleName()); // ���O
		ret.append(argsToString(con.getParameterTypes())); // ����
		ret.append(exceptionToString(con.getExceptionTypes())); // ��O
		return ret.toString();
	}

	/**
	 * �w�肳�ꂽ�R���X�g���N�^���Ăяo���A�I�u�W�F�N�g�𐶐����܂�<br>
	 * @param con �R���X�g���N�^
	 * @param args �Ăяo�����̈����i�J���}��؂�Ŏw��j
	 * @param holder �ێ��I�u�W�F�N�g�̃f�[�^�z���_�[
	 * @throws Throwable 
	 */
	public static Object createNewObject(Constructor<?> con, String args, ObjectHolder holder)
			throws Throwable {
		Type[] paramTypes = con.getParameterTypes();
		if (paramTypes.length == 0 && args.length() != 0) {
			throw new UserInputException("�����̐����s���Ȃ��߃I�u�W�F�N�g�����𒆎~���܂���");
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
	 *   �t�B�[���h
	 * ======================================================================= */

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g�̎w�肳�ꂽ�t�B�[���h�̏��𕶎���Ƃ��ĕԂ��܂�
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
	 * �w�肳�ꂽ�I�u�W�F�N�g�����t�B�[���h�̈ꗗ��Ԃ��܂�
	 * @param obj
	 * @return
	 */
	public static Field[] getFields(Object obj) {
		HashSet<Field> fieldSet = new HashSet<Field>();
		fieldSet.addAll(Arrays.asList(obj.getClass().getFields()));
		fieldSet.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
		Field[] fields = new Field[fieldSet.size()];
		fieldSet.toArray(fields);
		Arrays.sort(fields, new MemberComparator()); // �\�[�g
		return fields;
	}

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g�̎w�肳�ꂽ�t�B�[���h�̒l���擾���܂�
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
	 * �w�肳�ꂽ�t�B�[���h�̒l���w�肳�ꂽ�I�u�W�F�N�g�ɍX�V���܂�
	 * @param f
	 * @param obj �t�B�[���h�����������鑀�������I�u�W�F�N�g
	 * @param newValue ����������̒l
	 * @param holder �ێ��I�u�W�F�N�g�̃f�[�^�z���_�[
	 * @throws Exception ���͂��ꂽ�l���s���ȏꍇ�ɃX���[����܂�
	 */
	public static void updateField(Field f, Object obj, String newValue, ObjectHolder holder)
			throws Exception {
		Object newObject = convertStringToObject(f.getGenericType(), newValue, holder);
		f.setAccessible(true);
		f.set(obj, newObject);
	}

	/* =======================================================================
	 *   ���\�b�h
	 * ======================================================================= */

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g�������\�b�h�̈ꗗ��Ԃ��܂�
	 * @param obj
	 * @return
	 */
	public static Method[] getMethods(Object obj) {
		HashSet<Method> methodSet = new HashSet<Method>();
		methodSet.addAll(Arrays.asList(obj.getClass().getMethods()));
		methodSet.addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));
		Method[] methods = new Method[methodSet.size()];
		methodSet.toArray(methods);
		Arrays.sort(methods, new MemberComparator()); // �\�[�g
		return methods;
	}

	/**
	 * �w�肳�ꂽ���\�b�h�̏��𕶎���Ƃ��ĕԂ��܂�
	 * @param m
	 * @return
	 */
	public static String methodToString(Method m) {
		StringBuilder ret = new StringBuilder();
		String modif = Modifier.toString(m.getModifiers()); // �C���q
		ret.append(modif);
		if (modif.length() > 0) {
			ret.append(" ");
		}
		ret.append(m.getReturnType().getSimpleName()); // �߂�l
		ret.append(" ");
		ret.append(m.getName()); // ���O
		ret.append(argsToString(m.getParameterTypes())); // ����
		ret.append(exceptionToString(m.getExceptionTypes())); // ��O
		return ret.toString();
	}

	/**
	 * �w�肳�ꂽ���\�b�h���Ăяo���A�߂�l�̃I�u�W�F�N�g��Ԃ��܂�<br>
	 * @param m ���\�b�h
	 * @param obj ���\�b�h�Ăяo�����s���I�u�W�F�N�g
	 * @param args �Ăяo�����̈����i�J���}��؂�Ŏw��j
	 * @param holder �ێ��I�u�W�F�N�g�̃f�[�^�z���_�[
	 * @throws Throwable 
	 */
	public static Object invokeMethod(Method m, Object obj, String args, ObjectHolder holder)
			throws Throwable {
		Type[] paramTypes = m.getGenericParameterTypes();
		if (paramTypes.length == 0 && args.length() != 0) {
			throw new UserInputException("�����̐����s���Ȃ��߃��\�b�h�Ăяo���𒆎~���܂���");
		}

		Object[] paramObject = null;

		if (paramTypes.length > 0) {
			String[] paramStrings = args.split(",");
			if (paramStrings.length != paramTypes.length) {
				throw new UserInputException("�����̐����s���Ȃ��߃��\�b�h�Ăяo���𒆎~���܂���");
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
	 *   �z��
	 * ======================================================================= */

	/**
	 * �z��I�u�W�F�N�g�𐶐����܂�
	 * @param cls
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	public static Object createNewArray(String type, String size) throws Exception {
		Class<?> cls;

		// �N���X���̎擾
		// ��{�f�[�^�^�̏ꍇ
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
		// �Q�ƌ^�̏ꍇ
		else {
			cls = Class.forName(type);
		}

		// �T�C�Y�̎擾
		String[] sizeSplit = size.split(",");
		int length = sizeSplit.length;
		if (length > 2) {
			throw new UserInputException("�z��̎�����2�����܂łŎw�肵�Ă�������");
		}

		// 1�����z��̏ꍇ
		if (length == 1) {
			try {
				int dimension = Integer.parseInt(sizeSplit[0]);
				return Array.newInstance(cls, dimension);
			} catch (NumberFormatException e) {
				throw new UserInputException("�z��̃T�C�Y�͐��l�œ��͂��Ă�������");
			}
		}
		// 2�����z��̏ꍇ
		else if (length == 2) {
			try {
				int[] dimension = new int[2];
				dimension[0] = Integer.parseInt(sizeSplit[0]);
				dimension[1] = Integer.parseInt(sizeSplit[1]);
				return Array.newInstance(cls, dimension);
			} catch (NumberFormatException e) {
				throw new UserInputException("�z��̃T�C�Y�͐��l�œ��͂��Ă�������");
			}
		}
		// ���̑�
		else {
			throw new UserInputException("�z��̎�����2�����܂łŎw�肵�Ă�������");
		}
	}

	/**
	 * �w�肳�ꂽ�z��̗v�f�̍X�V�l���擾���܂�
	 * @param arr �z��
	 * @param index ����������index
	 * @param newValue ����������̒l
	 * @param holder �ێ��I�u�W�F�N�g�̃f�[�^�z���_�[
	 * @throws Exception ���͂��ꂽ�l���s���ȏꍇ�ɃX���[����܂�
	 */
	public static Object getUpdateArrayItem(Object arr, String newValue, ObjectHolder holder)
			throws Exception {
		return convertStringToObject(arr.getClass(), newValue, holder);
	}

	/**
	 * �w�肳�ꂽ�z�񂪊�{�f�[�^�^�̔z�񂩂��������܂�<br>
	 * 2�����z��܂őΉ����Ă��܂�
	 * @param arr
	 * @return ��{�f�[�^�^�̏ꍇ��true�A����ȊO��false
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
	 * �w�肳�ꂽ�z��̎�����Ԃ��܂�<br>
	 * 2�����z��܂őΉ����Ă��܂�
	 * @param arr
	 * @return
	 */
	public static int getArrayDimension(Object arr) {
		String clsName = arr.getClass().getSimpleName();
		// �N���X���Ɋ܂܂��[]�̐��ɂ���Ĕ��肷��
		return (clsName.length() - clsName.replaceAll("\\[\\]", "").length()) / 2;
	}

	/* =======================================================================
	 *   private���\�b�h
	 * ======================================================================= */

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

	/**
	 * �R���X�g���N�^�A���\�b�h�̈�����\���������Ԃ��܂�
	 * @param types �����̈ꗗ
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
	 * �w�肳�ꂽ������ɑΉ�����I�u�W�F�N�g���擾���܂�
	 * @param type
	 * @param value
	 * @param holder
	 * @return �Ή�����I�u�W�F�N�g�i�I�u�W�F�N�g��������Ȃ��ꍇ��null�j
	 * @throws UserInputException
	 */
	private static Object convertStringToObject(Type type, String value, ObjectHolder holder)
			throws Exception {
		// ��{�f�[�^�^�̏ꍇ
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
				return Long.valueOf(value);

			} else if (type.equals(Double.class) || type.equals(double.class)
					|| type.equals(Double[].class) || type.equals(double[].class)
					|| type.equals(Double[][].class) || type.equals(double[].class)) {
				return Double.valueOf(value);

			} else if (type.equals(Float.class) || type.equals(float.class)
					|| type.equals(Float[].class) || type.equals(float[].class)
					|| type.equals(Float[][].class) || type.equals(float[][].class)) {
				return Float.valueOf(value);

				// String�^�̏ꍇ
			} else if (type.equals(String.class) || type.equals(String[].class)
					|| type.equals(String[][].class)) {
				return convertString(value);

			} else {
				return value;
			}
		}

		// �Q�ƌ^�̏ꍇ�i���ɕێ�����Ă���I�u�W�F�N�g����w�肳�ꂽ�ꍇ�j
		else {
			return holder.getObject(value);
		}
	}

	/**
	 * �����Ɏw�肳�ꂽ�������char�^�ɕϊ����܂�<br>
	 * ������� '' �ň͂܂�Ă���K�v������܂�
	 * @param value
	 * @return
	 * @throws UserInputException 
	 */
	private static char convertCharacter(String value) throws UserInputException {
		if (value.charAt(0) != '\'' || value.charAt(value.length() - 1) != '\'') {
			throw new UserInputException("���͂��ꂽ�l���s���ł��Fchar�^�� \'\' �ň͂�ł�������");
		} else {
			// �ŏ��ƍŌ��''����菜��
			String charStr = value.substring(1, value.length() - 1);
			// �G�X�P�[�v�V�[�P���X
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
					throw new UserInputException("���͂��ꂽ�l���s���ł��Fchar�^�ɕϊ��ł��Ȃ��l�ł�");
				}
			} else {
				if (charStr.length() != 1) {
					throw new UserInputException("���͂��ꂽ�l���s���ł��Fchar�^�ɕϊ��ł��Ȃ��l�ł�");
				} else {
					return charStr.charAt(0);
				}
			}
		}
	}

	/**
	 * �����Ɏw�肳�ꂽ������̃G�X�P�[�v�V�[�P���X��ϊ����܂�<br>
	 * @param value
	 * @return
	 * @throws UserInputException
	 */
	private static String convertString(String value) throws UserInputException {
		if (value.charAt(0) != '\"' || value.charAt(value.length() - 1) != '\"') {
			throw new UserInputException("���͂��ꂽ�l���s���ł��FString�^�� \"\" �ň͂�ł�������");
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
 * �t�B�[���h�A���\�b�h���\�[�g���郋�[�����`���Ă���N���X�ł�
 */
class MemberComparator implements Comparator<Member> {
	@Override
	public int compare(Member m1, Member m2) {
		String s1 = m1.getName();
		String s2 = m2.getName();
		return s1.compareTo(s2);
	}
}
