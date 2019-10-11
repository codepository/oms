package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.pojo.Org;
import com.lt.cloud.pojo.UserOrg;
import com.lt.cloud.service.OrgService;
import com.lt.cloud.service.UserOrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
/**
 *    行业
 * @author lt
 *
 */
@RestController
@RequestMapping("/public/org")
public class OrgController {
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserOrgService userOrgService;
	@RequestMapping("/save")
	public String save(@RequestBody String json) {
		Org receiver=JsonUtils.getGson().fromJson(json, Org.class);
		if (StringUtils.isEmpty(receiver.getLabel())) {
			return ResponseCodeUtils.responseErr("标签label不能为空");
		}
		if (receiver.getParentid()==null) {
			return ResponseCodeUtils.responseErr("父节点parentid不能为空");
		}
		if (receiver.getDepth()==null) {
			return ResponseCodeUtils.responseErr("深度depth不能为空");
		}
		return this.orgService.save(receiver);
	}
	@RequestMapping("/delById")
	public String deleteById(@RequestParam Integer id) {
		return this.orgService.deleteById(id);
	}
	@RequestMapping("/findAll")
	public String findAll() {
		return this.orgService.findAll();
	}
	@RequestMapping("/findByOrgid")
	public String findByOrgid(@RequestParam Integer orgid) {
		return this.userOrgService.findByOrgid(orgid);
	}
	@RequestMapping("/saveTradeCharger")
	public String saveTradeCharger(@RequestBody String json) {
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
	// 从user_org表，删除对应行业的用户
	@RequestMapping("/delTradeChargerById")
	public String delById(@RequestParam Integer id) {
		return this.userOrgService.deleteById(id);
	}
	// 从user_org表查询，用户对应的行业
	@RequestMapping("/findOrgidsByUserid")
	public String findOrgidsByUserid(@RequestParam Long  userid) {
		return this.userOrgService.findOrgidsByUserid(userid);
	}
}
