package com.lt.cloud.service.impl;

import java.util.HashMap;
import java.util.List;

import com.lt.cloud.pojo.AdvitemReceiver;
public interface FileService<T> {
	List<T> findAllAndNotPage(String json);
	List<HashMap<String, Object>> findAllAsHashMap(AdvitemReceiver receiver);
}
