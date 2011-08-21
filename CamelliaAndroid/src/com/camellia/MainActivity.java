package com.camellia;

import com.camellia.header.HeaderRowDelegate;
import com.camellia.header.HeaderRowSupported;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity implements HeaderRowSupported {
	public static final String ADDRESS_FIELD = "address";
	public static final String NAME_FIELD = "name";
	private final MainActivity mainActivity;
	private EditText nameInput;
	private EditText locationInput;
	private HeaderRowDelegate headerRowDelegate;

	public MainActivity() {
		mainActivity = this;
		this.headerRowDelegate = new HeaderRowDelegate(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageButton searchButton = (ImageButton) findViewById(R.id.search);
		nameInput = (EditText) findViewById(R.id.name_input);
		locationInput = (EditText) findViewById(R.id.address_input);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(mainActivity, SearchActivity.class);
				i.putExtra(NAME_FIELD, nameInput.getText().toString());
				i.putExtra(ADDRESS_FIELD, locationInput.getText().toString());
				startActivity(i);
			}
		});
	}

	@Override
	public void goHome(View view) {
		headerRowDelegate.handleGoHome();
	}
}