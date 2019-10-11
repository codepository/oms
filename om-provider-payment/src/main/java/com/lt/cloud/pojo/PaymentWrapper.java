package com.lt.cloud.pojo;

public class PaymentWrapper {
	private Payment newpayment;
	private Payment oldpayment;
	public Payment getNewpayment() {
		return newpayment;
	}
	public void setNewpayment(Payment newpayment) {
		this.newpayment = newpayment;
	}
	public Payment getOldpayment() {
		return oldpayment;
	}
	public void setOldpayment(Payment oldpayment) {
		this.oldpayment = oldpayment;
	}
	
}
