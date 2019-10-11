package com.lt.cloud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lt.cloud.interceptor.RequiredPermission;
import com.lt.cloud.pojo.Invoice;
import com.lt.cloud.pojo.InvoiceReceiver;
import com.lt.cloud.service.InvoiceService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;

@RefreshScope
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	@RequestMapping("/findAll")
	public String findAll(@RequestBody String json){
		InvoiceReceiver invoiceReceiver=JsonUtils.getGson().fromJson(json, InvoiceReceiver.class);
		Page<Invoice> page=invoiceService.findAll(invoiceReceiver);
		return JsonUtils.formatPageForPagination(page);
	}
	@RequestMapping("/save")
	@RequiredPermission()
	public String save(@RequestBody String json ){
//		System.out.println("*****************保存发票*******************************");
		Invoice receiver=JsonUtils.getGson().fromJson(json, Invoice.class);
		Invoice invoice = this.invoiceService.save(receiver);
		return ResponseCodeUtils.response(invoice==null);
		
	}
	@RequestMapping("/saveWithPayment")
	@RequiredPermission()
	public String saveWithPayment(@RequestBody String pojos ) {
		return ResponseCodeUtils.response(this.invoiceService.saveWithPayment(pojos));
	}
	@RequestMapping("/update")
	@RequiredPermission()
	public String update(@RequestBody String json ){
		this.invoiceService.update(json);
		return ResponseCodeUtils.response(true);
	}
	@RequestMapping("/deleteById/{id}")
	@RequiredPermission()
	public String deleteById(@PathVariable Long id ) {
		try {
			this.invoiceService.deleteById(id);
			return ResponseCodeUtils.response(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping("/deleteInvoice")
	@RequiredPermission()
	public String deleteInvoice(@RequestBody String invoice ) {
		return ResponseCodeUtils.response(this.invoiceService.deleteInvoice(invoice));
	}
	@RequestMapping("/checkInvoiceno/{invoiceno}")
	public boolean checkInvoiceno(@PathVariable String invoiceno) {
		return this.invoiceService.checkInvoiceno(invoiceno);
	}
	@RequestMapping("/invoiceBack")
	@RequiredPermission()
	public String invoiceBack(@RequestBody String json ) {
		return ResponseCodeUtils.response(this.invoiceService.invoiceBack(json));
	}
	@RequestMapping("/getLastInvoiceno")
	public String getLastInvoiceno() {
		return this.invoiceService.getLastInvoiceno();
	}
}
