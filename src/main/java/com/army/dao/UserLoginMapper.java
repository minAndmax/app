package com.army.dao;

import com.alibaba.fastjson.JSONObject;
import com.army.vo.UserInfo;

public interface UserLoginMapper {

	//用户登录
	UserInfo userLogin(UserInfo userLogin) throws Exception;   
	
	//用户注册
	JSONObject userRegist(UserInfo userLogin) throws Exception;
	
	//根据用户名查询，注册时去重
	UserInfo findUserLoginByUserName(String userName)throws Exception;

}
