import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Calculator extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final Dimension BUTTON_SIZE = new Dimension(75, 30);
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
	private static final int MAX_INPUT_SIZE = 20;

	private JPanel mContentPane = new JPanel();
	private JLabel mTargetNumLabel; // 計算対象の数値
	private JLabel mOperationLabel; // 演算子
	private JList mMethodList; // Mathクラスのメソッドのリスト
	private DefaultListModel mListModel = new DefaultListModel();
	private JTextField mInputField; // 数値の入力フィールド
	private JButton mEqualBtn; // ＝ボタン

	public Calculator() {
		setSize(new Dimension(380, 570));
		setContentPane(mContentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 0, 5, 0);
		mContentPane.setLayout(gbl);

		// 計算対象の数値
		mTargetNumLabel = new JLabel("");
		mTargetNumLabel.setPreferredSize(new Dimension(150, 30));
		mTargetNumLabel.setFont(LABEL_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbl.setConstraints(mTargetNumLabel, gbc);

		// 演算子の数値
		mOperationLabel = new JLabel("");
		mOperationLabel.setPreferredSize(new Dimension(150, 30));
		mOperationLabel.setFont(LABEL_FONT);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbl.setConstraints(mOperationLabel, gbc);

		// 入力フィールド
		mInputField = new JTextField("");
		mInputField.setPreferredSize(new Dimension(300, 30));
		mInputField.setEditable(false);
		mInputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		mInputField.setHorizontalAlignment(JTextField.RIGHT);
		mInputField.setBackground(new Color(255, 255, 150));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbl.setConstraints(mInputField, gbc);

		// Mathクラスのメソッドのリスト
		mMethodList = new JList(mListModel);
		Method[] methods = Math.class.getDeclaredMethods();
		for (Method m : methods) {
			if (Modifier.isPublic(m.getModifiers())) {
				mListModel.addElement(m);
			}
		}
		mMethodList.addListSelectionListener(new MethodSelectedListener());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 200));
		scrollPane.getViewport().setView(mMethodList);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbl.setConstraints(scrollPane, gbc);

		// Cボタン
		JButton btnC = new JButton("C");
		btnC.setPreferredSize(BUTTON_SIZE);
		btnC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mInputField.setText("");
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbl.setConstraints(btnC, gbc);

		// ACボタン
		JButton btnAc = new JButton("AC");
		btnAc.setPreferredSize(BUTTON_SIZE);
		btnAc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mTargetNumLabel.setText("");
				mOperationLabel.setText("");
				mInputField.setText("");
				mEqualBtn.setEnabled(false);
			}
		});
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbl.setConstraints(btnAc, gbc);

		// 数字&演算子ボタン
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(4, 4));
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbl.setConstraints(gridPanel, gbc);
		// 1行目
		gridPanel.add(new NumberButton(7), 0);
		gridPanel.add(new NumberButton(8), 1);
		gridPanel.add(new NumberButton(9), 2);
		gridPanel.add(new OperationButton("+"), 3);
		// 2行目
		gridPanel.add(new NumberButton(4), 4);
		gridPanel.add(new NumberButton(5), 5);
		gridPanel.add(new NumberButton(6), 6);
		gridPanel.add(new OperationButton("-"), 7);
		// 3行目
		gridPanel.add(new NumberButton(1), 8);
		gridPanel.add(new NumberButton(2), 9);
		gridPanel.add(new NumberButton(3), 10);
		gridPanel.add(new OperationButton("*"), 11);
		// 4行目
		gridPanel.add(new NumberButton(0), 12);
		gridPanel.add(new DotButton(), 13);
		gridPanel.add(new OperationButton("%"), 14);
		gridPanel.add(new OperationButton("/"), 15);

		// ＝ボタン
		mEqualBtn = new JButton("=");
		mEqualBtn.setPreferredSize(new Dimension(300, 30));
		mEqualBtn.setEnabled(false); // ＝ボタンは演算子または引数が2つあるメソッドが選択されると有効になる
		mEqualBtn.addActionListener(new EqualButtonListener());
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbl.setConstraints(mEqualBtn, gbc);

		mContentPane.add(mTargetNumLabel);
		mContentPane.add(mOperationLabel);
		mContentPane.add(mInputField);
		mContentPane.add(scrollPane);
		mContentPane.add(btnC);
		mContentPane.add(btnAc);
		mContentPane.add(gridPanel);
		mContentPane.add(mEqualBtn);
		setVisible(true);
	}

	/**
	 * 数字ボタンを表すクラスです
	 */
	private class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;
		private String mNumber;

		NumberButton(int num) {
			mNumber = String.valueOf(num);
			setText(mNumber);
			setPreferredSize(BUTTON_SIZE);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String newVal = mInputField.getText() + mNumber;
			// 最大入力可能文字数をチェック
			if (newVal.contains(".")) {
				// double型の場合
				if (newVal.length() < MAX_INPUT_SIZE) {
					mInputField.setText(newVal);
				}
			} else {
				// long型の場合
				try {
					Long.parseLong(newVal);
					mInputField.setText(newVal);
				} catch (NumberFormatException ex) {
					// 何もしない
				}
			}
		}
	}

	/**
	 * 演算子ボタンを表すクラスです
	 */
	private class OperationButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;
		private String mOperation;

		OperationButton(String ope) {
			mOperation = ope;
			setText(ope);
			setPreferredSize(BUTTON_SIZE);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// マイナスを符号として使う場合
			if (mOperation.equals("-") && mInputField.getText().equals("")) {
				mInputField.setText("-");
				return;
			}

			// 数値以外の文字が含まれていないかチェック
			try {
				String in = mInputField.getText();
				if (in.contains(".")) {
					Double.parseDouble(in);
				} else {
					Long.parseLong(in);
				}
			} catch (NumberFormatException ex) {
				return;
			}
			mOperationLabel.setText(mOperation);
			mTargetNumLabel.setText(mInputField.getText());
			mInputField.setText("");
			mEqualBtn.setEnabled(true);
		}
	}

	/**
	 * ドットボタンを表すクラスです
	 */
	private class DotButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		DotButton() {
			setText(".");
			setPreferredSize(BUTTON_SIZE);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String in = mInputField.getText();
			if (!in.contains(".") && !in.equals("") && !in.equals("-")) {
				mInputField.setText(in + ".");
			}
		}
	}

	/**
	 * Mathクラスのメソッドが選択された時の処理を定義するクラスです。
	 */
	private class MethodSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (mInputField.getText().equals("")) {
				// 値が未入力の場合
				return;
			}

			Method m = (Method) mListModel.get(mMethodList.getSelectedIndex());
			if (m.getGenericParameterTypes().length == 1) {
				// 引数1つのメソッドは即実行
				Number num;
				String val = mInputField.getText();
				try {
					if (val.contains(".")) {
						num = Double.parseDouble(val);
					} else {
						num = Long.parseLong(val);
					}
				} catch (NumberFormatException ex) {
					return;
				}

				try {
					String result = m.invoke(Math.class, num).toString();
					mInputField.setText(result);
				} catch (Exception ex) {
					mInputField.setText("ERR");
					ex.printStackTrace();
				}
			} else {
				// 引数2つのメソッドは値を保持
				// 数値以外の文字が含まれていないかチェック
				try {
					String in = mInputField.getText();
					if (in.contains(".")) {
						Double.parseDouble(in);
					} else {
						Long.parseLong(in);
					}
				} catch (NumberFormatException ex) {
					return;
				}
				mOperationLabel.setText(m.getName());
				mTargetNumLabel.setText(mInputField.getText());
				mInputField.setText("");
				mEqualBtn.setEnabled(true);
			}
		}
	}

	/**
	 * ＝ボタンが押された時の処理を定義するクラスです
	 */
	private class EqualButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (mInputField.getText().equals("")) {
				// 値が未入力の場合
				return;
			}

			Number n1;
			Number n2;
			BigDecimal bd1;
			BigDecimal bd2;
			String val1 = mTargetNumLabel.getText();
			String val2 = mInputField.getText();
			try {
				if (val1.contains(".")) {
					double d = Double.parseDouble(val1);
					n1 = d;
					bd1 = new BigDecimal(d);
				} else {
					long l = Long.parseLong(val1);
					n1 = l;
					bd1 = new BigDecimal(l);
				}
				if (val2.contains(".")) {
					double d = Double.parseDouble(val2);
					n2 = d;
					bd2 = new BigDecimal(d);
				} else {
					long l = Long.parseLong(val2);
					n2 = l;
					bd2 = new BigDecimal(l);
				}
			} catch (NumberFormatException ex) {
				return;
			}

			if (mOperationLabel.getText().equals("+")) {
				BigDecimal score = bd1.add(bd2);
				inputFieldSetText(score.toString());
			} else if (mOperationLabel.getText().equals("-")) {
				BigDecimal score = bd1.subtract(bd2);
				inputFieldSetText(score.toString());
			} else if (mOperationLabel.getText().equals("*")) {
				BigDecimal score = bd1.multiply(bd2);
				inputFieldSetText(score.toString());
			} else if (mOperationLabel.getText().equals("/")) {
				BigDecimal score = bd1.divide(bd2);
				inputFieldSetText(score.toString());
			} else if (mOperationLabel.getText().equals("%")) {
				BigDecimal score = bd1.remainder(bd2);
				inputFieldSetText(score.toString());
			} else {
				// Mathクラスのメソッドを実行
				Method m = (Method) mListModel.get(mMethodList.getSelectedIndex());
				try {
					String result = m.invoke(Math.class, n1, n2).toString();
					inputFieldSetText(result);
				} catch (Exception ex) {
					mInputField.setText("ERR");
					ex.printStackTrace();
				}
			}

			mTargetNumLabel.setText("");
			mOperationLabel.setText("");
			mEqualBtn.setEnabled(false);
		}
	}

	/**
	 * 入力フィールドに引数で指定された文字列を設定します。<br>
	 * 20文字以上の文字列は20文字まで格納されます。
	 * @param txt
	 */
	private void inputFieldSetText(String txt) {
		if (txt.length() <= 20) {
			mInputField.setText(txt);
		} else {
			mInputField.setText(txt.substring(0, 20));
		}
	}

	public static void main(String[] args) {
		new Calculator();
	}
}