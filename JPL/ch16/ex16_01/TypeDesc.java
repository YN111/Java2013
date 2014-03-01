import java.lang.reflect.*;

public class TypeDesc {

	private java.io.PrintStream out = System.out;
	private static String[] basic = { "class", "interface", "enum", "annotation" };
	private static String[] supercl = { "extends", "implements" };
	private static String[] iFace = { null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		// �ċA�Ăяo�����~�F�X�[�p�[�^�C�v�����݂��Ȃ� or �X�[�p�[�^�C�v��Object
		if (type == null || type == Object.class)
			return;

		// Type��Class�I�u�W�F�N�g�ɕϊ�����
		Class<?> cls = null;
		if (type instanceof Class<?>)
			cls = (Class<?>) type;
		else if (type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		else
			throw new Error("Unexpected non-class type");

		// ���̌^��\��
		for (int i = 0; i < depth; i++)
			out.print(" ");
		int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2 : cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		// ����΁A�W�F�l���b�N�^�p�����[�^��\��
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		} else
			out.println();

		// ���̃N���X���������Ă���S�ẴC���^�t�F�[�X��\��
		Type[] interfaces = cls.getGenericInterfaces();
		for (Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}

		// �X�[�p�[�N���X�ɑ΂��čċA
		printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}

	public static void main(String[] args) {
		TypeDesc desc = new TypeDesc();
		for (String name : args) {
			try {
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (ClassNotFoundException e) {
				System.err.println(e);
			}
		}
	}
}
