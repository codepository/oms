package com.lt.cloud;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.AdvitemReceiver;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.service.OrderQueryService;
import com.lt.cloud.service.impl.FileService;
import com.lt.cloud.utils.DateUtil;
import com.lt.cloud.utils.JsonUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsProviderOrderApplicationTests {
	@Autowired
	private AdvitemQueryService advitemQueryService;
	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private FileService<Advitem> fileService;
	
//	@Test
//	public void contextLoads() {
//		AdvitemReceiver receiver=new AdvitemReceiver();
//		receiver.setAI_Trade("旅游酒店");
//		System.out.println(this.advitemQueryService.findStatistics(JsonUtils.getGson().toJson(receiver)));;
//	}
//	@Test
//	public void findAllAdv() {
////		String json="{\"SYS_DOCUMENTID\":\"61098\"}";
////		
////		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
//		AdvitemReceiver receiver=new AdvitemReceiver();
////		receiver.setAI_PublishTimeStart(DateUtil.parseDefaultDate("2018-01-16"));
////		receiver.setAI_PublishTimeEnd(DateUtil.parseDefaultDate("2018-01-31"));
//		receiver.setAI_Customer("恒大集团");
//		System.out.println(JsonUtils.getGson().toJson(receiver));
//		System.out.println(this.advitemQueryService.findAll(receiver));
//	}
//	@Test
//	public void findByAdvids() {
//		System.out.println("**************************测试：根据 id数组来查询*********************************");
//		String json="[61096]";
//		System.out.println(this.advitemQueryService.findByAdvids(json));
//	}
//	@Test
//	public void findStatistics() {
//		System.out.println("**************************统计数据*********************************");
//		AdvitemReceiver receiver=new AdvitemReceiver();
//		receiver.setAI_PublishTimeStart(DateUtil.parseDefaultDate("2018-01-17"));
//		receiver.setAI_PublishTimeEnd(DateUtil.parseDefaultDate("2018-01-31"));
//		System.out.println(this.advitemQueryService.findStatistics(JsonUtils.getGson().toJson(receiver)));
//		System.out.println("************************************************************");
//	}
//	@Test
//	public void findAllNotPage() {
//		AdvitemReceiver receiver=new AdvitemReceiver();
//		receiver.setColumns("AI_Customer");
//		receiver.setAI_Customer("恒大集团");
//		System.out.println(JsonUtils.getGson().toJson(this.fileService.findAllAndNotPage(JsonUtils.getGson().toJson(receiver))));
//	}
	@Test
	public void findAllAsHashMap() {
		AdvitemReceiver receiver=new AdvitemReceiver();
		receiver.setColumns("AI_Customer,AI_OrderID,AI_PublishTime,AI_Debt");
		receiver.setAI_Customer("恒大集团");
//		
//		String columns=receiver.getColumns();
//		List<HashMap<String, Object>> result = this.fileService.findAllAsHashMap(receiver);
//		List<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
//		String[] col = columns.split(",");
//		for (HashMap<String, Object> hashMap : result) {
//			Map<String, Object> map=new HashMap<>();
//			for (String key : col) {
//				map.put(key, hashMap.get(key));
//			}
//		}
		System.out.println(JsonUtils.getGson().toJson(this.fileService.findAllAsHashMap(receiver)));
	}
	@Test
	public void test() {
		List<Object> list =new ArrayList<>();
		HashMap<String, Object> map=new HashMap<>();
		double sum=0;
		Double sum1=0.0;
		System.out.println("List:"+(list instanceof List));
		System.out.println("ArrayList:"+(list instanceof ArrayList));
		System.out.println("HashMap:"+(map instanceof List));
		System.out.println("map:"+(map instanceof Map));
	}
	
}
