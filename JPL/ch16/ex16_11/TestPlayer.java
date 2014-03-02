import java.lang.reflect.*;
import org.junit.Test;
import junit.framework.TestCase;

public class TestPlayer extends TestCase {

	@Test
	public void testPlayer1() {
		Player1 p1 = new Player1();
		try {
			p1.play(null);
		} catch (NullPointerException e) {
		}

		boolean result[][] = null;
		try {
			Field f = p1.getClass().getDeclaredField("result");
			f.setAccessible(true);
			result = (boolean[][]) f.get(p1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (result[i][j]) {
					count++;
				}
			}
		}
		assertEquals(count, 4); // ○が4つあることを確認
	}

	@Test
	public void testPlayer2() {
		Player2 p2 = new Player2();
		try {
			p2.play(null);
		} catch (NullPointerException e) {
		}

		boolean result[][] = null;
		try {
			Field f = p2.getClass().getDeclaredField("result");
			f.setAccessible(true);
			result = (boolean[][]) f.get(p2);
		} catch (Exception e) {
		}

		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (result[i][j]) {
					count++;
				}
			}
		}
		assertEquals(count, 5); // ○が5つあることを確認
	}

}
