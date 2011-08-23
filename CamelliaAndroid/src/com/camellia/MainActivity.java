package com.camellia;

import static com.camellia.logging.Logging.log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.camellia.header.HeaderRowDelegate;
import com.camellia.header.HeaderRowSupported;

public class MainActivity extends Activity implements HeaderRowSupported, TextWatcher {
	public static final String ADDRESS_FIELD = "address";
	public static final String NAME_FIELD = "name";
	private final HeaderRowDelegate headerRowDelegate;
	private EditText locationInput;
	private final MainActivity mainActivity;
	private EditText nameInput;
	private ImageButton searchButton;

	public MainActivity() {
		mainActivity = this;
		headerRowDelegate = new HeaderRowDelegate(this);
	}

	@Override
	public void afterTextChanged(Editable editable) {
		if (nameInput.getText().length() > 0) {
			log("Enabling search button");
			enableSearchButton(true);
		} else {
			log("Enabling search button");
			enableSearchButton(false);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void goHome(View view) {
		headerRowDelegate.handleGoHome();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		searchButton = (ImageButton) findViewById(R.id.search);
		nameInput = (EditText) findViewById(R.id.name_input);
		nameInput.addTextChangedListener(this);
		locationInput = (EditText) findViewById(R.id.address_input);
		locationInput.addTextChangedListener(this);

		enableSearchButton(false);
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
	public void onProfileMenu(View view) {
		headerRowDelegate.handleProfileMenu();
	}

	@Override
	public void onSearchPopup(View view) {
		headerRowDelegate.handleSearchPopup((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), (ViewGroup) findViewById(R.id.header_row));
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	private void enableSearchButton(boolean state) {
		searchButton.setEnabled(state);
		searchButton.setClickable(state);
	}
}