package com.lt.cloud.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdvorderReceiver {
	private Long SYS_DOCUMENTID;
	private String AO_Customer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private String AO_Salesman;
	private Integer pageIndex;
	private Integer pageSize;
	private String SYS_AUTHORS;
	private String AO_Org;
	
	public String getAO_Org() {
		return AO_Org;
	}
	public void setAO_Org(String aO_Org) {
		AO_Org = aO_Org;
	}
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}
	
	public String getAO_Salesman() {
		return AO_Salesman;
	}
	public void setAO_Salesman(String aO_Salesman) {
		AO_Salesman = aO_Salesman;
	}
	public String getAO_Customer() {
		return AO_Customer;
	}
	public void setAO_Customer(String aO_Customer) {
		AO_Customer = aO_Customer;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
