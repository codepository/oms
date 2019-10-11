package com.lt.cloud.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="advitem")
public class Advitem{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SYS_DOCUMENTID;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_CREATED;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_LASTMODIFIED;
	private Integer SYS_ISLOCKED;
	private Integer SYS_DELETEFLAG;
	private Long SYS_CURRENTUSERID;
	private String SYS_CURRENTUSERNAME;
    private String SYS_CURRENTSTATUS;
	private String SYS_AUTHORS;
	private Integer SYS_ISKEEP;
	private Long AI_OrderID;//advdoc
	private Integer AI_Size_ID;//advdoc
	private String AI_Size;//advdoc
	private Double AI_Width;//advdoc
	private Double AI_Height;//advdoc
	private Integer AI_Color_ID;
	private String AI_Color;
	private String AI_Content;
	private Long AI_Customer_ID;
	private String AI_Customer;
	private Long AI_Agent_ID;
	private String AI_Agent;
	private Long AI_Advertiser_ID;
	private String AI_Advertiser;
	private Long AI_Org_ID;
	private String AI_Org;
	private Long AI_Salesman_ID;
	private String AI_Salesman;
	@Column(name="ai_type")
	private Integer AI_Type;
	@Column(name="ai_publication_id")
	private Integer AI_Publication_ID;
	@Column(name="ai_publication")
	private String AI_Publication;
	@Column(name="ai_edition_id")
	private Integer AI_Edition_ID;
	private String AI_Edition;
	@Column(name="ai_adtype_id")
	private Integer AI_AdType_ID;
	@Column(name="ai_adtype")
	private String AI_AdType;
	@Column(name="ai_tradeid")
	private Integer AI_TradeID;
	@Column(name="ai_trade")
	private String AI_Trade;
	@Column(name="ai_field_id")
	private Integer AI_Field_ID;
	@Column(name="ai_field")
	private String AI_Field;
	@Column(name="ai_pricemodeic")
	private Integer AI_PriceModeIC ;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="ai_publishtime")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date AI_PublishTime;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="ai_publishendtime")
	private Date AI_PublishEndTime;
	@Column(name="ai_publishdaycount")
	private Integer AI_PublishDayCount;
	private String AI_Week;
	@Column(name="ai_paymode_id")
	private Integer AI_PayMode_ID;
	@Column(name="ai_paymode")
	private String AI_PayMode;//支付方式
	@Column(name="ai_pricelist_id")
	private Integer AI_PriceList_ID;
	@Column(name="ai_pricelist")
	private String AI_PriceList;
	@Column(name="ai_price")
	private Double AI_Price;
	@Column(name="ai_unitprice")
	private Double AI_UnitPrice;
	@Column(name="ai_discounttotal")
	private Double AI_DiscountTotal;
	@Column(name="ai_additionalfee")
	private Double AI_AdditionalFee;
	@Column(name="ai_amountreceivable")
	private Double AI_AmountReceivable; //总共需要收款
	@Column(name="ai_amountpaid")
	private Double AI_AmountPaid;
	@Column(name="ai_amountrelief")
	private Double AI_AmountRelief;
	@Column(name="ai_amountreceived")
	private Double AI_AmountReceived;//已收款 （相当于实平账）
	@Column(name="ai_debt")
	private Double AI_Debt; //欠款
	@Column(name="ai_baddebt")
	private Double AI_BadDebt;
	@Column(name="ai_rebatemoney")
	private Double AI_RebateMoney;
	@Column(name="ai_invoicedmoney")
	private Double AI_InvoicedMoney;
	@Column(name="ai_uninvoicedmoney")
	private Double AI_UninvoicedMoney;
	@Column(name="ai_balancedmoney")
	private Double AI_BalancedMoney; //已平账
	@Column(name="ai_unbalancedmoney")
	private Double AI_UnbalancedMoney;//未平账
	@Column(name="ai_paystatus")
	private Integer AI_PayStatus;//0未平帐，1已平帐，2部分平帐
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name="ai_paytime")
	private Date AI_PayTime;
	@Column(name="ai_advpages")
	private Double AI_AdvPages;
	@Column(name="ai_invoiceno")
	private String AI_InvoiceNo;
	private Long AI_OperatorID;
	@Column(name="ai_area")
	private Double AI_Area ;
	@Column(name="ai_pubmemo")
	private String  AI_PubMemo ;// 划版备注
	@Column(name="ai_memo")
	private String AI_Memo;
	@Column(name="ai_articleid")
	private Long AI_ArticleID;
	private Long E_MID_ID; // 投放日
	private String E_MID;
	@Override
	public String toString() {
		return "Advitem [AI_Price=" + AI_Price + ", AI_UnitPrice=" + AI_UnitPrice + ", AI_AmountReceivable="
				+ AI_AmountReceivable + ", AI_AmountPaid=" + AI_AmountPaid + ", AI_AmountRelief=" + AI_AmountRelief
				+ ", AI_AmountReceived=" + AI_AmountReceived + ", AI_Debt=" + AI_Debt + ", AI_BadDebt=" + AI_BadDebt
				+ ", AI_RebateMoney=" + AI_RebateMoney + ", AI_InvoicedMoney=" + AI_InvoicedMoney
				+ ", AI_UninvoicedMoney=" + AI_UninvoicedMoney + ", AI_BalancedMoney=" + AI_BalancedMoney
				+ ", AI_UnbalancedMoney=" + AI_UnbalancedMoney + "]";
	}
	public Advitem() {}
	public Advitem(Double AI_AmountReceivable,Double AI_AmountPaid,Double AI_AmountReceived,Double AI_Debt,Double AI_AdvPages) {
		this.AI_AmountReceivable=AI_AmountReceivable;
		this.AI_AmountPaid=AI_AmountPaid;
		this.AI_AmountReceived=AI_AmountReceived;
		this.AI_Debt=AI_Debt;
		this.AI_AdvPages=AI_AdvPages;
	}
	
	public Long getE_MID_ID() {
		return E_MID_ID;
	}
	public void setE_MID_ID(Long e_MID_ID) {
		E_MID_ID = e_MID_ID;
	}
	public String getE_MID() {
		return E_MID;
	}
	public void setE_MID(String e_MID) {
		E_MID = e_MID;
	}
	public String getSYS_CURRENTSTATUS() {
		return SYS_CURRENTSTATUS;
	}
	public void setSYS_CURRENTSTATUS(String sYS_CURRENTSTATUS) {
		SYS_CURRENTSTATUS = sYS_CURRENTSTATUS;
	}
	public Long getAI_ArticleID() {
		return AI_ArticleID;
	}
	public void setAI_ArticleID(Long aI_ArticleID) {
		AI_ArticleID = aI_ArticleID;
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
	public Integer getSYS_ISLOCKED() {
		return SYS_ISLOCKED;
	}
	public void setSYS_ISLOCKED(Integer sYS_ISLOCKED) {
		SYS_ISLOCKED = sYS_ISLOCKED;
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
	public Integer getSYS_ISKEEP() {
		return SYS_ISKEEP;
	}
	public void setSYS_ISKEEP(Integer sYS_ISKEEP) {
		SYS_ISKEEP = sYS_ISKEEP;
	}
	public Long getAI_OrderID() {
		return AI_OrderID;
	}
	public void setAI_OrderID(Long aI_OrderID) {
		AI_OrderID = aI_OrderID;
	}
	public Integer getAI_Size_ID() {
		return AI_Size_ID;
	}
	public void setAI_Size_ID(Integer aI_Size_ID) {
		AI_Size_ID = aI_Size_ID;
	}
	public String getAI_Size() {
		return AI_Size;
	}
	public void setAI_Size(String aI_Size) {
		AI_Size = aI_Size;
	}
	public Double getAI_Width() {
		return AI_Width;
	}
	public void setAI_Width(Double aI_Width) {
		AI_Width = aI_Width;
	}
	public Double getAI_Height() {
		return AI_Height;
	}
	public void setAI_Height(Double aI_Height) {
		AI_Height = aI_Height;
	}
	public Integer getAI_Color_ID() {
		return AI_Color_ID;
	}
	public void setAI_Color_ID(Integer aI_Color_ID) {
		AI_Color_ID = aI_Color_ID;
	}
	public String getAI_Color() {
		return AI_Color;
	}
	public void setAI_Color(String aI_Color) {
		AI_Color = aI_Color;
	}
	public String getAI_Content() {
		return AI_Content;
	}
	public void setAI_Content(String aI_Content) {
		AI_Content = aI_Content;
	}

	public Long getAI_Customer_ID() {
		return AI_Customer_ID;
	}
	public void setAI_Customer_ID(Long aI_Customer_ID) {
		AI_Customer_ID = aI_Customer_ID;
	}
	public String getAI_Customer() {
		return AI_Customer;
	}
	public void setAI_Customer(String aI_Customer) {
		AI_Customer = aI_Customer;
	}
	public Long getAI_Agent_ID() {
		return AI_Agent_ID;
	}
	public void setAI_Agent_ID(Long aI_Agent_ID) {
		AI_Agent_ID = aI_Agent_ID;
	}
	public String getAI_Agent() {
		return AI_Agent;
	}
	public void setAI_Agent(String aI_Agent) {
		AI_Agent = aI_Agent;
	}
	public Long getAI_Advertiser_ID() {
		return AI_Advertiser_ID;
	}
	public void setAI_Advertiser_ID(Long aI_Advertiser_ID) {
		AI_Advertiser_ID = aI_Advertiser_ID;
	}
	public String getAI_Advertiser() {
		return AI_Advertiser;
	}
	public void setAI_Advertiser(String aI_Advertiser) {
		AI_Advertiser = aI_Advertiser;
	}
	public Long getAI_Org_ID() {
		return AI_Org_ID;
	}
	public void setAI_Org_ID(Long aI_Org_ID) {
		AI_Org_ID = aI_Org_ID;
	}
	public String getAI_Org() {
		return AI_Org;
	}
	public void setAI_Org(String aI_Org) {
		AI_Org = aI_Org;
	}
	public Long getAI_Salesman_ID() {
		return AI_Salesman_ID;
	}
	public void setAI_Salesman_ID(Long aI_Salesman_ID) {
		AI_Salesman_ID = aI_Salesman_ID;
	}
	public String getAI_Salesman() {
		return AI_Salesman;
	}
	public void setAI_Salesman(String aI_Salesman) {
		AI_Salesman = aI_Salesman;
	}
	public Integer getAI_Type() {
		return AI_Type;
	}
	public void setAI_Type(Integer aI_Type) {
		AI_Type = aI_Type;
	}
	public Integer getAI_Publication_ID() {
		return AI_Publication_ID;
	}
	public void setAI_Publication_ID(Integer aI_Publication_ID) {
		AI_Publication_ID = aI_Publication_ID;
	}
	public String getAI_Publication() {
		return AI_Publication;
	}
	public void setAI_Publication(String aI_Publication) {
		AI_Publication = aI_Publication;
	}
	public Integer getAI_Edition_ID() {
		return AI_Edition_ID;
	}
	public void setAI_Edition_ID(Integer aI_Edition_ID) {
		AI_Edition_ID = aI_Edition_ID;
	}
	public String getAI_Edition() {
		return AI_Edition;
	}
	public void setAI_Edition(String aI_Edition) {
		AI_Edition = aI_Edition;
	}
	public Integer getAI_AdType_ID() {
		return AI_AdType_ID;
	}
	public void setAI_AdType_ID(Integer aI_AdType_ID) {
		AI_AdType_ID = aI_AdType_ID;
	}
	public String getAI_AdType() {
		return AI_AdType;
	}
	public void setAI_AdType(String aI_AdType) {
		AI_AdType = aI_AdType;
	}
	public Integer getAI_TradeID() {
		return AI_TradeID;
	}
	public void setAI_TradeID(Integer aI_TradeID) {
		AI_TradeID = aI_TradeID;
	}
	public String getAI_Trade() {
		return AI_Trade;
	}
	public void setAI_Trade(String aI_Trade) {
		AI_Trade = aI_Trade;
	}
	public Integer getAI_Field_ID() {
		return AI_Field_ID;
	}
	public void setAI_Field_ID(Integer aI_Field_ID) {
		AI_Field_ID = aI_Field_ID;
	}
	public String getAI_Field() {
		return AI_Field;
	}
	public void setAI_Field(String aI_Field) {
		AI_Field = aI_Field;
	}
	public Integer getAI_PriceModeIC() {
		return AI_PriceModeIC;
	}
	public void setAI_PriceModeIC(Integer aI_PriceModeIC) {
		AI_PriceModeIC = aI_PriceModeIC;
	}
	
	public Date getAI_PublishTime() {
		return AI_PublishTime;
	}
	public void setAI_PublishTime(Date aI_PublishTime) {
		AI_PublishTime = aI_PublishTime;
	}
	
	public Date getAI_PublishEndTime() {
		return AI_PublishEndTime;
	}
	public void setAI_PublishEndTime(Date aI_PublishEndTime) {
		AI_PublishEndTime = aI_PublishEndTime;
	}
	public Integer getAI_PublishDayCount() {
		return AI_PublishDayCount;
	}
	public void setAI_PublishDayCount(Integer aI_PublishDayCount) {
		AI_PublishDayCount = aI_PublishDayCount;
	}
	
	public String getAI_Week() {
		return AI_Week;
	}
	public void setAI_Week(String aI_Week) {
		AI_Week = aI_Week;
	}
	public Integer getAI_PayMode_ID() {
		return AI_PayMode_ID;
	}
	public void setAI_PayMode_ID(Integer aI_PayMode_ID) {
		AI_PayMode_ID = aI_PayMode_ID;
	}
	public String getAI_PayMode() {
		return AI_PayMode;
	}
	public void setAI_PayMode(String aI_PayMode) {
		AI_PayMode = aI_PayMode;
	}
	public Integer getAI_PriceList_ID() {
		return AI_PriceList_ID;
	}
	public void setAI_PriceList_ID(Integer aI_PriceList_ID) {
		AI_PriceList_ID = aI_PriceList_ID;
	}
	public String getAI_PriceList() {
		return AI_PriceList;
	}
	public void setAI_PriceList(String aI_PriceList) {
		AI_PriceList = aI_PriceList;
	}
	public Double getAI_Price() {
		return AI_Price;
	}
	public void setAI_Price(Double aI_Price) {
		AI_Price = aI_Price;
	}
	public Double getAI_UnitPrice() {
		return AI_UnitPrice;
	}
	public void setAI_UnitPrice(Double aI_UnitPrice) {
		AI_UnitPrice = aI_UnitPrice;
	}
	public Double getAI_DiscountTotal() {
		return AI_DiscountTotal;
	}
	public void setAI_DiscountTotal(Double aI_DiscountTotal) {
		AI_DiscountTotal = aI_DiscountTotal;
	}
	public Double getAI_AdditionalFee() {
		return AI_AdditionalFee;
	}
	public void setAI_AdditionalFee(Double aI_AdditionalFee) {
		AI_AdditionalFee = aI_AdditionalFee;
	}
	public Double getAI_AmountReceivable() {
		return AI_AmountReceivable;
	}
	public void setAI_AmountReceivable(Double aI_AmountReceivable) {
		AI_AmountReceivable = aI_AmountReceivable;
	}
	public Double getAI_AmountPaid() {
		return AI_AmountPaid;
	}
	public void setAI_AmountPaid(Double aI_AmountPaid) {
		AI_AmountPaid = aI_AmountPaid;
	}
	public Double getAI_AmountRelief() {
		return AI_AmountRelief;
	}
	public void setAI_AmountRelief(Double aI_AmountRelief) {
		AI_AmountRelief = aI_AmountRelief;
	}
	public Double getAI_AmountReceived() {
		return AI_AmountReceived;
	}
	public void setAI_AmountReceived(Double aI_AmountReceived) {
		AI_AmountReceived = aI_AmountReceived;
	}
	public Double getAI_Debt() {
		return AI_Debt;
	}
	public void setAI_Debt(Double aI_Debt) {
		AI_Debt = aI_Debt;
	}
	public Double getAI_BadDebt() {
		return AI_BadDebt;
	}
	public void setAI_BadDebt(Double aI_BadDebt) {
		AI_BadDebt = aI_BadDebt;
	}
	public Double getAI_RebateMoney() {
		return AI_RebateMoney;
	}
	public void setAI_RebateMoney(Double aI_RebateMoney) {
		AI_RebateMoney = aI_RebateMoney;
	}
	public Double getAI_InvoicedMoney() {
		return AI_InvoicedMoney;
	}
	public void setAI_InvoicedMoney(Double aI_InvoicedMoney) {
		AI_InvoicedMoney = aI_InvoicedMoney;
	}
	public Double getAI_UninvoicedMoney() {
		return AI_UninvoicedMoney;
	}
	public void setAI_UninvoicedMoney(Double aI_UninvoicedMoney) {
		AI_UninvoicedMoney = aI_UninvoicedMoney;
	}
	public Double getAI_BalancedMoney() {
		return AI_BalancedMoney;
	}
	public void setAI_BalancedMoney(Double aI_BalancedMoney) {
		AI_BalancedMoney = aI_BalancedMoney;
	}
	public Double getAI_UnbalancedMoney() {
		return AI_UnbalancedMoney;
	}
	public void setAI_UnbalancedMoney(Double aI_UnbalancedMoney) {
		AI_UnbalancedMoney = aI_UnbalancedMoney;
	}
	public Integer getAI_PayStatus() {
		return AI_PayStatus;
	}
	public void setAI_PayStatus(Integer aI_PayStatus) {
		AI_PayStatus = aI_PayStatus;
	}
	public Date getAI_PayTime() {
		return AI_PayTime;
	}
	public void setAI_PayTime(Date aI_PayTime) {
		AI_PayTime = aI_PayTime;
	}
	public Double getAI_AdvPages() {
		return AI_AdvPages;
	}
	public void setAI_AdvPages(Double aI_AdvPages) {
		AI_AdvPages = aI_AdvPages;
	}
	public String getAI_InvoiceNo() {
		return AI_InvoiceNo;
	}
	public void setAI_InvoiceNo(String aI_InvoiceNo) {
		AI_InvoiceNo = aI_InvoiceNo;
	}
	public Long getAI_OperatorID() {
		return AI_OperatorID;
	}
	public void setAI_OperatorID(Long aI_OperatorID) {
		AI_OperatorID = aI_OperatorID;
	}
	public Double getAI_Area() {
		return AI_Area;
	}
	public void setAI_Area(Double aI_Area) {
		AI_Area = aI_Area;
	}
	public String getAI_PubMemo() {
		return AI_PubMemo;
	}
	public void setAI_PubMemo(String aI_PubMemo) {
		AI_PubMemo = aI_PubMemo;
	}
	public String getAI_Memo() {
		return AI_Memo;
	}
	public void setAI_Memo(String aI_Memo) {
		AI_Memo = aI_Memo;
	}
	public Integer getSYS_DELETEFLAG() {
		return SYS_DELETEFLAG;
	}
	public void setSYS_DELETEFLAG(Integer sYS_DELETEFLAG) {
		SYS_DELETEFLAG = sYS_DELETEFLAG;
	}
	
}
