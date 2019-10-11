package com.lt.cloud;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.reflect.TypeToken;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.Balance;
import com.lt.cloud.pojo.BalanceReceiver;
import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.InvoiceReceiver;
import com.lt.cloud.pojo.PagePojo;
import com.lt.cloud.pojo.Payment;
import com.lt.cloud.pojo.PaymentReceive;
import com.lt.cloud.service.BalanceService;
import com.lt.cloud.service.InvoiceService;
import com.lt.cloud.service.PaymentService;
import com.lt.cloud.utils.JsonUtils;

import net.bytebuddy.asm.Advice.This;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmProviderPaymentApplicationTests {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private InvoiceService invoiceService;
	@Test
	public void contextLoads() {
	}
//	@Test
//	public void findAllPayment() {
//		PaymentReceive receiver=new PaymentReceive();
////		receiver.setPinvoiceno("02447794");
////		receiver.setpCustomer("闽ADH331");
////		receiver.setHasleft(null);
////		receiver.setP_Org_ID(13l);
//		receiver.setP_SrcID(26585);
//		String json=JsonUtils.getGson().toJson(receiver);
//		System.out.println(JsonUtils.formatPageForPagination(this.paymentService.findAll(json)));
//	}
//	@Test
//	public void findAllBalance() {
//		BalanceReceiver receiver=new BalanceReceiver();
////		receiver.setSYS_DOCUMENTID(34259L);
////		receiver.setB_InvoiceNo("06946435");
////		receiver.setCustomer("闽ADH331");
//		receiver.setB_Org("公告,政务专版");
//		String json=JsonUtils.getGson().toJson(receiver);
//		System.out.println(JsonUtils.formatPageForPagination(this.balanceService.findAll(json)));
//	}
//	@Test
//	public void findAllInvoice() {
//		InvoiceReceiver receiver=new InvoiceReceiver();
//		receiver.setInvoiceno("00000027");
////		String json=JsonUtils.getGson().toJson(receiver);
//		System.out.println(JsonUtils.formatPageForPagination(this.invoiceService.findAll(receiver)));
//	}
//	@Test 
//	public void findAdvidByPayid() {
//		System.out.println(JsonUtils.getGson().toJson(this.balanceService.findAdvidByPayid(26603l)));
//	}
//	@Test
//	public void findAdvidByPayidAndPagable() {
//		System.out.println("****************************************根据收款id查询广告ID******************************************");
//		BalanceReceiver receiver=new BalanceReceiver();
//		receiver.setB_PayID(26624l);
//		receiver.setPageIndex(1);
//		receiver.setPageSize(10);
//		System.out.println(this.balanceService.findAdvidByPayidAndPagable(JsonUtils.getGson().toJson(receiver)));
//		PagePojo<Long> pagePojo=JsonUtils.getGson().fromJson(this.balanceService.findAdvidByPayidAndPagable(JsonUtils.getGson().toJson(receiver)), PagePojo.class);
//		System.out.println(pagePojo.getTotal());
//		System.out.println(JsonUtils.getGson().toJson(pagePojo.getRows()));
//		System.out.println("*******************************************************************************");
//	}
//	@Test
//	public void findAdvByPayidAndPagable() {
//		System.out.println("****************************************根据收款id查询广告******************************************");
//		BalanceReceiver receiver=new BalanceReceiver();
//		receiver.setB_PayID(26624l);
//		receiver.setPageIndex(1);
//		receiver.setPageSize(2);
//		System.out.println(this.balanceService.findAdvByPayidAndPagable(JsonUtils.getGson().toJson(receiver)));
//		System.out.println("*******************************************************************************");
//	}
//	@Test
//	public void findByPayidAndNeedBalanceReal() {
//		System.out.println("******************************根据收款id，并且B_Amount-B_AmountReal>0查询平账纪录*************************************************");
//		List<Balance> result = this.balanceService.findByPayidAndNeedBalanceReal(26641l);
//		for (Balance balance : result) {
//			System.out.println("收款号："+balance.getB_PayID()+"  平账号："+balance.getSYS_DOCUMENTID()+"  平账金额："+balance.getB_Amount()+"  实平账金额:"+balance.getB_AmountReal());
//		}
//		System.out.println("*******************************************************************************");
//	}
//	@Test
//	public void sortbalance( ) {
//		System.out.println("*****************************************排序**************************************");
//		List<Balance> balances=new ArrayList<>();
//		double amount=7.0;//本期可平账金额
//		for (Integer i = 10; i >1; i--) {
//			Balance balance=new Balance();
//			balance.setB_Amount(i.doubleValue());
//			balance.setB_AmountReal(1.0);
//			if (i%2==0) {
//				balance.setB_AdItemID(2l);
//			}else {
//				balance.setB_AdItemID(1l);
//			}
//			balances.add(balance);
//		}
//		for (Balance balance : balances) {
//			System.out.println(balance.getB_Amount()-balance.getB_AmountReal());
//		}
//		System.out.println("-----------------");
//		List<Balance> result = this.balanceService.getAllBalanceCanBalanceReal(this.balanceService.sortedAsc(balances), 4);
//		List<Balance> resultClone=JsonUtils.getGson().fromJson(JsonUtils.getGson().toJson(result), new TypeToken<List<Balance>>() {}.getType());
//		for (Balance balance : result) {
//			System.out.println(balance.getB_Amount()-balance.getB_AmountReal());
//		}
//		System.out.println("-----------------");
//		for (Balance balance : result) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  平账金额："+balance.getB_Amount()+"  本次实平账金额:"+balance.getB_AmountReal());
//		}
//		System.out.println("--------平账实平账纪录更新---------");
//		List<Balance> balancesWithBalanceReal4BalanceUpdate = this.balanceService.getBalancesWithBalanceReal4BalanceUpdate(resultClone, amount);
//		for (Balance balance : balancesWithBalanceReal4BalanceUpdate) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  平账金额："+balance.getB_Amount()+"  实平账金额:"+balance.getB_AmountReal());
//		}
//		System.out.println("--------平账实平账更新---------");
//		List<Balance> balancesforbalancereal = this.balanceService.getBalancesForBalanceReal(result, amount);
//		for (Balance balance : balancesforbalancereal) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  平账金额："+balance.getB_Amount()+"  实平账金额:"+balance.getB_AmountReal());
//		}
//		System.out.println("--------平账实平账广告id不重复---------");
//		List<Balance> balances4BalanceRealWithAdvidDistinct = this.balanceService.getBalances4BalanceRealWithAdvidDistinct(balancesforbalancereal);
//		for (Balance balance : balances4BalanceRealWithAdvidDistinct) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  平账金额："+balance.getB_Amount()+"  本次实平账金额:"+balance.getB_AmountReal());
//		}
//		System.out.println("--------获取实平账广告信息---------");
//		List<Advitem> advitems4balancereal=this.balanceService.getAdvForBalanceReal(balancesforbalancereal);
//		for (Advitem advitem : advitems4balancereal) {
//			System.out.println("订单号："+advitem.getAI_OrderID()+"  广告号："+advitem.getSYS_DOCUMENTID()+"  本实平账金额:"+advitem.getAI_AmountReceived());
//		}
//		System.out.println("--------获取广告id不重复的实平账广告信息---------");
//		List<Advitem> advitemswithadviddistinct=this.balanceService.getAdvForBalanceReal(balances4BalanceRealWithAdvidDistinct);
//		for (Advitem advitem : advitemswithadviddistinct) {
//			System.out.println("订单号："+advitem.getAI_OrderID()+"  广告号："+advitem.getSYS_DOCUMENTID()+"  本次实平账金额:"+advitem.getAI_AmountReceived());
//		}
//		System.out.println("--------获取本期实平账总金额---------");
//		System.out.println(this.balanceService.getTotalAmountRealAboutAdvitems(advitemswithadviddistinct));
//		
//	
//		System.out.println("*******************************************************************************");
//		
//	}
	@Test
	public void balanceFindStatistics() {
		System.out.println("********************************根据收款id统计平账金额和实平金额***********************************************");
		System.out.println(this.balanceService.findStatistics(26659l));
		System.out.println("*******************************************************************************");
	}
//	@Test
//	public void findAdvitemStaticsByPayid() {
//		System.out.println("*******************************根据收款ID，统计广告的平账情况************************************************");
//		System.out.println(this.balanceService.findAdvitemStaticsByPayid(26659l));
//		System.out.println("*******************************************************************************");
//	}
//	@Test
//	public void findByPayidAndNeedInvoice() {
//		System.out.println("**********************************根据【收款id】查询需要【开票】的【平账】*********************************************");
//		List<Balance> balances=this.balanceService.findByPayidAndNeedInvoice(26673l);
//		if (balances.size()==0) {
//			return;
//		}
//		Double invoiceablemoney=21.0;
//		Payment source=new Payment();
//		source.setPinvoiceno("0000014");
//		source.setPinvoiceablemoney(invoiceablemoney);
//		System.out.println("-----需要开票的纪录------");
//		for (Balance balance : balances) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		List<Balance> sortedUninvoiceAsc = this.balanceService.sortedUninvoiceAsc(balances);
//		List<Balance> balancesWhichCanInvoice = this.balanceService.getAllBalanceWhichCanInvoice(sortedUninvoiceAsc, invoiceablemoney);
//		System.out.println("-----可开票的纪录------");
//		for (Balance balance : balancesWhichCanInvoice) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		System.out.println("----------更新平账纪录---------------");
//		List<Balance> balancesWhichCanInvoiceClone=JsonUtils.getGson().fromJson(JsonUtils.getGson().toJson(balancesWhichCanInvoice), new TypeToken<List<Balance>>() {}.getType());
//		List<Balance> balancesWithInvoice4BalanceUpdate = this.balanceService.getBalancesWithInvoice4BalanceUpdate(balancesWhichCanInvoiceClone, source);
//		for (Balance balance : balancesWithInvoice4BalanceUpdate) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		System.out.println("------------本次开票金额-------------");
//		List<Balance> balancesInvoiceNow = this.balanceService.getBalancesInvoiceNow(balancesWhichCanInvoice,invoiceablemoney);
//		for (Balance balance : balancesInvoiceNow) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		System.out.println("-----------获取广告ID不同的开票信息--------------------");
//		List<Balance> balancesInvoiceNowWithAdvidDistinct = this.balanceService.getBalnacesInvoiceNowWithAdvidDistinct(balancesInvoiceNow);
//		for (Balance balance : balancesInvoiceNowWithAdvidDistinct) {
//			System.out.println("平账号："+balance.getSYS_DOCUMENTID()+"  广告号："+balance.getB_AdItemID()+"  开票金额："+balance.getB_AmountInvoiced()+"  未开票金额:"+balance.getB_AmountUninvoiced());
//		}
//		System.out.println("-----------广告开票列表(广告id不重复)--------------------");
//		List<Advitem> advitems=this.balanceService.getAdvForInvoice(balancesInvoiceNowWithAdvidDistinct);
//		for (Advitem advitem : advitems) {
//			System.out.println("订单号："+advitem.getAI_OrderID()+"  广告号："+advitem.getSYS_DOCUMENTID()+"  本次开票金额:"+advitem.getAI_InvoicedMoney());
//		}
//		System.out.println("-----------获取本次开票总金额--------------------");
//		Double invoicedmoney=this.balanceService.getTotalInvoicedMoney(advitems);
//		System.out.println(invoicedmoney);
//		System.out.println("*******************************************************************************");
//	}
//	@Test
//	public void autoInvoice() {
//		System.out.println("******************************测试自动开票*************************************************");
//		Payment payment=this.paymentService.findById(26682l);
//		Invoice invoice=new Invoice();
//		invoice.setInvoiceno("222223");
//		invoice.setI_Amount(33.0);
//		payment.setPinvoiceno(invoice.getInvoiceno());
//		payment.setPinvoicedmoney(payment.getPinvoicedmoney()+invoice.getI_Amount());
//		payment.setPinvoiceablemoney(payment.getPinvoiceablemoney()-invoice.getI_Amount());
//		System.out.println("已经开票："+payment.getPinvoicedmoney()+"   未开票:"+payment.getPinvoiceablemoney());
//		balanceService.autoInvoice(payment);
//		System.out.println("*******************************************************************************");
//	}
	@Test
	public void test() {
		String source="1234,2560,7890";
		String des="7890";
		String resutl="";
		for (String string : source.split(",")) {
			if (!string.equals(des)) {
				resutl+=string+",";
			}
		}
		resutl=resutl.substring(0, resutl.length()-1);
		System.out.println(resutl);
	}
	@Test
	public void findPaymentUnbalancedWithInvoiceno() {
		System.out.println(JsonUtils.getGson().toJson(this.paymentService.findPaymentUnbalancedWithInvoiceno("05989506")));
	}
}
