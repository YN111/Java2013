import java.util.ListIterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestABLListIterator extends TestCase {

	private ArrayBunchList<String> abl;

	@Before
	public void setUp() {
		String[][] arr = new String[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i][j] = String.valueOf(i * 5 + j);
			}
		}
		abl = new ArrayBunchList<String>(arr);
	}

	@Test
	public void testNext() {
		ListIterator<String> ite = abl.listIterator();
		for (int i = 0; i < 25; i++) {
			assertTrue(ite.hasNext());
			assertEquals(ite.nextIndex(), i);
			assertEquals(ite.next(), String.valueOf(i));
		}

		// これ以上要素がないことを確認
		assertFalse(ite.hasNext());
		assertEquals(ite.nextIndex(), abl.size());
		try {
			ite.next();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	@Test
	public void testPrevious() {
		ListIterator<String> ite = abl.listIterator();

		// これ以前に要素がないことを確認
		assertFalse(ite.hasPrevious());
		assertEquals(ite.previousIndex(), -1);
		try {
			ite.previous();
			fail();
		} catch (NoSuchElementException e) {
		}

		// イテレータを最後まで進める
		while (ite.hasNext()) {
			ite.next();
		}

		for (int i = 24; i >= 0; i--) {
			assertTrue(ite.hasPrevious());
			assertEquals(ite.previousIndex(), i);
			assertEquals(ite.previous(), String.valueOf(i));
		}

		// これ以前に要素がないことを確認
		assertFalse(ite.hasPrevious());
		assertEquals(ite.previousIndex(), -1);
		try {
			System.out.println(ite.previous());
			fail();
		} catch (NoSuchElementException e) {
		}
	}

}
