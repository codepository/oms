package com.lt.cloud.pojo;

public class Pricelistitem {
	private int SYS_DOCUMENTID;
	private double E_Price;//价格
	private int E_PID;//pricelist价格表ID
	private int E_MID;//投放日ID
	private int E_AdField_ID;//版位ID
	private int E_Color_ID;//颜色ID
	private int E_AdSize_ID;//规格ID
	
	private int E_IsUnitPrice;//是否单位价格
	private int week;
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(int sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}
	public int getE_PID() {
		return E_PID;
	}
	public void setE_PID(int e_PID) {
		E_PID = e_PID;
	}
	public int getE_MID() {
		return E_MID;
	}
	public void setE_MID(int e_MID) {
		E_MID = e_MID;
	}
	public int getE_AdField_ID() {
		return E_AdField_ID;
	}
	public void setE_AdField_ID(int e_AdField_ID) {
		E_AdField_ID = e_AdField_ID;
	}
	public int getE_Color_ID() {
		return E_Color_ID;
	}
	public void setE_Color_ID(int e_Color_ID) {
		E_Color_ID = e_Color_ID;
	}
	public int getE_AdSize_ID() {
		return E_AdSize_ID;
	}
	public void setE_AdSize_ID(int e_AdSize_ID) {
		E_AdSize_ID = e_AdSize_ID;
	}
	public double getE_Price() {
		return E_Price;
	}
	public void setE_Price(double e_Price) {
		E_Price = e_Price;
	}
	public int getE_IsUnitPrice() {
		return E_IsUnitPrice;
	}
	public void setE_IsUnitPrice(int e_IsUnitPrice) {
		E_IsUnitPrice = e_IsUnitPrice;
	}
	
	
	
}
