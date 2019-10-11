package com.lt.cloud.utils;

public class ResponseCodeUtils {
	public static String response(String message) {
		return "{\"message\":\""+message+"\"}";
	}
	public static String response(String message,boolean flag) {
		return "{\"message\":\""+message+"\",\"flag\": \""+flag+"\"}";
	}
	public static String response(boolean flag) {
		return "{\"message\":"+(flag ? "\"成功\"":"\"失败\"")+",\"flag\":"+flag+"}";
	}
	public static String responseErr(String message) {
		return "{\"message\":\""+message+"\",\"flag\": \""+false+"\"}";
	}
}
