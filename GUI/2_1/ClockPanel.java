import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class ClockPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int CENTER_X = 400; // アナログ時計の中心X座標
	private static final int CENTER_Y = 170; // アナログ時計の中心Y座標
	private static final int RADIUS = 110; // アナログ時計の半径
	private static final float SEC_LINE_WIDTH = 2.0f; // 秒針の幅
	private static final float MIN_LINE_WIDTH = 4.0f; // 分針の幅
	private static final float HOUR_LINE_WIDTH = 7.0f; // 時針の幅
	private static final int TIME_X = 100; // デジタル時計の起点X座標
	private static final int TIME_Y = 460; // デジタル時計の起点Y座標
	private static final int DATE_X = 200; // 日付部分の起点X座標
	private static final int DATE_Y = 530; // 日付部分の起点Y座標

	private static final Font FONT_TIME = new Font(Font.SANS_SERIF, Font.PLAIN, 150);
	private static final Font FONT_DATE = new Font(Font.SANS_SERIF, Font.PLAIN, 55);

	private Image mBackground = null;

	public ClockPanel() {
		URL url = getClass().getClassLoader().getResource("picture.jpg");
		ImageIcon icon;
		try {
			icon = new ImageIcon(url);
			mBackground = icon.getImage();
		} catch (NullPointerException e) {
			// ファイルが見つからなかった
			// 背景画像なしで時計が表示される
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(mBackground, 0, 0, getWidth(), getHeight(), this); // 背景
		drawText(g2, ClockUtil.getTime(), FONT_TIME, TIME_X, TIME_Y); // 時刻
		drawText(g2, ClockUtil.getDate(), FONT_DATE, DATE_X, DATE_Y); // 日付
		drawAnalogClock(g2); // アナログ時計
	}

	/**
	 * 文字列にエフェクトをつけて表示します
	 * @param g2 Graphics2Dオブジェクト
	 * @param text 表示する文字列
	 * @param font 表示フォント
	 * @param x 表示位置のx座標
	 * @param y 表示位置のy座標
	 */
	private void drawText(Graphics2D g2, String text, Font font, int x, int y) {
		g2.setFont(font);
		int fontSize = font.getSize();

		// 影
		int diff = fontSize / 30;
		g2.setColor(new Color(200, 200, 200, 80));
		g2.drawString(text, x + diff, y + diff);

		// 縁取り
		int width = fontSize / 75;
		if (width < 1) {
			width = 1;
		}
		g2.setColor(new Color(255, 255, 255));
		g2.drawString(text, x + width, y + width);
		g2.drawString(text, x + width, y - width);
		g2.drawString(text, x - width, y + width);
		g2.drawString(text, x - width, y - width);

		// 文字列の表示（グラデーション）
		Color blue1 = new Color(0, 102, 255);
		Color blue2 = new Color(51, 51, 255);
		FontMetrics fm = getFontMetrics(font);
		int textWidth = fm.stringWidth(text);
		int textHeight = fm.getHeight();
		g2.setPaint(new GradientPaint(x, y, blue1, x + textWidth, y + textHeight, blue2));
		g2.drawString(text, x, y);
	}

	/**
	 * アナログ時計を表示します
	 * @param g2
	 */
	private void drawAnalogClock(Graphics2D g2) {
		// 外枠を表示
		g2.setColor(new Color(255, 255, 255, 150));
		g2.fillOval(CENTER_X - RADIUS - 20, CENTER_Y - RADIUS - 20, RADIUS * 2 + 40, RADIUS * 2 + 40);
		g2.setColor(new Color(21, 116, 255));

		// 中心を表示
		g2.fillOval(CENTER_X - 8, CENTER_Y - 8, 16, 16);

		// 目盛りを表示
		g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		for (int i = 0; i < 12; i++) {
			double x = CENTER_X + (Math.sin(Math.toRadians(i * 30))) * RADIUS;
			double y = CENTER_Y - (Math.cos(Math.toRadians(i * 30))) * RADIUS;
			g2.fillOval((int) x - 5, (int) y - 5, 9, 9);
		}

		// 時刻を取得
		int hour = ClockUtil.getHour();
		int min = ClockUtil.getMinute();
		int sec = ClockUtil.getSecond();

		// 各針の角度を計算
		double secX = CENTER_X + (Math.sin(Math.toRadians(sec * 6))) * RADIUS * 0.9;
		double secY = CENTER_Y - (Math.cos(Math.toRadians(sec * 6))) * RADIUS * 0.9;
		double minX = CENTER_X + (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS * 0.75;
		double minY = CENTER_Y - (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS * 0.75;
		double hourX = CENTER_X + (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12)))) * RADIUS * 0.6;
		double hourY = CENTER_Y - (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12)))) * RADIUS * 0.6;

		// 秒針を表示
		g2.setStroke(new BasicStroke(SEC_LINE_WIDTH));
		g2.drawLine(CENTER_X, CENTER_Y, (int) secX, (int) secY);

		// 分針を表示
		g2.setStroke(new BasicStroke(MIN_LINE_WIDTH));
		g2.drawLine(CENTER_X, CENTER_Y, (int) minX, (int) minY);

		// 時針を表示
		g2.setStroke(new BasicStroke(HOUR_LINE_WIDTH));
		g2.drawLine(CENTER_X, CENTER_Y, (int) hourX, (int) hourY);
	}
}