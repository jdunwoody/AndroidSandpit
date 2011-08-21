package com.camellia.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
	private boolean hasMore;
	private int totalAvailable;
	private final List<SearchResult> results;

	public SearchResults() {
		this.results = new ArrayList<SearchResult>();
	}

	public SearchResults(SearchResult searchResult) {
		this();
		this.results.add(searchResult);
		this.setTotalAvailable(1);
		this.setHasMore(false);
	}

	public boolean hasMore() {
		return hasMore;
	}

	public int getTotalAvailable() {
		return totalAvailable;
	}

	public List<SearchResult> getResults() {
		return results;
	}

	public void add(SearchResult searchResult) {
		results.add(searchResult);
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public void setTotalAvailable(int totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
}