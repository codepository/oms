package com.lt.cloud.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.service.CategoryService;
import com.lt.cloud.utils.JsonUtils;


@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/selectAll")
	public String selectAll() {
		HashMap<String, Object> params=new HashMap<>();
		return JsonUtils.getGson().toJson(this.categoryService.selectAll(params));
	}
}
