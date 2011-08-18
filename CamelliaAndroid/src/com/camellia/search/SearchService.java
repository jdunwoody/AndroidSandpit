package com.camellia.search;

import static com.camellia.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
	private final WebInteraction webInteraction;

	public SearchService(WebInteraction webInteraction) {
		this.webInteraction = webInteraction;
	}

	public List<SearchResult> search(String name, String address) {
		log("Searching");
		return parse(makeWebRequest(name, address));
	}

	private List<SearchResult> parse(String jsonResponse) {
		return new ArrayList<SearchResult>();
	}

	private String makeWebRequest(String name, String location) {
		String url = String.format("/search/people?name=%s&location=%s", name, location);
		try {
			return webInteraction.get(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
