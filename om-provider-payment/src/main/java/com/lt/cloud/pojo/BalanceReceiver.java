package com.lt.cloud.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BalanceReceiver {
	  private Long SYS_DOCUMENTID;
	  private String SYS_CURRENTSTATUS;
	  private String SYS_AUTHORS;
	  private  String customer;//客户
	  private  Long orderid;//订单号
	  private  Long aditemid;//广告号
	  private  Long B_PayID;//收款号
	  private String B_InvoiceNo;//发票号
	  private Boolean isReversable;//是否可反平帐
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date publishtimeStart;//刊期
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date publishtimeEnd;
	  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	  private Date SYS_CREATEDSTART;
	  @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	  private Date SYS_CREATEDEND;
	  private String B_Salesman;
	  private Integer pageIndex;
	  private Integer pageSize;
	  private  String B_Org;
	  
	public String getSYS_CURRENTSTATUS() {
		return SYS_CURRENTSTATUS;
	}

	public void setSYS_CURRENTSTATUS(String sYS_CURRENTSTATUS) {
		SYS_CURRENTSTATUS = sYS_CURRENTSTATUS;
	}

	public String getB_InvoiceNo() {
		return B_InvoiceNo;
	}

	public String getB_Org() {
		return B_Org;
	}

	public void setB_Org(String b_Org) {
		B_Org = b_Org;
	}

	public void setB_InvoiceNo(String b_InvoiceNo) {
		B_InvoiceNo = b_InvoiceNo;
	}

	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}

	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}

	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}

	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}

	public String getB_Salesman() {
		return B_Salesman;
	}

	public void setB_Salesman(String b_Salesman) {
		B_Salesman = b_Salesman;
	}

	public String getCustomer() {
		return customer;
	}
	
	public Boolean getIsReversable() {
		return isReversable;
	}

	public void setIsReversable(Boolean isReversable) {
		this.isReversable = isReversable;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Long getAditemid() {
		return aditemid;
	}
	public void setAditemid(Long aditemid) {
		this.aditemid = aditemid;
	}

	public Long getB_PayID() {
		return B_PayID;
	}
	public void setB_PayID(Long b_PayID) {
		B_PayID = b_PayID;
	}
	public Date getPublishtimeStart() {
		return publishtimeStart;
	}
	public void setPublishtimeStart(Date publishtimeStart) {
		this.publishtimeStart = publishtimeStart;
	}
	public Date getPublishtimeEnd() {
		return publishtimeEnd;
	}
	public void setPublishtimeEnd(Date publishtimeEnd) {
		this.publishtimeEnd = publishtimeEnd;
	}
	public Date getSYS_CREATEDSTART() {
		return SYS_CREATEDSTART;
	}
	public void setSYS_CREATEDSTART(Date sYS_CREATEDSTART) {
		SYS_CREATEDSTART = sYS_CREATEDSTART;
	}
	public Date getSYS_CREATEDEND() {
		return SYS_CREATEDEND;
	}
	public void setSYS_CREATEDEND(Date sYS_CREATEDEND) {
		SYS_CREATEDEND = sYS_CREATEDEND;
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
