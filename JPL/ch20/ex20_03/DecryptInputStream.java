import java.io.*;

public class DecryptInputStream extends FilterInputStream {

	private byte xorByte = 7;

	DecryptInputStream(InputStream in) {
		super(in);
	}

	/**
	 * EncryptOutputStreamクラスで暗号化されたバイトを復号化して読み込みます
	 */
	@Override
	public int read() throws IOException {
		int b = super.read();
		return b ^ xorByte;
	}

	/**
	 * EncryptOutputStreamクラスで暗号化されたバイトを復号化して読み込みます
	 */
	@Override
	public int read(byte[] buf) throws IOException {
		int ret = super.read(buf);
		for (byte b : buf) {
			b = (byte) (b ^ xorByte);
		}
		return ret;
	}

	/**
	 * EncryptOutputStreamクラスで暗号化されたバイトを復号化して読み込みます
	 */
	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		int ret = super.read(buf, off, len);
		for (int i = off; i < off + len; i++) {
			buf[i] = (byte) (buf[i] ^ xorByte);
		}
		return ret;
	}
}
