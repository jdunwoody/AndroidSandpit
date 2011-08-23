package com.camellia.action;

import static com.camellia.logging.Logging.log;
import android.content.Context;
import android.content.Intent;

import com.camellia.ViewProfileActivity;

public class ViewProfileAction {
	public static final String PROFILE_ID = "profile_id";

	public void open(Context context, String profileId) {
		log("ViewProfileAction with profileid " + profileId + " and context: " + context);
		Intent viewProfile = new Intent(context, ViewProfileActivity.class);
		viewProfile.putExtra(ViewProfileAction.PROFILE_ID, profileId);

		log("ViewProfileAction starting activity");

		context.startActivity(viewProfile);
	}
}
