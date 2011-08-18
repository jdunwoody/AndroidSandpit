package com.camellia.logging;

import android.util.Log;

public class Logging {
	public static final String TAG = "CamelliaAndroid";

	public static void log(String message) {
		Log.i(TAG, message);
	}
}
