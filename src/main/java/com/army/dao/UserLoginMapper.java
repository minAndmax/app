package com.army.dao;

import com.alibaba.fastjson.JSONObject;
import com.army.vo.UserLogin;

public interface UserLoginMapper {

	//用户登录
	UserLogin userLogin(UserLogin userLogin) throws Exception;   
	
	//用户注册
	JSONObject userRegist(UserLogin userLogin) throws Exception;
	
	//根据用户名查询，注册时去重
	UserLogin findUserLoginByUserName(String userName)throws Exception;

}
