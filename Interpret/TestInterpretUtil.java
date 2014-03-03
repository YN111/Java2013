import java.lang.reflect.*;

import junit.framework.TestCase;

import org.junit.Test;

public class TestInterpretUtil extends TestCase {

	ObjectHolder mHolder = new ObjectHolder();

	@Test
	public void testCreateNewObject() {
		try {
			Constructor<?> con = Integer.class.getConstructor(int.class);
			Object obj = InterpretUtil.createNewObject(con, "100", mHolder);
			assertEquals(obj.getClass(), Integer.class);
		} catch (Throwable e) {
			throw new AssertionError();
		}

		// 例外
		try {
			Constructor<?> con = Integer.class.getConstructor(String.class);
			InterpretUtil.createNewObject(con, "\"test\"", mHolder);
			fail();
		} catch (NumberFormatException e) { // e.getCauseの値が返されているかテスト
		} catch (Throwable e) {
			fail();
		}

	}

	@Test
	public void testGetFieldValue() {
		try {
			Integer i = new Integer(10);
			Field f = i.getClass().getDeclaredField("value");
			Object obj = InterpretUtil.getFieldValue(f, i);
			assertEquals(obj, 10);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testUpdateField() {
		try {
			Integer i = new Integer(10);
			Field f = i.getClass().getDeclaredField("value");
			InterpretUtil.updateField(f, i, "100", mHolder); // private finalを変更
			Object obj = InterpretUtil.getFieldValue(f, i);
			assertEquals(obj, 100);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testInvokeMethod() {
		try {
			Integer i = new Integer(10);
			Method m = i.getClass().getMethod("toString");
			Object obj = InterpretUtil.invokeMethod(m, i, "", mHolder);
			assertEquals(obj, "10");
		} catch (Throwable e) {
			fail();
		}

		// 例外
		try {
			Integer i = new Integer(10);
			Method m = i.getClass().getMethod("toString");
			InterpretUtil.invokeMethod(m, i, "100", mHolder);
			fail();
		} catch (UserInputException e) {
		} catch (Throwable e) {
			fail();
		}

		// 例外
		try {
			Integer i = new Integer(10);
			Method m = i.getClass().getMethod("parseInt", String.class);
			InterpretUtil.invokeMethod(m, i, "\"test\"", mHolder);
			fail();
		} catch (NumberFormatException e) { // e.getCauseの値が返されているかテスト
		} catch (Throwable e) {
			fail();
		}
	}

	@Test
	public void testCreateNewArray() {
		try {
			assertEquals(InterpretUtil.createNewArray("boolean", "5").getClass(), boolean[].class);
			assertEquals(InterpretUtil.createNewArray("byte", "5").getClass(), byte[].class);
			assertEquals(InterpretUtil.createNewArray("char", "5").getClass(), char[].class);
			assertEquals(InterpretUtil.createNewArray("short", "5").getClass(), short[].class);
			assertEquals(InterpretUtil.createNewArray("int", "5").getClass(), int[].class);
			assertEquals(InterpretUtil.createNewArray("long", "5").getClass(), long[].class);
			assertEquals(InterpretUtil.createNewArray("float", "5").getClass(), float[].class);
			assertEquals(InterpretUtil.createNewArray("double", "5").getClass(), double[].class);
			assertEquals(InterpretUtil.createNewArray("java.lang.Integer", "5").getClass(),
					Integer[].class);
			assertEquals(Array.getLength(InterpretUtil.createNewArray("int", "5")), 5);
			Object arr = InterpretUtil.createNewArray("int", "2,3");
			assertEquals(Array.getLength(arr), 2);
			assertEquals(Array.getLength(Array.get(arr, 0)), 3);
			InterpretUtil.createNewArray("int", "2,3,3");
			fail();
		} catch (UserInputException e) {
		} catch (Throwable e) {
			fail();
		}
	}

	@Test
	public void testGetUpdateArrayItem() {
		try {
			int[] arrInt = new int[5];
			Integer[] arrInteger = new Integer[5];
			assertEquals(InterpretUtil.getUpdateArrayItem(arrInt, "10", mHolder), 10);
			assertEquals(InterpretUtil.getUpdateArrayItem(arrInteger, "10", mHolder), 10);
		} catch (Throwable e) {
			fail();
		}
	}

	@Test
	public void testGetArrayDimension() {
		int[] arrInt = new int[5];
		int[][] arrDoubleInt = new int[5][5];
		assertEquals(InterpretUtil.getArrayDimension(arrInt), 1);
		assertEquals(InterpretUtil.getArrayDimension(arrDoubleInt), 2);
	}

	@Test
	public void testConvertStringToObject() {
		Method m = null;
		try {
			m = InterpretUtil.class.getDeclaredMethod("convertStringToObject", Type.class,
					String.class, ObjectHolder.class);
		} catch (SecurityException e) {
			fail();
		} catch (NoSuchMethodException e) {
			fail();
		}
		m.setAccessible(true);
		
		String obj0 = "test";
		mHolder.addObject(obj0);

		try {
			assertEquals(m.invoke(InterpretUtil.class, boolean.class, "true", mHolder), true);
			assertEquals(m.invoke(InterpretUtil.class, boolean.class, "false", mHolder), false);
			assertEquals(m.invoke(InterpretUtil.class, byte.class, "1", mHolder), (byte) 1);
			assertEquals(m.invoke(InterpretUtil.class, short.class, "1", mHolder), (short) 1);
			assertEquals(m.invoke(InterpretUtil.class, int.class, "1", mHolder), 1);
			assertEquals(m.invoke(InterpretUtil.class, long.class, "1l", mHolder), 1L);
			assertEquals(m.invoke(InterpretUtil.class, long.class, "1L", mHolder), 1L);
			assertEquals(m.invoke(InterpretUtil.class, float.class, "1.0f", mHolder), 1.0f);
			assertEquals(m.invoke(InterpretUtil.class, float.class, "1.0F", mHolder), 1.0F);
			assertEquals(m.invoke(InterpretUtil.class, double.class, "1.0", mHolder), 1.0);
			assertEquals(m.invoke(InterpretUtil.class, char.class, "\'a\'", mHolder), 'a');
			assertEquals(m.invoke(InterpretUtil.class, String.class, "\"test\"", mHolder), "test");

			assertEquals(m.invoke(InterpretUtil.class, Object.class, "null", mHolder), null);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "true", mHolder), true);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "false", mHolder), false);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1", mHolder), 1);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1l", mHolder), 1L);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1L", mHolder), 1L);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1.0f", mHolder), 1.0f);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1.0F", mHolder), 1.0f);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "1.0", mHolder), 1.0);
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "\'a\'", mHolder), 'a');
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "\"test\"", mHolder), "test");
			
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "obj0", mHolder), "test");
			assertEquals(m.invoke(InterpretUtil.class, Object.class, "obj1", mHolder), "test");
			fail();
		} catch (IllegalArgumentException e) {
			fail();
		} catch (IllegalAccessException e) {
			fail();
		} catch (InvocationTargetException e) {
		}
	}

}
