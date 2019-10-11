package com.lt.cloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.AdvOtherMapper;
import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Edition;
import com.lt.cloud.pojo.OtherAdv;
import com.lt.cloud.pojo.Publication;
import com.lt.cloud.service.AdvOtherQueryService;
@Component
public class AdvOtherQueryServiceImpl implements AdvOtherQueryService{
	@Autowired
	private AdvOtherMapper advOtherMapper;
	@Override
	public List<Publication> selectAllPublication(HashMap<String, Object> params) {
		
		return this.advOtherMapper.selectAllPublication(params);
	}
	@Override
	public List<Edition> selectAllEdition(HashMap<String, Object> params) {
		
		return this.advOtherMapper.selectAllEdition(params);
	}
	@Override
	public List<OtherAdv> selectAll(AdvotherReceiver receiver) {
		
		return this.advOtherMapper.selectAll(receiver);
	}
	
}
