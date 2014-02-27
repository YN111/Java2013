import java.lang.annotation.*;
import java.lang.reflect.*;

public class AnnotationContents {

	/**
	 * �w�肳�ꂽ�^�ɓK�p����Ă���S�ẴA�m�e�[�V������\�����܂�
	 * @param cls
	 */
	public static void printAnnotation(AnnotatedElement element) {
		Annotation[] ants = element.getAnnotations();
		for (Annotation ant : ants)
			System.out.println(ant);
	}

}
