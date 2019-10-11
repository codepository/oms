package com.lt.cloud.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import com.lt.cloud.pojo.Customer;
import com.lt.cloud.pojo.CustomerReceiver;
import com.lt.cloud.service.QueryService;
import com.lt.cloud.utils.JsonUtils;

@RestController
@RequestMapping("/customer")
public class QueryController {
	@Autowired
	private QueryService queryService;
	@RequestMapping("/selectById/{id}")
	public String selectById(@PathVariable Long id) {
		return JsonUtils.getGson().toJson(this.queryService.selectById(id));
	}
	@RequestMapping("/selectAll")
	public String selectAllCustomer(Integer pageIndex,Integer pageSize,
			@RequestParam(required=false) String customerName){
		pageIndex=pageIndex==null?1:pageIndex;
		pageSize=pageSize==null?10:pageSize;
		PageHelper.startPage(pageIndex, pageSize);
		
		HashMap<String,Object> params=new HashMap<>();
		params.put("CUST_NAME", customerName);
		List<Customer> list=queryService.selectAllCustomer(params);
		
		return JsonUtils.formatListForPagination(list, pageIndex, pageSize);
	}
	@RequestMapping("/autoCompleteForEasyui")
	public String autoCompleteForEasyui(Integer pageSize,
			@RequestParam String CUST_NAME) {
	
		pageSize=pageSize==null?20:pageSize;
		PageHelper.startPage(1, pageSize);
		HashMap<String,Object> params=new HashMap<>();
		params.put("CUST_NAME", CUST_NAME);
		params.put("fields", "SYS_DOCUMENTID,CUST_NAME");
		List<Customer> list=queryService.selectAllCustomer(params);
		
		return JsonUtils.getGson().toJson(list);
	}
	@RequestMapping("/find4AutoComplete")
	public String find4AutoComplete(@RequestBody String  json) {
		CustomerReceiver customerReceiver=JsonUtils.getGson().fromJson(json, CustomerReceiver.class);
		System.out.println(customerReceiver.getCUST_NAME());
		PageHelper.startPage(1, customerReceiver.getPageSize());
		return JsonUtils.getGson().toJson(this.queryService.find4AutoComplete(customerReceiver));
	}
}
