import java.util.BitSet;
import java.util.HashMap;
import java.util.Set;

public class WhichChars {
	private HashMap<Byte, BitSet> used = new HashMap<Byte, BitSet>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			Byte b = (byte) (c >> 8);

			BitSet bs = used.get(b);
			if (bs == null) {
				bs = new BitSet();
				used.put(b, bs);
			}

			bs.set(c & 0x00FF);
		}
	}

	public String toString() {
		String desc = "[";
		Set<Byte> usedSet = used.keySet();

		for (Byte upper : usedSet) {
			BitSet lower = used.get(upper);
			for (int i = lower.nextSetBit(0); i >= 0; i = lower.nextSetBit(i + 1)) {
				desc += (char) (upper << 8 | i);
			}
		}

		return desc + "]";
	}
}
