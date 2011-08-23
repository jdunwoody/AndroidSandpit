package com.camellia.search;

import static com.camellia.logging.Logging.log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.camellia.R;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {
	private final LayoutInflater layoutInflator;
	private SearchResults results;

	public SearchResultAdapter(Context context, int textViewResourceId, SearchResults searchResults) {
		super(context, textViewResourceId, searchResults.getResults());
		results = searchResults;

		layoutInflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// View row = super.getView(position, convertView, parent);
		// SearchResultListViewHolder holder = (SearchResultListViewHolder) row.getTag();

		SearchResultListViewHolder holder;

		if (convertView == null) {
			convertView = layoutInflator.inflate(R.layout.search_result_row, null);

			// Creates a ViewHolder and store references to the two children views we want to bind data to.
			holder = new SearchResultListViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.search_result_name);

			convertView.setTag(holder);

			ImageView phone = (ImageView) convertView.findViewById(R.id.phone);
			log("Adding holder to phone");
			phone.setTag(holder);

			ImageView email = (ImageView) convertView.findViewById(R.id.email);
			log("Adding holder to email");
			email.setTag(holder);

			ImageView mobile = (ImageView) convertView.findViewById(R.id.mobile);
			log("Adding holder to mobile");
			mobile.setTag(holder);

		} else {
			// Get the ViewHolder back to get fast access to the TextView and the ImageView.
			holder = (SearchResultListViewHolder) convertView.getTag();
		}

		holder.name.setText(results.getResults().get(position).getName());

		return convertView;
	}

	public void updateResults(SearchResults searchResults) {
		results = searchResults;
	}

	// public View getView2(int position, View convertView, ViewGroup parent) {
	// View listViewRow = convertView;
	// if (listViewRow == null) {
	// LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// listViewRow = vi.inflate(R.layout.search_result_row, null);
	// View phone = listViewRow.findViewById(R.id.phone);
	// // phone.
	// }
	// SearchResult searchResult = results.getResults().get(position);
	// if (searchResult != null) {
	// TextView name = (TextView) listViewRow.findViewById(R.id.search_result_name);
	// if (name != null) {
	// name.setText(searchResult.getName());
	// }
	// }
	// return listViewRow;
	// }

	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	//
	// ViewHolder holder;
	//
	// if (contentView == null) {
	// holder = new ViewHolder();
	// contentView = inflater.inflate(R.layout.my_magic_list, null);
	// holder.label = (TextView) contentView.findViewById(R.id.label);
	// contentView.setTag(holder);
	// } else {
	// holder = (ViewHolder) contentView.getTag();
	// }
	//
	// holder.label.setText(getLabel());
	//
	// return contentView;
	// }

}