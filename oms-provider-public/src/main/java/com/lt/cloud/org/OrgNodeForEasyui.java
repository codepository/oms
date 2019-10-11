package com.lt.cloud.org;

import java.util.ArrayList;



public abstract class OrgNodeForEasyui implements Cloneable{
	private String label;
	private int id;
	private int NPARENTID;
	static int counter=0;//输出层的计算器
	ArrayList<OrgNodeForEasyui> children=new ArrayList<OrgNodeForEasyui>();
	private int mode=0;//0默认为子节点,1为组合节点
	public void setMode(int mode) {//如果当前节点包含子节点，则设置为1
		this.mode=mode;
	}
	public ArrayList<OrgNodeForEasyui> getNodes(){
		return this.children;
	}
	
	@Override
	public Object clone() {
		OrgNodeForEasyui abstractOrgNode=null;
		try {
			abstractOrgNode=(OrgNodeForEasyui) super.clone();
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
		System.out.println("+ "+this.getSTRNODENAME()+":");
		
		
		
		//调用所有子元素
		if(mode==1) {
			for (OrgNodeForEasyui node : children) {
				
				node.operation();
			}
		}
		
		//输出层数重置
		--counter;
	}
	//当前节点或者它的子节点是否包含目标节点
	public boolean insertIfIsParent(OrgNodeForEasyui target,OrgNodeForEasyui destination) {
		
		if(destination.getNNODEID()==target.getNPARENTID()) {
			destination.setMode(1);
			destination.insert(target,destination);
			
			return true;
		}
		if(destination.mode==0) return false;//当前节点不是目标节点的父节点，并且无子节点
		for (OrgNodeForEasyui abstractOrgNode : destination.getNodes()) {//判断它的子节点是否为目标节点的父节点
			abstractOrgNode.insertIfIsParent(target,abstractOrgNode);
		}
		return false;
		
	}
	public void insert(OrgNodeForEasyui target,OrgNodeForEasyui destination){
	    if (destination.getNodes().contains(target)) return;
		destination.getNodes().add(target);
	}
	public void delete(OrgNodeForEasyui node){
		System.out.println("子方法没有执行");
	}

	public String getSTRNODENAME() {
		return this.label;
	}

	public void setSTRNODENAME(String sTRNODENAME) {
		this.label = sTRNODENAME;
	}

	
	
	public int getNNODEID() {
		return id;
	}
	public void setNNODEID(int nNODEID) {
		this.id = nNODEID;
	}
	public int getNPARENTID() {
		return NPARENTID;
	}
	public void setNPARENTID(int nPARENTID) {
		NPARENTID = nPARENTID;
	}
	
}
