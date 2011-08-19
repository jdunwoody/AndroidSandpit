package com.camellia;

import static com.camellia.logging.Logging.log;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.camellia.logging.Logging;
import com.camellia.search.SearchResult;
import com.camellia.search.SearchResultAdapter;
import com.camellia.search.SearchResultListViewHolder;
import com.camellia.search.SearchResults;
import com.camellia.search.SearchService;
import com.camellia.search.WebInteraction;

public class SearchActivity extends ListActivity {
	private ProgressDialog progressDialog = null;
	private final SearchService search;
	private SearchResults searchResults;
	private SearchResultAdapter adapter;
	private Runnable showSearchResults;
	private ListView searchResultsListView;
	private ListView listView;
	private SendDetailsAction sendDetailsAction;
	private PhoneAction phoneAction;

	public SearchActivity() {
		HttpClient httpClient = new DefaultHttpClient();
		this.search = new SearchService(new WebInteraction(httpClient));
		this.sendDetailsAction = new SendDetailsAction();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		log("Creating searchActivity");
		searchResults = new SearchResults();
		this.adapter = new SearchResultAdapter(this, R.layout.search_result_row, searchResults);
		setListAdapter(this.adapter);

		listView = getListView();
		listView.setTextFilterEnabled(true);

		final Bundle searchParameters = getIntent().getExtras();

		showSearchResults = new Runnable() {
			@Override
			public void run() {
				performSearch(searchParameters);
				adapter.updateResults(searchResults);
			}
		};

		Thread thread = new Thread(null, showSearchResults, "MagentoBackground");
		thread.start();
		progressDialog = ProgressDialog.show(this, "Please wait...", "Retrieving data ...", true);
	}

	public void phoneClicked(View view) {
		log("Phone clicked");
		SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
		if (viewHolder != null) {
			sendDetailsAction.phone(this, viewHolder.name.getText().toString());
		}
	}

	public void mobileClicked(View view) {
		log("Mobile clicked");
		SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
		if (viewHolder != null) {
			sendDetailsAction.mobile(this, viewHolder.name.getText().toString());
		}
	}

	public void emailClicked(View view) {
		log("Email clicked");
		SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
		if (viewHolder != null) {
			sendDetailsAction.email(this, viewHolder.name.getText().toString());
		}
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		// getListView().getItemAtPosition(position)
		log("Clickity click from onListItemClick: " + id);

	}

	private void performSearch(Bundle searchParameters) {
		log("Performing search");
		try {
			String name = "smith";
			String address = "";
			if (searchParameters != null) {
				name = searchParameters.getString(MainActivity.NAME_FIELD);
				address = searchParameters.getString(MainActivity.ADDRESS_FIELD);
			}

			log("Search parameters supplied: " + name + " " + address);
			this.searchResults = search.search(name, address);
			log("found " + searchResults.getResults().size() + " from a total of " + searchResults.getTotalAvailable());
		} catch (Exception e) {
			Log.e(Logging.TAG, e.getMessage());
		}
		runOnUiThread(returnRes);
	}

	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			List<SearchResult> resultsList = searchResults.getResults();
			int resultsListSize = resultsList.size();

			if (resultsListSize > 0) {
				log("Data has changed in listview");
				adapter.notifyDataSetChanged();

				log("Adding " + resultsListSize + " results");
				for (SearchResult result : resultsList) {
					adapter.add(result);
				}
			}
			progressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};
}