package com.lt.cloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.pojo.AdvOrg;
import com.lt.cloud.pojo.Trade;
import com.lt.cloud.service.AdvOrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

@RefreshScope
@RestController
public class AdvOrgController {
	@Autowired
	private AdvOrgService advOrgService;
//	@RequestMapping("/advTrade/catTree")
//	public String getTrade() {
//		return advOrgService.buildTradeJSONTree();
//	}
	@RequestMapping("/advTrade/catTree")
	public String getTrade(@RequestParam String username) {
		return advOrgService.buildTradeJSONTree(username);
	}
	@RequestMapping("/advTrade/save")
	public String saveTrade(@RequestBody String json) {
		Trade trade=JsonUtils.getGson().fromJson(json, Trade.class);
		if (StringUtils.isEmpty(trade.getText())) {
			return ResponseCodeUtils.responseErr("行业名称text不能为空");
		}
		if (trade.getParentid()==0) {
			return ResponseCodeUtils.responseErr("父节点parentid不能为空"); 
		}
		if (trade.getDepth()==0) {
			return ResponseCodeUtils.responseErr("当前行业的depth不能为空,值一般为父行业的 depth-1"); 
		}
	
		return advOrgService.save(trade);
	}
	@RequestMapping("/advTrade/deleteById")
	public String deleteTradeById(@RequestParam int id) {
		return advOrgService.deleteById(id);
	}
//	@RequestMapping("/advTrade/catTree")
//	public String getAdvTradeTree() {
//		List<AdvTrade> list=advOrgService.selectAllTrade(new HashMap<>());
//		List<AdvTrade> list2=new ArrayList<>();
//		for (AdvTrade advOrg3:list) {
//			
//			AdvTrade advOrg=(AdvTrade) advOrg3.clone();
//
//			for (AdvTrade advOrg2:list) {
//
//				if(advOrg2.insertIfIsParent(advOrg, advOrg2)) {
//					list2.add(advOrg3);
//				}
//
//			}
//			
//		}
//
//		for (AdvTrade advOrg : list2) {
//			list.remove(advOrg);
//		}
//		for (AdvTrade advOrg : list) {
//			advOrg.operation();
//		}
//
//		return JsonUtils.getGson().toJson(list);
//	}
	@RequestMapping("/advOrg/catTree")
	public String getCatTree() {
		List<AdvOrg> list=advOrgService.selectAllAdvOrg(new HashMap<>());
		List<AdvOrg> list2=new ArrayList<>();
		
		for (AdvOrg advOrg3:list) {

			AdvOrg advOrg=(AdvOrg) advOrg3.clone();

			for (AdvOrg advOrg2:list) {

				if(advOrg2.insertIfIsParent(advOrg, advOrg2)) {
					list2.add(advOrg3);
				}

			}
			
		}

		for (AdvOrg advOrg : list2) {
			list.remove(advOrg);
		}
//		for (AdvOrg advOrg : list) {
//			advOrg.operation();
//		}

		return JsonUtils.getGson().toJson(list);
	}
}
