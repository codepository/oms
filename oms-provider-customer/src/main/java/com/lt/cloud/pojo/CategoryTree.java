package com.lt.cloud.pojo;
//这是一个视图
public class CategoryTree {
	private String categoryID;
	private String description;
	private String  parentID;
	private String sortNum;
	private String valid;
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getSortNum() {
		return sortNum;
	}
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
	
}
