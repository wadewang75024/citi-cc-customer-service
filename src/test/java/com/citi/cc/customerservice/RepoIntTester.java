package com.citi.cc.customerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.persistent.repo.CustomerRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RepoIntTester {
	
	  @Autowired
	  private TestEntityManager entityManager;
	 
	  @Autowired
	  private CustomerRepository customerRepository;
	  
	  @Test
	  public void findBySSN() {
		    // given
		    Customer customer = new Customer();
		    customer.setAddress("100 Test St");
		    customer.setFirstName("Test");
		    customer.setLastName("Test");
		    customer.setPhoneNumber("2140000000");
		    customer.setSsn("001-00-111");
		    
		    entityManager.persist(customer);
		    entityManager.flush();
		 
		    // when
		    Customer found = customerRepository.findByssn(customer.getSsn());
		 
		    // then
		    assertThat(found.getLastName())
		      .isEqualTo(customer.getLastName());
		}
	  
	 

}
