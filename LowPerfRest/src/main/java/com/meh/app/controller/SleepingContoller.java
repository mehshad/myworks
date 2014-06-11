package com.meh.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meh.app.entities.Customer;

@Controller
@RequestMapping("/sleepCustomer")
public class SleepingContoller {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Customer getCustomer() throws InterruptedException{
		Customer customer = new Customer();
		Thread.sleep(3000);
		customer.setId("123");
		customer.setName("Mehshad");
		return customer;
	}
}
