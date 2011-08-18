package com.camellia.search;

import static com.camellia.logging.Logging.log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.camellia.R;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {
	private SearchResults results;

	public SearchResultAdapter(Context context, int textViewResourceId, SearchResults searchResults) {
		super(context, textViewResourceId, searchResults.getResults());
		log("Creating searchResultAdapter");
		this.results = searchResults;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		log("getting SearchResultsAdapter view");
		View v = convertView;
		if (v == null) {
			log("inflating search result row");
			LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.search_result_row, null);
		}
		SearchResult searchResult = results.getResults().get(position);
		log("search results are: " + searchResult);
		if (searchResult != null) {
			TextView name = (TextView) v.findViewById(R.id.search_result_name);
			if (name != null) {
				name.setText(searchResult.getName());
			}
		}
		return v;
	}
}