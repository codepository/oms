package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Pricelistitem;
import com.lt.cloud.service.PrilistItemService;
import com.lt.cloud.utils.JsonUtils;

@RestController
@RefreshScope
@RequestMapping("/pricelistitem")
public class PricelistitemController {
	@Autowired
	private PrilistItemService prilistItemService;
	@RequestMapping("/selectAll")
	public String selectAllPricelistitem(@RequestBody String  json) {
		AdvotherReceiver receiver=JsonUtils.getGson().fromJson(json, AdvotherReceiver.class);
		return JsonUtils.getGson().toJson(this.prilistItemService.selectAll(receiver));
	}
	
}
