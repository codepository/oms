package com.lt.cloud.pojo;

public class CustomerReceiver {
	private String CUST_NAME;
	private Integer pageSize;
	private Integer pageIndex;
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
}
