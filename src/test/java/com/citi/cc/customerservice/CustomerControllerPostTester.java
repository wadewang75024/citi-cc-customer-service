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
public class CustomerControllerPostTester {
	
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
		 
	     mvc.perform(post("/customer/new").contentType(MediaType.APPLICATION_JSON).content(toJson(dto)));

	     verify(service, VerificationModeFactory.times(1)).createCustomer(Mockito.any());
	     reset(service);
	 }
}

