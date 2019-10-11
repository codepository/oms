package com.lt.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.reflect.TypeToken;

import com.lt.cloud.interceptor.RequiredPermission;
import com.lt.cloud.pojo.Advitem;

import com.lt.cloud.service.AdvitemUpdateSevice;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;


@RestController
@RefreshScope
@RequestMapping("/advitem")
public class AdvitemUpdateController {
	
	@Autowired
	private AdvitemUpdateSevice advitemUpdateSevice;
	@RequestMapping("/saveAll")
	@RequiredPermission()
	public String saveAll(@RequestBody String advitem) {
		boolean flag=this.advitemUpdateSevice.saveNewAdvitems(advitem);
		return ResponseCodeUtils.response(flag);
		
	}
	@RequestMapping("/updateAll")
	@RequiredPermission()
	public String updateAll(@RequestBody String advitemList){
		List<Advitem> advitems=JsonUtils.getGson().fromJson(advitemList, new TypeToken<List<Advitem>>() {}.getType());
		this.advitemUpdateSevice.updateAll(advitems);
		return ResponseCodeUtils.response(true);
	}
	@RequestMapping("/updateAdvContentWithMoneyNotChange")
//	@RequiredPermission()
	public String updateAdvContentWithMoneyNotChange(@RequestBody String advitemList) {
		List<Advitem> advitems=JsonUtils.getGson().fromJson(advitemList, new TypeToken<List<Advitem>>() {}.getType());
		return this.advitemUpdateSevice.updateAdvContentWithMoneyNotChange(advitems);
	}
	@RequestMapping("/updateAdvAndOrder")
	@RequiredPermission()
	public String updateAdvAndOrder(@RequestBody String advitemList ) {
		boolean flag=this.advitemUpdateSevice.updateAdvAndOrder(advitemList);
		return ResponseCodeUtils.response(flag);
	}
	@RequestMapping("/insertAdvitem")
	@RequiredPermission()
	public String insertAdvitem(@RequestBody String json ) {
		try {
			return JsonUtils.getGson().toJson(this.advitemUpdateSevice.insertAdvitem(JsonUtils.getGson().fromJson(json, Advitem.class)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseCodeUtils.response(true);
		
	}
	@RequestMapping("/deleteAdvitem/{id}")
	@RequiredPermission()
	public String deleteAdvitem(@PathVariable Long id ) {
		this.advitemUpdateSevice.deleteById(id);
		return ResponseCodeUtils.response(true);
	}
	@RequestMapping("/deleteByOrderId/{id}")
	@RequiredPermission()
	public String deleteByOrderId(@PathVariable Long id ) {
		try {
			this.advitemUpdateSevice.deleteByOrderId(id);
			return ResponseCodeUtils.response(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping("/updateAdvitem")
	@RequiredPermission()
	public String updateAdvitem(@RequestBody String json ) {
		return ResponseCodeUtils.response(this.advitemUpdateSevice.updateAdvitem(JsonUtils.getGson().fromJson(json, Advitem.class)));
	}
}
