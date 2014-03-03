import java.awt.*;

import javax.swing.*;

public class DialogUtil {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
	private static final Font LARGE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
	private static final Font PLAIN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

	/**
	 * �I�u�W�F�N�g�̐����ɐ��������ꍇ�̃_�C�A���O��\�����܂�<br>
	 * �I�u�W�F�N�g��ێ����邩��I������_�C�A���O��\�����܂�
	 * @param parent �e�R���|�[�l���g
	 * @return �ێ�����ꍇ��true, �ێ����Ȃ��ꍇ��false
	 */
	public static boolean showObjectKeepSelectDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("���̃I�u�W�F�N�g��ێ����܂����H"));
		pane.add(createPaddingPanel(WIDTH, 20, UIManager.getColor("control")));

		JPanel subPane = new JPanel();
		subPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		subPane.setPreferredSize(new Dimension(WIDTH - 10, 200));
		subPane.setBackground(Color.WHITE);

		subPane.add(new JLabel("�I�u�W�F�N�g��ێ�����ƈȉ��̂��Ƃ��ł���悤�ɂȂ�܂�"));
		subPane.add(createPaddingPanel(WIDTH, 15, Color.WHITE));

		JLabel label1 = new JLabel("�����\�b�h�Ăяo���̈����Ɏw��");
		label1.setFont(LARGE_FONT);
		subPane.add(label1);

		JTextArea txtArea1 = new JTextArea("�Ăяo�����͈����̗��ɃI�u�W�F�N�gID����͂��Ă�������");
		txtArea1.setFont(PLAIN_FONT);
		txtArea1.setLineWrap(true);
		txtArea1.setFocusable(false);
		txtArea1.setEditable(false);
		txtArea1.setPreferredSize(new Dimension(WIDTH, 30));
		subPane.add(txtArea1);
		subPane.add(createPaddingPanel(WIDTH, 10, Color.WHITE));

		JLabel label2 = new JLabel("���C�ӂ̃^�C�~���O�Ńt�B�[���h���������E���\�b�h�Ăяo��");
		label2.setFont(LARGE_FONT);
		subPane.add(label2);

		JTextArea txtArea2 = new JTextArea("�u�ێ����Ă���I�u�W�F�N�g�v�̗��̑ΏۃI�u�W�F�N�g���_�u���N���b�N���Ă�������");
		txtArea2.setFont(PLAIN_FONT);
		txtArea2.setLineWrap(true);
		txtArea2.setFocusable(false);
		txtArea2.setEditable(false);
		txtArea2.setPreferredSize(new Dimension(WIDTH - 20, 30));
		subPane.add(txtArea2);
		pane.add(subPane);

		int result = JOptionPane.showConfirmDialog(parent, pane, "�I�u�W�F�N�g�̐����ɐ������܂���",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// �u�͂��v���I�����ꂽ�ꍇ
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �z��̐����ɐ��������ꍇ�̃_�C�A���O��\�����܂�<br>
	 * �I�u�W�F�N�g��ێ����邩��I������_�C�A���O��\�����܂�
	 * @param parent �e�R���|�[�l���g
	 * @return �ێ�����ꍇ��true, �ێ����Ȃ��ꍇ��false
	 */
	public static boolean showArrayKeepSelectDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("���̔z���ێ����܂����H"));
		pane.add(createPaddingPanel(WIDTH, 20, UIManager.getColor("control")));

		JPanel subPane = new JPanel();
		subPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		subPane.setPreferredSize(new Dimension(WIDTH - 10, 200));
		subPane.setBackground(Color.WHITE);

		subPane.add(new JLabel("�z���ێ�����ƈȉ��̂��Ƃ��ł���悤�ɂȂ�܂�"));
		subPane.add(createPaddingPanel(WIDTH, 15, Color.WHITE));

		JLabel label1 = new JLabel("�����\�b�h�Ăяo���̈����Ɏw��");
		label1.setFont(LARGE_FONT);
		subPane.add(label1);

		JTextArea txtArea1 = new JTextArea("�Ăяo�����͈����̗��ɃI�u�W�F�N�gID����͂��Ă�������");
		txtArea1.setFont(PLAIN_FONT);
		txtArea1.setLineWrap(true);
		txtArea1.setFocusable(false);
		txtArea1.setEditable(false);
		txtArea1.setPreferredSize(new Dimension(WIDTH, 30));
		subPane.add(txtArea1);
		subPane.add(createPaddingPanel(WIDTH, 10, Color.WHITE));

		JLabel label2 = new JLabel("���C�ӂ̃^�C�~���O�Ŕz��𑀍�");
		label2.setFont(LARGE_FONT);
		subPane.add(label2);

		JTextArea txtArea2 = new JTextArea("�u�ێ����Ă���I�u�W�F�N�g�v�̗��̑ΏۃI�u�W�F�N�g���_�u���N���b�N���Ă�������");
		txtArea2.setFont(PLAIN_FONT);
		txtArea2.setLineWrap(true);
		txtArea2.setFocusable(false);
		txtArea2.setEditable(false);
		txtArea2.setPreferredSize(new Dimension(WIDTH - 20, 30));
		subPane.add(txtArea2);
		pane.add(subPane);

		int result = JOptionPane.showConfirmDialog(parent, pane, "�z��̐����ɐ������܂���",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// �u�͂��v���I�����ꂽ�ꍇ
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ���\�b�h�Ăяo���ɐ��������ꍇ�̃_�C�A���O��\�����܂�<br>
	 * �߂�l��void�^�̏ꍇ�͂�������Ăяo���܂�
	 * @param parent �e�R���|�[�l���g
	 * @param obj �������ꂽ�I�u�W�F�N�g
	 */
	public static void showMethodDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("�߂�l"));
		JTextArea retVal = new JTextArea("void");
		retVal.setFont(LARGE_FONT);
		retVal.setBackground(Color.WHITE);
		retVal.setPreferredSize(new Dimension(WIDTH - 10, 22));
		retVal.setFocusable(false);
		retVal.setEditable(false);
		pane.add(retVal);

		JOptionPane.showMessageDialog(parent, pane, "���\�b�h�̌Ăяo���ɐ������܂���",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * ���\�b�h�Ăяo���ɐ��������ꍇ�̃_�C�A���O��\�����܂�<br>
	 * �_�C�A���O��Ŗ߂�l��ێ����邩�I���ł��܂�
	 * @param msg �\�����郁�b�Z�[�W
	 * @param obj �������ꂽ�I�u�W�F�N�g
	 * @return �ێ�����ꍇ��true, �ێ����Ȃ��ꍇ��false
	 */
	public static boolean showMethodDialog(Component parent, Object obj) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("�߂�l"));
		JTextArea retVal = new JTextArea(obj.toString());
		retVal.setFont(LARGE_FONT);
		retVal.setBackground(Color.WHITE);
		retVal.setPreferredSize(new Dimension(WIDTH - 10, 22));
		retVal.setFocusable(false);
		retVal.setEditable(false);
		pane.add(retVal);
		pane.add(createPaddingPanel(WIDTH, 30, UIManager.getColor("control")));

		pane.add(new JLabel("���̃I�u�W�F�N�g��ێ����܂����H"));
		JTextArea msg = new JTextArea("�߂�l����{�f�[�^�^�̏ꍇ�̓��b�p�[�N���X�̃I�u�W�F�N�g���ێ�����܂�");
		msg.setFont(PLAIN_FONT);
		msg.setBackground(UIManager.getColor("control"));
		msg.setPreferredSize(new Dimension(WIDTH - 10, 30));
		msg.setLineWrap(true);
		msg.setFocusable(false);
		msg.setEditable(false);
		pane.add(msg);

		int result = JOptionPane.showConfirmDialog(parent, pane, "���\�b�h�̌Ăяo���ɐ������܂���",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// �u�͂��v���I�����ꂽ�ꍇ
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * �v�f�̕ێ��ɐ��������Ƃ��ɕ\������_�C�A���O��\�����܂�
	 * @param msg �\�����郁�b�Z�[�W
	 */
	public static void showItemKeepingDialog(Component parent, String msg) {
		JTextArea txtArea = new JTextArea();
		txtArea.setText(msg);
		txtArea.setLineWrap(true);
		txtArea.setFocusable(false);
		txtArea.setEditable(false);
		txtArea.setBackground(UIManager.getColor("control"));
		JScrollPane scrollpane = new JScrollPane(txtArea);
		scrollpane.setPreferredSize(DIMENSION);
		JOptionPane.showMessageDialog(parent, scrollpane, "�z��v�f��ێ����܂���", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * ��O���X���[���ꂽ�Ƃ��ɕ\������_�C�A���O��\�����܂�
	 * @param msg �\�����郁�b�Z�[�W
	 */
	public static void showExceptionDialog(Component parent, String msg) {
		JTextArea txtArea = new JTextArea();
		txtArea.setText(msg);
		txtArea.setLineWrap(true);
		txtArea.setFocusable(false);
		txtArea.setEditable(false);
		txtArea.setBackground(UIManager.getColor("control"));
		JScrollPane scrollpane = new JScrollPane(txtArea);
		scrollpane.setPreferredSize(DIMENSION);
		JOptionPane.showMessageDialog(parent, scrollpane, "��O���X���[����܂���", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * �]���p�̃p�l���𐶐����܂�
	 * @param width �]���̕�
	 * @param height �]���̍���
	 * @param color �w�i�F
	 * @return
	 */
	private static JPanel createPaddingPanel(int width, int height, Color color) {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(width, height));
		pane.setBackground(color);
		return pane;
	}
}
