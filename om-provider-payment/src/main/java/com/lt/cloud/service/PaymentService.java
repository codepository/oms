package com.lt.cloud.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.Payment;
import com.lt.cloud.pojo.PaymentReceive;

public interface PaymentService {
	Page<Payment> findAll(PaymentReceive paymentReceive,Pageable pageable);
	Page<Payment> findAll(String json);
	String findAllAsJson(String json);
	Payment save(Payment payment);
	Payment saveNormalPayment(Payment payment);
	Payment findById(Long id);
	Boolean update(String json);
	Boolean updateInvoice(String json);
	Boolean updateInvoice(Payment payment);
	void deleteById(Long id);
	void save(String json);
	void saveNewpayment(Payment payment);
	Payment updatePayment(Payment payment);
	Payment updatePaymentWithMoneyback(Payment old,Payment payment);
	Payment updatePaymentWithBalanceOrders(Payment old,Payment payment);
	Payment updatePaymentWithBalanceReal(Payment old,Payment payment);
	Payment updatePaymentWithPrebalanceOrders(Payment old,Payment payment);
	Payment updatePaymentWithInvoice(Payment old,Payment payment);
	Payment updatePaymentWithLendInvoice(Payment old,Payment payment);
	boolean moneyback(String pojos);
	void deleteByInvoiceno(String pinvoiceno);
	void updateWithBalance(Payment payment);
	void updateOnly(Payment source);
	boolean deleteInvoice(Invoice invoice);
	boolean updatePaymentWithInvoiceDelete(Payment payment, Invoice invoice);
	boolean isBalancedByInvoice(String invoiceno);
	List<Payment> findPaymentUnbalancedWithInvoiceno(String invoiceno);
	boolean transferMoney(String pojos);
	String findByAdvitemID(Long advitemID);
}
