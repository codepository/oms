package com.lt.cloud.mapper;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.CategoryTree;

public interface CategoryTreeMapper {
	List<CategoryTree> selectAll(HashMap<String, Object> params);
}
