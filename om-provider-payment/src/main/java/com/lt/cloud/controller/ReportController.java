package com.lt.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.cloud.service.ReportService;
/**
 * 用户自定义报表查询字段设置
 * @author lt
 *
 */
@RestController
@RequestMapping("/report")
public class ReportController{
	@Autowired
	private ReportService reportService;
	@RequestMapping("/save")
	public String save(@RequestBody String json) {
		return this.reportService.save(json);
	}
	@RequestMapping("/update")
	public String update(@RequestBody String json) {
		return this.reportService.update(json);
	}
	@RequestMapping("/findAll")
	public String findAll(@RequestBody String json) {
		return this.reportService.findAll(json);
	}
	@RequestMapping("/findAllByCreatoridAndTable")
	public String findAllByCreatoridAndTable(@RequestParam Long creatorid,@RequestParam String tablename) {
		return this.reportService.findAllByCreatoridAndTable(creatorid,tablename);
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam Long id) {
		return this.reportService.delete(id);
	}
}
