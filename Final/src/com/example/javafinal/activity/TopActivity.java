package com.example.javafinal.activity;

import com.example.javafinal.R;
import com.example.javafinal.util.Logger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TopActivity extends Activity {

	private static final String TAG = "TopActivity";

	private Handler mHandler = new Handler();
	private Time mTime = new Time();
	private Runnable mTimeUpdateTask;
	private TextView mTimeText;
	private boolean mTimeUpdateTaskRunning;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top);
		Logger.printDebugLog(TAG, "onCreate");

		// ボタンの初期化
		initButton(R.id.top_start, GameActivity.class); // スタート
		initButton(R.id.top_help, HelpActivity.class); // ヘルプ

		// 時刻表示の初期化
		mTimeText = (TextView) findViewById(R.id.time);
		updateTime();
		mTimeUpdateTask = new Runnable() {
			@Override
			public void run() {
				if (mTimeUpdateTaskRunning) {
					updateTime();
					mHandler.postDelayed(mTimeUpdateTask, 1000); // 1秒ごとに時刻更新
				}
			}
		};
	}

	@Override
	public void onResume() {
		super.onResume();
		mTimeUpdateTaskRunning = true;
		mTimeUpdateTask.run();
	}

	@Override
	public void onPause() {
		super.onPause();
		mTimeUpdateTaskRunning = false;
	}

	/**
	 * ボタンを初期化します。
	 * @param id ボタンのID
	 * @param nextActivity タップされた時に起動されるActivity
	 */
	private void initButton(int id, final Class<? extends Activity> nextActivity) {
		ImageButton button = (ImageButton) findViewById(id);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TopActivity.this, nextActivity);
				TopActivity.this.startActivity(intent);
			}
		});
	}

	/**
	 * 時刻の表示を更新します。
	 */
	@SuppressLint("DefaultLocale")
	private void updateTime() {
		mTime.setToNow();
		String time = String.format("%d:%02d:%02d", mTime.hour, mTime.minute, mTime.second);
		mTimeText.setText(time);
		Logger.printDebugLog(TAG, time);
	}

}
