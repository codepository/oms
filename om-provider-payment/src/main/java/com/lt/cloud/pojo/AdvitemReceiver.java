package com.lt.cloud.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdvitemReceiver {
	private Long SYS_DOCUMENTID;
	private String AI_Customer;
	private String AI_CustomerLike;
	private String AI_Salesman;
	private Long AI_Customer_ID;
	private Long AI_OrderID;
	private Boolean isBalance;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date AI_PublishTimeStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date AI_PublishTimeEnd;
	private String SYS_AUTHORS;
	private String AI_Trade;
	private String AI_TradeID;
	private Integer pageIndex;
	private Integer pageSize;
	private String limit;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private String AI_Content;
	private String AI_Publication;
	private String AI_PayMode;
	private String AI_InvoiceNo;
	private boolean hasReceivable;//true,结果包含总应收款
	private boolean hasReceived; //true,结果包含总已收款
	private boolean hasDebt;//true,结果包含总欠款
	private boolean hasPages;//true,结果包含总版面数
	private String orderby;
	private String filename;
	private String columns;
	private String colnames;
	private String AI_Memo;
	private String whereby;
	private String groupby;
	
	
	public String getAI_CustomerLike() {
		return AI_CustomerLike;
	}
	public void setAI_CustomerLike(String aI_CustomerLike) {
		AI_CustomerLike = aI_CustomerLike;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getWhereby() {
		return whereby;
	}
	public void setWhereby(String whereby) {
		this.whereby = whereby;
	}
	public String getGroupby() {
		return groupby;
	}
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	public String getAI_TradeID() {
		return AI_TradeID;
	}
	public void setAI_TradeID(String aI_TradeID) {
		AI_TradeID = aI_TradeID;
	}
	public String getAI_Memo() {
		return AI_Memo;
	}
	public void setAI_Memo(String aI_Memo) {
		AI_Memo = aI_Memo;
	}
	public String getColnames() {
		return colnames;
	}
	public void setColnames(String colnames) {
		this.colnames = colnames;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isHasReceivable() {
		return hasReceivable;
	}
	public void setHasReceivable(boolean hasReceivable) {
		this.hasReceivable = hasReceivable;
	}
	public boolean isHasReceived() {
		return hasReceived;
	}
	public void setHasReceived(boolean hasReceived) {
		this.hasReceived = hasReceived;
	}
	public boolean isHasDebt() {
		return hasDebt;
	}
	public void setHasDebt(boolean hasDebt) {
		this.hasDebt = hasDebt;
	}
	public boolean isHasPages() {
		return hasPages;
	}
	public void setHasPages(boolean hasPages) {
		this.hasPages = hasPages;
	}
	public String getAI_Content() {
		return AI_Content;
	}
	public void setAI_Content(String aI_Content) {
		AI_Content = aI_Content;
	}
	public String getAI_Publication() {
		return AI_Publication;
	}
	public void setAI_Publication(String aI_Publication) {
		AI_Publication = aI_Publication;
	}
	public String getAI_PayMode() {
		return AI_PayMode;
	}
	public void setAI_PayMode(String aI_PayMode) {
		AI_PayMode = aI_PayMode;
	}
	public String getAI_InvoiceNo() {
		return AI_InvoiceNo;
	}
	public void setAI_InvoiceNo(String aI_InvoiceNo) {
		AI_InvoiceNo = aI_InvoiceNo;
	}
	public String getAI_Trade() {
		return AI_Trade;
	}
	public void setAI_Trade(String aI_Trade) {
		AI_Trade = aI_Trade;
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
	public String getSYS_AUTHORS() {
		return SYS_AUTHORS;
	}
	public void setSYS_AUTHORS(String sYS_AUTHORS) {
		SYS_AUTHORS = sYS_AUTHORS;
	}
	public String getAI_Salesman() {
		return AI_Salesman;
	}
	public void setAI_Salesman(String aI_Salesman) {
		AI_Salesman = aI_Salesman;
	}
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}
	public Long getAI_Customer_ID() {
		return AI_Customer_ID;
	}
	public void setAI_Customer_ID(Long aI_Customer_ID) {
		AI_Customer_ID = aI_Customer_ID;
	}
	public Boolean getIsBalance() {
		return isBalance;
	}
	public void setIsBalance(Boolean isBalance) {
		this.isBalance = isBalance;
	}
	public Long getAI_OrderID() {
		return AI_OrderID;
	}
	public void setAI_OrderID(Long aI_OrderID) {
		AI_OrderID = aI_OrderID;
	}
	public String getAI_Customer() {
		return AI_Customer;
	}
	public void setAI_Customer(String aI_Customer) {
		AI_Customer = aI_Customer;
	}
	public Date getAI_PublishTimeStart() {
		return AI_PublishTimeStart;
	}
	public void setAI_PublishTimeStart(Date aI_PublishTimeStart) {
		AI_PublishTimeStart = aI_PublishTimeStart;
	}
	public Date getAI_PublishTimeEnd() {
		return AI_PublishTimeEnd;
	}
	public void setAI_PublishTimeEnd(Date aI_PublishTimeEnd) {
		AI_PublishTimeEnd = aI_PublishTimeEnd;
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
