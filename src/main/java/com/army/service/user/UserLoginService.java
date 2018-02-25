package com.army.service.user;

import com.alibaba.fastjson.JSONObject;
import com.army.vo.UserLogin;

public interface UserLoginService {

	//用户登录
	JSONObject userLogin(UserLogin userLogin) throws Exception;
	
	//用户注册
	JSONObject userRegist(UserLogin userLogin) throws Exception;
	
}
