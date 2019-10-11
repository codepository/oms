package com.lt.cloud.service;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Edition;
import com.lt.cloud.pojo.OtherAdv;
import com.lt.cloud.pojo.Publication;

public interface AdvOtherQueryService {
	List<OtherAdv> selectAll(AdvotherReceiver receiver);
	List<Publication> selectAllPublication(HashMap<String, Object> params);
	List<Edition> selectAllEdition(HashMap<String, Object> params);
}
