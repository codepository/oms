package com.lt.cloud.mapper;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.Customer;
import com.lt.cloud.pojo.CustomerReceiver;

public interface CustomerMapper {
	public List<Customer> selectAllCustomer(HashMap<String, Object> params);
	List<Customer> find4AutoComplete(CustomerReceiver receiver);
	Customer selectById(Long id);
	Long insertCustomer(Customer customer);
	Long updateCustomer(Customer customer);
	public Customer findByName(String name);
}
