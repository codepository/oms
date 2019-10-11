package com.lt.cloud.service;

import java.util.List;

import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Pricelistitem;

public interface PrilistItemService {
	List<Pricelistitem> selectAll(AdvotherReceiver receiver);
}
