package com.camellia.search;

import static com.camellia.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

	public List<SearchResult> search(String name, String address) {
		log("Searching");
		List<SearchResult> result = new ArrayList<SearchResult>();
		for (int i = 0; i < 10; i++) {
			result.add(new SearchResult("Adam Smith" + i));
		}
		return result;
	}
}
