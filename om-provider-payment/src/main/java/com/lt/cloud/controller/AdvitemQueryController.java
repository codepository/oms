package com.lt.cloud.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.pojo.AdvitemReceiver;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.utils.JsonUtils;
@RestController
@RequestMapping("/advitem")
public class AdvitemQueryController {
	@Autowired
	private AdvitemQueryService advitemQueryService;
	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		System.out.println(JsonUtils.getGson().toJson(request.getHeader("access-control-allow-origin")));
		return "hello";
	}
	@RequestMapping("/findAll")
	public String findAll(@RequestBody String json){
		//分页
//		System.out.println(json);
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
//		System.out.println("***********"+receiver.getAI_Customer()+"**********");
		return this.advitemQueryService.findAll(receiver);
	}
	@RequestMapping("/findById/{id}")
	public String selectById(@PathVariable Long id) {
		return JsonUtils.getGson().toJson(this.advitemQueryService.selectById(id));
	}
	
	@RequestMapping("/statistics")
	public String statistics(@RequestBody String json) {
		return this.advitemQueryService.findStatistics(json);
	}
	@RequestMapping("/findByAdvids")
	public String findByAdvids(@RequestBody String json) {
		return this.advitemQueryService.findByAdvids(json);
	}
	@RequestMapping(value="/exportResponseEntity",produces = {"application/vnd.ms-excel;charset=UTF-8"})
	public ResponseEntity<byte[]> exportResponseEntity(@RequestBody String json){
		return this.advitemQueryService.exportResponseEntity(json);
	}
	@RequestMapping(value="/exportAllReports",produces= {"application/vnd.ms-excel;charset=UTF-8"})
	public ResponseEntity<byte[]> exportAllReports(@RequestBody String json) {
		return this.advitemQueryService.exportAllReports(json);
	}
	@RequestMapping("/findAllReports")
	public String findAllReports(@RequestBody String json) {
		return this.advitemQueryService.findAllReports(json);
	}
}
