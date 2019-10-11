package com.lt.cloud.pojo;

import java.util.ArrayList;

public  class AdvTrade implements Cloneable{
	private int value;
	private String label;
	private int CT_nParentID;
	static int counter=0;//输出层的计算器
	public ArrayList<AdvTrade> children=new ArrayList<AdvTrade>();
	private int mode=0;//0默认为子节点,1为组合节点
	public void setMode(int mode) {//如果当前节点包含子节点，则设置为1
		this.mode=mode;
	}
	public ArrayList<AdvTrade> getNodes(){
		return this.children;
	}
	
	@Override
	public Object clone() {
		AdvTrade abstractOrgNode=null;
		try {
			abstractOrgNode=(AdvTrade) super.clone();
			abstractOrgNode.children=new ArrayList<>();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return abstractOrgNode;
	}
	public  void operation() {
		
		
		
		String formatString;
		//计算新的输出层数
		
		counter++;
		
		//计算字符串的格式（根据所达到的层数计算输出空格数）
		formatString="%"+(counter*2)+"s";
		//输出空格
		System.out.printf(formatString,"");
		//输出加号，后面跟着组合的名称
		System.out.println("+ "+this.getCT_strDescription()+":");
		
		
		
		//调用所有子元素
		if(mode==1) {
			for (AdvTrade node : children) {
				
				node.operation();
			}
		}
		
		//输出层数重置
		--counter;
	}
	//当前节点或者它的子节点是否包含目标节点
	public boolean insertIfIsParent(AdvTrade target,AdvTrade destination) {
		
		if(destination.getCT_nCategoryID()==target.getCT_nParentID()) {
			destination.setMode(1);
			destination.insert(target,destination);
			
			return true;
		}
		if(destination.mode==0) return false;//当前节点不是目标节点的父节点，并且无子节点
		for (AdvTrade abstractOrgNode : destination.getNodes()) {//判断它的子节点是否为目标节点的父节点
			abstractOrgNode.insertIfIsParent(target,abstractOrgNode);
		}
		return false;
		
	}
	public void insert(AdvTrade target,AdvTrade destination){
	    if (destination.getNodes().contains(target)) return;
		destination.getNodes().add(target);
	}
	public void delete(AdvTrade node){
		System.out.println("子方法没有执行");
	}

	public int getCT_nParentID() {
		return CT_nParentID;
	}
	public void setCT_nParentID(int cT_nParentID) {
		CT_nParentID = cT_nParentID;
	}
	public int getCT_nCategoryID() {
		return value;
	}
	public void setCT_nCategoryID(int cT_nCategoryID) {
		value = cT_nCategoryID;
	}
	public String getCT_strDescription() {
		return label;
	}
	public void setCT_strDescription(String cT_strDescription) {
		label = cT_strDescription;
	}

}
