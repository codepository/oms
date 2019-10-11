package com.lt.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lt.cloud.pojo.AdvitemReceiver;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.utils.JsonUtils;

import net.bytebuddy.asm.Advice.This;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvitemServiceTest {
	@Autowired
	private AdvitemQueryService advitemQueryService;
//	@Test
	public void findAllReports() {
		AdvitemReceiver receiver=new AdvitemReceiver();
		receiver.setPageIndex(1);
		receiver.setPageSize(2);
		receiver.setGroupby("group By AI_Trade,AI_Customer,AI_Salesman");
		receiver.setColumns("AI_Trade,AI_Customer,AI_Salesman,sum(AI_Debt) as AI_Debt,sum(AI_InvoicedMoney) as AI_InvoicedMoney,sum(AI_UninvoicedMoney) as AI_UninvoicedMoney");
		receiver.setWhereby("where AI_PublishTime<='2019-1-31' and AI_PublishTime>='2019-1-15' and AI_Debt>0");
		System.out.println(JsonUtils.getGson().toJson(receiver));
		System.out.println(this.advitemQueryService.findAllReports(JsonUtils.getGson().toJson(receiver)));;
	}
	@Test
	public void FindAllAdvitems() {
		AdvitemReceiver receiver=new AdvitemReceiver();
		receiver.setAI_CustomerLike("中庚");
		System.out.println(this.advitemQueryService.findAll(receiver));
	}
}
