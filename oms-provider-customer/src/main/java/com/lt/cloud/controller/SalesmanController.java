package com.lt.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.dao.jpa.SalesmanRepository;
import com.lt.cloud.pojo.Salesman;
import com.lt.cloud.utils.JsonUtils;
@RestController
public class SalesmanController {
	@Autowired
	private SalesmanRepository salesmanRepository;
	
	@RequestMapping("/salesman/find4AutoComplete/{name}")
	public String find4AutoComplete(@PathVariable String name) {
		List<Salesman> result = this.salesmanRepository.findByNameContaining(name);
		return JsonUtils.getGson().toJson(result);
	}
	@RequestMapping("/salesman/existsByName/{name}")
	public boolean existsByName (@PathVariable String name) {
		return this.salesmanRepository.existsByName(name);
	}
	@RequestMapping("/salesman/save")
	public boolean save(@RequestBody String json) {
		Salesman salesman=JsonUtils.getGson().fromJson(json, Salesman.class);
		try {
			if (this.salesmanRepository.existsByName(salesman.getName())) {
				throw new RuntimeException("用户【"+salesman.getName()+"】已经存在！！");
			}
			this.salesmanRepository.save(salesman);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
