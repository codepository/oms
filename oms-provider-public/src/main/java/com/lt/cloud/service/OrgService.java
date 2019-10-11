package com.lt.cloud.service;

import com.lt.cloud.pojo.Org;

public interface OrgService {

	String save(Org receiver);

	String deleteById(Integer id);

	String findAll();

}
