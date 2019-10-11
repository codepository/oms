package com.lt.cloud.mapper;

import java.util.List;

import com.lt.cloud.pojo.Org;

public interface OrgMapper {
	List<Org> findAll();
	boolean existsByLabel(String label);
	void deleteById(Integer id);
	void save(Org org);
}
