package com.meh.simplerest.restservice;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

@Component("ningWithFuture")
public class NingWithFuture {

	public void callLowPerf() throws IOException, InterruptedException,
			ExecutionException {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<Response> f = asyncHttpClient.prepareGet(
				"http://localhost:8080/lpa/sleepCustomer/").execute();
		System.out.println("Called low perf rest service");
		int x=0;
		while(!f.isDone()){
			x++;
		}
		Response r = f.get();
		System.out.println("Received response after "+x+" increments");
		System.out.println(r);
	}
}
