package com.example.javafinal.util;

import android.util.Log;

public class Logger {

	private static final String TAG_PREF = "JavaFinal";
	private static final boolean DEBUG = true;

	public static void printDebugLog(String tag, String msg) {
		if (DEBUG) {
			Log.d(TAG_PREF + ":" + tag, msg);
		}
	}

	public static void printErrorLog(String tag, String msg, Exception e) {
		if (DEBUG) {
			Log.e(TAG_PREF + ":" + tag, msg, e);
		}
	}

}
