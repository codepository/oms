package com.lt.cloud.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.AdvitemReceiver;

public interface AdvitemQueryService {
	String findAll(AdvitemReceiver receiver);
	List<Advitem> findByAI_OrderID(Long AI_OrderID);
	Advitem selectById(long id);
	String findStatistics(String json);
	/**
	 *  根据advid数组来查询
	 * @param json
	 * @return
	 */
	String findByAdvids(String json);
	List<Advitem> findByAdvids(List<Long> content);
	ResponseEntity<byte[]> exportResponseEntity(String json);
	String findAllReports(String json);
	ResponseEntity<byte[]> exportAllReports(String json);
}
