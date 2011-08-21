package com.camellia.search;

import static com.camellia.logging.Logging.log;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.camellia.http.WebInteraction;
import com.camellia.logging.Logging;

public class SearchService {
	// public static final String HOST = "http://161.117.107.28:18080";
	public static final String HOST = "http://devc.people.whitepages.sensis.com.au";

	private final WebInteraction webInteraction;

	public SearchService(WebInteraction webInteraction) {
		this.webInteraction = webInteraction;
	}

	public SearchResults search(String name, String address) {
		log("Searching");
		try {
			return parse(makeWebRequest(name, address));
		} catch (Exception e) {
			Log.e(Logging.TAG, "Failed to parse search response: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String makeWebRequest(String name, String location) {

		String url = String.format(HOST + "/search/people?name=%s&location=%s", URLEncoder.encode(name), URLEncoder.encode(location));
		try {
			String response = webInteraction.get(url);
			log("Response from search: " + response);

			return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private SearchResults parse(String response) throws Exception {
		log("Response from server in Json is: " + response);

		JSONObject jsonRoot = (JSONObject) new JSONTokener(response).nextValue();
		boolean hasMore = jsonRoot.getBoolean("hasMore");
		int totalAvailable = jsonRoot.getInt("totalAvailable");

		JSONArray people = (JSONArray) jsonRoot.get("people");

		SearchResults results = new SearchResults();
		results.setHasMore(hasMore);
		results.setTotalAvailable(totalAvailable);

		log("Number of people returned: " + people.length());

		for (int i = 0; i < people.length(); i++) {
			JSONObject person = (JSONObject) people.get(i);

			String name;
			if (person.has("Profile")) {
				JSONObject profile = (JSONObject) person.get("Profile");
				name = (String) profile.get("name");

			} else if (person.has("Person")) {
				JSONObject wpolPerson = (JSONObject) person.get("Person");
				name = (String) wpolPerson.get("name");

			} else {
				Log.e(Logging.TAG, "Unknown person type: " + person.toString());
				continue;
			}
			results.add(new SearchResult(name));
		}

		return results;
	}
}
