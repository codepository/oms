package com.lt.cloud.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="advorder")
public class Advorder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SYS_DOCUMENTID;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_CREATED;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_LASTMODIFIED;
	private Long SYS_CURRENTUSERID;
	private String SYS_CURRENTUSERNAME;
	private String SYS_AUTHORS;
	@Column(name="ao_customer_id")
	private Long AO_Customer_ID;
	@Column(name="ao_customer")
	private String AO_Customer;
	@Column(name="ao_agent_id")
	private Long AO_Agent_ID;
	@Column(name="ao_agent")
	private String AO_Agent;
	@Column(name="ao_advertiser_id")
	private Long AO_Advertiser_ID;
	private String AO_Advertiser;
	private Integer SYS_DELETEFLAG;
	@Column(name="ao_org_id")
	private Integer AO_Org_ID;
	@Column(name="ao_org")
	private String AO_Org;
	@Column(name="ao_salesman_id")
	private String AO_Salesman_ID;
	@Column(name="ao_salesman")
	private String AO_Salesman;
	@Column(name="ao_adcountic")
	private Integer AO_AdCountIC;
	@Column(name="ao_allmoney")
	private Double AO_AllMoney;
	@Column(name="ao_amountpaid")
	private Double AO_AmountPaid;
	@Column(name="ao_receivedmoney")
	private Double AO_ReceivedMoney;
	@Column(name="ao_debtmoney")
	private Double AO_DebtMoney;
	@Column(name="ao_operatorid")
	private Long AO_OperatorID;
	private String AO_Memo;
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	
	public Integer getSYS_DELETEFLAG() {
		return SYS_DELETEFLAG;
	}

	public void setSYS_DELETEFLAG(Integer sYS_DELETEFLAG) {
		SYS_DELETEFLAG = sYS_DELETEFLAG;
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
	public Long getAO_Customer_ID() {
		return AO_Customer_ID;
	}
	public void setAO_Customer_ID(Long aO_Customer_ID) {
		AO_Customer_ID = aO_Customer_ID;
	}
	public String getAO_Customer() {
		return AO_Customer;
	}
	public void setAO_Customer(String aO_Customer) {
		AO_Customer = aO_Customer;
	}
	public Long getAO_Agent_ID() {
		return AO_Agent_ID;
	}
	public void setAO_Agent_ID(Long aO_Agent_ID) {
		AO_Agent_ID = aO_Agent_ID;
	}
	public String getAO_Agent() {
		return AO_Agent;
	}
	public void setAO_Agent(String aO_Agent) {
		AO_Agent = aO_Agent;
	}
	public Long getAO_Advertiser_ID() {
		return AO_Advertiser_ID;
	}
	public void setAO_Advertiser_ID(Long aO_Advertiser_ID) {
		AO_Advertiser_ID = aO_Advertiser_ID;
	}
	public String getAO_Advertiser() {
		return AO_Advertiser;
	}
	public void setAO_Advertiser(String aO_Advertiser) {
		AO_Advertiser = aO_Advertiser;
	}

	public Integer getAO_Org_ID() {
		return AO_Org_ID;
	}
	public void setAO_Org_ID(Integer aO_Org_ID) {
		AO_Org_ID = aO_Org_ID;
	}
	public String getAO_Org() {
		return AO_Org;
	}
	public void setAO_Org(String aO_Org) {
		AO_Org = aO_Org;
	}
	public String getAO_Salesman_ID() {
		return AO_Salesman_ID;
	}
	public void setAO_Salesman_ID(String aO_Salesman_ID) {
		AO_Salesman_ID = aO_Salesman_ID;
	}
	public String getAO_Salesman() {
		return AO_Salesman;
	}
	public void setAO_Salesman(String aO_Salesman) {
		AO_Salesman = aO_Salesman;
	}
	public Integer getAO_AdCountIC() {
		return AO_AdCountIC;
	}
	public void setAO_AdCountIC(Integer aO_AdCountIC) {
		AO_AdCountIC = aO_AdCountIC;
	}
	public  Double getAO_AllMoney() {
		return AO_AllMoney;
	}
	public void setAO_AllMoney(Double aO_AllMoney) {
		AO_AllMoney = aO_AllMoney;
	}
	public  Double getAO_AmountPaid() {
		return AO_AmountPaid;
	}
	public void setAO_AmountPaid(Double aO_AmountPaid) {
		AO_AmountPaid = aO_AmountPaid;
	}
	public  Double getAO_ReceivedMoney() {
		return AO_ReceivedMoney;
	}
	public void setAO_ReceivedMoney(Double aO_ReceivedMoney) {
		AO_ReceivedMoney = aO_ReceivedMoney;
	}
	public  Double getAO_DebtMoney() {
		return AO_DebtMoney;
	}
	public void setAO_DebtMoney(Double aO_DebtMoney) {
		AO_DebtMoney = aO_DebtMoney;
	}
	public Long getAO_OperatorID() {
		return AO_OperatorID;
	}
	public void setAO_OperatorID(Long aO_OperatorID) {
		AO_OperatorID = aO_OperatorID;
	}
	public String getAO_Memo() {
		return AO_Memo;
	}
	public void setAO_Memo(String aO_Memo) {
		AO_Memo = aO_Memo;
	}

	
}
