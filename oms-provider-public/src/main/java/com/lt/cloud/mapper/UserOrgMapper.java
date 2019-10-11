package com.lt.cloud.mapper;

import java.util.List;

import com.lt.cloud.pojo.UserOrg;

public interface UserOrgMapper {
	List<Long> findOrgidsByUserid(Long userid);
	void deleteById(Integer id);
	boolean existsUserByOrgidAndUserid(UserOrg userOrg);
	void save(UserOrg userOrg);
	List<UserOrg> findByOrgid(Integer orgid);
}
