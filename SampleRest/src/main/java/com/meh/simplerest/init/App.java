package com.meh.simplerest.init;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meh.simplerest.restservice.NingWithFuture;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException
    {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        NingWithFuture ningWithFuture = (NingWithFuture)context.getBean("ningWithFuture");
        ningWithFuture.callLowPerf();
        context.registerShutdownHook();
    }
}
