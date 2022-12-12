package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {
	private static final String API_URL = "https://cat-data.netlify.app/api/facts/random";
	private RestTemplate restTemplate;

	public RestCatFactService() {

		restTemplate = new RestTemplate(); // ==> Updated this
	}

	@Override
	public CatFact getFact() {

		return restTemplate.getForObject(API_URL, CatFact.class);
	}

}
