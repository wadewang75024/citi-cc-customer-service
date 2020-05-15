package com.citi.cc.customerservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.cc.customerservice.data.CustomerDTO;
import com.citi.cc.customerservice.data.DataMapper;
import com.citi.cc.customerservice.exceptions.BusinessException;
import com.citi.cc.customerservice.persistent.entities.Customer;
import com.citi.cc.customerservice.services.CustomerDBService;

@RestController
public class CustomerController {
	public static Logger logger = LogManager.getLogger(CustomerController.class);
	
    @Autowired 
    CustomerDBService customerDBService;

    /**
     * Access via http://localhost:3001/
     * @return
     */
    @GetMapping(path="/customers")
    public List<CustomerDTO> getAllCustomers() {
    	logger.info("getAllCustomers starts...");
    		
    	Optional<List<Customer>> customers = null;
    	try {
    		customers = customerDBService.retrieveAll();
    		if ( customers.isPresent()) {
    			List<Customer> customerList = customers.get();
    			List<CustomerDTO> dtoList = new ArrayList<>();
    			customerList.forEach( customer-> {
    				CustomerDTO dto = DataMapper.mapToDTO(customer);
    				resetSSN(dto);
    				dtoList.add(dto);
    			}
    			);
    			return dtoList;
    		}
    		else
    			throw new BusinessException();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException();
    	}
    }

    /**
     * Access via http://localhost:3001/1
     * @param id
     * @return
     */
    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
    	logger.info("getCustomerById starts...");
    	try {
    	  Optional<Customer> customer = customerDBService.retrieveCustomerById(id);
    	  if ( customer.isPresent() ) {
    		  CustomerDTO dto = DataMapper.mapToDTO(customer.get());
    		  resetSSN(dto);
    		  return dto;
    	  }
    	  else {
    		  throw new BusinessException();
    	  }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException();
    	}
    }
    
    @PostMapping("/customer/new")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO dto) {
    	try {
    	  Customer customer = DataMapper.mapToEntity(dto);
    	  Customer newCustomer = customerDBService.createCustomer(customer);
    	  CustomerDTO result = DataMapper.mapToDTO(newCustomer);
    	  return result;
    	}
    	catch (Exception e) {
    		throw new BusinessException();
    	}
    }
    
    @PutMapping("/customer/update")
    public String update(@RequestBody CustomerDTO dto) {
    	try {
    		Customer customer = DataMapper.mapToEntity(dto);
    		customerDBService.updateSSN(customer);
    		return "ok";
    	}
    	catch (Exception e) {
    		throw new BusinessException();
    	}
    }
    
    private void resetSSN(CustomerDTO dto) {
    	dto.setSsn("xxx-xx-" + dto.getSsn().substring(7, dto.getSsn().length()));
    }
}

