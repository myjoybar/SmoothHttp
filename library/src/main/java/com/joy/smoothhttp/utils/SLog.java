package com.joy.smoothhttp.utils;

import android.util.Log;

/**
 * Created by joybar on 2018/5/17.
 */

public class SLog {
	private static boolean enable = true;
	private static final String TAG = "SmoothHttpClient";

	public static void setEnable(boolean enable) {
		SLog.enable = enable;
	}

	public static void printInfo(String msg) {
		if (enable) {
			Log.d(TAG, msg);
		}
	}
	public static void printError(String msg) {
		if (enable) {
			Log.e(TAG, msg);
		}
	}
	public static void printWarning(String message, Object... args) {
		if (enable) {
			if (args.length > 0) {
				message = String.format(message, args);
			}
			Log.e(TAG, message);
		}
	}
}
