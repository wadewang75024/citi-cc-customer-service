package com.citi.cc.customerservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {
	public static Logger logger = LogManager.getLogger(CustomerServiceApplication.class);
    public static void main(String[] args) {
    	logger.info("CustomerServiceApplication main starts...");
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}