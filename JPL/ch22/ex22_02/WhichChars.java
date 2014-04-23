import java.util.HashSet;

public class WhichChars {
	private HashSet<Character> used = new HashSet<Character>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	public String toString() {
		String desc = "[";
		for (Character c : used) {
			desc += c;
		}
		return desc + "]";
	}
}
