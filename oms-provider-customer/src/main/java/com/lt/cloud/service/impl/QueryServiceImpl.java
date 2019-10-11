package com.lt.cloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.CustomerMapper;
import com.lt.cloud.pojo.Customer;
import com.lt.cloud.pojo.CustomerReceiver;
import com.lt.cloud.service.QueryService;
@Component
public class QueryServiceImpl implements QueryService{
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public List<Customer> selectAllCustomer(HashMap<String, Object> params) {
		
		return this.customerMapper.selectAllCustomer(params);
	}
	@Override
	public Customer selectById(Long id) {
		
		return this.customerMapper.selectById(id);
	}
	@Override
	public List<Customer> find4AutoComplete(CustomerReceiver receiver) {
		
		return this.customerMapper.find4AutoComplete(receiver);
	}

}
