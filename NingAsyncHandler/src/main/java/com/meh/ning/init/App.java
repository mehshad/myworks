package com.meh.ning.init;

import java.io.IOException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meh.ning.restservice.NingAsyncClient;
import com.meh.ning.restservice.NingFutureAsyncClient;
import com.meh.ning.restservice.NingLifeCycle;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        NingAsyncClient ningAsyncClient = (NingAsyncClient)context.getBean("ningAsyncClient");
        ningAsyncClient.callRestService();
        NingFutureAsyncClient ningFutureAsyncClient = (NingFutureAsyncClient)context.getBean("ningFutureAsyncClient");
        ningFutureAsyncClient.callRestService();
        NingLifeCycle ningLifeCycle = (NingLifeCycle) context.getBean("ningLifeCycle");
        ningLifeCycle.callRestService();
        context.registerShutdownHook();
        System.out.println("SHUTDOWN!!!");
    }
}
