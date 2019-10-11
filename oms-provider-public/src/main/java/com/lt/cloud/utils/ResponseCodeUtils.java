package com.lt.cloud.utils;

public class ResponseCodeUtils {
	public static String responseErr(String message) {
		return "{\"message\":\""+message+"\",\"ok\":false}";
	}
	public static String responseOk(String message) {
		return "{\"message\":\""+message+"\",\"ok\":true}";
	}
	public static String err(Exception e) {
		return "{\"message\":\""+e.getMessage()+"\",\"ok\":false}";
	}
}
