package com.lt.cloud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lt.cloud.utils.JsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OmProviderPaymentApplication.class, MockServletContext.class})
public class AdvitemControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void export() {
		try {
			MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/advitem/export")).andReturn();
			System.out.println(JsonUtils.getGson().toJson(mvcResult.getResponse().getContentAsString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
