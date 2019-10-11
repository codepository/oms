package com.lt.cloud.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.lt.cloud.config.Settings;
import com.lt.cloud.dao.jpa.BalanceRepository;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.Balance;
import com.lt.cloud.pojo.BalanceReceiver;
import com.lt.cloud.pojo.Payment;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.service.AdvitemUpdateSevice;
import com.lt.cloud.service.BalanceService;
import com.lt.cloud.service.OrderQueryService;
import com.lt.cloud.service.PaymentService;
import com.lt.cloud.utils.JsonUtils;


@Component
public class BalanceServiceImpl implements BalanceService{
	@Autowired
	private BalanceRepository balanceRepository;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AdvitemQueryService advitemQueryService;
	@Autowired
	private AdvitemUpdateSevice advitemUpdateSevice;
	@Autowired
	private OrderQueryService orderQueryService;
	@Override
	public Page<Balance> findAll(BalanceReceiver balanceReceiver) {
		Specification<Balance> specification=new Specification<Balance>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Balance> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates=new ArrayList<>();
				if (balanceReceiver.getSYS_DOCUMENTID()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("SYS_DOCUMENTID"), balanceReceiver.getSYS_DOCUMENTID()));
				}
				if (balanceReceiver.getAditemid()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("B_AdItemID"), balanceReceiver.getAditemid()));
				}
				if (!StringUtils.isEmpty(balanceReceiver.getSYS_CURRENTSTATUS())) {
					predicates.add(criteriaBuilder.equal(root.get("SYS_CURRENTSTATUS"), balanceReceiver.getSYS_CURRENTSTATUS()));
				}
				if (!StringUtils.isEmpty(balanceReceiver.getCustomer())) {
					predicates.add(criteriaBuilder.like(root.get("B_Customer"), "%"+balanceReceiver.getCustomer()+"%"));
				}
				if (!StringUtils.isEmpty(balanceReceiver.getB_Salesman())) {
					predicates.add(criteriaBuilder.like(root.get("B_Salesman"), balanceReceiver.getB_Salesman()));
				}
				if (!StringUtils.isEmpty(balanceReceiver.getSYS_AUTHORS())) {
					predicates.add(criteriaBuilder.like(root.get("SYS_AUTHORS"), balanceReceiver.getSYS_AUTHORS()));
				}
				if (!StringUtils.isEmpty(balanceReceiver.getB_Org())) {
					predicates.add(criteriaBuilder.like(root.get("B_Org"), balanceReceiver.getB_Org()));
				}
				if(balanceReceiver.getOrderid()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("orderid"), balanceReceiver.getOrderid()));
				}
				if (balanceReceiver.getB_PayID()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("B_PayID"), balanceReceiver.getB_PayID()));
				}
				if (balanceReceiver.getPublishtimeStart()!=null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishtimeStart"), balanceReceiver.getPublishtimeStart()));
				}
				if (balanceReceiver.getPublishtimeEnd()!=null) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("publishtimeEnd"), balanceReceiver.getPublishtimeEnd()));
				}
				if (balanceReceiver.getSYS_CREATEDSTART()!=null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("SYS_CREATEDSTART"), balanceReceiver.getSYS_CREATEDSTART()));
				}
				if (balanceReceiver.getSYS_CREATEDEND()!=null) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("SYS_CREATEDEND"), balanceReceiver.getSYS_CREATEDEND()));
				}
				if (balanceReceiver.getB_InvoiceNo()!=null) {
					predicates.add(criteriaBuilder.like(root.get("B_InvoiceNo"), "%"+balanceReceiver.getB_InvoiceNo()+"%"));
				}
				List<Order> orders=new ArrayList<>();
				orders.add(criteriaBuilder.desc(root.get("SYS_LASTMODIFIED")));
				return query.where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orders).getRestriction();
			}
		};
		return this.balanceRepository.findAll(specification,
				PageRequest.of(balanceReceiver.getPageIndex()==null?0:(balanceReceiver.getPageIndex()-1), 
						balanceReceiver.getPageSize()==null?15:balanceReceiver.getPageSize()));
	}
	@Override
	@Transactional
	public Boolean saveAll(String json) {
		List<Balance> balances=JsonUtils.getGson().fromJson(json, new TypeToken<List<Balance>>() {}.getType());
		
		Payment payment=new Payment();
		balances.stream().map(e->e.getB_PayID()).distinct().forEach(id->{
			payment.setSYS_DOCUMENTiD(id);
			payment.setPbalancedmoney(balances.stream().map(b -> b.getB_Amount()==null?0.0:b.getB_Amount()).reduce((a, b) -> a + b).get());
			payment.setPbalancedreal(balances.stream().map(b -> b.getB_AmountReal()==null?0.0:b.getB_AmountReal()).reduce((a, b) -> a + b).get());
			// 已经开票金额
			Double pinvoicedmoney = balances.stream().map(b -> b.getB_AmountInvoiced()==null?0.0:b.getB_AmountInvoiced()).reduce((a, b) -> a + b).get();
			payment.setPinvoicedmoney(pinvoicedmoney);
		});
		try {
			//保存平账信息
			this.saveAllBalance(balances);
			//更新收款
			payment.setSYS_CURRENTSTATUS(balances.get(0).getSYS_CURRENTSTATUS());
			this.paymentService.updateWithBalance(payment);
			return true;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}
	@Override
	public void saveAllBalance(String json) {
		List<Balance> balances=JsonUtils.getGson().fromJson(json, new TypeToken<List<Balance>>() {}.getType());
		this.saveAllBalance(balances);
		
	}
	@Override
	public Balance updateWithBalance(Balance newbalance) {
		
		return newbalance;
	}
	@Override
	public Balance updateWithBalanceReal(Balance newbalance) {

		return newbalance;
	}
	@Override
	public Balance updateWithInvoice(Balance newbalance) {
		//修改开票金额
		if(newbalance.getB_AmountInvoiced()==null) newbalance.setB_AmountInvoiced(0.0);
		newbalance.setB_AmountUninvoiced(newbalance.getB_Amount()-newbalance.getB_AmountInvoiced());
		return newbalance;
	}
	@Override
	public void saveAllBalance(List<Balance> balances) {
		try {
			balances.stream().map(balance->{
				if (balance.getSYS_DOCUMENTID()==null) {
					balance.setSYS_CREATED(new Date());
					balance.setSYS_DELETEFLAG(0);
				}
				balance.setSYS_LASTMODIFIED(new Date());
				switch (balance.getSYS_CURRENTSTATUS()) {
				case Settings.PAYMENT_TYPE_BALANCE:
					balance=this.updateWithBalance(balance);
					break;
				case Settings.PAYMENT_TYPE_PREBALANCE:
					break;
				case Settings.PAYMENT_TYPE_BALANCEREAL:
					balance=this.updateWithBalanceReal(balance);
					break;
				case Settings.PAYMENT_TYPE_LENDINVOICE:
					
					break;
				case Settings.PAYMENT_TYPE_MONEYBACK:
					
					break;
				default:
					break;
				}

				return balance;
			}).forEach(this.balanceRepository::save);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	/**
	 * 将结果中转换成不重复的广告
	 * @param list
	 * @return
	 */
	private List<Balance> findRerversable1(List<Balance> list){
		if(list.size()==0) return null;
		List<Balance> result=new ArrayList<>();
		list.stream().map(item->item.getB_AdItemID()).distinct().forEach(item2 -> {
			Balance balance=null;
			for (Balance balance2 : list) {
				if (balance2.getB_AdItemID().equals(item2)) {
					balance=balance2;
					break;
				}
			}
			//不重复的广告ID，和对应的平账总额
			Double sum=list.stream().filter(item3->item3.getB_AdItemID().equals(item2))
					.map(item4->item4.getB_Amount())
					.reduce((acc,cur)->acc+cur).get();
			if(sum>0) {
				balance.setB_Amount(sum);
				result.add(balance);
			}
		});
		return result;
	}
	@Override
	public List<Balance> findAllReversable(Long payId) {
		List<Balance> list=this.balanceRepository.findByPayId(payId);
		return this.findRerversable1(list);
	}
	@Override
	public List<Balance> findAllReversable(BalanceReceiver receiver) {
		return null;
//		List<Balance> list=this.balanceRepository.findAllReversable(receiver);
//		return this.findRerversable1(list);
	}
	@Override
	public List<Balance> findByPayIdResultAdv(Long B_PayID) {
		
		return this.balanceRepository.findByPayIdWithAdv(B_PayID);
	}
	@Override
	public Page<Balance> findAll(String json) {
		BalanceReceiver balanceReceiver=JsonUtils.getGson().fromJson(json, BalanceReceiver.class);
		return this.findAll(balanceReceiver);
	}
	@Override
	public List<Object> findAdvidByPayid(Long B_PayID) {
		List<Object> result = this.balanceRepository.findAdvidByPayid(B_PayID);
		return result;
	}
	@Override
	public String findAdvidByPayidAsJson(Long B_PayID) {
		
		return JsonUtils.getGson().toJson(this.findAdvidByPayid(B_PayID));
	}
	@Override
	public String findAdvidByPayidAndPagable(String json) {
		BalanceReceiver receiver=JsonUtils.getGson().fromJson(json, BalanceReceiver.class);
		Pageable pageable=PageRequest.of(receiver.getPageIndex()==null?0:(receiver.getPageIndex()-1), 
				receiver.getPageSize()==null?15:receiver.getPageSize());
		System.out.println(JsonUtils.getGson().toJson(pageable));
		Page<Long> result = this.balanceRepository.findAdvidByPayidAndPagable(receiver.getB_PayID(),pageable);
		return JsonUtils.formatPageForPagination(result);
	}
	@Override
	public String findAdvByPayidAndPagable(String json) {
		Page<Long> result = this.findAdvidByPayidAsPage(json);
		if (result.getTotalPages() == 0) return "[]";
		List<Advitem> result1 = this.advitemQueryService.findByAdvids(result.getContent());
		return JsonUtils.formatDataForPagination(JsonUtils.getGson().toJson(result1), result.getTotalElements(), result.getPageable().getPageNumber()+1, result.getPageable().getPageSize());
	}
	@Override
	public Page<Long> findAdvidByPayidAsPage(String json) {
		BalanceReceiver receiver=JsonUtils.getGson().fromJson(json, BalanceReceiver.class);
		Pageable pageable=PageRequest.of(receiver.getPageIndex()==null?0:(receiver.getPageIndex()-1), 
				receiver.getPageSize()==null?15:receiver.getPageSize());
		Page<Long> result = this.balanceRepository.findAdvidByPayidAndPagable(receiver.getB_PayID(), pageable);
		return result;
	}
	@Override
	@Transactional
	public void autoInvoice(Payment source) {
//		System.out.println("******************自动开票*********************");
//		System.out.println(JsonUtils.getGson().toJson(source));
		//根据收款id查询，并且B_AmountUninvoiced>0
		List<Balance> balances=this.findByPayidAndNeedInvoice(source.getSYS_DOCUMENTiD());
		//本次总可开票金额
		Double invoicemoney=source.getPinvoicedmoney();
//		System.out.println("--------------收款ID:"+source.getSYS_DOCUMENTiD()+"可开票金额："+invoicemoney);
		if (balances.size()==0) {
			return;
		}
//		System.out.println("-----需要开票的纪录------");
//		for (Balance balance : balances) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
		//根据可开票的金额，获取平账纪录列表 balancesWhichCanInvoice(最后一个允许局部平账) 
		List<Balance> sortedUninvoiceAsc = this.sortedUninvoiceAsc(balances);
		List<Balance> balancesWhichCanInvoice1 = this.getAllBalanceWhichCanInvoice(sortedUninvoiceAsc,invoicemoney);
		System.out.println("-----可开票的纪录------");
		for (Balance balance : balancesWhichCanInvoice1) {
			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
		}
		//获取更新之后的平账信息 balancesWithInvoice4BalanceUpdate（即本次开票之后的平账纪录）
			//复制balancesWhichNowInvoice
		List<Balance> balancesWhichCanInvoiceClone1=JsonUtils.getGson().fromJson(JsonUtils.getGson().toJson(balancesWhichCanInvoice1), new TypeToken<List<Balance>>() {}.getType());
			//更新开票金额和关联发票号码( 好像会自动更新)
		List<Balance> balancesWithInvoice4BalanceUpdate=this.getBalancesWithInvoice4BalanceUpdate(balancesWhichCanInvoice1,source);
//		System.out.println("----------更新平账纪录---------------");
//		for (Balance balance : balancesWithInvoice4BalanceUpdate) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		
		
		
		//获取本次开票金额（累计已经开票金额-原来的开票金额）
		List<Balance> balancesInvoiceNow=this.getBalancesInvoice(balancesWhichCanInvoiceClone1,invoicemoney);
		//获取广告ID不重复的开票信息
		List<Balance> balancesInvoiceNowWithAdvidDistinct=this.getBalnacesInvoiceNowWithAdvidDistinct(balancesInvoiceNow);
		//广告开票列表(广告id不重复)
		List<Advitem> advitems=this.getAdvForInvoice(balancesInvoiceNowWithAdvidDistinct);
		
		
		try {
//			System.out.println("------------本次开票金额-------------");
//			for (Balance balance : balancesInvoiceNow) {
//				System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//			}
//			System.out.println("-----------获取广告ID不同的开票信息--------------------");
//			for (Balance balance : balancesInvoiceNowWithAdvidDistinct) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//			}
			//平账更新：开票金额更新 
			this.balanceRepository.saveAll(balancesWithInvoice4BalanceUpdate);
			//广告更新：开票金额更新
			this.advitemUpdateSevice.updateAll(advitems);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<Balance> findByPayidAndNeedInvoice(Long paymentid) {
		
		return this.balanceRepository.findByPayidAndNeedInvoice(paymentid);
	}
	@Override
	@Transactional
	public void autoBalanceReal(Payment source) {
		//根据 收款id，实平账金额大于0，按照欠款升序查询广告
		List<Balance> balances=this.findByPayidAndNeedBalanceReal(source.getSYS_DOCUMENTiD());
		if (balances.size()==0) {
			return;
		}
		//获取能够实平账的平账纪录列表(最后一个允许局部平账)
		List<Balance> balancesWhichNeedBalanceReal = this.getAllBalanceCanBalanceReal(this.sortedAsc(balances), source.getAmountleft());
		List<Balance> balancesWhichNeedBalanceRealClone=JsonUtils.getGson().fromJson(JsonUtils.getGson().toJson(balancesWhichNeedBalanceReal), new TypeToken<List<Balance>>() {}.getType());
		//获取更新之后的平账信息
		List<Balance> balancesWithBalanceReal4BalanceUpdate=this.getBalancesWithBalanceReal4BalanceUpdate(balancesWhichNeedBalanceRealClone, source.getAmountleft());
		//获取广告ID不重复的平账信息
		List<Balance> balancesforbalancereal= this.getBalancesForBalanceReal(balancesWhichNeedBalanceReal, source.getAmountleft());
		List<Balance> balances4balanceRealWithAdvidDistinct=this.getBalances4BalanceRealWithAdvidDistinct(balancesforbalancereal);
		//广告实平账列表(广告id不重复)
		List<Advitem> advitems=this.getAdvForBalanceReal(balances4balanceRealWithAdvidDistinct);
		try {
			//平账更新
			this.balanceRepository.saveAll(balancesWithBalanceReal4BalanceUpdate);
			//订单实平账
			this.orderQueryService.updateAllWithAdvitemlist(advitems);
			//广告实平账
			this.advitemUpdateSevice.updateAll(advitems);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		
	}
	@Override
	public Double getTotalInvoicedMoney(List<Advitem> advitems) {
		return advitems.stream().map(item->item.getAI_InvoicedMoney()).reduce((acc,cur)->acc+cur).get();
	}
	@Override
	public double getTotalAmountRealAboutAdvitems(List<Advitem> advitems) {
		
		return advitems.stream().map(item->item.getAI_AmountReceived()).reduce((acc,cur)->acc+cur).get();
	}
	@Override
	public List<Advitem> getAdvForInvoice(List<Balance> balancesInvoiceNowWithAdvidDistinct) {
		List<Advitem> advitems=new ArrayList<>();
		balancesInvoiceNowWithAdvidDistinct.stream().forEach(balance->{
			Advitem advitem=new Advitem();
			advitem.setSYS_DOCUMENTID(balance.getB_AdItemID());
			advitem.setAI_OrderID(balance.getB_OrderID());
			advitem.setSYS_CURRENTSTATUS(Settings.PAYMENT_TYPE_LENDINVOICE);
			advitem.setAI_InvoicedMoney(balance.getB_AmountInvoiced());
			advitems.add(advitem);
		});		
		return advitems;
	}
	@Override
	public List<Advitem> getAdvForBalanceReal(List<Balance> balances4balancereal){
		List<Advitem> advitems=new ArrayList<>();
		balances4balancereal.stream().forEach(balance->{
			Advitem advitem=new Advitem();
			advitem.setSYS_DOCUMENTID(balance.getB_AdItemID());
			advitem.setAI_OrderID(balance.getB_OrderID());
			advitem.setSYS_CURRENTSTATUS(Settings.PAYMENT_TYPE_BALANCEREAL);
			advitem.setAI_AmountReceived(balance.getB_AmountReal()==null?0.0:balance.getB_AmountReal());
			advitems.add(advitem);
		});		
		return advitems;
	}
    @Override
	public List<Balance> getBalnacesInvoiceNowWithAdvidDistinct(List<Balance> list) {
		
		List<Balance> result=new ArrayList<>();
		list.stream().map(item->item.getB_AdItemID()).distinct().forEach(item2 -> {
			Balance balance=null;
			for (Balance balance2 : list) {
				if (balance2.getB_AdItemID().equals(item2)) {
					balance=balance2;
					break;
				}
			}
			Double sum=list.stream().filter(item3->item3.getB_AdItemID().equals(item2))
					.map(item4->item4.getB_AmountInvoiced())
					.reduce((acc,cur)->acc+cur).get();
			if(sum>0) {
				balance.setB_AmountInvoiced(sum);
				result.add(balance);
			}
		});
		return result;
	}
	@Override
	public List<Balance> getBalances4BalanceRealWithAdvidDistinct(List<Balance> list){
		List<Balance> result=new ArrayList<>();
		list.stream().map(item->item.getB_AdItemID()).distinct().forEach(item2 -> {
//			System.out.println("-----------advid:"+item2);
			Balance balance=null;
			for (Balance balance2 : list) {
				if (balance2.getB_AdItemID().equals(item2)) {
					balance=balance2;
					break;
				}
			}
//			System.out.println("---------balance.advid:"+balance.getB_AdItemID());
			//不重复的广告ID，和对应的实平账总额
			Double sum=list.stream().filter(item3->item3.getB_AdItemID().equals(item2))
					.map(item4->item4.getB_AmountReal())
					.reduce((acc,cur)->acc+cur).get();
//			System.out.println("-----------sum:"+sum);
			if(sum>0) {
				balance.setB_AmountReal(sum);
				result.add(balance);
			}
		});
//		for (Balance balance : result) {
//			System.out.println("---------balance.advid:"+balance.getB_AdItemID()+"--实平账金额:"+balance.getB_AmountReal());
//		}
		return result;
	}
	@Override
	public List<Balance> getBalancesWithInvoice4BalanceUpdate(List<Balance> balancesWhichCanInvoiceClone,
			Payment source) {
		double total=0.0;
		double invoicemoney=source.getPinvoicedmoney();
		for (Balance balance : balancesWhichCanInvoiceClone) {
			total+=balance.getB_AmountUninvoiced();
			balance.setB_InvoiceNo(source.getPinvoiceno());
			if (total<=invoicemoney) {
				balance.setB_AmountInvoiced(balance.getB_AmountInvoiced()+balance.getB_AmountUninvoiced());
				balance.setB_AmountUninvoiced(0.0);
			}else {
				double invoiced=invoicemoney-total+balance.getB_AmountUninvoiced();
				balance.setB_AmountInvoiced(balance.getB_AmountInvoiced()+invoiced);
				balance.setB_AmountUninvoiced(balance.getB_AmountUninvoiced()-invoiced);
			}
		}
		return balancesWhichCanInvoiceClone;
	}
	@Override
	public List<Balance> getBalancesWithBalanceReal4BalanceUpdate(List<Balance> balances,double amountleft){
		double total=0.0;
		for (int i = 0; i < balances.size(); i++) {
			total+=(balances.get(i).getB_Amount()-balances.get(i).getB_AmountReal());
			if (total<=amountleft) {
				balances.get(i).setB_AmountReal(balances.get(i).getB_Amount());
			}else {
				double lastreal=balances.get(i).getB_AmountReal()+amountleft-(total-(balances.get(i).getB_Amount()-balances.get(i).getB_AmountReal()));
				balances.get(i).setB_AmountReal(lastreal);
			}
			
		}
		return balances;
	}
	
	private List<Balance> getBalancesInvoice(List<Balance> balancesWhichCanInvoice, Double invoicemoney) {
		double total=0.0;
		for (Balance balance : balancesWhichCanInvoice) {
			total+=balance.getB_AmountUninvoiced();
			if (total<=invoicemoney) {
				balance.setB_AmountInvoiced(balance.getB_AmountUninvoiced());
				balance.setB_AmountUninvoiced(0.0);
			}else {
				balance.setB_AmountInvoiced(invoicemoney-total+balance.getB_AmountUninvoiced());
				balance.setB_AmountUninvoiced(0.0);
			}
		}
		return balancesWhichCanInvoice;
	}
	@Override
	public List<Balance> getBalancesForBalanceReal(List<Balance> balances,double amountleft) {
		double total=0.0;
		for (int i = 0; i < balances.size(); i++) {
			total+=(balances.get(i).getB_Amount()-balances.get(i).getB_AmountReal());
			if (total<=amountleft) {
				balances.get(i).setB_AmountReal(balances.get(i).getB_Amount()-balances.get(i).getB_AmountReal());
			}else {
				double lastreal=balances.get(i).getB_AmountReal()-balances.get(i).getB_AmountReal()+amountleft-(total-(balances.get(i).getB_Amount()-balances.get(i).getB_AmountReal()));
				balances.get(i).setB_AmountReal(lastreal);
			}
			
		}
		return balances;
		
	}
	/**
	 *  根据收款剩余的余款，获取能够实平账的列表（最后一个允许局部平账）
	 * @param source
	 * @param amountleft
	 * @return
	 */
	@Override
	public List<Balance> getAllBalanceCanBalanceReal(List<Balance> source, double amountleft){
		double total=0.0;
		List<Balance> result=new ArrayList<>();
		for (Balance balance : source) {
			total+=balance.getB_Amount()-balance.getB_AmountReal();
			result.add(balance);
			if(total>=amountleft) {
				break;
			}
		}
		return result;
	}
	@Override
	public List<Balance> getAllBalanceWhichCanInvoice(List<Balance> sortedUninvoiceAsc, Double invoiceablemoney) {
		double total=0.0;
		List<Balance> result=new ArrayList<>();
		for (Balance balance : sortedUninvoiceAsc) {
			total+=balance.getB_AmountUninvoiced();
			result.add(balance);
			if(total>=invoiceablemoney) {
				break;
			}
		}
		return result;
	}
	@Override
	public List<Balance> sortedUninvoiceAsc(List<Balance> balances) {
		
		return balances.stream().sorted(Comparator.comparing(Balance::getB_AmountUninvoiced)).collect(Collectors.toList());
	}
	@Override
	public List<Balance> sortedAsc(List<Balance> balances) {
		return balances.stream().sorted(Comparator.comparing((balance)->{return balance.getB_Amount()-balance.getB_AmountReal();})).collect(Collectors.toList());
	}
	@Override
	public List<Balance> findByPayidAndNeedBalanceReal(Long id) {
		return this.balanceRepository.findByPayidAndNeedBalanceReal(id);
	}
	@Override
	@Transactional
	public boolean balance(String json) {
		List<Balance> balances=JsonUtils.getGson().fromJson(json, new TypeToken<List<Balance>>() {}.getType());
		// 更新订单
		boolean reso=this.orderQueryService.updateOrderWithBalances(balances);
		//添加平账和收款
		boolean resb=this.saveAll(json);
		return reso && resb;
	}
	@Override
	public String findStatistics(Long paymentid) {
		List<Double> result = this.balanceRepository.findStatistics(paymentid);
		return JsonUtils.getGson().toJson(result);
	}
	@Override
	public String findAdvitemStaticsByPayid(Long paymentid) {
		List<Double> result=this.balanceRepository.findAdvitemStaticsByPayid(paymentid);
		return result.size() >0?JsonUtils.getGson().toJson(result.get(0)):"";
	}




}
