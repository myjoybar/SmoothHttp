package com.joy.smoothhttp.utils;

import android.util.Log;

/**
 * Created by joybar on 2018/5/17.
 */

public class SLog {
	private static boolean enable = true;
	private static final String TAG = "Smoothhttp";

	public static void setEnable(boolean enable) {
		SLog.enable = enable;
	}

	public static void print(String msg) {
		if (enable) {
			Log.d(TAG, msg);
		}
	}

}