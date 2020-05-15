package com.citi.cc.customerservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.persistent.repo.CustomerRepository;

@Service
public class CustomerDBServiceImpl implements  CustomerDBService {
	
	@Autowired
	CustomerRepository dbRepo;
	
	public Optional<Customer> retrieveCustomerById(Long id) throws Exception {
		return dbRepo.findById(id);
	}
	
	public Optional<List<Customer>> retrieveAll() throws Exception {
		Optional<List<Customer>> cList = Optional.ofNullable(dbRepo.findAll());
		return cList;		
	}
	
	public Customer createCustomer(Customer c) throws Exception {
		return dbRepo.save(c);
	}
	
	public Customer updateSSN(Customer c) throws Exception {
		return dbRepo.save(c);
	}

}
