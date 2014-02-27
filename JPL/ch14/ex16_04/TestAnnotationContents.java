import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;

import junit.framework.TestCase;
import org.junit.*;

public class TestAnnotationContents extends TestCase {

	private ByteArrayOutputStream baos;
	private PrintStream ps;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		ps = System.out;
		System.setOut(new PrintStream(new BufferedOutputStream(baos)));
	}

	@After
	public void tearDown() {
		System.setOut(ps);
	}

	@Test
	public void testSyncCalc() {
		// クラス
		Class<Foo> cls = Foo.class;
		AnnotationContents.printAnnotation(cls);
		System.out.flush();
		String result = baos.toString();
		if (!(result.contains("@Sample1") && result.contains("@Sample2") && result
				.contains("@Sample3")))
			fail();
		baos.reset();

		// フィールド
		Field f = null;
		try {
			f = cls.getField("i");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		AnnotationContents.printAnnotation(f);
		System.out.flush();
		result = baos.toString();
		if (!(result.contains("@Sample1") && result.contains("@Sample2")))
			fail();
		baos.reset();

		// メソッド
		Method m = null;
		try {
			m = cls.getMethod("bar");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		AnnotationContents.printAnnotation(m);
		System.out.flush();
		result = baos.toString();
		if (!(result.contains("@Sample2") && result.contains("@Sample3")))
			fail();
	}
}

@Sample1
@Sample2
@Sample3
class Foo {
	@Sample1
	@Sample2
	public int i = 0;

	@Sample2
	@Sample3
	public void bar() {
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample1 {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample2 {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Sample3 {
}
