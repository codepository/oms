package com.lt.cloud.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class InvoiceReceiver {
	private Integer type;
	private  String invoiceno;
	private  String customer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date I_DateStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date I_DateEnd;
	private Integer pageIndex;
	private Integer pageSize;
	private String SYS_AUTHORS;
	
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public Date getI_DateStart() {
		return I_DateStart;
	}
	public void setI_DateStart(Date i_DateStart) {
		I_DateStart = i_DateStart;
	}
	public Date getI_DateEnd() {
		return I_DateEnd;
	}
	public void setI_DateEnd(Date i_DateEnd) {
		I_DateEnd = i_DateEnd;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
}
