import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ClockWindow extends JWindow {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_GUI_WIDTH = 655; // GUIの幅の初期値
	private static final int DEFAULT_GUI_HEIGHT = 520; // GUIの高さの初期値

	private ClockPanel mContentPane;
	private JPopupMenu mPopupMenu;
	private SettingDataHolder mDataHolder;

	ClockWindow() {
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);

		// メニューの設定
		mDataHolder = new SettingDataHolder();
		mPopupMenu = new ClockPopupMenu(mDataHolder);

		// 表示位置
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int x = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int y = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(x, y);

		// マウスイベント
		ClockMouseListener mouseListener = new ClockMouseListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

		mContentPane = new ClockPanel(this, mDataHolder);
		setContentPane(mContentPane);
		setBackground(Color.BLACK);
		new TimerThread().start();
	}

	/**
	 * 背景の形状と不透明度を変更します
	 */
	public void updateShape(float opacity, Shape shape) {
		setOpacity(opacity);
		setShape(shape);
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
	 * マウスイベントおよびマウスのモーションイベントに関する処理を定義します
	 */
	private class ClockMouseListener implements MouseListener, MouseMotionListener {
		Point startDragPos;
		Point startGuiPos;
		boolean leftClickFlag;

		@Override
		public void mouseClicked(MouseEvent e) {
			// Nothing to do
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// Nothing to do
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Nothing to do
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) { // 左クリック
				startDragPos = e.getLocationOnScreen();
				startGuiPos = getLocation();
				leftClickFlag = true;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) { // 左クリックが離された
				leftClickFlag = false;
			} else if (e.isPopupTrigger()) { // ポップアップトリガー
				mPopupMenu.show(ClockWindow.this, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (leftClickFlag == true) { // 左ドラッグ
				int dx = e.getXOnScreen() - startDragPos.x;
				int dy = e.getYOnScreen() - startDragPos.y;
				setLocation(startGuiPos.x + dx, startGuiPos.y + dy);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// Nothing to do
		}
	}

	public static void main(String[] args) {
		new ClockWindow().setVisible(true);
	}
}