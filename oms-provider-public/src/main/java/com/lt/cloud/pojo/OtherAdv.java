package com.lt.cloud.pojo;

public class OtherAdv {
	private int id;
	private String text;
	private String E_Code;
	public double E_Width;
	public double E_Height;
	public String E_Grid;
	public String E_AdType;
	public String E_Name;
	public String E_PID;
	public String table;
	public Double E_Area;

	
	public Double getE_Area() {
		return E_Area;
	}
	public void setE_Area(Double e_Area) {
		E_Area = e_Area;
	}
	public String getE_PID() {
		return E_PID;
	}
	public void setE_PID(String e_PID) {
		E_PID = e_PID;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
	public String getE_AdType() {
		return E_AdType;
	}
	public void setE_AdType(String e_AdType) {
		E_AdType = e_AdType;
	}
	public String getE_Grid() {
		return E_Grid;
	}
	public void setE_Grid(String e_Grid) {
		E_Grid = e_Grid;
	}
	public String getE_Code() {
		return E_Code;
	}
	public void setE_Code(String e_Code) {
		E_Code = e_Code;
	}
	public int getSYS_DOCUMENTID() {
		return id;
	}
	public void setSYS_DOCUMENTID(int sYS_DOCUMENTID) {
		id = sYS_DOCUMENTID;
	}
	public String getE_Name() {
		return text;
	}
	public void setE_Name(String e_Name) {
		text = e_Name;
		E_Name=e_Name;
	}
	public double getE_Width() {
		return E_Width;
	}
	public void setE_Width(double e_Width) {
		E_Width = e_Width;
	}
	public double getE_Height() {
		return E_Height;
	}
	public void setE_Height(double e_Height) {
		E_Height = e_Height;
	}
}
