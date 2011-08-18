package com.camellia.search;

import static com.camellia.logging.Logging.log;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.camellia.R;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {

	private List<SearchResult> items;

	public SearchResultAdapter(Context context, int textViewResourceId, List<SearchResult> items) {
		super(context, textViewResourceId, items);
		log("Creating searchResultAdapter");
		this.items = items;
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
		SearchResult searchResult = items.get(position);
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