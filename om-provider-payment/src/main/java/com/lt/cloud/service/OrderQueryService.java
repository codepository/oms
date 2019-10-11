package com.lt.cloud.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.Advorder;
import com.lt.cloud.pojo.AdvorderReceiver;
import com.lt.cloud.pojo.Balance;

public interface OrderQueryService {
	Page<Advorder> findAll(AdvorderReceiver receiver);
	Advorder findById(Long id);
	
	Advorder save(Advorder advOrder);
	String update(Advorder advOrder);
	
	boolean updateAllWithAdvitem(String advitemList);
	boolean updateWithAdvitemDelete(Long id);
	boolean deleteById(Long id);
	void updateAllWithAdvitemlist(List<Advitem> list);
	void updateAllWithAdvitemAdd(String advitemList);
	void updateAllWithAdvitemAdd(List<Advitem> list);
	Advorder updateOrderWithBalance(Advorder old,Advorder neworder);
	Advorder updateOrderWithBalanceReal(Advorder old,Advorder neworder);
	boolean updateOrderWithBalances(List<Balance> balances);
	boolean updateOrderAndAdvitem(String pojos);
	void updateAllWithAdvitemsWithAmountChanged(Advitem difference);
	

}
