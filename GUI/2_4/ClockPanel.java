import java.awt.*;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.geom.Point2D;

import javax.swing.*;

public class ClockPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private SettingDataHolder mDataHolder;
	private Frame mParent;

	public ClockPanel(Frame parent, SettingDataHolder holder) {
		mParent = parent;
		mDataHolder = holder;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 文字列のサイズに応じて表示の設定値を更新
		FontMetrics fm = g.getFontMetrics(mDataHolder.getTimeFont()); // 時刻表示部分
		int timeWidth = fm.stringWidth(ClockUtil.getTime());
		int timeHeight = fm.getAscent();
		fm = g.getFontMetrics(mDataHolder.getDateFont());
		int dateWidth = fm.stringWidth(ClockUtil.getDate()); // 日付表示部分
		mDataHolder.updateComponentPosition(timeWidth, timeHeight, dateWidth);

		// ウィンドウサイズの変更
		changeWindowSize();

		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawBackground(g2); // 背景
		drawText(g2, ClockUtil.getTime(), mDataHolder.getTimeFont(), mDataHolder.getTimeX(),
				mDataHolder.getTimeY()); // 時刻
		drawText(g2, ClockUtil.getDate(), mDataHolder.getDateFont(), mDataHolder.getDateX(),
				mDataHolder.getDateY()); // 日付
		drawAnalogClock(g2); // アナログ時計
	}

	/**
	 * 文字列の大きさに応じてウィンドウを適切なサイズに変更します
	 */
	private void changeWindowSize() {
		mParent.setSize(mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
	}

	/**
	 * 背景に画像または色を設定します
	 * @param g2
	 */
	private void drawBackground(Graphics2D g2) {
		if (mDataHolder.isPicture()) {
			Image image = mDataHolder.getPicture();
			if (image != null) {
				g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		} else {
			g2.setColor(mDataHolder.getBackgroundColor());
			g2.fillRect(0, 0, getWidth(), getHeight());
		}
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
		Color textColor = mDataHolder.getFontColor();

		// 影
		int diff = fontSize / 30;
		g2.setColor(new Color(200, 200, 200, 80));
		g2.drawString(text, x + diff, y + diff);

		// 縁取り
		int width = fontSize / 55;
		if (width < 1) {
			width = 1;
		}
		g2.setColor(new Color(255, 255, 255));
		g2.drawString(text, x + width, y + width);
		g2.drawString(text, x + width, y - width);
		g2.drawString(text, x - width, y + width);
		g2.drawString(text, x - width, y - width);

		// 文字列の表示
		if (mDataHolder.isRainbow()) {
			// 虹色で文字列を描画
			int timeY = mDataHolder.getTimeY();
			int strHeight = mDataHolder.getStrHeight();
			LinearGradientPaint gradient = new LinearGradientPaint(
					new Point2D.Float(0, (int) (timeY * 1.1)),
					new Point2D.Float((int) (strHeight * 0.4), (int) (timeY * 1.1) + (int) (strHeight * 0.4)),
					RainbowUtil.DIST, RainbowUtil.COLORS, MultipleGradientPaint.CycleMethod.NO_CYCLE,
					ColorSpaceType.LINEAR_RGB, RainbowUtil.getAffineTransform());
			g2.setPaint(gradient);
		} else {
			// 単色で文字列を描画
			g2.setColor(textColor);
		}
		g2.drawString(text, x, y);
	}

	/**
	 * アナログ時計を表示します
	 * @param g2
	 */
	private void drawAnalogClock(Graphics2D g2) {
		// 表示位置を取得
		int centerX = mDataHolder.getAnalogX();
		int centerY = mDataHolder.getAnalogY();
		int radius = mDataHolder.getRadius();

		// 時刻を取得
		int hour = ClockUtil.getHour();
		int min = ClockUtil.getMinute();
		int sec = ClockUtil.getSecond();

		// 背景画像を使用している場合は外枠を表示
		if (mDataHolder.isPicture()) {
			g2.setColor(new Color(255, 255, 255, 150));
			int outerR = mDataHolder.getOuterOvalRadius();
			g2.fillOval(centerX - outerR, centerY - outerR, outerR * 2, outerR * 2);
		}
		g2.setColor(mDataHolder.getAnalogColor());

		// 中心を表示
		int centerR = mDataHolder.getCenterOvalRadius(); // 円の半径
		g2.fillOval(centerX - centerR, centerY - centerR, centerR * 2, centerR * 2);

		// 目盛りを表示
		int divisionR = mDataHolder.getDivisionRadius(); // 円の半径
		for (int i = 0; i < 12; i++) {
			double x = centerX + (Math.sin(Math.toRadians(i * 30))) * radius;
			double y = centerY - (Math.cos(Math.toRadians(i * 30))) * radius;
			g2.fillOval((int) x - divisionR, (int) y - divisionR, divisionR * 2, divisionR * 2);
		}

		// 各針の角度を計算
		double secX = centerX + (Math.sin(Math.toRadians(sec * 6))) * radius * 0.9;
		double secY = centerY - (Math.cos(Math.toRadians(sec * 6))) * radius * 0.9;
		double minX = centerX + (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius * 0.75;
		double minY = centerY - (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius * 0.75;
		double hourX = centerX + (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius * 0.6;
		double hourY = centerY - (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius * 0.6;

		// 秒針を表示
		g2.setStroke(new BasicStroke(mDataHolder.getSecLineWidth()));
		g2.drawLine(centerX, centerY, (int) secX, (int) secY);

		// 分針を表示
		g2.setStroke(new BasicStroke(mDataHolder.getMinLineWidth()));
		g2.drawLine(centerX, centerY, (int) minX, (int) minY);

		// 時針を表示
		g2.setStroke(new BasicStroke(mDataHolder.getHourLineWidth()));
		g2.drawLine(centerX, centerY, (int) hourX, (int) hourY);
	}
}