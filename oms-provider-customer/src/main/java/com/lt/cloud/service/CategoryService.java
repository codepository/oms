package com.lt.cloud.service;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.CategoryTree;

public interface CategoryService {
	List<CategoryTree> selectAll(HashMap<String, Object> params);
}
