import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class Interpret extends JFrame implements MouseListener {
	private static final long serialVersionUID = -808225512344080975L;

	// 定数フィールド
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

	// オブジェクト生成パネル内のパーツ
	private JTextField mTxtFldClassName;
	private JButton mBtnClassNameFix;
	private JList mListViewConstructor;
	private DefaultListModel mListModelConstructor;
	private JTextArea mTxtAreaConstructorArgs;
	private JButton mBtnConstructor;

	// 配列生成パネル内のパーツ
	private JTextField mTxtFldArrClassName;
	private JTextField mTxtFldArrSize;
	private JButton mBtnArrayCreation;

	// 保持しているオブジェクトのリスト表示
	private JList mListViewObject;
	private DefaultListModel mListModelObject;

	// 現在のオブジェクトの表示
	private JLabel mLabelTargetObject;

	// フィールド書き換えパネル内のパーツ
	private JList mListViewField;
	private DefaultListModel mListModelField;
	private JTextField mTxtFldOldVal;
	private JTextField mTxtFldNewVal;
	private JButton mBtnField;

	// メソッド呼び出しパネル内のパーツ
	private JList mListViewMethod;
	private DefaultListModel mListModelMethod;
	private JTextArea mTxtAreaMethodArgs;
	private JButton mBtnMethod;

	// 配列操作パネル内のパーツ
	private JList mListViewArray;
	private DefaultListModel mListModelArray;
	private JTextField mTxtFldNewArrVal;
	private JButton mBtnArrayOperation;

	// 情報を保持
	private Object mTargetObject;
	private Object mTargetArray;
	private int mIndex;
	private Constructor<?>[] mConstructors;
	private Method[] mMethods;
	private Field[] mFields;

	// 登録済みオブジェクトを保持するデータホルダー
	private ObjectHolder mHolder = new ObjectHolder();

	/**
	 * コンストラクタ<br>
	 * 画面を初期化します
	 */
	public Interpret() {
		initFrame();
	}

	/**
	 * 画面の初期化を行います
	 */
	private void initFrame() {
		// Frameの初期化
		setTitle("Interpret");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ベースパネルの初期化
		JPanel panelBase = new JPanel();
		panelBase.setBackground(BASE_COLOR);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panelBase.setLayout(gbl);

		// 子コンポーネントを配置
		// テキスト「新規作成」
		JLabel txtObjectCreation = createTitleLabel("新規作成", DIMENSION_LEFT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.insets = DEFAULT_INSETS;
		gbl.setConstraints(txtObjectCreation, gbc);

		// 新規作成タブ
		JTabbedPane tabCreation = createNewMakingTab();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbl.setConstraints(tabCreation, gbc);

		// テキスト「保持しているオブジェクト」
		JLabel txtRegisteredObject = createTitleLabel("保持しているオブジェクト", DIMENSION_LEFT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(30, 10, 0, 10);
		gbl.setConstraints(txtRegisteredObject, gbc);

		// 保持オブジェクトのリスト
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

		// テキスト「現在のオブジェクト」
		JLabel txtSelectedObject = createTitleLabel("現在のオブジェクト", DIMENSION_RIGHT_TEXT);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbl.setConstraints(txtSelectedObject, gbc);

		// オブジェクト名
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

		// フィールド、メソッド、配列の操作タブ
		JTabbedPane tabOperation = createOperationTab();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 3;
		gbc.insets = new Insets(30, 10, 0, 10);
		gbl.setConstraints(tabOperation, gbc);

		// 描画
		panelBase.add(txtObjectCreation);
		panelBase.add(tabCreation);
		panelBase.add(txtRegisteredObject);
		panelBase.add(scrollPane);
		panelBase.add(txtSelectedObject);
		panelBase.add(paneSelectedObject);
		panelBase.add(tabOperation);
		getContentPane().add(panelBase);

		// リスナー登録
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
	 * タイトルテキストを生成します
	 * @param txt テキスト
	 * @param d 表示のサイズ
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
	 * メッセージテキストを生成します
	 * @param txt テキスト
	 * @return
	 */
	private JLabel createMessageLabel(String txt) {
		JLabel label = new JLabel(txt);
		label.setFont(MESSAGE_FONT);
		return label;
	}

	/**
	 * オブジェクト、配列の新規作成タブ（画面左上）を生成します
	 * @return
	 */
	private JTabbedPane createNewMakingTab() {
		JTabbedPane tab = new JTabbedPane();
		tab.setFont(TITLE_FONT);
		tab.add("オブジェクト", createObjectCreationPanel());
		tab.add("配列", createArrayCreationPanel());
		return tab;
	}

	/**
	 * オブジェクトの新規作成パネルを生成します
	 * @return
	 */
	private JPanel createObjectCreationPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// クラス名入力フィールド
		pane.add(createMessageLabel("①クラス名を入力してください"));
		mTxtFldClassName = new JTextField();
		mTxtFldClassName.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		pane.add(mTxtFldClassName);

		// クラス名決定ボタン
		mBtnClassNameFix = new JButton();
		mBtnClassNameFix.setText("決定");
		JPanel subPane = new JPanel();
		subPane.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 35));
		subPane.setBackground(Color.WHITE);
		subPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		subPane.add(mBtnClassNameFix);
		subPane.add(mBtnClassNameFix);
		pane.add(subPane);

		// コンストラクタのリスト
		pane.add(createMessageLabel("②コンストラクタを選んでください"));
		mListModelConstructor = new DefaultListModel();
		mListViewConstructor = new JList(mListModelConstructor);
		JScrollPane scrollPane = new JScrollPane(mListViewConstructor);
		scrollPane.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 80));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 5));

		// 引数
		pane.add(createMessageLabel("③引数を入力してください（カンマ区切り）"));
		mTxtAreaConstructorArgs = new JTextArea();
		mTxtAreaConstructorArgs.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mTxtAreaConstructorArgs.setBorder(GRAY_LINE_BORDER);
		mTxtAreaConstructorArgs.setLineWrap(true);
		pane.add(mTxtAreaConstructorArgs);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 5));

		// 実行ボタン
		mBtnConstructor = new JButton();
		mBtnConstructor.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mBtnConstructor.setText("オブジェクト生成");
		pane.add(mBtnConstructor);

		return pane;
	}

	/**
	 * 配列の新規作成パネルを生成します
	 * @return
	 */
	private JPanel createArrayCreationPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// 型名入力フィールド
		pane.add(createMessageLabel("①型名を入力してください"));
		mTxtFldArrClassName = new JTextField();
		mTxtFldArrClassName.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		pane.add(mTxtFldArrClassName);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 25));

		// サイズ
		pane.add(createMessageLabel("②配列のサイズを入力してください"));
		mTxtFldArrSize = new JTextField();
		mTxtFldArrSize.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 20));
		mTxtFldArrSize.setBorder(GRAY_LINE_BORDER);
		pane.add(mTxtFldArrSize);
		pane.add(createPaddingPanel(LEFT_ITEM_WIDTH, 140));

		// 実行ボタン
		mBtnArrayCreation = new JButton();
		mBtnArrayCreation.setPreferredSize(new Dimension(LEFT_ITEM_WIDTH, 30));
		mBtnArrayCreation.setText("配列生成");
		pane.add(mBtnArrayCreation);

		return pane;
	}

	/**
	 * フィールド、メソッド、配列に関する操作を行うGUIをまとめたタブ（画面右下）を生成します
	 * @return
	 */
	private JTabbedPane createOperationTab() {
		JTabbedPane tab = new JTabbedPane();
		tab.setFont(TITLE_FONT);
		tab.add("フィールド書き換え", createFieldPanel());
		tab.add("メソッド呼び出し", createMethodPanel());
		tab.add("配列の操作", createArrayPanel());
		return tab;
	}

	/**
	 * フィールド書き換えパネルを生成します
	 * @return
	 */
	private JPanel createFieldPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// フィールドのリスト
		pane.add(createMessageLabel("①書き換えるフィールドを選択してください"));
		mListModelField = new DefaultListModel();
		mListViewField = new JList(mListModelField);
		// 選択中の要素が変更された場合のリスナー登録
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

		// 変更前の値
		pane.add(createMessageLabel("変更前の値"));
		mTxtFldOldVal = new JTextField();
		mTxtFldOldVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		mTxtFldOldVal.setFont(MESSAGE_FONT);
		mTxtFldOldVal.setEditable(false);
		mTxtFldOldVal.setBackground(Color.WHITE);
		mTxtFldOldVal.setBorder(GRAY_LINE_BORDER);
		pane.add(mTxtFldOldVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 5));

		// 変更後の値
		pane.add(createMessageLabel("②変更後の値を入力してください"));
		mTxtFldNewVal = new JTextField();
		mTxtFldNewVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		pane.add(mTxtFldNewVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// 実行ボタン
		mBtnField = new JButton();
		mBtnField.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnField.setText("書き換え実行");
		pane.add(mBtnField);

		return pane;
	}

	/**
	 * メソッド呼び出しパネルを生成します
	 * @return
	 */
	private JPanel createMethodPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// メソッドのリスト
		pane.add(createMessageLabel("①呼び出すメソッドを選択してください"));
		mListModelMethod = new DefaultListModel();
		mListViewMethod = new JList(mListModelMethod);
		JScrollPane scrollPane = new JScrollPane(mListViewMethod);
		scrollPane.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 200));
		scrollPane.setBorder(GRAY_LINE_BORDER);
		pane.add(scrollPane);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 10));

		// 引数
		pane.add(createMessageLabel("②引数を入力してください（カンマ区切り）"));
		mTxtAreaMethodArgs = new JTextArea();
		mTxtAreaMethodArgs.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 75));
		mTxtAreaMethodArgs.setBorder(GRAY_LINE_BORDER);
		mTxtAreaMethodArgs.setLineWrap(true);
		pane.add(mTxtAreaMethodArgs);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// 実行ボタン
		mBtnMethod = new JButton();
		mBtnMethod.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnMethod.setText("呼び出し実行");
		pane.add(mBtnMethod);

		return pane;
	}

	/**
	 * 配列操作用のパネルを生成します
	 * @return
	 */
	private JPanel createArrayPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		pane.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
		pane.setBackground(Color.WHITE);

		// 配列のリスト
		pane.add(createPaddingPanel(RIGHT_PANEL_WIDTH, 5));
		pane.add(createMessageLabel("①操作する配列を選択してください"));
		pane.add(createMessageLabel("　ダブルクリックで要素を保持できます"));
		pane.add(createPaddingPanel(RIGHT_PANEL_WIDTH, 5));
		mListModelArray = new DefaultListModel();
		mListViewArray = new JList(mListModelArray);
		// 選択中の要素が変更された場合のリスナー登録
		mListViewArray.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int index = mListViewArray.getSelectedIndex();
				if (index < 0) {
					return;
				}
				String objName = mLabelTargetObject.getText();
				String key;
				if (objName.contains("【")) {
					key = objName.substring(objName.indexOf("【") + 1, objName.indexOf("】"));
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

		// 変更後の値
		pane.add(createMessageLabel("②変更後の配列要素を入力してください"));
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 5));
		mTxtFldNewArrVal = new JTextField();
		mTxtFldNewArrVal.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 20));
		pane.add(mTxtFldNewArrVal);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 15));

		// 実行ボタン
		mBtnArrayOperation = new JButton();
		mBtnArrayOperation.setPreferredSize(new Dimension(RIGHT_ITEM_WIDTH, 30));
		mBtnArrayOperation.setText("配列要素の更新");
		pane.add(mBtnArrayOperation);
		pane.add(createPaddingPanel(RIGHT_ITEM_WIDTH, 20));

		JTextArea msg = new JTextArea("フィールドの書き換えとメソッドの呼び出しはそれぞれ専用のタブから行ってください");
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
	 * 余白用のパネルを生成します
	 * @param width 余白の幅
	 * @param height 余白の高さ
	 * @return
	 */
	private JPanel createPaddingPanel(int width, int height) {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(width, height));
		pane.setBackground(Color.WHITE);
		return pane;
	}

	/**
	 * 指定されたオブジェクトを保持します
	 * @param obj
	 * @return 呼び出し用のキー
	 */
	private String keepObject(Object obj) {
		String key = mHolder.addObject(obj);
		mListModelObject.addElement("ID【" + key + "】  " + obj.getClass().getName());
		return key;
	}

	/**
	 * ターゲットオブジェクトを更新します
	 * @param obj オブジェクト
	 * @param key オブジェクトのキー（オブジェクトを保持しない場合はnull）
	 */
	private void updateTargetObject(Object obj, String key) {
		mTargetObject = obj;
		mTargetArray = null;

		// オブジェクト名を表示
		if (key == null) {
			mLabelTargetObject.setText(mTargetObject.getClass().getName());
		} else {
			mLabelTargetObject.setText("【" + key + "】  " + mTargetObject.getClass().getName());
		}

		// フィールドを取得して表示
		mFields = InterpretUtil.getFields(mTargetObject);
		mListModelField.clear();
		for (Field f : mFields) {
			try {
				mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// メソッドを取得して表示
		mMethods = InterpretUtil.getMethods(mTargetObject);
		mListModelMethod.clear();
		for (Method m : mMethods) {
			mListModelMethod.addElement(InterpretUtil.methodToString(m));
		}

		// 配列の要素を削除する
		mListModelArray.clear();
		mTxtFldNewArrVal.setText("");
	}

	/**
	 * ターゲット配列を更新します
	 * @param obj オブジェクト
	 * @param key オブジェクトのキー（オブジェクトを保持しない場合はnull）
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
			// ターゲットオブジェクトの更新
			// 1次元配列の場合
			if (dimension == 1) {
				mTargetObject = Array.get(mTargetArray, index);
				String viewClsName;
				if (InterpretUtil.isPrimitiveArray(arr)) {
					viewClsName = clsName.substring(0, clsName.length() - 2);
				} else {
					viewClsName = clsName + " ";
				}

				// オブジェクト名を表示
				if (key == null) {
					mLabelTargetObject.setText(viewClsName + "[" + index + "]");
				} else {
					mLabelTargetObject.setText("【" + key + "】  " + viewClsName + "[" + index + "]");
				}
			}

			// 2次元配列の場合
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

					// オブジェクト名を表示
					if (key == null) {
						mLabelTargetObject.setText(viewClsName + "[" + firstIndex + "]["
								+ secondIndex + "]");
					} else {
						mLabelTargetObject.setText("【" + key + "】  " + viewClsName + "["
								+ firstIndex + "][" + secondIndex + "]");
					}
				} else {
					// 内側の配列の要素数が0
					updateZeroArrayObject(clsName, key);
				}
			}

			else {
				throw new AssertionError();
			}

		} else {
			// 1次元配列または2次元配列の外側の配列の要素数が0
			updateZeroArrayObject(clsName, key);
		}

		// フィールドとメソッドの表示を更新
		if (InterpretUtil.isPrimitiveArray(mTargetArray)) {
			// 基本データ型の配列の場合
			mListModelField.clear();
			mListModelMethod.clear();
		} else {
			// 参照型の配列の場合
			updateFieldMethodView(mTargetObject);
		}
	}

	/**
	 * 配列の要素数が0の場合のオブジェクト名表示を更新します
	 */
	private void updateZeroArrayObject(String clsName, String key) {
		mTargetObject = null;
		if (key == null) {
			mLabelTargetObject.setText(clsName);
		} else {
			mLabelTargetObject.setText("【" + key + "】  " + clsName);
		}
	}

	/**
	 * 指定されたオブジェクトが持つフィールドとメソッドを取得し、表示を更新します
	 * @param obj
	 */
	private void updateFieldMethodView(Object obj) {
		if (mTargetObject == null) {
			mListModelField.clear();
			mListModelMethod.clear();
		} else {
			// フィールドを取得して表示
			mFields = InterpretUtil.getFields(mTargetObject);
			mListModelField.clear();
			for (Field f : mFields) {
				try {
					mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}

			// メソッドを取得して表示
			mMethods = InterpretUtil.getMethods(mTargetObject);
			mListModelMethod.clear();
			for (Method m : mMethods) {
				mListModelMethod.addElement(InterpretUtil.methodToString(m));
			}
		}

		// 古い情報を削除
		mTxtFldOldVal.setText("");
		mTxtFldNewVal.setText("");
		mTxtAreaMethodArgs.setText("");
	}

	/**
	 * 配列のリスト表示を更新します
	 * @param arr
	 */
	private void updateArrayView(Object arr) {
		mListModelArray.clear();
		int dimension = InterpretUtil.getArrayDimension(arr);
		String clsName = arr.getClass().getSimpleName();

		// 1次元配列の場合
		if (dimension == 1) {
			String viewClsName = clsName.substring(0, clsName.length() - 1);
			for (int i = 0; i < Array.getLength(arr); i++) {
				mListModelArray.addElement(viewClsName + i + "] = " + Array.get(arr, i));
			}
		}

		// 2次元配列の場合
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
	 * マウスのクリックイベントを処理します
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		// クラス名確定ボタン
		if (event.getSource() == mBtnClassNameFix) {
			mListModelConstructor.clear();
			try {
				Class<?> cls = Class.forName(mTxtFldClassName.getText()); // クラスオブジェクトの取得
				mConstructors = cls.getDeclaredConstructors(); // コンストラクタの配列を取得
				for (Constructor<?> con : mConstructors) {
					mListModelConstructor.addElement(InterpretUtil.constructorToString(con));
				}
				mTxtAreaConstructorArgs.setText("");
			} catch (ClassNotFoundException e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// オブジェクト生成ボタン
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
					// オブジェクトを保持する
					String key = keepObject(targetObj);
					updateTargetObject(targetObj, key);
				} else {
					// オブジェクトを保持しない
					updateTargetObject(targetObj, null);
				}
			} catch (Throwable e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// 配列生成ボタン
		else if (event.getSource() == mBtnArrayCreation) {
			Object targetArr = null;
			try {
				targetArr = InterpretUtil.createNewArray(mTxtFldArrClassName.getText(),
						mTxtFldArrSize.getText());

				// ダイアログの表示
				boolean keep = DialogUtil.showArrayKeepSelectDialog(this);
				mIndex = 0;
				if (keep == true) {
					// オブジェクトを保持する
					String key = keepObject(targetArr);
					updateTargetArray(targetArr, key, mIndex);
					updateArrayView(targetArr);
				} else {
					// オブジェクトを保持しない
					updateTargetArray(targetArr, null, mIndex);
					updateArrayView(targetArr);
				}

			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// オブジェクトのリスト
		if (event.getSource() == mListViewObject) {
			if (event.getClickCount() == 2) { // ダブルクリック
				String listItem = mListModelObject.elementAt(mListViewObject.getSelectedIndex())
						.toString();
				String key = listItem.substring(listItem.indexOf("【") + 1, listItem.indexOf("】"));
				Object targetObj = mHolder.getObject(key);
				if (!targetObj.getClass().isArray()) {
					// 選択アイテムがオブジェクトの場合
					updateTargetObject(targetObj, key);
				} else {
					// 選択アイテムが配列の場合
					mIndex = 0;
					updateTargetArray(targetObj, key, 0);
					updateArrayView(targetObj);
				}
			}
		}

		// フィールドのリスト
		if (event.getSource() == mListViewField) {
			if (event.getClickCount() == 2) { // ダブルクリック
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
					// オブジェクトの保持
					String key = keepObject(targetObj);
					DialogUtil.showItemKeepingDialog(this, "読み出し時のキーは  【" + key + "】 です");

				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}
		}

		// フィールド書き換えボタン
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

				// フィールドリストの更新
				mListModelField.clear();
				mFields = InterpretUtil.getFields(mTargetObject);
				for (Field f : mFields) {
					mListModelField.addElement(InterpretUtil.fieldToString(f, mTargetObject));
				}

				// 現在のオブジェクトが配列要素の場合は値を更新
				if (mTargetArray != null) {
					updateArrayView(mTargetArray);
					mListViewArray.setSelectedIndex(mIndex);
				}

			} catch (Exception e) {
				DialogUtil.showExceptionDialog(this, e.toString());
			}
		}

		// メソッド呼び出しボタン
		else if (event.getSource() == mBtnMethod) {
			int methodIndex = mListViewMethod.getSelectedIndex();
			if (methodIndex < 0) {
				return;
			}
			try {
				Object retObj = InterpretUtil.invokeMethod(mMethods[methodIndex], mTargetObject,
						mTxtAreaMethodArgs.getText(), mHolder);

				if (retObj == null) {
					// 戻り値がvoid型の場合
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

		// 配列要素のリスト
		if (event.getSource() == mListViewArray) {
			if (event.getClickCount() == 2) { // ダブルクリック
				int index = mListViewArray.getSelectedIndex();
				if (index < 0) {
					return;
				}

				try {
					int dimension = InterpretUtil.getArrayDimension(mTargetArray);
					Object targetObj = null;

					// 1次元配列の場合
					if (dimension == 1) {
						targetObj = Array.get(mTargetArray, index);
					}

					// 2次元配列の場合
					else if (dimension == 2) {
						int innerArrSize = Array.getLength(Array.get(mTargetArray, 0));
						targetObj = Array.get((Array.get(mTargetArray, index / innerArrSize)),
								index % innerArrSize);
					}

					if (targetObj == null) {
						return;
					}

					// オブジェクトの保持
					String key = keepObject(targetObj);
					DialogUtil.showItemKeepingDialog(this, "読み出し時のキーは  【" + key + "】 です");

				} catch (Exception e) {
					DialogUtil.showExceptionDialog(this, e.toString());
				}
			}
		}

		// 配列要素更新ボタン
		if (event.getSource() == mBtnArrayOperation) {
			int index = mListViewArray.getSelectedIndex();
			if (index < 0) {
				return;
			}
			try {
				int dimension = InterpretUtil.getArrayDimension(mTargetArray);

				// 1次元配列の場合
				if (dimension == 1) {
					Array.set(
							mTargetArray,
							index,
							InterpretUtil.getUpdateArrayItem(mTargetArray,
									mTxtFldNewArrVal.getText(), mHolder));
				}

				// 2次元配列の場合
				else if (dimension == 2) {
					int innerArrSize = Array.getLength(Array.get(mTargetArray, 0));
					Array.set(
							(Array.get(mTargetArray, index / innerArrSize)),
							index % innerArrSize,
							InterpretUtil.getUpdateArrayItem(mTargetArray,
									mTxtFldNewArrVal.getText(), mHolder));
				}

				// 配列のリストの更新
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