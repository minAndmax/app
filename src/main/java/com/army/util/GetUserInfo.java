package com.army.util;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class GetUserInfo {

	public static JSONObject requests(HttpServletRequest request) {
		
		JSONObject obj = (JSONObject) request.getAttribute(KeyWord.USERSESSION);
		
		return obj;
	}
	
	
	

}
