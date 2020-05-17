package com.citi.cc.customerservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.persistent.repo.CustomerRepository;
import com.citi.cc.customerservice.services.CustomerDBService;
import com.citi.cc.customerservice.services.CustomerDBServiceImpl;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ServiceLayerTester {
	
	 @TestConfiguration
	 static class CustomerServiceImplTestContextConfiguration {
	  
	        @Bean
	        public CustomerDBService customerService() {
	            return new CustomerDBServiceImpl();
	        }
	 }
	 
	 @Autowired
	 private CustomerDBService customerDBService;
	 
	 @MockBean
	 private CustomerRepository customerRepository;
	 
	 @Before
	 public void setUp() {
		 Customer customer = new Customer();
		    customer.setAddress("100 Test St");
		    customer.setFirstName("Test");
		    customer.setLastName("Test");
		    customer.setPhoneNumber("2140000000");
		    customer.setSsn("001-00-111");
	  
	     Mockito.when(customerRepository.findByssn(customer.getSsn()))
	       .thenReturn(customer);
	 }
	 
	 @Test
	 public void retrieveCustomerBySSN() throws Exception {
	     String ssn = "001-00-111";
	     Customer found = customerDBService.retrieveBySSN(ssn);
	   
	      assertThat(found.getSsn())
	       .isEqualTo(ssn);
	  }
}
