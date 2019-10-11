package com.lt.cloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.CategoryTreeMapper;
import com.lt.cloud.pojo.CategoryTree;
import com.lt.cloud.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryTreeMapper categoryTreeMapper;
	@Override
	public List<CategoryTree> selectAll(HashMap<String, Object> params) {
		
		return this.categoryTreeMapper.selectAll(params);
	}

}
