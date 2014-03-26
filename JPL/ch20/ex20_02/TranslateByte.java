import java.io.*;

public class TranslateByte extends FilterInputStream {

	private byte from;
	private byte to;

	/**
	 * コンストラクタ
	 * @param is
	 * @param from 変換元
	 * @param to 変換先
	 */
	TranslateByte(InputStream is, byte from, byte to) {
		super(is);
		this.from = from;
		this.to = to;
	}

	@Override
	public int read() throws IOException {
		int b = super.read();
		return b == from ? to : b;
	}

	@Override
	public int read(byte[] buf) throws IOException {
		int ret = super.read(buf);
		for (byte b : buf) {
			b = (b == from ? to : b);
		}
		return ret;
	}

	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		int ret = super.read(buf, off, len);
		for (int i = off; i < off + len; i++) {
			buf[i] = (buf[i] == from ? to : buf[i]);
		}
		return ret;
	}
}
