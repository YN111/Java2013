import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestShortStrings extends TestCase {

	private LinkedList<String> list;
	private ListIterator<String> it;
	private ShortStrings ss;

	@Before
	public void setUp() {
		list = new LinkedList<String>();
		list.add("test1");
		list.add("test1test2test3test4test5test6");
		list.add("test1test2");
		list.add("test1test2test3test4test5");
		list.add("test1test2test3");
		list.add("test1test2test3test4");
		list.add("test1test2test3test4");
		it = list.listIterator();
		ss = new ShortStrings(it, 16);
	}

	@Test
	public void testAdd() {
		try {
			ss.add("string");
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void testSet() {
		try {
			ss.set("string");
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void testHasNext() {
		assertTrue(ss.hasNext());
		assertTrue(ss.hasNext()); // 連続して呼ぶ
		ss.next();
		ss.next();
		ss.next();
		assertFalse(ss.hasNext());
	}

	@Test
	public void testHasPrevious() {
		assertFalse(ss.hasPrevious()); // 最初はfalse
		ss.next();
		ss.next();
		assertTrue(ss.hasPrevious());
		assertTrue(ss.hasPrevious()); // 連続して呼ぶ
	}

	@Test
	public void testNext() {
		assertEquals(ss.next(), "test1");
		assertEquals(ss.next(), "test1test2");
		assertEquals(ss.next(), "test1test2test3");
		try {
			ss.next();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	@Test
	public void testPrev() {
		try {
			ss.previous();
			fail();
		} catch (NoSuchElementException e) {
		}
		ss.next();
		ss.next();
		ss.next();
		assertEquals(ss.previous(), "test1test2test3");
		assertEquals(ss.previous(), "test1test2");
		assertEquals(ss.previous(), "test1");
	}

	@Test
	public void testRemove() {
		try {
			ss.remove();
		} catch (IllegalStateException e) {
		}

		assertEquals(ss.next(), "test1");
		assertEquals(ss.next(), "test1test2");
		ss.remove(); // "test1test2"がremove
		assertEquals(ss.next(), "test1test2test3");
		assertEquals(ss.previous(), "test1test2test3");
		ss.remove(); // "test1test2test3"がremove
		assertEquals(ss.previous(), "test1");
		assertEquals(ss.next(), "test1");
	}

	@Test
	public void testNextIndex() {
		assertEquals(ss.nextIndex(), 0);
		assertEquals(ss.next(), "test1");
		assertEquals(ss.nextIndex(), 2);
		assertEquals(ss.next(), "test1test2");
		assertEquals(ss.nextIndex(), 4);
		assertEquals(ss.next(), "test1test2test3");
		assertEquals(ss.nextIndex(), 7); // 次の要素がない
	}

	@Test
	public void testPreviousIndex() {
		ss.next();
		ss.next();
		ss.next();
		assertEquals(ss.previousIndex(), 4);
		assertEquals(ss.previous(), "test1test2test3");
		assertEquals(ss.previousIndex(), 2);
		assertEquals(ss.previous(), "test1test2");
		assertEquals(ss.previousIndex(), 0);
		assertEquals(ss.previous(), "test1");
		assertEquals(ss.previousIndex(), -1); // 前の要素がない
	}

}
