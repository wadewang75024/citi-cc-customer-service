package com.citi.cc.customerservice.data;

import com.citi.cc.customerservice.persistent.entities.Customer;

public class DataMapper {
	public static CustomerDTO mapToDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setAddress(customer.getAddress());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setPhoneNumber(customer.getPhoneNumber());
		dto.setSsn(customer.getSsn());
		
		return dto;
	}
	
	public static Customer mapToEntity(CustomerDTO dto) {
		Customer entity = new Customer();
		entity.setId(dto.getId());
		entity.setAddress(dto.getAddress());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSsn(dto.getSsn());
		
		return entity;
	}

}
