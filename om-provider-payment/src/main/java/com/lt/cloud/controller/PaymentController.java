package com.lt.cloud.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.dao.jpa.CollectionModeRepository;
import com.lt.cloud.interceptor.RequiredPermission;
import com.lt.cloud.pojo.CollectionMode;
import com.lt.cloud.service.PaymentService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

import reactor.core.publisher.Mono;

@RestController
@RefreshScope
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CollectionModeRepository collectionModeRepository;
	@RequestMapping("/findAll")
	public String selectAllPayment(@RequestBody String json) {
		return this.paymentService.findAllAsJson(json);
	}
	/**
	 * 根据广告ID查询收款
	 * @param advitemID
	 * @return
	 */
	@RequestMapping("/findByAdvitemID/{advitemID}")
	public String findByAdvitemID(@PathVariable Long advitemID) {
		return this.paymentService.findByAdvitemID(advitemID);
	}
	@RequestMapping("/findAllCollectionMode")
	public Mono<String> findAllCollectionMode(){
		List<CollectionMode> modes=this.collectionModeRepository.findAll();
		return Mono.just(JsonUtils.getGson().toJson(modes)).retry();
	}
	@RequestMapping("/save")
	@RequiredPermission()
	public String save(@RequestBody String json ) {
		this.paymentService.save(json);
		return ResponseCodeUtils.response(true);
	}
	@RequestMapping("/update")
	@RequiredPermission()
	public String update(@RequestBody String json ){
		
		return ResponseCodeUtils.response(this.paymentService.update(json));
	}
	@RequestMapping("/updateInvoice")
	@RequiredPermission()
	public String updateInvoice(@RequestBody String json ){
		
		return ResponseCodeUtils.response(this.paymentService.updateInvoice(json));
	}
	@RequestMapping("/findById/{id}")
	public Mono<String> findById(@PathVariable Long id){
		return Mono.just(JsonUtils.getGson().toJson(this.paymentService.findById(id)));
	}
	@RequestMapping("/deleteById/{id}")
	@RequiredPermission()
	public String deleteById(@PathVariable Long id ) {
		try {
			this.paymentService.deleteById(id);
			return ResponseCodeUtils.response(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping("/moneyback")
	@RequiredPermission()
	public String moneyback(@RequestBody String pojos ) {
		return ResponseCodeUtils.response(this.paymentService.moneyback(pojos));
	}
	@RequestMapping("/isBalancedByInvoice/{invoiceno}")
	public boolean isBalancedByInvoice(@PathVariable String invoiceno) {
		return this.paymentService.isBalancedByInvoice(invoiceno);
	}
	@RequestMapping("/transferMoney")
	@RequiredPermission()
	public String transferMoney(@RequestBody String pojos) {
		return ResponseCodeUtils.response(this.paymentService.transferMoney(pojos));
	}
}
