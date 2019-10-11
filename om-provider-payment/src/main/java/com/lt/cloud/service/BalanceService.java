package com.lt.cloud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.Balance;
import com.lt.cloud.pojo.BalanceReceiver;
import com.lt.cloud.pojo.Payment;

public interface BalanceService {
	Page<Balance> findAll(BalanceReceiver balanceReceiver);
	Page<Balance> findAll(String json);
	/**
	 * 找出所有可以反平帐的订单
	 * @param balanceReceiver
	 * @return
	 */
	List<Balance> findAllReversable(Long payid);
	List<Balance> findAllReversable(BalanceReceiver receiver);
	Boolean saveAll(String json);
	/**
	 * 根据收款号在平帐表中查询不重复的订单，并且订单的平帐总金额必须大于0,查询结果有两个字段：广告ID和平帐金额
	 * @param B_PayID
	 * @return
	 */
	List<Balance> findByPayIdResultAdv(Long B_PayID);
	List<Object> findAdvidByPayid(Long B_PayID);
	String findAdvidByPayidAsJson(Long B_PayID);
	void saveAllBalance(String json);
	void saveAllBalance(List<Balance> balances);
	Balance updateWithBalance(Balance newbalance);
	Balance updateWithBalanceReal(Balance newbalance);
	Balance updateWithInvoice(Balance newbalance);
	String findAdvidByPayidAndPagable(String json);
	Page<Long> findAdvidByPayidAsPage(String json);
	String findAdvByPayidAndPagable(String json);
	void autoBalanceReal(Payment source);
	List<Balance> findByPayidAndNeedBalanceReal(Long sys_DOCUMENTiD);
	List<Balance> sortedAsc(List<Balance> balances);
	List<Balance> getAllBalanceCanBalanceReal(List<Balance> source, double amountleft);
	List<Balance> getBalancesForBalanceReal(List<Balance> balances, double amountleft);
	List<Advitem> getAdvForBalanceReal(List<Balance> balances4balancereal);
	List<Balance> getBalances4BalanceRealWithAdvidDistinct(List<Balance> list);
	double getTotalAmountRealAboutAdvitems(List<Advitem> advitems);
	List<Balance> getBalancesWithBalanceReal4BalanceUpdate(List<Balance> balances, double amountleft);
	boolean balance(String json);
	String findStatistics(Long paymentid);
	String findAdvitemStaticsByPayid(Long paymentid);
	void autoInvoice(Payment payment);
	List<Balance> findByPayidAndNeedInvoice(Long sys_DOCUMENTiD);

	List<Balance> sortedUninvoiceAsc(List<Balance> balances);
	List<Balance> getAllBalanceWhichCanInvoice(List<Balance> sortedUninvoiceAsc, Double invoiceablemoney);
	List<Balance> getBalancesWithInvoice4BalanceUpdate(List<Balance> balancesWhichCanInvoiceClone, Payment source);
//	List<Balance> getBalancesInvoiceNow(List<Balance> balancesWhichCanInvoice, Double invoiceablemoney);
	List<Balance> getBalnacesInvoiceNowWithAdvidDistinct(List<Balance> balancesInvoiceNow);
	List<Advitem> getAdvForInvoice(List<Balance> balancesInvoiceNowWithAdvidDistinct);
	Double getTotalInvoicedMoney(List<Advitem> advitems);
}
