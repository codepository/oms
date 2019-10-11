package com.lt.cloud.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salesman {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SYS_DOCUMENTID;
	private String name;
	private String code;
	private String phone;
	public Long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}
	public void setSYS_DOCUMENTID(Long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
