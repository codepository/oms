package com.lt.cloud.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class PaymentReceive {
	private Long SYS_DOCUMENTiD;
	private String pCustomer;//客户
	private String customerLike;
	private String SYS_AUTHORS;//操作员
	private String SYSCURRENTUSERNAME;
	private String SYS_CURRENTSTATUS;
	private String pSalesman;//业务员
	private Long pOrderID;//订单号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  pDateStart;//	收款日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pDateEnd;//	收款日期
	private String pinvoiceno;//发票号
	private String p_publication;//刊物
	private Boolean isBalance;
	private Boolean isInvoice;
	private Integer pageIndex;
	private Integer pageSize;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private Long P_Org_ID;
	private String P_Org;
	private Boolean hasleft;//有无余款
	private Integer P_SrcID;//源收款号
	private Long advitemID;
	
	public Long getAdvitemID() {
		return advitemID;
	}

	public void setAdvitemID(Long advitemID) {
		this.advitemID = advitemID;
	}

	public String getCustomerLike() {
		return customerLike;
	}

	public void setCustomerLike(String customerLike) {
		this.customerLike = customerLike;
	}

	public Long getSYS_DOCUMENTiD() {
		
		return SYS_DOCUMENTiD;
	}

	public String getP_Org() {
		return this.P_Org;
	}

	public void setP_Org(String p_Org) {
		this.P_Org = p_Org;
	}

	public void setSYS_DOCUMENTiD(Long sYS_DOCUMENTiD) {
		SYS_DOCUMENTiD = sYS_DOCUMENTiD;
	}
	public String getpCustomer() {
		return pCustomer;
	}
	public void setpCustomer(String pCustomer) {
		this.pCustomer = pCustomer;
	}
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public String getSYSCURRENTUSERNAME() {
		return SYSCURRENTUSERNAME;
	}
	public void setSYSCURRENTUSERNAME(String sYSCURRENTUSERNAME) {
		SYSCURRENTUSERNAME = sYSCURRENTUSERNAME;
	}
	public String getSYS_CURRENTSTATUS() {
		return SYS_CURRENTSTATUS;
	}
	public void setSYS_CURRENTSTATUS(String sYS_CURRENTSTATUS) {
		SYS_CURRENTSTATUS = sYS_CURRENTSTATUS;
	}
	public String getpSalesman() {
		return pSalesman;
	}
	public void setpSalesman(String pSalesman) {
		this.pSalesman = pSalesman;
	}
	public Long getpOrderID() {
		return pOrderID;
	}
	public void setpOrderID(Long pOrderID) {
		this.pOrderID = pOrderID;
	}
	public Date getpDateStart() {
		return pDateStart;
	}
	public void setpDateStart(Date pDateStart) {
		this.pDateStart = pDateStart;
	}
	public Date getpDateEnd() {
		return pDateEnd;
	}
	public void setpDateEnd(Date pDateEnd) {
		this.pDateEnd = pDateEnd;
	}
	public String getPinvoiceno() {
		return pinvoiceno;
	}
	public void setPinvoiceno(String pinvoiceno) {
		this.pinvoiceno = pinvoiceno;
	}
	public String getP_publication() {
		return p_publication;
	}
	public void setP_publication(String p_publication) {
		this.p_publication = p_publication;
	}
	public Boolean getIsBalance() {
		return isBalance;
	}
	public void setIsBalance(Boolean isBalance) {
		this.isBalance = isBalance;
	}
	public Boolean getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Boolean isInvoice) {
		this.isInvoice = isInvoice;
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
	public Long getP_Org_ID() {
		return P_Org_ID;
	}
	public void setP_Org_ID(Long p_Org_ID) {
		P_Org_ID = p_Org_ID;
	}
	public Boolean getHasleft() {
		return hasleft;
	}
	public void setHasleft(Boolean hasleft) {
		this.hasleft = hasleft;
	}
	public Integer getP_SrcID() {
		return P_SrcID;
	}
	public void setP_SrcID(Integer p_SrcID) {
		P_SrcID = p_SrcID;
	}
	
	
	
}
