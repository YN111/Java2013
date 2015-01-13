package com.example.javafinal.activity;

import com.example.javafinal.game.GameView;

import android.app.Activity;
import android.os.Bundle;

import android.widget.LinearLayout;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.addView(new GameView(this));
		setContentView(layout);
	}
}
