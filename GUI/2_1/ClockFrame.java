import java.awt.*;
import javax.swing.*;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_GUI_WIDTH = 800; // GUIの幅
	private static final int DEFAULT_GUI_HEIGHT = 600; // GUIの高さ

	private ClockPanel mContentPane;

	ClockFrame() {
		super("Clock");
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 表示位置
		GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode displayMode = graphicEnv.getDefaultScreenDevice().getDisplayMode();
		int x = displayMode.getWidth() / 2 - DEFAULT_GUI_WIDTH / 2;
		int y = displayMode.getHeight() / 2 - DEFAULT_GUI_HEIGHT / 2;
		setLocation(x, y);

		mContentPane = new ClockPanel();
		setContentPane(mContentPane);
		setBackground(Color.BLACK);
		new TimerThread().start();
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
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ClockFrame().setVisible(true);
	}
}