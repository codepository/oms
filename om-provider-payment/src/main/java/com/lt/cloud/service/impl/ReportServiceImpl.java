package com.lt.cloud.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lt.cloud.dao.jpa.ReportRepository;
import com.lt.cloud.pojo.Report;
import com.lt.cloud.service.ReportService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

@Component
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportRepository reportRepository;
	@Override
	public String save(String json) {
		Report report=JsonUtils.getGson().fromJson(json, Report.class);
		
		return this.save(report);
	}

	private String save(Report report) {
		report.setCreateddate(new Date());
		try {
			if (report.getTablename()==null) {
				return ResponseCodeUtils.response("字段tablename不能为空", false);
			}
			if (report.getCreatorid()==null) {
				return ResponseCodeUtils.response("字段creatorid不能为空", false);
			}
			if (StringUtils.isEmpty(report.getTitle().trim())) {
				return ResponseCodeUtils.response("title 不能为空", false);
			}
			if (this.existsByCreatoridAndTitle(report.getCreatorid(),report.getTitle())) {
				return ResponseCodeUtils.response("【"+report.getTitle()+"】已经存在", false);
			};
			this.reportRepository.save(report);
			return ResponseCodeUtils.response(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.response(e.toString(),false);
		}

		
		
	}

	private boolean existsByCreatoridAndTitle(Long creatorid, String title) {
		
		return this.reportRepository.existsByCreatoridAndTitle(creatorid, title);
	}

	@Override
	public String update(String json) {
		Report report=JsonUtils.getGson().fromJson(json, Report.class);

		return this.save(report);
	}

	@Override
	public String findAll(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Long id) {
		try {
			this.reportRepository.deleteById(id);
			return ResponseCodeUtils.response(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.response(e.toString(),false);
		}
	}

	@Override
	public String findAllByCreatoridAndTable(Long creatorid, String table) {
		List<Report> list=this.findBycreatoridAndTable(creatorid, table);
		return JsonUtils.getGson().toJson(list);
	}

	private List<Report> findBycreatoridAndTable(Long creatorid, String table) {
		
		return this.reportRepository.findBycreatoridAndTablename(creatorid, table);
	}

}
