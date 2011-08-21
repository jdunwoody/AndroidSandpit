package com.camellia.action;

import com.camellia.ViewProfileActivity;

import android.content.Context;
import android.content.Intent;

public class ViewProfileAction {
	public static final String PROFILE_ID = "profile_id";

	public void open(Context context, String profileId) {
		Intent viewProfile = new Intent(context, ViewProfileActivity.class);
		viewProfile.putExtra(ViewProfileAction.PROFILE_ID, profileId);

		context.startActivity(viewProfile);
	}
}
