package com.camellia;

import static com.camellia.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.camellia.logging.Logging;
import com.camellia.search.SearchResult;
import com.camellia.search.SearchResultAdapter;
import com.camellia.search.SearchService;
import com.camellia.search.WebInteraction;

public class SearchActivity extends ListActivity {
	private ProgressDialog progressDialog = null;
	private final SearchService search;
	private List<SearchResult> searchResults;
	private SearchResultAdapter adapter;
	private Runnable showSearchResults;

	public SearchActivity() {
		HttpClient httpClient = new DefaultHttpClient();
		this.search = new SearchService(new WebInteraction(httpClient));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		log("Creating searchActivity");
		searchResults = new ArrayList<SearchResult>();
		this.adapter = new SearchResultAdapter(this, R.layout.search_result_row, searchResults);
		setListAdapter(this.adapter);

		final Bundle searchParameters = getIntent().getExtras();

		showSearchResults = new Runnable() {
			@Override
			public void run() {
				performSearch(searchParameters);
			}
		};
		Thread thread = new Thread(null, showSearchResults, "MagentoBackground");
		thread.start();
		progressDialog = ProgressDialog.show(this, "Please wait...", "Retrieving data ...", true);
	}

	private void performSearch(Bundle searchParameters) {
		log("Performing search");
		try {
			String name = "default name";
			String address = "default address";
			if (searchParameters != null) {
				name = searchParameters.getString(MainActivity.NAME_FIELD);
				address = searchParameters.getString(MainActivity.ADDRESS_FIELD);
			}

			log("Search parameters supplied: " + name + " " + address);
			this.searchResults = search.search(name, address);
			log("found " + searchResults.size());
		} catch (Exception e) {
			Log.e(Logging.TAG, e.getMessage());
		}
		runOnUiThread(returnRes);
	}

	//
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.search_results);
	//
	// adapter = new SearchResultAdapter(this);
	// setListAdapter(adapter);
	//
	// Bundle searchParameters = getIntent().getExtras();
	// if (searchParameters != null) {
	// String name = searchParameters.getString(MainActivity.NAME_FIELD);
	// String address = searchParameters.getString(MainActivity.ADDRESS_FIELD);
	//
	// performSearch(name, address);
	// // List<SearchResult> results = search.search(name, address);
	// // LinearLayout resultsLayout = (LinearLayout)
	// // findViewById(R.id.results);
	// // LinearLayout searchResultLayout = (LinearLayout)
	// // findViewById(R.layout.search_result);
	// //
	// // for (SearchResult result : results) {
	// // View.inflate(this, resource, searchResultLayout)
	// // resultsLayout.addView(searchResultLayout);
	// // }
	// }
	// }

	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			if (searchResults != null && searchResults.size() > 0) {
				log("Data has changed in listview");
				adapter.notifyDataSetChanged();

				log("Adding " + searchResults.size() + " results");
				for (int i = 0; i < searchResults.size(); i++) {
					adapter.add(searchResults.get(i));
				}
			}
			progressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};

	// private void performSearch(String name, String address) {
	// ArrayAdapter adapter = new ArrayAdapter(this, data, listView, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
	// }
}
