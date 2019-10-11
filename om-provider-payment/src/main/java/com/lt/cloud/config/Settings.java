package com.lt.cloud.config;

public class Settings {
	public static final int NONE=0;//未平帐
	public static final int COMPLETE=1;//已平帐
	public static final int PART=0;//部分平帐
	public static final String PAYMENT_TYPE_MONEYBACK="借票回款";
	public static final String PAYMENT_TYPE_BALANCE="平账";
	public static final String PAYMENT_TYPE_PREBALANCE="预平账";
	public static final String PAYMENT_TYPE_BALANCEREAL="实平账";
	public static final String PAYMENT_TYPE_BALANCREVERSE="反平账";
	public static final String PAYMENT_TYPE_LENDINVOICE="借票";
	public static final String PAYMENT_TYPE_NORMALPAYMENT="正常收款";
	public static final String PAYMENT_TYPE_TRANSFERIN="转入";
	public static final String PAYMENT_TYPE_TRANSFEROUT = "转出";
	public static final String PAYMENT_TYPE_NORMALINVOICE = "正常发票";
	
	public static final int BALANCE_NONE=0;//未平帐
	public static final int BALANCE_COMPLETE=1;//已平帐
	public static final int BALANCE_PART=0;//部分平帐
	/**
	 * 广告管理权限
	 */
	public static final String ROLE_ORDER_MANAGER="广告管理";
	/**
	 * 收款管理权限
	 */
	public static final String ROLE_PAYMENT_MANAGER="收款管理";
	/**
	 * 平账管理权限
	 */
	public static final String ROLE_BALANCE_MANAGER="平账管理";

}
