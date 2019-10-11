package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.pojo.Customer;
import com.lt.cloud.service.CustomerService;
import com.lt.cloud.utils.JsonUtils;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/customer/insertCustomer")
	public boolean insertCustomer(@RequestBody String json) {
		Customer customer = JsonUtils.getGson().fromJson(json, Customer.class);
		return this.customerService.insertCustomer(customer);
	}
	@RequestMapping("/customer/updateCustomer")
	public boolean updateCustomer(@RequestBody String json) {
		Customer customer = JsonUtils.getGson().fromJson(json, Customer.class);
		return this.customerService.updateCustomer(customer);
	}
	@RequestMapping("/customer/findByName/{name}")
	public Customer  findById(@PathVariable String name) {
		return this.customerService.findByName(name);
	}
	@RequestMapping("/customer/existsByName/{name}")
	public boolean  existsByName(@PathVariable String name) {
		return this.customerService.existsByName(name);
	}
}
