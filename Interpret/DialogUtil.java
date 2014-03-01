import java.awt.*;

import javax.swing.*;

public class DialogUtil {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
	private static final Font LARGE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
	private static final Font PLAIN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

	/**
	 * オブジェクトの生成に成功した場合のダイアログを表示します<br>
	 * オブジェクトを保持するかを選択するダイアログを表示します
	 * @param parent 親コンポーネント
	 * @return 保持する場合はtrue, 保持しない場合はfalse
	 */
	public static boolean showObjectKeepSelectDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("このオブジェクトを保持しますか？"));
		pane.add(createPaddingPanel(WIDTH, 20, UIManager.getColor("control")));

		JPanel subPane = new JPanel();
		subPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		subPane.setPreferredSize(new Dimension(WIDTH - 10, 200));
		subPane.setBackground(Color.WHITE);

		subPane.add(new JLabel("オブジェクトを保持すると以下のことができるようになります"));
		subPane.add(createPaddingPanel(WIDTH, 15, Color.WHITE));

		JLabel label1 = new JLabel("●メソッド呼び出しの引数に指定");
		label1.setFont(LARGE_FONT);
		subPane.add(label1);

		JTextArea txtArea1 = new JTextArea("呼び出し時は引数の欄にオブジェクトIDを入力してください");
		txtArea1.setFont(PLAIN_FONT);
		txtArea1.setLineWrap(true);
		txtArea1.setFocusable(false);
		txtArea1.setEditable(false);
		txtArea1.setPreferredSize(new Dimension(WIDTH, 30));
		subPane.add(txtArea1);
		subPane.add(createPaddingPanel(WIDTH, 10, Color.WHITE));

		JLabel label2 = new JLabel("●任意のタイミングでフィールド書き換え・メソッド呼び出し");
		label2.setFont(LARGE_FONT);
		subPane.add(label2);

		JTextArea txtArea2 = new JTextArea("「保持しているオブジェクト」の欄の対象オブジェクトをダブルクリックしてください");
		txtArea2.setFont(PLAIN_FONT);
		txtArea2.setLineWrap(true);
		txtArea2.setFocusable(false);
		txtArea2.setEditable(false);
		txtArea2.setPreferredSize(new Dimension(WIDTH - 20, 30));
		subPane.add(txtArea2);
		pane.add(subPane);

		int result = JOptionPane.showConfirmDialog(parent, pane, "オブジェクトの生成に成功しました",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// 「はい」が選択された場合
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 配列の生成に成功した場合のダイアログを表示します<br>
	 * オブジェクトを保持するかを選択するダイアログを表示します
	 * @param parent 親コンポーネント
	 * @return 保持する場合はtrue, 保持しない場合はfalse
	 */
	public static boolean showArrayKeepSelectDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("この配列を保持しますか？"));
		pane.add(createPaddingPanel(WIDTH, 20, UIManager.getColor("control")));

		JPanel subPane = new JPanel();
		subPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		subPane.setPreferredSize(new Dimension(WIDTH - 10, 200));
		subPane.setBackground(Color.WHITE);

		subPane.add(new JLabel("配列を保持すると以下のことができるようになります"));
		subPane.add(createPaddingPanel(WIDTH, 15, Color.WHITE));

		JLabel label1 = new JLabel("●メソッド呼び出しの引数に指定");
		label1.setFont(LARGE_FONT);
		subPane.add(label1);

		JTextArea txtArea1 = new JTextArea("呼び出し時は引数の欄にオブジェクトIDを入力してください");
		txtArea1.setFont(PLAIN_FONT);
		txtArea1.setLineWrap(true);
		txtArea1.setFocusable(false);
		txtArea1.setEditable(false);
		txtArea1.setPreferredSize(new Dimension(WIDTH, 30));
		subPane.add(txtArea1);
		subPane.add(createPaddingPanel(WIDTH, 10, Color.WHITE));

		JLabel label2 = new JLabel("●任意のタイミングで配列を操作");
		label2.setFont(LARGE_FONT);
		subPane.add(label2);

		JTextArea txtArea2 = new JTextArea("「保持しているオブジェクト」の欄の対象オブジェクトをダブルクリックしてください");
		txtArea2.setFont(PLAIN_FONT);
		txtArea2.setLineWrap(true);
		txtArea2.setFocusable(false);
		txtArea2.setEditable(false);
		txtArea2.setPreferredSize(new Dimension(WIDTH - 20, 30));
		subPane.add(txtArea2);
		pane.add(subPane);

		int result = JOptionPane.showConfirmDialog(parent, pane, "配列の生成に成功しました",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// 「はい」が選択された場合
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * メソッド呼び出しに成功した場合のダイアログを表示します<br>
	 * 戻り値がvoid型の場合はこちらを呼び出します
	 * @param parent 親コンポーネント
	 * @param obj 生成されたオブジェクト
	 */
	public static void showMethodDialog(Component parent) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("戻り値"));
		JTextArea retVal = new JTextArea("void");
		retVal.setFont(LARGE_FONT);
		retVal.setBackground(Color.WHITE);
		retVal.setPreferredSize(new Dimension(WIDTH - 10, 22));
		retVal.setFocusable(false);
		retVal.setEditable(false);
		pane.add(retVal);

		JOptionPane.showMessageDialog(parent, pane, "メソッドの呼び出しに成功しました",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * メソッド呼び出しに成功した場合のダイアログを表示します<br>
	 * ダイアログ上で戻り値を保持するか選択できます
	 * @param msg 表示するメッセージ
	 * @param obj 生成されたオブジェクト
	 * @return 保持する場合はtrue, 保持しない場合はfalse
	 */
	public static boolean showMethodDialog(Component parent, Object obj) {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		pane.setPreferredSize(DIMENSION);

		pane.add(new JLabel("戻り値"));
		JTextArea retVal = new JTextArea(obj.toString());
		retVal.setFont(LARGE_FONT);
		retVal.setBackground(Color.WHITE);
		retVal.setPreferredSize(new Dimension(WIDTH - 10, 22));
		retVal.setFocusable(false);
		retVal.setEditable(false);
		pane.add(retVal);
		pane.add(createPaddingPanel(WIDTH, 30, UIManager.getColor("control")));

		pane.add(new JLabel("このオブジェクトを保持しますか？"));
		JTextArea msg = new JTextArea("戻り値が基本データ型の場合はラッパークラスのオブジェクトが保持されます");
		msg.setFont(PLAIN_FONT);
		msg.setBackground(UIManager.getColor("control"));
		msg.setPreferredSize(new Dimension(WIDTH - 10, 30));
		msg.setLineWrap(true);
		msg.setFocusable(false);
		msg.setEditable(false);
		pane.add(msg);

		int result = JOptionPane.showConfirmDialog(parent, pane, "メソッドの呼び出しに成功しました",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// 「はい」が選択された場合
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 配列要素の保持に成功したときに表示するダイアログを表示します
	 * @param msg 表示するメッセージ
	 */
	public static void showArrayItemKeepingDialog(Component parent, String msg) {
		JTextArea txtArea = new JTextArea();
		txtArea.setText(msg);
		txtArea.setLineWrap(true);
		txtArea.setFocusable(false);
		txtArea.setEditable(false);
		txtArea.setBackground(UIManager.getColor("control"));
		JScrollPane scrollpane = new JScrollPane(txtArea);
		scrollpane.setPreferredSize(DIMENSION);
		JOptionPane.showMessageDialog(parent, scrollpane, "配列要素を保持しました", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 例外がスローされたときに表示するダイアログを表示します
	 * @param msg 表示するメッセージ
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
		JOptionPane.showMessageDialog(parent, scrollpane, "例外がスローされました", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 余白用のパネルを生成します
	 * @param width 余白の幅
	 * @param height 余白の高さ
	 * @param color 背景色
	 * @return
	 */
	private static JPanel createPaddingPanel(int width, int height, Color color) {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(width, height));
		pane.setBackground(color);
		return pane;
	}
}
