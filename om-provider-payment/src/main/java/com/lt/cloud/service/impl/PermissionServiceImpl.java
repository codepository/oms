package com.lt.cloud.service.impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lt.cloud.service.PermissionService;
import com.lt.cloud.utils.ResponseCodeUtils;

@Component
public class PermissionServiceImpl implements PermissionService{
	@Value("${PERMISSION_URL}")
	private String PERMISSION_URL;
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public String hasPermission(String token, String url) {
		String URI="http://"+PERMISSION_URL+"/user/permission/hasPermission?token={token}&url={url}";
		Map<String, String> uriVariables=new HashMap<>();
		uriVariables.put("token", token);
		uriVariables.put("url", url);
		String result=null;
		try {
			result=this.restTemplate.getForObject(URI, String.class, uriVariables);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.responseErr(e.toString());
		}
		return result;
	}

}
