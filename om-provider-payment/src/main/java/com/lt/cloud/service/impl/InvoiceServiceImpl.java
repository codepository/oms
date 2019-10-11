package com.lt.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.lt.cloud.dao.jpa.InvoiceRepository;
import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.InvoiceReceiver;
import com.lt.cloud.pojo.Payment;
import com.lt.cloud.pojo.PojosWrapper;
import com.lt.cloud.service.BalanceService;
import com.lt.cloud.service.InvoiceService;
import com.lt.cloud.service.PaymentService;
import com.lt.cloud.utils.DateUtil;
import com.lt.cloud.utils.JsonUtils;
@Component
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private BalanceService balanceService;
	@Override
	public Page<Invoice> findAll(InvoiceReceiver invoiceReceiver) {
		Specification<Invoice> specification=new Specification<Invoice>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates=new ArrayList<>();
				if(invoiceReceiver.getType()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("type"), invoiceReceiver.getType()));
				}
				if (!StringUtils.isEmpty(invoiceReceiver.getCustomer())) {
					predicates.add(criteriaBuilder.like(root.get("customer"), "%"+invoiceReceiver.getCustomer()+"%"));
				}
				if (!StringUtils.isEmpty(invoiceReceiver.getSYS_AUTHORS())) {
					predicates.add(criteriaBuilder.like(root.get("SYS_AUTHORS"), "%"+invoiceReceiver.getSYS_AUTHORS()+"%"));
				}
				if(invoiceReceiver.getI_DateStart()!=null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("I_Date"),invoiceReceiver.getI_DateStart()));
				}
				if(invoiceReceiver.getI_DateEnd()!=null) {
					predicates.add(criteriaBuilder.lessThan(root.get("I_Date"), DateUtil.addSeconds(invoiceReceiver.getI_DateEnd(), 86399)));
				}
				if(!StringUtils.isEmpty(invoiceReceiver.getInvoiceno())) {
					String[] ins = invoiceReceiver.getInvoiceno().split(",");
					In<String> in = criteriaBuilder.in(root.get("invoiceno"));
					for (String string : ins) {
						in.value(string);
					}
					predicates.add(in);
				}
				predicates.add(criteriaBuilder.notEqual(root.get("SYS_DELETEFLAG"),1));//为1，代表已经删除
				List<Order> orders=new ArrayList<>();
				orders.add(criteriaBuilder.desc(root.get("I_Date")));
 				return query.where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orders).getRestriction();
			}
		};
		return this.invoiceRepository.findAll(specification, 
				PageRequest.of(invoiceReceiver.getPageIndex()==null?0:(invoiceReceiver.getPageIndex()-1),
						invoiceReceiver.getPageSize()==null?15:invoiceReceiver.getPageSize()));
	}
	@Override
	public Invoice save(Invoice invoice) {
		invoice.setSYS_LASTMODIFIED(new Date());
		if(invoice.getSYS_CREATED()==null) {
			invoice.setSYS_DELETEFLAG(0);
			invoice.setSYS_CREATED(new Date());
		}
		return this.invoiceRepository.save(invoice);
	}
	@Override
	@Transactional
	public void update(String json) {
		Invoice invoice=JsonUtils.getGson().fromJson(json, Invoice.class);
		Invoice old=this.findById(invoice.getSYS_DOCUMENTID());
		if(old==null) throw new RuntimeException("ID为【"+invoice.getSYS_DOCUMENTID()+"】的发票已经删除");
		old.setSYS_LASTMODIFIED(new Date());
		old.setSYS_CURRENTUSERID(invoice.getSYS_CURRENTUSERID());
		old.setSYS_CURRENTUSERNAME(invoice.getSYS_CURRENTUSERNAME());
		//回款更新
		if(invoice.getI_AmountBack()!=null) {
			old.setI_AmountBack(old.getI_AmountBack()+invoice.getI_AmountBack());
			//已经关联金额的开票金额
			old.setIAmountLinked(old.getI_Amount());
			old.setIAmountUnLinked(old.getI_Amount()-old.getIAmountLinked());
		}
		
	}
	@Override
	public Invoice test(Invoice invoice) {
		
		return invoice;
	}
	@Override
	@Transactional
	public void deleteById(Long id) {
		
		this.invoiceRepository.deleteById(id);
		
	}
	@Override
	public Invoice findById(Long id) {
		Optional<Invoice> invoice = this.invoiceRepository.findById(id);
		return invoice.isPresent()?invoice.get():null;
	}
	@Override
	@Transactional
	public boolean saveWithPayment(String pojos) {
		PojosWrapper pojosWrapper=JsonUtils.getGson().fromJson(pojos, PojosWrapper.class);
		Invoice invoice=pojosWrapper.getInvoice();
		try {
			//添加发票
			Payment payment=pojosWrapper.getPayment();
			if(invoice!=null && !StringUtils.isEmpty(invoice.getInvoiceno())) {
				this.save(invoice);
				payment.setPinvoiceno(invoice.getInvoiceno());
				payment.setPinvoicedmoney(invoice.getI_Amount());
				payment.setPinvoiceablemoney(0.0);
			}else {
				payment.setPinvoiceablemoney(payment.getP_amount());
				payment.setPinvoicedmoney(0.0);
			}
			//添加或者更新收款
			
			if (payment.getSYS_DOCUMENTiD()==null) {
				if(StringUtils.isEmpty(payment.getPbalancedmoney())) payment.setPbalancedmoney(0.0);
				if(StringUtils.isEmpty(payment.getPbalancedreal())) payment.setPbalancedreal(0.0);
				Payment payment2=this.paymentService.save(payment);	
			}else {
				this.paymentService.updateInvoice(payment);
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	@Override
	@Transactional
	public boolean deleteInvoice(String json) {
		Invoice invoice=JsonUtils.getGson().fromJson(json, Invoice.class);
		try {
			//修改对应收款
			this.paymentService.deleteInvoice(invoice);
			//删除发票
			this.deleteById(invoice.getSYS_DOCUMENTID());
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public boolean checkInvoiceno(String invoiceno) {
		try {
			return this.invoiceRepository.existsByInvoiceno(invoiceno);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	@Transactional
	public boolean invoiceBack(String json) {
		PojosWrapper wrapper=JsonUtils.getGson().fromJson(json, PojosWrapper.class);
		Invoice invoice=wrapper.getInvoice();
		Payment payment=wrapper.getPayment();
		try {
			//新建发票,回款等于开票金额
			invoice.setSYS_DELETEFLAG(0);
			this.invoiceRepository.save(invoice);
			//收款开票更新
			this.updatePaymentWithInvoiceBack(payment,invoice);
			this.paymentService.updateOnly(payment);
			//自动开票
			this.balanceService.autoInvoice(payment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	private Invoice updateInvoice(Invoice invoice) {
		invoice.setSYS_CREATED(new Date());
		invoice.setSYS_LASTMODIFIED(new Date());
//		if(invoice.getIAmountLinked()==null) invoice.setIAmountLinked(0.0);
//		if(invoice.getIAmountUnLinked()==null)invoice.setIAmountUnLinked(0.0);
//		invoice.setIAmountLinked(invoice.getI_Amount());
//		invoice.setIAmountUnLinked(0.0);
		return invoice;	
	}
	private Payment updatePaymentWithInvoiceBack(Payment payment, Invoice invoice) {
		if(invoice.getI_Amount()<=0) {
			throw new RuntimeException("开票金额必须大于0");
		}
		if (StringUtils.isEmpty(payment.getPinvoiceno())) {
			payment.setPinvoiceno(invoice.getInvoiceno());
		} else {
			payment.setPinvoiceno(payment.getPinvoiceno()+","+invoice.getInvoiceno().trim());
		}
		payment.setPinvoicedmoney(payment.getPinvoicedmoney()+invoice.getI_Amount());
		payment.setPinvoiceablemoney(payment.getPinvoiceablemoney()-invoice.getI_Amount());
		return payment;
		
	}
	@Override
	public void updateInvoiceWithMoneyback(Payment newpayment) {
		try {
			// 借票回款 只有一张发票
			Invoice invoice=this.invoiceRepository.findinvoicebyInvoiceno(newpayment.getPinvoiceno());
			if (invoice == null) {
				throw new RuntimeException("借票回款更新发票【"+newpayment.getPinvoiceno()+"】失败，原因：该发票不存在");
			}
			invoice.setI_AmountBack(invoice.getI_AmountBack()+newpayment.getP_amount());
			invoice.setSYS_LASTMODIFIED(new Date());
			this.invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw new RuntimeException("借票回款更新发票失败");
		}
		
	}
	@Override
	public String getLastInvoiceno() {
		
		return this.invoiceRepository.getLastInvoiceno();
	}


}
