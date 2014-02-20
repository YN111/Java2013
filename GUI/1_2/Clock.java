import java.awt.*;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Clock extends Frame implements ActionListener {

	public static final int DEFAULT_GUI_WIDTH = 750; // GUI幅の初期値
	public static final int DEFAULT_GUI_HEIGHT = 600; // GUI高さの初期値
	public static final int MAX_GUI_WIDTH = 1920; // GUI幅の最大値
	public static final int MAX_GUI_HEIGHT = 1080; // GUI高さの最大値

	private Image imgBuffer = null; // ちらつき防止用のバッファ
	private Graphics2D gBuffer = null;
	private Dialog mDialog;
	private SettingDataHolder mDataHolder;

	/**
	 * コンストラクタ：GUIの設定と×ボタンで終了する処理を記述
	 */
	Clock() {
		// タイトル
		super("Clock");

		// サイズと色を指定
		setSize(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT);
		setResizable(false); // サイズ変更不可
		setBackground(Color.BLACK);

		mDataHolder = new SettingDataHolder();// DataHolderオブジェクトの初期化
		mDialog = new PropertyDialog(this, mDataHolder);// ダイアログオブジェクトの初期化
		initMenu();// メニューバーを設定

		// Closeイベントを受け取るリスナー
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); // 時計の表示を終了
			}
		});
	}

	/**
	 * 文字の幅に応じてウィンドウを適切なサイズに変更
	 */
	private void changeWindowSize() {
		setSize(mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
	}

	/**
	 * メニューバーの設定を行う
	 */
	public void initMenu() {
		MenuBar bar = new MenuBar();
		bar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		Menu menu = new Menu("メニュー");
		MenuItem property = new MenuItem("プロパティ");

		// リスナー追加
		property.addActionListener(this);

		// 項目を追加
		menu.add(property);
		bar.add(menu);

		// 表示
		this.setMenuBar(bar);
	}

	/**
	 * ActionEventを受け取ったらプロパティダイアログを開く
	 */
	public void actionPerformed(ActionEvent e) {
		mDialog.setVisible(true);
	}

	/**
	 * 描画を行う
	 */
	public void paint(Graphics g) {

		// 文字の幅に応じて表示の設定値を更新
		FontMetrics fm = g.getFontMetrics(mDataHolder.getTimeFont());
		int strNumWidth = fm.stringWidth("8"); // 数字の幅
		int strColonWidth = fm.stringWidth(":"); // コロンの幅
		int strHeight = fm.getAscent();
		fm = g.getFontMetrics(mDataHolder.getDateFont());
		int dateWidth = fm.stringWidth(ClockUtil.getDate());
		mDataHolder.renewViewPoint(strNumWidth, strColonWidth, strHeight, dateWidth);

		// ウィンドウサイズの変更
		changeWindowSize();

		// バッファ用のイメージを作る
		if (imgBuffer == null) {
			imgBuffer = createImage(MAX_GUI_WIDTH, MAX_GUI_HEIGHT);
			gBuffer = null;
		}

		// バッファ用のグラフィックを作る
		if (gBuffer == null)
			gBuffer = (Graphics2D) imgBuffer.getGraphics();

		// バッファをクリアする
		gBuffer.clearRect(0, 0, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());

		// 背景色を更新
		gBuffer.setColor(mDataHolder.getBackgroundColor());
		gBuffer.fillRect(0, 0, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());

		// アンチエイリアスON
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 時刻を取得
		int hour = ClockUtil.getHour();
		int min = ClockUtil.getMinute();
		int sec = ClockUtil.getSecond();
		int millisecond = ClockUtil.getMilliSecond();

		// デジタル時刻部分
		if (mDataHolder.isRainbow()) {
			// 虹色の設定を行う
			LinearGradientPaint gradient = new LinearGradientPaint(new Point2D.Float(0,
					mDataHolder.getTimeY() * 11 / 10), new Point2D.Float(mDataHolder.getStrHeight() / 2,
					mDataHolder.getTimeY() * 11 / 10 + mDataHolder.getStrHeight() / 2), RainbowUtil.DIST,
					RainbowUtil.COLORS, MultipleGradientPaint.CycleMethod.NO_CYCLE, ColorSpaceType.LINEAR_RGB,
					RainbowUtil.getAffineTransform());
			gBuffer.setPaint(gradient);
		} else {
			gBuffer.setColor(mDataHolder.getFontColor()); // 文字色を単色で設定
		}
		gBuffer.setFont(mDataHolder.getTimeFont()); // フォントの設定

		// 表示位置を設定
		int digitalX; // x座標
		int digitalY = mDataHolder.getTimeY(); // y座標
		int digiBeforeY = digitalY - mDataHolder.getStrHeight() * (millisecond - 600) / 400; // アニメーション表示時の古い時刻
		int digiAfterY = digiBeforeY + mDataHolder.getStrHeight(); // アニメーション表示時の新しい時刻

		// 時
		digitalX = mDataHolder.getHourX(); // 描画位置のx座標
		if (min == 59 && sec == 59 && millisecond > 600) {
			gBuffer.drawString(String.format("%02d", hour), digitalX, digiBeforeY);
			if (hour == 23) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", hour + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", hour), digitalX, digitalY);
		}
		digitalX = mDataHolder.getHourColonX(); // 描画位置のx座標
		gBuffer.drawString(":", digitalX, digitalY);

		// 分
		digitalX = mDataHolder.getMinX(); // 描画位置のx座標
		if (sec == 59 && millisecond > 600) {
			gBuffer.drawString(String.format("%02d", min), digitalX, digiBeforeY);
			if (min == 59) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", min + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", min), digitalX, digitalY);
		}
		digitalX = mDataHolder.getMinColonX(); // 描画位置のx座標
		gBuffer.drawString(":", digitalX, digitalY);

		// 秒
		digitalX = mDataHolder.getSecX(); // 描画位置のx座標
		if (millisecond > 600) {
			gBuffer.drawString(String.format("%02d", sec), digitalX, digiBeforeY);
			if (sec == 59) {
				gBuffer.drawString("00", digitalX, digiAfterY);
			} else {
				gBuffer.drawString(String.format("%02d", sec + 1), digitalX, digiAfterY);
			}
		} else {
			gBuffer.drawString(String.format("%02d", sec), digitalX, digitalY);
		}

		// 文字が表示領域からはみ出る部分を塗りつぶす
		gBuffer.setColor(mDataHolder.getBackgroundColor());
		gBuffer.fillRect(0, digitalY + 5, mDataHolder.getGuiWidth(), mDataHolder.getGuiHeight());
		gBuffer.fillRect(0, 0, mDataHolder.getGuiWidth(), digitalY - mDataHolder.getStrHeight());

		// 日付部分
		if (mDataHolder.isRainbow())
			gBuffer.setColor(Color.BLUE); // 虹色設定時は青色に設定
		else
			gBuffer.setColor(mDataHolder.getFontColor()); // 文字色の設定
		gBuffer.setFont(mDataHolder.getDateFont()); // フォントの設定
		gBuffer.drawString(ClockUtil.getDate(), mDataHolder.getDateX(), mDataHolder.getDateY()); // 文字列を中央に表示

		// アナログ時計部分
		// 表示位置を設定
		int analogX = mDataHolder.getAnalogX();
		int analogY = mDataHolder.getAnalogY();
		int radius = mDataHolder.getRadius();

		// 表示色を設定
		gBuffer.setColor(mDataHolder.getAnalogColor());

		// 中心を表示
		int centerR = mDataHolder.getCenterOvalRadius(); // 円の半径
		gBuffer.fillOval(analogX - centerR / 2, analogY - centerR / 2, centerR, centerR);

		// 外枠を表示
		int outerR = mDataHolder.getOuterOvalRadius(); // 円の半径
		for (int i = 0; i < 12; i++) {
			double x = analogX + (Math.sin(Math.toRadians(i * 30))) * radius;
			double y = analogY - (Math.cos(Math.toRadians(i * 30))) * radius;
			gBuffer.fillOval((int) x - outerR / 2, (int) y - outerR / 2, outerR, outerR);
		}

		// 各針の角度を計算
		double secX = analogX + (Math.sin(Math.toRadians(sec * 6))) * radius * 0.9;
		double secY = analogY - (Math.cos(Math.toRadians(sec * 6))) * radius * 0.9;
		double minX = analogX + (Math.sin(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius * 0.75;
		double minY = analogY - (Math.cos(Math.toRadians(min * 6 + (sec * 6 / 60)))) * radius * 0.75;
		double hourX = analogX + (Math.sin(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius * 0.6;
		double hourY = analogY - (Math.cos(Math.toRadians(hour * 30 + (min * 6 / 12)))) * radius * 0.6;

		// 秒針を表示
		gBuffer.setStroke(new BasicStroke(mDataHolder.getSecLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) secX, (int) secY);

		// 分針を表示
		gBuffer.setStroke(new BasicStroke(mDataHolder.getMinLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) minX, (int) minY);

		// 時針を表示
		gBuffer.setStroke(new BasicStroke(mDataHolder.getHourLineWidth()));
		gBuffer.drawLine(analogX, analogY, (int) hourX, (int) hourY);

		// 描画
		g.drawImage(imgBuffer, 0, 0, this);
		System.out.println();
	}

	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * メインメソッド 時計のサイズを指定して表示する
	 */
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.setVisible(true);
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
