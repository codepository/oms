package com.lt.cloud.mapper;

import java.util.List;

import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Pricelistitem;

public interface PricelistitemMapper {
	List<Pricelistitem> selectAll(AdvotherReceiver receiver);
}
