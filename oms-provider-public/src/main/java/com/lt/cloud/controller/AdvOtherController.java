package com.lt.cloud.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.service.AdvOtherQueryService;
import com.lt.cloud.utils.JsonUtils;
@RefreshScope
@RestController
@RequestMapping("/advother")
public class AdvOtherController {
	@Autowired
	private AdvOtherQueryService advOtherQueryService;
	
	@RequestMapping("/selectAll")
	public String selectAll(@RequestBody String json) {
		AdvotherReceiver receiver=JsonUtils.getGson().fromJson(json, AdvotherReceiver.class);
		return JsonUtils.getGson().toJson(this.advOtherQueryService.selectAll(receiver));
	}
	@RequestMapping("/pricemarketday")
	public String selectPricemarketday(@RequestBody String json) {
		AdvotherReceiver receiver=JsonUtils.getGson().fromJson(json, AdvotherReceiver.class);
		List<String> weeks=Arrays.asList("E_Mon","E_Tues","E_Wed","E_Thur","E_Fri","E_Sat","E_Sun");
		receiver.setTable("pricemarketday");
		receiver.setField(weeks.get(receiver.getWeek()-1));
		return JsonUtils.getGson().toJson(this.advOtherQueryService.selectAll(receiver));
	}
	
	
}
