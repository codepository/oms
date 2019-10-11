package com.lt.cloud.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer {
	private Long SYS_DOCUMENTID;


	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_CREATED;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_LASTMODIFIED;
	
	private String SYS_AUTHORS;
	
	private String CUST_NAME;
	private String CUST_CODE;
	private int CUST_USER_ID;
	private String CUST_USER;



	private String CUST_TRADEID;
	private String CUST_TRADE;
	private String CUST_PHONE;

	private String CUST_CREDIT;
	private String CUST_KEYWORDS;
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}
	
	public String getCUST_PHONE() {
		return CUST_PHONE;
	}
	public void setCUST_PHONE(String cUST_PHONE) {
		CUST_PHONE = cUST_PHONE;
	}
	public Date getSYS_CREATED() {
		return SYS_CREATED;
	}
	public void setSYS_CREATED(Date sYS_CREATED) {
		SYS_CREATED = sYS_CREATED;
	}
	public Date getSYS_LASTMODIFIED() {
		return SYS_LASTMODIFIED;
	}
	public void setSYS_LASTMODIFIED(Date sYS_LASTMODIFIED) {
		SYS_LASTMODIFIED = sYS_LASTMODIFIED;
	}
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	public String getCUST_CODE() {
		return CUST_CODE;
	}
	public void setCUST_CODE(String cUST_CODE) {
		CUST_CODE = cUST_CODE;
	}
	public int getCUST_USER_ID() {
		return CUST_USER_ID;
	}
	public void setCUST_USER_ID(int cUST_USER_ID) {
		CUST_USER_ID = cUST_USER_ID;
	}
	public String getCUST_USER() {
		return CUST_USER;
	}
	public void setCUST_USER(String cUST_USER) {
		CUST_USER = cUST_USER;
	}
	public String getCUST_TRADEID() {
		return CUST_TRADEID;
	}
	public void setCUST_TRADEID(String cUST_TRADEID) {
		CUST_TRADEID = cUST_TRADEID;
	}
	public String getCUST_TRADE() {
		return CUST_TRADE;
	}
	public void setCUST_TRADE(String cUST_TRADE) {
		CUST_TRADE = cUST_TRADE;
	}
	public String getCUST_CREDIT() {
		return CUST_CREDIT;
	}
	public void setCUST_CREDIT(String cUST_CREDIT) {
		CUST_CREDIT = cUST_CREDIT;
	}
	public String getCUST_KEYWORDS() {
		return CUST_KEYWORDS;
	}
	public void setCUST_KEYWORDS(String cUST_KEYWORDS) {
		CUST_KEYWORDS = cUST_KEYWORDS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
