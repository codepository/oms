package com.lt.cloud.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lt.cloud.pojo.Advitem;
@Transactional
public interface AdvitemUpdateSevice {
	Advitem insertAdvitem(Advitem advitem);
	Boolean updateAdvitem(Advitem advitem);
	Boolean saveAll(List<Advitem> advitems);
	void deleteById(Long id);
	// 直接更新，不做任务处理
	void updateAllDirect(List<Advitem> advitems);
	void updateAll(List<Advitem> advitems);
	Advitem updateAmountReceived(Advitem old,Advitem newadv);
	Advitem updateInvoice(Advitem old,Advitem newadv);
	Advitem updateAdvitemWithBalance(Advitem old,Advitem newadv);
	Advitem updateAdvitemWithMoneyback(Advitem old,Advitem newadv);
	Advitem updateAdvitemWithBalanceReal(Advitem old,Advitem newadv);
	Advitem updateAdvitemWithPreBalance(Advitem old,Advitem newadv);
	void deleteByOrderId(Long id);
	Advitem findById(Long id);
	
	/**
	 * ******************** 保存新的广告 ******************
	 */
	Boolean saveNewAdvitems(String advitems);
	boolean updateAdvAndOrder(String advitemList);
	String updateAdvContentWithMoneyNotChange(List<Advitem> advitems);
	
}
