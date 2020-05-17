package com.citi.cc.customerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.citi.cc.customerservice.controllers.CustomerController;
import com.citi.cc.customerservice.data.CustomerDTO;
import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.persistent.repo.CustomerRepository;
import com.citi.cc.customerservice.services.CustomerDBService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTester {
	
	 @Autowired
	 private MockMvc mvc;
	 
	 @MockBean
	 private CustomerDBService service;
	 
	 public static byte[] toJson(Object object) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        return mapper.writeValueAsBytes(object);
	 }
	 
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
	 
	 @Test
	 public void testCreateNewCustomer() throws Exception {
		 Customer customer = new Customer();
		 customer.setAddress("100 Test St");
		 customer.setFirstName("Test");
		 customer.setLastName("Test");
		 customer.setPhoneNumber("2140000000");
		 customer.setSsn("001-00-111");
		 
		 given(service.createCustomer(Mockito.any())).willReturn(customer);
		 
		 CustomerDTO dto = new CustomerDTO();
		 dto.setAddress("100 Test St");
		 dto.setFirstName("Test");
		 dto.setLastName("Test");
		 dto.setPhoneNumber("2140000000");
		 dto.setSsn("001-00-111");
		 
	     mvc.perform(post("/customer/new").contentType(MediaType.APPLICATION_JSON).content(toJson(dto)))
	     .andExpect(status().isOk())
	     .andExpect(jsonPath("$.ssn", is("001-00-111")));

	     verify(service, VerificationModeFactory.times(1)).createCustomer(Mockito.any());
	     reset(service);
	 }
}
