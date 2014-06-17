package com.meh.ning.restservice;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

@Component
public class NingAsyncClient {
	
	boolean exceptionOccured=false;

	public void callRestService() throws IOException{
		
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.prepareGet("http://localhost:8080/lpa/sleepCustomer/").execute(
				new AsyncCompletionHandler() {
					@Override
			        public Response onCompleted(Response response) throws Exception{
						 System.out.println("Completed the request with "+ response.getResponseBody() );
			            return response;
			        }

			        @Override
			        public void onThrowable(Throwable t){
			        	exceptionOccured=true;
			        	 System.out.println("Exception on request");
			        }
				});
		if(exceptionOccured){
			System.out.println("Method done with Exception");
		}else{
			System.out.println("Ning Async Client Method done successfully");
		}
		
	}
	
}
