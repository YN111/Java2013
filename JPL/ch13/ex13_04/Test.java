import java.util.ArrayList;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void testStringListConverter() {
		StringListConverter slc = new StringListConverter();

		// 正常系
		String items = "Boolean true\n" + "Byte 1\n" + "Short 1\n" + "Integer 1\n" + "Long 1\n"
				+ "Float 1.0\n" + "Double 1.0\n" + "Character c";
		slc.add(items);
		ArrayList<Object> list = slc.getList();

		assertEquals(list.get(0).getClass(), Boolean.class);
		assertEquals(list.get(0), true);
		assertEquals(list.get(1).getClass(), Byte.class);
		assertEquals(list.get(1), (byte) 1);
		assertEquals(list.get(2).getClass(), Short.class);
		assertEquals(list.get(2), (short) 1);
		assertEquals(list.get(3).getClass(), Integer.class);
		assertEquals(list.get(3), 1);
		assertEquals(list.get(4).getClass(), Long.class);
		assertEquals(list.get(4), 1L);
		assertEquals(list.get(5).getClass(), Float.class);
		assertEquals(list.get(5), 1.0f);
		assertEquals(list.get(6).getClass(), Double.class);
		assertEquals(list.get(6), 1.0);

		// エラー系
		items = "Bool ean true";	// 引数にスペースが複数
		try {
			slc.add(items);
			fail();
		} catch (IllegalArgumentException e) {
		}
		
		items = "Booleantrue";	// 引数にスペースがない
		try {
			slc.add(items);
			fail();
		} catch (IllegalArgumentException e) {
		}
		
		items = "String str";	// typeの値が不正
		try {
			slc.add(items);
			fail();
		} catch (IllegalArgumentException e) {
		}
		
		items = "Character char";	// valueの値が不正
		try {
			slc.add(items);
			fail();
		} catch (IllegalArgumentException e) {
		}
		
		items = "Integer f";	// valueの値が不正
		try {
			slc.add(items);
			fail();
		} catch (NumberFormatException e) {
		}
	}
}