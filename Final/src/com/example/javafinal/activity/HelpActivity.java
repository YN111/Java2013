package com.example.javafinal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.javafinal.R;

public class HelpActivity extends Activity {
	
	/** ガイドの種類 */
	enum Guide {
		/** プレイ方法 */
		Play,
		
		/** 標的の種類 */
		Target;
	}
	
	private Guide mGuide = Guide.Play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		final Button next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mGuide == Guide.Play) {
					// ガイドを切り替える
					mGuide = Guide.Target;
					next.setText("終了");
					ImageView view = (ImageView) findViewById(R.id.help_guide);
					view.setImageResource(R.drawable.help_guide2);
				} else if (mGuide == Guide.Target) {
					HelpActivity.this.finish();
				}
			}
		});
	}

}
