package com.lt.cloud.mapper;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.AdvitemReceiver;

public interface AdvitemMapper {
	List<Advitem> findAll(AdvitemReceiver receiver);
	List<HashMap<String, Object>> findAllAsHashMap(AdvitemReceiver receiver);
	List<HashMap<String, Object>> findAllReportsAsHashMap(AdvitemReceiver receiver);
	List<Advitem> findAllReports(AdvitemReceiver receiver);
}
