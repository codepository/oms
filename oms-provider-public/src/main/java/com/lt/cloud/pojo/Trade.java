package com.lt.cloud.pojo;

import java.util.ArrayList;
import java.util.List;

public class Trade {
	private int id;
	private String text;
	private String label;
	private int parentid;
	private String type;
	private int depth;
	private List<Trade> children=new ArrayList<Trade>();
//	public Trade(int id) {
//		this.id = id;
//	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Trade> getChildren() {
		return children;
	}
	public void setChildren(List<Trade> children) {
		this.children = children;
	}
	public void add(Trade trade) {
		this.children.add(trade);
	}
	
}
