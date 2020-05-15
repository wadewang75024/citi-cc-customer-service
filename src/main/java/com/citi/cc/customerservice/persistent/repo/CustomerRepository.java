package com.citi.cc.customerservice.persistent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citi.cc.customerservice.persistent.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
