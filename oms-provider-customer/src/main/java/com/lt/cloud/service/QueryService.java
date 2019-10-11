package com.lt.cloud.service;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.Customer;
import com.lt.cloud.pojo.CustomerReceiver;

public interface QueryService {
	List<Customer> selectAllCustomer(HashMap<String, Object> params);
	Customer selectById(Long id);
	List<Customer> find4AutoComplete(CustomerReceiver receiver);
}
