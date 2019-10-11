package com.lt.cloud.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Balance{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long SYS_DOCUMENTID;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
     private  Date SYS_CREATED;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
     private  Date SYS_LASTMODIFIED;
	 private Integer SYS_DELETEFLAG;
     private  Long SYS_ISLOCKED;
     private  String SYS_CURRENTSTATUS;
     private  Long SYS_CURRENTUSERID;
     private  String SYS_CURRENTUSERNAME;
     private  String SYS_AUTHORS;
     @Column(name="b_payid")
     private  Long B_PayID;//收款号
     @Column(name="B_orderid")
     private  Long B_OrderID;
     @Column(name="b_aditemid")
     private  Long B_AdItemID;
     private  Long B_Customer_ID;
     @Column(name="B_Customer")
     private  String B_Customer;
    
	 //0
     private  Long B_Advertiser_ID;
    
	
     private  String B_Advertiser;
    
	 //0
     private  Long B_Agent_ID;
    
	
     private  String B_Agent;
    
	 //0
     private  Long B_Org_ID;
    
	
     private  String B_Org;
    
	
     private  String B_Salesman_ID;
    
	
     private  String B_Salesman;
    
     @Column(name="b_paytype")
     private  Long B_PayType;//收款类型
     @Column(name="b_paymode")
     private  String B_PayMode;
     @Column(name="b_paymode_id")
     private  Long B_PayMode_ID;//收款方式
     @DateTimeFormat(pattern="yyyy-MM-dd")
     @Column(name="b_publishtime")
     private  Date B_PublishTime;//刊期
     private Double B_Amount;//平账金额
     @Column(name="b_amountreal")
     private Double B_AmountReal;//实平账金额
	 //0
     private  Long B_Type;//平账方式,平帐0,反平帐1，被反平帐2
     private  Long B_Mode;	//平账方式 ,预平帐1或者平帐0
     @Column(name="b_amountinvoiced")
     private Double B_AmountInvoiced;//已关联发票
     @Column(name="b_amountuninvoiced")
     private Double B_AmountUninvoiced;//未关联发票
     @Column(name="B_invoiceno")
     private  String B_InvoiceNo;
     private  Long B_OperatorID;
     private  Long B_Locked;
     //返回
    public Balance() {};
	public Balance(Long B_OrderID,Long B_AdItemID,Double B_Amount) {
		this.B_OrderID=B_OrderID;
		this.B_AdItemID=B_AdItemID;
		this.B_Amount=B_Amount;
	}
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
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
	public Long getSYS_ISLOCKED() {
		return SYS_ISLOCKED;
	}
	public void setSYS_ISLOCKED(Long sYS_ISLOCKED) {
		SYS_ISLOCKED = sYS_ISLOCKED;
	}
	public String getSYS_CURRENTSTATUS() {
		return SYS_CURRENTSTATUS;
	}
	public void setSYS_CURRENTSTATUS(String sYS_CURRENTSTATUS) {
		SYS_CURRENTSTATUS = sYS_CURRENTSTATUS;
	}
	
	public Integer getSYS_DELETEFLAG() {
		return SYS_DELETEFLAG;
	}
	public void setSYS_DELETEFLAG(Integer sYS_DELETEFLAG) {
		SYS_DELETEFLAG = sYS_DELETEFLAG;
	}
	public Long getSYS_CURRENTUSERID() {
		return SYS_CURRENTUSERID;
	}
	public void setSYS_CURRENTUSERID(Long sYS_CURRENTUSERID) {
		SYS_CURRENTUSERID = sYS_CURRENTUSERID;
	}
	public String getSYS_CURRENTUSERNAME() {
		return SYS_CURRENTUSERNAME;
	}
	public void setSYS_CURRENTUSERNAME(String sYS_CURRENTUSERNAME) {
		SYS_CURRENTUSERNAME = sYS_CURRENTUSERNAME;
	}
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public Long getB_PayID() {
		return B_PayID;
	}
	public void setB_PayID(Long b_PayID) {
		B_PayID = b_PayID;
	}
	public Long getB_OrderID() {
		return B_OrderID;
	}
	public void setB_OrderID(Long b_OrderID) {
		B_OrderID = b_OrderID;
	}
	public Long getB_AdItemID() {
		return B_AdItemID;
	}
	public void setB_AdItemID(Long b_AdItemID) {
		B_AdItemID = b_AdItemID;
	}
	public Long getB_Customer_ID() {
		return B_Customer_ID;
	}
	public void setB_Customer_ID(Long b_Customer_ID) {
		B_Customer_ID = b_Customer_ID;
	}
	public String getB_Customer() {
		return B_Customer;
	}
	public void setB_Customer(String b_Customer) {
		B_Customer = b_Customer;
	}
	public Long getB_Advertiser_ID() {
		return B_Advertiser_ID;
	}
	public void setB_Advertiser_ID(Long b_Advertiser_ID) {
		B_Advertiser_ID = b_Advertiser_ID;
	}
	public String getB_Advertiser() {
		return B_Advertiser;
	}
	public void setB_Advertiser(String b_Advertiser) {
		B_Advertiser = b_Advertiser;
	}
	public Long getB_Agent_ID() {
		return B_Agent_ID;
	}
	public void setB_Agent_ID(Long b_Agent_ID) {
		B_Agent_ID = b_Agent_ID;
	}
	public String getB_Agent() {
		return B_Agent;
	}
	public void setB_Agent(String b_Agent) {
		B_Agent = b_Agent;
	}
	public Long getB_Org_ID() {
		return B_Org_ID;
	}
	public void setB_Org_ID(Long b_Org_ID) {
		B_Org_ID = b_Org_ID;
	}
	public String getB_Org() {
		return B_Org;
	}
	public void setB_Org(String b_Org) {
		B_Org = b_Org;
	}
	public String getB_Salesman_ID() {
		return B_Salesman_ID;
	}
	public void setB_Salesman_ID(String b_Salesman_ID) {
		B_Salesman_ID = b_Salesman_ID;
	}
	public String getB_Salesman() {
		return B_Salesman;
	}
	public void setB_Salesman(String b_Salesman) {
		B_Salesman = b_Salesman;
	}
	public Long getB_PayType() {
		return B_PayType;
	}
	public void setB_PayType(Long b_PayType) {
		B_PayType = b_PayType;
	}
	public String getB_PayMode() {
		return B_PayMode;
	}
	public void setB_PayMode(String b_PayMode) {
		B_PayMode = b_PayMode;
	}
	public Long getB_PayMode_ID() {
		return B_PayMode_ID;
	}
	public void setB_PayMode_ID(Long b_PayMode_ID) {
		B_PayMode_ID = b_PayMode_ID;
	}
	public Date getB_PublishTime() {
		return B_PublishTime;
	}
	public void setB_PublishTime(Date b_PublishTime) {
		B_PublishTime = b_PublishTime;
	}
	public Double getB_Amount() {
		return B_Amount;
	}
	public void setB_Amount(Double b_Amount) {
		B_Amount = b_Amount;
	}
	public Double getB_AmountReal() {
		return B_AmountReal;
	}
	public void setB_AmountReal(Double b_AmountReal) {
		B_AmountReal = b_AmountReal;
	}
	public Long getB_Type() {
		return B_Type;
	}
	public void setB_Type(Long b_Type) {
		B_Type = b_Type;
	}
	public Long getB_Mode() {
		return B_Mode;
	}
	public void setB_Mode(Long b_Mode) {
		B_Mode = b_Mode;
	}
	public Double getB_AmountInvoiced() {
		return B_AmountInvoiced;
	}
	public void setB_AmountInvoiced(Double b_AmountInvoiced) {
		B_AmountInvoiced = b_AmountInvoiced;
	}
	public Double getB_AmountUninvoiced() {
		return B_AmountUninvoiced;
	}
	public void setB_AmountUninvoiced(Double b_AmountUninvoiced) {
		B_AmountUninvoiced = b_AmountUninvoiced;
	}
	public String getB_InvoiceNo() {
		return B_InvoiceNo;
	}
	public void setB_InvoiceNo(String b_InvoiceNo) {
		B_InvoiceNo = b_InvoiceNo;
	}
	public Long getB_OperatorID() {
		return B_OperatorID;
	}
	public void setB_OperatorID(Long b_OperatorID) {
		B_OperatorID = b_OperatorID;
	}
	public Long getB_Locked() {
		return B_Locked;
	}
	public void setB_Locked(Long b_Locked) {
		B_Locked = b_Locked;
	}
     
     

}
