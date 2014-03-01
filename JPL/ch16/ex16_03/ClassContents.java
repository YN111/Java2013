import java.lang.reflect.*;

public class ClassContents {
	
	public static void printMembers(Member[] mems) {
		for (Member m : mems) {
			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	public static void printExtendsMembers(Member[] mems, Class<?> c) {
		for (Member m : mems) {
			// �J�����g�N���X�Ő錾����Ă��郁���o�͕\�����Ȃ��i�d���������j
			if (m.getDeclaringClass() == c)
				continue;

			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String decl, String header) {
		return decl.replace(header, "");
	}

	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);

			// �錾����Ă���S�Ẵ����o��\��
			printMembers(c.getDeclaredFields());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getDeclaredMethods());

			// �p������Ă���public�ȃ����o��\��
			printExtendsMembers(c.getFields(), c);
			printExtendsMembers(c.getConstructors(), c);
			printExtendsMembers(c.getMethods(), c);

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}
}
