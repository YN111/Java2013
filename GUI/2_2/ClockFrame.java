import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_GUI_WIDTH = 814; // GUIの幅
	private static final int DEFAULT_GUI_HEIGHT = 638; // GUIの高さ

	private ClockPanel mContentPane;
	private Dialog mDialog;

	ClockFrame() {
		super("Clock");
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// メニューバーの設定
		SettingDataHolder holder = new SettingDataHolder();
		mDialog = new PropertyDialog(this, holder);
		initMenu();

		// 表示位置
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int x = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int y = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(x, y);

		mContentPane = new ClockPanel(this, holder);
		setContentPane(mContentPane);
		setBackground(Color.BLACK);
		new TimerThread().start();
	}

	/**
	 * メニューバーの設定を行う
	 */
	private void initMenu() {
		MenuBar bar = new MenuBar();
		bar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		Menu menu = new Menu("メニュー");

		// プロパティボタンを追加
		MenuItem property = new MenuItem("プロパティ");
		property.addActionListener(new PropertyActionListener());
		menu.add(property);
		bar.add(menu);

		this.setMenuBar(bar);
	}

	/**
	 * 一定間隔ごとに時計の表示を更新します
	 */
	private class TimerThread extends Thread {
		@Override
		public void run() {
			while (true) {
				mContentPane.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * メニューバーのプロパティボタンが押された時の処理を定義します
	 */
	private class PropertyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mDialog.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new ClockFrame().setVisible(true);
	}
}