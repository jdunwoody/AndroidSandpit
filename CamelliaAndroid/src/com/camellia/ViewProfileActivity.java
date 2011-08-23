package com.camellia;

import static com.camellia.logging.Logging.log;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.action.ViewProfileAction;
import com.camellia.domain.Profile;
import com.camellia.header.HeaderRowDelegate;
import com.camellia.header.HeaderRowSupported;
import com.camellia.profile.ViewProfileService;

public class ViewProfileActivity extends Activity implements HeaderRowSupported {

	private HeaderRowDelegate headerRowDelegate;
	private ViewProfileService viewProfileService;

	public ViewProfileActivity() {
		log("ViewProfileActivity...");
	}

	public void goBack(View view) {
		log("gotoBack");
		finish();
	}

	@Override
	public void goHome(View view) {
		headerRowDelegate.handleGoHome();
	}

	public void gotoAddProfileProfile(View view) {
		log("gotoAddProfileProfile");
	}

	public void gotoMoreProfile(View view) {
		log("gotoMoreProfile");
	}

	public void gotoNextProfile(View view) {
		log("gotoNextProfile");
	}

	public void gotoPreviousProfile(View view) {
		log("gotoPreviousProfile");
	}

	@Override
	public void onProfileMenu(View view) {
		headerRowDelegate.handleProfileMenu();
	}

	@Override
	public void onSearchPopup(View view) {
		headerRowDelegate.handleSearchPopup((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), (ViewGroup) findViewById(R.id.header_row));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
		headerRowDelegate = new HeaderRowDelegate(this);

		String profileId = getIntent().getStringExtra(ViewProfileAction.PROFILE_ID);
		Profile profile = viewProfileService.load(profileId, profileId);

		// findViewById(R.id.profile_vanity_url);
	}
}