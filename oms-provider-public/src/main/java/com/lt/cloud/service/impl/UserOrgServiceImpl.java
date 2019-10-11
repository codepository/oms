package com.lt.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.UserOrgMapper;
import com.lt.cloud.pojo.UserOrg;
import com.lt.cloud.service.UserOrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
@Component
public class UserOrgServiceImpl implements UserOrgService{
	@Autowired
	private UserOrgMapper mapper;
	@Override
	public synchronized String save(UserOrg receiver) {
		try {
			if (this.mapper.existsUserByOrgidAndUserid(receiver)) {
				return ResponseCodeUtils.responseErr("已经存在");
			}
			this.mapper.save(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		return ResponseCodeUtils.responseOk(receiver.getId().toString());
	}
	@Override
	public String deleteById(Integer id) {
		try {
			this.mapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		return ResponseCodeUtils.responseOk("成功");
	}
	@Override
	public String findOrgidsByUserid(Long userid) {
		List<Long> result=null;
		try {
			result=this.mapper.findOrgidsByUserid(userid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		return JsonUtils.getGson().toJson(result);
	}
	@Override
	public String findByOrgid(Integer orgid) {
		List<UserOrg> result=null;
		try {
			result=this.mapper.findByOrgid(orgid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
 		return JsonUtils.getGson().toJson(result);
	}

}
