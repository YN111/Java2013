import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Clock extends Frame {

	private static final int WIDTH = 800; // GUIの幅
	private static final int HEIGHT = 600; // GUIの高さ
	private static final int CENTER_X = 400; // アナログ時計の中心X座標
	private static final int CENTER_Y = 170; // アナログ時計の中心Y座標
	private static final int RADIUS = 110; // アナログ時計の半径
	private static final float SEC_LINE_WIDTH = 2.0f; // 秒針の幅
	private static final float MIN_LINE_WIDTH = 4.0f; // 分針の幅
	private static final float HOUR_LINE_WIDTH = 7.0f; // 時針の幅
	private static final int TIME_X = 120; // デジタル時計の起点X座標
	private static final int TIME_Y = 460; // デジタル時計の起点Y座標
	private static final int TIME_STR_SIZE = 150; // デジタル時計の文字サイズ
	private static final int DATE_X = 300; // 日付部分の起点X座標
	private static final int DATE_Y = 530; // 日付部分の起点Y座標
	private static final int DATE_STR_SIZE = 40; // 日付部分の文字サイズ
	private final Color digiColor = new Color(0, 51, 204); // デジタル時計部の色(#0033cc)
	private final Color anaColor = new Color(170, 170, 170); // アナログ時計部の色(#AAAAAA)
	private static int preSec; // 前回paintメソッドが呼ばれた時の秒

	Image imgBuffer = null; // ちらつき防止用のバッファ
	Graphics2D gBuffer = null;

	/**
	 * コンストラクタ：GUIの設定と×ボタンで終了する処理を記述
	 */
	Clock() {
		// タイトル
		super("Clock");

		// サイズと色を指定
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);

		// Closeイベントを受け取るリスナー
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); // 時計の表示を終了
			}
		});
	}

	public void paint(Graphics g) {

		// バッファ用のイメージを作る
		if (imgBuffer == null) {
			imgBuffer = createImage(WIDTH, HEIGHT);
			gBuffer = null;
		}

		// バッファ用のグラフィックを作る
		if (gBuffer == null)
			gBuffer = (Graphics2D) imgBuffer.getGraphics();

		// バッファをクリアする
		gBuffer.clearRect(0, 0, WIDTH, HEIGHT);

		// 背景色を設定
		gBuffer.setColor(Color.BLACK);

		// アンチエイリアスON
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// 時刻を取得
		int hour = getHour();
		int min = getMinute();
		int sec = getSecond();
		int millisecond = getMilliSecond();
		preSec = sec;

		// デジタル時刻部分（アニメーションあり）
		gBuffer.setColor(digiColor); // 文字色の設定
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, TIME_STR_SIZE)); // フォントの設定

		// 時
		if (min == 59 && sec == 59 && millisecond > 600) {
			int hourY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int hourNewY = hourY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", hour), TIME_X, hourY);
			if (hour == 23) {
				gBuffer.drawString("00", TIME_X, hourNewY);
			} else {
				gBuffer.drawString(String.format("%02d", hour + 1), TIME_X,
						hourNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", hour), TIME_X, TIME_Y);
		}
		gBuffer.drawString(":", TIME_X + 140, TIME_Y);

		// 分
		if (sec == 59 && millisecond > 600) {
			int minY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int minNewY = minY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", min), TIME_X + 210, minY);
			if (min == 59) {
				gBuffer.drawString("00", TIME_X + 210, minNewY);
			} else {
				gBuffer.drawString(String.format("%02d", min + 1),
						TIME_X + 210, minNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", min), TIME_X + 210, TIME_Y);
		}
		gBuffer.drawString(":", TIME_X + 350, TIME_Y);

		// 秒
		if (millisecond > 600) {
			int secY = TIME_Y - TIME_STR_SIZE * (millisecond - 600) / 400;
			int secNewY = secY + TIME_STR_SIZE;
			gBuffer.drawString(String.format("%02d", sec), TIME_X + 420, secY);
			if (sec == 59) {
				gBuffer.drawString("00", TIME_X + 420, secNewY);
			} else {
				gBuffer.drawString(String.format("%02d", sec + 1),
						TIME_X + 420, secNewY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", sec), TIME_X + 420, TIME_Y);
		}

		// 文字が表示領域からはみ出る部分を塗りつぶす
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(0, TIME_Y, WIDTH, TIME_Y + TIME_STR_SIZE);
		gBuffer.fillRect(0, 0, WIDTH, TIME_Y - TIME_STR_SIZE);

		// 日付部分
		gBuffer.setColor(digiColor); // 文字色の設定
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, DATE_STR_SIZE)); // フォントの設定
		gBuffer.drawString(getDate(), DATE_X, DATE_Y); // 文字列の表示

		// アナログ時計部分
		// 表示色を設定
		gBuffer.setColor(anaColor);

		// 中心を表示
		gBuffer.fillOval(CENTER_X - 8, CENTER_Y - 8, 16, 16);

		// 外枠を表示
		gBuffer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		for (int i = 0; i < 12; i++) {
			double x = CENTER_X + (Math.sin(Math.toRadians(i * 30))) * RADIUS;
			double y = CENTER_Y - (Math.cos(Math.toRadians(i * 30))) * RADIUS;
			gBuffer.fillOval((int) x - 4, (int) y - 4, 8, 8);
		}

		// 各針の角度を計算
		double secX = CENTER_X + (Math.sin(Math.toRadians(sec * 6))) * RADIUS
				* 0.9;
		double secY = CENTER_Y - (Math.cos(Math.toRadians(sec * 6))) * RADIUS
				* 0.9;
		double minX = CENTER_X
				+ (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS
				* 0.75;
		double minY = CENTER_Y
				- (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * RADIUS
				* 0.75;
		double hourX = CENTER_X
				+ (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12))))
				* RADIUS * 0.6;
		double hourY = CENTER_Y
				- (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12))))
				* RADIUS * 0.6;

		// 秒針を表示
		gBuffer.setStroke(new BasicStroke(SEC_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) secX, (int) secY);

		// 分針を表示
		gBuffer.setStroke(new BasicStroke(MIN_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) minX, (int) minY);

		// 時針を表示
		gBuffer.setStroke(new BasicStroke(HOUR_LINE_WIDTH));
		gBuffer.drawLine(CENTER_X, CENTER_Y, (int) hourX, (int) hourY);

		// 描画
		g.drawImage(imgBuffer, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * 現在の日付を取得する
	 * 
	 * @return GUIに表示する日付部分の文字列
	 */
	private static String getDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "/" + month + "/" + date;
	}

	/**
	 * 現在の時刻（時）を取得する
	 * 
	 * @return Hour
	 */
	private static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 現在の時刻（分）を取得する
	 * 
	 * @return Minute
	 */
	private static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 現在の時刻（秒）を取得する
	 * 
	 * @return Second
	 */
	private static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	
	/**
	 * 現在の時刻（ミリ秒）を取得する
	 * 
	 * @return MilliSecond
	 */
	private static int getMilliSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * メインメソッド 時計のサイズを指定して表示する
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.show();
		while (true) {
			// 一定間隔ごとに情報を更新
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clock.repaint();
		}
	}
}
