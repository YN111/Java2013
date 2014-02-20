import junit.framework.TestCase;

public class Test extends TestCase {

	public void testBitCount() throws Exception {
		assertEquals(BitCount.bitCount1(Integer.MIN_VALUE), Integer.bitCount(Integer.MIN_VALUE));
		assertEquals(BitCount.bitCount1(-1), Integer.bitCount(-1));
		assertEquals(BitCount.bitCount1(0), Integer.bitCount(0));
		assertEquals(BitCount.bitCount1(1), Integer.bitCount(1));
		assertEquals(BitCount.bitCount1(Integer.MAX_VALUE), Integer.bitCount(Integer.MAX_VALUE));

		assertEquals(BitCount.bitCount2(Integer.MIN_VALUE), Integer.bitCount(Integer.MIN_VALUE));
		assertEquals(BitCount.bitCount2(-1), Integer.bitCount(-1));
		assertEquals(BitCount.bitCount2(0), Integer.bitCount(0));
		assertEquals(BitCount.bitCount2(1), Integer.bitCount(1));
		assertEquals(BitCount.bitCount2(Integer.MAX_VALUE), Integer.bitCount(Integer.MAX_VALUE));
	}

}