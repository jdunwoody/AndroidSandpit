package com.camellia.header;

import com.camellia.MainActivity;

import android.content.Context;
import android.content.Intent;

public class HeaderRowDelegate {
	private final Context context;

	public HeaderRowDelegate(Context context) {
		this.context = context;
	}

	public void handleGoHome() {
		Intent goHome = new Intent(context, MainActivity.class);
		goHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(goHome);
	}
}