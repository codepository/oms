package com.lt.cloud.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoicetype")
public class Invoicetype {
	   
	@Id
    private  long id;
   
	
    private  String text;
   
    
    public  long  getId(){
    		return  this.id;
    };
    public  void  setId(long id){
    	this.id=id;
    }  
   
    public  String  getText(){
    		return  this.text;
    };
    public  void  setText(String text){
    	this.text=text;
    }  
   
  
}
