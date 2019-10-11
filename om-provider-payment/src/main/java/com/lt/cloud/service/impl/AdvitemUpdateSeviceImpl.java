package com.lt.cloud.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.lt.cloud.config.Settings;
import com.lt.cloud.dao.jpa.AdvitemRepository;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.service.AdvitemUpdateSevice;
import com.lt.cloud.service.OrderQueryService;
import com.lt.cloud.utils.DateUtil;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
@Component

public class AdvitemUpdateSeviceImpl implements AdvitemUpdateSevice{
	@Autowired
	private AdvitemRepository advitemRepository;
	@Autowired
	private OrderQueryService orderQueryService;
	@Override
	public Advitem insertAdvitem(Advitem advitem) {
		
		try {
			if (advitem.getSYS_DOCUMENTID()==null) {
				advitem.setSYS_DELETEFLAG(0);
			}
			return this.advitemRepository.save(advitem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean updateAdvitem(Advitem advitem) {
		try {
			this.advitemRepository.save(advitem);
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void deleteById(Long id) {
		this.advitemRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Boolean saveAll(List<Advitem> advitems) {
		advitems.stream().map(item->{
			item.setSYS_LASTMODIFIED(new Date());
			if(item.getSYS_DOCUMENTID()==null) {
				item.setSYS_DELETEFLAG(0);
				item.setSYS_CREATED(new Date());
				item.setAI_PayStatus(Settings.BALANCE_NONE);
				if(StringUtils.isEmpty(item.getAI_AmountPaid())) {
					item.setAI_AmountPaid(item.getAI_AmountReceivable());
				}
				if (StringUtils.isEmpty(item.getAI_PublishEndTime())) {
					item.setAI_PublishEndTime(item.getAI_PublishTime());
					item.setAI_PublishDayCount(1);
				}else {
					item.setAI_PublishDayCount(DateUtil.daysSubstract(item.getAI_PublishEndTime(),item.getAI_PublishTime()));
				}
			}
			item.setAI_UnitPrice(item.getAI_Price());
			if (StringUtils.isEmpty(item.getAI_UnbalancedMoney())) {
				item.setAI_UnbalancedMoney(item.getAI_AmountReceivable()-item.getAI_BalancedMoney());
			}
			if(StringUtils.isEmpty(item.getAI_UninvoicedMoney())) {
				item.setAI_UninvoicedMoney(item.getAI_AmountReceivable()-item.getAI_InvoicedMoney());
			}
			if (StringUtils.isEmpty(item.getAI_Debt())) {
				item.setAI_Debt(item.getAI_AmountReceivable()-item.getAI_AmountReceived());
			}
			return item;
		}).forEach(this.advitemRepository::save);
		return true;
	}

	@Override
	public Advitem updateAdvitemWithBalance(Advitem old, Advitem newadv) {
		//修改平帐金额
		if (old.getAI_BalancedMoney()==null) {
			old.setAI_BalancedMoney(0.0);
		}
		old.setAI_BalancedMoney(old.getAI_BalancedMoney()+newadv.getAI_BalancedMoney());//平帐为平帐金额
		if(old.getAI_BalancedMoney()>0) {
			old.setAI_PayTime(new Date());
			if (old.getAI_BalancedMoney()<old.getAI_AmountReceivable()) {//平帐金额小于总额
				old.setAI_PayStatus(Settings.BALANCE_PART);
			}else {
				old.setAI_PayStatus(Settings.BALANCE_COMPLETE);
			}
		}
		//未平账金额更新
		old.setAI_UnbalancedMoney(old.getAI_AmountReceivable()-old.getAI_BalancedMoney());
		//收款和欠款更新
		old=this.updateAmountReceived(old, newadv);
		
		//修改发票
		old=this.updateInvoice(old, newadv);
		return old;
	}

	@Override
	public Advitem updateInvoice(Advitem old, Advitem newadv) {
		//修改开票金额
		if (newadv.getAI_InvoicedMoney()!=null) {
			if(old.getAI_InvoicedMoney()==null) old.setAI_InvoicedMoney(0.0);
			old.setAI_InvoicedMoney(old.getAI_InvoicedMoney()+newadv.getAI_InvoicedMoney());
			old.setAI_UninvoicedMoney(old.getAI_UninvoicedMoney()-newadv.getAI_InvoicedMoney());
			if (old.getAI_InvoicedMoney()<0) {
				old.setAI_UninvoicedMoney(old.getAI_UninvoicedMoney()+old.getAI_InvoicedMoney());
				old.setAI_InvoicedMoney(0.0);
			}
		}
		// 修改发票号码
		if (old.getAI_InvoicedMoney()>0) {//如果开票金额大于0,就更新发票号吗
			if (StringUtils.isEmpty(old.getAI_InvoiceNo())) {
				old.setAI_InvoiceNo(newadv.getAI_InvoiceNo());
			} else {
				old.setAI_InvoiceNo(","+newadv.getAI_InvoiceNo());
			}
		}else {
			old.setAI_InvoiceNo("");
		}
		return old;
	}
	@Override
	public Advitem updateAmountReceived(Advitem old, Advitem newadv) {
		//收款金额更新
		if(old.getAI_AmountReceived()==null) old.setAI_AmountReceived(0.0);
		old.setAI_AmountReceived(old.getAI_AmountReceived()+newadv.getAI_AmountReceived());//收款为【实】平帐金额
		//欠款金额更新
		old.setAI_Debt(old.getAI_Debt()-newadv.getAI_AmountReceived());
		
		if (old.getAI_AmountReceived()<0) {
			old.setAI_Debt(old.getAI_Debt()+old.getAI_AmountReceived());
			old.setAI_AmountReceived(0.0);
		}
		if (old.getAI_Debt()<0) {
			old.setAI_AmountReceived(old.getAI_AmountReceived()+old.getAI_Debt());
			old.setAI_Debt(0.0);
		}
		return old;
	}
	@Override
	public Advitem updateAdvitemWithMoneyback(Advitem old, Advitem newadv) {
		//收款和欠款更新
		old=this.updateAmountReceived(old, newadv);
		return old;
	}

	@Override
	public Advitem updateAdvitemWithPreBalance(Advitem old, Advitem newadv) {
		//修改平帐金额
		if(old.getAI_BalancedMoney()==null) old.setAI_BalancedMoney(0.0);
		old.setAI_BalancedMoney(old.getAI_BalancedMoney()+newadv.getAI_BalancedMoney());//平帐为平帐金额
		if(old.getAI_BalancedMoney()>0) {
			old.setAI_PayTime(new Date());
			if (old.getAI_BalancedMoney()<old.getAI_AmountReceivable()) {//平帐金额小于总额
				old.setAI_PayStatus(Settings.BALANCE_PART);
			}else {
				old.setAI_PayStatus(Settings.BALANCE_COMPLETE);
			}
		}
		//未平账金额更新
		old.setAI_UnbalancedMoney(old.getAI_AmountReceivable()-old.getAI_BalancedMoney());
		//收款和欠款更新
		old=this.updateAmountReceived(old, newadv);
		
		//修改开票金额
		old=this.updateInvoice(old, newadv);
		return old;
	}
	@Override
	public Advitem updateAdvitemWithBalanceReal(Advitem old, Advitem newadv) {
		//收款和欠款更新
		return old=this.updateAmountReceived(old, newadv);
	}
	private Advitem updateAdvitemWithBalanceReverse(Advitem old, Advitem newadv) {
		
		return this.updateAdvitemWithBalance(old, newadv);
	}
	private Advitem updateAdvitemWithInvoice(Advitem old, Advitem newadv) {
		old=this.updateInvoice(old, newadv);
		return old;
	}

	@Override
	@Transactional
	public void updateAll(List<Advitem> advitems) {
		try {
			advitems.stream().map(item->{

				Advitem advitem= this.findById(item.getSYS_DOCUMENTID());
				if(advitem==null) return null;
				switch (item.getSYS_CURRENTSTATUS()) {
				case Settings.PAYMENT_TYPE_BALANCE:
					advitem=this.updateAdvitemWithBalance(advitem,item);
					break;
				case Settings.PAYMENT_TYPE_PREBALANCE:
					advitem=this.updateAdvitemWithPreBalance(advitem,item);
					break;
				case Settings.PAYMENT_TYPE_BALANCEREAL:
					advitem=this.updateAdvitemWithBalanceReal(advitem, item);
					break;
				case Settings.PAYMENT_TYPE_MONEYBACK:
					advitem=this.updateAdvitemWithMoneyback(advitem, item);
				case Settings.PAYMENT_TYPE_BALANCREVERSE:
					advitem=this.updateAdvitemWithBalanceReverse(advitem,item);
					break;
				case Settings.PAYMENT_TYPE_LENDINVOICE:
					advitem=this.updateAdvitemWithInvoice(advitem,item);
					break;
				default:
					throw new RuntimeException("不存在的收款类型【"+item.getSYS_CURRENTSTATUS()+"】");
				}
				advitem.setSYS_LASTMODIFIED(new Date());
				if (item.getSYS_CURRENTUSERID()!=null) {
					advitem.setSYS_CURRENTUSERID(item.getSYS_CURRENTUSERID());
				}
				if (StringUtils.isEmpty(item.getSYS_CURRENTUSERNAME())) {
					advitem.setSYS_CURRENTUSERNAME(item.getSYS_CURRENTUSERNAME());
				}
				return advitem;
			}).forEach(ad->{
				if(ad!=null) this.advitemRepository.save(ad);
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}


	@Override
	@Query
	@Transactional
	public void deleteByOrderId(Long id) {
		advitemRepository.deleteByOrderId(id);
		
	}
	@Override
	public Advitem findById(Long id) {
		Optional<Advitem> advitem = this.advitemRepository.findById(id);
		return advitem.isPresent()?advitem.get():null;
	}

	@Override
	@Transactional
	public Boolean saveNewAdvitems(String json) {
		List<Advitem> advitems=JsonUtils.getGson().fromJson(json, new TypeToken<List<Advitem>>() {}.getType());
		try {
			this.saveAll(advitems);
			this.orderQueryService.updateAllWithAdvitemAdd(advitems);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional
	public boolean updateAdvAndOrder(String advitemList) {
		List<Advitem> advitems=JsonUtils.getGson().fromJson(advitemList, new TypeToken<List<Advitem>>() {}.getType());
		try {
			// 计算广告变化前后金额的变化
			Advitem difference = this.getDifferenceOfMoneyForOrder(advitems);
			for (Advitem advitem : advitems) {
				advitem.setSYS_LASTMODIFIED(new Date());
				advitem.setSYS_CURRENTSTATUS("");
			}
			this.advitemRepository.saveAll(advitems);
			if (this.checkIfAmountChange(difference)) {
				difference.setAI_OrderID(advitems.get(0).getAI_OrderID());
				this.orderQueryService.updateAllWithAdvitemsWithAmountChanged(difference);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 *   查看 应收款，营业额，欠款，已收款是否有变化
	 * @param advitems
	 * @return
	 */
	private boolean checkIfAmountChange(Advitem difference) {
		boolean flag = false;
	    if(difference.getAI_AmountReceivable()==0.0 && difference.getAI_AmountReceived() == 0.0 && difference.getAI_Debt() == 0.0 && difference.getAI_AmountReceived() ==0.0) {
	    	
	    } else {
	    	flag = true;
	    }
//	    System.out.println("-----------价格变化:"+flag);
		return flag;
	}

	/**
	 *  应收款，营业额，欠款，已收款
	 * @param advitems
	 * @return
	 */
	private Advitem getDifferenceOfMoneyForOrder(List<Advitem> advitems) {
		Double differenceReceivable = 0.0;
		Double differencePaid = 0.0;
		Double differenceDebt = 0.0;
		Double differenceReceived= 0.0;
		Advitem result = new Advitem();
		for (Advitem item : advitems) {
			Advitem advitem = this.findById(item.getSYS_DOCUMENTID());
			differenceReceivable += (item.getAI_AmountReceivable() - advitem.getAI_AmountReceivable());
			differencePaid += (item.getAI_AmountPaid() - advitem.getAI_AmountPaid());
			differenceDebt += (item.getAI_Debt() - advitem.getAI_Debt());
			differenceReceived += (item.getAI_AmountReceived() - advitem.getAI_AmountReceived());
		}
		result.setAI_AmountReceivable(differenceReceivable);
		result.setAI_AmountPaid(differencePaid);
		result.setAI_Debt(differenceDebt);
		result.setAI_AmountReceived(differenceReceived);
//		System.out.println("价格变化："+result.getAI_AmountReceivable()+","+result.getAI_AmountPaid());
		return result;
	}

	@Override
	public void updateAllDirect(List<Advitem> advitems) {
		this.advitemRepository.saveAll(advitems);
		
	}

	@Override
	public String updateAdvContentWithMoneyNotChange(List<Advitem> advitems) {
		try {
			this.updateAllDirect(advitems);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.responseErr(e.toString());
		}
		return ResponseCodeUtils.response(true);
	}
	



}
