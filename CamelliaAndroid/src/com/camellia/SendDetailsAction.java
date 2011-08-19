package com.camellia;

import android.content.Context;
import android.content.Intent;

public class SendDetailsAction {
	public void email(Context context, String data) {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		// emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Whitepages");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message from whitepages. You sent details for: " + data);
		emailIntent.setType("text/plain");

		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

	public void mobile(Context context, String data) {
		Intent mobileIntent = new Intent(android.content.Intent.ACTION_CALL);
		mobileIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message from whitepages. You sent details for: " + data);
		mobileIntent.setType("text/plain");

		context.startActivity(Intent.createChooser(mobileIntent, "Call..."));
	}

	public void phone(Context context, String data) {
		Intent mobileIntent = new Intent(android.content.Intent.ACTION_CALL);
		mobileIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message from whitepages. You sent details for: " + data);
		mobileIntent.setType("text/plain");

		context.startActivity(Intent.createChooser(mobileIntent, "Call..."));
	}

}