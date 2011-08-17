package com.camellia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CamelliaAndroidActivity extends Activity {
	private CamelliaAndroidActivity mainActivity;

	public CamelliaAndroidActivity() {
		this.mainActivity = this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button searchButton = (Button) findViewById(R.drawable.search);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(mainActivity, screen2.class);
				startActivity(i);
			}
		});
	}
}