package com.meh.works.init;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.meh.works.restservice.AsyncRestService;

@Configuration
@EnableAsync
@EnableAutoConfiguration
@ComponentScan("com.meh.works")
public class App implements CommandLineRunner
{
	@Autowired
	AsyncRestService asyncRestService;
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

	public void run(String... args) throws Exception {
		Future<String> results = asyncRestService.callRestService();
		System.out.println("Results have not been received");
		int i=0;
		while(!results.isDone()){
			i++;
		}
		System.out.println("I did "+i+" increments before this was done");
		System.out.println("Results received "+results.get());
	}
}
