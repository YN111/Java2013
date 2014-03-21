import java.io.*;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * コンストラクタ<br>
	 * DataInputStreamから状態を読み込みます
	 * @param dis
	 */
	public Attr(DataInputStream dis) {
		String name = null;

		try {
			// nameの設定
			byte[] nameByte = new byte[dis.readInt()];
			dis.read(nameByte);
			name = new String(nameByte);

			// valueの設定
			byte[] valueByte = new byte[dis.readInt()];
			dis.read(valueByte);
			ByteArrayInputStream bais = new ByteArrayInputStream(valueByte);
			ObjectInputStream ois = new ObjectInputStream(bais);
			this.value = ois.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	/**
	 * 引数に指定されたDataOutputStreamにオブジェクトの内容を書き込みます
	 * @param dos
	 */
	public void getStream(DataOutputStream dos) {
		try {
			// nameをバイト列に変換して書き込み
			byte[] nameByte = name.getBytes();
			int nameByteSize = nameByte.length;
			dos.writeInt(nameByteSize); // バイト配列のサイズ
			dos.writeBytes(name);

			// valueをバイト列に変換して書き込み
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			oos.close();
			baos.close();
			byte[] valueByte = baos.toByteArray();
			int valueByteSize = valueByte.length;
			dos.writeInt(valueByteSize); // バイト配列のサイズ
			dos.write(valueByte);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
