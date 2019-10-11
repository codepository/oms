package com.lt.cloud.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户自定义的查询报表，及查询条件
 * @author lt
 *
 */

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="report")
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createddate;
	private Long creatorid;
	/**
	 * 报表名称
	 */
	private String title;
	// 对应表格
	private String tablename;
	// 查询的显示字段
	private String columns;
	// 被合计字段
	private String groupby;
	// 排序字段
	private String orderby;
	
	
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Long getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(Long creatorid) {
		this.creatorid = creatorid;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getGroupby() {
		return groupby;
	}
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	
}
