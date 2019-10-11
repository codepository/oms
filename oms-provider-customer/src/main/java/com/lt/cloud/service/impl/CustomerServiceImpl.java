package com.lt.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.CustomerMapper;
import com.lt.cloud.pojo.Customer;
import com.lt.cloud.service.CustomerService;
@Component
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public boolean insertCustomer(Customer customer) {
		try {
			Customer c=this.findByName(customer.getCUST_NAME());
			if (c!=null) {
				throw new RuntimeException("用户【"+customer.getCUST_NAME()+"】已经存在不要重复添加");
			}
			this.customerMapper.insertCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		try {
			this.customerMapper.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Customer findByName(String name) {
		
		return this.customerMapper.findByName(name);
	}

	@Override
	public boolean existsByName(String name) {
		Customer c=this.findByName(name);
		if (c!=null) {
			return true;
		}
		return false;
	}

}
