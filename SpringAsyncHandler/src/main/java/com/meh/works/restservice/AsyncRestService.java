package com.meh.works.restservice;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncRestService {
	
	RestTemplate restTemplate = new RestTemplate();

	@Async
	public Future<String> callRestService(){
		String response = restTemplate.getForObject("http://localhost:8080/lpa/sleepCustomer/", String.class);
		return new AsyncResult<String>(response);
	}
}
