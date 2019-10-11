package com.lt.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lt.cloud.pojo.AdvotherReceiver;
import com.lt.cloud.pojo.Pricelistitem;
import com.lt.cloud.service.PrilistItemService;
import com.lt.cloud.utils.JsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsProviderPublicApplicationTests {
	@Autowired
	private PrilistItemService prilistItemService;
	@Test
	public void contextLoads() {
	}
	@Test
	public void pricelistselectall() {
		String json="{\"E_PID\":1,\"E_MID\":1,\"E_AdField_ID\":1,\"E_Color_ID\":1,\"E_AdSize_ID\":32}";
		AdvotherReceiver receiver=JsonUtils.getGson().fromJson(json, AdvotherReceiver.class);
		System.out.println(JsonUtils.getGson().toJson(receiver));
		System.out.println(JsonUtils.getGson().toJson(this.prilistItemService.selectAll(receiver)));;
	}
}
