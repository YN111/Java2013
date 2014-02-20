import junit.framework.TestCase;

public class SortHarnessTest extends TestCase {
	public void testSort() {
		// ���l�̃\�[�g
		Object[] intData = { 3, 2, 8, 4, 3 }; // �\�[�g��
		Object[] sortIntData = { 2, 3, 3, 4, 8 }; // �\�[�g��
		SortHarness bsort = new SimpleSortHarness();
		SortMetrics metrics = bsort.sort(intData); // �\�[�g���s
		System.out.println("===== Integer =====");
		for (int i = 0; i < 5; i++) {
			assertEquals(intData[i], sortIntData[i]);
			System.out.println(intData[i]);
		}
		System.out.println();

		// ������̃\�[�g
		Object[] stringData = { "Bear", "Animal", "Elephant", "Cat", "Dog" }; // �\�[�g��
		Object[] sortStringData = { "Animal", "Bear", "Cat", "Dog", "Elephant" }; // �\�[�g��
		metrics = bsort.sort(stringData);
		System.out.println("===== String =====");
		for (int i = 0; i < 5; i++) {
			assertEquals(stringData[i], sortStringData[i]);
			System.out.println(stringData[i]);
		}
		System.out.println();

		// MyObject�̃\�[�g
		Object[] myObj = { new MyObject(1, "One"), new MyObject(8, "Eight"),
				new MyObject(5, "Five"), new MyObject(10, "Ten"),
				new MyObject(2, "Two"), };
		Object[] sortMyObj = { new MyObject(1, "One"), new MyObject(2, "Two"),
				new MyObject(5, "Five"), new MyObject(8, "Eight"),
				new MyObject(10, "Ten"), };
		metrics = bsort.sort(myObj);
		System.out.println("===== MyObject =====");
		for (int i = 0; i < 5; i++) {
			System.out.println(myObj[i]);
			assertEquals(myObj[i], sortMyObj[i]);
		}
	}
}

class MyObject implements Comparable {
	private int data1;
	private String data2;

	MyObject(int data1, String data2) {
		this.data1 = data1;
		this.data2 = data2;
	}

	public int getData1() {
		return data1;
	}

	public String getData2() {
		return data2;
	}

	/**
	 * data1�̒l�̏����Ɋ�Â��ăI�u�W�F�N�g���r����
	 */
	public int compareTo(Object obj) {
		if (this.getData1() == ((MyObject) obj).getData1()) {
			return 0;
		} else {
			return (this.getData1() < ((MyObject) obj).getData1() ? -1 : 1);
		}
	}

	public String toString() {
		return data1 + ": " + data2;
	}

	public boolean equals(Object obj) {
		return ((this.getData1() == ((MyObject) obj).getData1())
				&& (this.getData2() == ((MyObject) obj).getData2()) ? true
				: false);
	}
}