package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.interceptor.RequiredPermission;
import com.lt.cloud.pojo.BalanceReceiver;
import com.lt.cloud.service.BalanceService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

import reactor.core.publisher.Mono;

@RefreshScope
@RestController
@RequestMapping("/balance")
public class BalanceController {
	@Autowired
	private BalanceService balanceService;
	@RequestMapping(value = "/findAll")
	public String findAll(@RequestBody String json) {
		return JsonUtils.formatPageForPagination(this.balanceService.findAll(json));
	}
	@RequestMapping(value="/saveAll")
	@RequiredPermission()
	public String saveAll(@RequestBody String json ){
		return ResponseCodeUtils.response(this.balanceService.saveAll(json));
		
	}
	@RequestMapping(value="/findAllReversable/{payId}")
	public Mono<String> findAllReversable(@PathVariable Long payId){
		return Mono.just(JsonUtils.getGson().toJson(this.balanceService.findAllReversable(payId)));
	}
	@RequestMapping(value="/findAllReversable")
	public Mono<String> findAllReversable1(@RequestBody String json){
		BalanceReceiver receiver=JsonUtils.getGson().fromJson(json, BalanceReceiver.class);
		return Mono.just(JsonUtils.getGson().toJson(this.balanceService.findAllReversable(receiver)));
	}
	@RequestMapping(value="/findByPayIdResultAdv/{payId}")
	public Mono<String> findByPayIdResultAdv(@PathVariable Long payId){
		return Mono.just(JsonUtils.getGson().toJson(this.balanceService.findByPayIdResultAdv(payId)));
	}
	@RequestMapping(value="/findAdvidByPayid/{id}")
	public String findAdvidByPayid(@PathVariable Long id) {
		return this.balanceService.findAdvidByPayidAsJson(id);
	}
	@RequestMapping(value="/findAdvByPayidAndPagable")
	public String findAdvByPayidAndPagable(@RequestBody String json) {
		return this.balanceService.findAdvByPayidAndPagable(json);
	}
	@RequestMapping(value="/findAdvidByPayidAndPagable")
	public String findAdvidByPayidAndPagable(@RequestBody String json) {
		return this.balanceService.findAdvidByPayidAndPagable(json);
	}
	@RequestMapping(value="/balance")
	@RequiredPermission()
	public String balance(@RequestBody String json ) {
		return ResponseCodeUtils.response(this.balanceService.balance(json));
	}
	@RequestMapping(value="/findStatistics/{paymentid}")
	public String findStatistics(@PathVariable Long paymentid) {
		return this.balanceService.findStatistics(paymentid);
	}
	@RequestMapping(value="/findAdvitemStaticsByPayid/{paymentid}")
	public String findAdvitemStaticsByPayid(@PathVariable Long paymentid) {
		return this. balanceService.findAdvitemStaticsByPayid(paymentid);
	}
}
