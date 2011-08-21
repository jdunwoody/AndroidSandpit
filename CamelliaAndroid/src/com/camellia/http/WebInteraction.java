package com.camellia.http;

import static com.camellia.logging.Logging.log;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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

	public String get(String url) throws Exception {
		log("GETting: " + url);

		HttpGet req = new HttpGet(url);
		return body(web.execute(req));
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
		log("Received " + contentLength + " characters in response");

		String content = read(entity.getContent());
		log("All content " + content);
		return content;

		// char[] buffer = new char[contentLength];
		// InputStream contentStream = entity.getContent();
		// Reader reader = new InputStreamReader(contentStream);
		// int bytesRead = reader.read(buffer, 0, contentLength);
		// log(bytesRead + "Bytes read");
		//
		// return new String(buffer);
	}

	private String read(InputStream inputStream) throws Exception {
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8");
		return writer.toString();
	}
}
