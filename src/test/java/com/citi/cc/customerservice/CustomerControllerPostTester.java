package com.citi.cc.customerservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.citi.cc.customerservice.controllers.CustomerController;
import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.persistent.repo.CustomerRepository;
import com.citi.cc.customerservice.utils.JsonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerPostTester {
	 @Autowired
	 private MockMvc mvc;
	 
	 @Autowired
	 private CustomerRepository repository;
	 
	 @Test
	 public void testCreateNewCustomer() throws Exception {
		 Customer customer = new Customer();
		 customer.setAddress("100 Test St");
		 customer.setFirstName("Test");
		 customer.setLastName("Test");
		 customer.setPhoneNumber("2140000000");
		 customer.setSsn("001-00-111");
		 
	     mvc.perform(post("/customer/new").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(customer)));

	     Customer found = repository.findByssn("001-00-111");
	     assertThat(found).extracting(Customer::getSsn).equals("001-00-111");
	 }
}
