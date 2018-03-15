package com.army.service.user;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.UserInfo;

public interface UserLoginService {

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	JSONObject userLogin(HttpServletRequest request, UserInfo userLogin) throws Exception;

	/**
	 * 用户注册
	 * 
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	JSONObject userRegist(UserInfo userLogin) throws Exception;

	/**
	 * 查找所有用户
	 * 
	 * @param uinfo
	 * @return
	 * @throws Exception
	 */
	JSONArray findAllUserInfo(UserInfo uInfo) throws Exception;

	/**
	 * 修改用户状态
	 * 
	 * @param request
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	JSONObject updateUser(HttpServletRequest request, UserInfo uInfo) throws Exception;

	/**
	 * 根据ID查找用户
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	JSONObject findUserById(UserInfo uInfo) throws Exception;
}
