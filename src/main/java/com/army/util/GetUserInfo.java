package com.army.util;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class GetUserInfo {

	public static JSONObject requests(HttpServletRequest request) {
		
		JSONObject obj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		
		return obj;
	}
	
	public static String getUserName(HttpServletRequest request) {
		
		JSONObject obj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		
		String userName = obj.getString("userName");
		
		return userName;
	}
	
	
	

}
