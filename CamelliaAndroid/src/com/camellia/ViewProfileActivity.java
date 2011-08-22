package com.camellia;

import static com.camellia.logging.Logging.log;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ViewProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
	}

	public void gotoBack(View view) {
		log("gotoBack");
		finish();
	}

	public void gotoPreviousProfile(View view) {
		log("gotoPreviousProfile");
	}

	public void gotoNextProfile(View view) {
		log("gotoNextProfile");
	}

	public void gotoAddProfileProfile(View view) {
		log("gotoAddProfileProfile");
	}

	public void gotoMoreProfile(View view) {
		log("gotoMoreProfile");
	}
}