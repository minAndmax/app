package com.army.service.user;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.army.vo.UserInfo;

public interface UserLoginService {

	//用户登录
	JSONObject userLogin(HttpServletRequest request,UserInfo userLogin) throws Exception;
	
	//用户注册
	JSONObject userRegist(UserInfo userLogin) throws Exception;
	
}
