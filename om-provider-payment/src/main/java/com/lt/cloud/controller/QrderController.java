package com.lt.cloud.controller;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.interceptor.RequiredPermission;
import com.lt.cloud.pojo.Advorder;
import com.lt.cloud.pojo.AdvorderReceiver;
import com.lt.cloud.service.OrderQueryService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

import net.sf.jsqlparser.statement.update.Update;
import reactor.core.publisher.Mono;
@RestController
@RefreshScope
@RequestMapping("/order")
public class QrderController {
	@Autowired
	private OrderQueryService orderQueryService;
	@RequestMapping("/findById/{id}")
	public Mono<String> selectById(@PathVariable Long id) {
		return Mono.just(JsonUtils.getGson().toJson(this.orderQueryService.findById(id)));
	}
	@RequestMapping("/findAll")
	public String findAll(@RequestBody String json) {
		AdvorderReceiver receiver=JsonUtils.getGson().fromJson(json, AdvorderReceiver.class);
		Page<Advorder> page=this.orderQueryService.findAll(receiver);
		return JsonUtils.formatPageForPagination(page);
	}
	@RequestMapping("/save")
	@RequiredPermission()
	public String save(@RequestBody String json ){
		Advorder advorder=JsonUtils.getGson().fromJson(json, Advorder.class);
		if (advorder.getSYS_DOCUMENTID()==null) {
			advorder.setSYS_CREATED(new Date());
		}
		advorder.setSYS_LASTMODIFIED(new Date());
		this.orderQueryService.save(advorder);
		return ResponseCodeUtils.response(true);
	}
	@RequestMapping("/update")
//	@RequiredPermission()
	public String update(@RequestBody String json) {
		Advorder advorder=JsonUtils.getGson().fromJson(json, Advorder.class);
		return this.orderQueryService.update(advorder);
	}
	@RequestMapping("/deleteById/{id}")
	@RequiredPermission()
	public String deleteById(@PathVariable Long id ) {
		boolean result = this.orderQueryService.deleteById(id);
		return ResponseCodeUtils.response(result);
	}
	/**
	 * 更新广告内容和订单
	 * @param pojos
	 * @return
	 */
	@RequestMapping("/updateOrderAndAdvitem")
	@RequiredPermission()
	public String updateOrderAndAdvitems(@RequestBody String pojos ) {
		return ResponseCodeUtils.response(this.orderQueryService.updateOrderAndAdvitem(pojos));
	}
	@PostMapping("/updateAllWithAdvitem")
	@RequiredPermission()
	public String updateAllWithAdvitem(@RequestBody String advitems ) {
		return ResponseCodeUtils.response(this.orderQueryService.updateAllWithAdvitem(advitems));
		
	}
	@RequestMapping("/updateWithAdvitemDelete/{id}")
	@RequiredPermission()
	public String updateWithAdvitemDelete(@PathVariable Long id ) {
		return ResponseCodeUtils.response(this.orderQueryService.updateWithAdvitemDelete(id));
	}

	

}
