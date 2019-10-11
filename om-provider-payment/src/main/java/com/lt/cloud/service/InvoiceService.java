package com.lt.cloud.service;
import org.springframework.data.domain.Page;
import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.InvoiceReceiver;
import com.lt.cloud.pojo.Payment;
public interface InvoiceService {
	Page<Invoice> findAll(InvoiceReceiver invoiceReceiver);
	Invoice save(Invoice invoice);
	Invoice test(Invoice invoice);
	Invoice findById(Long id);
	void deleteById(Long id);
	void update(String json);
	boolean saveWithPayment(String pojos);
	boolean deleteInvoice(String pojos);
	boolean checkInvoiceno(String invoiceno);
	boolean invoiceBack(String json);
	void updateInvoiceWithMoneyback(Payment newpayment);
	String getLastInvoiceno();
}
