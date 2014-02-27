import java.lang.annotation.*;
import java.lang.reflect.*;

public class AnnotationContents {

	/**
	 * 指定された型に適用されている全てのアノテーションを表示します
	 * @param cls
	 */
	public static void printAnnotation(AnnotatedElement element) {
		Annotation[] ants = element.getAnnotations();
		for (Annotation ant : ants)
			System.out.println(ant);
	}

}
