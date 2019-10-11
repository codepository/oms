package com.lt.cloud;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.lt.cloud.pojo.InvoiceReceiver;
import com.lt.cloud.service.InvoiceService;
import com.lt.cloud.utils.DateUtil;
import com.lt.cloud.utils.JsonUtils;
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvooiceServiceTests {
	@Autowired
	private InvoiceService invoiceService;
	
	@Test
	public void findAll() {
		System.out.println("*******************查询所有***************************");
		InvoiceReceiver invoiceReceiver=new InvoiceReceiver();
		invoiceReceiver.setInvoiceno("05989524");
		invoiceReceiver.setI_DateStart(DateUtil.parseDefaultDate("2019-03-06"));
		invoiceReceiver.setI_DateEnd(DateUtil.parseDefaultDate("2019-03-06"));
		this.invoiceService.findAll(invoiceReceiver);
		System.out.println(JsonUtils.formatPageForPagination(this.invoiceService.findAll(invoiceReceiver)));
		System.out.println("**********************************************");
	}
	@Test
	public void test() {
		Date date1=new Date();
		Date date2=DateUtil.addDays(date1, 300);
		System.out.println(DateUtil.daysSubstract(date1, date2));
	}
}
