package com.lt.cloud.mapper;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.AdvOrg;
import com.lt.cloud.pojo.AdvTrade;
import com.lt.cloud.pojo.Trade;

public interface AdvOrgMapper {
	List<AdvOrg> selectAllAdvOrg(HashMap<String, Object> params);
	List<AdvTrade> selectAllTrade(HashMap<String, Object> params);
	
	List<Trade> findAllTrade(HashMap<String, Object> params);
	List<Trade> findAllTradeByUser(String username);
	void deleteById(int id);
	int save(Trade trade);
	// 当前行业是否存在子节点
	boolean existsChildById(int id);
	// 当前行业是否已经存在
	boolean existsByText(String text);
}
