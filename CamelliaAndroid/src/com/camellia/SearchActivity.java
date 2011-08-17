package com.camellia;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.camellia.search.SearchResult;
import com.camellia.search.SearchService;

public class SearchActivity extends Activity {

    private final SearchService search;

    public SearchActivity(SearchService search) {
        this.search = search;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Bundle searchParameters = getIntent().getExtras();
        if (searchParameters != null) {
            String name = searchParameters.getString(MainActivity.NAME_FIELD);
            String address = searchParameters.getString(MainActivity.ADDRESS_FIELD);

            List<SearchResult> results = search.search(name, address);
            LinearLayout resultsLayout = (LinearLayout) findViewById(R.id.results);
            LinearLayout searchResultLayout = (LinearLayout) findViewById(R.layout.search_result);

            for (SearchResult result : results) {
                View.inflate(this, resource, searchResultLayout)
                resultsLayout.addView(searchResultLayout);
            }
        }
    }
}