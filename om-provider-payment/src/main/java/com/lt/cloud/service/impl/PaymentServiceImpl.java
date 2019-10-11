package com.lt.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lt.cloud.config.Settings;
import com.lt.cloud.dao.jpa.PaymnetRepository;
import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.Payment;
import com.lt.cloud.pojo.PaymentReceive;
import com.lt.cloud.pojo.PaymentWrapper;
import com.lt.cloud.service.BalanceService;
import com.lt.cloud.service.InvoiceService;
import com.lt.cloud.service.PaymentService;
import com.lt.cloud.utils.JsonUtils;





@Component
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymnetRepository paymnetRepository;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private InvoiceService invoiceService;
	@Override
	public Page<Payment> findAll(PaymentReceive paymentReceive, Pageable pageable) {
		
		Specification<PaymentReceive> spec=new Specification<PaymentReceive>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PaymentReceive> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate=new ArrayList<>();
				if(paymentReceive.getSYS_DOCUMENTiD()!=null) {
					predicate.add(criteriaBuilder.equal(root.get("SYS_DOCUMENTiD"),paymentReceive.getSYS_DOCUMENTiD()));
				}
				if(!StringUtils.isEmpty(paymentReceive.getpCustomer())) {
					predicate.add(criteriaBuilder.equal(root.get("pCustomer"), paymentReceive.getpCustomer()));
				}
				if (!StringUtils.isEmpty(paymentReceive.getCustomerLike())) {
					predicate.add(criteriaBuilder.like(root.get("pCustomer"), paymentReceive.getCustomerLike()));
				}
				if(!StringUtils.isEmpty(paymentReceive.getSYS_AUTHORS())) {
					predicate.add(criteriaBuilder.equal(root.get("SYS_AUTHORS"), paymentReceive.getSYS_AUTHORS()));
				}
				if(!StringUtils.isEmpty(paymentReceive.getPinvoiceno())) {
					predicate.add(criteriaBuilder.like(root.get("pinvoiceno"), paymentReceive.getPinvoiceno()));
				}
				if(!StringUtils.isEmpty(paymentReceive.getpSalesman())) {
					predicate.add(criteriaBuilder.equal(root.get("pSalesman"), paymentReceive.getpSalesman()));
				}
				if (paymentReceive.getP_Org_ID()!=null) {
					predicate.add(criteriaBuilder.equal(root.get("P_Org_ID"), paymentReceive.getP_Org_ID()));
				}
				if (!StringUtils.isEmpty(paymentReceive.getP_Org())) {
					predicate.add(criteriaBuilder.equal(root.get("P_Org"), paymentReceive.getP_Org()));
				}
				if (paymentReceive.getP_SrcID()!=null) {
					predicate.add(criteriaBuilder.equal(root.get("P_SrcID"), paymentReceive.getP_SrcID()));
				}
				if (paymentReceive.getEndDate()!=null) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("SYS_CREaTED"),paymentReceive.getEndDate()));
				}
				if (paymentReceive.getStartDate()!=null) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("SYS_CREaTED"),paymentReceive.getStartDate()));
				}
				if(paymentReceive.getpDateStart()!=null) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("pDate"),paymentReceive.getpDateStart()));
				}
				if(paymentReceive.getpDateEnd()!=null) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("pDate"), paymentReceive.getpDateEnd()));
				}
				if (paymentReceive.getpOrderID()!=null) {
					predicate.add(criteriaBuilder.like(root.get("pOrderID"), "%"+paymentReceive.getpOrderID()+"%"));
				}
				if (!StringUtils.isEmpty(paymentReceive.getP_publication())) {
					predicate.add(criteriaBuilder.equal(root.get("p_publication"), paymentReceive.getP_publication()));
				}
				if (paymentReceive.getHasleft()!=null) {
					if (paymentReceive.getHasleft()==true) {
						predicate.add(criteriaBuilder.greaterThan(root.get("amountleft"), 0));
					}
					if (paymentReceive.getHasleft()==false) {
						predicate.add(criteriaBuilder.equal(root.get("amountleft"), 0));
					}
				}
				if(paymentReceive.getIsBalance()!=null && paymentReceive.getIsBalance()==true) {
					predicate.add(criteriaBuilder.greaterThan(root.get("pbalanceablemoney"), 0));
				}
				if (paymentReceive.getIsInvoice()!=null && paymentReceive.getIsInvoice()==true) {
					predicate.add(criteriaBuilder.greaterThan(root.get("pinvoiceablemoney"), 0));
				}
				if (!StringUtils.isEmpty(paymentReceive.getSYS_CURRENTSTATUS())) {
					predicate.add(criteriaBuilder.equal(root.get("SYS_CURRENTSTATUS"), paymentReceive.getSYS_CURRENTSTATUS()));
				}
				predicate.add(criteriaBuilder.notEqual(root.get("SYS_DELETEFLAG"),1));//为1，代表已经删除
				Predicate[] pre=new Predicate[predicate.size()];
				List<Order> orders=new ArrayList<>();
				orders.add(criteriaBuilder.desc(root.get("SYS_LASTMODIFIED")));
				return query.where(predicate.toArray(pre)).orderBy(orders).getRestriction();
			}
		};
		
		return this.paymnetRepository.findAll(spec, pageable);
	}
	private Payment setMoneyback(Payment payment) {
		payment.setPinvoicedmoney(payment.getP_amount());
		payment.setP_Type(4);
		return payment;
	}
	private Payment normalpayment(Payment payment) {
		payment.setAmountleft(payment.getP_amount());
		payment.setPbalanceablemoney(payment.getP_amount());
		payment.setPinvoiceablemoney(payment.getP_amount());
		return payment;
	}
	private Payment lendpayment(Payment payment) {
		payment.setPbalanceablemoney(payment.getPinvoicedmoney());
		return payment;
	}
	@Override
	public void saveNewpayment(Payment payment) {
		payment.setSYS_CREaTED(new Date());
		payment.setSYS_LASTMODIFIED(new Date());
		if (payment.getP_amount()==null || payment.getP_amount()==0) {
			//收款额为0，说明是借票收款，可平帐金额等于借票金额
			lendpayment(payment);
		}else {
			
			if (payment.getP_SrcID()!=null) {
				//为借票回款
				setMoneyback(payment);
			}else {
				//为正常收款，可平帐金额等于收款总额
				normalpayment(payment);
			}
			
		}
		try {
			this.save(payment);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Payment updatePaymentWithBalanceOrders(Payment old, Payment payment) {
		//增加平帐金额
		if (payment.getPbalancedmoney()!=null) {
			// 平账金额更新
			if(old.getPbalancedmoney()==null) old.setPbalancedmoney(0.0);
			old.setPbalancedmoney(old.getPbalancedmoney()+payment.getPbalancedmoney());
			// 可平账金额更新
			if(old.getPbalanceablemoney()==null) old.setPbalanceablemoney(0.0);
			old.setPbalanceablemoney(old.getPbalanceablemoney()-payment.getPbalancedmoney());
		}
		//修改实平帐金额
		if (payment.getPbalancedreal()!=null) {
			if(old.getPbalancedreal()==null) old.setPbalancedreal(0.0);
			old.setPbalancedreal(old.getPbalancedreal()+payment.getPbalancedmoney());
		}
		// 余款更新
		old.setAmountleft(old.getAmountleft()-payment.getPbalancedmoney());
//		// 开票金额更新
//		this.setInvoice(old,payment);
		return old;
		
	}
	/**
	 * 反平账不更新收款的开票信息
	 * @param old
	 * @param payment
	 * @return
	 */
	private Payment updatePaymentWithBalanceReverse(Payment old, Payment payment) {
		//增加平帐金额
		if (payment.getPbalancedmoney()!=null) {
			// 平账金额更新
			if(old.getPbalancedmoney()==null) old.setPbalancedmoney(0.0);
			old.setPbalancedmoney(old.getPbalancedmoney()+payment.getPbalancedmoney());
			// 可平账金额更新
			if(old.getPbalanceablemoney()==null) old.setPbalanceablemoney(0.0);
			old.setPbalanceablemoney(old.getPbalanceablemoney()-payment.getPbalancedmoney());
		}
		//实平金额更新
		if (payment.getPbalancedreal()!=null) {
			old=this.updatePaymentWithBalanceReal(old,payment);
		}
		return old;
	}
	@Override
	public Payment updatePaymentWithPrebalanceOrders(Payment old, Payment payment) {
		//增加平帐金额
		if (payment.getPbalancedmoney()!=null) {
			// 平账金额更新
			if(old.getPbalancedmoney()==null) old.setPbalancedmoney(0.0);
			old.setPbalancedmoney(old.getPbalancedmoney()+payment.getPbalancedmoney());
			// 可平账金额更新
			if(old.getPbalanceablemoney()==null) old.setPbalanceablemoney(0.0);
			old.setPbalanceablemoney(old.getPbalanceablemoney()-payment.getPbalancedmoney());
		}
		//实平金额更新
		if (payment.getPbalancedreal()!=null) {
			old=this.updatePaymentWithBalanceReal(old,payment);
		}
//		// 开票金额更新
//		this.setInvoice(old,payment);
		return old;
		
	}
	@Override
	public Payment updatePaymentWithBalanceReal(Payment old, Payment payment) {
		//更新实平账金额
		if (old.getPbalancedreal()==null) old.setPbalancedreal(0.0);
		old.setPbalancedreal(old.getPbalancedreal()+payment.getPbalancedreal());
		// 更新余款
		old.setAmountleft(old.getAmountleft()-payment.getPbalancedreal());
		return old;
	}
	@Override
	public Payment updatePaymentWithLendInvoice(Payment old, Payment payment) {
		//修改关联发票金额
		if (payment.getPinvoicedmoney()!=null) {
			//如果开票金额不为0或者空
			if(old.getPinvoicedmoney()==null) old.setPinvoicedmoney(0.0);
			//更新已开票金额
			old.setPinvoicedmoney(old.getPinvoicedmoney()+payment.getPinvoicedmoney());

			//更新可开票金额
			if(old.getPinvoiceablemoney()==null)old.setPinvoiceablemoney(0.0);
			old.setPinvoiceablemoney(old.getPinvoiceablemoney()-payment.getPinvoicedmoney());

		}
		return old;
		
	}
	@Override
	public Payment updatePaymentWithInvoice(Payment old,Payment payment) {
		return old;
	
		
	}
	@Override
	public Payment updatePayment(Payment payment) {
		Payment old=this.findById(payment.getSYS_DOCUMENTiD());
		old.setSYS_LASTMODIFIED(new Date());
		
		switch (payment.getSYS_CURRENTSTATUS()) {
		case Settings.PAYMENT_TYPE_MONEYBACK:
			old=this.updatePaymentWithMoneyback(old,payment);
			break;
		case Settings.PAYMENT_TYPE_BALANCE:
			old=this.updatePaymentWithBalanceOrders(old, payment);
			break;
		case Settings.PAYMENT_TYPE_PREBALANCE:
			old=this.updatePaymentWithPrebalanceOrders(old, payment);
			break;
		case Settings.PAYMENT_TYPE_BALANCEREAL:
			old=this.updatePaymentWithBalanceReal(old, payment);
		case Settings.PAYMENT_TYPE_LENDINVOICE:
			old=this.updatePaymentWithLendInvoice(old, payment);
		default:
			throw new RuntimeException("不存在的收款更新方式【"+payment.getSYS_CURRENTSTATUS()+"】");
		}


		try {
			return this.save(old);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void save(String json) {
		Payment payment=JsonUtils.getGson().fromJson(json, Payment.class);
		try {
			if(payment.getSYS_DOCUMENTiD()==null) {
				this.saveNewpayment(payment);
			}else {
				this.updatePayment(payment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("收款【"+json+"】保存失败！！");
		}
		
	}
	@Override
	public Payment save(Payment payment) {
		payment.setSYS_CREaTED(new Date());
		payment.setSYS_LASTMODIFIED(new Date());
		payment.setSYS_DELETEFLAG(0);
		switch (payment.getSYS_CURRENTSTATUS()) {
		case "正常收款":
			payment=this.saveNormalPayment(payment);
			break;
		default:
			break;
		}
		
		return this.paymnetRepository.save(payment);
	}
	@Override
	public Payment saveNormalPayment(Payment payment) {
		payment.setPbalanceablemoney(payment.getP_amount());
		payment.setPbalancedmoney(0.0);
		payment.setAmountleft(payment.getP_amount());
		payment.setPbalancedreal(0.0);
		payment.setPamountout(0.0); // 转款
		payment.setPamountback(0.0); // 退款金额
		return payment;
	}

	@Override
	public Payment findById(Long id) {
		Optional<Payment> result = this.paymnetRepository.findById(id);
		if(result.isPresent()) return result.get();
		return null;
	}
	@Override
	public Boolean update(String json) {
		Payment np=JsonUtils.getGson().fromJson(json, Payment.class);
		Payment payment=this.findById(np.getSYS_DOCUMENTiD());
		if(payment==null) return false;
		payment.setSYS_LASTMODIFIED(new Date());
		payment.setSYS_CURRENTUSERID(np.getSYS_CURRENTUSERID());
		payment.setsYSCURRENTUSERNAME(np.getsYSCURRENTUSERNAME());
		
		//修改开票金额
		if (np.getPinvoicedmoney()!=null) {
			payment.setPinvoicedmoney(payment.getPinvoicedmoney()+np.getPinvoicedmoney());
			payment.setPinvoiceablemoney(payment.getP_amount()-payment.getPinvoicedmoney());
		}
		//修改平帐金额
		if (np.getPbalancedmoney()!=null) {
			payment.setPbalancedmoney(payment.getPbalancedmoney()+np.getPbalancedmoney());
			payment.setPbalanceablemoney(payment.getP_amount()-payment.getPbalancedmoney());
		}
		try {
			this.paymnetRepository.save(payment);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Boolean updateInvoice(Payment np) {
		Payment payment=this.findById(np.getSYS_DOCUMENTiD());
		if(payment==null) return false;
		payment.setSYS_LASTMODIFIED(new Date());
		payment.setSYS_CURRENTUSERID(np.getSYS_CURRENTUSERID());
		payment.setsYSCURRENTUSERNAME(np.getsYSCURRENTUSERNAME());
		//借票回款修改
		if (np.getP_amount()!=null) {
			//回款涉及总额和剩余金额变化
			if(payment.getP_amount()==null) payment.setP_amount(0.0);
			if(payment.getAmountleft()==null) payment.setAmountleft(0.0);
			payment.setP_amount(payment.getP_amount()+np.getP_amount());
			payment.setAmountleft(payment.getAmountleft()+np.getPamountout());
		}
		//修改开票金额
		if (np.getPinvoicedmoney()!=null) {
			if(payment.getPinvoicedmoney()==null) payment.setPinvoicedmoney(0.0);
			//修改已经开票金额
			payment.setPinvoicedmoney(payment.getPinvoicedmoney()+np.getPinvoicedmoney());
			//修改可开票金额
			if (payment.getP_amount()-payment.getPinvoicedmoney()>0) {
				payment.setPinvoiceablemoney(payment.getP_amount()-payment.getPinvoicedmoney());
			}else {
				payment.setPinvoiceablemoney(0.0);
			}
			
			if(payment.getPinvoiceno()==null||"".equals(payment.getPinvoiceno())) {
				payment.setPinvoiceno(np.getPinvoiceno());
			}else {
				payment.setPinvoiceno(payment.getPinvoiceno()+","+np.getPinvoiceno());
			}
			
		}
		//修改平帐金额
		if (np.getPbalancedmoney()!=null) {
			if(payment.getPbalancedmoney()==null) payment.setPbalancedmoney(0.0);
			payment.setPbalancedmoney(payment.getPbalancedmoney()+np.getPbalancedmoney());
			if (payment.getP_amount()-payment.getPbalancedmoney()>0) {
				payment.setPbalanceablemoney(payment.getP_amount()-payment.getPbalancedmoney());
			}else {
				payment.setPbalanceablemoney(0.0);
			}
		}
		//修改实平帐金额
		if (np.getPbalancedreal()!=null) {
			if(payment.getPbalancedreal()==null) payment.setPbalancedreal(0.0);
			payment.setPbalancedreal(payment.getPbalancedreal()+np.getPbalancedreal());
		}
		try {
			this.paymnetRepository.save(payment);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void updateWithBalance(Payment newpayment) {
		Payment old=this.findById(newpayment.getSYS_DOCUMENTiD());
		switch (newpayment.getSYS_CURRENTSTATUS()) {
		case Settings.PAYMENT_TYPE_BALANCE:
			old=this.updatePaymentWithBalanceOrders(old, newpayment);
			break;
		case Settings.PAYMENT_TYPE_BALANCEREAL:
			old=this.updatePaymentWithBalanceReal(old, newpayment);
			break;
		case Settings.PAYMENT_TYPE_PREBALANCE:
			old=this.updatePaymentWithPrebalanceOrders(old, newpayment);
			break;
		case Settings.PAYMENT_TYPE_BALANCREVERSE:
			//反平账不更新收款的发票
			
			old=this.updatePaymentWithBalanceReverse(old, newpayment);
			break;
		default:
			throw new RuntimeException("不存在的收款平账方式【"+newpayment.getSYS_CURRENTSTATUS()+"】");
		}
		try {
			this.paymnetRepository.save(old);
		} catch (Exception e) {
			throw new RuntimeException("收款平账失败【"+JsonUtils.getGson().toJson(old)+"】");
		}
		
	}
	@Override
	public Boolean updateInvoice(String json) {
		Payment np=JsonUtils.getGson().fromJson(json, Payment.class);
		return this.updateInvoice(np);
	}
	private String deleteInvoiceno(String old,String target) {
		if (old.indexOf(",")>-1) {
			if (old.indexOf(target)==0) {
				old=old.replace(target+",", "");
			}else {
				old=old.replace(","+target, "");
			}
		}else {
			old=old.replace(target, "");
		}
		return old;
	}
	@Override
	public void deleteById(Long id) {
		this.paymnetRepository.deleteById(id);
		
	}
	Payment updatePaymentWithMoneyback(Payment payment,Long sourcePaymentId) {
		//P_SrcID只有借票回款才不为空
		payment.setP_SrcID(null);
		//新的收款SYS_DOCUMENTiD为空，无法对旧的收款进行更新，所以要赋予其旧收款的ID
		payment.setSYS_DOCUMENTiD(sourcePaymentId);
		return this.updatePayment(payment);
	}
	@Override
	@Transactional
	public boolean moneyback(String pojos) {
		PaymentWrapper wrapper=JsonUtils.getGson().fromJson(pojos, PaymentWrapper.class);
		try {
		//新建收款
		wrapper.getNewpayment().setPinvoiceno(wrapper.getOldpayment().getPinvoiceno());
		this.save(JsonUtils.getGson().toJson(wrapper.getNewpayment()));
		//源收款需要实平金额
		double needbalancereal=wrapper.getOldpayment().getPbalancedmoney()-wrapper.getOldpayment().getPbalancedreal();
		//本次回款金额
		double moneyback=wrapper.getNewpayment().getP_amount();
		//本期实平金额=源收款需要实平金额《=本次回款金额？源收款需要实平金额：本次回款金额
		double balancereal= needbalancereal<=moneyback?needbalancereal:moneyback;
		//借票回款更新源收款
		Payment source=this.updatePaymentWithMoneyback(wrapper.getOldpayment(), wrapper.getNewpayment());
		this.updateOnly(source);
		//更新发票
		this.invoiceService.updateInvoiceWithMoneyback(wrapper.getNewpayment());
		//自动实平账
		source.setAmountleft(source.getAmountleft()+balancereal); //source为最终实平之后的值，所以要先加上本次实平金额，
		this.balanceService.autoBalanceReal(source);
		return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public Payment updatePaymentWithMoneyback(Payment old,Payment payment) {
		// 收款为 old=old+new
		if (old.getP_amount()==null) old.setP_amount(0.0);
		old.setP_amount(old.getP_amount()+payment.getP_amount());
		old.setAmountleft(old.getAmountleft()+payment.getP_amount());
		// 修改实平账金额
		double total=old.getPbalancedmoney()-old.getPbalancedreal();// 需实平金额
		double amount=payment.getP_amount(); // 本次回款金额
		if (total>0) {// 实平账金额 《平账金额 需要更新
			if (total <= amount) { //回款》=需实平金额 
				old.setPbalancedreal(old.getPbalancedreal()+total);
				old.setAmountleft(old.getAmountleft()-total);
			}else {
				old.setPbalancedreal(old.getPbalancedreal()+amount);
				old.setAmountleft(old.getAmountleft()-amount);
			}
		}
		return old;
		
	}
	@Override
	public void deleteByInvoiceno(String pinvoiceno) {
		try {
			List<Payment> payments=this.paymnetRepository.findByPinvoiceno(pinvoiceno);
			if (payments.size()>=2) {
				throw new RuntimeException("一个发票号找到多个收款，删除失败");
			}
			if (payments.size()==1) {
				Payment payment=payments.get(0);
				//已经评过账的不能删除
				if (payment.getPbalancedmoney()!=null && payment.getPbalancedmoney()>0) {
					throw new RuntimeException("一个发票号对应的收款已经平过账不能删除，删除失败");
				}
				this.paymnetRepository.deleteById(payment.getSYS_DOCUMENTiD());
			}
			this.paymnetRepository.deleteByPinvoiceno(pinvoiceno);
		} catch (Exception e) {
			throw new RuntimeException("删除发票【"+pinvoiceno+"】失败");
		}
		
	}
	@Override
	public String findAllAsJson(String json) {
		PaymentReceive receive=JsonUtils.getGson().fromJson(json, PaymentReceive.class);

		return JsonUtils.formatPageForPagination(this.findAll(receive));
	}

	private Page<Payment> findAll(PaymentReceive receive) {
		Pageable pageable=PageRequest.of(receive.getPageIndex()==null?0:(receive.getPageIndex()-1), receive.getPageSize()==null?15:receive.getPageSize());

		return this.findAll(receive, pageable);
		
	}
	@Override
	public Page<Payment> findAll(String json) {
		PaymentReceive receive=JsonUtils.getGson().fromJson(json, PaymentReceive.class);
		Pageable pageable=PageRequest.of(receive.getPageIndex()==null?0:(receive.getPageIndex()-1), receive.getPageSize()==null?15:receive.getPageSize());

		return this.findAll(receive, pageable);
	}
	@Override
	public void updateOnly(Payment source) {
		try {
			source.setSYS_LASTMODIFIED(new Date());
			this.paymnetRepository.save(source);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	@Override
	public boolean deleteInvoice(Invoice invoice) {
		try {
			//是否已经平过账
			if (this.isBalancedByInvoice(invoice.getInvoiceno())) {
				throw new RuntimeException("发票号【"+invoice.getInvoiceno()+"】对应的收款已经平过账了，无法删除");
			}
			// 根据发票，以及
			List<Payment> payments=this.findPaymentUnbalancedWithInvoiceno(invoice.getInvoiceno());
			if (payments.size()>1 ) {
				throw new RuntimeException("发票号【"+invoice.getInvoiceno()+"】，对应多个收款，无法删除");
			}
			if (payments.size()==0) {
				return true;
			}
			//更新收款
			this.updatePaymentWithInvoiceDelete(payments.get(0),invoice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return true;
		
	}
	@Override
	public List<Payment> findPaymentUnbalancedWithInvoiceno(String invoiceno) {
		return this.paymnetRepository.findPaymentUnbalancedWithInvoiceno(invoiceno);
	}
	// 确定指定发票的收款是否已经平账
	@Override
	public boolean isBalancedByInvoice(String invoiceno) {
		//根据发票号找到收款
		Integer flag = this.paymnetRepository.findIsBalancedWithInvoiceno(invoiceno);
		if (flag == 1) {
			return true;
		}
		return false;
	}
	@Override
	@Transactional
	public boolean updatePaymentWithInvoiceDelete(Payment payment, Invoice invoice) {
		// 判断是否已经平过账
		if (payment.getPbalancedmoney()>0) {
			throw new RuntimeException("收款已经平过账，无法删除发票");
		}
		try {
			
			//判断是 借票、正常收款、转入
			switch (payment.getSYS_CURRENTSTATUS()) {
			case Settings.PAYMENT_TYPE_NORMALPAYMENT:
				//收款的发票号更新
				this.updatePaymentWithInvoicenoSlice(payment,invoice.getInvoiceno());
				//收款开票金额和未开票金额更新
				payment.setPinvoiceablemoney(payment.getPinvoiceablemoney()+invoice.getI_Amount());
				payment.setPinvoicedmoney(payment.getPinvoicedmoney()-invoice.getI_Amount());
				this.updateOnly(payment);//更新数据库
				break;
		    case Settings.PAYMENT_TYPE_LENDINVOICE:
		    	if (payment.getP_amount()>0) {
					throw new RuntimeException("发票【"+invoice.getInvoiceno()+"】对应的收款【"+payment.getSYS_DOCUMENTiD()+"】，已经回款【"+payment.getP_amount()+"】，无法删除");
				}
		    	//删除收款
		    	this.paymnetRepository.deleteById(payment.getSYS_DOCUMENTiD());
		    	break;
		    case Settings.PAYMENT_TYPE_TRANSFERIN:
		    	if (payment.getP_amount()>0) {
					throw new RuntimeException("发票【"+invoice.getInvoiceno()+"】对应的收款【"+payment.getSYS_DOCUMENTiD()+"】，已经回款【"+payment.getP_amount()+"】，无法删除");
				}
		    	//删除收款
		    	this.paymnetRepository.deleteById(payment.getSYS_DOCUMENTiD());
		    	break;
			default:
				throw new RuntimeException("收款类型为【"+payment.getSYS_CURRENTSTATUS()+",删除发票失败】");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		return true;
		
		
	}
	private void updatePaymentWithInvoicenoSlice(Payment payment, String invoiceno) {
		String source=payment.getPinvoiceno();
		String des=invoiceno;
		String resutl="";
		for (String string : source.split(",")) {
			if (!string.equals(des)) {
				resutl+=string+",";
			}
		}
		if(!StringUtils.isEmpty(resutl))resutl=resutl.substring(0, resutl.length()-1);
		payment.setPinvoiceno(resutl);
	}
	@Override
	public boolean transferMoney(String pojos) {
		PaymentWrapper wrapper=JsonUtils.getGson().fromJson(pojos, PaymentWrapper.class);
		Payment source = this.findById(wrapper.getOldpayment().getSYS_DOCUMENTiD());
		if (source == null) {
			throw new RuntimeException("收款【"+wrapper.getOldpayment().getSYS_DOCUMENTiD()+"】不存在！！");
		}
		Payment destination = wrapper.getNewpayment();
		double transferMoney = destination.getP_amount(); // 转款金额
		double leftMoney = source.getAmountleft(); // 余款
		if (leftMoney <=0) {
			throw new RuntimeException("收款【"+source.getSYS_DOCUMENTiD()+"】的余款为【"+leftMoney+"】，无法转款");
		}
		if (transferMoney > leftMoney) {
			throw new RuntimeException("转出金额【"+transferMoney+"】大于余款【"+leftMoney+"】，无法转出！！");
		}
		if (source.getPamountout() == null) {
			source.setPamountout(0.0);
		}
		source.setPamountout(source.getPamountout()+transferMoney);// 更新转出金额
		source.setAmountleft(leftMoney-transferMoney); // 更新余款
		source.setPbalanceablemoney(source.getPbalanceablemoney()-transferMoney); // 更新可平账金额
		// 修改开票金额
		double invoiceable = source.getPinvoicedmoney() - source.getPbalancedmoney(); // 可转移开票金额
		double invoiced = 0.0;
		this.initialPaymentWithAmount(destination);// 初始化新的收款
		source.setSYS_LASTMODIFIED(new Date());
		if (invoiceable <= 0) { // 开票金额 < 已平账金额
			// 什么也不做
		} else {
			if (invoiceable<transferMoney) { // 可转开票金额<转款金额
				invoiced = invoiceable;
			} else {
				invoiced = transferMoney;
			}
			destination.setPinvoicedmoney(invoiced);
			destination.setPinvoiceablemoney(transferMoney-invoiced);
			source.setPinvoicedmoney(source.getPinvoicedmoney()-invoiced);
			source.setPinvoiceablemoney(source.getPinvoiceablemoney()+invoiced-transferMoney);
		}
		try {
			// 转出纪录 
			Payment record = new Payment();
			record.setP_Customer_iD(source.getP_Customer_iD());
			record.setpCustomer(source.getpCustomer());
			record.setP_amount(-transferMoney);
			record.setPinvoicedmoney(-invoiced);
			record.setSYS_LASTMODIFIED(new Date());
			record.setSYS_CREaTED(new Date());
			record.setpDate(destination.getpDate());
			record.setP_SrcID(source.getSYS_DOCUMENTiD().intValue());
			record.setSYS_CURRENTSTATUS(Settings.PAYMENT_TYPE_TRANSFEROUT);
//			System.out.println("transferMoney:"+transferMoney+",invoiceable:"+invoiceable+",invoiced:" + invoiced);
//			System.out.println("收款金额："+destination.getP_amount()+"，可平账金额："+destination.getPbalanceablemoney()+"，平账金额："+destination.getPbalancedmoney()+"，余款："+destination.getAmountleft()+"，未开票金额："+destination.getPinvoiceablemoney()+"，已开票金额："+destination.getPinvoicedmoney()+"，转出金额：" +destination.getPamountout());
//			System.out.println("收款金额："+source.getP_amount()+"，可平账金额："+source.getPbalanceablemoney()+"，平账金额："+source.getPbalancedmoney()+"，余款："+source.getAmountleft()+"，未开票金额："+source.getPinvoiceablemoney()+"，已开票金额："+source.getPinvoicedmoney()+"，转出金额：" +source.getPamountout());
			this.paymnetRepository.save(record);
			
			this.paymnetRepository.save(destination);
			
			this.paymnetRepository.save(source);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存转出款【"+JsonUtils.getGson().toJson(destination)+"】失败");
		}
	}
	private void initialPaymentWithAmount(Payment payment) {
		payment.setSYS_CREaTED(new Date());
		payment.setSYS_LASTMODIFIED(new Date());
		payment.setSYS_DELETEFLAG(0);
		payment.setPbalanceablemoney(payment.getP_amount());
		payment.setPbalancedmoney(0.0);
		payment.setAmountleft(payment.getP_amount());
		payment.setPbalancedreal(0.0);
		payment.setPamountout(0.0); // 转款
		payment.setPamountback(0.0); // 退款金额
		payment.setPinvoiceablemoney(payment.getP_amount());
		payment.setPinvoicedmoney(0.0);
	}
	@Override
	public String findByAdvitemID(Long advitemID) {
		List<Payment> reslut=this.paymnetRepository.findByAdvitemID(advitemID);
		return JsonUtils.getGson().toJson(reslut);
	}









}
