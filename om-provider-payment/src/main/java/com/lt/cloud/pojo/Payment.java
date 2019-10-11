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
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SYS_DOCUMENTiD;
	@Column(name="p_srcid")
	private Integer P_SrcID;//源收款号
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_CREaTED;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SYS_LASTMODIFIED;
	private Integer SYS_DELETEFLAG;
	private String SYS_CURRENTSTATUS;
	private Long SYS_CURRENTUSERID;
	@Column(name="SYS_CURRENTUSERNAME")
	private String sYSCURRENTUSERNAME;
	@Column(name="P_Orderid")
	private Long pOrderiD;
	private Long p_Customer_iD;
	@Column(name="P_Customer")
	private String pCustomer;
	private Long P_Salesman_iD;
	@Column(name="P_Salesman")
	private String pSalesman;
	@Column(name="p_publication_id")
	private Integer ppublicationiD;
	private String 	p_publication;
	@Column(name="p_paymode_id")
	private Integer ppayModeiD;
	@Column(name="p_paymode")
	private String ppaymode;
	private String SYS_AUTHORS;//收款人
	private Double p_amount;//收款金额
	@Column(name="P_Amountleft")
	private Double 	amountleft;//余款
	@Column(name="p_amountout")
	private Double pamountout;//	转出金额
	@Column(name="p_amountback")
	private Double pamountback;//退款金额
	@Column(name="p_invoiceno")
	private String pinvoiceno;//发票号
	@Column(name="p_invoiceablemoney")
	private Double pinvoiceablemoney;//未开票金额
	@Column(name="p_invoicedmoney")
	private Double pinvoicedmoney;//已开票金额
	@Column(name="p_balanceablemoney")
	private Double pbalanceablemoney;//可平账金额
	@Column(name="p_balancedmoney")
	private Double pbalancedmoney;//平账金额
	@Column(name="p_balancedreal")
	private Double pbalancedreal;//实平账金额
	private Integer p_Type;//收款类型
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="p_Date")
	private Date pDate;//	收款日期
	private Long p_OperatoriD;//收款员
	@Column(name="p_hasleft")
	private Integer phasleft;//是否有余款
	@Column(name="p_hasreceipt")
	private Integer phasreceipt;//是否有发票
	public Long getP_Org_ID() {
		return P_Org_ID;
	}
	public void setP_Org_ID(Long p_Org_ID) {
		P_Org_ID = p_Org_ID;
	}
	public String getP_Org() {
		return P_Org;
	}
	public void setP_Org(String p_Org) {
		P_Org = p_Org;
	}
	@Column(name="p_hasout")
	private Integer phasout;//是否有转账
	@Column(name="p_hasback")
	private Integer phasback;//是否有退款
	@Column(name="p_locked")
	private Integer pLocked;//是否锁定
	private String p_Memo;//备注
	@Column(name="p_org_id")
	private Long P_Org_ID;//部门
	@Column(name="p_org")
	private String P_Org; // 部门
	
	public Integer getP_SrcID() {
		return P_SrcID;
	}
	public void setP_SrcID(Integer p_SrcID) {
		P_SrcID = p_SrcID;
	}
	public Long getSYS_DOCUMENTiD() {
		return SYS_DOCUMENTiD;
	}
	public void setSYS_DOCUMENTiD(Long sYS_DOCUMENTiD) {
		SYS_DOCUMENTiD = sYS_DOCUMENTiD;
	}
	public Date getSYS_CREaTED() {
		return SYS_CREaTED;
	}
	public void setSYS_CREaTED(Date sYS_CREaTED) {
		SYS_CREaTED = sYS_CREaTED;
	}
	public Date getSYS_LASTMODIFIED() {
		return SYS_LASTMODIFIED;
	}
	
	public Integer getSYS_DELETEFLAG() {
		return SYS_DELETEFLAG;
	}
	public void setSYS_DELETEFLAG(Integer sYS_DELETEFLAG) {
		SYS_DELETEFLAG = sYS_DELETEFLAG;
	}
	public void setSYS_LASTMODIFIED(Date sYS_LASTMODIFIED) {
		SYS_LASTMODIFIED = sYS_LASTMODIFIED;
	}
	public String getSYS_CURRENTSTATUS() {
		return SYS_CURRENTSTATUS;
	}
	public void setSYS_CURRENTSTATUS(String sYS_CURRENTSTATUS) {
		SYS_CURRENTSTATUS = sYS_CURRENTSTATUS;
	}
	public Long getSYS_CURRENTUSERID() {
		return SYS_CURRENTUSERID;
	}
	public void setSYS_CURRENTUSERID(Long sYS_CURRENTUSERID) {
		SYS_CURRENTUSERID = sYS_CURRENTUSERID;
	}
	public String getsYSCURRENTUSERNAME() {
		return sYSCURRENTUSERNAME;
	}
	public void setsYSCURRENTUSERNAME(String sYSCURRENTUSERNAME) {
		this.sYSCURRENTUSERNAME = sYSCURRENTUSERNAME;
	}
	public Long getpOrderiD() {
		return pOrderiD;
	}
	public void setpOrderiD(Long pOrderiD) {
		this.pOrderiD = pOrderiD;
	}
	public Long getP_Customer_iD() {
		return p_Customer_iD;
	}
	public void setP_Customer_iD(Long p_Customer_iD) {
		this.p_Customer_iD = p_Customer_iD;
	}
	public String getpCustomer() {
		return pCustomer;
	}
	public void setpCustomer(String pCustomer) {
		this.pCustomer = pCustomer;
	}
	public Long getP_Salesman_iD() {
		return P_Salesman_iD;
	}
	public void setP_Salesman_iD(Long p_Salesman_iD) {
		P_Salesman_iD = p_Salesman_iD;
	}
	public String getpSalesman() {
		return pSalesman;
	}
	public void setpSalesman(String pSalesman) {
		this.pSalesman = pSalesman;
	}
	public Integer getPpublicationiD() {
		return ppublicationiD;
	}
	public void setPpublicationiD(Integer ppublicationiD) {
		this.ppublicationiD = ppublicationiD;
	}
	public String getP_publication() {
		return p_publication;
	}
	public void setP_publication(String p_publication) {
		this.p_publication = p_publication;
	}
	public Integer getPpayModeiD() {
		return ppayModeiD;
	}
	public void setPpayModeiD(Integer ppayModeiD) {
		this.ppayModeiD = ppayModeiD;
	}
	public String getPpaymode() {
		return ppaymode;
	}
	public void setPpaymode(String ppaymode) {
		this.ppaymode = ppaymode;
	}
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public Double getP_amount() {
		return p_amount;
	}
	public void setP_amount(Double p_amount) {
		this.p_amount = p_amount;
	}
	public Double getAmountleft() {
		return amountleft;
	}
	public void setAmountleft(Double amountleft) {
		this.amountleft = amountleft;
	}
	public Double getPamountout() {
		return pamountout;
	}
	public void setPamountout(Double pamountout) {
		this.pamountout = pamountout;
	}
	public Double getPamountback() {
		return pamountback;
	}
	public void setPamountback(Double pamountback) {
		this.pamountback = pamountback;
	}
	public String getPinvoiceno() {
		return pinvoiceno;
	}
	public void setPinvoiceno(String pinvoiceno) {
		this.pinvoiceno = pinvoiceno;
	}
	public Double getPinvoiceablemoney() {
		return pinvoiceablemoney;
	}
	public void setPinvoiceablemoney(Double pinvoiceablemoney) {
		this.pinvoiceablemoney = pinvoiceablemoney;
	}
	public Double getPinvoicedmoney() {
		return pinvoicedmoney;
	}
	public void setPinvoicedmoney(Double pinvoicedmoney) {
		this.pinvoicedmoney = pinvoicedmoney;
	}
	public Double getPbalanceablemoney() {
		return pbalanceablemoney;
	}
	public void setPbalanceablemoney(Double pbalanceablemoney) {
		this.pbalanceablemoney = pbalanceablemoney;
	}
	public Double getPbalancedmoney() {
		return pbalancedmoney;
	}
	public void setPbalancedmoney(Double pbalancedmoney) {
		this.pbalancedmoney = pbalancedmoney;
	}
	public Double getPbalancedreal() {
		return pbalancedreal;
	}
	public void setPbalancedreal(Double pbalancedreal) {
		this.pbalancedreal = pbalancedreal;
	}
	public Integer getP_Type() {
		return p_Type;
	}
	public void setP_Type(Integer p_Type) {
		this.p_Type = p_Type;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public Long getP_OperatoriD() {
		return p_OperatoriD;
	}
	public void setP_OperatoriD(Long p_OperatoriD) {
		this.p_OperatoriD = p_OperatoriD;
	}
	public Integer getPhasleft() {
		return phasleft;
	}
	public void setPhasleft(Integer phasleft) {
		this.phasleft = phasleft;
	}
	public Integer getPhasreceipt() {
		return phasreceipt;
	}
	public void setPhasreceipt(Integer phasreceipt) {
		this.phasreceipt = phasreceipt;
	}
	public Integer getPhasout() {
		return phasout;
	}
	public void setPhasout(Integer phasout) {
		this.phasout = phasout;
	}
	public Integer getPhasback() {
		return phasback;
	}
	public void setPhasback(Integer phasback) {
		this.phasback = phasback;
	}
	public Integer getpLocked() {
		return pLocked;
	}
	public void setpLocked(Integer pLocked) {
		this.pLocked = pLocked;
	}
	public String getP_Memo() {
		return p_Memo;
	}
	public void setP_Memo(String p_Memo) {
		this.p_Memo = p_Memo;
	}
	
		
	
}
