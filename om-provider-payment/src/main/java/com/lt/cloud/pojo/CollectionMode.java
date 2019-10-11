package com.lt.cloud.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collectionmode")
public class CollectionMode {
	   
	@Id
    private  long SYS_DOCUMENTID;

    private  String E_Name;

	public long getSYS_DOCUMENTID() {
		return SYS_DOCUMENTID;
	}

	public void setSYS_DOCUMENTID(long sYS_DOCUMENTID) {
		SYS_DOCUMENTID = sYS_DOCUMENTID;
	}

	public String getE_Name() {
		return E_Name;
	}

	public void setE_Name(String e_Name) {
		E_Name = e_Name;
	}
    
    
}
