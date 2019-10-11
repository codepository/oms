package com.lt.cloud;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alibaba.druid.pool.DruidDataSource;
import com.esotericsoftware.kryo.serializers.DefaultSerializers.ClassSerializer;
import com.lt.cloud.config.MyCorsFilter;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.lt.cloud.dao.jpa")
@MapperScan("com.lt.cloud.mapper")
public class OmProviderPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmProviderPaymentApplication.class, args);
	}
	@Autowired
	private Environment env;
	@Bean
	public DataSource dataSource(){//必须配置不然LCN无法启动
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
		dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	} 
	@Bean
	public MyCorsFilter corsFilter() throws Exception {
	  return new MyCorsFilter();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
