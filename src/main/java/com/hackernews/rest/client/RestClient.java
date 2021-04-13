package com.hackernews.rest.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hackernews.rest.utilities.HackerNewsConstants;

public class RestClient {
	
	public ResponseEntity<?> callRestService(String url) {
		
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(HackerNewsConstants.HTTP_ENTITY_PARAM, header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Object> result = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
		return result;
	}

}
