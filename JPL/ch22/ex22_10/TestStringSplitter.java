import java.io.CharArrayReader;
import java.io.Reader;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestStringSplitter extends TestCase {

	@Test
	public void testSplit() {
		StringBuilder in = new StringBuilder();
		in.append("token1 token2 #comment1\n");
		in.append("#comment line\n");
		in.append("token3");

		Reader reader = new CharArrayReader(in.toString().toCharArray());
		List<String> list = StringSplitter.split(reader);

		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "token1");
		assertEquals(list.get(1), "token2");
		assertEquals(list.get(2), "token3");
	}

}
