// ex01_16 (P29)

import java.io.IOException;

public class BadDataSetException extends Exception {
	private String dataset;
	private IOException e;
	
	// コンストラクタ
	BadDataSetException(String dataset, IOException e) {
		this.dataset = dataset;
		this.e = e;
	}
	
	public String getDataset() {
		return dataset;
	}
	
	public IOException getDetail() {
		return e;
	}
}