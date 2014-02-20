// ex01_15 (P25)

import junit.framework.TestCase;

public class LookupTest extends TestCase{
	public void testFind() {
		newLookup lookup = new newSimpleLookup();
        lookup.add("A", 1);
        lookup.add("B", 3);
        lookup.add("C", 5);
        assertEquals(lookup.find("C"),5);
        assertEquals(lookup.find("B"),3);
        assertEquals(lookup.find("A"),1);
        lookup.remove("B");
        assertEquals(lookup.find("B"),null);
        lookup.remove("F");	// エラーメッセージが出るかチェック
    }
}
