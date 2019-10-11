package com.lt.cloud.pojo;

import java.util.ArrayList;
import java.util.List;

public class PowerNode {
	private int value;
	private String label;
	private int depth;
	private int parentid;
	List<PowerNode> children=new ArrayList<>();
	
	public List<PowerNode> getChildren() {
		return children;
	}
	public void setChildren(List<PowerNode> children) {
		this.children = children;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
}
