package com.landerer.hystrix.democlient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public final class UuidCommand extends HystrixCommand<String> {

	public UuidCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UUID")));
	}

	@Override
	protected String run() throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8080/demoservice/hystrix/uuid/");
		getRequest.addHeader("accept", "application/xml");
		HttpResponse response = httpClient.execute(getRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		StringBuilder builder = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		return builder.toString();
	}

	@Override
	protected String getFallback() {
		return "Service currently unavailable!";
	}
}
