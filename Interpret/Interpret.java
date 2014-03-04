import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class Interpret extends JFrame implements MouseListener {
	private static final long serialVersionUID = -808225512344080975L;

	// �萔�t�B�[���h
	private static final int LEFT_PANEL_WIDTH = 260;
	private static final int LEFT_PANEL_HEIGHT = 315;
	private static final int LEFT_ITEM_WIDTH = LEFT_PANEL_WIDTH - 20;
	private static final int RIGHT_PANEL_WIDTH = 400;
	private static final int RIGHT_PANEL_HEIGHT = 390;
	private static final int RIGHT_ITEM_WIDTH = RIGHT_PANEL_WIDTH - 20;
	private static final Color BASE_COLOR = new Color(230, 230, 230);
	private static final Color LIGHT_YELLOW = new Color(255, 255, 150);
	private static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static final Font MESSAGE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private static final LineBorder GRAY_LINE_BORDER = new LineBorder(Color.GRAY);
	private static final Dimension DIMENSION_LEFT_TEXT = new Dimension(LEFT_ITEM_WIDTH - 10, 18);
	private static final Dimension DIMENSION_RIGHT_TEXT = new Dimension(RIGHT_ITEM_WIDTH - 10, 18);
	private static final Insets DEFAULT_INSETS = new Insets(5, 30, 0, 30);

	// �I�u�W�F�N�g�����p�l�����̃p�[�c
	private JTextField mTxtFldClassName;
	private JButton mBtnClassNameFix;
	private JList mListViewConstructor;
	private DefaultListModel mListModelConstructor;
	private JTextArea mTxtAreaConstructorArgs;
	private JButton mBtnConstructor;

	// �z�񐶐��p�l�����̃p�[�c
	private JTextField mTxtFldArrClassName;
	private JTextField mTxtFldArrSize;
	private JButton mBtnArrayCreation;

	// �ێ����Ă���I�u�W�F�N�g�̃��X�g�\��
	private JList mListViewObject;
	private DefaultListModel mListModelObject;

	// ���݂̃I�u�W�F�N�g�̕\��
	private JLabel mLabelTargetObject;

	// �t�B�[���h���������p�l�����̃p�[�c
	private JList mListViewField;
	private DefaultListModel mListModelField;
	private JTextField mTxtFldOldVal;
	private JTextField mTxtFldNewVal;
	private JButton mBtnField;

	// ���\�b�h�Ăяo���p�l�����̃p�[�c
	private JList mListViewMethod;
	private DefaultListModel mListModelMethod;
	private JTextArea mTxtAreaMethodArgs;
	private JButton mBtnMethod;

	// �z�񑀍�p�l�����̃p�[�c
	private JList mListViewArray;
	private DefaultListModel mListModelArray;
	private JTextField mTxtFldNewArrVal;
	private JButton mBtnArrayOperation;

	// ����ێ�
	private Object mTargetObject;
	private Object mTargetArray;
	private int mIndex;
	private Constructor<?>[] mConstructors;
	private Method[] mMethods;
	private Field[] mFields;

	// �o�^�ς݃I�u�W�F�N�g��ێ�����f�[�^�z���_�[
	private ObjectHolder mHolder = new ObjectHolder();

	/**
	 * �R���X�g���N�^<br>
	 * ��ʂ����������܂�
	 */
	public Interpret() {
		initFrame();
	}

	/**
	 * ��ʂ̏��������s���܂�
	 */
	private void initFrame() {
		// Frame�̏�����
		setTitle("Interpret");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// �x�[�X�p�l���̏�����
		JPanel panelBase = new JPanel();
		panelBase.setBackground(BASE_COLOR);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panelBase.setLayout(gbl);

		// �q�R���|�[�l���g��z�u
		// �e�L�X�g�u�V�K�쐬�v
		JLabel txtObjectCreation = createTitleLabel("�V�K�쐬", DIMENSION_LEFT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.insets = DEFAULT_INSETS;
		gbl.setConstraints(txtObjectCreation, gbc);

		// �V�K�쐬�^�u
		JTabbedPane tabCreation = createNewMakingTab();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbl.setConstraints(tabCreation, gbc);

		// �e�L�X�g�u�ێ����Ă���I�u�W�F�N�g�v
		JLabel txtRegisteredObject = createTitleLabel("�ێ����Ă���I�u�W�F�N�g", DIMENSION_LEFT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(30, 10, 0, 10);
		gbl.setConstraints(txtRegisteredObject, gbc);

		// �ێ��I�u�W�F�N�g�̃��X�g
		mListModelObject = new DefaultListModel();
		mListViewObject = new JList(mListModelObject);
		JScrollPane scrollPane = new JScrollPane(mListViewObject);
		scrollPane.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, 100));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(GRAY_LINE_BORDER);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.insets = DEFAULT_INSETS;
		gbl.setConstraints(scrollPane, gbc);

		// �e�L�X�g�u���݂̃I�u�W�F�N�g�v
		JLabel txtSelectedObject = createTitleLabel("���݂̃I�u�W�F�N�g", DIMENSION_RIGHT_TEXT);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbl.setConstraints(txtSelectedObject, gbc);

		// �I�u�W�F�N�g��
		JPanel paneSelectedObject = new JPanel();
		paneSelectedObject.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		paneSelectedObject.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, 28));
		paneSelectedObject.setBackground(LIGHT_YELLOW);
		mLabelTargetObject = new JLabel();
		mLabelTargetObject.setFont(TITLE_FONT);
		paneSelectedObject.add(mLabelTargetObject);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(paneSelectedObject, gbc);

		// �t�B�[���h�A���\�b�h�A�z��̑���^�u
		JTabbedPane tabOperation = createOperationTab();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 3;
		gbc.insets = new Insets(30, 10, 0, 10);
		gbl.setConstraints(tabOperation, gbc);

		// �`��
		panelBase.add(txtObjectCreation);
		panelBase.add(tabCreation);
		panelBase.add(txtRegisteredObject);
		panelBase.add(scrollPane);
		panelBase.add(txtSelectedObject);
		panelBase.add(paneSelectedObject);
		panelBase.add(tabOperation);
		getContentPane().add(panelBase);

		// ���X�i�[�o�^
		mBtnClassNameFix.addMouseListener(this);
		mBtnConstructor.addMouseListener(this);
		mBtnArrayCreation.addMouseListener(this);
		mListViewObject.addMouseListener(this);
		mListViewField.addMouseListener(this);
		mBtnField.addMouseListener(this);
		mBtnMethod.addMouseListener(this);
		mListViewArray.addMouseListener(this);
		mBtnArrayOperation.addMouseListener(this);

		super.setVisible(true);
	}

	/**
	 * �^�C�g���e�L�X�g�𐶐����܂�
	 * @param txt �e�L�X�g
	 * @param d �\���̃T�C�Y
	 * @return
	 */
	private JLabel createTitleLabel(String txt, Dimension d) {
		JLabel label = new JLabel();
		label.setBackground(BASE_COLOR);
		label.setFont(TITLE_FONT);
		label.setPreferredSize(d);
		label.setText(txt);
		return label;
	}

	/**
	 * ���b�Z�[�W�e�L�X�g�𐶐����܂�
	 * @param txt �e�L�X�g
	 * @return
	 */
	private JLabel createMessageLabel(String txt) {
		JLabel label = new JLabel(txt);
		label.setFont(MESSAGE_FONT);
		return label;
	}

	/**
	 * �I�u�W�F�N�g�A�z��̐V�K�쐬�^�u�i��ʍ���j�𐶐����܂�
	 * @return
	 */
	private JTabbedPane createNewMakingTab() {
		JTabbedPane tab = new JTabbedPane();
		tab.setFont(TITLE_FONT);
		tab.add("�I�u�W�F�N�g", createObjectCreationPanel());
		tab.add("�z��", createArrayCreationPanel());
		return tab;
	}

	/**
	 * �I�u�W�F�N�g�̐V�K�쐬�p�l���𐶐����܂�
	 * @return
	 */
	private JPanel createObjectCreationPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// �N���X�����̓t�B�[���h
		pane.add(createMessageLabel("�@�N���X������͂��Ă�������"));
		mTxtFldClassName = new JTextField();
		mTxtFldClassName.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		pane.add(mTxtFldClassName);

		// �N���X������{�^��
		mBtnClassNameFix = new JButton();
		mBtnClassNameFix.setText("����");
		JPanel subPane = new JPanel();
		subPane.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 35));
		subPane.setBackground(Color.WHITE);
		subPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		subPane.add(mBtnClassNameFix);
		subPane.add(mBtnClassNameFix);
		pane.add(subPane);

		// �R���X�g���N�^�̃��X�g
		pane.add(createMessageLabel("�A�R���X�g���N�^��I��ł�������"));
		mListModelConstructor = new DefaultListModel();
		mListViewConstructor = new JList(mListModelConstructor);
		JScrollPane scrollPane = new JScrollPane(mListViewConstructor);
		scrollPane.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 80));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 5));

		// ����
		pane.add(createMessageLabel("�B��������͂��Ă��������i�J���}��؂�j"));
		mTxtAreaConstructorArgs = new JTextArea();
		mTxtAreaConstructorArgs.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mTxtAreaConstructorArgs.setBorder(GRAY_LINE_BORDER);
		mTxtAreaConstructorArgs.setLineWrap(true);
		pane.add(mTxtAreaConstructorArgs);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 5));

		// ���s�{�^��
		mBtnConstructor = new JButton();
		mBtnConstructor.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mBtnConstructor.setText("�I�u�W�F�N�g����");
		pane.add(mBtnConstructor);

		return pane;
	}

	/**
	 * �z��̐V�K�쐬�p�l���𐶐����܂�
	 * @return
	 */
	private JPanel createArrayCreationPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// �^�����̓t�B�[���h
		pane.add(createMessageLabel("�@�^������͂��Ă�������"));
		mTxtFldArrClassName = new JTextField();
		mTxtFldArrClassName.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		pane.add(mTxtFldArrClassName);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 25));

		// �T�C�Y
		pane.add(createMessageLabel("�A�z��̃T�C�Y����͂��Ă�������"));
		mTxtFldArrSize = new JTextField();
		mTxtFldArrSize.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		mTxtFldArrSize.setBorder(GRAY_LINE_BORDER);
		pane.add(mTxtFldArrSize);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 140));

		// ���s�{�^��
		mBtnArrayCreation = new JButton();
		mBtnArrayCreation.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mBtnArrayCreation.setText("�z�񐶐�");
		pane.add(mBtnArrayCreation);

		return pane;
	}

	/**
	 * �t�B�[���h�A���\�b�h�A�z��Ɋւ��鑀����s��GUI���܂Ƃ߂��^�u�i��ʉE���j�𐶐����܂�
	 * @return
	 */
	private JTabbedPane createOperationTab() {
		JTabbedPane tab = new JTabbedPane();
		tab.setFont(TITLE_FONT);
		tab.add("�t�B�[���h��������", createFieldPanel());
		tab.add("���\�b�h�Ăяo��", createMethodPanel());
		tab.add("�z��̑���", createArrayPanel());
		return tab;
	}

	/**
	 * �t�B�[���h���������p�l���𐶐����܂�
	 * @return
	 */
	private JPanel createFieldPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// �t�B�[���h�̃��X�g
		pane.add(createMessageLabel("�@����������t�B�[���h��I�����Ă�������"));
		mListModelField = new DefaultListModel();
		mListViewField = new JList(mListModelField);
		// �I�𒆂̗v�f���ύX���ꂽ�ꍇ�̃��X�i�[�o�^
		mListViewField.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int fieldIndex = mListViewField.getSelectedIndex();
				if (fieldIndex < 0) {
					return;
				}
				try {
					String oldVal = InterpretUtil.getFieldValue(mFields[fieldIndex], mTargetObject)
							.toString();
					mTxtFldOldVal.setText(oldVal);
				} catch (NullPointerException e) {
					mTxtFldOldVal.setText("null");
				} catch (Exception e) {
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(mListViewField);
		scrollPane.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 200));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 10));

		// �ύX�O�̒l
		pane.add(createMessageLabel("�ύX�O�̒l"));
		mTxtFldOldVal = new JTextField();
		mTxtFldOldVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		mTxtFldOldVal.setFont(MESSAGE_FONT);
		mTxtFldOldVal.setEditable(false);
		mTxtFldOldVal.setBackground(Color.WHITE);
		mTxtFldOldVal.setBorder(GRAY_LINE_BORDER);
		pane.add(mTxtFldOldVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 5));

		// �ύX��̒l
		pane.add(createMessageLabel("�A�ύX��̒l����͂��Ă�������"));
		mTxtFldNewVal = new JTextField();
		mTxtFldNewVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		pane.add(mTxtFldNewVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// ���s�{�^��
		mBtnField = new JButton();
		mBtnField.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnField.setText("�����������s");
		pane.add(mBtnField);

		return pane;
	}

	/**
	 * ���\�b�h�Ăяo���p�l���𐶐����܂�
	 * @return
	 */
	private JPanel createMethodPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// ���\�b�h�̃��X�g
		pane.add(createMessageLabel("�@�Ăяo�����\�b�h��I�����Ă�������"));
		mListModelMethod = new DefaultListModel();
		mListViewMethod = new JList(mListModelMethod);
		JScrollPane scrollPane = new JScrollPane(mListViewMethod);
		scrollPane.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 200));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 10));

		// ����
		pane.add(createMessageLabel("�A��������͂��Ă��������i�J���}��؂�j"));
		mTxtAreaMethodArgs = new JTextArea();
		mTxtAreaMethodArgs.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 75));
		mTxtAreaMethodArgs.setBorder(GRAY_LINE_BORDER);
		mTxtAreaMethodArgs.setLineWrap(true);
		pane.add(mTxtAreaMethodArgs);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// ���s�{�^��
		mBtnMethod = new JButton();
		mBtnMethod.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnMethod.setText("�Ăяo�����s");
		pane.add(mBtnMethod);

		return pane;
	}

	/**
	 * �z�񑀍�p�̃p�l���𐶐����܂�
	 * @return
	 */
	private JPanel createArrayPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// �z��̃��X�g
		pane.add(createPaddingPanel(RIGHT_PANEL_WIDTH, 5));
		pane.add(createMessageLabel("�@���삷��z���I�����Ă�������"));
		pane.add(createMessageLabel("�@�_�u���N���b�N�ŗv�f��ێ��ł��܂�"));
		pane.add(createPaddingPanel(RIGHT_PANEL_WIDTH, 5));
		mListModelArray = new DefaultListModel();
		mListViewArray = new JList(mListModelArray);
		// �I�𒆂̗v�f���ύX���ꂽ�ꍇ�̃��X�i�[�o�^
		mListViewArray.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int index = mListViewArray.getSelectedIndex();
				if (index < 0) {
					return;
				}
				String objName = mLabelTargetObject.getText();
				String key;
				if (objName.contains("�y")) {
					key = objName.substring(objName.indexOf("�y") + 1, objName.indexOf("�z"));
				} else {
					key = null;
				}
				updateTargetArray(mTargetArray, key, index);
			}
		});
		JScrollPane scrollPane = new JScrollPane(mListViewArray);
		scrollPane.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 200));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// �ύX��̒l
		pane.add(createMessageLabel("�A�ύX��̔z��v�f����͂��Ă�������"));
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 5));
		mTxtFldNewArrVal = new JTextField();
		mTxtFldNewArrVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		pane.add(mTxtFldNewArrVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// ���s�{�^��
		mBtnArrayOperation = new JButton();
		mBtnArrayOperation.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnArrayOperation.setText("�z��v�f�̍X�V");
		pane.add(mBtnArrayOperation);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 20));

		JTextArea msg = new JTextArea("�t�B�[���h�̏��������ƃ��\�b�h�̌Ăяo���͂��ꂼ���p�̃^�u����s���Ă�������");
		msg.setFont(MESSAGE_FONT);
		msg.setBackground(new Color(200, 255, 200));
		msg.setLineWrap(true);
		msg.setFocusable(false);
		msg.setEditable(false);
		msg.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 35));
		pane.add(msg);

		return pane;
	}

	/**
	 * �]���p�̃p�l���𐶐����܂�
	 * @param width �]���̕�
	 * @param height �]���̍���
	 * @return
	 */
	private JPanel createPaddingPanel(int width, int height) {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(width, height));
		pane.setBackground(Color.WHITE);
		return pane;
	}

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g��ێ����܂�
	 * @param obj
	 * @return �Ăяo���p�̃L�[
	 */
	private String keepObject(Object obj) {
		String key = mHolder.addObject(obj);
		mListModelObject.addElement("ID�y" + key + "�z  " + obj.getClass().getName());
		return key;
	}

	/**
	 * �^�[�Q�b�g�I�u�W�F�N�g���X�V���܂�
	 * @param obj �I�u�W�F�N�g
	 * @param key �I�u�W�F�N�g�̃L�[�i�I�u�W�F�N�g��ێ����Ȃ��ꍇ��null�j
	 */
	private void updateTargetObject(Object obj, String key) {
		mTargetObject = obj;
		mTargetArray = null;

		// �I�u�W�F�N�g����\��
		if (key == null) {
			mLabelTargetObject.setText(mTargetObject.getClass().getName());
		} else {
			mLabelTargetObject.setText("�y" + key + "�z  " + mTargetObject.getClass().getName());
		}

		// �t�B�[���h���擾���ĕ\��
		mFields = InterpretUtil.getFields(mTargetObject);
		mListModelField.clear();
		for (Field f : mFields) {
			try {
				mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// ���\�b�h���擾���ĕ\��
		mMethods = InterpretUtil.getMethods(mTargetObject);
		mListModelMethod.clear();
		for (Method m : mMethods) {
			mListModelMethod.addElement(InterpretUtil.methodToString(m));
		}

		// �z��̗v�f���폜����
		mListModelArray.clear();
		mTxtFldNewArrVal.setText("");
	}

	/**
	 * �^�[�Q�b�g�z����X�V���܂�
	 * @param obj �I�u�W�F�N�g
	 * @param key �I�u�W�F�N�g�̃L�[�i�I�u�W�F�N�g��ێ����Ȃ��ꍇ��null�j
	 */
	private void updateTargetArray(Object arr, String key, int index) {
		mTargetArray = arr;
		int dimension = InterpretUtil.getArrayDimension(mTargetArray);
		String clsName;
		if (InterpretUtil.isPrimitiveArray(arr)) {
			clsName = mTargetArray.getClass().getSimpleName();
		} else {
			clsName = mTargetArray.getClass().getName();
		}

		if (Array.getLength(mTargetArray) > 0) {
			// �^�[�Q�b�g�I�u�W�F�N�g�̍X�V
			// 1�����z��̏ꍇ
			if (dimension == 1) {
				mTargetObject = Array.get(mTargetArray, index);
				String viewClsName;
				if (InterpretUtil.isPrimitiveArray(arr)) {
					viewClsName = clsName.substring(0, clsName.length() - 2);
				} else {
					viewClsName = clsName + " ";
				}

				// �I�u�W�F�N�g����\��
				if (key == null) {
					mLabelTargetObject.setText(viewClsName + "[" + index + "]");
				} else {
					mLabelTargetObject.setText("�y" + key + "�z  " + viewClsName + "[" + index + "]");
				}
			}

			// 2�����z��̏ꍇ
			else if (dimension == 2) {
				if (Array.getLength(Array.get(mTargetArray, 0)) > 0) {
					int firstIndex = index / Array.getLength(Array.get(mTargetArray, 0));
					int secondIndex = index % Array.getLength(Array.get(mTargetArray, 0));
					mTargetObject = Array.get(Array.get(mTargetArray, firstIndex), secondIndex);
					String viewClsName;
					if (InterpretUtil.isPrimitiveArray(arr)) {
						viewClsName = clsName.substring(0, clsName.length() - 4);
					} else {
						viewClsName = clsName + " ";
					}

					// �I�u�W�F�N�g����\��
					if (key == null) {
						mLabelTargetObject.setText(viewClsName + "[" + firstIndex + "]["
								+ secondIndex + "]");
					} else {
						mLabelTargetObject.setText("�y" + key + "�z  " + viewClsName + "["
								+ firstIndex + "][" + secondIndex + "]");
					}
				} else {
					// �����̔z��̗v�f����0
					updateZeroArrayObject(clsName, key);
				}
			}

			else {
				throw new AssertionError();
			}

		} else {
			// 1�����z��܂���2�����z��̊O���̔z��̗v�f����0
			updateZeroArrayObject(clsName, key);
		}

		// �t�B�[���h�ƃ��\�b�h�̕\�����X�V
		if (InterpretUtil.isPrimitiveArray(mTargetArray)) {
			// ��{�f�[�^�^�̔z��̏ꍇ
			mListModelField.clear();
			mListModelMethod.clear();
		} else {
			// �Q�ƌ^�̔z��̏ꍇ
			updateFieldMethodView(mTargetObject);
		}
	}

	/**
	 * �z��̗v�f����0�̏ꍇ�̃I�u�W�F�N�g���\�����X�V���܂�
	 */
	private void updateZeroArrayObject(String clsName, String key) {
		mTargetObject = null;
		if (key == null) {
			mLabelTargetObject.setText(clsName);
		} else {
			mLabelTargetObject.setText("�y" + key + "�z  " + clsName);
		}
	}

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g�����t�B�[���h�ƃ��\�b�h���擾���A�\�����X�V���܂�
	 * @param obj
	 */
	private void updateFieldMethodView(Object obj) {
		if (mTargetObject == null) {
			mListModelField.clear();
			mListModelMethod.clear();
		} else {
			// �t�B�[���h���擾���ĕ\��
			mFields = InterpretUtil.getFields(mTargetObject);
			mListModelField.clear();
			for (Field f : mFields) {
				try {
					mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}

			// ���\�b�h���擾���ĕ\��
			mMethods = InterpretUtil.getMethods(mTargetObject);
			mListModelMethod.clear();
			for (Method m : mMethods) {
				mListModelMethod.addElement(InterpretUtil.methodToString(m));
			}
		}

		// �Â������폜
		mTxtFldOldVal.setText("");
		mTxtFldNewVal.setText("");
		mTxtAreaMethodArgs.setText("");
	}

	/**
	 * �z��̃��X�g�\�����X�V���܂�
	 * @param arr
	 */
	private void updateArrayView(Object arr) {
		mListModelArray.clear();
		int dimension = InterpretUtil.getArrayDimension(arr);
		String clsName = arr.getClass().getSimpleName();

		// 1�����z��̏ꍇ
		if (dimension == 1) {
			String viewClsName = clsName.substring(0, clsName.length() - 1);
			for (int i = 0; i < Array.getLength(arr); i++) {
				mListModelArray.addElement(viewClsName + i + "] = " + Array.get(arr, i));
			}
		}

		// 2�����z��̏ꍇ
		else if (dimension == 2) {
			String viewClsName = clsName.substring(0, clsName.length() - 3);
			for (int i = 0; i < Array.getLength(arr); i++) {
				for (int j = 0; j < Array.getLength(Array.get(arr, i)); j++) {
					mListModelArray.addElement(viewClsName + i + "][" + j + "] = "
							+ Array.get(Array.get(arr, i), j));
				}
			}
		}

		else {
			throw new AssertionError();
		}
	}

	/**
	 * �}�E�X�̃N���b�N�C�x���g���������܂�
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		// �N���X���m��{�^��
		if (event.getSource() == mBtnClassNameFix) {
			mListModelConstructor.clear();
			try {
				Class<?> cls = Class.forName(mTxtFldClassName.getText()); // �N���X�I�u�W�F�N�g�̎擾
				mConstructors = cls.getDeclaredConstructors(); // �R���X�g���N�^�̔z����擾
				for (Constructor<?> con : mConstructors) {
					mListModelConstructor.addElement(InterpretUtil.constructorToString(con));
				}
				mTxtAreaConstructorArgs.setText("");
			} catch (ClassNotFoundException e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// �I�u�W�F�N�g�����{�^��
		else if (event.getSource() == mBtnConstructor) {
			int constIndex = mListViewConstructor.getSelectedIndex();
			if (constIndex < 0) {
				return;
			}
			try {
				Object targetObj = InterpretUtil.createNewObject(mConstructors[constIndex],
						mTxtAreaConstructorArgs.getText(), mHolder);
				boolean keep = DialogUtil.showObjectKeepSelectDialog(this);
				if (keep == true) {
					// �I�u�W�F�N�g��ێ�����
					String key = keepObject(targetObj);
					updateTargetObject(targetObj, key);
				} else {
					// �I�u�W�F�N�g��ێ����Ȃ�
					updateTargetObject(targetObj, null);
				}
			} catch (Throwable e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// �z�񐶐��{�^��
		else if (event.getSource() == mBtnArrayCreation) {
			Object targetArr = null;
			try {
				targetArr = InterpretUtil.createNewArray(mTxtFldArrClassName.getText(),
						mTxtFldArrSize.getText());

				// �_�C�A���O�̕\��
				boolean keep = DialogUtil.showArrayKeepSelectDialog(this);
				mIndex = 0;
				if (keep == true) {
					// �I�u�W�F�N�g��ێ�����
					String key = keepObject(targetArr);
					updateTargetArray(targetArr, key, mIndex);
					updateArrayView(targetArr);
				} else {
					// �I�u�W�F�N�g��ێ����Ȃ�
					updateTargetArray(targetArr, null, mIndex);
					updateArrayView(targetArr);
				}

			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// �I�u�W�F�N�g�̃��X�g
		if (event.getSource() == mListViewObject) {
			if (event.getClickCount() == 2) { // �_�u���N���b�N
				String listItem = mListModelObject.elementAt(mListViewObject.getSelectedIndex())
						.toString();
				String key = listItem.substring(listItem.indexOf("�y") + 1, listItem.indexOf("�z"));
				Object targetObj = mHolder.getObject(key);
				if (!targetObj.getClass().isArray()) {
					// �I���A�C�e�����I�u�W�F�N�g�̏ꍇ
					updateTargetObject(targetObj, key);
				} else {
					// �I���A�C�e�����z��̏ꍇ
					mIndex = 0;
					updateTargetArray(targetObj, key, 0);
					updateArrayView(targetObj);
				}
			}
		}

		// �t�B�[���h�̃��X�g
		if (event.getSource() == mListViewField) {
			if (event.getClickCount() == 2) { // �_�u���N���b�N
				int index = mListViewField.getSelectedIndex();
				if (index < 0) {
					return;
				}

				mFields[index].setAccessible(true);
				try {
					Object targetObj = mFields[index].get(mTargetObject);
					if (targetObj == null) {
						return;
					}
					// �I�u�W�F�N�g�̕ێ�
					String key = keepObject(targetObj);
					DialogUtil.showItemKeepingDialog(this, "�ǂݏo�����̃L�[��  �y" + key + "�z �ł�");

				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}
		}

		// �t�B�[���h���������{�^��
		if (event.getSource() == mBtnField) {
			int fieldIndex = mListViewField.getSelectedIndex();
			if (fieldIndex < 0) {
				return;
			}

			Field targetField = mFields[fieldIndex];
			targetField.setAccessible(true);
			try {
				InterpretUtil.updateField(mFields[fieldIndex], mTargetObject,
						mTxtFldNewVal.getText(), mHolder);

				// �t�B�[���h���X�g�̍X�V
				mListModelField.clear();
				mFields = InterpretUtil.getFields(mTargetObject);
				for (Field f : mFields) {
					mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
				}

				// ���݂̃I�u�W�F�N�g���z��v�f�̏ꍇ�͒l���X�V
				if (mTargetArray != null) {
					updateArrayView(mTargetArray);
					mListViewArray.setSelectedIndex(mIndex);
				}

			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// ���\�b�h�Ăяo���{�^��
		else if (event.getSource() == mBtnMethod) {
			int methodIndex = mListViewMethod.getSelectedIndex();
			if (methodIndex < 0) {
				return;
			}
			try {
				Object retObj = InterpretUtil.invokeMethod(mMethods[methodIndex], mTargetObject,
						mTxtAreaMethodArgs.getText(), mHolder);

				if (retObj == null) {
					// �߂�l��void�^�̏ꍇ
					DialogUtil.showMethodDialog(this);
				} else {
					boolean keep = DialogUtil.showMethodDialog(this, retObj);
					if (keep == true) {
						keepObject(retObj);
					}
				}
			} catch (Throwable e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// �z��v�f�̃��X�g
		if (event.getSource() == mListViewArray) {
			if (event.getClickCount() == 2) { // �_�u���N���b�N
				int index = mListViewArray.getSelectedIndex();
				if (index < 0) {
					return;
				}

				try {
					int dimension = InterpretUtil.getArrayDimension(mTargetArray);
					Object targetObj = null;

					// 1�����z��̏ꍇ
					if (dimension == 1) {
						targetObj = Array.get(mTargetArray, index);
					}

					// 2�����z��̏ꍇ
					else if (dimension == 2) {
						int innerArrSize = Array.getLength(Array.get(mTargetArray, 0));
						targetObj = Array.get((Array.get(mTargetArray, index / innerArrSize)),
								index % innerArrSize);
					}

					if (targetObj == null) {
						return;
					}

					// �I�u�W�F�N�g�̕ێ�
					String key = keepObject(targetObj);
					DialogUtil.showItemKeepingDialog(this, "�ǂݏo�����̃L�[��  �y" + key + "�z �ł�");

				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}
		}

		// �z��v�f�X�V�{�^��
		if (event.getSource() == mBtnArrayOperation) {
			int index = mListViewArray.getSelectedIndex();
			if (index < 0) {
				return;
			}
			try {
				int dimension = InterpretUtil.getArrayDimension(mTargetArray);

				// 1�����z��̏ꍇ
				if (dimension == 1) {
					Array.set(
							mTargetArray,
							index,
							InterpretUtil.getUpdateArrayItem(mTargetArray,
									mTxtFldNewArrVal.getText(), mHolder));
				}

				// 2�����z��̏ꍇ
				else if (dimension == 2) {
					int innerArrSize = Array.getLength(Array.get(mTargetArray, 0));
					Array.set(
							(Array.get(mTargetArray, index / innerArrSize)),
							index % innerArrSize,
							InterpretUtil.getUpdateArrayItem(mTargetArray,
									mTxtFldNewArrVal.getText(), mHolder));
				}

				// �z��̃��X�g�̍X�V
				updateArrayView(mTargetArray);
				mIndex = index;
				mListViewArray.setSelectedIndex(mIndex);
			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	public static void main(String[] args) {
		new Interpret();
	}
}