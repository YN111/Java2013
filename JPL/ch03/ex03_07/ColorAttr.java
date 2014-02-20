public class ColorAttr extends Attr {
	private ScreenColor myColor; // �ϊ����ꂽ�F

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor(); // �F����ݒ�
	}

	public ColorAttr(String name) {
		this(name, "transparent"); // �f�t�H���g�J���[�Ƃ��ē�����ݒ�
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	@Override
	public Object setValue(Object newValue) {
		// �X�[�p�[�N���X��setValue���ŏ��ɍs��
		Object retval = super.setValue(newValue); // value���X�V
		decodeColor(); // myColor��ݒ�
		return retval;
	}

	/**
	 * �l���L�q�ł͂Ȃ�ScreenColor�ɐݒ肷��
	 */
	public ScreenColor setValue(ScreenColor newValue) {
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/**
	 * �ϊ����ꂽScreenColor�I�u�W�F�N�g��Ԃ�
	 */
	public ScreenColor getColor() {
		return myColor;
	}

	/**
	 * getValue()�œ�����L�q����ScreenColor��ݒ肷��
	 */
	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	/**
	 * name, value�Ƃ��ɒl����v�����true��Ԃ�
	 */
	public boolean equals(ColorAttr color) {
		if ((this.getName().equals(color.getName()))
				&& (this.getValue().equals(color.getValue())))
			return true;
		else
			return false;
	}

	/**
	 * name, value�̒l�Ɋ�Â��ăn�b�V���R�[�h��Ԃ�
	 */
	public int hashCode() {
		return (getName().hashCode() + getValue().hashCode()) / 2;
	}
}

class ScreenColor {
	private Object color;

	/**
	 * �F��ݒ肷��R���X�g���N�^
	 */
	ScreenColor(Object color) {
		this.color = color;
	}

	/**
	 * �F���擾����
	 */
	public Object getScreenColor() {
		return color;
	}

	@Override
	public int hashCode() {
		return getScreenColor().hashCode();
	}
}
