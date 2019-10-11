package com.lt.cloud.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lt.cloud.pojo.Balance;


import java.lang.Long;
import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long>{
	Page<Balance> findAll(Specification<Balance> specification,Pageable pageable);
	@Query(value="select * from balance b where b.B_PayID=?1",nativeQuery=true)
	List<Balance> findByPayId(Long B_PayID);
	@Query(value="select distinct b.B_AdItemID from balance b where b.B_PayID=?1",nativeQuery=true)
	List<Object> findAdvidByPayid(Long B_PayID);
	@Query(value="select sum(a.AI_AmountReceivable),sum(a.AI_AmountReceived),sum(a.AI_Debt),sum(a.AI_BalancedMoney),sum(a.AI_UnbalancedMoney),"
			+ "sum(a.AI_InvoicedMoney),sum(a.AI_UninvoicedMoney) from advitem a "
			+ "where a.SYS_DOCUMENTID in (select b.B_AdItemID from balance b where b.B_PayID=?1) "
			+ "group by a.AI_AmountReceivable and a.AI_AmountReceived and a.AI_Debt and a.AI_BalancedMoney "
			+ "and a.AI_UnbalancedMoney and a.AI_InvoicedMoney and a.AI_UninvoicedMoney",nativeQuery=true)
	List<Double> findAdvitemStaticsByPayid(Long B_PayID);
	/**
	 * 根据收款id统计，该收款已经平账的总金额、实平账总金额
	 * @param B_PayID
	 * @param pageable
	 * @return
	 */
	@Query(value="select sum(b.B_Amount),sum(b.B_AmountReal) from balance b where b.B_PayID=?1 group by b.B_Amount and b.B_AmountReal",nativeQuery=true)
	List<Double> findStatistics(Long B_PayID);
	@Query(value="select distinct b.B_AdItemID from balance b where b.B_PayID=?1",
			countQuery="select count(distinct b.B_AdItemID) from balance b where b.B_PayID=?1",
			nativeQuery=true)
	Page<Long> findAdvidByPayidAndPagable(Long B_PayID,Pageable pageable);
	
	@Query(value="select new Balance(b.B_OrderID,b.B_AdItemID,sum(b.B_Amount)) from Balance b where b.B_PayID=?1 group by b.B_AdItemID,b.B_OrderID having sum(b.B_Amount)>0")
	List<Balance> findByPayIdWithAdv(Long B_PayID);
	@Query(value="select * from balance b where b.B_PayID=?1 and b.B_Amount-b.B_AmountReal>0",
			nativeQuery=true)
	List<Balance> findByPayidAndNeedBalanceReal(Long id);
	@Query(value="select * from balance b where b.B_PayID=?1 and B_AmountUninvoiced!=0",nativeQuery=true)
	List<Balance> findByPayidAndNeedInvoice(Long paymentid);
	
//	@Query(value="select new Balance(b.B_OrderID,b.B_AdItemID,sum(b.B_Amount)) from Balance b "
//			+ "where b.B_PayID=#{#receiver.B_PayID}"
//			+ "group by b.B_AdItemID,b.B_OrderID having sum(b.B_Amount)>0")
//	List<Balance> findAllReversable(BalanceReceiver receiver);
	
}
