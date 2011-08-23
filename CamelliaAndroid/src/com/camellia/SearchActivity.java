package com.camellia;

import static com.camellia.logging.Logging.log;

import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.camellia.action.SendDetailsAction;
import com.camellia.action.ViewProfileAction;
import com.camellia.header.HeaderRowDelegate;
import com.camellia.header.HeaderRowSupported;
import com.camellia.http.WebInteraction;
import com.camellia.logging.Logging;
import com.camellia.search.SearchResult;
import com.camellia.search.SearchResultAdapter;
import com.camellia.search.SearchResultListViewHolder;
import com.camellia.search.SearchResults;
import com.camellia.search.SearchService;

public class SearchActivity extends ListActivity implements HeaderRowSupported {
    private SearchResultAdapter     adapter;
    private final HeaderRowDelegate headerRowDelegate;
    private TextView                hiSearchResultsCountView;
    private ListView                listView;
    private ProgressDialog          progressDialog = null;

    private final Runnable          returnRes      = new Runnable() {
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

    private final SearchService     search;
    private SearchResults           searchResults;
    private final SendDetailsAction sendDetailsAction;
    private Runnable                showSearchResults;
    private TextView                totalSearchResultsCountView;

    private final ViewProfileAction viewDetailsAction;

    public SearchActivity() {
        search = new SearchService(new WebInteraction(new DefaultHttpClient()));
        sendDetailsAction = new SendDetailsAction();
        viewDetailsAction = new ViewProfileAction();
        headerRowDelegate = new HeaderRowDelegate(this);
    }

    public void emailClicked(View view) {
        log("Email clicked");
        SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
        if (viewHolder != null) {
            sendDetailsAction.email(this, viewHolder.name.getText().toString());
        }
    }

    @Override
    public void goHome(View view) {
        headerRowDelegate.handleGoHome();
    }

    public void mobileClicked(View view) {
        log("Mobile clicked");
        SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
        if (viewHolder != null) {
            sendDetailsAction.mobile(this, viewHolder.name.getText().toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        log("Creating searchActivity");
        searchResults = new SearchResults();
        adapter = new SearchResultAdapter(this, R.layout.search_result_row, searchResults);
        setListAdapter(adapter);

        listView = getListView();
        listView.setTextFilterEnabled(true);

        totalSearchResultsCountView = (TextView) findViewById(R.id.search_results_stats_total);
        hiSearchResultsCountView = (TextView) findViewById(R.id.search_results_stats_hi);

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

    @Override
    public void onProfileMenu(View view) {
        headerRowDelegate.handleProfileMenu();
    }

    @Override
    public void onSearchPopup(View view) {
        headerRowDelegate.handleSearchPopup((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                (ViewGroup) findViewById(R.id.header_row));
    }

    public void phoneClicked(View view) {
        log("Phone clicked");
        SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
        if (viewHolder != null) {
            sendDetailsAction.phone(this, viewHolder.name.getText().toString());
        }
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        log("Search result clicked");

        SearchResultListViewHolder viewHolder = (SearchResultListViewHolder) view.getTag();
        if (viewHolder != null) {
            log("Opening viewHolder");
            viewDetailsAction.open(this, viewHolder.name.getText().toString());
        }
    }

    private void performSearch(Bundle searchParameters) {
        log("Performing search");
        try {
            String name = "default";
            String address = "";
            if (searchParameters != null) {
                name = searchParameters.getString(MainActivity.NAME_FIELD);
                address = searchParameters.getString(MainActivity.ADDRESS_FIELD);
            }

            log("Search parameters supplied: " + name + " " + address);
            searchResults = search.search(name, address);
            updateStats();
            log("found " + searchResults.getResults().size() + " from a total of " + searchResults.getTotalAvailable());
        } catch (Exception e) {
            Log.e(Logging.TAG, e.getMessage());
            searchResults = new SearchResults(new SearchResult("Sample Result"));
        }
        runOnUiThread(returnRes);
    }

    private void updateStats() {
        totalSearchResultsCountView.setText(searchResults.getTotalAvailable());
        hiSearchResultsCountView.setText(searchResults.getTotalAvailable());
    }
}