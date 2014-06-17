package com.meh.ning.restservice;

import java.io.IOException;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;

@Component
public class NingLifeCycle {

	boolean exceptionOccured=false;

	public void callRestService() throws IOException{
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<String> f = asyncHttpClient.prepareGet("http://localhost:8080/lpa/sleepCustomer/").execute(
				new AsyncHandler(){

					public void onThrowable(Throwable t) {
						exceptionOccured=true;
						System.out.println("Exception occured");
					}

					public STATE onBodyPartReceived(
							HttpResponseBodyPart bodyPart) throws Exception {
						System.out.println("Body part received "+bodyPart);
						return null;
					}

					public STATE onStatusReceived(
							HttpResponseStatus responseStatus) throws Exception {
						System.out.println("Response status received "+responseStatus.getStatusCode());
						return null;
					}

					public STATE onHeadersReceived(HttpResponseHeaders headers)
							throws Exception {
						System.out.println("Headers received "+headers);
						return null;
					}

					public Object onCompleted() throws Exception {
						 System.out.println("Completed the request");
						return null;
					}
					
				});
		if(exceptionOccured){
			System.out.println("Method done with Exception");
		}else{
			System.out.println("Ning Async Client Method done successfully");
		}
	}
}
