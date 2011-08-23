package com.camellia.profile;

import com.camellia.domain.Profile;
import com.camellia.http.WebInteraction;

public class ViewProfileService {
	private final WebInteraction webInteraction;

	public ViewProfileService(WebInteraction webInteraction) {
		this.webInteraction = webInteraction;
	}

	public Profile load(String profileId, String name) {
		return new Profile("http://www.hi.com.au/" + name);
	}
}
