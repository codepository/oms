package com.lt.cloud.service;

import com.lt.cloud.pojo.UserOrg;

public interface UserOrgService {

	String save(UserOrg receiver);

	String deleteById(Integer id);

	String findOrgidsByUserid(Long userid);

	String findByOrgid(Integer orgid);

}
