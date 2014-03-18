import java.io.*;

public class EncryptOutputStream extends FilterOutputStream {

	private byte xorByte = 7;

	public EncryptOutputStream(OutputStream os) {
		super(os);
	}

	/**
	 * �o�C�g���Í������ď������݂܂�<br>
	 * �������ɂ�DecryptInputStream�N���X��read���\�b�h���g�p���܂�
	 */
	@Override
	public void write(int b) throws IOException {
		super.write(b ^ xorByte);
	}

	/**
	 * �o�C�g����Í������ď������݂܂�<br>
	 * �������ɂ�DecryptInputStream�N���X��read���\�b�h���g�p���܂�
	 */
	@Override
	public void write(byte[] bytes) throws IOException {
		for (byte b : bytes) {
			super.write(b ^ xorByte);
		}
	}

	/**
	 * �o�C�g��̎w�肳�ꂽ�͈͂��Í������ď������݂܂�<br>
	 * �������ɂ�DecryptInputStream�N���X��read���\�b�h���g�p���܂�
	 */
	@Override
	public void write(byte[] bytes, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			super.write(bytes[i] ^ xorByte);
		}
	}
}
