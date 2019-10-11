package com.lt.cloud.service;

import com.lt.cloud.pojo.Customer;

public interface CustomerService {
	boolean insertCustomer(Customer customer);
	boolean updateCustomer(Customer customer);
	Customer findByName(String name);
	boolean existsByName(String name);
}
