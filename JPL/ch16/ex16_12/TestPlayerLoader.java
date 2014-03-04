import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.junit.Test;
import junit.framework.TestCase;

public class TestPlayerLoader extends TestCase {

	@Test
	public void testFindResourse() {
		PlayerLoader pl = new PlayerLoader();
		assertEquals(pl.findResource("foo"), null);
		try {
			assertEquals(pl.findResource("test.book"), new URL(
					"file:/C:/Users/yutaka/Documents/Java2014_03/ex16_12/test.book"));
		} catch (MalformedURLException e) {
			fail();
		}
	}

	@Test
	public void testFindResourses() {
		PlayerLoader pl = new PlayerLoader();
		assertEquals(pl.findResources("foo"), null);

		Enumeration<URL> enu = pl.findResources("test");
		try {
			assertEquals(enu.nextElement(), new URL(
					"file:/C:/Users/yutaka/Documents/Java2014_03/ex16_12/test/test1.book"));
			assertEquals(enu.nextElement(), new URL(
					"file:/C:/Users/yutaka/Documents/Java2014_03/ex16_12/test/test2.book"));
			assertEquals(enu.nextElement(), new URL(
					"file:/C:/Users/yutaka/Documents/Java2014_03/ex16_12/test/test3.book"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			enu.nextElement();
			fail();
		} catch (NoSuchElementException e) {
		}
	}
}