import java.io.*;

public class EncryptOutputStream extends FilterOutputStream {

	private byte xorByte = 7;

	public EncryptOutputStream(OutputStream os) {
		super(os);
	}

	/**
	 * バイトを暗号化して書き込みます<br>
	 * 復号化にはDecryptInputStreamクラスのreadメソッドを使用します
	 */
	@Override
	public void write(int b) throws IOException {
		super.write(b ^ xorByte);
	}

	/**
	 * バイト列を暗号化して書き込みます<br>
	 * 復号化にはDecryptInputStreamクラスのreadメソッドを使用します
	 */
	@Override
	public void write(byte[] bytes) throws IOException {
		for (byte b : bytes) {
			super.write(b ^ xorByte);
		}
	}

	/**
	 * バイト列の指定された範囲を暗号化して書き込みます<br>
	 * 復号化にはDecryptInputStreamクラスのreadメソッドを使用します
	 */
	@Override
	public void write(byte[] bytes, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			super.write(bytes[i] ^ xorByte);
		}
	}
}
