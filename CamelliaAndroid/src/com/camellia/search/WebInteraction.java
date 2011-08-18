package com.camellia.search;

import static com.camellia.logging.Logging.log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class WebInteraction {
	private static final String BASE = null;
	private final HttpClient web;

	public WebInteraction(HttpClient web) {
		this.web = web;
	}

	public String post(String url, Map<String, String> params) throws Exception {
		params.put("json", "true");
		params.put("quiet", "true");

		log("POSTing: " + url + " with params = " + params);
		HttpPost post = new HttpPost(url);

		post.setEntity(makeParamsForPost(params));
		return body(web.execute(post));
	}

	public List<JsonSearchResult> get() throws Exception {
		return parse(get(BASE + "page/list/"));
	}

	public String get(String url) throws Exception {
		log("GETting: " + url);
		HttpGet req = new HttpGet(url);
		return body(web.execute(req));
	}

	private List<JsonSearchResult> parse(String response) throws Exception {
		JSONArray array = (JSONArray) new JSONTokener(response).nextValue();
		List<JsonSearchResult> results = new ArrayList<JsonSearchResult>();

		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			// long timestamp = obj.getLong("date");
			// String url = obj.getString("url");
			// String title = obj.getString("title");
			// boolean has_content = obj.getBoolean("has_content");
			results.add(new JsonSearchResult());
		}

		return results;
	}

	private UrlEncodedFormEntity makeParamsForPost(Map<String, String> params) throws Exception {
		return new UrlEncodedFormEntity(makeParamList(params));
	}

	private String makeParamsForGet(Map<String, String> params) {
		return URLEncodedUtils.format(makeParamList(params), "UTF-8");
	}

	private List<NameValuePair> makeParamList(Map<String, String> params) {
		List<NameValuePair> paramList = new java.util.ArrayList<NameValuePair>();

		for (String param : params.keySet()) {
			paramList.add(new BasicNameValuePair(param, params.get(param)));
		}

		return paramList;
	}

	private String body(HttpResponse result) throws Exception {
		String body = readResponse(result.getEntity());

		int code = result.getStatusLine().getStatusCode();
		switch (code) {
		case 200:
			return body;
		case 404:
			throw new HttpException();
		default:
			throw new HttpException("Response returned code " + code + " -- body content: " + body);
		}
	}

	private String readResponse(HttpEntity entity) throws Exception {
		int contentLength = (int) entity.getContentLength();
		char[] buffer = new char[contentLength];
		InputStream contentStream = entity.getContent();
		Reader reader = new InputStreamReader(contentStream);

		reader.read(buffer, 0, contentLength);

		return new String(buffer);
	}
}
