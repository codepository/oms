package com.lt.cloud.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.lt.cloud.pojo.Payment;
import com.lt.cloud.pojo.PaymentReceive;

public interface PaymnetRepository extends JpaRepository<Payment,Long>{
	Page<Payment> findAll(Specification<PaymentReceive> spec,Pageable pageable);
	Payment findByPinvoicenoContaining(String pinvoiceno);
	void deleteByPinvoiceno(String invoiceno);
	List<Payment> findByPinvoiceno(String invoiceno);
	@Modifying
//	@Transactional
	@Query("delete from Payment p  where p.P_SrcID=?1 and p.SYS_CURRENTSTATUS=?2")
	void deleteWithPsrcidAndSYS_CURRENTSTATUS(Integer id,String SYS_CURRENTSTATUS);
	@Query(value="select if(sum(P_BalancedMoney)>0,1,0) from payment where P_InvoiceNo like %?1% and SYS_DELETEFLAG=0",nativeQuery=true)
	public Integer findIsBalancedWithInvoiceno(String pinvoiceno);
	/**
	 * 根据发票号，寻找可以删除发票的收款
	 * @param pnvoiceno，(P_InvoiceableMoney+P_InvoicedMoney)>0，P_BalancedMoney=0
	 * @return
	 */
	@Query(value="select * from payment where P_InvoiceNo like %?1% and SYS_DELETEFLAG=0 "
			+ "and (P_InvoiceableMoney+P_InvoicedMoney)>0 and P_BalancedMoney=0",nativeQuery=true)
	List<Payment> findPaymentUnbalancedWithInvoiceno(String pnvoiceno);
	@Query(value="select * from payment where SYS_DOCUMENTID in (select B_PayID from balance where B_AdItemID=?1 and SYS_DELETEFLAG=0)",nativeQuery=true)
	List<Payment> findByAdvitemID(Long advitemID);

}
