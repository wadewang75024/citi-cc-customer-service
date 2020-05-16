package com.citi.cc.customerservice.services;

import java.util.List;
import java.util.Optional;

import com.citi.cc.customerservice.persistent.entities.Customer;

public interface CustomerDBService {
	Optional<Customer> retrieveCustomerById(Long id) throws Exception;
	Optional<List<Customer>> retrieveAll() throws Exception;
	
	Customer createCustomer(Customer c) throws Exception;
	
	Customer updateSSN(Customer c) throws Exception;
	
	Customer retrieveBySSN(String ssn) throws Exception;
}
