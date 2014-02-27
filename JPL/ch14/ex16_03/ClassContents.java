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
			// カレントクラスで宣言されているメンバは表示しない（重複を避ける）
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

			// 宣言されている全てのメンバを表示
			printMembers(c.getDeclaredFields());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getDeclaredMethods());

			// 継承されているpublicなメンバを表示
			printExtendsMembers(c.getFields(), c);
			printExtendsMembers(c.getConstructors(), c);
			printExtendsMembers(c.getMethods(), c);

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}
}
