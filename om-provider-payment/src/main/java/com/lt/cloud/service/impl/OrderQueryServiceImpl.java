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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.lt.cloud.config.Settings;
import com.lt.cloud.dao.jpa.AdvorderRepository;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.Advorder;
import com.lt.cloud.pojo.AdvorderReceiver;
import com.lt.cloud.pojo.Balance;
import com.lt.cloud.pojo.OrderAndAdvitem;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.service.AdvitemUpdateSevice;
import com.lt.cloud.service.OrderQueryService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

@Component
public class OrderQueryServiceImpl implements OrderQueryService{
	 private Logger logger = LoggerFactory.getLogger(OrderQueryService.class);
	@Autowired
	private AdvorderRepository advorderRepository;
	@Autowired
	private AdvitemQueryService advitemQueryService;
	@Autowired
	private AdvitemUpdateSevice advitemUpdateSevice;
	@Override
	public Page<Advorder> findAll(AdvorderReceiver receiver) {
		Specification<Advorder> specification=new Specification<Advorder>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Advorder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates=new ArrayList<>();
				if(!StringUtils.isEmpty(receiver.getAO_Customer())) {
					predicates.add(criteriaBuilder.like(root.get("AO_Customer"), "%"+receiver.getAO_Customer()+"%"));
				}
				if (receiver.getEndDate()!=null) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("SYS_CREATED"),receiver.getEndDate()));
				}
				if (receiver.getStartDate()!=null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("SYS_CREATED"),receiver.getStartDate()));
				}
				if (receiver.getSYS_DOCUMENTID()!=null) {
					predicates.add(criteriaBuilder.equal(root.get("SYS_DOCUMENTID"), receiver.getSYS_DOCUMENTID()));
				}
				if (!StringUtils.isEmpty(receiver.getAO_Salesman())) {
					predicates.add(criteriaBuilder.equal(root.get("AO_Salesman"), receiver.getAO_Salesman()));
				}
				if (!StringUtils.isEmpty(receiver.getSYS_AUTHORS())) {
					predicates.add(criteriaBuilder.equal(root.get("SYS_AUTHORS"), receiver.getSYS_AUTHORS()));
				}
				if (!StringUtils.isEmpty(receiver.getAO_Org())) {
					predicates.add(criteriaBuilder.equal(root.get("AO_Org"), receiver.getAO_Org()));
				}
				List<Order> orders=new ArrayList<>();
				orders.add(criteriaBuilder.desc(root.get("SYS_CREATED")));
				return query.where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orders).getRestriction();
			}
		
		};
		return this.advorderRepository.findAll(specification,
				PageRequest.of(
						receiver.getPageIndex()==null?0:(receiver.getPageIndex()-1),
								receiver.getPageSize()==null?10:receiver.getPageSize()));
	}

	@Override
	public Advorder findById(Long id) {
		Optional<Advorder> result = this.advorderRepository.findById(id);
		return result.isPresent()?result.get():null;
	}

	@Override
	public Advorder save(Advorder advOrder) {
		advOrder.setSYS_DELETEFLAG(0);
		if(advOrder.getAO_AllMoney()==null) advOrder.setAO_AllMoney(0.0);
		if(advOrder.getAO_DebtMoney()==null) advOrder.setAO_DebtMoney(0.0);
		if(advOrder.getAO_AmountPaid()==null) advOrder.setAO_AmountPaid(0.0);
		if(advOrder.getAO_ReceivedMoney()==null)advOrder.setAO_ReceivedMoney(0.0);
		if(advOrder.getAO_DebtMoney()==null) advOrder.setAO_DebtMoney(0.0);
		return this.advorderRepository.save(advOrder);
	}

	@Override
	public String update(Advorder advOrder) {
		try {
			this.save(advOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.responseErr(e.toString());
		}
		List<Advitem> advitems = this.advitemQueryService.findByAI_OrderID(advOrder.getSYS_DOCUMENTID());
		if (advitems.size()==0) {
			return ResponseCodeUtils.response(true);
		}
		List<Advitem> needUpdate=new ArrayList<>();
		advitems.stream().forEach(adv-> {
			boolean flag = false;
			if (!adv.getAI_Customer().equals(advOrder.getAO_Customer()) || !adv.getAI_Advertiser().equals(advOrder.getAO_Advertiser()) || !adv.getAI_Salesman().equals(advOrder.getAO_Salesman())) {
				flag=true;
				adv.setAI_Customer(advOrder.getAO_Customer());
				adv.setAI_Customer_ID(advOrder.getAO_Customer_ID());
				adv.setAI_Advertiser(advOrder.getAO_Advertiser());
				adv.setAI_Advertiser_ID(advOrder.getAO_Advertiser_ID());
				adv.setAI_Salesman(advOrder.getAO_Salesman());
				adv.setAI_Salesman_ID(Long.parseLong(advOrder.getAO_Salesman_ID()));
				adv.setSYS_LASTMODIFIED(new Date());
			}
			if (flag) {
				needUpdate.add(adv);
			}
		});
		if (needUpdate.size()==0) {
			return ResponseCodeUtils.response(true);
		}
		try {
			this.advitemUpdateSevice.updateAllDirect(needUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.responseErr(e.toString());
		}
		return ResponseCodeUtils.response(true);
	}
	@Override
	public void updateAllWithAdvitemAdd(String advitemList) {
		List<Advitem> list=JsonUtils.getGson().fromJson(advitemList,new TypeToken<List<Advitem>>() {}.getType());
		this.updateAllWithAdvitemlist(list);
	}
	@Override
	public void updateAllWithAdvitemAdd(List<Advitem> list) {
		this.updateAllWithAdvitemlist(list);
	}
	@Override
    @Transactional
	public boolean updateAllWithAdvitem(String advitemList) {
		List<Advitem> list=JsonUtils.getGson().fromJson(advitemList,new TypeToken<List<Advitem>>() {}.getType());
		try {
			//更新adv
			this.advitemUpdateSevice.updateAll(list);
			//更新order
			this.updateAllWithAdvitemlist(list);
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		
	}
	@Override
	public Advorder updateOrderWithBalance(Advorder old, Advorder neworder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advorder updateOrderWithBalanceReal(Advorder old, Advorder neworder) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public void updateAllWithAdvitemlist(List<Advitem> list) {
		//先找出不同的OrderId
		list.stream().map(e->e.getAI_OrderID()).distinct().forEach(item2->{
			Advorder advorder=this.advorderRepository.findById(item2).get();
			advorder.setSYS_LASTMODIFIED(new Date());
			list.stream().filter(item -> {
				return advorder.getSYS_DOCUMENTID().equals(item.getAI_OrderID());
			}).forEach(item1 -> {
				if(item1.getAI_AmountReceivable()!=null)advorder.setAO_AllMoney(advorder.getAO_AllMoney()+item1.getAI_AmountReceivable());
				if(item1.getAI_AmountPaid()!=null)advorder.setAO_AmountPaid(advorder.getAO_AmountPaid()+item1.getAI_AmountPaid());
				if(item1.getAI_Debt()!=null)advorder.setAO_DebtMoney(advorder.getAO_DebtMoney()+item1.getAI_Debt());
				//已收款变化（或者实平帐金额变化）
				if(item1.getAI_AmountReceived()!=null && item1.getAI_AmountReceived()!=0) {
					advorder.setAO_ReceivedMoney(advorder.getAO_ReceivedMoney()+item1.getAI_AmountReceived());
					advorder.setAO_DebtMoney(advorder.getAO_DebtMoney()-item1.getAI_AmountReceived());
				}
				try {
					this.advorderRepository.save(advorder);
				} catch (Exception e2) {
					logger.debug("更新失败");
					e2.printStackTrace();
					throw new RuntimeException();
				}
				
			});
		}
		);
	}
	@Override
	@Transactional
	public boolean deleteById(Long id) {
		try {
			this.advorderRepository.deleteById(id);
			this.advitemUpdateSevice.deleteByOrderId(id);
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	@Override
	@Transactional
	public boolean updateWithAdvitemDelete(Long id) {
		Advitem advitem = this.advitemQueryService.selectById(id);
		advitem.setAI_AmountReceivable(-advitem.getAI_AmountReceivable());
		advitem.setAI_AmountPaid(-advitem.getAI_AmountPaid());
		advitem.setAI_AmountReceived(-advitem.getAI_AmountReceived());
		advitem.setAI_Debt(-advitem.getAI_Debt());
		try {
			this.advitemUpdateSevice.deleteById(id);
			this.updateAllWithAdvitemsWithAmountChanged(advitem);
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}		
	}


	@Override
	public boolean updateOrderWithBalances(List<Balance> balances) {
		List<Advitem> advitems=new ArrayList<>();
		balances.stream().forEach(balance->{
			Advitem advitem=new Advitem();
			advitem.setSYS_DOCUMENTID(balance.getB_AdItemID());
			advitem.setSYS_CURRENTSTATUS(balance.getSYS_CURRENTSTATUS());
			advitem.setAI_OrderID(balance.getB_OrderID());
			advitem.setAI_BalancedMoney(balance.getB_Amount());
			advitem.setAI_InvoicedMoney(balance.getB_AmountInvoiced());
			advitem.setAI_UninvoicedMoney(balance.getB_AmountUninvoiced());
			advitem.setSYS_CURRENTUSERID(balance.getSYS_CURRENTUSERID());
			advitem.setSYS_CURRENTUSERNAME(balance.getSYS_CURRENTUSERNAME());
			advitem.setAI_InvoiceNo(balance.getB_InvoiceNo());
			switch (advitem.getSYS_CURRENTSTATUS()) {
			case Settings.PAYMENT_TYPE_PREBALANCE:
				advitem.setAI_AmountReceived(balance.getB_AmountReal()==null?0.0:balance.getB_AmountReal());
				break;
			case Settings.PAYMENT_TYPE_BALANCE:
				advitem.setAI_AmountReceived(balance.getB_Amount());
				break;
			case Settings.PAYMENT_TYPE_BALANCREVERSE:
				advitem.setAI_AmountReceived(balance.getB_AmountReal()==null?0.0:balance.getB_AmountReal());
				break;
			default:
				break;
			}
			if (balance.getB_Amount()!=0) advitems.add(advitem);
			
		});
		String advitemList=JsonUtils.getGson().toJson(advitems);
	    // 更新订单
		return this.updateAllWithAdvitem(advitemList);
	}

	@Override
	@Transactional
	public boolean updateOrderAndAdvitem(String pojos) {
		OrderAndAdvitem orderAndAdvitem=JsonUtils.getGson().fromJson(pojos, OrderAndAdvitem.class);
		try {
//			this.advorderRepository.save(entity)
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return false;
	}
	/**
	 *   修改 应收款，营业额，欠款，已收款
	 * @param advitems
	 * @return
	 */
	@Override
	public void updateAllWithAdvitemsWithAmountChanged(Advitem difference) {
		try {
			Advorder order=this.findById(difference.getAI_OrderID());
			order.setAO_AllMoney(order.getAO_AllMoney()+difference.getAI_AmountReceivable());
			order.setAO_AmountPaid(order.getAO_AmountPaid()+difference.getAI_AmountPaid());
			order.setAO_DebtMoney(order.getAO_DebtMoney()+difference.getAI_Debt());
			order.setAO_ReceivedMoney(order.getAO_ReceivedMoney()+difference.getAI_AmountReceived());
			order.setSYS_LASTMODIFIED(new Date());
			this.advorderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
	}


	
	

}
