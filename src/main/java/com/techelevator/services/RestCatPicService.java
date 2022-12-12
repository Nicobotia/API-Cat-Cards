package com.techelevator.services;

import com.techelevator.model.CatFact;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {
	private static final String API_URL = "https://cat-data.netlify.app/api/pictures/random";
	private RestTemplate restTemplate;

	public RestCatPicService() {

		restTemplate = new RestTemplate(); // ==> Updated this
	}

	@Override
	public CatPic getPic() {
		return restTemplate.getForObject(API_URL, CatPic.class);

	}

}	
