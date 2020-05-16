package com.citi.cc.customerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.citi.cc.customerservice.controllers.CustomerController;
import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.services.CustomerDBService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerGetTester {
	
	 @Autowired
	 private MockMvc mvc;
	 
	 @MockBean
	 private CustomerDBService service;
	 
	 @Test
	 public void testRetrieveAllCustomers()
	   throws Exception {
	      
		 // given
		 Customer customer1 = new Customer();
		 customer1.setAddress("100 Test St");
		 customer1.setFirstName("Test");
		 customer1.setLastName("Test");
		 customer1.setPhoneNumber("2140000000");
		 customer1.setSsn("001-00-111");
		 
		 Customer customer2 = new Customer();
		 customer2.setAddress("100 Test St");
		 customer2.setFirstName("Test");
		 customer2.setLastName("Test");
		 customer2.setPhoneNumber("2140000000");
		 customer2.setSsn("002-00-222");
		 
		 Customer customer3 = new Customer();
		 customer3.setAddress("100 Test St");
		 customer3.setFirstName("Test");
		 customer3.setLastName("Test");
		 customer3.setPhoneNumber("2140000000");
		 customer3.setSsn("003-00-333");
	  
	     Optional<List<Customer>> allCustomers = Optional.of(Arrays.asList(customer1, customer2, customer3));
	     
	     given(service.retrieveAll()).willReturn(allCustomers);
	  
	     mvc.perform(get("/customers")
	     .contentType(MediaType.APPLICATION_JSON))
	     .andExpect(status().isOk())
	     .andExpect(jsonPath("$", hasSize(3)))
	     .andExpect(jsonPath("$[0].ssn", is("xxx-xx-111")))
	     .andExpect(jsonPath("$[1].ssn", is("xxx-xx-222")))
         .andExpect(jsonPath("$[2].ssn", is("xxx-xx-333")));
	     verify(service, VerificationModeFactory.times(1)).retrieveAll();
	     reset(service);
	 }
}
