// ex01_16 (P29)

import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class BadDataSetExceptionTest extends TestCase {
	public void test() {
		MyUtilities util = new MyUtilities();
		try {
			util.inputDataSet();
		} catch (BadDataSetException e) {
			System.out.println("->" + e.getDetail());
			assertEquals(e.getDataset(), "notexist.dset");
			assertEquals(e.getDetail().toString(),
					"java.io.FileNotFoundException: notexist.dset (�w�肳�ꂽ�t�@�C����������܂���B)");
		}
	}
}

class MyUtilities {
	public void inputDataSet() throws BadDataSetException {
		String file = "notexist.dset"; // ���݂��Ȃ��t�@�C��
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadDataSetException(file, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				; // ����
			}
		}
	}
}
