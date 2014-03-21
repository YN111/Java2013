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
	 * �R���X�g���N�^<br>
	 * DataInputStream�����Ԃ�ǂݍ��݂܂�
	 * @param dis
	 */
	public Attr(DataInputStream dis) {
		String name = null;

		try {
			// name�̐ݒ�
			byte[] nameByte = new byte[dis.readInt()];
			dis.read(nameByte);
			name = new String(nameByte);

			// value�̐ݒ�
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
	 * �����Ɏw�肳�ꂽDataOutputStream�ɃI�u�W�F�N�g�̓��e���������݂܂�
	 * @param dos
	 */
	public void getStream(DataOutputStream dos) {
		try {
			// name���o�C�g��ɕϊ����ď�������
			byte[] nameByte = name.getBytes();
			int nameByteSize = nameByte.length;
			dos.writeInt(nameByteSize); // �o�C�g�z��̃T�C�Y
			dos.writeBytes(name);

			// value���o�C�g��ɕϊ����ď�������
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			oos.close();
			baos.close();
			byte[] valueByte = baos.toByteArray();
			int valueByteSize = valueByte.length;
			dos.writeInt(valueByteSize); // �o�C�g�z��̃T�C�Y
			dos.write(valueByte);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
