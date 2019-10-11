package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.pojo.UserOrg;
import com.lt.cloud.service.UserOrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
/**
 * 用户和行业的关系
 * @author lt
 *
 */
@RestController
@RequestMapping("/public/userorg")
public class UserOrgController {
	@Autowired
	private UserOrgService userOrgService;
	@RequestMapping("/save")
	public String save(@RequestBody String json) {
		UserOrg receiver = JsonUtils.getGson().fromJson(json, UserOrg.class);
		if (receiver.getOrgid() == null) {
			return ResponseCodeUtils.responseErr("行业orgid不能为空");
		}
		if (receiver.getUserid()==null) {
			return ResponseCodeUtils.responseErr("用户id不能为空！");
		}
		if (StringUtils.isEmpty(receiver.getUsername())) {
			return ResponseCodeUtils.responseErr("用户username 不能为空");
		}
		return this.userOrgService.save(receiver);
	}
	@RequestMapping("/delById")
	public String delById(@RequestParam Integer id) {
		return this.userOrgService.deleteById(id);
	}
	@RequestMapping("/findOrgidsByUserid")
	public String findOrgidsByUserid(@RequestParam Long userid) {
		return this.userOrgService.findOrgidsByUserid(userid);
	}
	@RequestMapping("/findByOrgid")
	public String findByOrgid(@RequestParam Integer orgid) {
		return this.userOrgService.findByOrgid(orgid);
	}
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
