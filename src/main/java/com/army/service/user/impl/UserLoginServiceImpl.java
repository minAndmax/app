package com.army.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.army.dao.UserLoginMapper;
import com.army.service.user.UserLoginService;
import com.army.util.MD5Util;
import com.army.util.StatusEnum;
import com.army.vo.UserLogin;

@Service(value = "userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	private static final Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserLoginMapper userLoginMapper;

	public JSONObject userLogin(UserLogin userLogin) throws Exception {

		JSONObject obj = new JSONObject();

		userLogin.setUserPassword(MD5Util.getMD5(userLogin.getUserPassword()));

		UserLogin user = userLoginMapper.userLogin(userLogin);

		if (user == null) {
			obj.put("loginTip", "登录失败，用户名或密码错误");
			obj.put("loginStatus", StatusEnum.FAIL.getNum()); // 0表示登录失败
		} else {
			obj.put("userName", user.getUserName());
			obj.put("loginTip", "登录成功");
			obj.put("loginStatus", StatusEnum.SSUCCESS.getNum());
		}
		log.info("执行了 userLogin: " + obj);
		return obj;
	}

	public JSONObject userRegist(UserLogin userLogin) throws Exception {

		JSONObject obj = new JSONObject();

		UserLogin ul = userLoginMapper.findUserLoginByUserName(userLogin.getUserName());

		if (ul != null) {

			obj.put("userRegist", StatusEnum.FAIL.getNum());
			obj.put("registTip", "用户名已存在，请重新输入");

			log.info("执行了 userRegist,用户注册:注册失败，用户名已存在");

		} else {

			userLogin.setUserPassword(MD5Util.getMD5(userLogin.getUserPassword()));

			userLoginMapper.userRegist(userLogin);

			obj.put("userRegist", StatusEnum.SSUCCESS.getNum());
			obj.put("registTip", "用户注册成功");

			log.info("执行了 userRegist,用户注册:注册成功");
		}

		return obj;
	}

}
